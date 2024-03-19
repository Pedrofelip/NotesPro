package br.com.fiap.notesPro.model;

import java.util.Date;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity

public class Tarefas{

     @Id @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
     private String nome;
     private String status;
     private String descricao;
     private  Date data;

}

//deixou de ser esse de baixo e virou o de cima

// public record Tarefas (Long id, String nome, String status, String descricao, Date data) {

//     public Tarefas(Long id, String nome, String status, String descricao, Date data) {
//         var key = (id == null) ? Math.abs(( new Random().nextLong()) ) : id;
//         this.id = key;
//         this.nome = nome;
//         this.status = status;
//         this.descricao = descricao;
//         this.data = data;

//     }
// }
