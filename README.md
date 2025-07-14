# 📘 Реєстр шкільних закладів

Система обліку шкільних закладів: створення, перегляд та деактивація.

## 📌 Опис

Це монолітна Java Spring Boot + React система, яка дозволяє:

- Переглядати перелік шкіл
- Фільтрувати за областю, типом та активністю
- Створювати нові школи
- Деактивувати школи (без фізичного видалення з БД)

---

## ⚙️ Технології

### 🔹 Backend
- Java 17
- Spring Boot 3.5.3
- Spring Web, JPA, Validation
- PostgreSQL
- Liquibase
- Gradle

### 🔸 Frontend
- React + Vite
- Axios
- CSS Modules

### 🐳 Інфраструктура
- Docker
- Docker Compose

---

## 📁 ERD — Схема бази даних

#### School
- id: int, PK
- name: string
- edrpou: string (8 chars, unique)
- region: string
- type: enum (Гімназія, Ліцей, ЗЗСО)
- is_active: boolean

---

