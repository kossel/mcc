
<%@ include file="/common/taglibs.jsp"%>
<head>
<title><fmt:message key="personList.title" /></title>
<meta name="menu" content="PersonMenu" />
    <style>
        .centerCellText{text-align:center !important;  vertical-align:middle !important;}
        .filter-table .quick { margin-left: 0.5em; font-size: 0.8em; text-decoration: none; }
        .fitler-table .quick:hover { text-decoration: underline; }
        td.alt { background-color: #ffc; background-color: rgba(255, 255, 0, 0.2); }

        tr.striped { background-color: #f0f8ff; }
    </style>
</head>
<div class="span12">
	<h2>
		<fmt:message key='personList.heading' />
	</h2>
    <fmt:message key='opt.staffmx' />   <a href="mailto:staffmx@cosl.mx" target="_top">staffmx@cosl.mx</a> <br/>
    <fmt:message key='opt.expats' />   <a href="mailto:expats@cosl.mx" target="_top">expats@cosl.mx</a><br/>
    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
	<div id="actions" class="form-actions">
		<a class="btn btn-primary" href="<c:url value='personform'/>"> <i
			class="icon-plus icon-white"></i> <fmt:message key="button.add" /></a> <a
			class="btn" href="<c:url value='persons'/>"> <i class="icon-ok"></i>
			<fmt:message key="button.done" /></a>
	</div>

    </security:authorize>
    <fmt:message key="opt.changesNo" />: ${opt.changes} &nbsp<fmt:message key="opt.user" />: ${opt.whoLastEditPersons}&nbsp <fmt:message key="opt.time" />: ${opt.lastEditPersonsTime}
	<display:table name="personList"
		class="table table-condensed table-bordered table-hover" requestURI=""
		id="personList" export="true" pagesize="200" >
        <display:column titleKey="person.id" class="centerCellText"  headerClass="centerCellText">
            <c:out value="${personList_rowNum}"/>
        </display:column>
        <display:column property="department.fullName"  titleKey="department.name" headerClass="centerCellText" />
        <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
		    <display:column property="fullName" titleKey="person.name" href="personform" media="html" paramId="id" paramProperty="id"/>
            <display:column property="fullName" media="csv excel xml pdf" titleKey="person.name" />
        </security:authorize>
        <security:authorize ifAnyGranted="ROLE_ANONYMOUS">
            <display:column property="fullName" titleKey="person.name"/>
        </security:authorize>
		<display:column property="position.fullPosition" titleKey="position.name" />
        <display:column property="mobile"  titleKey="person.mobile" class="centerCellText" headerClass="centerCellText"/>
        <display:column property="email"  titleKey="person.email"/>
        <display:column property="ext" 	titleKey="person.ext" class="centerCellText"  headerClass="centerCellText" />
        <display:column property="skype" titleKey="person.skype" />
		<display:setProperty name="paging.banner.item_name">
			<fmt:message key="personList.persons" />
		</display:setProperty>
		<display:setProperty name="paging.banner.items_name">
			<fmt:message key="personList.persons" />
		</display:setProperty>

		<display:setProperty name="export.pdf.filename" >
			<fmt:message key="personList.title" />.pdf</display:setProperty>
	</display:table>



    <script type="text/javascript">
        $(document).ready(function() {
            var stripeTable = function(table) { //stripe the table (jQuery selector)
                table.find('tr').removeClass('striped').filter(':visible:even').addClass('striped');
            };

            $('#personList').filterTable({
                callback: function(term, table) {

                    table.find('tr').removeClass('striped').filter(':visible:even').addClass('striped');
                    //splitRowspan();
                   // rowspan();

                }
            });
           rowspan();
            stripeTable($('#personList'));


            function splitRowspan (){
                var currentrspan=0;
                var txt;
                $('#personList tr td:nth-child(2)').each(function(){


                    var rspan = $(this).attr("rowspan");
                    //    alert(rspan + "   "+currentrspan);
                    if(typeof rspan != "undefined" && rspan != currentrspan ){
                        txt = $(this).html();
                        currentrspan = rspan;

                    }

                    if(typeof rspan == "undefined" && currentrspan>=1){
                        $(this).before('<td>'+txt+'</td>');
                    }
                    currentrspan = currentrspan-1;
                    $(this).removeAttr("rowspan");


                });
            }
          //  $('#personList > tbody  > tr').each(function(row) { alert(row.html);});

            function rowspan(){
                $('#personList').each(function () {

                    var dimension_cells = new Array();
                    var dimension_col = 2;

                    // first_instance holds the first instance of identical td
                    var first_instance = null;
                    // iterate
                    // through rows
                    $(this).find('tr').each(function () {

                        // find the td of the correct column (determined by the dimension_col set above)
                        var dimension_td = $(this).find('td:nth-child(' + dimension_col + ')');

                        if (first_instance == null) {
                            // must be the first row
                            first_instance = dimension_td;

                        } else if (dimension_td.text() == first_instance.text()) {
                            // the current td is identical to the previous
                            // remove the current td
                            dimension_td.remove();
                            // increment the rowspan attribute of the first instance
                            if(typeof first_instance.attr('rowspan')== 'undefined'){
                                first_instance.attr('rowspan', 1);
                            }
                            first_instance.attr('rowspan', parseInt(first_instance.attr('rowspan')) + 1);
                        } else {
                            // this cell is different from the last
                            first_instance = dimension_td;
                            first_instance.addClass('centerCellText');
                        }

                    });
                });
            }






        });
    </script>
</div>