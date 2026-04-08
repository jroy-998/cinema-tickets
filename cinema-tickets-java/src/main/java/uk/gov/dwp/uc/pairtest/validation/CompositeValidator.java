package uk.gov.dwp.uc.pairtest.validation;

import java.util.List;
import uk.gov.dwp.uc.pairtest.exception.ValidationException;

public class CompositeValidator<T> implements Validator<T> {

  private final List<Validator<T>> validators;

  public CompositeValidator(List<Validator<T>> validators) {
    this.validators = validators;
  }

  @Override
  public void validate(T input) throws ValidationException {
    for (Validator<T> validator : validators) {
      validator.validate(input);
    }
  }
}
