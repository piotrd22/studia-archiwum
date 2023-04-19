package cube

import (
	"errors"
	"math"
)

type Cube struct {
	a float64
}

func NewCube(a float64) (*Cube, error) {
	if a < 0 {
		return nil, errors.New("a must be positive")
	}
	return &Cube{a: a}, nil
}

func (c Cube) BaseSurfaceArea() float64 {
	return math.Pow(c.a, 2)
}
func (c Cube) SideSurfaceArea() float64 {
	return math.Pow(c.a, 2)
}

func (c Cube) SurfaceArea() float64 {
	return 4*c.SideSurfaceArea() + 2*c.BaseSurfaceArea()
}

func (c Cube) Volume() float64 {
	return math.Pow(c.a, 3)
}
