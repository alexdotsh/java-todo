package com.app.todo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String Title;

    @Size(min = 5, message = "Description need to be longer")
    private String Description;

    @Column(columnDefinition = "boolean default false")
    private Boolean Done = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return Title; }
    public void setTitle(String Title) { this.Title = Title; }

    public String getDescription() { return Description; }
    public void setDescription(String Description) { this.Description = Description; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Boolean getDone() { return Done; }
    public void setDone(Boolean Done) { this.Done = Done; }
}
