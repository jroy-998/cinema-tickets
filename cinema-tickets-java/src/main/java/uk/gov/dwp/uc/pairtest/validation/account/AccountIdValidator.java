package uk.gov.dwp.uc.pairtest.validation.account;

import uk.gov.dwp.uc.pairtest.exception.AccountIdInvalidException;
import uk.gov.dwp.uc.pairtest.exception.ValidationException;
import uk.gov.dwp.uc.pairtest.validation.Validator;

public class AccountIdValidator implements Validator<Long> {

  @Override
  public void validate(Long accountId) throws ValidationException {
    if (accountId < 0) {
      throw new AccountIdInvalidException();
    }
  }
}
