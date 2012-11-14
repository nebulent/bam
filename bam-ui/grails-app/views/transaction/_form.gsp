<%@ page import="com.netflexitysolutions.software.bam.ui.Transaction" %>



<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="transaction.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="endDate" type="number" value="${transactionInstance.endDate}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'processId', 'error')} required">
	<label for="processId">
		<g:message code="transaction.processId.label" default="Process Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="processId" type="number" value="${transactionInstance.processId}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'processName', 'error')} ">
	<label for="processName">
		<g:message code="transaction.processName.label" default="Process Name" />
		
	</label>
	<g:textField name="processName" value="${transactionInstance?.processName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="transaction.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="startDate" type="number" value="${transactionInstance.startDate}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'transactionStatusCode', 'error')} ">
	<label for="transactionStatusCode">
		<g:message code="transaction.transactionStatusCode.label" default="Transaction Status Code" />
		
	</label>
	<g:textField name="transactionStatusCode" value="${transactionInstance?.transactionStatusCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'uuid', 'error')} ">
	<label for="uuid">
		<g:message code="transaction.uuid.label" default="Uuid" />
		
	</label>
	<g:textField name="uuid" value="${transactionInstance?.uuid}"/>
</div>

