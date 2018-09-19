<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Cerca città
<form action="ricercacitta">
<input type="text" name="cercacitta"><input type="submit">
</form>
<br>
<table border=1>
<tr>
<th>citta</th><th>cancella</th><th>modifica</th>
</tr>
<c:forEach items="${listacitta}" var="citta">

<tr>
	<td>
    <p>${citta.citta}</p>
	</td>
	<td>
	<form action=cancella>
	<input type="hidden" name="identd" value=${citta.id }>
	<input type="submit" name="cancella" value="cancella">
	</form>
	</td>
	<td>
	<form action=aggiungimodifica>
	<input type="hidden" name="identd" value=${citta.id }>
	<input type="submit" name="modifica" value="modifica" >
	</form>
	</td>
</tr>
</c:forEach>
</td>
</tr>
</table>
<br><br>
Aggiungi Città
<form action="aggiungimodifica">
<input type="text" name="citta" placeholder="nome citta">
<input type="text" name="countrycode" placeholder="country code">
<input type="text" name="district" placeholder="district">
<input type="number" name="population" placeholder="population">
<input type="submit" value="Inserisci citta">
</form>
</body>
</html>