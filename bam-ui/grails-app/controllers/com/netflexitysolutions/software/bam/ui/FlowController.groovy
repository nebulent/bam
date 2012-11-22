package com.netflexitysolutions.software.bam.ui

import netflexity.schema.software.bam.messages._1.CreateFlow;
//import netflexity.schema.software.bam.messages._1.UpdateFlow;
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
		def flowInstance = new Flow(id: flowType.id, uuid: flowType.uuid, stageTypeCode: flowType.stageTypeId,
											process: new Process(id: flowType.process.id, name: flowType.process.name), 
											stage: new Stage(id: flowType.stage.id, name: flowType.stage.name))
		
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
			def flowInstance = new Flow(id: flowType.id, uuid: flowType.uuid, stageTypeCode: flowType.stageTypeId,
											process: new Process(id: flowType.process.id, name: flowType.process.name), 
											stage: new Stage(id: flowType.stage.id, name: flowType.stage.name))
			
	        [flowInstance: flowInstance, 
				processes: bamInternalService.getProcesses(new GetProcesses()).processes,
				stages: bamInternalService.getStages(new GetStages()).stages]
			break
		case 'POST':
//	        def flowInstance = new Flow(params)
//			def flowType = new FlowType(id: params.id, name: flowInstance.name, description: flowInstance.description)
//			def result = bamInternalService.updateFlow(new UpdateFlow(flow: flowType)).flow
//	        if (!result) {
//	            render view: 'create', model: [flowInstance: flowInstance]
//	            return
//	        }
//
//			flash.message = message(code: 'default.updated.message', args: [message(code: 'flow.label', default: 'Flow'), result.id])
//	        redirect action: 'show', id: result.id
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
