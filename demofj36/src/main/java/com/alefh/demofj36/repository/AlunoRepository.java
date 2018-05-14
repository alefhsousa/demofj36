package com.alefh.demofj36.repository;

import com.alefh.demofj36.domain.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AlunoRepository extends
        MongoRepository<Aluno, String> {

    List<Aluno> findByNome(String nome);
}
