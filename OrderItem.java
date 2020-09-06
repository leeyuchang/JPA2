package com.jpa1.jpa1.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDER_ITEM")
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class OrderItem {

  @Id @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ORDER_ITEM_ID")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "ITEM_ID")
  private Item item;
  
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "ORDER_ID")
  private Order order;

  private int orderPrice;

  private int count;

  public static OrderItem createOrderItem(Item item, int price, int count){
    OrderItem orderItem = new OrderItem();
    orderItem.setItem(item);
    orderItem.setOrderPrice(price);
    orderItem.setCount(count);
    return orderItem;
  }

  public void cancel() {
    item.addStock(count);
  }

  public int getTotalPrice() {
    return this.orderPrice * this.count;
  }

  private void setCount(int count) {
    this.count = count;
    item.removeStock(count);
  }

}
