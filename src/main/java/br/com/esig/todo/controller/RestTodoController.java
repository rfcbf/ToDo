package br.com.esig.todo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import br.com.esig.todo.entity.Todo;
import br.com.esig.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class RestTodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/api")
    public List<Todo> listar() {
        return todoService.recuperar("T");
    }

    @DeleteMapping("/api/{id}")
    public void deletar(@PathVariable Integer id) {
        todoService.excluir(id);
    }

    @PostMapping("/api")
    public ResponseEntity<Object> salvar(@RequestBody Todo todo) {
        Todo todos = todoService.salvar(todo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(todo.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/api")
    public ResponseEntity<Object> atualizar(@RequestBody Todo todo) {

        todoService.atualizar(todo);

        return ResponseEntity.noContent().build();
    }

}




