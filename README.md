# **Campr Injection Workshop**

## **Running in Docker**

---

## Requirements

- Docker desktop > 2.1
  ```bash
  brew cask install docker
  ```
- A Java IDE _e.g, IntelliJ IDEA_

## Steps to complete the workshop

1. Run the application

   ```bash
   docker-compose rm -f && docker-compose up
   ```

2. Open the application & find the bug http://localhost:8080/

3. Fix the bug in the code

4. Run tests

   ```bash
   docker exec -it campr-injection-workshop /bin/sh -c 'cd app && ./gradlew -g /app/.gradle test'
   ```

5. Once the test passes, Restart the application
   ```bash
   docker-compose rm -f && docker-compose up
   ```
6. Open the application & verify the bug is fixed http://localhost:8080/

7. That's it. Stop the services and do clean up

   ```bash
   docker-compose rm -f
   ```
