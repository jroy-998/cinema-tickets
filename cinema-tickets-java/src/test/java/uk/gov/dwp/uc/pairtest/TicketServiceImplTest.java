package uk.gov.dwp.uc.pairtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.calculator.DefaultPriceCalculator;
import uk.gov.dwp.uc.pairtest.calculator.DefaultSeatCalculator;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.validation.DefaultAccountValidator;
import uk.gov.dwp.uc.pairtest.validation.TicketRequestValidatorImpl;

public class TicketServiceImplTest {
    private TicketServiceImpl ticketService;

    @BeforeEach
    void beforeEach() {
        this.ticketService = new TicketServiceImpl(
                new TicketRequestValidatorImpl(),
                new DefaultAccountValidator(),
                new DefaultPriceCalculator(),
                new DefaultSeatCalculator(),
                new TicketPaymentServiceImpl(),
                new SeatReservationServiceImpl()
        );
    }

    @Test
    void purchaseTest() {
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);

        this.ticketService.purchaseTickets(1L, adultRequest);
    }
}
