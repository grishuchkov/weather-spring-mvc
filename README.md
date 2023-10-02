
<a name="readme-top"></a>
---
### Навигация:
[1. Описание](#descriptiption)  
[2. Технологии](#tech)  
[3. Быстрый старт](#start)   
[4. Взаимодействие с приложением](#use)  
[5. Автор проекта](#author)
---
<a name="tech"></a>
### Технологии:

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/MAVEN-%232E7EEA.svg?style=for-the-badge&logo=maven&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-D70A53?style=for-the-badge&logo=Lombok&logoColor=white)

![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)
![Spring](https://img.shields.io/badge/spring%20MVC-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Spring](https://img.shields.io/badge/spring%20security-%236DB33F.svg?style=for-the-badge&logo=&logoColor=white)

![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![FlyWay](https://img.shields.io/badge/flyway-%23DD0031.svg?style=for-the-badge&logo=flyway&logoColor=white)

![Bootstrap](https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white)


---
<a name="descriptiption"></a>
### Описание:
MVC-Tracker – это простой трекер погоды, базирующийся на Spring-MVC(S) паттерне. 


##### Реализован следующий функционал: 
* Регистрация, аутентификация и авторизация пользователя с помощью Spring Secure.
* Поиск локаций по названию, добавление их к себе.
* Отображение текущего состояния погоды для каждой локации пользователя.

Для получения локаций и погоды для них используется сторонний API: [openweathermap.org](https://openweathermap.org/api)

Базовое ТЗ и идею проекта можно прочитать тут: [ссылка](https://zhukovsd.github.io/java-backend-learning-course/Projects/WeatherViewer/)  
Проект выполнен с расхождением от изначального ТЗ: использован Spring фреймворк.

<p align="right">(<a href="#readme-top">↑ Наверх</a>)</p>

---
<a name="start"></a>
### Быстрый старт:

* Клонируйте репозиторий:  
   `git clone https://github.com/grishuchkov/weather-spring-mvc.git`  

* Для запуска проекта нужно скачать веб-сервер `Apache Tomcat`.  
В проекте используется `javax.servlet`, а не `jakarta.servler`.
Этот нюанс стоит учитывать, при выборе версии `Apache Tomcat`. При разработке использовался `Tomcat 9.0.29`, [скачать с официального сайта](https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.29/bin/apache-tomcat-9.0.29.zip).

* Для конфигурации нужно выполнить следующие шаги: `Edit Configuration` -> `Add New Configuration` -> `Tomcat Server (local)`.  
В открывшемся окне нужно выбрать `Configure`, затем указать `Tomcat base directory`, после чего нажать `fix` -> `war-exploded`.

* Затем нужно заполнить properties-файлы. 
Файл с данными для базы-данных уже сформирован [database.properties](src%2Fmain%2Fresources%2Fdatabase.properties), вам нужно поменять данные на свои, либо создать базу, подходящую под дефолтные поля файла.   
Можно использовать `docker run --name postgres -e POSTGRES_PASSWORD=postgres -p {YOUR-PORT}:5432 -d postgres`

* Затем нужно создать файл на основе [api.properties.origin](src%2Fmain%2Fresources%2Fapi.properties.origin), вставив свой `openweather.token`. 
Получить его можно тут: [openweathermap.org](https://openweathermap.org/api)

* Во время запуска приложение самостоятельно проинициализирует таблицы базы-данных, а также вставит тестовые значения.

<p align="right">(<a href="#readme-top">↑ Наверх</a>)</p>

---
<a name="use"></a>
### Взаимодействие с приложением:

При запуске приложения существует дефолтный пользователь: 

| Логин  | Пароль |
|--------|--------|
| `user` | `100`  |

- `/login` - открывает старицу аутентификации.
- `/register` - открывает старицу регистрации.
- После аутентификации доступна страница `/main`

На главной странице есть несколько действий:
- Просмотр погоды для тех локаций, что есть у пользователя.
- `Log Out` - выход из аккаунта.
- `Search location` - поиск локации по имени, при вызове которой осуществляется отображение всех найденных локаций с таким именем. Локацию можно доавить к себе, нажав кнопку `Add location`.
Затем снова осуществляется redirect на страницу `/main`.

---
<a name="author"></a>
### Автор проекта:  [Grishuchkov Danila](https://github.com/grishuchkov)

#### Данный репозиторий является реализацией второго учебного проекта из курса [Java Backend Learning](https://zhukovsd.github.io/java-backend-learning-course/)


---
