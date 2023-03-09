package collatz

import (
	"fmt"
	"math"
)

func even(n float64) bool {
	return math.Mod(n, 2) == 0
}

func odd(n float64) (b bool) {
	return !even(n)
}

var length int = 0

func Collatz(n float64) {
	switch {
	case n == 1:
		length++
		fmt.Printf("Done! The length is: %d\n", length)
	case even(n):
		n = n / 2
		length++
		Collatz(n)
	case odd(n):
		n = n*3 + 1
		length++
		Collatz(n)
	}

	length = 0
}

func Collatzv2(n float64, length int) {
	switch {
	case n == 1:
		length++
		fmt.Printf("Done! The length is: %d\n", length)
	case even(n):
		n = n / 2
		Collatzv2(n, length+1)
	case odd(n):
		n = n*3 + 1
		Collatzv2(n, length+1)
	}
}
