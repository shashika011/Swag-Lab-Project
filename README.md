# Swag Lab Automation Testing Project

## 📌 Project Overview

This project is an end-to-end web automation testing framework developed using **Selenium WebDriver, Java, TestNG, and Maven**.

The project automates functional test scenarios for the **Swag Labs** web application.

## 🛠️ Technologies & Tools

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Web UI automation |
| TestNG | Test execution |
| Maven | Build management |
| GitHub Actions | CI/CD |

## 🛠️ Technologies & Tools

| Technology | Purpose |
|---|---|
| Java | Programming language |

## 🏗️ Project Structure

```text
Swag-Lab-Project/
│
├── .github/
│   └── workflows/
│       └── automation.yml
│
├── src/
│   └── test/
│       ├── java/
│       │   ├── pages/
│       │   ├── tests/
│       │   └── utilities/
│       └── resources/
│
├── Drivers/
├── Reports/
├── Screenshots/
├── TestData/
├── XMLFiles/
├── logs/
├── target/
├── test-output/
│
├── pom.xml
├── .gitignore
├── LICENSE
└── README.md
```

### 📂 Folder Description

| Folder / File              | Description                                                                           |
| -------------------------- | --------------------------------------------------------------------------------------|
| `.github/workflows`        | GitHub Actions CI/CD configuration                                                    |                                        
| `src/test/java/pages`      | Page Object classes and web page locators                                             |
| `src/test/java/pages/base` | Common test setup and teardown                                                        |
| `src/test/java/tests`      | TestNG test classes                                                                   |
| `src/test/java/utilities`  | Reusable utility/helper/Browser Factory/Config Provider/ Excel Data Provider  classes |
| `src/test/resources`       | Configuration and test data                                                           |
| `Drivers`                  | Browser driver files, if required                                                     |
| `Reports`                  | Generated test reports                                                                |
| `Screenshots`              | Test execution screenshots                                                            |
| `TestData`                 | Test data files                                                                       |
| `XMLFiles`                 | TestNG suite configuration files                                                      |
| `logs`                     | Test execution logs                                                                   |
| `target`                   | Maven build and test output                                                           |
| `test-output`              | TestNG generated output                                                               |
| `pom.xml`                  | Maven dependencies and project configuration                                          |
| `.gitignore`               | Files excluded from Git                                                               |
| `README.md`                | Project documentation                                                                 |

