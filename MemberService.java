package com.jpa1.jpa1.service;

import java.util.List;

import com.jpa1.jpa1.domain.Member;
import com.jpa1.jpa1.repository.MemberRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public Long join(Member member) {
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    List<Member> findMember = memberRepository.findByName(member.getName());
    if(!findMember.isEmpty()) {
      throw new IllegalStateException("Already");
    }
  }
}
