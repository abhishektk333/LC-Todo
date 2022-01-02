<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
<table class="table">
<caption>Your Todos are:  </caption>

<thead>
<tr>
<th>Description</th>
<th>Target Date</th>
<th>IsDone</th>
<th></th>
<th></th>
</tr>
</thead>

<tbody>
<c:forEach items="${todos }" var="todo">
<tr>
<td>${todo.desc }</td>
<td><fmt:formatDate value="${todo.targetDate }" pattern="dd/MM/yyyy"/></td>
<td>${todo.done }</td>
<td><a type="button" class="btn btn-primary" href="/update-todo?id=${todo.id }">Update</a></td>
<td><a type="button" class="btn btn-warning" href="/delete-todo?id=${todo.id }">Delete</a></td>
</tr>
</c:forEach>
</tbody>
</table>
<br>
<button><a href="/add-todo">Add new todo</a></button>
</div>

<%@ include file="common/footer.jspf" %>
