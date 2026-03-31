package uk.gov.dwp.uc.pairtest.calculator;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public interface PriceCalculator {

    Integer calculateTotalPrice(TicketTypeRequest ticketTypeRequest);
}
