package main

import (
	f "flag"
	t "fmt"
	m "math"
)

type R struct {
	A, B, C float64
}

func main() {
	a := f.Float64("a", 0, "a")
	b := f.Float64("b", 0, "b")
	c := f.Float64("c", 0, "c")
	f.Parse()

	if *a == 0 {
		panic("")
	}

	r := R{*a, *b, *c}
	t.Println(r.E())
}

func (r *R) E() (float64, float64, bool) {
	d := r.B*r.B - 4*r.A*r.C

	if d < 0 {
		return 0, 0, false
	}

	return (-r.B + m.Sqrt(d)) / (2 * r.A), (-r.B - m.Sqrt(d)) / (2 * r.A), true
}
