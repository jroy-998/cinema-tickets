package uk.gov.dwp.uc.pairtest.strategy.pricing;

public class ChildTicketPricingStrategy implements TicketPricingStrategy {

    private final static int PRICE_PER_TICKET = 15;

    @Override
    public int calculatePrice(int numberOfTickets) {
        return numberOfTickets * PRICE_PER_TICKET;
    }
}
