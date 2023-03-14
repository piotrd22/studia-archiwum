package gameoflife

import (
	"fmt"
	"math/rand"
	"os"
	"os/exec"
	"time"
)

const (
	width  = 40
	height = 40
)

func Play() {
	board := make([][]bool, height)
	for i := range board {
		board[i] = make([]bool, width)
	}

	// set up initial cells randomly
	rand.Seed(time.Now().UnixNano())
	for i := range board {
		for j := range board[i] {
			if rand.Intn(100) < 20 {
				board[i][j] = true
			}
		}
	}

	for {
		printBoard(board)
		time.Sleep(100 * time.Millisecond)
		board = nextGeneration(board)
		clearScreen()
	}
}

func nextGeneration(board [][]bool) [][]bool {
	newBoard := make([][]bool, len(board))
	for i := range newBoard {
		newBoard[i] = make([]bool, len(board[i]))
	}

	for i := range board {
		for j := range board[i] {
			count := countNeighbors(board, i, j)

			if board[i][j] && (count == 2 || count == 3) {
				newBoard[i][j] = true
			}

			if !board[i][j] && count == 3 {
				newBoard[i][j] = true
			}
		}
	}

	return newBoard
}

func countNeighbors(board [][]bool, row, col int) int {
	count := 0
	for i := row - 1; i <= row+1; i++ {
		for j := col - 1; j <= col+1; j++ {
			if i == row && j == col {
				continue
			}
			if i < 0 || i >= len(board) || j < 0 || j >= len(board[i]) {
				continue
			}
			if board[i][j] {
				count++
			}
		}
	}
	return count
}

func printBoard(board [][]bool) {
	for i := range board {
		for j := range board[i] {
			if board[i][j] {
				fmt.Print("\u25A0 ")
			} else {
				fmt.Print("  ")
			}
		}
		fmt.Println()
	}
}

func clearScreen() {
	cmd := exec.Command("clear")
	cmd.Stdout = os.Stdout
	cmd.Run()
}
