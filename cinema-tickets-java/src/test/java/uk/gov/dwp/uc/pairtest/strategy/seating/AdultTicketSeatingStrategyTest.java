package uk.gov.dwp.uc.pairtest.strategy.seating;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AdultTicketSeatingStrategyTest {

  private AdultTicketSeatingStrategy adultTicketSeatingStrategy;

  @BeforeEach
  void beforeEach() {
    adultTicketSeatingStrategy = new AdultTicketSeatingStrategy();
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 8, 10, 20})
  void adultSeatsWillBeCalculatedCorrectly(int numberOfTickets) {
    assertEquals(
        numberOfTickets, adultTicketSeatingStrategy.calculateNumberOfSeats(numberOfTickets));
  }
}
