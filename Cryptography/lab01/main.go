package main

import (
	"fmt"
	"log"
	"os"
	"strconv"

	"github.com/piotrd22/studia-archiwum/Cryptography/lab01/affine"
	"github.com/piotrd22/studia-archiwum/Cryptography/lab01/cezar"
	"github.com/piotrd22/studia-archiwum/Cryptography/lab01/files"
)

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
	action := os.Args[2]

	var key int
	var a, b int

	switch cipher {
	case "-c":
		fmt.Println("Cezar!")

		switch action {
		case "-e":
			fmt.Print("Enter the key (an integer from 1 to 25): ")
			fmt.Scanln(&key)

			if key < 1 || key > 25 {
				log.Fatal("Invalid key")
			}

			files.WriteToFile("data/key.txt", strconv.Itoa(key))

			cezar.EncryptMessage()
			fmt.Println("All done")

		case "-d":
			decrypt := cezar.DecryptMessage()
			fmt.Println("Dectypted message is " + decrypt)

		case "-j":
			key := cezar.FindKey()
			fmt.Println("Key is " + key)

		case "-k":
			info := cezar.AllCodes()
			fmt.Println(info)

		default:
			log.Fatal("Invalid flag")
		}

	case "-a":
		fmt.Println("Affine!")

		switch action {
		case "-e":
			fmt.Println("Enter the key (two integers a and b separated by a space): ")
			fmt.Scanln(&a, &b)

			if a < 1 || a > 25 || b < 1 || b > 25 {
				log.Fatal("Invalid key")
			}

			files.WriteToFile("data/key.txt", fmt.Sprintf("%v %v", a, b))

			affine.EncryptMessage()
			fmt.Println("All done")

		case "-d":
			decrypt := affine.DecryptMessage()
			fmt.Println("Dectypted message is " + decrypt)

		case "-j":
			key := affine.FindKey()
			fmt.Println("Keys are " + key)

		case "-k":
			info := affine.AllCodes()
			fmt.Println(info)

		default:
			log.Fatal("Invalid flag")
		}

	default:
		log.Fatal("Invalid flag")
	}
}
