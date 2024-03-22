package controllertest.implement;

import controllertest.persistence.Member;
import controllertest.persistence.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MemberFinder {

    private final MemberRepository memberRepository;

    public MemberFinder(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public Member getById(final long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
