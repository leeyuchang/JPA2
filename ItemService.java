package com.jpa1.jpa1.service;

import java.util.List;

import com.jpa1.jpa1.domain.Item;
import com.jpa1.jpa1.repository.ItemRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public void saveItem(Item item) {
    itemRepository.save(item);
  }

  public List<Item> findItems() {
    return itemRepository.findAll();
  }

  public Item findOne(Long id) {
    return itemRepository.findOne(id);
  }
  
}
