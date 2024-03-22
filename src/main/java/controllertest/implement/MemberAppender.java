package controllertest.implement;

import controllertest.dto.AppendRequest;
import controllertest.persistence.Member;
import controllertest.persistence.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MemberAppender {

    private final MemberRepository memberRepository;

    public MemberAppender(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void append(final AppendRequest appendRequest) {
        Member member = new Member(appendRequest.name(), appendRequest.age());
        memberRepository.save(member);
    }
}
