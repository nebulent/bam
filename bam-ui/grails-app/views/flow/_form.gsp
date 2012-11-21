<%@ page import="com.netflexitysolutions.software.bam.ui.Flow" %>



<div class="fieldcontain ${hasErrors(bean: flowInstance, field: 'stageTypeCode', 'error')} required">
	<label for="stageTypeCode">
		<g:message code="flow.stageTypeCode.label" default="Stage Type Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="stageTypeCode" from="${com.netflexitysolutions.software.bam.ui.Flow$StageTypeCode?.values()}" keys="${com.netflexitysolutions.software.bam.ui.Flow$StageTypeCode.values()*.name()}" required="" value="${flowInstance?.stageTypeCode?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: flowInstance, field: 'process', 'error')} required">
	<label for="process">
		<g:message code="flow.process.label" default="Process" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="process" name="process.id" from="${com.netflexitysolutions.software.bam.ui.Process.list()}" optionKey="id" required="" value="${flowInstance?.process?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: flowInstance, field: 'stage', 'error')} required">
	<label for="stage">
		<g:message code="flow.stage.label" default="Stage" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="stage" name="stage.id" from="${com.netflexitysolutions.software.bam.ui.Stage.list()}" optionKey="id" required="" value="${flowInstance?.stage?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: flowInstance, field: 'storeMessagePayload', 'error')} ">
	<label for="storeMessagePayload">
		<g:message code="flow.storeMessagePayload.label" default="Store Message Payload" />
		
	</label>
	<g:checkBox name="storeMessagePayload" value="${flowInstance?.storeMessagePayload}" />
</div>

<div class="fieldcontain ${hasErrors(bean: flowInstance, field: 'uuid', 'error')} ">
	<label for="uuid">
		<g:message code="flow.uuid.label" default="Uuid" />
		
	</label>
	<g:textField name="uuid" value="${flowInstance?.uuid}"/>
</div>

