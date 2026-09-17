package com.spring.demo.service.impl;

import com.spring.demo.model.Item;
import com.spring.demo.repo.ItemRepo;
import com.spring.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.demo.exception.ValidationException;
import com.spring.demo.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {


    private final ItemRepo itemRepo;

    @Autowired
    public ItemServiceImpl(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }


    @Override
    public Item addItem(Item item) {
       validateItem(item);
       if (item.getId() != null) {
           throw new ValidationException("A new item must not have an ID");
       }
       return  itemRepo.save(item);
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {
        itemRepo.delete(showItemById(id));
    }

    @Override
    public List<Item> showAllItem() {
        return itemRepo.findAll();
    }

    @Override
    public Item showItemById(Long id) {
        validateId(id);
        return itemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found: " + id));
    }

    @Override
    @Transactional
    public void updateItem(Long id,Item item) {
        validateId(id);
        validateItem(item);
        if (item.getId() != null && !id.equals(item.getId())) {
            throw new ValidationException("Item form ID must match the path ID");
        }
        Item existingItem = showItemById(id);

        existingItem.setName(item.getName());
        existingItem.setPrice(item.getPrice());
        existingItem.setQuantity(item.getQuantity());

        itemRepo.save(existingItem);

    }
    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("Item ID must be positive");
        }
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new ValidationException("Item is required");
        }
        if (item.getName() == null || item.getName().isBlank() || item.getName().length() > 255) {
            throw new ValidationException("Item name is required and must not exceed 255 characters");
        }
        if (item.getPrice() == null || !Double.isFinite(item.getPrice()) || item.getPrice() < 0) {
            throw new ValidationException("Item price is required and must be a finite, non-negative number");
        }
        if (item.getQuantity() == null || item.getQuantity() < 0) {
            throw new ValidationException("Item quantity is required and must be zero or greater");
        }
    }
}
