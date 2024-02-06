import sysv_ipc
import time

KEY = 12
NULL_CHAR = '\0'

player = 1
game_counter = 2
player_1_win = 0
player_2_win = 0
game = '  ;  ;  '

def create_or_get_resources():
    global player
    try:
        sem1 = sysv_ipc.Semaphore(KEY, sysv_ipc.IPC_CREX, 0o700, 0)
        sem2 = sysv_ipc.Semaphore(KEY + 1, sysv_ipc.IPC_CREX, 0o700, 1)
        mem = sysv_ipc.SharedMemory(KEY, sysv_ipc.IPC_CREX)
        player = 1
    except sysv_ipc.ExistentialError:
        sem1 = sysv_ipc.Semaphore(KEY + 1)
        sem2 = sysv_ipc.Semaphore(KEY)
        mem = sysv_ipc.SharedMemory(KEY)
        player = 2
        time.sleep(0.1)
    return sem1, sem2, mem

def read_game(mem):
    s = mem.read()
    s = s.decode()
    i = s.find(NULL_CHAR)
    if i != -1:
        s = s[:i]
    return s

def save_game(mem, s):
    s += NULL_CHAR
    s = s.encode()
    mem.write(s)

def print_game():
    print(game.split(';')[game_counter + 1] + "\n")

def print_final_win():
    winner = 'Player 1' if player_1_win > player_2_win else 'Player 2'
    print(f'{winner} won\nTHE END')

def check(game_number):
    split_game = game.split(';')
    if split_game[game_number][0] != ' ' and split_game[game_number][1] != ' ':
        return 2 if split_game[game_number][0] == split_game[game_number][1] else 1
    return 0

def make_move(letter, player, game_copy):
    split_game = game_copy.split(';')
    split_game[game_counter] = (
        split_game[game_counter][:player - 1] + letter + split_game[game_counter][player:]
    )
    return ';'.join(split_game)

sem1, sem2, mem = create_or_get_resources()

if player == 1:
    save_game(mem, game) 

while game_counter >= -1:
    try:
        sem2.acquire()
    except:
        print_final_win()
        break

    game = read_game(mem)

    if game_counter < 2 and player == 1:
        print_game()

    if game_counter != 2 and player == 1:
        round_winner = check(game_counter + 1)
        if round_winner == 2:
            player_2_win += 1
        else:
            player_1_win += 1
        print(f"\n Player {round_winner} won this round. Actual score is:"
              f"\n Player 1: {player_1_win} \n Player 2: {player_2_win} \n")

    if game_counter == -1:
        print_final_win()
        break

    valid_letters = ['A', 'B', 'C']
    while True:
        print('Guess one of three letters A, B, or C:')
        letter = input(f"Player {player}: ").upper()
        if letter in valid_letters:
            break
        else:
            print('Invalid input. Please enter A, B, or C.')

    game = read_game(mem)
    game = make_move(letter, player, game)
    save_game(mem, game)

    if player == 2:
        round_winner = check(game_counter)
        if round_winner == 2:
            player_2_win += 1
        else:
            player_1_win += 1
        print(f"\n Player {round_winner} won this round. Actual score is:"
              f"\n Player 1: {player_1_win} \n Player 2: {player_2_win} \n")

    game_counter -= 1

    if player == 2:
        print_game()

    try:
        sem1.release()
    except:
        print_final_win()
        break

try:
    sysv_ipc.remove_shared_memory(mem.id)
    sysv_ipc.remove_semaphore(sem1.id)
    sysv_ipc.remove_semaphore(sem2.id)
except sysv_ipc.ExistentialError:
    pass
