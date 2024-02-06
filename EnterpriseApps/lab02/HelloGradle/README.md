# Komendy

Dodać do pliku build.gradle:
```java
jar {
    manifest {
        attributes(
                'Class-Path': configurations.compileClasspath.collect { it.getName() }.join(' '),
                'Main-Class': 'org.example.Main'
        )
    }
}
```

Następnie:
```java
gradle build --no-daemon 
```

I na koniec:
```java
java -jar build/libs/HelloGradle-1.0-SNAPSHOT.jar
```