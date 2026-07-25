package com.item.service;

import java.util.List;

import com.item.model.Item;

public interface ItemService {

	// Adds a new item.
	boolean addItem(Item item);

	// Updates an existing item.
	boolean updateItem(Item item);

	// Gets one item by ID.
	Item getItemById(Long id);

	// Deletes one item by ID.
	boolean removeItemById(Long id);

	// Gets all items.
	List<Item> getItems();
}
