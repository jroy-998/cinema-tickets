package uk.gov.dwp.uc.pairtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.factory.TicketStrategyFactory;
import uk.gov.dwp.uc.pairtest.strategy.pricing.AdultTicketPricingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.pricing.ChildTicketPricingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.pricing.InfantTicketPricingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.seating.AdultTicketSeatingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.seating.ChildTicketSeatingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.seating.InfantTicketSeatingStrategy;
import uk.gov.dwp.uc.pairtest.validation.AccountValidator;
import uk.gov.dwp.uc.pairtest.validation.TicketRequestValidator;

import static org.mockito.Mockito.when;

public class TicketServiceImplTest {
    private TicketServiceImpl ticketService;

    @Mock
    TicketRequestValidator ticketRequestValidator;

    @Mock
    AccountValidator accountValidator;

    @Mock
    TicketStrategyFactory ticketStrategyFactory;

    @Mock
    TicketPaymentService ticketPaymentService;

    @Mock
    SeatReservationService seatReservationService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);

        this.ticketService = new TicketServiceImpl(
                ticketRequestValidator,
                accountValidator,
                ticketStrategyFactory,
                ticketPaymentService,
                seatReservationService
        );
    }

    @Test
    void singleTicketPurchaseWillBeProcessed() {
        this.setStrategyMocks();

        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);

        this.ticketService.purchaseTickets(1L, adultRequest);
    }

    private void setStrategyMocks() {
        when(ticketStrategyFactory.getTicketPricingStrategy(TicketTypeRequest.Type.INFANT)).thenReturn(new InfantTicketPricingStrategy());
        when(ticketStrategyFactory.getTicketSeatingStrategy(TicketTypeRequest.Type.INFANT)).thenReturn(new InfantTicketSeatingStrategy());

        when(ticketStrategyFactory.getTicketPricingStrategy(TicketTypeRequest.Type.CHILD)).thenReturn(new ChildTicketPricingStrategy());
        when(ticketStrategyFactory.getTicketSeatingStrategy(TicketTypeRequest.Type.CHILD)).thenReturn(new ChildTicketSeatingStrategy());

        when(ticketStrategyFactory.getTicketPricingStrategy(TicketTypeRequest.Type.ADULT)).thenReturn(new AdultTicketPricingStrategy());
        when(ticketStrategyFactory.getTicketSeatingStrategy(TicketTypeRequest.Type.ADULT)).thenReturn(new AdultTicketSeatingStrategy());
    }
}
