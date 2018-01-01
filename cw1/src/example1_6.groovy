// Napisy wprowadzane w kolejnych dialogach mają formę:  pozycja = koszt.
// Zsumuj wszystkie koszty dla tych samych pozycji (np. mleko, chleb). (5)

class example1_6 {
    static void main(String[] args) {
        def items = collectItems()
        items.each { key, value ->
            println "$key: $value"
        }
    }

    static LinkedHashMap collectItems() {
        def map = [:]

        while (true) {
            def rawProduct = getRawProduct()
            if (rawProduct == "")
                break

            def parts = rawProduct.split(" = ")
            def product = parts[0]
            def price = new BigDecimal(parts[1])

            if (map.containsKey(product)) {
                def sum = map.get(product)
                sum += price
                map.replace(product, sum)
            }
            else
                map.put(product, price)
        }

        return map
    }

    static String getRawProduct() {
        def prompt = true
        def scanner = new Scanner(System.in)
        def input = ""

        while (prompt) {
            prompt = false
            print "Podaj produkt: "
            input = scanner.nextLine()
            if (input == "")
                break

            if (!input.matches("[a-zA-Z]* = [0-9]*.[0-9]{2}")) {
                prompt = true
                println "Błędny zapis produktu. Spróbuj ponownie..."
            }
        }

        return input
    }
}
