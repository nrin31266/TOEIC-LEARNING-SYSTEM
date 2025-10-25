app-common/
├── src/main/java/com/app/common/
│   ├── dto/
│   │   ├── ApiResponse.java
│   │   ├── ErrorResponse.java
│   ├── exception/
│   │   ├── BaseException.java
│   │   ├── BusinessException.java
│   │   ├── ErrorCode.java
│   ├── event/
│   │   ├── BaseEvent.java
│   │   ├── OrderCreatedEvent.java
│   │   ├── PaymentFailedEvent.java
│   ├── utils/
│   │   ├── JsonUtils.java
│   │   ├── DateUtils.java
│   │   ├── StringUtils.java
│   ├── constants/
│   │   ├── KafkaTopics.java
│   │   ├── AppConstants.java
│   └── handler/
│       ├── BaseExceptionHandler.java
├── pom.xml
// build and deployment scripts and configurations
mvn clean deploy
