package controllertest.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import controllertest.dto.AppendRequest;
import controllertest.fake_class.MemberFakeService;
import controllertest.application.MemberService;
import controllertest.util.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.http.MediaType.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class MemberControllerTest extends ControllerTest {

    private final MemberService memberService = new MemberFakeService();

    @Override
    protected Object initController() {
        return new MemberController(memberService);
    }

    @DisplayName("Find 테스트")
    @Test
    void find() throws Exception {
        long memberId = 1L;

        mockMvc.perform(
                        get("/members/{memberId}", memberId)
                )
                .andExpect(status().isOk())
                .andDo(document("Find 검증 API",
                        pathParameters(
                                parameterWithName("memberId").description("회원 id")
                        ),
                        resource(ResourceSnippetParameters.builder()
                                .tag("회원")
                                .summary("회원 찾기")
                                .responseFields(
                                        fieldWithPath("memberId").type(NUMBER).description("회원 id"),
                                        fieldWithPath("name").type(STRING).description("이름"),
                                        fieldWithPath("age").type(NUMBER).description("나이")
                                )
                                .build()
                        )));
    }

    @DisplayName("Append 테스트")
    @Test
    void append() throws Exception {
        AppendRequest appendRequest = new AppendRequest("name", 20);
        String json = objectMapper.writeValueAsString(appendRequest);

        mockMvc.perform(
                        post("/members")
                                .contentType(APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andDo(document("Append 검증 API",
                        resource(ResourceSnippetParameters.builder()
                                .tag("회원")
                                .summary("회원가입")
                                .requestFields(
                                        fieldWithPath("name").type(STRING).description("이름"),
                                        fieldWithPath("age").type(NUMBER).description("나이")
                                )
                                .build()
                        )));
    }

    // 템플릿 용도
    @DisplayName("테스트 템플릿")
    @Test
    void testTemplate() throws Exception {
        MockHttpSession session = new MockHttpSession();
        long memberId = 1L;
        AppendRequest appendRequest = new AppendRequest("name", 20);
        String json = objectMapper.writeValueAsString(appendRequest);

        mockMvc.perform(
                        post("/members/{memberId}", memberId)
                                .session(session)
                                .param("keyword", "value")
                                .contentType(APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andDo(document("테스트 템플릿 API",
                        pathParameters(
                                parameterWithName("memberId").description("회원 id")
                        ),
                        resource(ResourceSnippetParameters.builder()
                                .tag("테스트")
                                .summary("테스트 템플릿")
                                .requestHeaders(
                                        headerWithName("session").description("세션값")
                                )
                                .queryParameters(
                                        parameterWithName("keyword").description("검색 keyword")
                                )
                                .requestFields(
                                        fieldWithPath("name").type(STRING).description("이름"),
                                        fieldWithPath("age").type(NUMBER).description("나이")
                                )
                                .responseFields(
                                        fieldWithPath("memberId").type(NUMBER).description("회원 id"),
                                        fieldWithPath("name").type(STRING).description("이름"),
                                        fieldWithPath("age").type(NUMBER).description("나이")
                                )
                                .build()
                        )));
    }
}