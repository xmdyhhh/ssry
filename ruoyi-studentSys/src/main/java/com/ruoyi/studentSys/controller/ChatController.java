package com.ruoyi.studentSys.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.studentSys.websocket.Friend;
import com.ruoyi.studentSys.websocket.Message;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.Session;
import java.util.*;

@Controller
public class ChatController extends BaseController {

    @Autowired
    ISysUserService userService;


    @GetMapping("/chat")
    public String chat(ModelMap modelMap) {

        List<SysUser> sysUsers = userService.selectUserListAll();

        Map<String, Friend> linkedHashMap= new LinkedHashMap<>();
        Random random = new Random();
        for (SysUser sysUser : sysUsers) {
            if (sysUser.getLoginName().equals(this.getLoginName())) {
                continue;
            }
            Friend friend = new Friend();
            friend.setId(sysUser.getLoginName());
            friend.setName(sysUser.getUserName());
            String avatar = "avatar/"+ (random.nextInt(10) + 1) +".jpg";
            friend.setAvatar(avatar);


            friend.setStatus("offline");
            friend.setMessages(new ArrayList<Message>());
            linkedHashMap.put(sysUser.getLoginName(), friend);

        }
        modelMap.put("friends", linkedHashMap);

        return "studentSys/chat/chat";
    }


}
