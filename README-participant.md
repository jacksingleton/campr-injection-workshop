## 0. Prepare for the workshop

The following commands should run without errors:

* `git clone https://github.com/jacksingleton/injection-workshop.git`
* `cd injection-workshop`
* `mvn -DskipTests clean package`
* `java -jar target/injection-1.0-SNAPSHOT-jar-with-dependencies.jar`

## 1. Generate IDEA Project
* ```mvn idea:idea```

## 2. Run tests
* ```mvn test```

## 3. Fix the failing test
* The test fails for the simplest case of sql injection
* A passing test does not imply correctness!

## 4. Package & Test Manually
* ```mvn clean package```
* ```java -jar target/injection-1.0-SNAPSHOT-jar-with-dependencies.jar```

## 5. Review slides (or become a facilitator!)
* The slides are html/js
* Open presentation/index.html in a web browser
