package main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
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

    /**
     * The {@link ApplicationContext}.
     */
    @Autowired
    public ApplicationContext applicationContext;

    /**
     * Sends an email every hour of the recently stored into the database.
     */
    @Scheduled(fixedRate = 3600000)
    public void sendEmail() {

        final String bodyString = getMessagesFromQueue();

        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject("Test");
        message.setText(bodyString);
        javaMailSender.send(message);
        log.info("Email sent to {}", recipient);
    }

    /**
     * Gets the messages from queue.
     *
     * @return the messages as String
     */
    private String getMessagesFromQueue() {
        // get all the messages dropped into the queue
        final JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        jmsTemplate.setReceiveTimeout(5000);

        final StringBuilder body = new StringBuilder();

        // get all the files
        while (true) {
            final Email message = (Email) jmsTemplate.receiveAndConvert("mailQueue");
            if (message == null) {
                break;
            }
            log.info("Text Message: {}", message);
            body.append(message);
        }
        return body.toString();
    }
}
