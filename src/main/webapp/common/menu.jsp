<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" permissions="rolesAdapter">
<div class="nav-collapse collapse">
<ul class="nav">

    <menu:displayMenu name="MainMenu"/>
    <menu:displayMenu name="UserMenu"/>
    <menu:displayMenu name="PersonMenu"/>
    <menu:displayMenu name="AdminMenu"/>
    <c:if test="${empty pageContext.request.remoteUser}">
        <li>
            <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
        </li>
    </c:if>
    <menu:displayMenu name="Logout"/>
    
    
    
    
      <!--Pet-START-->
   <!--menu:displayMenu name="PetMenu"/>
    <!--Pet-END-->
    <!--Position-START
    <!--menu:displayMenu name="PositionMenu"/>
    <!--Position-END-->
    <!--Department-START
    <!--menu:displayMenu name="DepartmentMenu"/>-->
    <!--Department-END-->
</ul>
</div>
</menu:useMenuDisplayer>
