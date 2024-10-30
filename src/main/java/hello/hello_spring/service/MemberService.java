package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
// 클래스에서 테스트생성 및 실행 ctrl+shift+t
@Transactional

public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */

    public Long join(Member member){


            //같은 이름이 있는 중복 회원X
            // Extract method 단축키 ctrl + alt + m
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();

    }
    //Optional 객체가 값이 있을 경우, 그 값을 인자로 받아
    // 처리하는 람다식을 실행
    //없을 경우 아무 작업 하지 않음
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent((m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }));
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){

            return memberRepository.findAll();


    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
