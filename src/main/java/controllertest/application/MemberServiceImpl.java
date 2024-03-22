package controllertest.application;

import controllertest.dto.AppendRequest;
import controllertest.dto.HelloRequest;
import controllertest.dto.MemberResponse;
import controllertest.implement.MemberAppender;
import controllertest.implement.MemberFinder;
import controllertest.persistence.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberFinder memberFinder;
    private final MemberAppender memberAppender;

    public MemberServiceImpl(final MemberFinder memberFinder, final MemberAppender memberAppender) {
        this.memberFinder = memberFinder;
        this.memberAppender = memberAppender;
    }

    @Override
    public MemberResponse findMember(final long memberId) {
        Member member = memberFinder.getById(memberId);
        return new MemberResponse(member.getId(), member.getName(), member.getAge());
    }

    @Override
    public void append(final AppendRequest appendRequest) {
        memberAppender.append(appendRequest);
    }
}