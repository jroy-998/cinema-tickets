package uk.gov.dwp.uc.pairtest.strategy.validation.ticket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.MaximumTicketsExceededException;
import uk.gov.dwp.uc.pairtest.validation.ticket.TotalTicketsValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TotalTicketsValidatorTest {

    private TotalTicketsValidator totalTicketsValidator;

    @BeforeEach
    void beforeEach() {
        totalTicketsValidator = new TotalTicketsValidator();
    }

    @ParameterizedTest
    @MethodSource("validTicketCombinationsDataProvider")
    void validTicketCombinationsWillPassValidation(List<TicketTypeRequest> ticketTypeRequests) {
        assertDoesNotThrow(() -> totalTicketsValidator.validate(ticketTypeRequests));
    }

    @ParameterizedTest
    @MethodSource("invalidTicketCombinationsDataProvider")
    void invalidTicketCombinationsWillFailValidation(List<TicketTypeRequest> ticketTypeRequests) {
        assertThrows(MaximumTicketsExceededException.class, () -> totalTicketsValidator.validate(ticketTypeRequests));
    }

    private static List<List<TicketTypeRequest>> validTicketCombinationsDataProvider() {
        return List.of(
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1)
                ),
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 24)
                ),
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 25)
                ),
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10),
                        new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10),
                        new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
                )
        );
    }

    private static List<List<TicketTypeRequest>> invalidTicketCombinationsDataProvider() {
        return List.of(
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 26)
                ),
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1000)
                ),
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 20),
                        new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10)
                ),
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10),
                        new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10),
                        new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 10)
                )
        );
    }
}
