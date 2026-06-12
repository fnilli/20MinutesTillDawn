# 20 Minutes Till Dawn - AP Project

> A 2D top-down survival shooter cloned from **20 Minutes Till Dawn** using the **libGDX** framework in **Java**.
> 
> Developed as the graphics assignment for the "Advanced Programming" course at Sharif University of Technology.

## Overview
This project is a fast-paced, 360-degree action roguelite game where the player must survive against endless waves of enemies for a specified duration (2, 5, 10, or 20 minutes). Built upon the MVC architecture, it features a complete user authentication system, dynamic combat mechanics, and a character progression system driven by experience points dropped by defeated monsters.

## Key Features
* **Account & Menu System:** Registration, login, profile management (avatars, passwords), settings (audio, keybinds), and a sortable Scoreboard.
* **Dynamic Combat:** WASD movement, mouse-aimed shooting, active reloading, and an optional auto-aim system.
* **RPG Progression:** Collect XP seeds to level up and draft randomized abilities (e.g., Vitality, Damager, Speedy, Procrease) during gameplay.
* **Entity Variety:** Choose from 5 heroes (Shana, Diamond, Scarlet, Lilith, Dasher) and fight distinct enemies (Tentacle Monsters, Eyebats, and an Elder Boss with a shrinking arena shield).
* **Arsenal:** Equip different weapons like Revolvers, Shotguns, and Dual SMGs, each with unique ammo capacities, reload times, and damage outputs.
* **Persistence:** Complete JSON-based serialization to save and resume game progress.
* **Visual Polish:** Custom HUD, damage/death animations, localized dynamic lighting around the player, and projectile tracking.

## Build and Run

### Prerequisites
Ensure you have the following installed on your system:
* Java Development Kit (JDK) 17 (e.g., `sudo apt install openjdk-17-jdk`)

*Note: If Gradle fails due to BellSoft Liberica toolchain issues, add `org.gradle.java.installations.auto-download=false` to your `gradle.properties` and remove the `toolchain` vendor specification from the root `build.gradle`.*

### Execution
Navigate to the root directory to compile and run the game via the Gradle wrapper:


chmod +x gradlew
./gradlew lwjgl3:run
## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application via the Gradle CLI.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.
