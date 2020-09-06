package com.jpa1.jpa1.domain;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MEMBER")
@Getter @Setter
@ToString(exclude = "orders")
@EqualsAndHashCode(of = "id")
public class Member {

  @Id @GeneratedValue(strategy = IDENTITY)
  @Column(name = "MEMBER_ID")
  private Long id;
  private String name;

  @Embedded
  private Address address;

  @OneToMany(cascade = ALL, mappedBy = "member")
  List<Order> orders = new ArrayList<>();
  
}