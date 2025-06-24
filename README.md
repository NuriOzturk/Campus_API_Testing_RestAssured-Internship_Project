
# 🎓 Campus API Testing Framework


### 
This project contains an **API Test Automation Framework** and a **manual API test collection** developed for an actively used education management system. It includes manual tests using **Postman** as well as automation tests using **Java + Rest Assured**.

---

## 📁 Project Structure

```
Campus_API_Testing_RestAssured
├── src/
│   └── test/
│       ├── java/
│       │   └── campus/                  # Java source files
│       │       ├── Cities
│       │       ├── Country
│       │       ├── Education
│       │       ├── Grading
│       │       ├── Incident
│       │       ├── States
│       │       └── StudentGroup
│       │
│       └── utilities/                  # Utility classes
│           ├── ConfigReader
│           └── ParentPage
│
├── resources/                          # Resource files
│   └── Campus API Testing Postman.postman\_collection.json
├── configuration.properties            # Configuration file
└── pom.xml                             # Maven configuration file

````

---

## 🧪 Technologies & Tools

| Purpose                      | Technology / Tool            |
|-----------------------------|------------------------------|
| Test Automation             | Java + Rest Assured          |
| Test Framework              | TestNG                       |
| API Testing                 | Postman                      |
| Build Management            | Maven                        |
| Configuration Management    | `.properties` file           |

---

## 📝 Features

### ✅ **1. Manual API Tests (Postman)**

- Postman collection: `Campus API Testing Postman.postman_collection.json`
- Core tests: Login, Student Group, Grading, Education, Incident, etc.
- For each endpoint:
  - Positive and negative scenarios
  - Script-based response validation
  - Status code, response body, and JSON structure assertions

### 🤖 **2. Automated API Tests (Rest Assured)**

- Java-based tests located under `src/test/java/campus`.
- Separate package per functional module:
  - `StudentGroup`, `Grading`, `Incident`, etc.
- Common configurations and token management are handled in `ParentPage` and `ConfigReader`.
- Test scenarios include:
  - GET, POST, PUT, DELETE
  - Positive and negative flows
  - Dynamic data usage and assertions

---

## ⚙️ Setup & Execution

### 🔹 Prerequisites

- Java 11+
- Rest Assured
- TestNG
- Maven
- IntelliJ IDEA (or another IDE)
- Postman

### 🔹 Configuration

Edit the `configuration.properties` file according to your API environment:
```properties
baseUrl=*****
username=*****
password=*****
````

### 🔹 Running Automation Tests

```bash
   mvn clean test
```

---

## 📂 User Stories Covered

The detailed test scenarios for the following modules:

* Student Group
* Grading
* Education
* Incident
* Country, State, City

---

## 📌 Notes

* Token management is handled via `ParentPage`.
* `ConfigReader` fetches environment data from the `.properties` file.
* The project is CI/CD-ready (e.g., for Jenkins integration).
* Both positive and negative scenarios are covered in Postman and Java tests.

---

**Contributors:**

- **[Zafer Ataklı](https://github.com/zaferatakli)**
- **[Rıfat Batır](https://github.com/rftbtr)**
- **[Yiğit Çam](https://github.com/Yigit-Cam)**
- **[Tugba Kilic](https://github.com/TugbaKilic33)**
- **[Nuri Öztürk](https://github.com/NuriOzturk)**
- **[Azim Korkmaz](https://github.com/AzimKorkmaz)**
- **[Mert Can Özdemir](https://github.com/lioncarnes)**
- **[Sibel Öztemel](https://github.com/Sibel52)**
- **[Eren Icinozbebek](https://github.com/theeren123)**

---

## 🔗 GitHub Links

* [Main Repository](https://github.com/zaferatakli/Campus_API_Testing_RestAssured)

---

## 📜 License

The tested website [https://test.mersys.io/](https://test.mersys.io/) belongs to Techno Study's education platform. This
project is created **strictly for educational and internal testing** purposes. It is not intended for commercial use.

---
