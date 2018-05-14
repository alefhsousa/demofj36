package com.alefh.demofj36.service;

import com.alefh.demofj36.conf.RabbitConf;
import com.alefh.demofj36.domain.Aluno;
import com.alefh.demofj36.repository.AlunoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AlunoService {

    private AlunoRepository repository;
    private RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.topico.nome}")
    private String NOME_TOPICO;
    @Value("${spring.rabbitmq.routingKey.alunos}")
    private String ROUTING_KEY_ALUNO;

    public AlunoService(AlunoRepository alunoRepository,
                        RabbitTemplate rabbitTemplate) {
        this.repository =  alunoRepository;
        this.rabbitTemplate = rabbitTemplate;
    }


    public Aluno cria(Aluno aluno) {
        aluno = repository.save(aluno);

        for(int i = 0; i < 10; i++) {
            System.out.println("enviando msg: " + i);
            rabbitTemplate.convertAndSend(NOME_TOPICO, ROUTING_KEY_ALUNO, aluno);
        }
        return aluno;
    }

    public List<Aluno> listaByNome(String nome) {
        return repository.findByNome(nome);
    }

    public Aluno byId(String id) {
        return repository.findOne(id);
    }

    public Collection<Aluno> buscaTodos() {
        return repository.findAll();
    }

    public void atualiza(String id) {

    }
}
