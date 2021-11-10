# OpenMarvel

You need to add your own api keys from : https://developer.marvel.com/

into your local.properties file:

apiKey = "PUBLIC MARVEL API KEY"


privateApiKey = "PRIVATE MARVEL API KEY"


The project uses Java 11 and Gradle 6.5.

The Architecture for the project is Clean Architecture and MVVM for the Presentation Layer.

It's done following Classic TDD just for the HomeScreen.

There are some End To End UI tests following the Robot Pattern.

Things to improve:

Paging 3.0 (Check before doing that if the issue with UI tests with paging 3.0 is fixed)
After moving to paging 3.0, use flow in place of LiveDatas to SharedFlow.
Unit tests for Detail Feature
Update Gradle, Kotlin and Libraries (de.fayard.buildSrcVersions plugin for automatic updates for the dependencies has problems with gradle 7.0+ try moving to versions catalog using toml and also check if update recomendation is fixed for last Android Studio version)
Improve the Detail Screen
Add Motion Layout animation in Detail Screen.
