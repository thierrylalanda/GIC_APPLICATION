<%-- 
    Document   : newjsp
    Created on : 26 janv. 2017, 13:56:45
    Author     : messi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<h3 class="page-title">
    ${vue}
</h3>
<ul class="breadcrumb">
    <li>
        <a href="RedirectionVue?vue=Accueil"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">${vue}</a>

    </li>

    <li class="pull-right search-wrap">
        <form action="search_result.html" class="hidden-phone">
            <div class="input-append search-input-area">
                <input class="" id="appendedInputButton" type="text">
                <button class="btn" type="button"><i class="icon-search"></i> </button>
            </div>
        </form>
    </li>
</ul>
<div class="row responsive" style="margin-right: 5px;margin-left: 5px">    
    <c:if test="${not empty sessionScope.sms}">
        <table class="table table-hover  table-responsive simple_print">
            <thead class="text-primary">

                <tr>
                    <th>CATEGORIE</th>
                    <th>CODE</th>
                    <th>DESIGNATION</th>
                    <th>QUANTITE</th>
                    <th>PRIX UNITAIRE</th>
                    <th>PRIX TOTAL</th>
                    <th>DATE LIVRAISON</th>
                </tr>

            </thead>
            <tbody id="tbody">
                <c:forEach items="${sessionScope.sms}"var="r">
                    <tr class="produits">
                        <td > <c:out value="${r.getCategorie().getNomCategorie()}"/> </td>
                        <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                        <td><c:out value="${r.getDesignation()}"/></td>
                        <td class="qteu alert-danger"><f:formatNumber value="${r.getQuantite()}"type="NUMBER"/></td>
                        <td><f:formatNumber value="${r.getPrixUnitaire()}"type="NUMBER"/></td>
                        <td><f:formatNumber value="${r.getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                        <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                    </tr>

                </c:forEach>

            </tbody>
        </table><br><br>
        <a href="RedirectionVue?vue=Commande produits"> <button  class="btn btn-success  btn-primary pull-right " >Passer la Commande</button></a>
    </c:if>

    <c:if test="${not empty sessionScope.smss}">
        <table class="table table-hover  table-responsive simple_print">
            <thead class="text-primary">
                <tr>
                <tr>
                    <th>CATEGORIE</th>
                    <th>CODE</th>
                    <th>DESIGNATION</th>
                    <th>QUANTITE</th>
                    <th>PRIX UNITAIRE</th>
                    <th>PRIX TOTAL</th>
                    <th>DATE LIVRAISON</th>
                </tr>
                </tr>
            </thead>
            <tbody id="tbody">
                <c:forEach items="${sessionScope.smss}"var="r">
                    <tr class="produits">
                        <td > <c:out value="${r.getCategorie().getNomCategorie()}"/> </td>
                        <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                        <td><c:out value="${r.getDesignation()}"/></td>
                        <td class="qteu alert-danger"><f:formatNumber value="${r.getQuantite()}"type="NUMBER"/></td>
                        <td><f:formatNumber value="${r.getPrixUnitaire()}"type="NUMBER"/></td>
                        <td><f:formatNumber value="${r.getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                        <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                    </tr>

                </c:forEach>

            </tbody>
        </table> 
        <a href="RedirectionVue?vue=Commande produits"> <button  class="btn btn-success  btn-primary pull-right " >Passer la Commande</button></a>
    </c:if>
</div>
