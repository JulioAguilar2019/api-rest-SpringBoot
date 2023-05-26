package com.app.apispringboot.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    @Column(name ="title", nullable = false)
    private String title;
    @Column(name ="description", nullable = false)
    private String description;
    @Column(name ="content", nullable = false)
    private String content;

}
