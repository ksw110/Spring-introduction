package hello.hello_spring.repository;

// Member 도메인 클래스를 가져옵니다. 이 클래스는 회원의 정보를 담고 있습니다.
import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

// 다양한 데이터를 저장하기 위한 컬렉션을 제공하는 패키지입니다.
import java.util.*;

// MemberRepository 인터페이스를 구현하는 MemoryMemberRepository 클래스입니다.

public class MemoryMemberRepository implements MemberRepository {

    // 모든 회원 정보를 저장할 HashMap을 정의합니다. ID를 키로, Member 객체를 값으로 사용합니다.
    private static Map<Long, Member> store = new HashMap<>();

    // 회원 ID를 자동으로 증가시키기 위한 시퀀스 변수입니다. 처음에는 0으로 설정합니다.
    private static long sequence = 0L;

    // 회원 정보를 저장하는 메소드입니다.
    @Override
    public Member save(Member member) {
        // 시퀀스를 증가시켜 새로운 회원에게 고유한 ID를 부여합니다.
        member.setId(++sequence);
        // ID와 회원 객체를 맵에 저장합니다.
        store.put(member.getId(), member);

        // 저장한 회원 객체를 반환합니다.
        return member;
    }

    // ID로 회원 정보를 조회하는 메소드입니다.
    @Override
    public Optional<Member> findById(Long id) {
        // ID에 해당하는 회원을 Optional로 감싸서 반환합니다.
        // 회원이 존재하지 않을 경우 null을 반환할 수 있으므로 Optional로 처리합니다.
        return Optional.ofNullable(store.get(id));
    }

    // 이름으로 회원 정보를 찾는 메소드입니다.
    @Override
    public Optional<Member> findByName(String name) {
        // 저장된 회원 목록에서 이름이 일치하는 회원을 찾습니다.
        // stream을 사용하여 필터링한 후, 일치하는 회원이 있을 경우 반환합니다.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // 저장된 모든 회원 정보를 리스트로 반환하는 메소드입니다.
    @Override
    public List<Member> findAll() {
        // HashMap에 저장된 회원 객체의 값을 ArrayList로 변환하여 반환합니다.
        return new ArrayList<>(store.values());
    }

    // 저장소를 초기화하는 메소드입니다. 테스트 시 데이터 간섭을 방지하기 위해 사용합니다.
    public void clearStore() {
        // 모든 회원 정보를 맵에서 제거합니다.
        store.clear();
    }
}
