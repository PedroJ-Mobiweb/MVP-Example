# What are they?
Patterns are created to solve problems that concurrently occur, thus allowing developers to follow a structure and ultimately improve the code comprehensibility, provide better organization and scalability. It is also great to use a well-known pattern as that helps others, who pick up your project, later on, to understand what is going on.

## MVP Representation
![MVP](https://cdn.journaldev.com/wp-content/uploads/2017/08/android-mvp-flow.png)

MVP separates your code in three main layers which allow you to have different abstraction layers for different responsibilities


Model holds the business logic and communicates with the data source.


View is the interface that your activity or fragment class will implement. It contains methods for view actions, show progress bars, etc.


Presenter acts as a communicator between the View and the Model. It will handle view actions and make requests to the model. As soon as the model completes the buisness logic it will send a response to the presenter and call any of the view methods to present the answer to the user.
