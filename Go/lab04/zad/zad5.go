package zad

import (
	"fmt"
	"log"
)

func multiplyMatrices(matrixA [][]int, matrixB [][]int) ([][]int, error) {
	rowsA := len(matrixA)
	colsB := len(matrixB[0])

	if len(matrixB) != len(matrixA[0]) {
		return nil, fmt.Errorf("number of columns of matrix A must match number of rows of matrix B")
	}

	result := make([][]int, rowsA)
	for i := range result {
		result[i] = make([]int, colsB)
	}

	for i := 0; i < rowsA; i++ {
		for j := 0; j < colsB; j++ {
			for k := 0; k < len(matrixB); k++ {
				result[i][j] += matrixA[i][k] * matrixB[k][j]
			}
		}
	}

	return result, nil
}

func printMatrix(matrix [][]int) {
	for i := 0; i < len(matrix); i++ {
		for j := 0; j < len(matrix[0]); j++ {
			fmt.Printf("%d ", matrix[i][j])
		}
		fmt.Println()
	}
}

func Equal(matrixA [][]int, matrixB [][]int) bool {
	if len(matrixA) != len(matrixB) {
		return false
	}
	for i := 0; i < len(matrixA); i++ {
		for j := 0; j < len(matrixA[i]); j++ {
			if matrixA[i][j] != matrixB[i][j] {
				return false
			}
		}
	}
	return true

}

func Zad5(matrixA [][]int, matrixB [][]int) {
	result, err := multiplyMatrices(matrixA, matrixB)

	if err != nil {
		log.Fatal(err)
	}

	printMatrix(result)
}
