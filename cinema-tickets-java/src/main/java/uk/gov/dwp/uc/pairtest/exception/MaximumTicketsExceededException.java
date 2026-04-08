package uk.gov.dwp.uc.pairtest.exception;

public class MaximumTicketsExceededException extends ValidationException {

  public MaximumTicketsExceededException(String message) {
    super(message);
  }
}
