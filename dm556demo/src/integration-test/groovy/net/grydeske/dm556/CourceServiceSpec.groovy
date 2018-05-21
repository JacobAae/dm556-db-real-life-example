package net.grydeske.dm556

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourceServiceSpec extends Specification {

    CourceService courceService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Cource(...).save(flush: true, failOnError: true)
        //new Cource(...).save(flush: true, failOnError: true)
        //Cource cource = new Cource(...).save(flush: true, failOnError: true)
        //new Cource(...).save(flush: true, failOnError: true)
        //new Cource(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //cource.id
    }

    void "test get"() {
        setupData()

        expect:
        courceService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Cource> courceList = courceService.list(max: 2, offset: 2)

        then:
        courceList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courceService.count() == 5
    }

    void "test delete"() {
        Long courceId = setupData()

        expect:
        courceService.count() == 5

        when:
        courceService.delete(courceId)
        sessionFactory.currentSession.flush()

        then:
        courceService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Cource cource = new Cource()
        courceService.save(cource)

        then:
        cource.id != null
    }
}
