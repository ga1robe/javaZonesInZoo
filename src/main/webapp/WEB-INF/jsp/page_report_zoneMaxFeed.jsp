<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<h3>Strefa w ZOO z maksymalnym dziennym zapotrzebowaniu karmy</h3>

<table class="table">
	<thead>
		<tr>
			<th scope="col">Strefa:</th>
		</tr>
	</thead>
	<tbody>

			<tr>
				<td scope="row">&quot;${zoneMaxFeed}&quot;</td>
			</tr>

	</tbody>
</table>


<%@ include file="common/footer.jspf" %>