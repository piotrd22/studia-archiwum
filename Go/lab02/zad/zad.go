package zad

import (
	"fmt"
	"math"
	"math/big"
	"strconv"
	"strings"
)

func fibonacci(n int, evals map[int]int) int {

	evals[n]++

	// if _, isOk := evals[n]; isOk {  // tells me i dont have to do it
	// 	evals[n]++
	// } else {
	// 	evals[n] = 1
	// }

	if n == 0 {
		return n
	} else if n == 1 {
		return n
	}

	return fibonacci(n-1, evals) + fibonacci(n-2, evals)
}

func evalsInFibonacci(n int) map[int]int {
	evals := make(map[int]int)
	fibonacci(n, evals)
	return evals
}

func evaluateSmall(n int, evals map[int]int) int {
	minDiff := float64(n - evals[len(evals)-1])
	small := len(evals) - 1

	for key, value := range evals {
		diff := n - value
		if math.Abs(float64(diff)) < minDiff {
			minDiff = math.Abs(float64(diff))
			small = key
		}
	}

	return small
}

func factorialWithCache(n int, cache *big.Int) *big.Int {
	if n < 0 {
		return big.NewInt(0)
	}
	if n == 0 {
		return big.NewInt(1)
	}
	bigN := big.NewInt(int64(n))
	return bigN.Mul(bigN, cache)
}

func evaluate(asciiValues []int) {
	var limit big.Int
	limit.Exp(big.NewInt(10), big.NewInt(800), nil)

	n := 0
	cache := big.NewInt(1)

	for {
		n++
		silnia := factorialWithCache(n, cache)
		cache = silnia
		asciiFound := true

		for _, asciiVal := range asciiValues {
			asciiStr := strconv.Itoa(asciiVal)
			if !strings.Contains(silnia.String(), asciiStr) {
				asciiFound = false
				break
			}
		}

		if asciiFound {
			fmt.Printf("Silna liczba: %d\n", n)

			evals := evalsInFibonacci(30)
			small := evaluateSmall(n, evals)
			fmt.Printf("Slaba liczba: %d\n", small)
			break
		}

		if silnia.Cmp(&limit) < 0 {
			continue
		} else {
			fmt.Printf("Silna liczba jest za silna")
			break
		}
	}
}

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

	evaluate(asciiValues)
}
