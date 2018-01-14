//Firma software'owa prowadzi projekty w:
//Groovy, Grails, Java, JEE
//Do każdego projektu przypisani są programiści, co pokazuje poniższa mapa:
//
//def pmap = [ Groovy: [ 'Asia', 'Jan' ],
//             Grails: [ 'Asia', 'Jan', 'Stefan', 'Mirek' ],
//             Java: [ 'Asia', 'Stefan', 'Mirek' ],
//             JEE: [ 'Slawek', 'Stefan', 'Janusz' ]
//]
//Wypisać:
//a) ile osób pracuje w każdym z projektów  (3 p)
//b) projekty, które mają więcej niż dwóch programistów (3p )
//c)  w jakich projektach biorą udzial poszczególni programisci (6 p.)

def pmap = [ Groovy: [ 'Asia', 'Jan' ],
             Grails: [ 'Asia', 'Jan', 'Stefan', 'Mirek' ],
             Java: [ 'Asia', 'Stefan', 'Mirek' ],
             JEE: [ 'Slawek', 'Stefan', 'Janusz' ]
]

// a)
def keys = pmap.keySet()
keys.each { key ->
    def count = pmap.get(key).size()
    println "$key: $count"
}

// b)
def moreThan2 = pmap.findAll {
    it.value.size() > 2
}
println moreThan2

// c)
def names = []
println pmap.values().each({
    names = (names + it).unique()
})
names.each { name ->
    def projects = pmap.findAll({ it.value.contains(name) }).collect { el -> el.key }
    println "$name: $projects"
}