package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.services.AuthenticationService;
import com.codesoom.myseat.services.SeatReservationService;
import com.codesoom.myseat.services.SeatService;
import com.codesoom.myseat.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeatReservationController.class)
@AutoConfigureRestDocs(
        uriScheme = "https", 
        uriHost = "api.codesoom-myseat.site"
)
class SeatReservationControllerTest {
    private static final String ACCESS_TOKEN 
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    private static final Long USER_ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String ENCODED_PASSWORD
            = "$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW";

    private static final Long SEAT_ID = 1L;
    private static final int NUMBER = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private SeatReservationService seatReservationService;

    @MockBean
    private UserService userService;

    @MockBean
    private SeatService seatService;

    @Test
    @DisplayName("예약 성공")
    void reservation_success() throws Exception {
        // given
        User user = User.builder()
                .id(USER_ID)
                .name(NAME)
                .email(EMAIL)
                .password(ENCODED_PASSWORD)
                .build();

        Seat seat = Seat.builder()
                .id(SEAT_ID)
                .number(NUMBER)
                .status(false)
                .build();

        given(authService.parseToken(ACCESS_TOKEN))
                .willReturn(EMAIL);

        given(userService.findUser(EMAIL))
                .willReturn(user);

        given(seatService.findSeat(NUMBER))
                .willReturn(seat);

        // when
        ResultActions subject 
                = mockMvc.perform(
                        post("/seat-reservation/{number}", NUMBER)
                                .header("Authorization", "Bearer " + ACCESS_TOKEN));

        // then
        subject.andExpect(status().isNoContent());

        // docs
        subject.andDo(document("seat-reservation",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                        parameterWithName("number").description("좌석 번호")
                )
        ));
    }
}
