# How to setup and run the Game:
> Note: It is advised to have your laptop plugged-in to a power source before running the game, because on some Machines/Operating Systems the IDE won't be supplied with the necessary Processing Power and might result in laggy results.
1. Clone the Repository
2. Make sure to download and install Java 11 (https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
3. Open the project in your favourite IDE (Intellij if possible since it was developed in this IDE) by Going to File > Open.. and selecting the build.gradle file
4. Set Java 11 as your Java version in File > Project Structure.. > Project
5. The game can be started by running the main of the Game class
6. To login use nickname: test and password: test

## How to use the provided tools
1. In Intellij go to the Gradle menu on top-right and double click the "check" button (from the "verification" drop-down) in order to run Jacoco, Spotbugs, PMD, Checkstyle and the tests.
2. If you want to check the code coverage double click on the "JacocoTestReport" button (from the "verification" drop-down).