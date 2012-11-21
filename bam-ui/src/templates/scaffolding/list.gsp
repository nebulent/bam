<% import grails.persistence.Event %>
<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bam">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
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
						<g:if test="\${flash.message}">
						<bootstrap:alert class="alert-info">\${flash.message}</bootstrap:alert>
						</g:if>
					</div>
					<div class="row-fluid hidden-tablet hidden-phone">
						<div class="span12">
							<h4 class="title"><g:message code="default.list.label" args="[entityName]" /></h4>
							<div class="squiggly-border"></div>

							<table class="table table-striped table-bordered table-radmin">
								<thead>
									<tr>
										<%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
											allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
											props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && it.type != null && !Collection.isAssignableFrom(it.type) }
											Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
											props.eachWithIndex { p, i ->
												if (i < 6) {
													if (p.isAssociation()) { %>
											<th class="header"><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></th>
										<%      } else { %>
											<g:sortableColumn property="${p.name}" title="\${message(code: '${domainClass.propertyName}.${p.name}.label', default: '${p.naturalName}')}" />
										<%  }   }   } %>
										<th style="text-align:center; width: 89px">View</th>
										<th style="text-align:center; width: 63px">Edit</th>
										<th style="text-align:center; width: 77px">Delete</th>
									</tr>
								</thead>
								<tbody>
								<g:each in="\${${propertyName}List}" var="${propertyName}">
									<tr>
									<%  props.eachWithIndex { p, i ->
									        if (i < 6) {
												if (p.type == Boolean || p.type == boolean) { %>
										<td><g:formatBoolean boolean="\${${propertyName}.${p.name}}" /></td>
									<%          } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
										<td><g:formatDate date="\${${propertyName}.${p.name}}" /></td>
									<%          } else { %>
										<td>\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</td>
									<%  }   }   } %>
										<td>
											<g:link action="show" id="\${${propertyName}.id}" class="btn btn-small">
												<i class="icon-edit"></i>
												Show
											</g:link>
										</td>
										<td>
											<g:link action="edit" id="\${${propertyName}.id}" class="btn btn-small">
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
								<bootstrap:paginate total="\${${propertyName}Total}" />
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
