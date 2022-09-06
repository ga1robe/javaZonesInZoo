<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<h3>Lista stref w ZOO z dziennym zapotrzebowaniem karmy</h3>

<table class="table">
	<thead>
		<tr>
			<th scope="col">Strefa:</th>
			<th scope="col">dzienne zapotrzebowanie karmy</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${zoneFeeds}" var="item">
			<tr>
				<td scope="row">&quot;${item.key}&quot;</td>
				<td scope="row">${item.value}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<%@ include file="common/footer.jspf" %>