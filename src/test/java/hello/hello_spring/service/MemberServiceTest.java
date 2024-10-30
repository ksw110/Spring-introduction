package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
class MemberServiceTest {
// 테스트 코드는 한글로 적어도 됨

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        // 테스트가 끝난 후, 메모리에 저장된 회원 데이터를 초기화합니다.
        // 이는 각 테스트가 서로 영향을 주지 않도록 하기 위한 것입니다.
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given (무엇인가 주어졌는데)
        Member member = new Member();
        member.setName("spring");

        //when (실행했을 때)
        Long seveId = memberService.join(member);

        //then (결과가 나와야함)
       Member findMember = memberService.findOne(seveId).get();
       assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when ; Ctrl + Alt + V 변수를 자동으로 생성하는 기능
        memberService.join(member1); //람다식 예외처리 함수
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//
//        }

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}