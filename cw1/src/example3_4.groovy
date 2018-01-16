// a) Napisać wyrażenie regularne, za pomoca którego mozna odnaleźć w tekście dowolne liczby rzeczywiste.
// b) zastosować je do przetwarzania tekstu zawierającego (posród innych) napisy w postaci liczba PLN, w taki sposób, aby zamienić te fragmenty na liczba EUR (po aktualnym kursie)

import groovy.json.JsonSlurper

// a)
def realNumberRegex = /\-?[0-9]+\.[0-9]+|\-?[0-9]+/
def inputText = "Tekst, tutaj liczba 0.12, tutaj inna 3.14, a tutaj skomplikowana ujemna -112.12434"
def matches = inputText =~ realNumberRegex
matches.each {
    println it
}

// b)

def inputPricesText = "Jajka 5.12 PLN, maslo 10.99 PLN lub maslo 2 EUR lub 2.51 EUR za smietane"
def plnPriceRegex = /(\-?[0-9]+\.[0-9]+|\-?[0-9]+)\ [P][L][N]/

def euro = (new JsonSlurper()).parse(new URL('http://api.nbp.pl/api/exchangerates/rates/A/EUR?format=json'))["rates"][0]["mid"]
def plnToEuro = 1/euro

println inputPricesText
def updatedPricesText = inputPricesText.replaceAll(plnPriceRegex, {
    def price = (it[0] =~ realNumberRegex)[0] as BigDecimal
    return String.format("%.2f", price * plnToEuro) + " EUR"
})
println updatedPricesText

