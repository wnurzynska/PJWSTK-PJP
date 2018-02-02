// Przedstawić ciekawe i praktycznie użyteczne zastosowania trampoline() i memoize().

// Trampoline
// http://mrhaki.blogspot.com/2011/04/groovy-goodness-recursion-with-closure.html
// Wrapuje closure w TrampolineClosure, wywołania będą sekwencyjne.
// Wywołania będą wykonywane tak długo aż kod nie dostanie czegoś innego niż TrampolineCLosure - unikniemy również stack overflow exception.
def sizeList
sizeList = { list, counter = 0 ->
    if (list.size() == 0) {
        counter
    } else {
        sizeList.trampoline(list.tail(), counter + 1)
    }
}
sizeList = sizeList.trampoline()

println sizeList(1..10000) == 10000

// Memoize
// http://mrhaki.blogspot.com/2011/05/groovy-goodness-cache-closure-results.html
// Memoize cache'uje wywołania closure'a, dzięki czemu kolejne wywołania od razu zwracają wyniki
def factorial = {
    println "First call for $it!"
    def result = 1
    for (def i = 1; i <= it; i++)
        result *= i

    return result
}.memoize()

def args = [1, 2, 3, 2, 4, 3]
args.each {
    def result = factorial(it)
    println "$it! = $result"
}