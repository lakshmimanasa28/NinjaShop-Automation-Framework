# 📘 Design Document – NinjaShop Automation Framework

## 🎯 Objective
To automate the checkout flow of an e-commerce website using Selenium with a scalable and maintainable framework.

---

## 🏗️ Framework Design

### 🔹 Design Pattern
Page Object Model (POM)

### 🔹 Components
- BasePage → common reusable methods
- Pages → UI interaction logic
- Tests → Test scenarios
- Utils → Configuration handling

---

## 🔄 Workflow Covered

1. Navigate to Home Page
2. Add product to cart
3. Proceed to checkout
4. Select Guest Checkout
5. Fill Billing Details
6. Handle:
   - Same address checkbox
   - Dynamic dropdowns
7. Handle Step 3 (conditional)
8. Select Delivery Method
9. Select Payment Method
10. Confirm Order

---

## ⚠️ Challenges

### 1. Dynamic Checkout Steps
- Step 3 sometimes skipped
- Solution: Conditional logic using `.in` class

### 2. AJAX Loading Issues
- Buttons clickable but not functional
- Solution: Wait for loaders to disappear

### 3. Dropdown Handling
- State loads after country selection
- Solution: Wait for options > 1

---

## ✅ Solution Approach

- Used explicit waits everywhere
- Avoided Thread.sleep
- Used JavaScriptExecutor for unstable elements
- Implemented conditional step handling

---

## 📈 Future Enhancements
- Data-driven testing (DataProvider)
- Cross-browser execution
- CI/CD integration