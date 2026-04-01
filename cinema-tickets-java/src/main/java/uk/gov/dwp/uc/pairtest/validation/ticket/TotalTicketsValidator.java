package uk.gov.dwp.uc.pairtest.validation.ticket;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.MaximumTicketsExceededException;
import uk.gov.dwp.uc.pairtest.exception.ValidationException;
import uk.gov.dwp.uc.pairtest.validation.Validator;

import java.util.List;

public class TotalTicketsValidator implements Validator<List<TicketTypeRequest>> {

    @Override
    public void validate(List<TicketTypeRequest> ticketRequests) throws ValidationException {
        int totalTickets = ticketRequests
                .stream()
                .mapToInt(TicketTypeRequest::getNoOfTickets)
                .sum();

        if (totalTickets > 25) {
            throw new MaximumTicketsExceededException();
        }
    }
}
