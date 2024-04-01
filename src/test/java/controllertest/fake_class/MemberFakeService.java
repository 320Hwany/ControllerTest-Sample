package controllertest.fake_class;

import controllertest.dto.AppendRequest;
import controllertest.application.MemberService;
import controllertest.dto.MemberResponse;
import controllertest.error.BadRequestException;

public class MemberFakeService implements MemberService {

    @Override
    public MemberResponse findMember(final long memberId) {
        if (memberId == 1L) {
            return new MemberResponse(memberId, "name", 20);
        }

        throw new BadRequestException("잘못된 요청입니다.");
    }

    @Override
    public void append(final AppendRequest appendRequest) {
        System.out.println("Hello Append");
    }
}
