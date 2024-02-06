Najlepiej:

build.gradle:
```java
jar {
    manifest {
        attributes 'Main-Class': 'org.example.Main'
    }

    from {
        configurations.compileClasspath.collect { it.isDirectory() ? it : zipTree(it) }
    }
}
```

I wtedy biblioteki same się załączą

```java
gradle build --no-daemon

```

Na końcu:
```java
java -jar build/libs/Zad02-1.0-SNAPSHOT.jar
```

LUB MECHANICZNIE:

Po zmianach w build.gradle:

```java
gradle build --no-daemon

```

Następnie pobierz plik .jar ze strony https://mvnrepository.com z biblioteką Apache Commons Math (i wersja, którą używamy) i dodaj go do folderu build/libs

Na końcu:
```java
java -jar build/libs/Zad02-1.0-SNAPSHOT.jar
```