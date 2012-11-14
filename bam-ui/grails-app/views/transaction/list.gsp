
<%@ page import="com.netflexitysolutions.software.bam.ui.Transaction" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
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
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="endDate" title="${message(code: 'transaction.endDate.label', default: 'End Date')}" />
						
							<g:sortableColumn property="processId" title="${message(code: 'transaction.processId.label', default: 'Process Id')}" />
						
							<g:sortableColumn property="processName" title="${message(code: 'transaction.processName.label', default: 'Process Name')}" />
						
							<g:sortableColumn property="startDate" title="${message(code: 'transaction.startDate.label', default: 'Start Date')}" />
						
							<g:sortableColumn property="transactionStatusCode" title="${message(code: 'transaction.transactionStatusCode.label', default: 'Transaction Status Code')}" />
						
							<g:sortableColumn property="uuid" title="${message(code: 'transaction.uuid.label', default: 'Uuid')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${transactionInstanceList}" var="transactionInstance">
						<tr>
						
							<td>${fieldValue(bean: transactionInstance, field: "endDate")}</td>
						
							<td>${fieldValue(bean: transactionInstance, field: "processId")}</td>
						
							<td>${fieldValue(bean: transactionInstance, field: "processName")}</td>
						
							<td>${fieldValue(bean: transactionInstance, field: "startDate")}</td>
						
							<td>${fieldValue(bean: transactionInstance, field: "transactionStatusCode")}</td>
						
							<td>${fieldValue(bean: transactionInstance, field: "uuid")}</td>
						
							<td class="link">
								<g:link action="show" id="${transactionInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${transactionInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
