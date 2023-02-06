from flask import Flask, request, jsonify
from app.RejestrKont import RejestrKont
from app.KontoOsobiste import KontoOsobiste


app = Flask(__name__)


@app.route("/konta/stworz_konto", methods=['POST'])
def stworz_konto():
    dane = request.get_json()
    if RejestrKont.searchUserbyPesel(dane["pesel"]) != None:
        return jsonify("This PESEL is already in our DB"), 400
    else:
        print(f"Request o stworzenie konta z danymi: {dane}")
        konto = KontoOsobiste(dane["imie"], dane["nazwisko"], dane["pesel"])
        RejestrKont.addUser(konto)
        return jsonify("Konto stworzone"), 201


@app.route("/konta/ile_kont", methods=['GET'])
def ile_kont():
    print("Request o ilosc kont")
    return jsonify(RejestrKont.usersCount()), 201


@app.route("/konta/konto/<pesel>", methods=['GET'])
def wyszukaj_konto_z_peselem(pesel):
    konto = RejestrKont.searchUserbyPesel(pesel)
    if konto == None:
        return jsonify("There is no user with this PESEL"), 404
    else:
        return jsonify(imie=konto.imie,  nazwisko=konto.nazwisko, pesel=konto.pesel, saldo=konto.saldo), 200


@app.route("/konta/konto/<pesel>", methods=["PUT"])
def updateAccount(pesel):
    updateData = request.get_json()
    konto = RejestrKont.updateUser(pesel, updateData)
    if konto == "You cannot change pesel!":
        return jsonify(konto), 200
    elif konto == None:
        return jsonify("There is no user with this PESEL"), 400
    else:
        return jsonify(imie=konto.imie,  nazwisko=konto.nazwisko, pesel=konto.pesel, saldo=konto.saldo), 200


@app.route("/konta/konto/<pesel>", methods=["DELETE"])
def deleteAccount(pesel):
    if RejestrKont.searchUserbyPesel(pesel) == None:
        return jsonify("There is no user with this PESEL"), 400
    else:
        res = RejestrKont.deleteUser(pesel)
        return jsonify(res), 200


@app.route("/konta/wyczysc", methods=["DELETE"])
def deleteAllAccounts():
    res = RejestrKont.deleteAllAccounts()
    return jsonify(res), 200
