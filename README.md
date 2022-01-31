<h2>School journal</h2>
<h4>Description</h4>
<p>This app acts as a school journal. Allows you to enter data in the form of marks, students, subjects, teachers, and student parents.</p>
<h4>Technologies</h4>
<ol>
<li>Java version: 11
<li>Spring Boot v2.6.3
<li>Jetty-9.4.44
<li>MySQL database 8.0.27
<li>Apache commons-dbcp2 pool
<li>docker-compose
<li>Flyway
<li>Hibernate
<li>Slf4j+log4j
<li>Lombok
<li>Jackson
</ol>
<h4>How to run</h4>
<p>In order to build and run the application, follow these steps. 
In the console, go to the project directory and input:</p>
<ol>
<li>mvn clean package
<li>docker-compose up -d
<li>mvn flyway:migrate
</ol>
