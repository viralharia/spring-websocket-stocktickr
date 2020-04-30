# StockTickr App

A Spring Boot application that demonstrates using WebSockets using STOMP protocol to stream stock prices

## Running application locally
```
git clone https://github.com/viralharia/spring-websocket-stocktickr.git
cd spring-websocket-stocktickr
mvnw spring-boot:run

hit - http://localhost:8080/index.html
```

## Screenshot of running application
* First click on 'Connect' button to establish the socket connection.
* Then add couple of Tickr names like - IBM, MSFT, AMZN, GOOG...

![Screenshot of running application](https://github.com/viralharia/spring-websocket-stocktickr/blob/master/screeshots/screenshot_1.PNG)

#### Few debug messages are available under browser's console
![browser's console](https://github.com/viralharia/spring-websocket-stocktickr/blob/master/screeshots/screenshot_2.PNG)