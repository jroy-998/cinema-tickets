package uk.gov.dwp.uc.pairtest.strategy.pricing;

public class AdultTicketPricingStrategy implements TicketPricingStrategy {

  private static final int PRICE_PER_TICKET = 25;

  @Override
  public int calculatePrice(int numberOfTickets) {
    return numberOfTickets * PRICE_PER_TICKET;
  }
}
