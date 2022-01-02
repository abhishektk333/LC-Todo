<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
<h1>Add todos here ${name}</h1>
<form:form method="POST" modelAttribute="todo">
<form:hidden path="id"/>
<fieldset class="form-group">
<form:label path="desc">Description:</form:label>
<form:input path="desc" type="text" class="form-control" required="required"/>
<form:errors path="desc" cssClass="text-danger"/>
</fieldset>

<fieldset class="form-group">
<form:label path="targetDate">Target Date:</form:label>
<form:input path="targetDate" type="text" class="form-control" required="required" />
<form:errors path="targetDate" cssClass="text-danger"/>
</fieldset>
<button type="Submit" class="btn btn-success">Add</button>
</form:form>
</div>

<%@ include file="common/footer.jspf" %>