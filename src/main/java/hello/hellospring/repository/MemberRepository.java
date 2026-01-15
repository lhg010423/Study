package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    // Optional : 자바8의 기능, 만약 값이 null이면 사용함
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();




}
