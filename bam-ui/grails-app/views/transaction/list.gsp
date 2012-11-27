
<%@ page import="com.netflexitysolutions.software.bam.ui.Transaction" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bam">
		<g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
					<div class="row-fluid">
						<div class="span12">
							<div class="hero-unit non-index">
								<h2><g:message code="default.list.label" args="[entityName]" /></h2>
								<br />
								<p class="hidden-phone">
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
					<div class="row-fluid hidden-tablet hidden-phone">
						<div class="span12">
							<h4 class="title"><g:message code="default.list.label" args="[entityName]" /></h4>
							<div class="squiggly-border"></div>
							<form action="list" method="get">
								Uuid: <input type="text" name="query"/>
								<select id="status" name="status">
									<option value="">Select status code...</option>
									<option value="PROCESSING">PROCESSING</option>
									<option value="COMPLETED">COMPLETED</option>
								</select>
								<select id="health" name="health">
									<option value="">Select health code...</option>
									<option value="HEALTHY">HEALTHY</option>
									<option value="ERROR">ERROR</option>
								</select>
								<input type="submit" value="Search">
							</form>
							<table class="table table-striped table-bordered table-radmin">
								<thead>
									<tr>
										
											<g:sortableColumn property="uuid" title="${message(code: 'transaction.uuid.label', default: 'Uuid')}" />
										
											<g:sortableColumn property="processName" title="${message(code: 'transaction.processName.label', default: 'Process Name')}" />
										
											<g:sortableColumn property="startDate" title="${message(code: 'transaction.startDate.label', default: 'Start Date')}" />
										
											<g:sortableColumn property="endDate" title="${message(code: 'transaction.endDate.label', default: 'End Date')}" />
										
											<th class="header"><g:message code="transaction.trace.label" default="Trace" /></th>
											
											<g:sortableColumn property="transactionStatusCode" title="${message(code: 'transaction.transactionStatusCode.label', default: 'Transaction Status Code')}" />
										
											<g:sortableColumn property="healthCode" title="${message(code: 'transaction.healthCode.label', default: 'Health Code')}" />
										
										<th style="text-align:center; width: 89px">View</th>
										<th style="text-align:center; width: 63px">Edit</th>
										<th style="text-align:center; width: 77px">Delete</th>
									</tr>
								</thead>
								<tbody>
								<g:each in="${transactionInstanceList}" var="transactionInstance">
									<tr>
									
										<td>${fieldValue(bean: transactionInstance, field: "uuid")}</td>
									
										<td>${fieldValue(bean: transactionInstance, field: "processName")}</td>
									
										<td><g:formatDate date="${transactionInstance.startDate}" /></td>
									
										<td><g:formatDate date="${transactionInstance.endDate}" /></td>
										
										<td>
											<g:each in="${transactionInstance.bpmFlowTransactions.sort { a, b -> a.id - b.id }}" var="transactionStage">
												${transactionStage.stageName}<br/>
											</g:each>
										</td>
									
										<td>${fieldValue(bean: transactionInstance, field: "transactionStatusCode")}</td>
									
										<td>${fieldValue(bean: transactionInstance, field: "healthCode")}</td>
									
										<td>
											<g:link action="show" id="${transactionInstance.id}" class="btn btn-small">
												<i class="icon-edit"></i>
												Show
											</g:link>
										</td>
										<td>
											<g:link action="edit" id="${transactionInstance.id}" class="btn btn-small">
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

							<div class="pagination pagination-right">
								<bootstrap:paginate total="${transactionInstanceTotal}"  params="${[query: '', transactionStatusCode: '', healthCode: '']}"/>
							</div>

						</div>
		
					</div>

	</body>
</html>
