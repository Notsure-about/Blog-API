package com.example.Blog_API.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @Column(name = "Title")
    String title;
    @Column(name = "Content")
    String Content;
    @ManyToOne // it means that many entities(posts) can belong to one category
    @JoinColumn(name = "category_id") // foreign key name in table entity
    private Category category;
    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;
}
