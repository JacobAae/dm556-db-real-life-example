package net.grydeske.dm556

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourceController {

    CourceService courceService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courceService.list(params), model:[courceCount: courceService.count()]
    }

    def show(Long id) {
        respond courceService.get(id)
    }

    def create() {
        respond new Cource(params)
    }

    def save(Cource cource) {
        if (cource == null) {
            notFound()
            return
        }

        try {
            courceService.save(cource)
        } catch (ValidationException e) {
            respond cource.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cource.label', default: 'Cource'), cource.id])
                redirect cource
            }
            '*' { respond cource, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courceService.get(id)
    }

    def update(Cource cource) {
        if (cource == null) {
            notFound()
            return
        }

        try {
            courceService.save(cource)
        } catch (ValidationException e) {
            respond cource.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cource.label', default: 'Cource'), cource.id])
                redirect cource
            }
            '*'{ respond cource, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courceService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cource.label', default: 'Cource'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cource.label', default: 'Cource'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
