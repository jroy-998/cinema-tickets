package uk.gov.dwp.uc.pairtest.factory;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.strategy.pricing.TicketPricingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.seating.TicketSeatingStrategy;

public interface TicketStrategyFactory {

    TicketPricingStrategy getTicketPricingStrategy(TicketTypeRequest.Type ticketType);

    TicketSeatingStrategy getTicketSeatingStrategy(TicketTypeRequest.Type ticketType);
}
