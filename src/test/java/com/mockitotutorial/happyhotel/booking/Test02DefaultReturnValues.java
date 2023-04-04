package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.*;

class Test02DefaultReturnValues {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;

	@BeforeEach
	void setup() {
		this.paymentServiceMock = mock(PaymentService.class);
		this.roomServiceMock = mock(RoomService.class);
		this.bookingDAOMock = mock(BookingDAO.class);
		this.mailSenderMock = mock(MailSender.class);

		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

		// mockito return values for list
		System.out.println("Default list returned from mockito: " + roomServiceMock.getAvailableRooms());
		System.out.println("Default string returned from mockito: " + roomServiceMock.findAvailableRoomId(null));
		System.out.println("Default int returned from mockito: " + roomServiceMock.getRoomCount());
	}

	@Test
	void should_CountAvailablePlaces() {
		// given
		int expected = 0; // here we rely on mockito default return values for mocks

		// when
		int actual = bookingService.getAvailablePlaceCount();

		// then
		assertEquals(expected, actual);

	}

}
