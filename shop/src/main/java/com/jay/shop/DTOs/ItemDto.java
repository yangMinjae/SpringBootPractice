package com.jay.shop.DTOs;

public class ItemDto {
    public record AddRequest(String title, int price, String fileKey){}
    public record UpdateRequest(Long id, String title, Integer price){}
}
