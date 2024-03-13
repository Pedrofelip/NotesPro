package br.com.fiap.notesPro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.fiap.notesPro.model.Tarefas;

@RestController
@RequestMapping("tarefas")
public class TarefasController {

    Logger log = LoggerFactory.getLogger(getClass());

    List<Tarefas> repository = new ArrayList<>();

    @GetMapping
    public List<Tarefas> index() {
        return repository;
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Tarefas create(@RequestBody Tarefas tarefas) { // binding
        log.info("cadastrando tarefa {} ", tarefas);
        repository.add(tarefas);
        return tarefas;

    }


    @GetMapping("{id}")
    public ResponseEntity<Tarefas> show(@PathVariable Long id) {
        log.info("buscando tarefa por id {}", id);

        // for(Tarefas tarefa : repository){
        // if (tarefa.id().equals(id))
        // return ResponseEntity.ok(tarefa);
        // }

        var tarefaEncontrada = getTarefaBydId(id);


        if (tarefaEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tarefaEncontrada.get());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("apagando tarefa");

        var tarefaEncontrada = getTarefaBydId(id);

        if (tarefaEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.remove(tarefaEncontrada.get());

        return ResponseEntity.noContent().build();
    }


    private Optional<Tarefas> getTarefaBydId(Long id) {
        var tarefaEncontrada = repository
                .stream()
                .filter(c -> c.id().equals(id))
                .findFirst();
        return tarefaEncontrada;
    }


    @PutMapping("{id}")
    public ResponseEntity<Tarefas> update (@PathVariable Long id, @RequestBody Tarefas tarefa) {
        log.info("atualizando tarefa com id {} para {}", id, tarefa);
        //buscar a categoria
        var tarefaEncontrada = getTarefaBydId(id);

        if (tarefaEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        //criar uma nova categoria com os novos dados
        var tarefaAntiga = tarefaEncontrada.get();
        var tarefaNova = new Tarefas(id, tarefa.nome(), tarefa.status(), tarefa.descricao(), tarefa.data());

        // apagar a categoria
        repository.remove(tarefaAntiga);

        // add a categoria nova
        repository.add(tarefaNova);


        return ResponseEntity.ok(tarefaNova);
    }


}