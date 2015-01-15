# Injection Workshop

## Package & Run
* ```cd injection```
* ```mvn clean package```
* ```java -jar target/injection-1.0-SNAPSHOT-jar-with-dependencies.jar```

## Interact over network
* ```telnet $IP 8888```
* ```echo 'Bob' | nc $IP 8888```

## Requirements
* Java 1.6+
* Maven
* A Java IDE

## Learn More
* owasp page: https://www.owasp.org/index.php/Top_10_2013-A1-Injection
* sql injection prevention cheat sheet: https://www.owasp.org/index.php/SQL_Injection_Prevention_Cheat_Sheet
* sqlmap: http://sqlmap.org/

For workshop details, see injection.otl