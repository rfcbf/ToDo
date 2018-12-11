package br.com.esig.dao;

import br.com.esig.entity.Todo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TodoDaoImpl implements TodoDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void salvar(Todo todo) {
        em.persist(todo);
    }

    @Override
    public List<Todo> recuperar() {
        return em.createQuery("select p from Todo p", Todo.class).getResultList();
    }

    @Override
    public Todo recuperarPorId(Integer id) {
        return em.find(Todo.class, id);
    }

    @Override
    public void atualizar(Todo todo) {
        em.merge(todo);
    }

    @Override
    public void excluir(Integer id) {
        em.remove(em.getReference(Todo.class, id));
    }

}
