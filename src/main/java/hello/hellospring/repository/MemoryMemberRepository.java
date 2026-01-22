package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 세팅
        store.put(member.getId(), member); // 저장
        return member; // 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // ㄴ 결과가 null 이어도 반환 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 찾는 값이 더 있나 계속 찾음
        // ㄴ 파라미터로 온 값이 저장되어 있는 값이랑 같을 때만 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    
    // 저장된 값들을 초기화
    public void clearStore() {
        store.clear();
    }


}
