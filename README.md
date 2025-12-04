# 🎄 Advent of Code 🎅
A collection of [Advent of Code](https://adventofcode.com/) puzzle solutions written in **idiomatic Kotlin** 🎯

## 🎁 About

This repository contains solutions to Advent of Code challenges, focusing on writing clean, idiomatic Kotlin code. Each puzzle is solved with an emphasis on readability, maintainability, and leveraging Kotlin's powerful features like extension functions, data classes, sealed classes, and functional programming paradigms.

The main goal is to demonstrate how Kotlin's expressive syntax and modern language features can elegantly solve complex algorithmic problems while keeping the code understandable and maintainable. 🚀✨

## 📁 Project Structure

```
src/
├── main/
│   ├── kotlin/
│   │   ├── aoc2025/          📅 Solutions organized by year
│   │   │   ├── day01/        🎄 Each day has its own package
│   │   │   ├── day02/
│   │   │   └── ...
│   │   └── utils/            🛠️  Shared utilities and helpers
│   └── resources/
│       └── aoc2025/          📝 Puzzle inputs and examples
│           ├── day01/
│           └── ...
```

- 📦 Solutions are organized by year (e.g., `aoc2025`) and day (e.g., `day01`)
- 🛠️ Utility functions and helpers are located in the `utils` package
- 📝 Input files are stored in the `resources` directory, organized by year and day

## ⚙️ Requirements

- ☕ Kotlin 1.9.21+
- ☕ JDK 17+

## 🏗️ Building and Running

This project uses Gradle with Kotlin DSL. To build the project:

```bash
./gradlew build
```

To run a specific solution, execute the main class or use the Gradle run task. 🚀