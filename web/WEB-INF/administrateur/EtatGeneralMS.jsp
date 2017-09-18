

<%-- 
    Document   : EtatMS
    Created on : 29 janv. 2017, 22:17:22
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="space20 "></div>
<div class="space20 "></div>
<div class="row-fluid ">
    <div class="space12 ">

        <form method="post" action="listeproduit?vue=editeMagasinS&action=date&niveau=5&id_magasin=${magasin.getIdMagasin()}" class="form-inline">
            <div class=" span3 ">
                <!-- Les champs texte avec le code "onclick" déclenchant le script -->
                <label for="date1 "class="form-control-label " >DU</label>

                <input type="text" class="form-control datepicker" name="date1" required>


            </div>
            <div class=" span3 ">
                <label for="date2 "class="form-control-label " >AU</label>

                <input type="text" class="form-control datepicker " name="date2" required>


            </div>
            <div class=" span3 ">

                <button type="submit" class="btn btn-success  btn-primary pull-left btn-lg btn-large" id="sendcc" >Search</button>

            </div>
        </form>

        <form class="form-inline "method="post" action="listeproduit?vue=editeMagasinS&action=redirect&niveau=5&id_magasin=${magasin.getIdMagasin()}">
            <div class="form-group ">
                <button type="submit" class="btn btn-success  btn-primary pull-left btn-lg btn-large" id="sendcc" >Tous</button>
            </div>
        </form>
    </div>

    <div class=" space12 "></div>
    <div class=" space12 "></div>

    <c:if test="${not empty message}">
        <div class="alert alert-block alert-danger" style="">

            <h4>Erreur !</h4>
            <c:out value="Erreur: ${message.getMessage()}"/> !

        </div>
    </c:if>

</div>
<div class="space20 "></div>
<div class="space20 "></div>
<c:if test="${not empty actions}">
    <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
        <thead class="text-primary">
            <tr>
                <th>DEMANDEUR</th>
                <th>DATE</th>
                <th>CATEGORIE</th>
                <th>CODE</th>
                <th>DESIGNATION</th>
                <th>QUANTITE</th>
                <th>PRIX UNITAIRE</th>
                <th>PRIX TOTAL</th>
                <th>APPARIEL</th>

            </tr>
        </thead>
        <tbody id="tbody">
            <c:set var="t" value="${0}"/>
            <c:forEach items="${operation}" var="com">
                <tr class="produits">
                    <td class="categories"><c:out value="${com.getPersonnel().getNomPrenom()}"/></td>
                    <td class="date"><f:formatDate value="${com.getDate()}" type="Date" dateStyle="MEDIUM" /></td>
                    <td class="categories"><c:out value="${com.getCategorie()}"/></td>
                    <td class="code"><c:out value="${com.getCodeProduit()}"/></td>
                    <td class="pt"><c:out value="${com.getDesignation()}"/></td>
                    <td class="qte"><f:formatNumber value="${com.getQuantite()}"type="NUMBER"/></td>
                    <td class="pu"><f:formatNumber value="${com.getPrix()}"type="CURRENCY"currencySymbol="FCFA"/></td>
                    <td class="pt"><f:formatNumber value="${com.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                    <td class="pt"><c:out value="${com.getAppariel()}"/></td>
                    <c:set var="t" value="${t+com.getPrixTotal()}"/>
                </tr>
            </c:forEach>

        </tbody>
        <tfoot>
            <tr  class="text-capitalize text-center text-success text-primary text-info">
                <td colspan="9">
                    La Somme Total est de:<f:formatNumber value="${t}" type="CURRENCY" currencySymbol="FCFA"/>
                </td>

            </tr>
        </tfoot>              
    </table>
</c:if> 

