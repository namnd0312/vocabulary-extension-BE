package com.namnd.englishvocabextension.services.impl;

import com.namnd.englishvocabextension.enums.EProcess;
import com.namnd.englishvocabextension.models.PhraseOfWord;
import com.namnd.englishvocabextension.models.Vocabulary;
import com.namnd.englishvocabextension.services.EmailService;
import com.namnd.englishvocabextension.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.time.Instant;
import java.util.List;
import java.util.Properties;


/**
 * @author nam.nd
 * @created 18/08/2021 - 12:25 AM
 */

@Service
public class EmailServiceImpl implements EmailService {

    private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";

    @Value("${config.mail.host}")
    private String host;
    @Value("${config.mail.port}")
    private String port;
    @Value("${config.mail.username}")
    private String email;
    @Value("${config.mail.password}")
    private String password;

    @Autowired
    ThymeleafService thymeleafService;

    @Override
    public void sendMail(List<Vocabulary> vocabularies, EProcess process) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        Message message = new MimeMessage(session);
        try {
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("namnd0312@gmail.com")});

            message.setFrom(new InternetAddress(email));
            message.setSubject(this.convertEmailTitle(process));
            message.setContent(thymeleafService.getContent(vocabularies), CONTENT_TYPE_TEXT_HTML);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMailPhraseOfWord(List<PhraseOfWord> phraseOfWords, EProcess process) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        Message message = new MimeMessage(session);
        try {
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("namnd0312@gmail.com")});

            message.setFrom(new InternetAddress(email));
            message.setSubject(this.convertEmailTitle(process));
            message.setContent(thymeleafService.getContentPhrase(phraseOfWords), CONTENT_TYPE_TEXT_HTML);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    String convertEmailTitle(EProcess process) {
        String now = Util.instantToString(Instant.now());
        switch (process) {
            case SECOND_DAY:
                return now + " - DANH SÁCH TỪ VỰNG HỌC NGÀY THỨ 2";

            case A_WEEK:
                return now + " - DANH SÁCH TỪ VỰNG HỌC TUẦN THỨ 1";

            case TWO_WEEK:
                return now + " - DANH SÁCH TỪ VỰNG HỌC TUẦN THỨ 2";

            case A_MONTH:
                return now + " - DANH SÁCH TỪ VỰNG HỌC THÁNG THỨ 1";

            case TWO_MONTH:
                return now + " - DANH SÁCH TỪ VỰNG HỌC THÁNG THỨ 2";

            default:
                return "";
        }
    }
}
