
<%@ page import="com.netflexitysolutions.software.bam.ui.Flow" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bam">
		<g:set var="entityName" value="${message(code: 'flow.label', default: 'Flow')}" />
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

							<table class="table table-striped table-bordered table-radmin">
								<thead>
									<tr>
										
											<g:sortableColumn property="stageTypeCode" title="${message(code: 'flow.stageTypeCode.label', default: 'Stage Type Code')}" />
										
											<th class="header"><g:message code="flow.process.label" default="Process" /></th>
										
											<th class="header"><g:message code="flow.stage.label" default="Stage" /></th>
										
											<g:sortableColumn property="storeMessagePayload" title="${message(code: 'flow.storeMessagePayload.label', default: 'Store Message Payload')}" />
										
											<g:sortableColumn property="uuid" title="${message(code: 'flow.uuid.label', default: 'Uuid')}" />
										
										<th style="text-align:center; width: 89px">View</th>
										<th style="text-align:center; width: 63px">Edit</th>
										<th style="text-align:center; width: 77px">Delete</th>
									</tr>
								</thead>
								<tbody>
								<g:each in="${flowInstanceList}" var="flowInstance">
									<tr>
									
										<td>${fieldValue(bean: flowInstance, field: "stageTypeId")}</td>
									
										<td>${fieldValue(bean: flowInstance, field: "process.name")}</td>
									
										<td>${fieldValue(bean: flowInstance, field: "stage.name")}</td>
									
										<td><g:formatBoolean boolean="${flowInstance.storeMessagePayload}" /></td>
									
										<td>${fieldValue(bean: flowInstance, field: "uuid")}</td>
									
										<td>
											<g:link action="show" id="${flowInstance.id}" class="btn btn-small">
												<i class="icon-edit"></i>
												Show
											</g:link>
										</td>
										<td>
											<g:link action="edit" id="${flowInstance.id}" class="btn btn-small">
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
								<bootstrap:paginate total="${flowInstanceTotal}" />
								<!-- ul>
									<li class="disabled">
										<a href="#">Prev</a>
									</li>
									<li class="active">
										<a href="#">1</a>
									</li>
									<li>
										<a href="#">2</a>
									</li>
									<li>
										<a href="#">3</a>
									</li>
									<li>
										<a href="#">4</a>
									</li>
									<li>
										<a href="#">5</a>
									</li>
									<li>
										<a href="#">Next</a>
									</li>
								</ul -->
							</div>

						</div>
		
					</div>

	</body>
</html>
