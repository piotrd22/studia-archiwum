package main

import (
	"fmt"
	"github.com/piotrd22/studia-archiwum/Go/lab06/cube"
	"github.com/piotrd22/studia-archiwum/Go/lab06/cylinder"
)

type Area interface {
	BaseSurfaceArea() float64
	SideSurfaceArea() float64
	SurfaceArea() float64
	Volume() float64
}

type Floats interface {
	float64 | float32
}

func main() {
	c, err := cylinder.NewCylinder(10, 12)
	if err != nil {
		panic(err)
	}

	cb, err2 := cube.NewCube(6)
	if err2 != nil {
		panic(err)
	}

	calculateSurfaceArea(c, cb)

	fmt.Println(addFloats(c.SurfaceArea(), cb.SurfaceArea()))
}

func calculateSurfaceArea(a, b Area) {
	fmt.Printf("%v, %v\n", a.SurfaceArea(), b.SurfaceArea())
}

func addFloats[F Floats](a, b F) F {
	return a + b
}
