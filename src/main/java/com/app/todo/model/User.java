package com.app.todo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private String Username;
    private String Email;
    private String password;

    @Transient
    private String passwordConfirm;


    public Long getId() { 
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
