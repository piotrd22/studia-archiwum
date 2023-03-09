package zad

import "math"

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

func EvaluateSmall(n int, evals map[int]int) int {
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
