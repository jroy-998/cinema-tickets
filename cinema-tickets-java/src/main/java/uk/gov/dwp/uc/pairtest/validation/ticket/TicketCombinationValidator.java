package uk.gov.dwp.uc.pairtest.validation.ticket;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidTicketCombinationException;
import uk.gov.dwp.uc.pairtest.exception.ValidationException;
import uk.gov.dwp.uc.pairtest.validation.Validator;

import java.util.List;

public class TicketCombinationValidator implements Validator<List<TicketTypeRequest>> {

    @Override
    public void validate(List<TicketTypeRequest> requests) throws ValidationException {
        if ((getNumberOfTicketsPerType(requests, TicketTypeRequest.Type.INFANT) > 0
        || getNumberOfTicketsPerType(requests, TicketTypeRequest.Type.CHILD) > 0)
            && getNumberOfTicketsPerType(requests, TicketTypeRequest.Type.ADULT) <=0) {
            throw new InvalidTicketCombinationException();
        }
    }

    private long getNumberOfTicketsPerType(List<TicketTypeRequest> requests, TicketTypeRequest.Type type) {
        return requests
                .stream()
                .filter(request -> request.getTicketType().equals(type))
                .count();
    }
}
