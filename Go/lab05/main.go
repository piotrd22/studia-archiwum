package main

import (
	"flag"
	"fmt"
	"math"
)

type Roots struct {
	A, B, C float64
}

func main() {
	a := flag.Float64("a", 0, "a")
	b := flag.Float64("b", 0, "b")
	c := flag.Float64("c", 0, "c")
	flag.Parse()

	if *a == 0 {
		panic("Cannot divide by 0!")
	}

	roots := Roots{*a, *b, *c}
	fmt.Println(roots.Evaluate())
}

func (r *Roots) Evaluate() (float64, float64, bool) {
	delta := r.B*r.B - 4*r.A*r.C

	if delta < 0 {
		return 0, 0, false
	}

	x1 := (-r.B + math.Sqrt(delta)) / (2 * r.A)
	x2 := (-r.B - math.Sqrt(delta)) / (2 * r.A)

	return x1, x2, true
}
