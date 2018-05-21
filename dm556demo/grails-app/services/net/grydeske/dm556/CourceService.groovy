package net.grydeske.dm556

import grails.gorm.services.Service

@Service(Cource)
interface CourceService {

    Cource get(Serializable id)

    List<Cource> list(Map args)

    Long count()

    void delete(Serializable id)

    Cource save(Cource cource)

}