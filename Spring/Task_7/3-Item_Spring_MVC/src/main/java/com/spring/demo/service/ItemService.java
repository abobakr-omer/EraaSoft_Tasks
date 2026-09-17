package com.spring.demo.service;

import com.spring.demo.model.Item;


import java.util.List;

public interface ItemService {


    Item addItem(Item item);

    void deleteItem(Long id);

    List<Item> showAllItem();

    Item showItemById(Long id);

    void updateItem(Long id,Item item);



}
