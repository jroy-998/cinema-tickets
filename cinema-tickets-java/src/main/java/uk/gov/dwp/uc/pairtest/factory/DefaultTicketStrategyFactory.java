package uk.gov.dwp.uc.pairtest.factory;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.strategy.pricing.AdultTicketPricingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.pricing.ChildTicketPricingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.pricing.InfantTicketPricingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.pricing.TicketPricingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.seating.AdultTicketSeatingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.seating.ChildTicketSeatingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.seating.InfantTicketSeatingStrategy;
import uk.gov.dwp.uc.pairtest.strategy.seating.TicketSeatingStrategy;

public class DefaultTicketStrategyFactory implements TicketStrategyFactory {

  @Override
  public TicketPricingStrategy getTicketPricingStrategy(TicketTypeRequest.Type ticketType) {
    return switch (ticketType) {
      case INFANT -> new InfantTicketPricingStrategy();
      case CHILD -> new ChildTicketPricingStrategy();
      case ADULT -> new AdultTicketPricingStrategy();
    };
  }

  @Override
  public TicketSeatingStrategy getTicketSeatingStrategy(TicketTypeRequest.Type ticketType) {
    // TODO Add default?
    return switch (ticketType) {
      case INFANT -> new InfantTicketSeatingStrategy();
      case CHILD -> new ChildTicketSeatingStrategy();
      case ADULT -> new AdultTicketSeatingStrategy();
    };
  }
}
