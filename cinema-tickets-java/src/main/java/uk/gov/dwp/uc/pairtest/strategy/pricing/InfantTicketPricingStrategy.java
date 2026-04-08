package uk.gov.dwp.uc.pairtest.strategy.pricing;

public class InfantTicketPricingStrategy implements TicketPricingStrategy {

  private static final int PRICE_PER_TICKET = 0;

  @Override
  public int calculatePrice(int numberOfTickets) {
    return numberOfTickets * PRICE_PER_TICKET;
  }
}
