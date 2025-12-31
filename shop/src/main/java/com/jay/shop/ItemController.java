package com.jay.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
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

    @PostMapping("/add")
    String addPost(String title, Integer price){
        itemService.saveItem(title, price);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model){
        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("data", result.get());
            return "detail.html";
        }else{
            return "redirect:/list";
        }
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model){
        return itemRepository
                .findById(id)
                .map(item -> {
                    model.addAttribute("data",item);
                    return "edit.html";
                })
                .orElse("redirect:/list");
    }

    @PostMapping("/update")
    String update(Long id, String title, Integer price){
        itemService.updateItem(id, title, price);
        return "redirect:/list";
    }


}
