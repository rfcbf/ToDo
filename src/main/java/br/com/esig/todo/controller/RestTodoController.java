package br.com.esig.todo.controller;

import java.util.List;

import br.com.esig.todo.entity.Todo;
import br.com.esig.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/api/{texto}")
    public void salvar(@PathVariable String texto) {
        Todo todo = new Todo();
        todo.setConcluido(false);
        todo.setTexto(texto);
        todoService.salvar(todo);
    }

    @PutMapping("/api/{id}")
    public void atualizar(@PathVariable Integer id) {

        Todo todo = todoService.recuperarPorId(id);

        if (todo.getConcluido()){
            todo.setConcluido(false);
        }else{
            todo.setConcluido(true);
        }

        todoService.atualizar(todo);

    }

}




