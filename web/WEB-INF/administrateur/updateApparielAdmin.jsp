<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="space15"></div>
<table class="table table-striped table-hover table-bordered" >
    <thead>
        <tr>
            <th>Numero</th>
            <th>type</th>
            <th>statut</th>
            <th>mise en rebus</th>
            <th>numero serie</th>
            <th>model</th>
            <th>valeur</th>
            <th>en service le</th>

        </tr>
    </thead>
    <tbody>

        <c:forEach items="${personnel.getApparielList()}" var="app">
            <tr class="">
                <td class=""> ${app.getNumeroParck()}</td>
                <td class=""> ${app.getTypeAppariel()}</td>
                <td class=""> ${app.getStatut()}</td>
                <td class=""> ${app.getMisAuRebut()}</td>
                <td class=""> ${app.getNumeroSerie()}</td>
                <td class=""> ${app.getModel()}</td>
                <td class=""> ${app.getValeur()}</td>
                <td class="center"> ${app.getMisEnService()}</td>

            </tr>
        </c:forEach>


    </tbody>
</table>