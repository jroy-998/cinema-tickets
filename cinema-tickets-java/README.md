# Cinema Tickets Service

## Implementation
Have implemented the Service layer under the assumption that this will be called by a Presentation layer in the future.

Service makes use of the Factory pattern to obtain various dependencies which are used to validate and process requests.

These Factories are injected into the Service so that they can in theory be swapped out easily if required.

Validation makes use of a Composite validator so that multiple different validation classes can be plugged in as required for each type of validation.

Business logic utilises the Strategy and Factory patterns to define logic based on the type of ticket being purchased. This factory s again injected int he service so can be easily swapped if needed.

## Testing
Service and associated classes have unit tests written against them. These can be executed with the following command:
```
mvn test
```

## Future Considerations
If progressing this application, the following should be considered:
- Integrating with a Web Framework to serve content via APIs (such as Spring Boot served as a REST API)
- Introducing Integration Tests (with the possible addition of Gherkin if utilising BDD)
- Introducing Coding standards checks as part of build
- Implementing a logging strategy and adding log messages at appropriate places