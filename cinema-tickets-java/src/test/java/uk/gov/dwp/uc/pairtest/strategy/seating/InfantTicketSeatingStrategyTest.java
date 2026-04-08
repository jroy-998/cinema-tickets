package uk.gov.dwp.uc.pairtest.strategy.seating;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InfantTicketSeatingStrategyTest {

  private InfantTicketSeatingStrategy infantTicketSeatingStrategy;

  @BeforeEach
  void beforeEach() {
    infantTicketSeatingStrategy = new InfantTicketSeatingStrategy();
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 8, 10, 20})
  void infantSeatsWillBeCalculatedCorrectly(int numberOfTickets) {
    assertEquals(0, infantTicketSeatingStrategy.calculateNumberOfSeats(numberOfTickets));
  }
}
