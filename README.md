# 🛒 NinjaShop Automation Framework

## Project Overview
This project is a Selenium-based automation framework designed to test the complete checkout flow of the NinjaShop demo e-commerce application.

The framework follows industry best practices like:
- Page Object Model (POM)
- TestNG for execution
- Reusable utilities
- Explicit waits (no Thread.sleep)

---

## Features Covered
✔ Guest Checkout Flow  
✔ Billing Details Form Automation  
✔ Handling Dynamic Dropdowns (Country/State)  
✔ Conditional Flow Handling (Step 3 Skipped Scenario)  
✔ Delivery Method Selection  
✔ Payment Method Handling  
✔ Order Confirmation Validation  

---

## Tech Stack
- Java
- Selenium WebDriver
- TestNG
- Maven
- ChromeDriver



## 📂 Project Structure


## 📁 Project Structure

```
NinjaShop-Automation/
│
├── pom.xml                      
├── testng.xml                 
│
├── DESIGN_DOCUMENT.md           # Framework design document
│
├── screenshots/                 
│
├── src/
│   ├── main/java/ninjashop/
│   │
│   │   ├── base/
│   │   │   ├── BasePage.java
│   │   │   └── BaseTest.java
│   │   │
│   │   ├── pages/
│   │   │   ├── HomePage.java
│   │   │   ├── LoginPage.java
│   │   │   ├── RegisterPage.java
│   │   │   ├── ProductPage.java
│   │   │   ├── CartPage.java
│   │   │   └── CheckoutPage.java
│   │   │
│   │   ├── utils/
│   │   │   ├── ConfigReader.java
│   │   │   ├── ScreenshotUtil.java
│   │   │   └── ExtentReportManager.java
│   │
│   ├── test/java/ninjashop/
│   │
│   │   ├── tests/
│   │   │   ├── AuthenticationTest.java
│   │   │   ├── ProductTest.java
│   │   │   ├── CartTest.java
│   │   │   ├── CheckoutTest.java
│   │   │   └── FormValidationTest.java
│   │   │
│   │   ├── dataprovider/
│   │   │   └── LoginDataProvider.java
│   │   │
│   │   ├── listeners/
│   │   │   └── TestListener.java
│
├── src/test/resources/          
│
├── target/                   
├── test-output/                 
```

---

## ▶️ How to Run
1. Clone the repository
2. Open in Eclipse/IntelliJ
3. Run: testng.xml

---

## Key Challenges Solved
- Handling AJAX-based checkout steps
- Skipped Step 3 (delivery address)
- Dynamic waits for loading overlays
- Reliable click using JavaScriptExecutor

---

## Screenshots
Screenshots are available in the `/screenshots` folder.

---

## 👨‍💻 Author
Katakamsetty Lakshmi Manasa
