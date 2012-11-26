
<%@ page import="com.netflexitysolutions.software.bam.ui.Transaction"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="bam">
<g:set var="entityName"
	value="${message(code: 'transaction.label', default: 'Transaction')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<div class="row-fluid">
		<div class="span12">
			<div class="hero-unit non-index">
				<h2>
					<g:message code="default.show.label" args="[entityName]" />
				</h2>
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
			<bootstrap:alert class="alert-info">
				${flash.message}
			</bootstrap:alert>
		</g:if>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<h4 class="title">
				<g:message code="default.show.label" args="[entityName]" />
			</h4>
			<div class="squiggly-border"></div>

			<dl>

				<g:if test="${transactionInstance?.uuid}">
					<dt>
						<g:message code="transaction.uuid.label" default="Uuid" />
					</dt>

					<dd>
						<g:fieldValue bean="${transactionInstance}" field="uuid" />
					</dd>

				</g:if>

				<g:if test="${transactionInstance?.processName}">
					<dt>
						<g:message code="transaction.processName.label"
							default="Process Name" />
					</dt>

					<dd>
						<g:fieldValue bean="${transactionInstance}" field="processName" />
					</dd>

				</g:if>

				<g:if test="${transactionInstance?.startDate}">
					<dt>
						<g:message code="transaction.startDate.label" default="Start Date" />
					</dt>

					<dd>
						<g:formatDate date="${transactionInstance?.startDate}" />
					</dd>

				</g:if>

				<g:if test="${transactionInstance?.endDate}">
					<dt>
						<g:message code="transaction.endDate.label" default="End Date" />
					</dt>

					<dd>
						<g:formatDate date="${transactionInstance?.endDate}" />
					</dd>

				</g:if>

				<g:if test="${transactionInstance?.transactionStatusCode}">
					<dt>
						<g:message code="transaction.transactionStatusCode.label"
							default="Transaction Status Code" />
					</dt>

					<dd>
						<g:fieldValue bean="${transactionInstance}"
							field="transactionStatusCode" />
					</dd>

				</g:if>

				<g:if test="${transactionInstance?.healthCode}">
					<dt>
						<g:message code="transaction.healthCode.label"
							default="Health Code" />
					</dt>

					<dd>
						<g:fieldValue bean="${transactionInstance}" field="healthCode" />
					</dd>

				</g:if>

			</dl>

		</div>

	</div>
	<div class="row-fluid">
		<div class="span12">
			<h4 class="title">
				<g:message code="transaction.steps.label" default="Steps" />
			</h4>
			<div class="squiggly-border"></div>
			<table class="table table-striped table-bordered table-radmin">
				<thead>
					<tr>
						<th class="header"><g:message
								code="transactionflow.stagename.label" default="Stage" /></th>
						<th class="header"><g:message
								code="transactionflow.datetime.label" default="Date" /></th>
						<th class="header"><g:message
								code="transactionflow.payload.label" default="Payload" /></th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${transactionInstance.flowTransactions}"
						var="flowTransaction">
						<tr>
							<td>
								${fieldValue(bean: flowTransaction, field: "stageName")}
							</td>
							<td><g:formatDate date="${flowTransaction.transactionDate}" /></td>
							<td>${fieldValue(bean: flowTransaction, field: "payload")}</td>
						</tr>
					</g:each>
				</tbody>
			</table>

		</div>

	</div>
	<div class="row-fluid">
		<div class="span12">
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
