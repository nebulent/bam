package com.netflexitysolutions.software.bam.ui

import netflexity.schema.software.bam.messages._1.GetTransactions;
import netflexity.ws.software.bam.services._1_0.BAMInternal;

import org.springframework.dao.DataIntegrityViolationException

class TransactionController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

	BAMInternal bamInternalService

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [transactionInstanceList: Transaction.list(params), transactionInstanceTotal: Transaction.count()]
        [transactionInstanceList: bamInternalService.getTransactions(new GetTransactions(limit: 10)).transactions, transactionInstanceTotal: 10]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[transactionInstance: new Transaction(params)]
			break
		case 'POST':
	        def transactionInstance = new Transaction(params)
	        if (!transactionInstance.save(flush: true)) {
	            render view: 'create', model: [transactionInstance: transactionInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'transaction.label', default: 'Transaction'), transactionInstance.id])
	        redirect action: 'show', id: transactionInstance.id
			break
		}
    }

    def show() {
        def transactionInstance = Transaction.get(params.id)
        if (!transactionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
            redirect action: 'list'
            return
        }

        [transactionInstance: transactionInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def transactionInstance = Transaction.get(params.id)
	        if (!transactionInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [transactionInstance: transactionInstance]
			break
		case 'POST':
	        def transactionInstance = Transaction.get(params.id)
	        if (!transactionInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (transactionInstance.version > version) {
	                transactionInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'transaction.label', default: 'Transaction')] as Object[],
	                          "Another user has updated this Transaction while you were editing")
	                render view: 'edit', model: [transactionInstance: transactionInstance]
	                return
	            }
	        }

	        transactionInstance.properties = params

	        if (!transactionInstance.save(flush: true)) {
	            render view: 'edit', model: [transactionInstance: transactionInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'transaction.label', default: 'Transaction'), transactionInstance.id])
	        redirect action: 'show', id: transactionInstance.id
			break
		}
    }

    def delete() {
        def transactionInstance = Transaction.get(params.id)
        if (!transactionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
            redirect action: 'list'
            return
        }

        try {
            transactionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
