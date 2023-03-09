package zad

import (
	"fmt"
	"strings"
)

func EvaluateBigAndSmall() {
	// asciiValues := []int{112, 105, 111, 100, 97, 109} // kod ASCII literek w nicku "piodam"
	var name string
	fmt.Println("Please write your name: ")
	fmt.Scan(&name)

	var surname string
	fmt.Println("Please write your surname: ")
	fmt.Scan(&surname)

	nick := strings.ToLower(name[0:3] + surname[0:3])

	byteValues := []byte(nick)
	var asciiValues []int

	for i := 0; i < len(byteValues); i++ {
		asciiValues = append(asciiValues, int(byteValues[i]))
	}

	Evaluate(asciiValues)
}
