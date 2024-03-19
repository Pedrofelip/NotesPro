package br.com.fiap.notesPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.notesPro.model.Tarefas;


public interface TarefaRepository extends JpaRepository<Tarefas, Long>{
    
}
