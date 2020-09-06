package com.jpa1.jpa1.domain;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ORDERS")
@Getter @Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "member", "orderItems", "delivery" })
public class Order {

  @Id @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ORDER_ID")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "MEMBER_ID")
  private Member member;

  @OneToMany(mappedBy = "order", cascade = ALL)
  private List<OrderItem> orderItems = new ArrayList<>();

  @OneToOne(fetch = LAZY, cascade = ALL)
  @JoinColumn(name = "DELIVERY_ID")
  private Delivery delivery;

  @Enumerated(STRING)
  private OrderStatus status;

  private LocalDateTime orderDate;

  /**
   * Helper mathods for setting 2way references
   * 
   * @param member
   */
  public void setMember(Member member) {
    this.member = member;
    member.getOrders().add(this);
  }

  public void setOrderItems(OrderItem orderItem) {
    orderItems.add(orderItem);
    orderItem.setOrder(this);
  }

  public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
    delivery.setOrder(this);
  }

  /**
   * Helper method for creating order entity
   * 
   * @param member
   * @param delivery
   * @param orderItems
   * @return
   */
  public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
    Order order = new Order();
    order.setMember(member);
    order.setDelivery(delivery);
    Arrays.stream(orderItems).forEach(oi -> order.setOrderItems(oi));
    order.setStatus(OrderStatus.ORDER);
    order.setOrderDate(LocalDateTime.now());
    return order;
  }

  /**
   * Helpeer methods for business logic
   */
  public void cancel() {
    if (!delivery.isCancelable()) {
      throw new IllegalStateException("Can't cancel the order in : " + delivery.getStatus().name());
    }
    setStatus(OrderStatus.CANCEL);
    getOrderItems().forEach(oi -> oi.cancel());
  }

  public int getTotalOrderPrice() {
    return getOrderItems().stream().mapToInt(oi -> oi.getTotalPrice()).sum();
  }
}
