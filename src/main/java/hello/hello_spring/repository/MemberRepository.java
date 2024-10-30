package hello.hello_spring.repository;

// Member 도메인 클래스를 가져옵니다. 이 클래스는 회원의 정보를 담고 있습니다.
import hello.hello_spring.domain.Member;

// List와 Optional을 사용하기 위해 가져옵니다.
import java.util.List;
import java.util.Optional;

// 회원 정보를 관리하기 위한 메소드를 정의하는 인터페이스입니다.
public interface MemberRepository {

    // 회원을 저장하는 메소드. 저장한 회원 객체를 반환합니다.
    Member save(Member member);

    // 주어진 ID로 회원 정보를 찾는 메소드. 회원이 존재하지 않으면 Optional.empty()를 반환합니다.
    Optional<Member> findById(Long id);

    // 주어진 이름으로 회원 정보를 찾는 메소드. 일치하는 회원이 없을 경우 Optional.empty()를 반환합니다.
    Optional<Member> findByName(String name);

    // 저장된 모든 회원 정보를 리스트 형태로 반환하는 메소드입니다.
    List<Member> findAll();
}
