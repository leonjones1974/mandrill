# Credit Suisse - Mandrill Test

## To Build

mvn clean install 

## Assumptions

* The requirement does not mention execution, therefore the live order board is considered immutable
* Whether to test the correct initialization of fields in the model is a subject of ongoing debate.  I opted to here
personally because there was little else in the model to test.  Whether to test this kind of thing tends to depend
on team preference, so I'd expect to be flexible