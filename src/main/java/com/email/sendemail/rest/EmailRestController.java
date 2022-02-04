package com.email.sendemail.rest;

import com.email.sendemail.models.EmailModel;
import com.email.sendemail.services.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
//ds
@RestController
@CrossOrigin
public class EmailRestController {

    @Autowired
    SendEmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailModel emailModel){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+emailModel.getTo());
        ResponseEntity<?> resp=null;
        Map map=new HashMap();
        try {
            if(emailModel!=null) {
                emailService.sendmail(emailModel);
                map.put("message", "email sent successfully");
            }
        }
        catch (Exception e){
            map.put("message",e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(map, HttpStatus.OK);

    }
}
