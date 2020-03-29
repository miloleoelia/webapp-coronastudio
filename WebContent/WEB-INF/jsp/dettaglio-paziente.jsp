<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="sticky-top" style="background-color: white;">
	<a class="btn btn-secondary mt-2" href="<c:url value='/'></c:url>" role="button">Torna all'elenco</a>
</div>
<h1>${sommario.paziente.nome} ${sommario.paziente.cognome}</h1>
<strong>${sommario.paziente.codiceFiscale}</strong>

<div class="row">
	<div class="col-sm-6">
		<canvas id="graficoFebbre"></canvas>
	</div>
	<div class="col-sm-6">
		<p>Ultima temperatura corporea rilevata:
			${sommario.ultimaMisurazione.temperaturaCorporea} °C</p>
		<p>Utlimi sintomi rilevati:</p>
		<ul>
			<c:forEach items="${sommario.ultimaMisurazione.sintomi}"
				var="sintomo">
				<li><c:out value="${sintomo.nome}"></c:out></li>
			</c:forEach>
		</ul>
	</div>
</div>

<table class="table mt-2">
	<thead>
		<tr>
			<th scope="col">Data</th>
			<th scope="col">Temperatura corporea</th>
			<th scope="col">Sintomi</th>
			<th scope="col">Note</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${sommario.misurazioni}" var="misurazione">
			<tr>
				<th scope="row"><fmt:formatDate pattern="dd/MM hh:mm"
						value="${misurazione.data}" /></th>
				<td><c:out value="${misurazione.temperaturaCorporea}" /></td>
				<td>
					<ul>
						<c:forEach items="${misurazione.sintomi}"
							var="sintomo">
							<li><c:out value="${sintomo.nome}"></c:out></li>
						</c:forEach>
					</ul>
				</td>
				<td><c:out value="${misurazione.note}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>




















<script>
$(document).ready(function(){
	var canvasFebbre = $('#graficoFebbre');
	var graficoFebbre = new Chart(canvasFebbre, {
	    type: 'line',
	    data: {
	        labels: ${labelsGrafico},
	        datasets: [{
	        	label: 'Temperatura corporea',
	            data: ${valoriGrafico},
	            backgroundColor : 'rgba(255, 0, 0, 0.1)',
	            fill:false,
	            borderColor: "rgb(255, 0, 0)",
	            lineTension:0.1
	        }]
	    }
	});
});
</script>
