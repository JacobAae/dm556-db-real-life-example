package net.grydeske.dm556

import grails.gorm.services.Service

@Service(Enrolment)
interface EnrolmentService {

    Enrolment get(Serializable id)

    List<Enrolment> list(Map args)

    Long count()

    void delete(Serializable id)

    Enrolment save(Enrolment enrolment)

}