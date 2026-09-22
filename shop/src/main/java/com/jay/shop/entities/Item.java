package com.jay.shop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Table(name = "tb_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Item extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String title;
    private Integer price;

    public void update(String title, Integer price){
        this.title = title;
        this.price = price;
    }
}

