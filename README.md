
# AuthApp
### Introduction
This project is an authentication application and it was built with Spring Boot and ReactJS. The application contains four user and they have differents roles (you can find these in the *Credentials* section). 

There are three different roles: 
- *Editor*: has access for the Editor page
- *User*: has access for the User page
- *Administrator*: has access for the Administrator and all of the other pages

If somebody has multiple roles then he/she has access for multiple pages. After the first three login attemption you will have to fill a captcha and verify you are not a robot. Then after a succesful login you will redirect to the main page that contains your username, roles and the last time when you logged in and the header will show you the accessible subpages.

### Preview
<img src="imgs/01_login.png" width="400"> <img src="imgs/02_home.png" width="400"> 
## Getting Started

### Set up database and schema access
The application uses a MySQL database, that you have to set up. You can use either **MySQL Workbench**:

 1. The root password has to be: *root*
 2. Create the schema wich has to be: *auth-app*
 3. After `mvn install` (in the *Installing and running the backend* section) you have to `drop` the schema and `create` it again or just `drop` all the tables one by one 

or **Docker**:

 1. `docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:8`
 2. Wait at least 30 seconds
 3. `docker exec mysql mysql -h127.0.0.1 -uroot -proot -e "CREATE SCHEMA auth-app;"`
 4. In the path *backend/src/main/resources* rename the *application.properties* (e.g. to *application.propertiesWORKBENCH*), then rename *application.propertiesDOCKER* to *application.properties*
 5. After `mvn install` (in the *Installing and running the backend* section) drop the schema: `docker exec mysql mysql -h127.0.0.1 -uroot -proot -e "DROP SCHEMA auth-app;"`
 6. Then create the schema agin: `docker exec mysql mysql -h127.0.0.1 -uroot -proot -e "CREATE SCHEMA auth-app;"`

### Installing and running the backend
Go to the *backend* folder, then:

```
mvn install
```

> `Drop` the schema and `create` it again or just `drop` all the tables one by one as it mentioned in *Set up database and schema access* section.

Then

```
mvn spring-boot:run
```

### Installing and running the frontend

Go to the *frontend* folder, then:
```
npm install
```

Then

```
npm start
```

### Credentials
The text you have to use is **inside** the square bracket [].
|Username  |Password  |Role(s)        |
|:--------:|:--------:|:-------------:|
|[Admin]   |[admin]   |Administrator  |
|[User 1]  |[user1]   |Editor, User   |
|[User 2]  |[user2]   |Editor         |
|[User 3]  |[user3]   |User           |

## Built With
* [Spring Boot](https://spring.io/projects/spring-boot) 
* [React](https://reactjs.org/) 
## Author

 **Stella Tóth-Baranyi**
