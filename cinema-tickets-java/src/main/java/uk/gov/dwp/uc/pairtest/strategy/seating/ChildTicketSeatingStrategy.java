package uk.gov.dwp.uc.pairtest.strategy.seating;

public class ChildTicketSeatingStrategy implements TicketSeatingStrategy {

    @Override
    public int calculateNumberOfSeats(int numberOfTickets) {
        return numberOfTickets;
    }
}
