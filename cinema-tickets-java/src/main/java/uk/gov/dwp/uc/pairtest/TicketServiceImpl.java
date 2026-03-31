package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.calculator.PriceCalculator;
import uk.gov.dwp.uc.pairtest.calculator.SeatCalculator;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.validation.AccountValidator;
import uk.gov.dwp.uc.pairtest.validation.DefaultAccountValidator;
import uk.gov.dwp.uc.pairtest.validation.TicketRequestValidator;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    private final TicketRequestValidator ticketRequestValidator;
    private final AccountValidator accountValidator;
    private final PriceCalculator priceCalculator;
    private final SeatCalculator seatCalculator;
    private final TicketPaymentService ticketPaymentService;
    private final SeatReservationService seatReservationService;

    public TicketServiceImpl(
            TicketRequestValidator ticketRequestValidator,
            AccountValidator accountValidator,
            PriceCalculator priceCalculator,
            SeatCalculator seatCalculator,
            TicketPaymentService ticketPaymentService,
            SeatReservationService seatReservationService) {
        this.ticketRequestValidator = ticketRequestValidator;
        this.accountValidator = accountValidator;
        this.priceCalculator = priceCalculator;
        this.seatCalculator = seatCalculator;
        this.ticketPaymentService = ticketPaymentService;
        this.seatReservationService = seatReservationService;
    }

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        this.accountValidator.validateAccount(accountId);

        List<TicketTypeRequest> requests = List.of(ticketTypeRequests);

        this.ticketRequestValidator.validateTicketRequest(ticketTypeRequests);

        int totalPrice = requests
                .stream()
                .map(this.priceCalculator::calculateTotalPrice)
                .mapToInt(Integer::intValue)
                .sum();

        int totalNumberOfSeats = requests
                .stream()
                .map(this.seatCalculator::calculateNumberOfSeats)
                .mapToInt(Integer::intValue)
                .sum();

        this.ticketPaymentService.makePayment(accountId, totalPrice);
        this.seatReservationService.reserveSeat(accountId, totalNumberOfSeats);
    }

}
