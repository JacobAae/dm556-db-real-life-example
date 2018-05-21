package net.grydeske.dm556

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EnrolmentServiceSpec extends Specification {

    EnrolmentService enrolmentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Enrolment(...).save(flush: true, failOnError: true)
        //new Enrolment(...).save(flush: true, failOnError: true)
        //Enrolment enrolment = new Enrolment(...).save(flush: true, failOnError: true)
        //new Enrolment(...).save(flush: true, failOnError: true)
        //new Enrolment(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //enrolment.id
    }

    void "test get"() {
        setupData()

        expect:
        enrolmentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Enrolment> enrolmentList = enrolmentService.list(max: 2, offset: 2)

        then:
        enrolmentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        enrolmentService.count() == 5
    }

    void "test delete"() {
        Long enrolmentId = setupData()

        expect:
        enrolmentService.count() == 5

        when:
        enrolmentService.delete(enrolmentId)
        sessionFactory.currentSession.flush()

        then:
        enrolmentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Enrolment enrolment = new Enrolment()
        enrolmentService.save(enrolment)

        then:
        enrolment.id != null
    }
}
