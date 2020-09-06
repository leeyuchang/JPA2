package com.jpa1.jpa1.repository;

import static com.jpa1.jpa1.domain.QMember.member;
import static com.jpa1.jpa1.domain.QOrder.order;

import java.util.List;

import javax.persistence.EntityManager;

import com.jpa1.jpa1.domain.Order;
import com.jpa1.jpa1.service.OrderSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

  private final EntityManager em;

  public void save(Order order) {
    em.persist(order);
  }

  public Order findByOrderId(Long orderId) {
    return em.find(Order.class, orderId);
  }

  /**
   * Dynamic query by QueryDSL
   * @param orderSearch
   * @return
   */
  public List<Order> findAll(OrderSearch orderSearch) {

    JPQLQueryFactory queryFactory = new JPAQueryFactory(em);

    return queryFactory
        .selectFrom(order)
        .leftJoin(order.member, member)
        .where(likeName(orderSearch), eqStatus(orderSearch))
        .fetch();
  }

  private BooleanExpression likeName(OrderSearch orderSearch) {
    return orderSearch.getMemberName() != null ? member.name.contains(orderSearch.getMemberName()) : null;
  }

  private BooleanExpression eqStatus(OrderSearch orderSearch) {
    return orderSearch.getOrderStatus() != null ? order.status.eq(orderSearch.getOrderStatus()) : null;
  }

}
