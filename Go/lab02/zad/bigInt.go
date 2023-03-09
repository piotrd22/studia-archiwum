package zad

import (
	"fmt"
	"math/big"
	"strconv"
	"strings"
)

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

func Evaluate(asciiValues []int) {
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
			small := EvaluateSmall(n, evals)
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
