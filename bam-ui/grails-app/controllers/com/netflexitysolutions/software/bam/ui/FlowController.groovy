package com.netflexitysolutions.software.bam.ui

import org.springframework.dao.DataIntegrityViolationException

class FlowController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [flowInstanceList: Flow.list(params), flowInstanceTotal: Flow.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[flowInstance: new Flow(params)]
			break
		case 'POST':
	        def flowInstance = new Flow(params)
	        if (!flowInstance.save(flush: true)) {
	            render view: 'create', model: [flowInstance: flowInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'flow.label', default: 'Flow'), flowInstance.id])
	        redirect action: 'show', id: flowInstance.id
			break
		}
    }

    def show() {
        def flowInstance = Flow.get(params.id)
        if (!flowInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])
            redirect action: 'list'
            return
        }

        [flowInstance: flowInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def flowInstance = Flow.get(params.id)
	        if (!flowInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [flowInstance: flowInstance]
			break
		case 'POST':
	        def flowInstance = Flow.get(params.id)
	        if (!flowInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (flowInstance.version > version) {
	                flowInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'flow.label', default: 'Flow')] as Object[],
	                          "Another user has updated this Flow while you were editing")
	                render view: 'edit', model: [flowInstance: flowInstance]
	                return
	            }
	        }

	        flowInstance.properties = params

	        if (!flowInstance.save(flush: true)) {
	            render view: 'edit', model: [flowInstance: flowInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'flow.label', default: 'Flow'), flowInstance.id])
	        redirect action: 'show', id: flowInstance.id
			break
		}
    }

    def delete() {
        def flowInstance = Flow.get(params.id)
        if (!flowInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])
            redirect action: 'list'
            return
        }

        try {
            flowInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
