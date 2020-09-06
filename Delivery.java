package com.jpa1.jpa1.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DELIVERY")
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Delivery {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "DELIVERY_ID")
  private Long id;

  @OneToOne(mappedBy = "delivery")
  private Order order;

  @Enumerated(EnumType.STRING)
  private DeliveryStatus status;

  @Embedded
  private Address address;

  public Delivery(DeliveryStatus status, Address address) {
    this.status = status;
    this.address = address;
  }
  
}
