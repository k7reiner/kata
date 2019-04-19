This kata will return a boolean value for a product that answers if the product qualifies for a red pencil promotion.
In focusing on the scope of the Code Kata being to implement the rules for activating and ending
red pencil promotions, I chose to use a list as a fake product database and added a simple controller for trying it out.

To try it out, run the RedPencil app and go to http://localhost:8080/redpencil?id=prod1
The fake db in fauxProductRepository.java has several products set up in different scenarios.

There is of course plenty of room for improvement:
- return an product object instead of a boolean
- extract time and percentage properties to an application.properties file.
- ...