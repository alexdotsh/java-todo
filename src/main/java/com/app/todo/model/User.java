package com.app.todo.model;

import java.util.Set;
import javax.persistence.*;
import java.util.LinkedHashSet;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@DiscriminatorColumn(name = "type")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 20, message = "Username needs to be between 2 to 20 characters long")
    @NotBlank(message = "Username can't be blank")
    private String username;

    @NotBlank(message = "Email can't be blank")
    @Email
    private String email;

    @Size(min = 8, max = 20, message = "Password needs to be between 2 to 20 characters long")
    private String password;
    @Transient
    private String passwordConfirm;

    @Column(insertable = false, updatable = false, unique = true)
    private String type;
    @Column(unique = true)
    private String externalId;

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Todo> todo = new LinkedHashSet<>();

    @ManyToMany
    private Set<Role> roles;

    public String getExternalId() { return externalId; }
    public void setExternalId(String externalId) { this.externalId = externalId; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPasswordConfirm() { return passwordConfirm; }
    public void setPasswordConfirm(String passwordConfirm) { this.passwordConfirm = passwordConfirm; }

    public Set<Todo> getTodo() { return todo; }
    public void setTodo(Set<Todo> todo) {
        this.todo.clear();
        this.todo.addAll(todo);
    }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
}
