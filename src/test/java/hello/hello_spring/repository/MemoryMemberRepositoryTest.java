package hello.hello_spring.repository;

// Member 도메인 클래스를 가져옵니다. 이 클래스는 회원의 정보를 담고 있습니다.
import hello.hello_spring.domain.Member;

// Assertions를 가져와서 테스트 결과를 검증하는 데 사용합니다.
import org.assertj.core.api.Assertions;

// 테스트 후에 실행할 메소드를 정의하기 위해 사용합니다.
import org.junit.jupiter.api.AfterEach;

// 테스트 메소드를 정의하기 위해 사용합니다.
import org.junit.jupiter.api.Test;

// List를 사용하기 위해 가져옵니다. 여러 회원을 저장하고 조회하는 데 사용합니다.
import java.util.List;

// Assertions를 정적 임포트하여 간편하게 사용할 수 있습니다.
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    // MemoryMemberRepository의 인스턴스를 생성합니다. 이 객체를 통해 회원 데이터를 메모리에 저장하고 관리합니다.
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 테스트가 끝난 후에 실행되는 메소드입니다.
    @AfterEach
    public void afterEach() {
        // 테스트가 끝난 후, 메모리에 저장된 회원 데이터를 초기화합니다.
        // 이는 각 테스트가 서로 영향을 주지 않도록 하기 위한 것입니다.
        repository.clearStore();
    }

    // 회원을 저장하는 기능을 테스트하는 메소드입니다.
    @Test
    public void save() {
        // 새로운 Member 객체를 생성합니다. 이 객체는 회원 정보를 담고 있습니다.
        Member member = new Member();
        // 회원의 이름을 "spring"으로 설정합니다.
        member.setName("spring");

        // 생성한 회원을 메모리에 저장합니다.
        repository.save(member);
        // 저장한 회원의 ID를 사용하여 다시 조회합니다. get()은 Optional에서 값을 가져오는 메소드입니다.
        Member result = repository.findById(member.getId()).get();

        // 저장한 회원과 조회한 회원이 동일한지 확인합니다.
        // 이 검증이 통과하면 save() 메소드가 올바르게 작동하는 것입니다.
        assertThat(member).isEqualTo(result);
    }

    // 이름으로 회원을 찾는 기능을 테스트하는 메소드입니다.
    @Test
    public void findByName() {
        // 첫 번째 회원을 생성하고 저장합니다.
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // 두 번째 회원을 생성하고 저장합니다.
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // "spring1"이라는 이름으로 회원을 조회합니다.
        // findByName() 메소드는 Optional<Member>를 반환합니다.
        Member result = repository.findByName("spring1").get();

        // 찾은 회원이 첫 번째 회원과 같은지 확인합니다.
        // 이 검증이 통과하면 findByName() 메소드가 올바르게 작동하는 것입니다.
        assertThat(result).isEqualTo(member1);
    }

    // 모든 회원을 찾는 기능을 테스트하는 메소드입니다.
    @Test
    public void findAll() {
        // 첫 번째 회원을 생성하고 저장합니다.
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // 두 번째 회원을 생성하고 저장합니다.
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 저장소에 있는 모든 회원을 조회합니다.
        List<Member> result = repository.findAll();

        // 조회한 회원의 수가 2명인지 확인합니다.
        // 이 검증이 통과하면 findAll() 메소드가 올바르게 작동하는 것입니다.
        assertThat(result.size()).isEqualTo(2);
    }
}
