
<%@ page import="com.netflexitysolutions.software.bam.ui.Process" %>
<%@ page import="com.netflexitysolutions.software.bam.ui.Flow" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bam">
		<g:set var="entityName" value="${message(code: 'process.label', default: 'Process')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
					<div class="row-fluid">
						<div class="span12">
							<div class="hero-unit non-index">
								<h2><g:message code="default.show.label" args="[entityName]" /></h2>
								<br />
								<p class="hidden-phone">
									<g:link class="btn btn-info btn-large pull-right" action="list">
										<g:message code="default.list.label" args="[entityName]" />
									</g:link>
									<g:link class="btn btn-info btn-large pull-right" action="create">
										<g:message code="default.create.label" args="[entityName]" />
									</g:link>
								</p>
							</div>
						</div>
					</div>

					<div class="row-fluid">
						<g:if test="${flash.message}">
						<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
						</g:if>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<h4 class="title"><g:message code="default.show.label" args="[entityName]" /></h4>
							<div class="squiggly-border"></div>

				<dl>
				
					<g:if test="${processInstance?.name}">
						<dt><g:message code="process.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${processInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${processInstance?.description}">
						<dt><g:message code="process.description.label" default="Description" /></dt>
						
							<dd><g:fieldValue bean="${processInstance}" field="description"/></dd>
						
					</g:if>
					
					<g:if test="${flowInstanceList}">
						<dt><g:message code="process.flows.label" default="Flows" /></dt>
						<dd>
							<table class="table table-striped table-bordered table-radmin">
								<thead>
									<tr>
										
											<g:sortableColumn property="uuid" title="${message(code: 'flow.uuid.label', default: 'Uuid')}" />
										
											<th class="header"><g:message code="flow.process.label" default="Process" /></th>
										
											<th class="header"><g:message code="flow.stage.label" default="Stage" /></th>
										
											<g:sortableColumn property="stageTypeCode" title="${message(code: 'flow.stageTypeCode.label', default: 'Stage Type Code')}" />
										
											<g:sortableColumn property="storeMessagePayload" title="${message(code: 'flow.storeMessagePayload.label', default: 'Store Message Payload')}" />
										
										<th style="text-align:center; width: 89px">View</th>
										<th style="text-align:center; width: 63px">Edit</th>
										<th style="text-align:center; width: 77px">Delete</th>
									</tr>
								</thead>
								<tbody>
								<g:each in="${flowInstanceList}" var="flowInstance">
									<tr>
									
										<td>${fieldValue(bean: flowInstance, field: "uuid")}</td>
									
										<td>${fieldValue(bean: flowInstance, field: "process.name")}</td>
									
										<td>${fieldValue(bean: flowInstance, field: "stage.name")}</td>
									
										<td>${fieldValue(bean: flowInstance, field: "stageTypeId")}</td>
									
										<td><g:formatBoolean boolean="${flowInstance.storeMessagePayload}" /></td>
									
										<td>
											<g:link controller="flow" action="show" id="${flowInstance.id}" class="btn btn-small">
												<i class="icon-edit"></i>
												Show
											</g:link>
										</td>
										<td>
											<g:link controller="flow" action="edit" id="${flowInstance.id}" class="btn btn-small">
												<i class="icon-edit"></i>
												Edit
											</g:link>
										</td>
										<td>
											<a class="btn btn-small btn-danger" href="#">
												<i class="icon-trash icon-white"></i>
												Delete
											</a>
										</td>
									</tr>
								</g:each>
								</tbody>
							</table>
						</dd>
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${processInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${processInstance?.id}">
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
