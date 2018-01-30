// Firma software'owa prowadzi projekty w:
// Groovy, Grails, Java, JEE
// Plik Projekty.txt zawiera informacje o składzie osobowym każdego z projektów w postaci:
// nazwaprojektu<TAB>Nazwisko imie(1)<TAB>Nazwiskoimie(2)<TAB> itd
// Utworzyc pliki:
// ProjektyDuze.txt - zawierający spis projektów w którym pracuje > niż 3 osoby
// Programisci.txt - zawierajacy spis programistow w postaci:
// nazwisko imie<tab>proj1<tab>proj2<tab> itd.

def projectsSourceFile = new File("Projekty.txt")
def programmerProjects = [:]
projectsSourceFile.each {
    if (it.size() == 0)
        return

    def rawValues = it.split("\t")

    def projectName = rawValues[0]
    def programmers = rawValues.drop(1)

    if (programmers.size() > 3) {
        def bigProjectsFile = new File("ProjektyDuze.txt")
        bigProjectsFile.text = ""
        programmers.each { bigProjectsFile.text += it + "\n" }
    }

    programmers.each {
        if (!programmerProjects.containsKey(it))
            programmerProjects.put(it, [projectName])
        else {
            def projects = programmerProjects.get(it)
            projects.push(projectName)
        }
    }
}

def prorammerProjectsFile = new File("Programisci.txt")
prorammerProjectsFile.text = ""
programmerProjects.each {
    def projectsAsText = ""
    it.value.each { projectsAsText += "\t" + it }
    prorammerProjectsFile.text += it.key + projectsAsText + "\n"
}

