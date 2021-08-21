package com.namnd.englishvocabextension.services.impl;

import com.namnd.englishvocabextension.models.PhraseOfWord;
import com.namnd.englishvocabextension.models.Vocabulary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.List;

/**
 * @author nam.nd
 * @created 18/08/2021 - 12:27 AM
 */

@Service
public class ThymeleafService {

    private static final String MAIL_TEMPLATE_BASE_NAME = "mail/MailMessages";
    private static final String MAIL_TEMPLATE_PREFIX = "/templates/";
    private static final String MAIL_TEMPLATE_SUFFIX = ".html";
    private static final String UTF_8 = "UTF-8";

    private static final String TEMPLATE_NAME = "mail-template";

    private static TemplateEngine templateEngine;

    static {
        templateEngine = emailTemplateEngine();
    }

    private static TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(htmlTemplateResolver());
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

    private static ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MAIL_TEMPLATE_BASE_NAME);
        return messageSource;
    }

    private static ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(MAIL_TEMPLATE_PREFIX);
        templateResolver.setSuffix(MAIL_TEMPLATE_SUFFIX);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    public String getContent(List<Vocabulary> vocabularies) {
        final Context context = new Context();

        context.setVariable("name", "Nam");
        context.setVariable("email_message", "Đây là nội dung học từ vựng ngày hôm nay của bạn");
        context.setVariable("words", vocabularies);

        return templateEngine.process(TEMPLATE_NAME, context);
    }

    public String getContentPhrase(List<PhraseOfWord> phraseOfWords) {
        final Context context = new Context();

        context.setVariable("email_message", "Các cụm từ ôn tập");
        context.setVariable("phrases", phraseOfWords);
        return templateEngine.process(TEMPLATE_NAME, context);
    }
}
