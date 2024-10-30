package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
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
// 테스트 코드는 한글로 적어도 됨

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void 회원가입() {
        //given (무엇인가 주어졌는데)
        Member member = new Member();
        member.setName("spring1");

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

}