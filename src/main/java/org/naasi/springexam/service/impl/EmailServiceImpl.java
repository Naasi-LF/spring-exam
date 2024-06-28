package org.naasi.springexam.service.impl;
import org.naasi.springexam.pojo.EmailProperties;
import org.naasi.springexam.service.EmailService;
import org.naasi.springexam.security.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailProperties emailProperties;

    @Override
    public boolean send(String to, String title, String content) {
        return MailUtil.sendMail(emailProperties, to, title, content);
    }

    @Override
    public boolean sendBulk(List<String> toList, String title, List<String> contents) {
        boolean allSent = true;
        for (int i = 0; i < toList.size(); i++) {
            boolean sent = send(toList.get(i), title, contents.get(i));
            if (!sent) {
                allSent = false;
            }
        }
        return allSent;
    }
}