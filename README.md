# PrettyLittleThing Automation Project

## Overview
This project automates the process of purchasing an item from PrettyLittleThing using Selenium WebDriver and TestNG.

## Tools and Technologies
- **Java**
- **Selenium WebDriver**
- **WebDriver Manager**
- **TestNG**

## Project Structure
- **Page Object Model (POM)**:
  - `LandingPage.java`
  - `PlpPage.java`
  - `PdpPage.java`
  - `CartPage.java`
  - `CheckoutPage.java`

- **Test Class**:
  - `BuyOneItem.java`: Executes the Test Case.
  - It works for all type of products with any type of sizes be that "One Size", S/M or "16"
  - It also handles the scenarios that if a product is out of stock then it will look for a different product.
  
- **Abstract Components**:
  - `AbstractComponent.java`
  
- **Properties File**
  - `Selectors`

## Configuration
- **Credentials and URL**: Configured directly in the test class.

## Author
- Abu Bakar Hamid
