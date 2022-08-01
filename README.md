# Hooked App Backend

Fishing application built with a Spring Boot backend, React frontend and a MySql database. Intended to be a platform for fishing entusiasts to share stories or pictures of their catch of the day. Users can login and connect with people who love to do what they do.

# What is needed to run this project?
* IDE's for example VsCode, IntelliJ
* Postman if only running the backend
* Java jdk, Node.js, React 
* MySql

### The frontend to this project is located here, https://github.com/RLuong87/Hobbyist-React-App

# How to run locally

```bash
  git clone https://github.com/RLuong87/Hobbyist-App.git
```

# You will need MySql downloaded and installed

Create Database

```bash
  create database <database_name>;
```

Create user

```bash
  create user '<username>'@'%" identified by <password>;
```

Grant permissions
```bash
  grant all on <database_name>.* to '<username>'@'%';
```

## JAVA Spring Boot

### Application.properties

Spring configurations

```bash
  spring.jpa.hibernate.ddl-auto=update
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

MySql Configs

```bash
  spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
  spring.datasource.username=<username>
  spring.datasource.password=<password>
```

JWT Configs

```bash
  hooked.app.jwtSecret=<secret_key>
```
 _____
|Note:| All of these configurations will have to be added to application.properties located inside of the resources folder
 _____
# API

Sign Up

```bash
  POST /api/auth/signup
```

Sign In

```bash
  POST /api/auth/signin
```




