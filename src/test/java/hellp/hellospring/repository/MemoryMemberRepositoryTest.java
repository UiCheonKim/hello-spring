package hellp.hellospring.repository;

import hellp.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // -> 밑 메소드가 끝날때마다 어떤 동작을 하는 것, 콜백 메서드
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // Optional<Member> byId = repository.findById(member.getId());
        // 반환타입이 Optional에서 값을 꺼낼때는 get을 써서 꺼낼수는 있다 -> 단 좋진않다 테스트로는 상관 x
        // get을 붙임으로서 옵션을 한번 깐다(?)
        /*
        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result==member));
         */
        /*
        Member result = repository.findById(member.getId()).get();
        assertEquals(member, result);
        */

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
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
}
