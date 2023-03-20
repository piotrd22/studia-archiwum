package cezar

import (
	"fmt"
	"log"
	"strconv"
	"strings"

	"github.com/piotrd22/studia-archiwum/Cryptography/lab01/files"
)

func EncryptMessage() string {
	crypto := ""

	message := files.ReadFromFile("data/plain.txt")
	key_string := files.ReadFromFile("data/key.txt")

	key, err := strconv.Atoi(key_string)
	if err != nil {
		log.Fatal(err)
	}

	if key < 0 || key > 25 {
		log.Fatal("Wrong key!")
	}

	key = key % 26

	message_with_no_spaces := strings.ReplaceAll(message, " ", "\n")
	msg := []rune(message_with_no_spaces)

	for i := 0; i < len(msg); i++ {
		intMsg := int(msg[i])
		if intMsg > 64 && intMsg < 91 {
			intMsg += key
			if intMsg > 90 {
				intMsg -= 26
			}
		}

		if intMsg > 96 && intMsg < 123 {
			intMsg += key
			if intMsg > 122 {
				intMsg -= 26
			}
		}
		crypto += string(rune(intMsg))
	}

	crypto_to_save := strings.ReplaceAll(crypto, "\n", " ")
	files.WriteToFile("data/crypto.txt", crypto_to_save)

	return crypto_to_save
}

func DecryptMessage() string {
	message := ""

	cryptogram := files.ReadFromFile("data/crypto.txt")
	crypto := strings.ReplaceAll(cryptogram, " ", "\n")

	key_string := files.ReadFromFile("data/key.txt")

	key, err := strconv.Atoi(key_string)
	if err != nil {
		log.Fatal(err)
	}

	if key < 0 || key > 25 {
		log.Fatal("Wrong key!")
	}

	key = key % 26

	msg := []rune(crypto)

	for i := 0; i < len(msg); i++ {
		intMsg := int(msg[i])
		if intMsg > 64 && intMsg < 91 {
			intMsg -= key
			if intMsg < 65 {
				intMsg += 26
			}
		}

		if intMsg > 96 && intMsg < 123 {
			intMsg -= key
			if intMsg < 97 {
				intMsg += 26
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

	var found_key string

	for i, letter := range crypto {
		if (letter > 64 && letter < 91) || (letter > 96 && letter < 123) {
			key := (int(letter) - int(extra[i])) % 26

			if key < 0 {
				key = 26 + key
			}

			found_key = strconv.Itoa(key)

			files.WriteToFile("data/key-found.txt", found_key)

			return found_key
		}
	}

	found_key = "Key was not found"

	return found_key
}

func AllCodes() string {
	message := ""

	files.WriteToFile("data/decrypt.txt", "")

	cryptogram := files.ReadFromFile("data/crypto.txt")
	crypto := strings.ReplaceAll(cryptogram, " ", "\n")

	for key := 1; key < 26; key++ {
		key = key % 26

		msg := []rune(crypto)

		for i := 0; i < len(msg); i++ {
			intMsg := int(msg[i])
			if intMsg > 64 && intMsg < 91 {
				intMsg -= key
				if intMsg < 65 {
					intMsg += 26
				}
			}

			if intMsg > 96 && intMsg < 123 {
				intMsg -= key
				if intMsg < 97 {
					intMsg += 26
				}
			}
			message += string(rune(intMsg))
		}

		message_to_save := strings.ReplaceAll(message, "\n", " ")
		files.AppendToFile("data/decrypt.txt", fmt.Sprintf("%v\n", message_to_save))

		message = ""
	}

	return "All done"
}
