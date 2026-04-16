package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

@Entity
@Table(name="messages")
public class Messages {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column (nullable = false,unique = true)
    private String user;

    @Column
    private String messages;

}
