<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="petDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='petDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="petList.pet"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<!-- <div class="span2"> -->
<%--     <h2><fmt:message key="petDetail.heading"/></h2> --%>
<%--     <fmt:message key="petDetail.message"/> --%>
<!-- </div> -->

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="pet" method="post" action="petform" cssClass="well form-horizontal"
           id="petForm" onsubmit="return validatePet(this)">
<form:hidden path="id"/>
    <spring:bind path="pet.age">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pet.age" styleClass="control-label"/>
        <div class="controls">
            <form:input path="age" id="age"  maxlength="255"/>
            <form:errors path="age" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pet.name">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pet.name" styleClass="control-label"/>
        <div class="controls">
            <form:input path="name" id="name"  maxlength="100"/>
            <form:errors path="name" cssClass="help-inline"/>
        </div>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <div class="control-group">
    <appfuse:label key="pet.name" styleClass="control-label"/>
    <div class="controls">
    <form:select path="person.id" items="${personlist}" itemLabel="firstName" itemValue="id"/>
    </div>
    </div>
    <spring:bind path="pet.type">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pet.type" styleClass="control-label"/>
        <div class="controls">
            <form:input path="type" id="type"  maxlength="255"/>
            <form:errors path="type" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty pet.id}">
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

<v:javascript formName="pet" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['petForm']).focus();
    });
</script>
