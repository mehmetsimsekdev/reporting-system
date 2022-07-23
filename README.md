- [1. Reporting System](#1-reporting-system)
  - [1.1. Reporting System Key Features](#11-reporting-system-key-features)
  - [1.2. Build instructions](#12-build-instructions)
    - [1.2.1. Build requirements](#121-build-requirements)
    - [1.2.2. Build steps](#122-build-steps)
  - [1.3. Screenshots](#13-screenshots)
 

# 1. Reporting System

Reporting System web application for hospitals and laboratories.

## 1.1. Reporting System Key Features

- Light, simple and useful theme
- Authorization system with 3 different roles : ADMIN,USER,GUEST:
  + ADMIN can read, create, edit and delete reports
  + ADMIN can change anyone's role and delete anyone member to system in the admin page
  + USER can read, create and edit reports
  + GUEST can only read reports
- Customizable user profile
- Avanced report rearch option
- Option to convert report to pdf
- Jwt authentication system

## 1.2. Build instructions
The source code can be built using maven. **Reporting System** is a Springboot project. 

### 1.2.1. Build requirements
 - Git (to clone repo)
 - Maven (for linux `sudo apt install maven`) if downloaded before, you can check with `mvn -version  `
 - A database on your computer local or web-based application([phpmayadmin](https://www.phpmyadmin.net/) etc.) etc.

### 1.2.2. Build steps

 1. Open terminal and change the current working directory to the location where you want the cloned directory. 
  **Clone** source code.
   	
    `git clone https://github.com/mehmetsimseknet/reporting-system.git`
  
 2. Enter into `reporting-system/src/main/resources` and open **application.properties**. You can connect your database with url,username and password.
  
 3. Go back to source directory again
    
    `cd yourFolder/reporting-system`
  
 4. Run maven command
    
    `mvn spring-boot:run`

 5. And application will be active on **port 8090**.
    
    `http://localhost:8090/`



## 1.3. Screenshots
![Reporting System Login](https://github.com/mehmetsimseknet/reporting-system/blob/main/screenshots/Screenshot%20from%202022-07-23%2021-45-17.png?raw=true)
![Reporting System Reports](https://github.com/mehmetsimseknet/reporting-system/blob/main/screenshots/Screenshot%20from%202022-07-23%2021-45-46.png?raw=true)
![Reporting System Report Design](https://github.com/mehmetsimseknet/reporting-system/blob/main/screenshots/Screenshot%20from%202022-07-23%2021-46-08.png?raw=true)
![Reporting System Create Report](https://github.com/mehmetsimseknet/reporting-system/blob/main/screenshots/Screenshot%20from%202022-07-23%2021-46-17.png?raw=true)
![Reporting System Account Details](https://github.com/mehmetsimseknet/reporting-system/blob/main/screenshots/Screenshot%20from%202022-07-23%2021-46-24.png?raw=true)
![Reporting System Admin Page](https://github.com/mehmetsimseknet/reporting-system/blob/main/screenshots/Screenshot%20from%202022-07-23%2021-46-33.png?raw=true)
![Reporting System Database](https://github.com/mehmetsimseknet/reporting-system/blob/main/screenshots/Screenshot%20from%202022-07-23%2022-23-45.png?raw=true)
