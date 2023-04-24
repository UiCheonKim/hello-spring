package hellp.hellospring.repository;

import hellp.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // 실무에선 동시성 문제로 ConcurrentHashMap를 써야된다.
    private static long sequence = 0L;
    // 실무에선 동시성 문제로 AtomicLong를 써야된다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // return store.get(id) -> Null이 반환 될 가능성이 있으면 Optional.ofNullable로 감싼다.
        // 감싸서 반환해주면 Client에서 뭘 할 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 자바8의 람다 루프로 돌린다
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // findAny -> 하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // 멤버들 쭉 반환
    }

    public void clearStore() {
        store.clear(); // store 비움
    }
}
