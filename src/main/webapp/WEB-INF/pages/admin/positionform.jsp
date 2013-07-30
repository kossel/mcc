<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="positionDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='positionDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="positionList.position"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="positionDetail.heading"/></h2>
    <fmt:message key="positionDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="position" method="post" action="positionform" cssClass="well form-horizontal"
           id="positionForm" onsubmit="return validatePosition(this)">
<form:hidden path="id"/>
    <spring:bind path="position.nameCH">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="position.nameCH" styleClass="control-label"/>
        <div class="controls">
            <form:input path="nameCH" id="nameCH"  maxlength="150"/>
            <form:errors path="nameCH" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="position.nameES">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="position.nameES" styleClass="control-label"/>
        <div class="controls">
            <form:input path="nameES" id="nameES"  maxlength="150"/>
            <form:errors path="nameES" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty position.id}">
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

<v:javascript formName="position" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['positionForm']).focus();
    });
</script>
