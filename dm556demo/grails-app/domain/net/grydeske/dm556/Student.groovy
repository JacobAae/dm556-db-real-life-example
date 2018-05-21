package net.grydeske.dm556

class Student {

    String name
    String studentId
    Integer yearStarted
    Date dateOfBirth

    Date dateCreated
    Date lastUpdated

    static constraints = {
    }

    String toString() {
        "$name ($studentId)"
    }
}
