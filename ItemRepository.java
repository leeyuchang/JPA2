package com.jpa1.jpa1.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.jpa1.jpa1.domain.Item;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

  private final EntityManager em;

  public void save(Item item) {
    if (item.getId() == null) {
      em.persist(item);
    } else {
      em.merge(item);
    }
  }

  public Item findOne(Long id) {
    return em.find(Item.class, id);
  }

  public List<Item> findAll() {
    return em.createQuery("select i from Item i", Item.class).getResultList();
  }
}
