package uk.gov.dwp.uc.pairtest.strategy.validation.ticket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidTicketCombinationException;
import uk.gov.dwp.uc.pairtest.validation.ticket.TicketCombinationValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketCombinationValidatorTest {

    private TicketCombinationValidator ticketCombinationValidator;

    @BeforeEach
    void beforeEach() {
        ticketCombinationValidator = new TicketCombinationValidator();
    }

    @ParameterizedTest
    @MethodSource("validTicketCombinationsDataProvider")
    void validRequestWillPassValidation(List<TicketTypeRequest> ticketTypeRequests) {
        assertDoesNotThrow(() -> ticketCombinationValidator.validate(ticketTypeRequests));
    }

    @ParameterizedTest
    @MethodSource("invalidTicketCombinationDataProvider")
    void invalidRequestWillFailValidation(List<TicketTypeRequest> ticketTypeRequests) {
        assertThrows(InvalidTicketCombinationException.class, () -> ticketCombinationValidator.validate(ticketTypeRequests));
    }

    private static List<List<TicketTypeRequest>> validTicketCombinationsDataProvider() {
        return List.of(
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2)
                ),
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2),
                        new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 2)
                ),
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1),
                        new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
                ),
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2),
                        new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 2),
                        new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1)
                )
        );
    }

    private static List<List<TicketTypeRequest>> invalidTicketCombinationDataProvider() {
        return List.of(
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1)
                ),
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2)
                ),
                List.of(
                        new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 2),
                        new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2)
                )
        );
    }
}
