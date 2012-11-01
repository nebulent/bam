<%@ page import="com.netflexitysolutions.software.bam.ui.Stage" %>



<div class="fieldcontain ${hasErrors(bean: stageInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="stage.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${stageInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stageInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="stage.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${stageInstance?.name}"/>
</div>

