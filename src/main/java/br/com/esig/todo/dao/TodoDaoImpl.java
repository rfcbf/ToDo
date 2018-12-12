package br.com.esig.todo.dao;

import br.com.esig.todo.entity.Todo;
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
    public List<Todo> recuperar(String filtro) {

        if (filtro.equals("T")) {
            return em.createQuery("select p from Todo p order by texto", Todo.class).getResultList();
        }else if (filtro.equals("C")) {
            return em.createQuery("select p from Todo p where concluido = 'S' order by texto", Todo.class).getResultList();
        }else{
            return em.createQuery("select p from Todo p where concluido = 'N' order by texto", Todo.class).getResultList();
        }
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


    @Override
    public void concluir(Integer id){

        Todo todo = recuperarPorId(id);
        todo.setConcluido("S");
        em.merge(todo);

    }


    @Override
    public void naoConcluir(Integer id){
        Todo todo = recuperarPorId(id);
        todo.setConcluido("N");
        em.merge(todo);
    }

}
