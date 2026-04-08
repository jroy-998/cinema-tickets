package uk.gov.dwp.uc.pairtest.validation;

import uk.gov.dwp.uc.pairtest.exception.ValidationException;

public interface Validator<T> {

  void validate(T input) throws ValidationException;
}
