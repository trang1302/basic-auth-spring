# Spring Basic Authentication

This is a simple REST API built with Spring Boot that utilizes Basic Authentication and User Roles

# Required Tech
```
Java 11, Maven, Postman (or another API client), IDE of your choice
```
# Setup & Launch
```

 - Download and compile the project with Maven using the provided 
   pom.xml
 - Run SpringBasicAuthApplication.java 

```

# Using REST API
```
There are two users with different roles: car_buyer and car_seller

car_buyer can consume the API with a GET request at /api

car_seller can consume and POST a new car (see POSTing to API below)

```

## Authenticating as car_buyer

```
Navigate to localhost:8080/api using Postman or your preferred API client and enter the
car_buyer credentials in when prompted:

username: car_buyer
password: buy_today

![Screenshot of authentication in Postman and API response](/img/screen_shot_1.png?raw=true)


# Got Questions? Contact me!
efim@shulginmusic.com
