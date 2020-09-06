package com.jpa1.jpa1.service;

import java.util.List;

import com.jpa1.jpa1.domain.Delivery;
import com.jpa1.jpa1.domain.DeliveryStatus;
import com.jpa1.jpa1.domain.Item;
import com.jpa1.jpa1.domain.Member;
import com.jpa1.jpa1.domain.Order;
import com.jpa1.jpa1.domain.OrderItem;
import com.jpa1.jpa1.repository.ItemRepository;
import com.jpa1.jpa1.repository.MemberRepository;
import com.jpa1.jpa1.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

  @Autowired MemberRepository memberRepository;
  @Autowired OrderRepository orderRepository;
  @Autowired ItemRepository itemRepository;

  /**
   * Order service
   * @param memberId
   * @param itemId
   * @param count
   * @return
   */
  public Long order(Long memberId, Long itemId, int count) {
    Member member = memberRepository.fineOne(memberId);
    Item item = itemRepository.findOne(itemId);
    Delivery delivery = new Delivery(DeliveryStatus.READY, member.getAddress());
    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
    Order order = Order.createOrder(member, delivery, orderItem);
    orderRepository.save(order);
    return order.getId();
  }

  /**
   * Order cancel service
   * @param orderId
   */
  public void cancel(Long orderId) {
    Order order = orderRepository.findByOrderId(orderId);
    order.cancel();
  }

  /**
   * Order list service by QueryDSL
   * @param orderSearch
   * @return
   */
  public List<Order> findOrders(OrderSearch orderSearch) {
    return orderRepository.findAll(orderSearch);
  }
}
