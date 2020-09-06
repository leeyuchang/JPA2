package com.jpa1.jpa1.service;

import com.jpa1.jpa1.domain.OrderStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class OrderSearch {

  private String memberName;
  private OrderStatus orderStatus;

}
