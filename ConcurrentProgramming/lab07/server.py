import socket


SERVER_IP="127.0.0.1"
SERVER_PORT=5001
BUF_SIZE=1024

SERVER_ADDRESS = (SERVER_IP, SERVER_PORT)

# Creating a server socket and binding with adress: 127.0.0.1:5001
SERVER_SOCKET = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
SERVER_SOCKET.bind(SERVER_ADDRESS)

print(f"Serwer działa na: {SERVER_IP}:{SERVER_PORT}")

def initialize_two_players():
    player_nickname_1, player_address_1 = SERVER_SOCKET.recvfrom(BUF_SIZE)
    player_nickname_1 = player_nickname_1.decode()
    print(f"Gracz {player_nickname_1} dołączył do gry")

    player_nickname_2, player_address_2 = SERVER_SOCKET.recvfrom(BUF_SIZE)
    player_nickname_2 = player_nickname_2.decode()
    print(f"Gracz {player_nickname_2} dołączył do gry")

    player_1 = {
        "nickname": player_nickname_1, 
        "address": player_address_1, 
        "score": 0
    }

    player_2 = {
        "nickname": player_nickname_2, 
        "address": player_address_2, 
        "score": 0
    }

    # Sending opponents nicknames
    SERVER_SOCKET.sendto(player_nickname_2.encode(), player_address_1)
    SERVER_SOCKET.sendto(player_nickname_1.encode(), player_address_2)

    return player_1, player_2


def play_round(player_1, player_2):
    player_1_choice = player_1["choice"]
    player_2_choice = player_2["choice"]

    if player_1_choice == player_2_choice:
        return None
    elif (
        (player_1_choice == "rock" and player_2_choice == "scissors")
        or (player_1_choice == "paper" and player_2_choice == "rock")
        or (player_1_choice == "scissors" and player_2_choice == "paper")
    ):
        return player_1
    else:
        return player_2
    

def display_scores(round_counter, message, player_1, player_2):
    round_string = f"Wynik rundy nr {round_counter}"
    print("\n" + round_string)
    print(message)
    print(f"{player_1['nickname']} ({player_1['score']}) : ({player_2['score']}) {player_2['nickname']}\n")


try:
    while True:
        round_counter = 1
        player_1, player_2 = initialize_two_players()

        # Game is on
        while True:
            # Receiving player's choices
            player_choice_x, player_address_x = SERVER_SOCKET.recvfrom(BUF_SIZE)
            player_choice_y, player_address_y = SERVER_SOCKET.recvfrom(BUF_SIZE)

            # Assigning choices to the correst player (same address)
            if player_1["address"] == player_address_x:
                player_1["choice"] = player_choice_x.decode()
                player_2["choice"] = player_choice_y.decode()
            else:
                player_1["choice"] = player_choice_y.decode()
                player_2["choice"] = player_choice_x.decode()

            # If one of the players left
            if "exit" in [player_1["choice"], player_2["choice"]]:
                SERVER_SOCKET.sendto("exit".encode(), player_1["address"])
                SERVER_SOCKET.sendto("exit".encode(), player_2["address"])
                print("\nGra skończona. Finalne wyniki:")
                print(f"{player_1['nickname']} ({player_1['score']}) : ({player_2['score']}) {player_2['nickname']}")
                break
            
            # If everything is correct, we play the round and announce the winner
            winner = play_round(player_1, player_2)
            message = ""
            if winner is None:
                message = f"\nRemis.\n\nGracz {player_1['nickname']} wybrał {player_1['choice']}\nGracz {player_2['nickname']} wybrał {player_2['choice']}"
            else:
                message = f"\nGracz {winner['nickname']} wygrał tą rundę.\n\nGracz {player_1['nickname']} wybrał {player_1['choice']}\nGracz {player_2['nickname']} wybrał {player_2['choice']}"
                winner["score"] += 1
            
            display_scores(round_counter, message, player_1, player_2)

            message = message.encode()
            SERVER_SOCKET.sendto(message, player_1["address"])
            SERVER_SOCKET.sendto(message, player_2["address"])
            
            round_counter += 1

        print("\nCzekamy na graczy")
        
        
except KeyboardInterrupt:
    print("\nSerwer się zamyka")

# Closing the server socket
SERVER_SOCKET.close()