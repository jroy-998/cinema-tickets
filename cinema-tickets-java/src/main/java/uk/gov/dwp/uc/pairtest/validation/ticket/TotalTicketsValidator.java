package uk.gov.dwp.uc.pairtest.validation.ticket;

import java.util.List;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.MaximumTicketsExceededException;
import uk.gov.dwp.uc.pairtest.exception.ValidationException;
import uk.gov.dwp.uc.pairtest.validation.Validator;

public class TotalTicketsValidator implements Validator<List<TicketTypeRequest>> {

  public static final int MAXIMUM_NUMBER_OF_TICKETS = 25;

  @Override
  public void validate(List<TicketTypeRequest> ticketRequests) throws ValidationException {
    int totalTickets = ticketRequests.stream().mapToInt(TicketTypeRequest::getNoOfTickets).sum();

    if (totalTickets > MAXIMUM_NUMBER_OF_TICKETS) {
      throw new MaximumTicketsExceededException(
          String.format(
              "Number of tickets: %s, exceeds maximum allowed: %s",
              totalTickets, MAXIMUM_NUMBER_OF_TICKETS));
    }
  }
}
