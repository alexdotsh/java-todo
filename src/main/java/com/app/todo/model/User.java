package com.app.todo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is mandatory")
    private String login;

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @OneToMany(/*cascade = CascadeType.ALL, */fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Todo> Todos;

    public Set<Todo> getTodos() { return Todos; }

    public void setTodos(Set<Todo> Todos) {
        this.Todos = Todos;
    }

}
