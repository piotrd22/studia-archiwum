package main

import (
	"github.com/piotrd22/studia-archiwum/Go/lab03/collatz"
)

func main() {
	// flag.Parse()
	// n, _ := strconv.ParseFloat(flag.Arg(0), 64)
	// collatz.Collatz(n)
	// collatz.Collatzv2(n, 0)
	// collatz.Collatz(n)
	collatz.FindLongest(4000, 5000)
}
