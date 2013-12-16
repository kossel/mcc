<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="departmentList.title"/></title>
    <meta name="menu" content="DepartmentMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span12">
    <h2><fmt:message key="departmentList.heading"/></h2>

    <form method="get" action="${ctx}/departments" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="departmentList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/admin/departmentform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/persons"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="departmentList" class="table table-condensed table-striped table-hover" requestURI="" id="departmentList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="departmentform" media="html"
        paramId="id" paramProperty="id" titleKey="department.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="department.id"/>
    <display:column property="nameCH" sortable="true" titleKey="department.nameCH"/>
    <display:column property="nameES" sortable="true" titleKey="department.nameES"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="departmentList.department"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="departmentList.departments"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="departmentList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="departmentList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="departmentList.title"/>.pdf</display:setProperty>
</display:table>
