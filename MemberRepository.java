package com.jpa1.jpa1.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.jpa1.jpa1.domain.Member;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

  private final EntityManager em;

  public void save(Member member) {
    em.persist(member);
  }

  public Member fineOne(Long id) {
    return em.find(Member.class, id);
  }

  public List<Member> findAll() {
    return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
  }

  public List<Member> findByName(String name) {
    return em.createQuery("SELECT m FROM Member m WHERE m.name = :name ", Member.class)
             .setParameter("name", name)
             .getResultList();
  }
}
