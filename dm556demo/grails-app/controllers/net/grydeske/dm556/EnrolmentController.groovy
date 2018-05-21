package net.grydeske.dm556

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EnrolmentController {

    EnrolmentService enrolmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond enrolmentService.list(params), model:[enrolmentCount: enrolmentService.count()]
    }

    def show(Long id) {
        respond enrolmentService.get(id)
    }

    def create() {
        respond new Enrolment(params)
    }

    def save(Enrolment enrolment) {
        if (enrolment == null) {
            notFound()
            return
        }

        try {
            enrolmentService.save(enrolment)
        } catch (ValidationException e) {
            respond enrolment.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'enrolment.label', default: 'Enrolment'), enrolment.id])
                redirect enrolment
            }
            '*' { respond enrolment, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond enrolmentService.get(id)
    }

    def update(Enrolment enrolment) {
        if (enrolment == null) {
            notFound()
            return
        }

        try {
            enrolmentService.save(enrolment)
        } catch (ValidationException e) {
            respond enrolment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'enrolment.label', default: 'Enrolment'), enrolment.id])
                redirect enrolment
            }
            '*'{ respond enrolment, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        enrolmentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'enrolment.label', default: 'Enrolment'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'enrolment.label', default: 'Enrolment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
