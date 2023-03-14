package zad

import (
	"testing"
)

func TestEqual(t *testing.T) {
	matrix := [][]int{
		{0, 1, 2},
		{3, 4, 5},
		{6, 7, 8},
	}

	matrix2 := [][]int{
		{0, 1, 2},
		{3, 4, 5},
		{6, 7, 8},
	}

	if Equal(matrix, matrix2) != true {
		t.Errorf("should be true")
	}
}

func TestZad5(t *testing.T) {

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

	matrix3 := [][]int{
		{3, 6, 9},
		{30, 42, 54},
		{57, 78, 99},
	}

	got, err := multiplyMatrices(matrix, matrix2)

	if err != nil {
		t.Error(err)
	}

	want := matrix3

	if !Equal(got, want) {
		t.Errorf("should be equal")
	}
}
