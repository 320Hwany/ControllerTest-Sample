package controllertest.application;

import controllertest.dto.AppendRequest;
import controllertest.dto.MemberResponse;

public interface MemberService {

    MemberResponse findMember(final long memberId);

    void append(final AppendRequest appendRequest);
}
