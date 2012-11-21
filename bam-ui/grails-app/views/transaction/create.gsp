<%@ page import="com.netflexitysolutions.software.bam.ui.Transaction" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bam">
		<g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
					<div class="row-fluid">
						<div class="span12">
							<div class="hero-unit non-index">
								<h2><g:message code="default.create.label" args="[entityName]" /></h2>
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
		
						<g:hasErrors bean="${transactionInstance}">
						<bootstrap:alert class="alert-error">
						<ul>
							<g:eachError bean="${transactionInstance}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
							</g:eachError>
						</ul>
						</bootstrap:alert>
						</g:hasErrors>
					</div>
					
					<div class="row-fluid">
						<div class="span12">
							<h4 class="title"><g:message code="default.create.label" args="[entityName]" /></h4>
							<div class="squiggly-border"></div>

							<g:form class="form-horizontal invoice-form" action="create" id="${transactionInstance?.id}" >
								<fieldset>
									<f:all bean="transactionInstance"/>

									<div class="hr"></div>

									<div class="form-actions pull-right">
										<button type="submit" class="btn btn-primary">
											<i class="icon-ok icon-white"></i>
											<g:message code="default.button.create.label" default="Create" />
										</button>
									</div>

								</fieldset>
							</g:form>

						</div>
					</div>
					
	</body>
</html>
