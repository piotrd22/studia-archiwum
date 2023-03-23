// Copyrights
// Piotr Damrych

package main

import (
	"fmt"
	"log"
	"os"

	"github.com/piotrd22/studia-archiwum/Cryptography/lab01/affine"
	"github.com/piotrd22/studia-archiwum/Cryptography/lab01/caesar"
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

	switch cipher {
	case "-c":
		fmt.Println("Caesar!")

		switch action {
		case "-e":
			caesar.EncryptMessage()
			fmt.Println("All done")

		case "-d":
			decrypt := caesar.DecryptMessage()
			fmt.Println("Dectypted message is " + decrypt)

		case "-j":
			key := caesar.FindKey()
			fmt.Println("Key is " + key)

		case "-k":
			info := caesar.AllCodes()
			fmt.Println(info)

		default:
			log.Fatal("Invalid flag")
		}

	case "-a":
		fmt.Println("Affine!")

		switch action {
		case "-e":
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
