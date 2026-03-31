package uk.gov.dwp.uc.pairtest.calculator;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Map;

public class DefaultPriceCalculator implements PriceCalculator {

    private static final Map<TicketTypeRequest.Type, Integer> TICKET_PRICES = Map.of(
            TicketTypeRequest.Type.INFANT, 0,
            TicketTypeRequest.Type.CHILD, 15,
            TicketTypeRequest.Type.ADULT, 25
    );

    public Integer calculateTotalPrice(TicketTypeRequest request)
    {
        return request.getNoOfTickets() * TICKET_PRICES.get(request.getTicketType());
    }
}
