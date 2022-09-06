<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<c:if test="${error != null}">
	<div class="alert alert-danger" role="alert">
		${error}
	</div>
</c:if>

<c:if test="${success != null}">
	<div class="alert alert-success" role="alert">
		${success}
	</div>
</c:if>

<h3>Lista zwierząt w ZOO</h3>

<table class="table">
	<thead>
		<tr>
			<th scope="col">Zwierze:</th>
			<th scope="col">Imię:</th>
			<th scope="col">w strefie:</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${animals}" var="item">
			<tr>
				<td scope="row">${item.type}</td>
				<td scope="row">&quot;${item.name}&quot;</td>
				<td scope="row">&quot;${item.zone}&quot;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<%@ include file="common/footer.jspf" %>