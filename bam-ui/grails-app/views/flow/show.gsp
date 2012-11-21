
<%@ page import="com.netflexitysolutions.software.bam.ui.Flow" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bam">
		<g:set var="entityName" value="${message(code: 'flow.label', default: 'Flow')}" />
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
				
					<g:if test="${flowInstance?.stageTypeCode}">
						<dt><g:message code="flow.stageTypeCode.label" default="Stage Type Code" /></dt>
						
							<dd><g:fieldValue bean="${flowInstance}" field="stageTypeCode"/></dd>
						
					</g:if>
				
					<g:if test="${flowInstance?.process}">
						<dt><g:message code="flow.process.label" default="Process" /></dt>
						
							<dd><g:link controller="process" action="show" id="${flowInstance?.process?.id}">${flowInstance?.process?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${flowInstance?.stage}">
						<dt><g:message code="flow.stage.label" default="Stage" /></dt>
						
							<dd><g:link controller="stage" action="show" id="${flowInstance?.stage?.id}">${flowInstance?.stage?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${flowInstance?.storeMessagePayload}">
						<dt><g:message code="flow.storeMessagePayload.label" default="Store Message Payload" /></dt>
						
							<dd><g:formatBoolean boolean="${flowInstance?.storeMessagePayload}" /></dd>
						
					</g:if>
				
					<g:if test="${flowInstance?.uuid}">
						<dt><g:message code="flow.uuid.label" default="Uuid" /></dt>
						
							<dd><g:fieldValue bean="${flowInstance}" field="uuid"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${flowInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${flowInstance?.id}">
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
