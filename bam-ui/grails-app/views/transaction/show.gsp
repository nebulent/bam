
<%@ page import="com.netflexitysolutions.software.bam.ui.Transaction" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${transactionInstance?.endDate}">
						<dt><g:message code="transaction.endDate.label" default="End Date" /></dt>
						
							<dd><g:fieldValue bean="${transactionInstance}" field="endDate"/></dd>
						
					</g:if>
				
					<g:if test="${transactionInstance?.processId}">
						<dt><g:message code="transaction.processId.label" default="Process Id" /></dt>
						
							<dd><g:fieldValue bean="${transactionInstance}" field="processId"/></dd>
						
					</g:if>
				
					<g:if test="${transactionInstance?.processName}">
						<dt><g:message code="transaction.processName.label" default="Process Name" /></dt>
						
							<dd><g:fieldValue bean="${transactionInstance}" field="processName"/></dd>
						
					</g:if>
				
					<g:if test="${transactionInstance?.startDate}">
						<dt><g:message code="transaction.startDate.label" default="Start Date" /></dt>
						
							<dd><g:fieldValue bean="${transactionInstance}" field="startDate"/></dd>
						
					</g:if>
				
					<g:if test="${transactionInstance?.transactionStatusCode}">
						<dt><g:message code="transaction.transactionStatusCode.label" default="Transaction Status Code" /></dt>
						
							<dd><g:fieldValue bean="${transactionInstance}" field="transactionStatusCode"/></dd>
						
					</g:if>
				
					<g:if test="${transactionInstance?.uuid}">
						<dt><g:message code="transaction.uuid.label" default="Uuid" /></dt>
						
							<dd><g:fieldValue bean="${transactionInstance}" field="uuid"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${transactionInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${transactionInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
