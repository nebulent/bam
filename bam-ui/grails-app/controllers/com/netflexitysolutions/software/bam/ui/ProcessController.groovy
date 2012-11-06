package com.netflexitysolutions.software.bam.ui

import org.springframework.dao.DataIntegrityViolationException

import netflexity.schema.software.bam.messages._1.CreateProcess;
import netflexity.schema.software.bam.messages._1.GetProcesses;
import netflexity.schema.software.bam.types._1.ProcessType;
import netflexity.ws.software.bam.services._1_0.BAMInternal

class ProcessController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
	
	BAMInternal bamInternalService

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [processInstanceList: bamInternalService.getProcesses(new GetProcesses()).processes, processInstanceTotal: 10]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[processInstance: new Process(params)]
			break
		case 'POST':
	        def processInstance = new Process(params)
			def processType = new ProcessType(name: processInstance.name, description: processInstance.description)
			def result = bamInternalService.createProcess(new CreateProcess(process: processType)).process
	        if (!result) {
	            render view: 'create', model: [processInstance: processInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'process.label', default: 'Process'), result.id])
	        redirect action: 'show', id: result.id
			break
		}
    }

    def show() {
        def processInstance = bamInternalService.getProcesses(new GetProcesses(processId: params.id)).processes[0]
        if (!processInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'process.label', default: 'Process'), params.id])
            redirect action: 'list'
            return
        }

        [processInstance: processInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def processInstance = bamInternalService.getProcesses(new GetProcesses(processId: params.id)).processes[0]
	        if (!processInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'process.label', default: 'Process'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [processInstance: processInstance]
			break
		case 'POST':
	        def processInstance = Process.get(params.id)
	        if (!processInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'process.label', default: 'Process'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (processInstance.version > version) {
	                processInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'process.label', default: 'Process')] as Object[],
	                          "Another user has updated this Process while you were editing")
	                render view: 'edit', model: [processInstance: processInstance]
	                return
	            }
	        }

	        processInstance.properties = params

	        if (!processInstance.save(flush: true)) {
	            render view: 'edit', model: [processInstance: processInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'process.label', default: 'Process'), processInstance.id])
	        redirect action: 'show', id: processInstance.id
			break
		}
    }

    def delete() {
        def processInstance = Process.get(params.id)
        if (!processInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'process.label', default: 'Process'), params.id])
            redirect action: 'list'
            return
        }

        try {
            processInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'process.label', default: 'Process'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'process.label', default: 'Process'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
