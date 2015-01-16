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
