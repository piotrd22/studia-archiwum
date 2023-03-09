package main

import (
	"fmt"
	"log"
	"os"
)

// const alphabet = "abcdefghijklmnopqrstuvwxyz"

func main() {
	if len(os.Args) != 3 {
		fmt.Println("Masz do wyboru dwa szyfry:")
		fmt.Println("-c Cezar")
		fmt.Println("-a Afiniczny")

		fmt.Println("Oraz:")
		fmt.Println("-e (szyfrowanie),")
		fmt.Println("-d (odszyfrowanie),")
		fmt.Println("-j (kryptoanaliza z tekstem jawnym),")
		fmt.Println("-k (kryptoanaliza wyłącznie w oparciu o kryptogram)")
		return
	}

	cipher := os.Args[1]
	// action := os.Args[2]

	var key int
	var a, b int

	switch cipher {
	case "-c":
		fmt.Print("Enter the key (an integer from 1 to 25): ")
		fmt.Scanln(&key)
		if key < 1 || key > 25 {
			log.Fatal("Invalid key")
		}

	case "-a":
		fmt.Print("Enter the key (two integers a and b separated by a space): ")
		fmt.Scanln(&a, &b)

	default:
		log.Fatal("Invalid flag")
	}
}
