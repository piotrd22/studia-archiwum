package main

import (
	"fmt"
	"math"
	"math/rand"
	"time"
)

func main() {
	// first()
	// second()
	// third()
	// fourth()
	// fourtDifferentWay()
}

func first() {
	var userAge int
	fmt.Println("Please write your age: ")
	fmt.Scan(&userAge)
	months := userAge * 12
	// days := userAge * 365
	days := int(time.Since(time.Now().AddDate(-userAge, 0, 0)).Hours() / 24)
	fmt.Printf("You have lived %v months / %v days", months, days)
}

func second() {
	var userAge float32
	fmt.Println("Please write your age: ")
	fmt.Scan(&userAge)
	wenusAge := (userAge * 365.26) / 224.7
	marsAge := (userAge * 365.26) / 686.98
	fmt.Printf("You would have %v years on Wenus and %v years on Mars", wenusAge, marsAge)
}

func third() {
	var a, b, c float64

	fmt.Println("Podaj współczynniki a, b i c trójmianu kwadratowego:")
	fmt.Scan(&a, &b, &c)

	delta := b*b - 4*a*c

	if delta < 0 {
		fmt.Println("Delta jest ujemna, brak pierwiastków rzeczywistych.")
	} else if delta == 0 {
		x := -b / (2 * a)
		fmt.Printf("Trójmian ma jeden podwójny pierwiastek x = %v\n", x)
	} else {
		x1 := (-b - math.Sqrt(delta)) / (2 * a)
		x2 := (-b + math.Sqrt(delta)) / (2 * a)
		fmt.Printf("Trójmian ma dwa pierwiastki: x1 = %v i x2 = %v\n", x1, x2)
	}
}

func fourth() {
	rand.Seed(time.Now().UnixNano())

	random := rand.Intn(100) + 1

	fmt.Println("Guess a number between 1 and 100.")
	for {
		var guess int
		fmt.Print("Give your guess or exit -1 ")
		_, err := fmt.Scan(&guess)

		if err != nil {
			fmt.Println("Wrong data!")
			break
		}

		if guess == -1 {
			fmt.Println("Goodbye!")
			break
		}

		if guess == random {
			fmt.Println("Cool! You guessed!")
			break
		} else if guess < random {
			fmt.Println("Given number is too small")
		} else {
			fmt.Println("Given number is too large")
		}
	}
}

func fourtDifferentWay() {
	rand.Seed(time.Now().UnixNano())
	randomNumber := rand.Intn(100)

	var userNumber int
	fmt.Println("Guess the number between 0 and 100")
	fmt.Scan(&userNumber)

	for userNumber != randomNumber {
		if userNumber > randomNumber {
			fmt.Println("Your number is too big")
		} else {
			fmt.Println("Your number is too small")
		}
		fmt.Println("Guess again")
		fmt.Scan(&userNumber)
	}
}

func additionalTask() {
	// TODO
}
