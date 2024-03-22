package controllertest.dto;

public record MemberResponse(
        long memberId,
        String name,
        int age
) {
}
