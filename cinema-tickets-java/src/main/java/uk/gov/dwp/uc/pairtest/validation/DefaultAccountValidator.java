package uk.gov.dwp.uc.pairtest.validation;

import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class DefaultAccountValidator implements AccountValidator {


    @Override
    public void validateAccount(Long accountId) {
        if (accountId < 0) {
            //TODO: different exception required
            throw new InvalidPurchaseException();
        }
    }
}
