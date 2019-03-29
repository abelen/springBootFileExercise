package main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * The Email Service.
 */
@Service
@Slf4j
public class EmailService {

    /**
     * The recipient for the email.
     */
    @Value("${spring.mail.recipient}")
    private String recipient;

    /**
     * The {@link JavaMailSender}
     */
    @Autowired
    public JavaMailSender javaMailSender;


//    @Scheduled(fixedRate = 3600000)
    @Scheduled(fixedRate = 60000)
    public void sendEmail() {
        final String hello = "hello";

        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject("Test");
        message.setText(hello);
        javaMailSender.send(message);
        log.info("Email sent to {}", recipient);
    }
}
