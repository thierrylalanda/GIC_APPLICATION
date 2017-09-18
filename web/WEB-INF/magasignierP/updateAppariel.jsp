<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="space15"></div>
<table class="table table-striped table-hover table-bordered"cellspacing="0" width="100%" >
    <thead>
        <tr>
            <th>Numero</th>
            <th>type</th>
            <th>lieu</th>
            <th>fabricant</th>
            <th>numero serie</th>
            <th>model</th>

        </tr>
    </thead>
    <tbody>

        <c:forEach items="${personnel.getApparielList()}" var="app">
            <c:if test="${app.getNumeroSerie() != '000000'}">
                <tr class="">
                    <td class=""> ${app.getNumeroParck()}</td>
                    <td class=""> ${app.getTypeAppareil().getNom()}</td>
                    <td class=""> ${app.getLieu()}</td>
                    <td class=""> ${app.getFabricant()}</td>
                    <td class=""> ${app.getNumeroSerie()}</td>
                    <td class=""> ${app.getModel()}</td>

                </tr>
            </c:if>
        </c:forEach>


    </tbody>
</table>