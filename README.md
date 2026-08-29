# 👾 20 Minutes Till Dawn

> A **2D top-down survival shooter** inspired by *20 Minutes Till Dawn*, developed in **Java** using the **libGDX** framework.
>
> Developed as a graphics assignment for the **Advanced Programming** course at **Sharif University of Technology**.

## 🎮 Overview

This project is a fast-paced, pixel-art action roguelite where the player must survive against endless waves of enemies for a selected duration of **2, 5, 10, or 20 minutes**.

Players can choose from different characters and weapons, fight enemies using **360-degree shooting**, collect experience dropped by defeated monsters, and level up by acquiring new abilities and upgrades. As the game progresses, enemies become increasingly challenging, requiring players to build effective combinations of weapons, abilities, and character attributes to survive.

The project also includes a complete user system, customizable settings, score tracking, and game progress persistence.

## ✨ Features

* Multiple playable characters with different attributes
* Multiple weapons with unique damage, ammo, and reload mechanics
* Mouse-aimed 360-degree shooting
* Enemy waves with increasing difficulty
* Different enemy types and boss encounters
* XP, leveling, and randomized ability upgrades
* Health, damage, and survival mechanics
* Save and resume game progress
* User accounts, profiles, and scoreboards
* Customizable controls and game settings
* HUD, animations, and visual effects

## 🛠️ Technologies

* **Java**
* **libGDX**
* **LWJGL3**
* **Gradle**

## 📁 Project Structure

```text id="h3n92q"

20MinTillDawn2/
├── assets/              # Game assets
├── build/               # Build output
├── core/                # Core game logic
├── data/                # Persistent game and user data
├── docs/                # Project documentation
├── gradle/              # Gradle wrapper files
├── lwjgl3/              # Desktop launcher
│
├── build.gradle         # Gradle build configuration
├── gradle.properties    # Gradle properties
├── gradlew              # Gradle wrapper
├── gradlew.bat          # Windows Gradle wrapper
├── settings.gradle      # Project settings
└── README.md
```
## 🚀 Build and Run

### Prerequisites

- **JDK 17**

The project is configured to work with Java 17 without requiring a specific JDK vendor. OpenJDK, Eclipse Temurin, Amazon Corretto, BellSoft Liberica, and other Java 17 distributions should work.

### Run on Linux/macOS

From the project root:

```bash
chmod +x gradlew
./gradlew lwjgl3:run
```

### Run on Windows

```bat
gradlew.bat lwjgl3:run
```


### Run from an IDE

You can also run the desktop launcher directly from:

```text id="ps2yrd"
lwjgl3/src/main/java/com/tilldawn/lwjgl3/Lwjgl3Launcher.java
```

Run the `Lwjgl3Launcher` class using IntelliJ IDEA or another Java IDE.

## 📚 Documentation

The original assignment specification is available in:

```text id="y8plun"
docs/HW3.pdf
```

## 🎓 Credits

**Sharif University of Technology**
Department of Computer Engineering

**Course:** Advanced Programming
**Academic Year:** 2024–2025
