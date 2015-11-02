# Credit Suisse - Mandrill Test

## To Build

mvn clean install 

## Assumptions

* The requirement does not mention execution, therefore the live order board is considered immutable
* That the LiveOrderBoard is non deterministic in terms of ordering
* That the LiveOrderBoard interface is our own client implementation to the service and that
its therefore reasonable to share common classes, i.e. Ccy, Direction, Price
* The LiveOrderBoard can be called in a blocking RPC style.  In reality this may well be invoked asynchronously, but
it felt like premature optimisation given the context of this exercise

## Implementation Considerations

* Whether to test the correct initialization of fields in the model is a subject of ongoing debate.  I opted to here but
  could have been easily convinced not to
  
* Other considerations of significance are documented as comments in the code