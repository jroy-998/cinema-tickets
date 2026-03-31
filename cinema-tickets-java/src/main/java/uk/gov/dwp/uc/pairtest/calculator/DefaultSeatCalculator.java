package uk.gov.dwp.uc.pairtest.calculator;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Map;

public class DefaultSeatCalculator implements SeatCalculator {

    public static final Map<TicketTypeRequest.Type, Integer> SEATS_REQUIRED = Map.of(
            TicketTypeRequest.Type.INFANT, 0,
            TicketTypeRequest.Type.CHILD, 1,
            TicketTypeRequest.Type.ADULT, 1
    );

    @Override
    public int calculateNumberOfSeats(TicketTypeRequest request) {
        return request.getNoOfTickets() * SEATS_REQUIRED.get(request.getTicketType());
    }
}
