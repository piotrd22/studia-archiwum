package main

import (
	"github.com/piotrd22/studia-archiwum/Go/lab02/async"
)

func main() {
	// fmt.Printf("%T\n", 0xFEE)
	// fmt.Println(Fibonacci(11))

	// go async.Loop(0)

	// for i := 0; i < 10; i++ {
	// 	go async.Loop(i)
	// }

	// for i := 0; i < 10; i++ {
	// 	go async.Loop2(i)
	// }

	// var c chan string = make(chan string) // we have to make channel because by default is nil like below

	// var c chan string
	// if c == nil {
	// 	fmt.Println("channel c is nil, going to define it")
	// 	c = make(chan string)
	// 	fmt.Printf("Type of c is %T", c)
	// }

	// go async.Pinger(c)
	// go async.Ponger(c)
	// go async.Printer(c)

	// var input string
	// fmt.Scanln(&input)

	async.SelectDemo()
}

func Fibonacci(x int) int {
	if x == 0 {
		return 0
	} else if x == 1 {
		return 1
	} else {
		return Fibonacci(x-1) + Fibonacci(x-2)
	}
}
