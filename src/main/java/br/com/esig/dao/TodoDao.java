package br.com.esig.dao;

import br.com.esig.entity.Todo;
import java.util.List;

public interface TodoDao {

    void salvar(Todo todo);

    List<Todo> recuperar();

    Todo recuperarPorId(Integer id);

    void atualizar(Todo todo);

    void excluir(Integer id);

}
