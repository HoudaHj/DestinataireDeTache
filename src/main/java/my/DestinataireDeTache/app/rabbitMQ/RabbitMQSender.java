package my.DestinataireDeTache.app.rabbitMQ;

import my.DestinataireDeTache.app.dto.TaskDTO;
import my.DestinataireDeTache.app.objects.GlobalVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSender {
    private static final Logger log = LoggerFactory.getLogger(RabbitMQSender.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendResult(TaskDTO result) {

        rabbitTemplate.convertAndSend(GlobalVariables.RESULT_QUEUE, result);
        log.info("send resultQ"+result.toString());
    }
}
