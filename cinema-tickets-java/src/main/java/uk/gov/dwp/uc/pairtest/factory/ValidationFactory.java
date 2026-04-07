package uk.gov.dwp.uc.pairtest.factory;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.validation.Validator;

import java.util.List;

public interface ValidationFactory {

    Validator<List<TicketTypeRequest>> ticketValidator();

    Validator<Long> accountValidator();
}
