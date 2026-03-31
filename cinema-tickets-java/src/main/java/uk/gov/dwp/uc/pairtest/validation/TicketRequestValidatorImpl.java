package uk.gov.dwp.uc.pairtest.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import java.util.Arrays;

public class TicketRequestValidatorImpl implements TicketRequestValidator {

    public static final int MAXIMUM_TOTAL_TICKETS = 25;

    @Override
    public void validateTicketRequest(TicketTypeRequest... ticketTypeRequests) {
        this.validateTotalNumberOfTickets(ticketTypeRequests);
    }

    private void validateTotalNumberOfTickets(TicketTypeRequest... ticketTypeRequests) {
        int totalTickets = Arrays.stream(ticketTypeRequests)
                .map(TicketTypeRequest::getNoOfTickets)
                .mapToInt(Integer::intValue)
                .sum();

        if (totalTickets > MAXIMUM_TOTAL_TICKETS) {
            //TODO: Different exceptions?
            throw new InvalidPurchaseException();
        }
    }
}
