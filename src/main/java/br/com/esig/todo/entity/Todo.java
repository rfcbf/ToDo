package br.com.esig.todo.entity;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@NotBlank
    @Size(min = 5, max = 50)
    @Column(nullable = false, length = 50)
    private String texto;

    //@NotBlank
    //@Type(type="true_false")
    @Column(nullable = false, length = 1)
    private Boolean concluido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }
}
