package com.example.Blog_API.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;

@Column(name = "content")
String content;
@ManyToOne
@JoinColumn(name = "entity_id")
private entity entity;
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
}
