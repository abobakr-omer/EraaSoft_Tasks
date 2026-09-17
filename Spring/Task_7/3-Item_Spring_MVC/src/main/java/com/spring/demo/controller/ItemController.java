package com.spring.demo.controller;


import com.spring.demo.model.Item;
import com.spring.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/all-items")
    public String showAllItems(Model model){
        List<Item> items = itemService.showAllItem();
        model.addAttribute("items",items);

        return "items";
    }

    @PostMapping("/add-item")
    public String addItem(@ModelAttribute("item")Item item){

        itemService.addItem(item);

        return "redirect:/items/all-items";
    }

    @GetMapping("/show-item/{id}")
    public String showItem(@PathVariable("id") Long id,Model model){
        Item  item =itemService.showItemById(id);
        model.addAttribute("item",item);

        return "item-details";
    }

    @GetMapping("/show-item-form")
    public String showItemForm(){
        return "add-item";
    }

    @PostMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable("id") Long id){
        itemService.deleteItem(id);

        return "redirect:/items/all-items";
    }

    @PostMapping("/update-item/{id}")
    public String updateItem(@PathVariable("id") Long id,@ModelAttribute("item") Item item){
        itemService.updateItem(id,item);


        return "redirect:/items/all-items";
    }

    @GetMapping("/show-update-form/{id}")
    public String showUpdateForm(@PathVariable("id") Long id,Model model){
        Item item = itemService.showItemById(id);


        model.addAttribute("item",item);

        return "update-item";

    }



}
