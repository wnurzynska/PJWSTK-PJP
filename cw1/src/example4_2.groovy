import groovy.json.JsonSlurper

// Plik MenuPl.txt zawiera menu w postaci:
// nazwa_dania cena
//
// Ceny sa w PLN.
// Na podstawie pliku wejsciowego stworzyc plik MenuEur.txt majacy te sama postac, ale z cenami w EUR.

def euro = (new JsonSlurper()).parse(new URL('http://api.nbp.pl/api/exchangerates/rates/A/EUR?format=json'))["rates"][0]["mid"]
def plnToEuro = 1/euro

def menuSourceFile = new File("MenuPl.txt")
def menuOutputFile = new File("MenuEur.txt")
menuOutputFile.text = ""

menuSourceFile.each {
    def courseRaw = it.split(" ")
    def eurPrice = String.format("%.2f", (courseRaw[1] as BigDecimal) * plnToEuro)
    menuOutputFile.text += courseRaw[0] + " " + eurPrice + "\n"
}