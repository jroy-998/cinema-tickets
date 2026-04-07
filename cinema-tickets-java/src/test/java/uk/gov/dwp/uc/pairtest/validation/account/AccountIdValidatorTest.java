package uk.gov.dwp.uc.pairtest.validation.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.dwp.uc.pairtest.exception.AccountIdInvalidException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountIdValidatorTest {

    private AccountIdValidator accountIdValidator;

    @BeforeEach
    void beforeEach() {
        accountIdValidator = new AccountIdValidator();
    }

    @ParameterizedTest
    @ValueSource (longs = { 0L, 1L, 12L, 123L, 54321L })
    void validAccountIdWillPassValidation(Long accountId) {
        assertDoesNotThrow(() -> accountIdValidator.validate(accountId));
    }

    @ParameterizedTest
    @ValueSource (longs = {-1L, -123L, -54321L})
    void invalidAccountIdWillThrowException(Long accountId) {
        assertThrows(AccountIdInvalidException.class, () -> accountIdValidator.validate(accountId));
    }
}
