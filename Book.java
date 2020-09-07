package com.jpa1.jpa1.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.ToString;

@Entity
@DiscriminatorValue("B")
@Getter @ToString
public class Book extends Item {
  private String name;
  private String isbn;
}
