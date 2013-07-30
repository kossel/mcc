<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="petList.title"/></title>
    <meta name="menu" content="PetMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="petList.heading"/></h2>

    <form method="get" action="${ctx}/pets" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="petList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/petform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="petList" class="table table-condensed table-striped table-hover" requestURI="" id="petList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="petform" media="html"
        paramId="id" paramProperty="id" titleKey="pet.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="pet.id"/>
    <display:column property="age" sortable="true" titleKey="pet.age"/>
    <display:column property="name" sortable="true" titleKey="pet.name"/>
    <display:column property="type" sortable="true" titleKey="pet.type"/>
	<display:column property="person.firstName" sortable="true" titleKey="person name"/>
    <display:setProperty name="paging.banner.item_name"><fmt:message key="petList.pet"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="petList.pets"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="petList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="petList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="petList.title"/>.pdf</display:setProperty>
</display:table>
