<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h1>Pazienti in analisi</h1>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Codice fiscale</th>
      <th scope="col">Nome</th>
      <th scope="col">Cognome</th>
      <th scope="col">Ultima temperatura rilevata</th>
      <th scope="col">Data ultimo aggiornamento</th>
      <th scope="col">Azioni</th>
    </tr>
  </thead>
  <tbody>
  <c:if test = "${listaSommarioPazienti.isEmpty()}">
  	<tr>
  		<td colspan="7">Nessun paziente presente</th>
  	</tr>
  </c:if>
  <c:forEach items="${listaSommarioPazienti}" var="sommarioPaziente">
    <tr>
      <th scope="row"><c:out value="${sommarioPaziente.paziente.codiceFiscale}"/></th>
      <td><c:out value="${sommarioPaziente.paziente.nome}"/></td>
      <td><c:out value="${sommarioPaziente.paziente.cognome}"/></td>
      <td><c:out value="${sommarioPaziente.ultimaMisurazione.temperaturaCorporea}"/> °C</td>
      <td>  <fmt:formatDate pattern="dd/MM/yyyy hh:mm" value="${sommarioPaziente.dataUltimoAggiornamento}" /></td>
      <td>
      	<a class="btn btn-primary" href="<c:url value='/pazienti/${sommarioPaziente.paziente.codiceFiscale}'></c:url>" role="button">Dettaglio</a>
      </td>
    </tr>
    </c:forEach>
  </tbody>
</table>