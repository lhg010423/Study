package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    // test 에서는 메서드 이름을 한글로 가능
    // ctrl + r : 이전에 실행했던 거를 다시 실행

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;



    @Test
    void 회원가입() {
        // given 뭔가가 주어짐
        Member member = new Member();
        member.setName("spring100");

        // when 이거를 실행함
        Long saveId = memberService.join(member);

        // then 결과로 이게 나옴
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // 람다식 뒤에 메서드를 실행, 만약 오류가 생겼는데 그 오류가
        // IllegalStateException.class 이면 true
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 변수 추출 단축키 : ctrl + alt + v
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }

}