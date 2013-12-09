<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="departmentDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='departmentDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="departmentList.department"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="departmentDetail.heading"/></h2>
    <fmt:message key="departmentDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="department" method="post" action="departmentform" cssClass="well form-horizontal"
           id="departmentForm" onsubmit="return validateDepartment(this)">
<form:hidden path="id"/>
    <spring:bind path="department.nameCH">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="department.nameCH" styleClass="control-label"/>
        <div class="controls">
            <form:input path="nameCH" id="nameCH"  maxlength="150"/>
            <form:errors path="nameCH" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="department.nameES">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="department.nameES" styleClass="control-label"/>
        <div class="controls">
            <form:input path="nameES" id="nameES"  maxlength="150"/>
            <form:errors path="nameES" cssClass="help-inline"/>
        </div>
    </div>

    <spring:bind path="department.ord">
        <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
    <appfuse:label key="department.ord" styleClass="control-label"/>
    <div class="controls">
        <form:input path="ord" id="ord"  maxlength="150"/>
        <form:errors path="ord" cssClass="help-inline"/>
    </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty department.id}">
            <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="department" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['departmentForm']).focus();
    });
</script>
