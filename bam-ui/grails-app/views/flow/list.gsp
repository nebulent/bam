
<%@ page import="com.netflexitysolutions.software.bam.ui.Flow" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'flow.label', default: 'Flow')}" />
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
						
							<th class="header"><g:message code="flow.process.label" default="Process" /></th>
						
							<th class="header"><g:message code="flow.stage.label" default="Stage" /></th>
						
							<g:sortableColumn property="storeMessagePayload" title="${message(code: 'flow.storeMessagePayload.label', default: 'Store Message Payload')}" />
						
							<g:sortableColumn property="uuid" title="${message(code: 'flow.uuid.label', default: 'Uuid')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${flowInstanceList}" var="flowInstance">
						<tr>
						
							<td>${fieldValue(bean: flowInstance, field: "process.name")}</td>
						
							<td>${fieldValue(bean: flowInstance, field: "stage.name")}</td>
						
							<td><g:formatBoolean boolean="${flowInstance.storeMessagePayload}" /></td>
						
							<td>${fieldValue(bean: flowInstance, field: "uuid")}</td>
						
							<td class="link">
								<g:link action="show" id="${flowInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${flowInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
