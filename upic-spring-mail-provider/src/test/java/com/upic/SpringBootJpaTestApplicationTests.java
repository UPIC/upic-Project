package com.upic;

import com.upic.condition.MailCondition;
import com.upic.condition.MailLogCondition;
import com.upic.dto.MailInfo;
import com.upic.dto.MailLogInfo;
import com.upic.service.MailLogService;
import com.upic.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaTestApplication.class)
public class SpringBootJpaTestApplicationTests {
    @Autowired
    private MailService mailService;

    @Autowired
    private MailLogService mailLogService;

    /**
     * ************************************** Mail *****************************************
     */
    @Test
    public void testAddMail() {
        for (int i = 0; i < 20; i++) {
            MailInfo mailInfo = new MailInfo();
            mailInfo.setMailContent("Content" + i);
            mailInfo.setTitle("Title" + i);
            mailService.addMail(mailInfo);
        }
    }

    @Test
    public void testUpdateMail() {
        MailInfo mailInfo = mailService.getMailById(1L);
        mailInfo.setTitle("1234");
        mailInfo = mailService.updateMail(mailInfo);
    }

    @Test
    public void testSearchMail() {
        MailCondition mailCondition = new MailCondition();
        PageRequest pageRequest = new PageRequest();
        Page<MailInfo> mailInfoPage = mailService.searchMail(mailCondition, pageRequest);
        System.out.println(mailInfoPage.getTotalElements());
        System.out.println(mailInfoPage.getTotalPages());
        for (MailInfo mailInfo : mailInfoPage.getContent()) {
            System.out.println(mailInfo);
        }
    }

    @Test
    public void testDeleteMail() {
        mailService.deleteMail(1L);
    }

    /**
     * ************************************** Mail *****************************************
     */
    @Test
    public void testAddMailLog() {
        for (int i = 1; i < 11; i++) {
            MailLogInfo mailLogInfo = new MailLogInfo();
            MailInfo mailInfo = mailService.getMailById(i);
            mailLogInfo.setMail(mailInfo);
            mailLogInfo.setOperation("Operation" + i);
            mailLogService.addMailLog(mailLogInfo);
        }
    }

    @Test
    public void testUpdateMailLog() {
        MailLogInfo mailLogInfo = mailLogService.getByMailLogId(1L);
        mailLogInfo.setOperation("1234");
        mailLogService.updateMailLog(mailLogInfo);
    }

    @Test
    public void testSearchMailLog() {
        MailLogCondition mailLogCondition = new MailLogCondition();
        PageRequest pageRequest = new PageRequest();
        Page<MailLogInfo> mailLogInfoPage = mailLogService.searchMailLog(mailLogCondition, pageRequest);
        System.out.println(mailLogInfoPage.getTotalElements());
        System.out.println(mailLogInfoPage.getTotalPages());
        for (MailLogInfo mailLogInfo : mailLogInfoPage.getContent()) {
            System.out.println(mailLogInfo);
        }
    }

    @Test
    public void testDeleteMailLog() {
        mailLogService.deleteMailLog(1L);
    }
}
