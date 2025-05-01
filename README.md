# Environment

- Node.js
- JDK17
- MySQL (8.0 and above)
- Redis (2.6 and above)
- Maven

# Frontend

In command prompt, enter:

```powershell
npm install
npm run dev
```

# Backend

Configure MySQL and Redis connection in application.yaml:

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/medical?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&autoReconnect=true&rewriteBatchedStatements=true
    username: yourusername
    password: yourpassword
  redis:
    host: 127.0.0.1
    port: 6379
    lettuce:
      pool:
        max-active: 10
        max-idle: 10
        min-idle: 1
        time-between-eviction-runs: 10s
```

- ### **Method 1: Using IDEA GUI (Recommended)**

  1. **Ensure project is properly loaded**:
     - Verify Maven dependencies are downloaded (click refresh icon in right toolbar)
  2. **Locate main class**:
     - Find the class annotated with `@SpringBootApplication` (`AdminTemplateApplication.java`)
  3. **Click to run**:
     - Click the green arrow ▶️ next to the class → Select `Run 'AdminTemplateApplication'`

- ### **Method 2: Via Maven Command**

  To run manually in IDEA's terminal:

  ```shell
  # Navigate to project root (folder containing pom.xml)
  cd /path/to/your-project
  
  # Start with Maven 
  mvn spring-boot:run
  ```

- ### **Method 3: Run Packaged JAR**

  Build first then run:

  ```
  mvn clean package
  java -jar target/medical-2.7.10.jar
  ```

Open in a browser: http://localhost:5173/login
