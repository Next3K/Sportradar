# Game Board  

This project provides an efficient implementation of Football World Bup Score Board.

## Table of Contents

- [Overview](#overview)
- [Installation](#installation)
- [License](#license)

## Overview 

The implementation provides methods that allows to:
1. Start a game
2. Finish a game
3. Update score
4. Get summary of the scores ordered by total score in descending order

Typical Wold Cup hosts around 64 matches which means that number and frequency of operations 1, 2 and 3 is quite limited. On the other hand, the number of requests for operation 4 (get summary) can be expected to reach thousands per second because the number of fans/users is quite unlimited. Hence, this implementation prioritizes the efficiency of operation 4. 

The beforementioned operations have the following approximate time complexities:


1. Start a game - _O(logN)_
2. Finish a game - _O(logN)_
3. Update score - _O(logN)_
4. Get summary - _O(N)_

 


## Installation

Clone the repository:

```bash
git clone https://github.com/Next3K/Sportradar.git   
cd Sportradar 
```

Make sure that Maven and Java (17+) are installed.

```bash
mvn --version
```

```bash
java --version
```

Install all dependencies with Maven.

```bash
mvn clean install 
```

Run the app.

```bash
java -cp target/Sportradar-1.0-SNAPSHOT.jar org.example.Main
```
You van modify the Main.java source code to perform any logic according to your liking. 

An example output:

```bash
Starting World Cup
----------- Current Games -----------
SPAIN - BRAZIL: 2 - 3
MEXICO - CANADA: 0 - 5
GERMANY - FRANCE: 2 - 2
URUGUAY - PORTUGAL: 1 - 1
POLAND - JAPAN: 1 - 0
ARGENTINA - SAUDI_ARABIA: 0 - 1
------------------------------------- 


----------- Current Games -----------
SPAIN - BRAZIL: 4 - 3
MEXICO - CANADA: 0 - 6
GERMANY - FRANCE: 3 - 2
URUGUAY - PORTUGAL: 2 - 1
POLAND - JAPAN: 1 - 1
ARGENTINA - SAUDI_ARABIA: 1 - 1
------------------------------------- 


----------- Current Games -----------
POLAND - JAPAN: 3 - 2
ARGENTINA - SAUDI_ARABIA: 1 - 3
------------------------------------- 


Finished in: 17 ms
```


## License 
This project is licensed under the MIT License – see the [LICENSE](./LICENSE) file for details.


