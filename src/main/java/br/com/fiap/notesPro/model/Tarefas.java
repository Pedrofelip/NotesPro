package br.com.fiap.notesPro.model;

import java.sql.Date;
import java.util.Random;

// imutável
public record Tarefas (Long id, String nome, String status, String descricao, Date data) {

    public Tarefas(Long id, String nome, String status, String descricao, Date data) {
        var key = (id == null) ? Math.abs(( new Random().nextLong()) ) : id;
        this.id = key;
        this.nome = nome;
        this.status = status;
        this.descricao = descricao;
        this.data = data;

    }
}
