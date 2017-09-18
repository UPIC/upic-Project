package com.upic.serviceimpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.MailCondition;
import com.upic.dto.MailInfo;
import com.upic.po.Mail;
import com.upic.repository.MailRepository;
import com.upic.repository.Spec.MailSpec;
import com.upic.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService {
    @Autowired
    private MailRepository mailRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    public MailInfo addMail(MailInfo mailInfo) {
        try {
            Mail mail = new Mail();
            UpicBeanUtils.copyProperties(mailInfo, mail);
            mail = mailRepository.save(mail);
            UpicBeanUtils.copyProperties(mail, mailInfo);
        } catch (Exception e) {
            LOGGER.info("addMail:信件" + mailInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
        return mailInfo;
    }

    public MailInfo updateMail(MailInfo mailInfo) {
        try {
            Mail mail = mailRepository.findOne(mailInfo.getId());
            UpicBeanUtils.copyProperties(mailInfo, mail);
            mail = mailRepository.saveAndFlush(mail);
            UpicBeanUtils.copyProperties(mail, mailInfo);
        } catch (Exception e) {
            LOGGER.info("updateMail:信件" + mailInfo.toString() + "修改失败。错误信息：" + e.getMessage());
            return null;
        }
        return mailInfo;
    }

    public Page<MailInfo> searchMail(MailCondition mailCondition, Pageable pageable) {
        Page<Mail> mailPage = null;
        try {
            mailPage = mailRepository.findAll(new MailSpec(mailCondition), pageable);
            return QueryResultConverter.convert(mailPage, pageable, new AbstractDomain2InfoConverter<Mail, MailInfo>() {
                @Override
                protected void doConvert(Mail domain, MailInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchMail:信件列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public MailInfo getMailById(long mailId) {
        try {
            Mail mail = mailRepository.findOne(mailId);
            if (mail == null) {
                throw new Exception();
            }
            MailInfo mailInfo = new MailInfo();
            UpicBeanUtils.copyProperties(mail, mailInfo);
            return mailInfo;
        } catch (Exception e) {
            LOGGER.info("getMailById:单个信件查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public void deleteMail(long mailId) {
        try {
            mailRepository.delete(mailId);
        } catch (Exception e) {
            LOGGER.info("deleteMail:删除信件失败。错误信息：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
