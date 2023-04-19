package cylinder

import (
	"errors"
	"math"
)

type Cylinder struct {
	radius, height float64
}

func NewCylinder(radius, height float64) (*Cylinder, error) {
	if radius < 0 || height < 0 {
		return nil, errors.New("radius and height must be positive")
	}
	return &Cylinder{radius: radius, height: height}, nil
}

func (c Cylinder) BaseSurfaceArea() float64 {
	return math.Pi * math.Pow(c.radius, 2)
}
func (c Cylinder) SideSurfaceArea() float64 {
	return 2 * math.Pi * c.height * c.radius
}

func (c Cylinder) SurfaceArea() float64 {
	return 2*c.BaseSurfaceArea() + c.SideSurfaceArea()
}

func (c Cylinder) Volume() float64 {
	return c.BaseSurfaceArea() * c.height
}
