//Daty w tekście maja postać YYYY-MM-DD (rok- miesiac- dzien). Wypisać wszystkie poprawne daty.
//czyli: po wyróznieniu wszytskicgh fragmentów, które mogą być datami sprawdizc ich poprawnośc (np. czy właściwa jest liczba dni w miesiącu)

import java.text.ParseException
import java.text.SimpleDateFormat

def inputText = "2017-07-29 tekst tekst 1999-35-01 tutaj było źle, 1991-12-33, tu też, tekst tekst a tu dobrze 2000-01-01"

def matches = inputText =~ /[0-9]{4}-[0-9]{2}-[0-9]{2}/
def validDates = matches.findAll({
    def valid = true

    try {
        def dateFormat = new SimpleDateFormat("yyyy-MM-dd")
        dateFormat.setLenient(false)
        dateFormat.parse(it)
    } catch(ParseException e) {
        valid = false
    }

    return valid
})

println validDates