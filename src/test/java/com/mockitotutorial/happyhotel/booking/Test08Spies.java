package com.mockitotutorial.happyhotel.booking;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

class Test08Spies {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOSpy;
	private MailSender mailSenderMock;

	@BeforeEach
	void setup() {
		this.paymentServiceMock = mock(PaymentService.class);
		this.roomServiceMock = mock(RoomService.class);
		this.bookingDAOSpy = spy(BookingDAO.class);
		this.mailSenderMock = mock(MailSender.class);

		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOSpy, mailSenderMock);
	}

	@Test
	void should_InvokePayment_When_Prepaid() {
		// given
		boolean isPrepaid = true;
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),
				2, isPrepaid);

		// when
		String bookingId = bookingService.makeBooking(bookingRequest);

		// then
		verify(bookingDAOSpy).save(bookingRequest);
		System.out.println("bookingId=" + bookingId);

	}
	
	@Test
	void should_CancelBooking_When_InputOk() {
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),
				2, true);
		bookingRequest.setRoomId("1.3");
		String bookingId = "1";
		
		doReturn(bookingRequest).when(bookingDAOSpy).get(bookingId);

		// when
		bookingService.cancelBooking(bookingId);

		// then
		verify(bookingDAOSpy, times(1)).delete(bookingId);
	}

}
