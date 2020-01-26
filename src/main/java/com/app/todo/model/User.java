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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NotBlank(message = "Name is mandatory")
    private String username;
    private String email;
    private String password;

    @Transient
    private String passwordConfirm;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(/*cascade = CascadeType.ALL, */fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Todo> Todos;

    public Set<Todo> getTodos() { return Todos; }

    public void setTodos(Set<Todo> Todos) {
        this.Todos = Todos;
    }

}
