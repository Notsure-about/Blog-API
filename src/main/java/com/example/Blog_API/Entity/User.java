package com.example.Blog_API.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Column(name = "name")
  String name;
  @Column(name = "email", unique = true)
  String email;
  @OneToMany(mappedBy = "user")
  private List<entity> entities = new ArrayList<>();
}
