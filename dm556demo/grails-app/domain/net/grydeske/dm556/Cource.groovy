package net.grydeske.dm556

import groovy.transform.ToString

@ToString
class Cource {

    String name
    String semester
    Integer year

    static constraints = {
        semester inList: ['Spring', 'Fall']
    }
}
