package uk.gov.dwp.uc.pairtest.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public interface TicketRequestValidator {

    void validateTicketRequest(TicketTypeRequest... ticketTypeRequests);
}
