<%@ page import="com.netflexitysolutions.software.bam.ui.Process" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bam">
		<g:set var="entityName" value="${message(code: 'process.label', default: 'Process')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
					<div class="row-fluid">
						<div class="span12">
							<div class="hero-unit non-index">
								<h2><g:message code="default.edit.label" args="[entityName]" /></h2>
								<br />
								<p class="hidden-phone">
									<g:link class="btn btn-info btn-large pull-right" action="list">
										<g:message code="default.list.label" args="[entityName]" />
									</g:link>
								</p>
							</div>
						</div>
					</div>

					<div class="row-fluid">
						<g:if test="${flash.message}">
						<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
						</g:if>
		
						<g:hasErrors bean="${processInstance}">
						<bootstrap:alert class="alert-error">
						<ul>
							<g:eachError bean="${processInstance}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
							</g:eachError>
						</ul>
						</bootstrap:alert>
						</g:hasErrors>
					</div>
					
					<div class="row-fluid">
						<div class="span12">
							<h4 class="title"><g:message code="default.edit.label" args="[entityName]" /></h4>
							<div class="squiggly-border"></div>

							<g:form class="form-horizontal invoice-form" action="edit" id="${processInstance?.id}" >
								<g:hiddenField name="version" value="${processInstance?.version}" />
								<fieldset>
									<f:all bean="processInstance"/>

									<div class="hr"></div>

									<div class="form-actions pull-right">
										<button type="submit" class="btn btn-primary">
											<i class="icon-ok icon-white"></i>
											<g:message code="default.button.update.label" default="Update" />
										</button>
									</div>

								</fieldset>
							</g:form>

						</div>
					</div>
					
	</body>
</html>
