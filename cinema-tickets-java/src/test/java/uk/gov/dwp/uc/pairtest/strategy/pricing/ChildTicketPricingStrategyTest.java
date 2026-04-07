package uk.gov.dwp.uc.pairtest.strategy.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChildTicketPricingStrategyTest {

    private ChildTicketPricingStrategy childTicketPricingStrategy;

    @BeforeEach
    void beforeEach() {
        childTicketPricingStrategy = new ChildTicketPricingStrategy();
    }

    @ParameterizedTest
    @CsvSource({ "0, 0", "1, 15", "2, 30", "10, 150" })
    void childTicketPriceWillBeCalculated(int numberOfTickets, int expectedPrice) {
        assertEquals(expectedPrice, childTicketPricingStrategy.calculatePrice(numberOfTickets));
    }
}
