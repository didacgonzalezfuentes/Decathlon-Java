# Decathlon Scoring System (Java) 🏃‍♂️🏆

A robust, Object-Oriented command-line application built in Java to process athletic performances, calculate scores based on official formulas, and manage dynamic leaderboards for a Decathlon event.

## 🚀 Technical Highlights
* **MVC Architecture:** Clean separation of concerns with distinct Model (`dominio`), View (`iu`), and Controller (`casosdeuso`) packages.
* **Data Structures:** Utilizes `TreeMap` for efficient athlete retrieval via registration numbers and `LinkedList` for dynamic, real-time leaderboard sorting.
* **Complex Scoring Math:** Implements the official Decathlon mathematical formulas (using parameters A, B, and C) to accurately convert times and distances into points.
* **Interactive CLI:** Features a fully functional Command Line Interface built with `Scanner` to register athletes, input scores, and query the leaderboard.

## 💻 How to Run
1. Clone the repository.
2. Compile the `.java` files.
3. Run the `InterficieUsuario` class to launch the interactive terminal menu.
