package com.netflexitysolutions.software.bam.ui

import netflexity.schema.software.bam.messages._1.CreateFlow;
import netflexity.schema.software.bam.messages._1.GetProcesses;
import netflexity.schema.software.bam.messages._1.GetStages;
import netflexity.schema.software.bam.messages._1.GetFlows;
import netflexity.schema.software.bam.types._1.FlowType;
import netflexity.ws.software.bam.services._1_0.BAMInternal;

import org.springframework.dao.DataIntegrityViolationException

class FlowController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

	BAMInternal bamInternalService

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [flowInstanceList: bamInternalService.getFlows(new GetFlows()).flows, flowInstanceTotal: Flow.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[flowInstance: new Flow(params), 
				processes: bamInternalService.getProcesses(new GetProcesses()).processes,
				stages: bamInternalService.getStages(new GetStages()).stages]
			break
		case 'POST':
	        def flowInstance = new Flow(params)
			def flowType = new FlowType(uuid: flowInstance.uuid, stageTypeId: flowInstance.stageTypeCode,
										processId: flowInstance.process.id, stageId: flowInstance.stage.id)
			def result = bamInternalService.createFlow(new CreateFlow(flow: flowType)).flow
	        if (!result) {
	            render view: 'create', model: [flowInstance: flowInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'flow.label', default: 'Flow'), flowInstance.id])
	        redirect action: 'show', id: flowInstance.id
			break
		}
    }

    def show() {
		def flowType = bamInternalService.getFlows(new GetFlows(flowId: params.id)).flows[0]
        if (!flowType) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])
            redirect action: 'list'
            return
        }
		def flowInstance = new Flow(uuid: flowType.uuid, stageTypeCode: flowType.stageTypeId,
										process: flowType.process, stage: flowType.stage)
		flowInstance.id = flowType.id
		
        [flowInstance: flowInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
			def flowType = bamInternalService.getFlows(new GetFlows(flowId: params.id)).flows[0]
	        if (!flowType) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])
	            redirect action: 'list'
	            return
	        }
			def stage = new Stage(id: flowType.stage.id, name: flowType.stage.name)
			def flowInstance = new Flow(uuid: flowType.uuid, stageTypeCode: flowType.stageTypeId,
											process: new Process(id: flowType.process.id, name: flowType.process.name), 
											stage: new Stage(id: flowType.stage.id, name: flowType.stage.name))
			flowInstance.id = flowType.id
			
	        [flowInstance: flowInstance, 
				processes: bamInternalService.getProcesses(new GetProcesses()).processes,
				stages: bamInternalService.getStages(new GetStages()).stages]
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
