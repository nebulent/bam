package com.netflexitysolutions.software.bam.ui

import netflexity.schema.software.bam.messages._1.GetTransactions;
import netflexity.schema.software.bam.types._1.TransactionDetailsType;
import netflexity.schema.software.bam.types._1.TransactionType;
import netflexity.ws.software.bam.services._1_0.BAMInternal;

import org.apache.commons.codec.binary.Base64;
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
		def TransactionDetailsType transactionType = bamInternalService.getTransactions(new GetTransactions(transactionId: params.id)).transactions[0]
        if (!transactionType) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
            redirect action: 'list'
            return
        }
		def transactionInstance = new Transaction(uuid: transactionType.uuid, processName: transactionType.processName, 
								startDate: transactionType.startDate, endDate: transactionType.endDate,
								transactionStatusCode: transactionType.transactionStatusCode, healthCode: transactionType.healthCode,
								flowTransactions: transactionType.bpmFlowTransactions
									.collect( {new FlowTransaction(id: it.id, stageName: it.stageName, 
													transactionDate: it.transactionDate,
													payload: it.bpmFlowTransactionPayloads.isEmpty() 
														? null 
														: new String(it.bpmFlowTransactionPayloads.get(0).payloads.get(0)))} ).sort { a, b -> a.id - b.id });
		transactionInstance.id = transactionType.id
		
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
