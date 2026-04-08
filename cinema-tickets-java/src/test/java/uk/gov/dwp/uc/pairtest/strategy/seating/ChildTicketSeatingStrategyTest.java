package uk.gov.dwp.uc.pairtest.strategy.seating;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ChildTicketSeatingStrategyTest {

  private ChildTicketSeatingStrategy childTicketSeatingStrategy;

  @BeforeEach
  void beforeEach() {
    childTicketSeatingStrategy = new ChildTicketSeatingStrategy();
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 8, 10, 20})
  void childSeatsWillBeCalculatedCorrectly(int numberOfTickets) {
    assertEquals(
        numberOfTickets, childTicketSeatingStrategy.calculateNumberOfSeats(numberOfTickets));
  }
}
