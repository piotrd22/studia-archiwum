package files

import (
	"bufio"
	"log"
	"os"
)

func ReadFromFile(filename string) string {
	file, err := os.Open(filename)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	str := ""
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		str += line
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}

	return str
}

func WriteToFile(filename string, text string) {
	_, err := os.Stat(filename)
	if os.IsNotExist(err) {
		fileCreate, err := os.Create(filename)
		if err != nil {
			log.Fatal(err)
		}
		defer fileCreate.Close()
	}

	file, err := os.OpenFile(filename, os.O_WRONLY|os.O_TRUNC, 0644)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	textWriter := bufio.NewWriter(file)

	_, err = textWriter.WriteString(text)
	if err != nil {
		log.Fatal(err)
	}

	textWriter.Flush()
	defer file.Close()
}

func AppendToFile(filename string, text string) {
	_, err := os.Stat(filename)
	if os.IsNotExist(err) {
		fileCreate, err := os.Create(filename)
		if err != nil {
			log.Fatal(err)
		}
		defer fileCreate.Close()
	}

	file, err := os.OpenFile(filename, os.O_APPEND|os.O_WRONLY, 0644)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	textWriter := bufio.NewWriter(file)

	_, err = textWriter.WriteString(text)
	if err != nil {
		log.Fatal(err)
	}

	textWriter.Flush()
	defer file.Close()
}
