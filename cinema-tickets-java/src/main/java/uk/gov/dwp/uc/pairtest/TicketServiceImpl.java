package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.factory.TicketStrategyFactory;
import uk.gov.dwp.uc.pairtest.strategy.pricing.TicketPricingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.seating.TicketSeatingStrategy;
import uk.gov.dwp.uc.pairtest.validation.AccountValidator;
import uk.gov.dwp.uc.pairtest.validation.TicketRequestValidator;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    private final TicketRequestValidator ticketRequestValidator;
    private final AccountValidator accountValidator;
    private final TicketStrategyFactory ticketStrategyFactory;
    private final TicketPaymentService ticketPaymentService;
    private final SeatReservationService seatReservationService;

    public TicketServiceImpl(
            TicketRequestValidator ticketRequestValidator,
            AccountValidator accountValidator,
            TicketStrategyFactory ticketStrategyFactory,
            TicketPaymentService ticketPaymentService,
            SeatReservationService seatReservationService) {
        this.ticketRequestValidator = ticketRequestValidator;
        this.accountValidator = accountValidator;
        this.ticketStrategyFactory = ticketStrategyFactory;
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
                .mapToInt(request -> {
                    TicketPricingStrategy pricingStrategy = this.ticketStrategyFactory.getTicketPricingStrategy(request.getTicketType());
                    return pricingStrategy.calculatePrice(request.getNoOfTickets());
                })
                .sum();

        int totalNumberOfSeats = requests
                .stream()
                .mapToInt(request -> {
                    TicketSeatingStrategy seatingStrategy = this.ticketStrategyFactory.getTicketSeatingStrategy(request.getTicketType());
                    return seatingStrategy.calculateNumberOfSeats(request.getNoOfTickets());
                })
                .sum();

        this.ticketPaymentService.makePayment(accountId, totalPrice);
        this.seatReservationService.reserveSeat(accountId, totalNumberOfSeats);
    }

}
