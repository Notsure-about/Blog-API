package com.example.Blog_API.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;
    @Column(name = "Title")
private String  title;
    @Column(name = " Description")
private String  description;
    @OneToMany(mappedBy = "category")
    private List <entity> entities = new ArrayList<>();
}
