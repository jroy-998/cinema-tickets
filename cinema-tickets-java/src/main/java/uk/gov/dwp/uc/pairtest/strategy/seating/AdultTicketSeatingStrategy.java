package uk.gov.dwp.uc.pairtest.strategy.seating;

public class AdultTicketSeatingStrategy implements TicketSeatingStrategy {

    @Override
    public int calculateNumberOfSeats(int numberOfTickets) {
        return numberOfTickets;
    }
}
