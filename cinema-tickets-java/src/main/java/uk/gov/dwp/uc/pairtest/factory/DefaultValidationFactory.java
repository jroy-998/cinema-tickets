package uk.gov.dwp.uc.pairtest.factory;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.validation.CompositeValidator;
import uk.gov.dwp.uc.pairtest.validation.Validator;
import uk.gov.dwp.uc.pairtest.validation.account.AccountIdValidator;
import uk.gov.dwp.uc.pairtest.validation.ticket.TicketCombinationValidator;
import uk.gov.dwp.uc.pairtest.validation.ticket.TotalTicketsValidator;

import java.util.List;

public class DefaultValidationFactory implements ValidationFactory {

    @Override
    public Validator<List<TicketTypeRequest>> ticketValidator() {
        return new CompositeValidator<>(List.of(
                new TotalTicketsValidator(),
                new TicketCombinationValidator()
        ));
    }

    @Override
    public Validator<Long> accountValidator() {
        return new CompositeValidator<>(List.of(
                new AccountIdValidator()
        ));
    }
}
