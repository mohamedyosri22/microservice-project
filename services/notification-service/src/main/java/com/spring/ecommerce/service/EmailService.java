package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.Product;
import com.spring.ecommerce.kafka.PaymentConfirmation;
import com.spring.ecommerce.model.EmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.UTF8;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(
            String destionationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(
          mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,UTF_8.name()
        );
        messageHelper.setFrom("mhmd.you@yahoo.com");
        final String templateName = EmailTemplate.PAYMENT_CONFIRMATION.getTemplate();

        Map<String,Object> var = new HashMap<>();

        var.put("customerName",customerName);
        var.put("amount",amount);
        var.put("orderReference",orderReference);

        Context context = new Context();

        context.setVariables(var);
        messageHelper.setSubject(EmailTemplate.PAYMENT_CONFIRMATION.getSubject());

        try{
            String htmlTemplate = templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);
            messageHelper.setTo(destionationEmail);

            mailSender.send(mimeMessage);
            log.info(String.format("INFO --- Email Successfully sent to %s with template %s",destionationEmail,templateName));

        }catch (MessagingException exp){
            log.warn("WARINING -- Cannot send email to :: {}",destionationEmail);

        }
    }

    @Async
    public void sentOrderConfirmationEmail(
            String destionationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(
                mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,UTF_8.name()
        );
        messageHelper.setFrom("mhmd.you@yahoo.com");
        final String templateName = EmailTemplate.ORDER_CONFIRMATION.getTemplate();

        Map<String,Object> var = new HashMap<>();

        var.put("customerName",customerName);
        var.put("amount",amount);
        var.put("orderReference",orderReference);
        var.put("Products",products);

        Context context = new Context();

        context.setVariables(var);
        messageHelper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getSubject());

        try{
            String htmlTemplate = templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);
            messageHelper.setTo(destionationEmail);

            mailSender.send(mimeMessage);
            log.info(String.format("INFO --- Email Successfully sent to %s with template %s",destionationEmail,templateName));

        }catch (MessagingException exp){
            log.warn("WARINING -- Cannot send email to :: {}",destionationEmail);

        }
    }
}
