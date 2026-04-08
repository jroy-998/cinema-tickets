package uk.gov.dwp.uc.pairtest.factory;

import java.util.List;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.validation.Validator;

public interface ValidationFactory {

  Validator<List<TicketTypeRequest>> ticketValidator();

  Validator<Long> accountValidator();
}
