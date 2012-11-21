
<%@ page import="com.netflexitysolutions.software.bam.ui.Process" %>
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
