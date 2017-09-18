
<%-- 
    Document   : Listeproduit
    Created on : 19 nov. 2016, 15:53:48
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h3 class="page-title">
    Etat de Stock Du Magasin
</h3>
<ul class="breadcrumb">
    <li>
        <a href="StatistiqueMP?vue=Accueil&action=autre"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">${vue}</a>

    </li>

    <li class="pull-right search-wrap">
        <form action="" class="hidden-phone">
            <div class="input-append search-input-area">
                <input class="" id="appendedInputButton" type="text">
                <button class="btn" type="button"><i class="icon-search"></i> </button>
            </div>
        </form>
    </li>
</ul>


<div class="widget black">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i> Stock Actuel du Magasin</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <table class="table table-hover table-bordered simple_print" id=""cellspacing="0" width="100%">
                <thead>
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
                    <c:if test="${all=='tous'}" >
                        <c:set var="t" value="${0}"/>
                        <c:forEach items="${stockMP}"var="r">
                            <tr>
                                <td > <c:out value="${r.getCategorie().getNomCategorie()}"/> </td>
                                <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                                <td><c:out value="${r.getDesignation()}"/></td>
                                <td><f:formatNumber value="${r.getQuantite()}" type="NUMBER"/></td>
                                <td><f:formatNumber value="${r.getPrixUnitaire()}" type="CURRENCY"/></td>
                                <td><f:formatNumber value="${r.getPrixUnitaire() * r.getQuantite()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                                <c:set var="tt" value="${r.getPrixUnitaire() * r.getQuantite()}"/>
                                <c:set var="t" value="${t + tt}"/> </tr>
                            </c:forEach>
                        </c:if>
                </tbody>
                <tfoot>
                    <tr>
                        <th colspan="7" style="text-align: center">Total: <f:formatNumber value="${t}"type="CURRENCY" currencySymbol="FCFA"/></th>

                    </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>

