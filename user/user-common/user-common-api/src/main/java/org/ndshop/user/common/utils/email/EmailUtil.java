package org.ndshop.user.common.utils.email;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.ndshop.user.common.utils.regex.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-29 17:47]
 * @Description: [邮件发送工具]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class EmailUtil {
    private static Logger CONSOLE = LoggerFactory.getLogger(EmailUtil.class);
    public static void SendMail(Email em) throws Exception {
        if (StringUtils.isBlank(em.getContent()) ||
                (null == em.getReceiver() && em.getReceiver().size() == 0)
                || StringUtils.isBlank(em.getSubject()) || null == em.getSender()) {
            throw new Exception("收件人/发送人/主题/内容 不能为空!");
        }
        EmailGenerate emailGenerate = new EmailGenerate();
        if (emailGenerate.sendHtmlEMail(em)) {
            CONSOLE.info("邮件发送成功!");
        } else {
            CONSOLE.info("邮件发送失败!");
        }
    }

}