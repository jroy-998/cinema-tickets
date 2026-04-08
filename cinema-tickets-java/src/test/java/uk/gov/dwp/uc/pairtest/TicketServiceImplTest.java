package uk.gov.dwp.uc.pairtest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.AccountIdInvalidException;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.exception.InvalidTicketCombinationException;
import uk.gov.dwp.uc.pairtest.factory.ticket.TicketStrategyFactory;
import uk.gov.dwp.uc.pairtest.factory.validation.ValidationFactory;
import uk.gov.dwp.uc.pairtest.strategy.pricing.AdultTicketPricingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.pricing.ChildTicketPricingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.seating.AdultTicketSeatingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.seating.ChildTicketSeatingStrategy;
import uk.gov.dwp.uc.pairtest.validation.Validator;

public class TicketServiceImplTest {
  private TicketServiceImpl ticketService;

  @Mock ValidationFactory validationFactory;

  @Mock Validator<Long> accountValidator;

  @Mock Validator<List<TicketTypeRequest>> ticketValidator;

  @Mock TicketStrategyFactory ticketStrategyFactory;

  @Mock AdultTicketPricingStrategy adultTicketPricingStrategy;

  @Mock ChildTicketPricingStrategy childTicketPricingStrategy;

  @Mock AdultTicketSeatingStrategy adultTicketSeatingStrategy;

  @Mock ChildTicketSeatingStrategy childTicketSeatingStrategy;

  @Mock TicketPaymentService ticketPaymentService;

  @Mock SeatReservationService seatReservationService;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);

    this.ticketService =
        new TicketServiceImpl(
            validationFactory, ticketStrategyFactory, ticketPaymentService, seatReservationService);
  }

  @Test
  void singleTicketPurchaseWillBeProcessed() {
    when(validationFactory.accountValidator()).thenReturn(accountValidator);
    when(validationFactory.ticketValidator()).thenReturn(ticketValidator);

    when(adultTicketPricingStrategy.calculatePrice(1)).thenReturn(25);
    when(ticketStrategyFactory.getTicketPricingStrategy(any()))
        .thenReturn(adultTicketPricingStrategy);

    when(adultTicketSeatingStrategy.calculateNumberOfSeats(1)).thenReturn(1);
    when(ticketStrategyFactory.getTicketSeatingStrategy(TicketTypeRequest.Type.ADULT))
        .thenReturn(adultTicketSeatingStrategy);

    TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);

    assertDoesNotThrow(() -> this.ticketService.purchaseTickets(1L, adultRequest));

    verify(accountValidator, times(1)).validate(1L);
    verify(ticketValidator, times(1)).validate(List.of(adultRequest));
    verify(adultTicketPricingStrategy, times(1)).calculatePrice(1);
    verify(adultTicketSeatingStrategy, times(1)).calculateNumberOfSeats(1);
  }

  @Test
  void multipleTicketPurchaseWillBeProcessed() {
    when(validationFactory.accountValidator()).thenReturn(accountValidator);
    when(validationFactory.ticketValidator()).thenReturn(ticketValidator);

    when(adultTicketPricingStrategy.calculatePrice(2)).thenReturn(50);
    when(ticketStrategyFactory.getTicketPricingStrategy(TicketTypeRequest.Type.ADULT))
        .thenReturn(adultTicketPricingStrategy);

    when(childTicketPricingStrategy.calculatePrice(1)).thenReturn(15);
    when(ticketStrategyFactory.getTicketPricingStrategy(TicketTypeRequest.Type.CHILD))
        .thenReturn(childTicketPricingStrategy);

    when(adultTicketSeatingStrategy.calculateNumberOfSeats(2)).thenReturn(2);
    when(ticketStrategyFactory.getTicketSeatingStrategy(TicketTypeRequest.Type.ADULT))
        .thenReturn(adultTicketSeatingStrategy);

    when(childTicketSeatingStrategy.calculateNumberOfSeats(1)).thenReturn(1);
    when(ticketStrategyFactory.getTicketSeatingStrategy(TicketTypeRequest.Type.CHILD))
        .thenReturn(childTicketSeatingStrategy);

    TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);
    TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1);

    assertDoesNotThrow(() -> this.ticketService.purchaseTickets(1L, adultRequest, childRequest));

    verify(accountValidator, times(1)).validate(1L);
    verify(ticketValidator, times(1)).validate(List.of(adultRequest, childRequest));
    verify(adultTicketPricingStrategy, times(1)).calculatePrice(2);
    verify(childTicketPricingStrategy, times(1)).calculatePrice(1);
    verify(adultTicketSeatingStrategy, times(1)).calculateNumberOfSeats(2);
    verify(childTicketSeatingStrategy, times(1)).calculateNumberOfSeats(1);
  }

  @Test
  void invalidAccount_willThrowException() {
    when(validationFactory.accountValidator()).thenReturn(accountValidator);
    when(validationFactory.ticketValidator()).thenReturn(ticketValidator);

    doThrow(new AccountIdInvalidException()).when(accountValidator).validate(-1L);

    TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);

    assertThrows(
        InvalidPurchaseException.class, () -> this.ticketService.purchaseTickets(-1L, request));
  }

  @Test
  void invalidTicketRequest_willThrowException() {
    when(validationFactory.accountValidator()).thenReturn(accountValidator);
    when(validationFactory.ticketValidator()).thenReturn(ticketValidator);

    TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1);

    doThrow(new InvalidTicketCombinationException())
        .when(ticketValidator)
        .validate(List.of(request));

    assertThrows(
        InvalidPurchaseException.class, () -> this.ticketService.purchaseTickets(1L, request));
  }
}
