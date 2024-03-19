package br.com.fiap.notesPro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.notesPro.model.Tarefas;
import br.com.fiap.notesPro.repository.TarefaRepository;

@RestController
@RequestMapping("tarefas")
public class TarefasController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    TarefaRepository repository;

    @GetMapping
    public List<Tarefas> index() {
        return repository.findAll();
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Tarefas create(@RequestBody Tarefas tarefas) { // binding
        log.info("cadastrando tarefa {} ", tarefas);
        repository.save(tarefas);
        return tarefas;

    }


    @GetMapping("{id}")
    public ResponseEntity<Tarefas> show(@PathVariable Long id) {
        log.info("buscando tarefa por id {}", id);


        return repository
            .findById(id)
            .map(ResponseEntity::ok) //referente method
            .orElse(ResponseEntity.notFound().build());

            // map -> recebe a função para transformar

        // Optional é a solução do NupointerException -- Ao invés dele retornar nulo,
        // ele retorna um Optional vazio. Analogia = ele é uma caixa, uma caixa que pode
        // estar vazia.

        // for(Tarefas tarefa : repository){
        // if (tarefa.id().equals(id))
        // return ResponseEntity.ok(tarefa);
        // }

        // var tarefaEncontrada = getTarefaBydId(id);


        // if (tarefaEncontrada.isEmpty()) {
        //     return ResponseEntity.notFound().build();
        // }

        // return ResponseEntity.ok(tarefaEncontrada.get());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("apagando tarefa");

        verificarSeExisteTarefa(id);

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //     var tarefaEncontrada = getTarefaBydId(id);

    //     if (tarefaEncontrada.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }

    //     repository.remove(tarefaEncontrada.get());

    //     return ResponseEntity.noContent().build();
    // }


    // private Optional<Tarefas> getTarefaBydId(Long id) {
    //     var tarefaEncontrada = repository
    //             .stream()
    //             .filter(c -> c.id().equals(id))
    //             .findFirst();
    //     return tarefaEncontrada;
    // }


    @PutMapping("{id}")
    public ResponseEntity<Tarefas> update (@PathVariable Long id, @RequestBody Tarefas tarefa) {
        log.info("atualizando tarefa com id {} para {}", id, tarefa);

        verificarSeExisteTarefa(id);

            tarefa.setId(id);
            repository.save(tarefa);
            return ResponseEntity.ok(tarefa);
    }


     private void verificarSeExisteTarefa(Long id) {
        repository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, 
            "Não existe categoria com o id informado. Consulte lista em /categoria"));
    }

    //     if (tarefaEncontrada.isEmpty())
    //         return ResponseEntity.notFound().build();

    //     //criar uma nova categoria com os novos dados
    //     var tarefaAntiga = tarefaEncontrada.get();
    //     var tarefaNova = new Tarefas(id, tarefa.nome(), tarefa.status(), tarefa.descricao(), tarefa.data());

    //     // apagar a categoria
    //     repository.remove(tarefaAntiga);

    //     // add a categoria nova
    //     repository.add(tarefaNova);


    //     return ResponseEntity.ok(tarefaNova);
    // }


}


