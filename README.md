# Game Board  

This project provides an efficient implementation of Football World Bup Score Board.

## Table of Contents

- [Overview](#overview)
- [Installation](#installation)
- [License](#license)

## Overview 

The implementation provides an interface that allows to:
1. Start a game
2. Finish a game
3. Update score
4. Get summary of the scores by total score

It has been assumed that operation 4. will be the most commonly used since the number of games is usually quite low and number 
of potential fans demanding a summary of the games is quite unlimited. Hence, the implementation prioritizes the efficiency of operation 4. 

The beforementioned operations have the following time complexities:
1. _O(logN)_
2. _O(logN)_
3. _O(logN)_
4. _O(N)_

## Installation

Clone the repository:

```bash
git clone https://github.com/Next3K/Sportradar.git   
cd Sportradar 
```

Make sure Maven is installed and your Java version is 17 or higher.

```bash
mvn --version
```

Install all dependencies with Maven.

```bash
mvn clean install 
```

Modify the Main.java code to perform any logic according to your liking and run the app.

Run the app.

```bash
mvn install 
```

## License 
This project is licensed under the MIT License – see the [LICENSE](./LICENSE) file for details.


