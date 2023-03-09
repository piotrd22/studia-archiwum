package collatz

import "fmt"

func collatzWithEvals(n float64, length int, startingN float64, evals map[float64]int) {
	switch {
	case n == 1:
		length++
		evals[startingN] = length
	case even(n):
		n = n / 2
		collatzWithEvals(n, length+1, startingN, evals)
	case odd(n):
		n = n*3 + 1
		collatzWithEvals(n, length+1, startingN, evals)
	}
}

func checkLength(start float64, end float64) map[float64]int {
	if start > end || start <= 0 || end <= 0 {
		panic("Bad arguments")
	}

	evals := make(map[float64]int)
	for n := start; n < end; n++ {
		collatzWithEvals(n, 0, n, evals)
	}
	// fmt.Println(evals)

	return evals
}

func findLongest(evals map[float64]int) int {
	longestLen := 0
	var index float64 = -1
	for key, value := range evals {
		if value > longestLen {
			longestLen = value
			index = key
		}
	}

	return int(index)
}

func FindLongest(start float64, end float64) {
	evals := checkLength(start, end)
	result := findLongest(evals)
	fmt.Printf("Done! The longest Collatz conjecture is for number: %d\n", result)
}
