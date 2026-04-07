package uk.gov.dwp.uc.pairtest.strategy.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdultTicketPricingStrategyTest {

    private AdultTicketPricingStrategy adultTicketPricingStrategy;

    @BeforeEach
    void beforeEach() {
        adultTicketPricingStrategy = new AdultTicketPricingStrategy();
    }

    @ParameterizedTest
    @CsvSource({ "0, 0", "1, 25", "2, 50", "10, 250" })
    void adultTicketPriceWillBeCalculated(int numberOfTickets, int expectedPrice) {
        assertEquals(expectedPrice, adultTicketPricingStrategy.calculatePrice(numberOfTickets));
    }
}
