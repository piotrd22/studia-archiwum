from unittest_assertions import AssertEqual
import requests
from selenium.webdriver.common.keys import Keys
from behave import *

assert_equal = AssertEqual()
URL = "http://localhost:5000"


@given('Number of accounts in registry equals: "{count}"')
def ile_kont(context, count):
    ile_kont = requests.get(URL + f"/konta/ile_kont")
    assert_equal(ile_kont.json(), int(count))


@when('I create an account using name: "{name}", last name: "{last_name}", pesel: "{pesel}"')
def stworz_konto(context, name, last_name, pesel):
    json_body = {"imie": f"{name}",
                 "nazwisko": f"{last_name}",
                 "pesel": pesel
                 }
    create_resp = requests.post(URL + "/konta/stworz_konto", json=json_body)
    assert_equal(create_resp.status_code, 201)


@then('Number of accounts in registry equals: "{count}"')
def ile_kont(context, count):
    ile_kont = requests.get(URL + f"/konta/ile_kont")
    assert_equal(ile_kont.json(), int(count))


@then('Account with pesel "{pesel}" exists in registry')
def wyszukaj_konto_z_peselem(context, pesel):
    resp = requests.get(URL + f"/konta/konto/{pesel}")
    assert_equal(resp.status_code, 200)


@given('Account with pesel "{pesel}" exists in registry')
def wyszukaj_konto_z_peselem(context, pesel):
    resp = requests.get(URL + f"/konta/konto/{pesel}")
    assert_equal(resp.status_code, 200)


@when('I delete account with pesel: "{pesel}"')
def deleteAccount(context, pesel):
    resp = requests.delete(URL + f"/konta/konto/{pesel}")
    assert_equal(resp.status_code, 200)


@then('Account with pesel "{pesel}" does not exists in registry')
def wyszukaj_konto_z_peselem(context, pesel):
    resp = requests.get(URL + f"/konta/konto/{pesel}")
    assert_equal(resp.status_code, 404)


@when('I update last name in account with pesel "{pesel}" to "{last_name}"')
def updateAccount(context, pesel, last_name):
    json_body = {
        "nazwisko": f"{last_name}"
    }
    resp = requests.put(URL + f"/konta/konto/{pesel}", json=json_body)
    assert_equal(resp.status_code, 200)


@then('Last name in account with pesel "{pesel}" is "{last_name}"')
def sprawdzenie_nazwiska(context, pesel, last_name):
    resp = requests.get(URL + f"/konta/konto/{pesel}")
    assert_equal(resp.status_code, 200)
    assert_equal(resp.json()["nazwisko"], last_name)


@when('I clear the account reagistry')
def usun_wszystkie_konta(context):
    resp = requests.delete(URL + f"/konta/wyczysc")
    assert_equal(resp.status_code, 200)
