package com.jpa1.jpa1.domain;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public abstract class Item {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ITEM_ID")
  private Long id;
  private String name;
  private int price;
  private int stockQuantity;

  public void addStock(int count) {
    setStockQuantity(getStockQuantity() + count);
  }

  public void removeStock(int count) {
    int restStockQuantity = getStockQuantity() - count;
    if (restStockQuantity < 0) {
      throw new NotEnoughStockException("Out of stock : " + getName());
    }
    setStockQuantity(restStockQuantity);
  }

}
