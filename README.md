See below for the requirements that drove this kata.

This kata will return a boolean value for a product that answers if the product qualifies for a red pencil promotion.
Because I focused the scope of the kata on implementing the rules for activating and ending
red pencil promotions, I chose to use a list as a fake product database and added a simple controller for trying it out.

To try it out, build and run the RedPencil app and go to http://localhost:8080/redpencil?id=prod1
The fake db in fauxProductRepository.java has several products set up in different scenarios from prod1 to prod6
that will return a boolean for whether the product qualifies for a red pencil.

There is of course plenty of room for improvement:
- return a product object instead of a boolean
- extract time and percentage properties to an application.properties file.
- ...

Red Pencil Code Kata
--------------------
Goal: Support red pencil promotions for reduced prices on items in an online store.
The scope of the kata is the implementation of the rules for activating and ending red pencil promotions.

A red pencil promotion starts due to a price reduction. The price has to be reduced by at least 5% but at most bei 30% and the previous price had to be stable for at least 30 days.
A red pencil promotion lasts 30 days as the maximum length.
If the price is further reduced during the red pencil promotion the promotion will not be prolonged by that reduction.
If the price is increased during the red pencil promotion the promotion will be ended immediately.
If the price if reduced during the red pencil promotion so that the overall reduction is more than 30% with regard to the original price, the promotion is ended immediately.
After a red pencil promotion has ended, additional red pencil promotions may follow – as long as the start condition is valid: the price was stable for 30 days and these 30 days don’t intersect with a previous red pencil promotion.
During the red pencil promotion the old price is crossed out in red and the new reduced price is written next to it.
To avoid misuse of red pencil promotions the red pencil promotions are activated and deactivated automatically.