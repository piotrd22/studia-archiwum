package zad

import "fmt"

func Zad4() {
	matrix := [][]int{
		{0, 1, 2},
		{3, 4, 5},
		{6, 7, 8},
	}

	matrix2 := [][]int{
		{6, 7, 8},
		{3, 4, 5},
		{0, 1, 2},
	}

	answer := make([][]int, 3)

	for i := 0; i < len(matrix); i++ {
		for j := 0; j < len(matrix[i]); j++ {
			answer[i] = append(answer[i], matrix[i][j]+matrix2[i][j])
		}
	}

	fmt.Println(answer)
}
