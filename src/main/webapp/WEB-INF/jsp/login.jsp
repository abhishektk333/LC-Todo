<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container" style=margin-top:40px;>

	<font color="red">${errorMessage}</font>
	<form method="post">
	<div>Name : <input type="text" name="name" /></div>
	<br>
	<div>Password : <input type="password" name="password" /> </div>
	<br>
	<input type="submit" />
	</form>
</div>
<%@ include file="common/footer.jspf" %>

