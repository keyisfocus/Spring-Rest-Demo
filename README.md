# Spring-Rest-Demo

REST service operating on one simple db model User,
with three endpoints for **creating** new users,
**retrieving** user info and **updating** user status.

With a task scheduler to automatically change user status
to `AWAY` after 5 minutes of last status update.

And a simple Exception handling mechanism that returns
HTTP responses with meaningful messages.

Technologies used:
* Java 16
* Spring Boot 2 (Web, JPA, Validation) with Maven
* Postgresql 13
* Lombok


