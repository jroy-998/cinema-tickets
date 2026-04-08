package uk.gov.dwp.uc.pairtest.strategy.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class InfantTicketPricingStrategyTest {

  private InfantTicketPricingStrategy infantTicketPricingStrategy;

  @BeforeEach
  void beforeEach() {
    infantTicketPricingStrategy = new InfantTicketPricingStrategy();
  }

  @ParameterizedTest
  @CsvSource({"0, 0", "1, 0", "2, 0", "10, 0"})
  void infantTicketPriceWillBeCalculated(int numberOfTickets, int expectedPrice) {
    assertEquals(expectedPrice, infantTicketPricingStrategy.calculatePrice(numberOfTickets));
  }
}
