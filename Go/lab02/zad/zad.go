package zad

import (
	"fmt"
	"math/big"
	"strconv"
	"strings"
)

func factorial(n int) *big.Int {
	if n < 0 {
		return big.NewInt(0)
	}
	if n == 0 {
		return big.NewInt(1)
	}
	bigN := big.NewInt(int64(n))
	return bigN.Mul(bigN, factorial(n-1))
}

func EvaluateBig() {
	asciiValues := []int{112, 105, 111, 100, 97, 109} // kod ASCII literek w nicku "piodam"

	var limit big.Int
	limit.Exp(big.NewInt(10), big.NewInt(800), nil)

	n := 0
	for {
		n++
		silnia := factorial(n)
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
