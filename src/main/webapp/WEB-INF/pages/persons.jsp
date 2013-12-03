
<%@ include file="/common/taglibs.jsp"%>
<head>
<title><fmt:message key="personList.title" /></title>
<meta name="menu" content="PersonMenu" />
</head>
<div class="span12">
	<h2>
		<fmt:message key='personList.heading' />
	</h2>

    <security:authorize ifAnyGranted="ROLE_ADMIN">
	<div id="actions" class="form-actions">
		<a class="btn btn-primary" href="<c:url value='/personform'/>"> <i
			class="icon-plus icon-white"></i> <fmt:message key="button.add" /></a> <a
			class="btn" href="<c:url value='/mainMenu'/>"> <i class="icon-ok"></i>
			<fmt:message key="button.done" /></a>
	</div>

    </security:authorize>


	<display:table name="personList"
		class="table table-condensed table-striped table-hover" requestURI=""
		id="personList" export="true" pagesize="200">
        <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
        <display:column property="id" sortable="true" href="personform"
			media="html" paramId="id" paramProperty="id" titleKey="person.id" />
        </security:authorize>
        <security:authorize ifAnyGranted="ROLE_ANONYMOUS">
        <display:column property="id" sortable="true" titleKey="person.id"/>
        </security:authorize>
		<display:column property="id" media="csv excel xml pdf" 
 			titleKey="person.id" />
        <display:column property="department.fullName" sortable="true" titleKey="department.name"/>
		<display:column property="fullName" sortable="true" titleKey="person.name" />
		<display:column property="position.fullPosition" sortable="true" titleKey="position.name" />
        <display:column property="mobile" sortable="true" titleKey="person.mobile"/>
        <display:column property="email" sortable="true" titleKey="person.email"/>
        <display:column property="ext" sortable="true"	titleKey="person.ext" />
        <display:column property="skype" sortable="true"	titleKey="person.skype" />
		<display:setProperty name="paging.banner.item_name">
			<fmt:message key="personList.person" />
		</display:setProperty>
		<display:setProperty name="paging.banner.items_name">
			<fmt:message key="personList.persons" />
		</display:setProperty>
		<display:setProperty name="export.excel.filename">
			<fmt:message key="personList.title" />.xls</display:setProperty>
		<display:setProperty name="export.csv.filename">
			<fmt:message key="personList.title" />.csv</display:setProperty>
		<display:setProperty name="export.pdf.filename">
			<fmt:message key="personList.title" />.pdf</display:setProperty>
	</display:table>
</div>