package br.univille.projeto_final.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.projeto_final.entity.Funcionario;


public interface FuncionarioService {
    List<Funcionario> getAll();
}
