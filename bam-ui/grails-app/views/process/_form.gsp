<%@ page import="com.netflexitysolutions.software.bam.ui.Process" %>



<div class="fieldcontain ${hasErrors(bean: processInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="process.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${processInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: processInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="process.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${processInstance?.description}"/>
</div>

