package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test를 끝날때마다 메서드가 실행이 끝날때마다 실행되는 메서드
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
        // 아래 코드의 앞부분 Assertions에 커서를 올리고 alt + enter를 하면
        // Add on demand static import for... 임포트하는게 나옴 그거 클릭
        // Assertions.assertThat(member).isEqualTo(result);


        // Assertions -> org.junit.jupiter.api 선택
        // Assertions.assertEquals(member, result);

        // 아래처럼 해도 됨
        // System.out.println("result = " + (result == member));
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        // 글씨를 바꾸고 싶은 곳에 커서를 올리고 shift + F6을 하면
        // 해당 글씨를 전부 바꿀 수 있음
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);


        // run 단축키 : ctrl + shift + F10
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }




    // 테스트를 각각의 메서드로 하면 상관없지만 클래스를 run해서 전체 test를
    // 실행하면 메서드 실행 순서가 랜덤이다
    // 맨위에 첫번째로 작성했다고 그게 먼저 실행 안됨
    // 모든 테스트는 메서드별로 따로 실행한다

}
