package com.ruoyi.studentSys.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.studentSys.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController extends BaseController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendmail")
    public String sendmail(){
        return "studentSys/mail/sendmail";
    }

    @PostMapping("/sendmail")
    @ResponseBody
    public AjaxResult sendmail(String to,String subject,String content){
        emailService.sendSimpleMail(to,subject,content);
        return success();
    }
    @GetMapping("/sendmailhtml")
    public String sendmailhtml(){
        return "studentSys/mail/sendmailhtml";
    }

    @PostMapping("/sendmailhtml")
    @ResponseBody
    public AjaxResult sendmailhtml(String to,String subject,String content){
        emailService.sendSimpleMail(to,subject,content);
        return success();
    }
}
