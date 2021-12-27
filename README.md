<h2>Home task #21</h2>
<h4>Description</h4>
<p>This app acts as a school journal. Allows you to enter data in the form of marks, students, subjects, teachers, and student parents.</p>
<h4>Task list</h4>
<ol>
<li> git repo and best practices
<li> base on #20
<li> add DAO and hibernate tiers with spring integration
<li> all configurations in Java code (don't use xml)
<li> deploy to servlet container per student
</ol>
<h4>Technologies</h4>
<ol>
<li>Java version: 11
<li>MySQL database
<li>commons-dbcp2 pool
<li>docker-compose
<li>Flyway
<li>Hibernate
<li>Slf4j+logback
<li>Lombok
</ol>
<h4>How to run</h4>
<p>In order to build and run the application, follow these steps. 
In the console, go to the project directory and type:</p>
<ol>
<li>mvn clean package
<li>docker-compose up -d
<li>mvn flyway:migrate
</ol>
