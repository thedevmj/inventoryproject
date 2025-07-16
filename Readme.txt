# 🗃️ Inventory Management System (Java + JDBC)

A robust desktop-based inventory management tool built using Java and JDBC, designed to track products, categories, and stock levels with precision and flexibility. This system is ideal for small businesses or personal inventory tracking needs.

## 🛠️ Features

- Product CRUD: Add, update, delete, and view inventory items
- Category Management: Organize products into categories
- Stock Tracking: Monitor quantity and restock alerts
- SQL Integration: Seamless communication with relational databases
- User-Friendly UI: Built with Java Swing (customized JTable layouts)

## 📦 Tech Stack

| Layer        | Technology       |
|--------------|------------------|
| Language     | Java             |
| UI Framework | Java Swing       |
| Database     | MySQL / SQLite   |
| DB Access    | JDBC             |

## 🔗 Architecture Overview

- **Modular Design**: Packages structured for separation of concerns (`dao`, `model`, `service`, `ui`)
- **DAO Pattern**: Simplified database interactions using Data Access Objects
- **Error Handling**: Graceful SQL and UI feedback for missing or null records
- **UI Customization**: Grouped JTable display for categories and products
