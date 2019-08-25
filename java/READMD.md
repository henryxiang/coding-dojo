# Gradle Demo App (Groovy + Java)

## Running a Specific App in Dev Mode

```bash
./gradlew runApp -PmainClass=com.demo.JavaApp
```

To run the default application, which specified by `mainClassName` variable in [`build.gradle`](./build.gradle) file,
use the following command

```bash
./gradlew run
```

## Running All Tests

```bash
./gradlew test
```

## Building JAR File for Distribution

```bash
./gradlew build
```

The "fat-jar" file will be built and put into [`build/lib`](./build/lib/) directory. The name of JAR file is configurable in [`settings.gradle`](./settings.gradle) file.

## Running Java Class in JVM

```bash
java -cp build/lib/demo-app.jar <full.class.Name>
```

To run the default class,

```bash
java -jar build/lib/demo-app.jar
```
