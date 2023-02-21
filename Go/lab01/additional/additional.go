package main

import (
	"bufio"
	"fmt"
	"log"
	"math/rand"
	"os"
	"sort"
	"strconv"
	"strings"
	"time"
)

func main() {
	additionalTask()
}

type Player struct {
	username string
	tries    int
	date     string
}

var db []Player

func additionalTask() {
	random := rand.Intn(100) + 1
	numberOfGuesses := 1

	var playerUsername string
	fmt.Println("What is your username? ")
	_, err := fmt.Scan(&playerUsername)

	if err != nil {
		fmt.Println("Wrong type! Should be string")
	}

	fmt.Println("Guess a number between 1 and 100.")

	for {
		var guess int
		fmt.Print("Give your guess or exit with -1 ")
		_, err2 := fmt.Scan(&guess)

		if err2 != nil {
			fmt.Println("Wrong type! Should be int")
		}

		if guess == -1 {
			fmt.Println("Goodbye!")
			break
		}

		if guess == random {
			fmt.Printf("Cool! You guessed in %v tries!\n", numberOfGuesses)

			player := Player{
				username: playerUsername,
				tries:    numberOfGuesses,
				date:     time.Now().String(),
			}
			db = append(db, player)

			fmt.Println("Do you want to play antoher one? [Y|N]")
			var ifMore string
			_, err := fmt.Scan(&ifMore)

			if err != nil {
				fmt.Println("Wrong data! Should be string")
			}

			if ifMore == "Y" {
				fmt.Println("Cool let's play another one!")

				fmt.Println("What is your username? ")
				_, err := fmt.Scan(&playerUsername)

				if err != nil {
					fmt.Println("Wrong type! Should be string")
				}

				random = rand.Intn(100) + 1
				numberOfGuesses = 1
			} else {
				sort.SliceStable(db, func(i, j int) bool {
					return db[i].tries < db[j].tries
				})

				fmt.Printf("Sorted database, %v\n", db)

				var tempDb [][]string

				file, err := os.OpenFile("hallOfFame.txt", os.O_RDONLY, 0644)

				if err != nil {
					log.Fatal(err)
				}

				scanner := bufio.NewScanner(file)
				for scanner.Scan() {
					toTempDb := strings.Split(scanner.Text(), ",")
					tempDb = append(tempDb, toTempDb)
				}

				if err := scanner.Err(); err != nil {
					log.Fatal(err)
				}

				var hallOfFame []int

				for _, element := range tempDb {
					tries, err := strconv.Atoi(element[0])
					if err != nil {
						fmt.Println("Error during conversion")
						return
					}

					hallOfFame = append(hallOfFame, tries)
				}

				sort.Ints(hallOfFame)

				if db[0].tries < hallOfFame[0] {
					fmt.Println("Wow! You broke global record!")
				} else if db[0].tries == hallOfFame[0] {
					fmt.Println("Wow! You equal global record!")
				}

				file, err = os.OpenFile("hallOfFame.txt", os.O_APPEND|os.O_WRONLY, 0644)
				if err != nil {
					log.Fatal(err)
				}
				defer file.Close()

				textWriter := bufio.NewWriter(file)

				var toSave string

				for _, element := range db {
					toSave = fmt.Sprintf("\n%v,%v,%v", element.tries, element.username, element.date)
					_, err = textWriter.WriteString(toSave)
					if err != nil {
						log.Fatal(err)
					}
				}
				textWriter.Flush()

				if err != nil {
					log.Fatal(err)
				}
				defer file.Close()

				break
			}

		} else if guess < random {
			numberOfGuesses += 1
			fmt.Println("Given number is too small")
		} else {
			numberOfGuesses += 1
			fmt.Println("Given number is too large")
		}
	}
}
