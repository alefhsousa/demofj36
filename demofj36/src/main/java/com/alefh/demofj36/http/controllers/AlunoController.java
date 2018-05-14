package com.alefh.demofj36.http.controllers;

import com.alefh.demofj36.domain.Aluno;
import com.alefh.demofj36.service.AlunoService;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public Collection<Aluno> getAlunos() {
        return alunoService.buscaTodos();
    }

    @PutMapping("/{id}")
    public Aluno atualizaAluno(@PathVariable String id) {
        alunoService.atualiza(id);
        return new Aluno();
    }

    @PostMapping
    public Aluno criarAluno(@RequestBody Aluno aluno) {
        return alunoService.cria(aluno);
    }


}
