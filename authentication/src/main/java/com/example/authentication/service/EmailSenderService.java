package com.example.authentication.service;


import com.example.authentication.config.ApplicationProperties;
import com.example.authentication.exception.AppException;
import com.example.authentication.model.request.EmailBodyRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.example.authentication.model.constants.ExceptionConstants.EMAIL_SEND_EXCEPTION;


@Log4j2
@Service
@RequiredArgsConstructor
public class EmailSenderService {

  private final JavaMailSender mailSender;
  private final ApplicationProperties applicationProperties;

  @Async
  public void send(EmailBodyRequest emailBodyRequest) {
    String subject = "Please verify your registration";
    String fromAddress = "info.writtenn@gmail.com";

    String content = "Dear [[name]],<br>"
        + "Please click the link below to verify your registration:<br>"
        + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
        + "Thank you,<br>"
        + "Written.";

    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);
      helper.setPriority(1);
      helper.setSubject(subject);
      helper.setFrom(fromAddress);
      helper.setTo(emailBodyRequest.receiverEmail());

      content = content.replace("[[name]]", emailBodyRequest.receiverEmail());
      String verifyURL =
          applicationProperties.getEmailConfirmationUrl() + emailBodyRequest.verificationCode();

      content = content.replace("[[URL]]", verifyURL);
      helper.setText(content, true);

      mailSender.send(message);

    } catch (MessagingException e) {
      log.error(e);
      throw new AppException(EMAIL_SEND_EXCEPTION, e.getMessage());
    }

  }
}
