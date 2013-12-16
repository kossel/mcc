<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="positionList.title"/></title>
    <meta name="menu" content="PositionMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span12">
    <h2><fmt:message key="positionList.heading"/></h2>

    <form method="get" action="${ctx}/positions" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="positionList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/admin/positionform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/persons"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="positionList" class="table table-condensed table-striped table-hover" requestURI="" id="positionList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="positionform" media="html"
        paramId="id" paramProperty="id" titleKey="position.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="position.id"/>
    <display:column property="nameCH" sortable="true" titleKey="position.nameCH"/>
    <display:column property="nameES" sortable="true" titleKey="position.nameES"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="positionList.position"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="positionList.positions"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="positionList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="positionList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="positionList.title"/>.pdf</display:setProperty>
</display:table>
