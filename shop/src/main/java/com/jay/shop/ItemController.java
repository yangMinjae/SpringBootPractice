package com.jay.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping("/list")
    String list(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items",result);
        var a = new Item();
        System.out.println(a);
        return "list.html";
    }
    @GetMapping("/write")
    String write(){
        return "write.html";
    }

//    @PostMapping("/add")
//    String addPost(@RequestParam(name="title") String title, String price){
//        System.out.println(title);
//        System.out.println(price);
//        return "redirect:/list";
//    }
//    @PostMapping("/add")
//    String addPost(@RequestParam Map<String, String> formData){
//        String name = formData.get("title");
//        Integer price = Integer.parseInt(formData.get("price"));
//        if (name == null || name.trim().isEmpty()) {
//            throw new IllegalArgumentException("상품명은 필수입니다.");
//        }
//
//        if (name.length() < 2 || name.length() > 50) {
//            throw new IllegalArgumentException("상품명 길이 오류");
//        }
//
//        if (price == null || price <= 0) {
//            throw new IllegalArgumentException("가격은 0보다 커야 합니다.");
//        }
//        Item it = new Item();
//        it.setTitle(name);
//        it.setPrice(price);
//        itemRepository.save(it);
//        return "redirect:/list";
//    }
    @PostMapping("/add")
    String addPost(@ModelAttribute Item item){
        String name = item.getTitle();
        Integer price = item.getPrice();
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }

        if (name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException("상품명 길이 오류");
        }

        if (price == null || price <= 0) {
            throw new IllegalArgumentException("가격은 0보다 커야 합니다.");
        }
        itemRepository.save(item);
        return "redirect:/list";
    }

}
