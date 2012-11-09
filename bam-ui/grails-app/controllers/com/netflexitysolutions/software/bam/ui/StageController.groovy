package com.netflexitysolutions.software.bam.ui

import netflexity.schema.software.bam.messages._1.CreateStage;
import netflexity.schema.software.bam.messages._1.GetStages;
import netflexity.schema.software.bam.types._1.StageType;
import netflexity.ws.software.bam.services._1_0.BAMInternal;

import org.springframework.dao.DataIntegrityViolationException

class StageController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

	BAMInternal bamInternalService

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [stageInstanceList: bamInternalService.getStages(new GetStages()).stages, stageInstanceTotal: 10]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[stageInstance: new Stage(params)]
			break
		case 'POST':
	        def stageInstance = new Stage(params)
			def stageType = new StageType(name: stageInstance.name, description: stageInstance.description)
			def result = bamInternalService.createStage(new CreateStage(stage: stageType)).stage
	        if (!result) {
	            render view: 'create', model: [stageInstance: stageInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'stage.label', default: 'Stage'), stageInstance.id])
	        redirect action: 'show', id: stageInstance.id
			break
		}
    }

    def show() {
        def stageInstance = Stage.get(params.id)
        if (!stageInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stage.label', default: 'Stage'), params.id])
            redirect action: 'list'
            return
        }

        [stageInstance: stageInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def stageInstance = Stage.get(params.id)
	        if (!stageInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stage.label', default: 'Stage'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [stageInstance: stageInstance]
			break
		case 'POST':
	        def stageInstance = Stage.get(params.id)
	        if (!stageInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stage.label', default: 'Stage'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (stageInstance.version > version) {
	                stageInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'stage.label', default: 'Stage')] as Object[],
	                          "Another user has updated this Stage while you were editing")
	                render view: 'edit', model: [stageInstance: stageInstance]
	                return
	            }
	        }

	        stageInstance.properties = params

	        if (!stageInstance.save(flush: true)) {
	            render view: 'edit', model: [stageInstance: stageInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'stage.label', default: 'Stage'), stageInstance.id])
	        redirect action: 'show', id: stageInstance.id
			break
		}
    }

    def delete() {
        def stageInstance = Stage.get(params.id)
        if (!stageInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stage.label', default: 'Stage'), params.id])
            redirect action: 'list'
            return
        }

        try {
            stageInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'stage.label', default: 'Stage'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stage.label', default: 'Stage'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
