package com.upic.serviceimpl;

import com.upic.SpringBootJpaTestApplication;
import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.condition.MailCondition;
import com.upic.dto.MailInfo;
import com.upic.po.Mail;
import com.upic.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaTestApplication.class)
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;


    @Test
    public void addMail() throws Exception {

        Mail mail = new Mail();
        mail.setCreatTime(new Date());
        mail.setMailNum("111111");
        mail.setMailContent("你好");
        mail.setTitle("测试");
        MailInfo mailInfo = new MailInfo();
        UpicBeanUtils.copyProperties(mail, mailInfo);
        MailInfo m = mailService.addMail(mailInfo);
        assertEquals("111111",m.getMailNum());
    }

    @Test
    public void updateMail() throws Exception {


        MailInfo mail = mailService.getMailById(1L);
        mail.setCreatTime(new Date());
        mail.setMailNum("111111");
        mail.setMailContent("你好啊");
        mail.setTitle("测试");
        MailInfo m = mailService.updateMail(mail);
        assertEquals("你好啊",m.getMailContent());
    }

    @Test
    public void searchMail() throws Exception {

        MailCondition mailCondition = new MailCondition();
        mailCondition.setMailNum("111111");
        PageRequest p=new PageRequest();
        Page<MailInfo> page=mailService.searchMail(mailCondition,p);
        System.out.println(page.getTotalElements());
        System.out.println(page.getContent());
        assertEquals(1,page.getTotalElements());
    }

    @Test
    public void getMailById() throws Exception {
        MailInfo mail = mailService.getMailById(1L);
        assertNotNull(mail);

    }

    @Test
    public void deleteMail() throws Exception {

        mailService.deleteMail(1L);
        MailInfo mail = mailService.getMailById(1L);
        assertEquals(null,mail);

    }

}