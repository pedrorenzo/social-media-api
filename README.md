# Social Media API
A simple social media API. I created this API to learn a little more about HATEOAS, Richardson Maturity Model and Restful Web Services.

For the construction of this API, I used Java 8, Spring Boot, Spring MVC, Spring Boot Actuator, Swagger and Maven.
I also implemented exception handling and HATEOAS (Hypermedia as the Engine of Application State) at a more basic level, where our API for obtaining user by id returns the link for obtaining all users.

For those who have not yet had contact with HATEOAS, here is an interesting definition:
When HATEOAS is implemented, the API starts to provide links that will tell users how to navigate through its resources.
With that, the user does not need to have a deep knowledge of the API, just know the initial URL and from the links provided, he can access all resources in a circular way, guiding himself through the requests made.

Taking into consideration the Richardson Maturity Model, our API can be considered Level 2.
For those who have not yet had contact with Richardson Maturity Model:
- Level 0:

Expose SOAP web services in REST Style. Example:

[http://server/getUsers](http://server/getUsers)
 or [http://server/doThis](http://server/doThis)

- Level 1:

Expose resources with proper URI. Example:

[http://server/users](http://server/users) or
[http://server/users/1](http://server/users/1)

Note: Improper use of HTTP methods

- Level 2:

Level 1 + Proper use of HTTP methods

- Level 3:

Level 2 + HATEOAS (Return the data + next possible actions)