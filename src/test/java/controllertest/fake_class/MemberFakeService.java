package controllertest.fake_class;

import controllertest.dto.AppendRequest;
import controllertest.application.MemberService;
import controllertest.dto.MemberResponse;

public class MemberFakeService implements MemberService {

    @Override
    public MemberResponse findMember(final long memberId) {
        return new MemberResponse(memberId, "name", 20);
    }

    @Override
    public void append(final AppendRequest appendRequest) {
        System.out.println("Hello Append");
    }
}
