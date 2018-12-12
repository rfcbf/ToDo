package br.com.esig.todo.service;

import br.com.esig.todo.dao.TodoDao;
import br.com.esig.todo.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;

    @Override
    public void salvar(Todo todo) {
        todoDao.salvar(todo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Todo> recuperar(String filtro) {
        return todoDao.recuperar(filtro) ;
    }

    @Override
    @Transactional(readOnly = true)
    public Todo recuperarPorId(Integer id) {
        return todoDao.recuperarPorId(id);
    }

    @Override
    public void atualizar(Todo todo) {
        todoDao.atualizar(todo);
    }

    @Override
    public void excluir(Integer id) {
        todoDao.excluir(id);
    }

    @Override
    public void concluir(Integer id){
        todoDao.concluir(id);
    }

    @Override
    public void naoConcluir(Integer id){
        todoDao.naoConcluir(id);
    }

}
