package com.ruoyi.studentSys.service;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.studentSys.domain.IDCardInfo;
import com.ruoyi.studentSys.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.Date;

@Service
public class IDCardService {


    @Value("${baidu.apiKey}")
    String apiKey;
    @Value("${baidu.secretKey}")
    String secretKey;
    @Value("${baidu.tokenUrl}")
    String tokenUrl;
    @Value("${baidu.idCardUrl}")
    String idCardUrl;

    @Autowired
    RestTemplate restTemplate;

    public AjaxResult getToken(){

        JSONObject jsonObject =  (JSONObject)  CacheUtils.get("baidu_token");
        if(jsonObject!=null)
        {
            Date lastDate =  (Date) jsonObject.get("last_date");
            Integer expiresIn =  (Integer) jsonObject.get("expires_in");//单位秒
            //获取时间间隔，单位秒
            long timeDiffSeconds =( new Date().getTime() - lastDate.getTime())/  1000;

            //剩余时间小于12个小时，则重新获取,否则世界使用就像
            if(timeDiffSeconds<(expiresIn-12*60*60))
            {
                String access_token=  (String) jsonObject.get("access_token");
                AjaxResult success = AjaxResult.success();
                success.put("access_token",access_token);
                return success;
            }
        }
        try
        {
            String url = StringUtils.format("{}?client_id={}&client_secret={}&grant_type=client_credentials",
                    tokenUrl,  apiKey, secretKey );

            String responseString = restTemplate.postForObject(url, null,String.class);

            System.out.println(responseString.toString());

            jsonObject = JSONObject.parseObject(responseString);

            String access_token=  (String) jsonObject.get("access_token");
            jsonObject.put("last_date",new Date());
            CacheUtils.put("baidu_token",jsonObject);
            AjaxResult success = AjaxResult.success();
            success.put("access_token",access_token);
            return success;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }


    }


    public AjaxResult getIDCardOCR(MultipartFile file,String idCardSide) throws Exception {

        AjaxResult token = this.getToken();
        if(!token.isSuccess())
        {
            return token;
        }

        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token=" +(String)token.get("access_token");

        byte[] fileBytes = file.getBytes();



        byte[] imgData  = file.getBytes();
        String imgBase64 = Base64Util.encode(imgData);
        String imgParam = URLEncoder.encode(imgBase64, "UTF-8");


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //提交参数设置
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id_card_side", idCardSide );// front/back
        map.add("image", imgParam);
        map.add("detect_risk", "false");
        map.add("detect_quality", "false");
        map.add("detect_photo", "false");
        map.add("detect_card", "false");
        map.add("detect_direction", "false");

        // 组装请求体
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String requestStr="id_card_side=front&image="+ imgParam +"&detect_risk=false&detect_quality=false&detect_photo=false&detect_card=false&detect_direction=false";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestStr, headers);

        //发起请求
        String responseString = restTemplate.postForObject(url, requestEntity, String.class);

        IDCardInfo idCardInfo = this.convert2IDCardInfo(responseString,idCardSide);

        AjaxResult success = AjaxResult.success();
        success.put("idCardInfo", idCardInfo);
        return success;


    }



    public IDCardInfo convert2IDCardInfo(String jsonString,String idCardSide) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        // 解析JSON字符串为JsonNode
        JsonNode rootNode = objectMapper.readTree(jsonString);

        // 获取words_result节点
        JsonNode wordsResultNode = rootNode.get("words_result");

        // 创建IDCardInfo对象并设置属性
        IDCardInfo idCardInfo = new IDCardInfo();
        if(idCardSide.equalsIgnoreCase("front"))
        {
            idCardInfo.setName(wordsResultNode.get("姓名").get("words").asText());
            idCardInfo.setNation(wordsResultNode.get("民族").get("words").asText());
            idCardInfo.setAddress(wordsResultNode.get("住址").get("words").asText());
            idCardInfo.setIdNumber(wordsResultNode.get("公民身份号码").get("words").asText());
            idCardInfo.setBirth(wordsResultNode.get("出生").get("words").asText());
            idCardInfo.setSex(wordsResultNode.get("性别").get("words").asText());
        }
        else
        {
            idCardInfo.setExpDate(wordsResultNode.get("失效日期").get("words").asText());
            idCardInfo.setIssueDate(wordsResultNode.get("签发日期").get("words").asText());
            idCardInfo.setIssueOrg(wordsResultNode.get("签发机关").get("words").asText());
        }


        return idCardInfo;
    }


}
