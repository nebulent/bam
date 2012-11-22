package com.netflexitysolutions.software.bam.ui

import groovy.util.logging.Log;

import org.springframework.dao.DataIntegrityViolationException

import com.sun.media.sound.AbstractMidiDeviceProvider.Info;

import netflexity.schema.software.bam.messages._1.CreateProcess;
import netflexity.schema.software.bam.messages._1.GetProcesses;
import netflexity.schema.software.bam.messages._1.UpdateProcess;
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
		def processType = bamInternalService.getProcesses(new GetProcesses(processId: params.id)).processes[0]
        if (!processType) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'process.label', default: 'Process'), params.id])
            redirect action: 'list'
            return
        }
		def processInstance = new Process(name: processType.name, description: processType.description)
		processInstance.id = processType.id
		
        [processInstance: processInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
			def processType = bamInternalService.getProcesses(new GetProcesses(processId: params.id)).processes[0]
	        if (!processType) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'process.label', default: 'Process'), params.id])
	            redirect action: 'list'
	            return
	        }
			def processInstance = new Process(name: processType.name, description: processType.description)
			processInstance.id = processType.id
			
	        [processInstance: processInstance]
			break
		case 'POST':
	        def processInstance = new Process(params)
			def processType = new ProcessType(id: params.id, name: processInstance.name, description: processInstance.description)
			def result = bamInternalService.updateProcess(new UpdateProcess(process: processType)).process
	        if (!result) {
	            render view: 'create', model: [processInstance: processInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'process.label', default: 'Process'), result.id])
	        redirect action: 'show', id: result.id
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
