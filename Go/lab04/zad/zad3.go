package zad

import "fmt"

func Zad3() {
	matrix := [][]int{
		{0, 1, 2},
		{3, 4, 5},
		{6, 7, 8},
	}

	for _, v := range matrix {
		for j := 0; j < len(v); j++ {
			fmt.Print(v[j], " ")
		}
		fmt.Println()
	}
}
