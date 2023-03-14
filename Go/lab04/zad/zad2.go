package zad

import "fmt"

func Zad2() {
	var arr [20]float64
	var arr2 [20]float64
	var suma [20]float64

	for i := 0; i < len(arr); i++ {
		arr[i] = 2.0
		arr2[i] = 3.0

		suma[i] = arr[i] * arr2[i]
	}

	fmt.Println(suma)
}
