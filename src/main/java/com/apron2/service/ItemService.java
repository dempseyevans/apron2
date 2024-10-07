package com.apron2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apron2.entity.Item;

@Service
public interface ItemService {

List<Item> getAllItems(String search);
    Item getItemById(Long id);
    Item createItem(Item item);
    Item updateItem(Long id, Item item);
    void deleteItem(Long id);

}
