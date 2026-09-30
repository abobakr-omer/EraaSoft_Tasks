package com.spring.demo.service.bundleMessage;

import com.spring.demo.helper.MessageResponse;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BundleMessageService {

    private final ResourceBundleMessageSource messageSource;

    public BundleMessageService(
            ResourceBundleMessageSource messageSource
    ) {
        this.messageSource = messageSource;
    }

    public String getMessageEn(
            String code,
            Object... args
    ) {
        return messageSource.getMessage(
                code,
                args,
                Locale.ENGLISH
        );
    }

    public String getMessageAr(
            String code,
            Object... args
    ) {
        return messageSource.getMessage(
                code,
                args,
                Locale.forLanguageTag("ar")
        );
    }

    public MessageResponse getMessage(
            String code,
            Object... args
    ) {
        return new MessageResponse(
                getMessageEn(code, args),
                getMessageAr(code, args)
        );
    }
}
