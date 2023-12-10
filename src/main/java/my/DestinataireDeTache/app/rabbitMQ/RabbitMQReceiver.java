package my.DestinataireDeTache.app.rabbitMQ;

import my.DestinataireDeTache.app.dto.TaskDTO;
import my.DestinataireDeTache.app.objects.GlobalVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component

public class RabbitMQReceiver {
    private static final Logger log = LoggerFactory.getLogger(RabbitMQReceiver.class);

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @RabbitListener(queues = GlobalVariables.TASK_QUEUE)
    public void receiveTask(TaskDTO task) {
        TaskDTO result = performFakeCalculation(task);
        log.info("receive taskQ : "+task.toString());
        rabbitMQSender.sendResult(result);
    }

    private TaskDTO performFakeCalculation(TaskDTO task) {
        long counter = task.getCounter()+1;
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern(GlobalVariables.DATE_TEMPLATE));
        return new TaskDTO(task.getTaskId(), task.getDescription(), date, counter);
    }

}
