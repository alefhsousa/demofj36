package com.alefh.clientdemo.consumer;


import com.alefh.clientdemo.domain.Aluno;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AlunoConsumer {

    @RabbitListener(queues = "alunos", containerFactory = "simpleListenerContainerFactory")
    public void leMensagem(Aluno aluno) throws InterruptedException {
        Thread.sleep(20000);
        log.info("msg {}", aluno);
    }
}
