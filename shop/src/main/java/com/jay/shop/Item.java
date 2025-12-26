package com.jay.shop;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@ToString
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    //@Column(length = 1000)
    public String title;
    public Integer price;
    public LocalDateTime date;
}

