package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    // test 에서는 메서드 이름을 한글로 가능
    // ctrl + r : 이전에 실행했던 거를 다시 실행

    MemberService memberService;
    // 바로 아래 afterEach를 사용하기 위해 아래 객체 생성
    MemoryMemberRepository memberRepository;

    // DI 개념, 의존성 주입, 아래 기능을 @Autowired 로 가능
    @BeforeEach
    public void beforeEach() {
        // 1. 여기서 진짜 금고(저장소)를 하나 딱 만듭니다.
        memberRepository = new MemoryMemberRepository();
        // 2. 서비스에게 "자, 네가 쓸 금고는 이거야!"라고 생성자를 통해 넘겨줍니다.
        memberService = new MemberService(memberRepository);
    }



    // test를 끝날때마다 메서드가 실행이 끝날때마다 실행되는 메서드
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        // given 뭔가가 주어짐
        Member member = new Member();
        member.setName("hello");

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

/*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
        }
*/

        // then

    }




    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}