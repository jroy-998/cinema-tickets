package uk.gov.dwp.uc.pairtest.validation.ticket;

import java.util.List;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidTicketCombinationException;
import uk.gov.dwp.uc.pairtest.exception.ValidationException;
import uk.gov.dwp.uc.pairtest.validation.Validator;

public class TicketCombinationValidator implements Validator<List<TicketTypeRequest>> {

  @Override
  public void validate(List<TicketTypeRequest> requests) throws ValidationException {
    if ((getNumberOfTicketsPerType(requests, TicketTypeRequest.Type.INFANT) > 0
            || getNumberOfTicketsPerType(requests, TicketTypeRequest.Type.CHILD) > 0)
        && getNumberOfTicketsPerType(requests, TicketTypeRequest.Type.ADULT) <= 0) {
      throw new InvalidTicketCombinationException(
          "Ticket combination is invalid. Unable to purchase child/infant ticket without an adult ticket");
    }
  }

  private long getNumberOfTicketsPerType(
      List<TicketTypeRequest> requests, TicketTypeRequest.Type type) {
    return requests.stream().filter(request -> request.getTicketType().equals(type)).count();
  }
}
