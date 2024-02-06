import socket

SERVER_IP="127.0.0.1"
SERVER_PORT=5001
BUF_SIZE=1024

CHOICES =   {
    1: "rock",
    2: "paper",
    3: "scissors"
}

SERVER_ADDRESS = (SERVER_IP, SERVER_PORT)

# Creating a client socket
CLIENT_SOCKET = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)


def display_options():
    print("\n1: Kamień\n" +
          "2: Papier\n" +
          "3: Nożyczki\n" +
          "0: Wyjście z programu\n")


try:
    player_nickname = input("Wpisz nazwę użytkownika: ")
    CLIENT_SOCKET.sendto(player_nickname.encode(), SERVER_ADDRESS)
    opponent_nickname, _ = CLIENT_SOCKET.recvfrom(BUF_SIZE)

    print(f"Rywal: {opponent_nickname.decode()}")

    # Game
    while True:
        display_options()
        choice = input("Wybierz ruch: ")

        if choice == "0":
            CLIENT_SOCKET.sendto("exit".encode(), SERVER_ADDRESS)
            print("Wychodzę")
            break

        if choice.isdigit() and 0 < int(choice) <= len(CHOICES):
            # Changes the picked number to rock, paper or scissors
            player_choice = CHOICES[int(choice)]
            CLIENT_SOCKET.sendto(player_choice.encode(), SERVER_ADDRESS)

            server_response, _ = CLIENT_SOCKET.recvfrom(BUF_SIZE)
            server_response = server_response.decode()
            if server_response == "exit":
                print("Przeciwnik opuścił grę. Wychodzę.")
                break
            else:
                print(server_response)
        else:
            print("Niepoprawny input.")
        

except KeyboardInterrupt:
    CLIENT_SOCKET.sendto("exit".encode(), SERVER_ADDRESS)
    print("\nKlient wychodzi")

# Closing the client socket
CLIENT_SOCKET.close()
