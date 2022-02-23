## Assumptions made

1. There's only one game active for one player
2. One player loses all money they can't start a new game
3. Playing for Cash and Playing for Free add to the same balance
4. Playing for Free doesn't consume free round that user might have won
5. Player has an equal chance of winning small, medium and big.

Domain model was designed to quickly adapt if these assumptions where to change.


## Design

Modeling and designing this app was done using Domain Driven Design. Initial Event Storming Session can be viewed here
https://postimg.cc/zyGXPtnm. The focus while creating the solution was on extensibility, testability and readability, not on correct infrastructure. Application
was realised using hexagonal/clean architecture.

Domain logic is contained within 2 Aggregates (Round and GameState) and a set of Policies. Testing is realised through unit tests of the domain logic. See tests
in groovy/com/janchabik/gameservice/domain

Extensibility is realised through set of GameConfigurationProvider (to change default values for startingCash etc.) and set of Policies that can be added,
removed, changed rather easily since domain logic depends only on their interfaces. Fe. OutComeCalculationPolicy is provisioned through a factory, which in 
this case is an overkill, but the purpose was to showcase possible extensibility ie. providing different policies based on external factors.

Readability and reflecting domain over writing less code. For example PlayingForFreeBetDeductionPolicy and PlayingForFreeBetDeductionPolicy do the same thing
yet they're different from domain perspective hence separate classes.

## Known limitations

DefaultOutComeCalculationPolicy could be made into a generic component that takes betWinningCalculation strategies and their respective percentages.

Repository layer could be better, but the focus wasn't on that.

Better auditng could be done - like adding aggregateVersion to GameState aggregate, timestamps and Policynames for RoundEvents.

Integration tests (with SpringContext) could've been done to test domain + infrastructure + api. Since domain logic is tested extensively I didn't feel the need
to add additional tests. Also http requests in resources/http/requests.http could be made into a test suite running during CI.

Better api docs could be added OpenAPI/Swagger but I was too lazy to do it. resources/http/requests.http should be enough for most people

## Building and running the project

./gradlew build

./gradlew bootRun

## Testing the API
resources/http/requests.http contains sample requests to test the API.


##### Author @Jan Chabik
