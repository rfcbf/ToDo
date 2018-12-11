package br.com.esig.service;

import br.com.esig.entity.Todo;

import java.util.List;

public interface TodoService {

    void salvar(Todo todo);
    List<Todo> recuperar();
    Todo recuperarPorId(Integer id);
    void atualizar(Todo todo);
    void excluir(Integer id);

}
