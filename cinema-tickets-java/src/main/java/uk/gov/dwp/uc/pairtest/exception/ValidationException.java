package uk.gov.dwp.uc.pairtest.exception;

public class ValidationException extends RuntimeException {

  public ValidationException(String message) {
    super("Validation error: " + message);
  }
}
