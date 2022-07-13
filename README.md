# Hooked App Backend

Fishing application built with a Spring Boot backend, React frontend and a MySql database. Intended to be a platform for fishing entusiasts to share stories or pictures of their catch of the day. Users can login and connect with people who love to do what they do.

# How to run locally

```bash
  git clone https://github.com/RLuong87/Hobbyist-App.git
```

The frontend to this project is located here, https://github.com/RLuong87/Hobbyist-React-App

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

JAVA Spring Boot

Application.properties

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

Note: All of these configurations will have to be added to application.properties located inside of the resources folder

# API

Sign Up

```bash
  POST /api/auth/signup
```




tables:
| header1 | header2 | header3 |
| :------ | :-----: | ------: |
| left aligned | `centered` | *italic right*|
| small | __43__ | **super** |

* step 1
* step 2 
  * step 2a
  * step 2b
* step 3

1. numbers
1. list
1. numbers
  1. hello 

* Title and discription.  
* Stack -> list of technology and packages used
* why this was made  
* How to run locally
* how to deploy
* img of it running
* story about the project
