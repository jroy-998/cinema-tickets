package uk.gov.dwp.uc.pairtest.validation.account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.dwp.uc.pairtest.exception.AccountIdInvalidException;

public class AccountIdValidatorTest {

  private AccountIdValidator accountIdValidator;

  @BeforeEach
  void beforeEach() {
    accountIdValidator = new AccountIdValidator();
  }

  @ParameterizedTest
  @ValueSource(longs = {0L, 1L, 12L, 123L, 54321L})
  void validAccountIdWillPassValidation(Long accountId) {
    assertDoesNotThrow(() -> accountIdValidator.validate(accountId));
  }

  @ParameterizedTest
  @ValueSource(longs = {-1L, -123L, -54321L})
  void invalidAccountIdWillThrowException(Long accountId) {
    AccountIdInvalidException exception =
        assertThrows(AccountIdInvalidException.class, () -> accountIdValidator.validate(accountId));
    assertEquals(
        String.format("Validation error: Account ID %s is invalid", accountId),
        exception.getMessage());
  }
}
