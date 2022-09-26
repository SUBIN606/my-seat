//package com.codesoom.myseat.services;
//
//import com.codesoom.myseat.domain.Seat;
//import com.codesoom.myseat.domain.User;
//import com.codesoom.myseat.repositories.SeatRepository;
//import com.codesoom.myseat.domain.SeatReservation;
//import com.codesoom.myseat.repositories.SeatReservationRepository;
//import com.codesoom.myseat.exceptions.SeatNotFoundException;
//import com.codesoom.myseat.exceptions.SeatReservationNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
///**
// * 좌석 예약 취소 서비스
// */
//@Service
//public class SeatReservationCancelService {
//    private final SeatRepository seatRepository;
//    private final SeatReservationRepository reservationRepository;
//
//    public SeatReservationCancelService(SeatRepository seatRepository, SeatReservationRepository reservationRepository) {
//        this.seatRepository = seatRepository;
//        this.reservationRepository = reservationRepository;
//    }
//
//    /**
//     * 취소된 좌석 예약 정보를 반환한다.
//     * 
//     * @param seatNumber 예약 취소할 좌석 번호
//     * @param user 취소 요청한 사용자 정보
//     * @return 좌석 예약 정보
//     * @throws SeatNotFoundException 좌석을 찾을 수 없는 경우 예외를 던진다.
//     * @throws SeatReservationNotFoundException 좌석 예약 내역을 찾을 수 없는 경우 예외를 던진다.
//     */
//    public SeatReservation cancelReservation(
//            int seatNumber
////            User user
//    ) {
//        Seat seat = seat(seatNumber);
//        seat.cancelReservation();
//        
//        SeatReservation reservation = seatReservation(user.getId());
//        reservation.cancelReservation();
//        reservationRepository.delete(reservation);
//
//        return reservation;
//    }
//
//    /**
//     * 좌석 예약 내역을 반환한다.
//     *
//     * @param userId 사용자 id
//     * @return 좌석 예약 내역
//     * @throws SeatReservationNotFoundException 좌석 예약 내역을 찾을 수 없는 경우 예외를 던진다.
//     */
//    private SeatReservation seatReservation(Long userId) {
//        return reservationRepository.findByDateAndUserId(today(), userId)
//                .orElseThrow(() -> new SeatReservationNotFoundException(
//                        "[" + userId + "] 회원의 당일 예약 내역을 찾을 수 없어서 조회에 실패했습니다."));
//    }
//
//    /**
//     * 조회된 좌석을 반환한다.
//     *
//     * @param seatNumber 좌석 번호
//     * @return 좌석
//     * @throws SeatNotFoundException 좌석을 찾을 수 없는 경우 예외를 던진다.
//     */
//    private Seat seat(int seatNumber) {
//        return seatRepository.findByNumber(seatNumber)
//                .orElseThrow(() -> new SeatNotFoundException(
//                        "[" + seatNumber + "]번 좌석을 찾을 수 없어서 조회에 실패했습니다."));
//    }
//
//    /**
//     * 오늘 날짜를 반환한다.
//     *
//     * @return 오늘 날짜
//     */
//    private String today() {
//        LocalDateTime now = LocalDateTime.now();
//        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//    }
//}
