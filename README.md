# Dockerizing Test Automation for Scalable Test Execution

## Project Overview
This project containerizes a Selenium WebDriver test suite for Swag Labs using Docker, enabling isolated test environments and consistent execution across platforms.

## Test Suite
- **Login Tests**: User authentication validation
- **Cart Tests**: Product cart functionality
- **Checkout Tests**: Complete purchase flow

## Project Structure
```
src/
├── main/java/org/example/pages/     # Page Object Model classes
│   ├── LoginPage.java
│   ├── InventoryPage.java
│   ├── CartPage.java
│   └── CheckoutPage.java
└── test/java/org/example/
    ├── base/                         # Test configuration
    │   ├── BaseTest.java
    │   └── DriverManager.java
    └── tests/                        # Test classes
        ├── LoginTest.java
        ├── CartTest.java
        └── CheckoutTest.java
```

## Technologies
- Java 11
- Selenium WebDriver 4.16.0
- TestNG 7.8.0
- Maven 3.9
- Docker
- Allure Reports
- GitHub Actions

## Local Execution

### Run Tests
```bash
mvn clean test
```

### Generate Allure Report
```bash
mvn allure:serve
```

## Docker Execution

### Build Docker Image
```bash
docker build -t swag-labs-tests .
```

### Run Tests in Container
```bash
docker run swag-labs-tests
```

### Run with Volume for Reports
```bash
docker run -v "%cd%"/allure-results:/app/target/allure-results swag-labs-tests
```

## CI/CD Pipeline

### GitHub Actions
The project includes automated CI/CD pipeline that:
- Builds Docker image
- Runs test suite in container
- Generates Allure reports
- Deploys reports to GitHub Pages
- Sends email notifications
- Posts Slack notifications

### Setup GitHub Secrets
Configure in Settings > Secrets and variables > Actions:
- `EMAIL_USERNAME` - Gmail address
- `EMAIL_PASSWORD` - Gmail App Password
- `EMAIL_TO` - Recipient email
- `SLACK_WEBHOOK_URL` - Slack webhook URL

See CI_SETUP.txt for detailed instructions.

## Troubleshooting

### Maven Clean Error
```bash
# Close IDE, then:
rmdir /s /q target
mvn test
```

### Docker Build Issues
- Ensure Docker is running
- Check internet connection for dependency downloads

### Test Failures
- Verify https://www.saucedemo.com/ is accessible
- Check Chrome/ChromeDriver compatibility

## Reports
- Local: `target/allure-results/`
- GitHub Pages: Available after CI/CD run
- View locally: `mvn allure:serve`
