package affine

import (
	"fmt"
	"log"
	"strconv"
	"strings"

	"github.com/piotrd22/studia-archiwum/Cryptography/lab01/files"
)

var tableOfCoprimes = []int{1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25}

func contains(s []int, x int) bool {
	for _, v := range s {
		if v == x {
			return true
		}
	}

	return false
}

func EncryptMessage() string {
	crypto := ""

	message := files.ReadFromFile("data/plain.txt")
	key_string := files.ReadFromFile("data/key.txt")
	keys := strings.Split(key_string, " ")

	a, err := strconv.Atoi(keys[0])
	b, err2 := strconv.Atoi(keys[1])

	if err != nil || err2 != nil {
		log.Fatal(err)
	}

	if a < 1 || a > 25 || b < 1 || b > 25 {
		log.Fatal("Invalid key")
	}

	if !contains(tableOfCoprimes, a) {
		log.Fatal("A has to be Coprime with 26!")
	}

	a = a % 26
	b = b % 26

	message_with_no_spaces := strings.ReplaceAll(message, " ", "\n")
	msg := []rune(message_with_no_spaces)

	for i := 0; i < len(msg); i++ {
		intMsg := int(msg[i])
		if intMsg > 64 && intMsg < 91 {
			intMsg = a*intMsg + b
			for intMsg > 90 {
				intMsg -= 26
			}
		}

		if intMsg > 96 && intMsg < 123 {
			intMsg = a*intMsg + b
			for intMsg > 122 {
				intMsg -= 26
			}
		}
		crypto += string(rune(intMsg))
	}

	crypto_to_save := strings.ReplaceAll(crypto, "\n", " ")
	files.WriteToFile("data/crypto.txt", crypto_to_save)

	return crypto_to_save
}

func findCoprime(x int) int {
	number := 0
	for (number*x)%26 != 1 {
		number++
	}

	return number
}

func DecryptMessage() string {
	message := ""

	cryptogram := files.ReadFromFile("data/crypto.txt")
	crypto := strings.ReplaceAll(cryptogram, " ", "\n")

	key_string := files.ReadFromFile("data/key.txt")
	keys := strings.Split(key_string, " ")

	a, err := strconv.Atoi(keys[0])
	b, err2 := strconv.Atoi(keys[1])

	if err != nil || err2 != nil {
		log.Fatal(err)
	}

	if a < 1 || a > 25 || b < 1 || b > 25 {
		log.Fatal("Invalid key")
	}

	if !contains(tableOfCoprimes, a) {
		log.Fatal("A has to be Coprime with 26!")
	}

	a = findCoprime(a)
	b = b % 26

	msg := []rune(crypto)

	for i := 0; i < len(msg); i++ {
		intMsg := int(msg[i])
		if intMsg > 64 && intMsg < 91 {
			intMsg = a * (intMsg - b)

			if intMsg < 65 {
				intMsg += 26
			} else {
				for intMsg > 90 {
					intMsg -= 26
				}
			}
		}

		if intMsg > 96 && intMsg < 123 {
			intMsg = a * (intMsg - b)

			if intMsg < 97 {
				intMsg += 26
			} else {
				for intMsg > 122 {
					intMsg -= 26
				}
			}
		}
		message += string(rune(intMsg))
	}

	message_to_save := strings.ReplaceAll(message, "\n", " ")
	files.WriteToFile("data/decrypt.txt", message_to_save)

	return message_to_save
}

func FindKey() string {
	crypto := files.ReadFromFile("data/crypto.txt")
	crypto = strings.ReplaceAll(crypto, "\n", " ")
	crypto = strings.ReplaceAll(crypto, " ", "")

	extra := files.ReadFromFile("data/extra.txt")
	extra = strings.ReplaceAll(extra, "\n", " ")
	extra = strings.ReplaceAll(extra, " ", "")

	var keyA string
	var keyB string

	message := ""

	msg := []rune(crypto)

	for _, a := range tableOfCoprimes {

		for b := 1; b <= 26; b++ {

			for i := 0; i < len(msg); i++ {
				intMsg := int(msg[i])
				if intMsg > 64 && intMsg < 91 {
					intMsg = a * (intMsg - b)

					if intMsg < 65 {
						intMsg += 26
					} else {
						for intMsg > 90 {
							intMsg -= 26
						}
					}
				}

				if intMsg > 96 && intMsg < 123 {
					intMsg = a * (intMsg - b)

					if intMsg < 97 {
						intMsg += 26
					} else {
						for intMsg > 122 {
							intMsg -= 26
						}
					}
				}
				message += string(rune(intMsg))
			}

			if message == extra {
				keyA = strconv.Itoa(findCoprime(a))
				keyB = strconv.Itoa(b)

				keysString := fmt.Sprintf("%v %v\n", keyA, keyB)

				files.WriteToFile("data/key-new.txt", keysString)

				return keysString
			}

			message = ""
		}
	}

	return "Key was not found"
}

func AllCodes() string {
	message := ""

	files.WriteToFile("data/decrypt.txt", "")

	cryptogram := files.ReadFromFile("data/crypto.txt")
	crypto := strings.ReplaceAll(cryptogram, " ", "\n")

	msg := []rune(crypto)

	for _, a := range tableOfCoprimes {

		for b := 1; b <= 26; b++ {

			for i := 0; i < len(msg); i++ {
				intMsg := int(msg[i])
				if intMsg > 64 && intMsg < 91 {
					intMsg = a * (intMsg - b)

					if intMsg < 65 {
						intMsg += 26
					} else {
						for intMsg > 90 {
							intMsg -= 26
						}
					}
				}

				if intMsg > 96 && intMsg < 123 {
					intMsg = a * (intMsg - b)

					if intMsg < 97 {
						intMsg += 26
					} else {
						for intMsg > 122 {
							intMsg -= 26
						}
					}
				}
				message += string(rune(intMsg))
			}

			message_to_save := strings.ReplaceAll(message, "\n", " ")
			files.AppendToFile("data/decrypt.txt", fmt.Sprintf("%v\n", message_to_save))

			message = ""
		}
	}

	return "All done"
}
