package controllertest.controller;

import controllertest.dto.AppendRequest;
import controllertest.application.MemberService;
import controllertest.dto.MemberResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/{memberId}")
    public MemberResponse find(@PathVariable final long memberId) {
        return memberService.findMember(memberId);
    }

    @PostMapping("/members")
    public void append(@RequestBody final AppendRequest appendRequest) {
        memberService.append(appendRequest);
    }
}
