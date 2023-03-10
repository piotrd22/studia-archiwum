package cezar

import (
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

	message_with_no_spaces := strings.ReplaceAll(message, " ", "")
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

	files.WriteToFile("data/crypto.txt", crypto)

	return crypto
}
