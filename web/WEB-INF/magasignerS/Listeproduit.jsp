
<%-- 
    Document   : Listeproduit
    Created on : 19 nov. 2016, 15:53:48
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="space20"></div>
<h3 class="page-title">
    ETAT DU STOCK ACTUEL :  ${sessionScope.magasinMS.getNomMagasin()}
</h3>
<ul class="breadcrumb">
    <li>
        <a href="RedirectionVue?vue=Accueil"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">${vue}: ${sessionScope.magasinMS.getNomMagasin()}</a>

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
<div class="widget black">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i> ETAT DU STOCK : ${sessionScope.magasinMS.getNomMagasin()} </h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">

            <div class="row-fluid">
                <c:if test="${actions=='all'}">
                    <table class="table table-hover table-bordered simple_print" cellspacing="0" width="100%" id="">
                        <thead class=" thead-inverse">
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
                                <tr class="produit">
                                    <c:forEach items="${stockMS}"var="r">

                                        <td scope="row"> <c:out value="${r.getCategorie().getNomCategorie()}"/> </td>
                                        <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                                        <td><c:out value="${r.getDesignation()}"/></td>
                                        <td><f:formatNumber value="${r.getQuantite()}" type="NUMBER"/></td>
                                        <td><f:formatNumber value="${r.getPrixUnitaire()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                        <td><f:formatNumber value="${r.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                        <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                                        <c:set var="t" value="${t + r.getPrixTotal()}"/>
                                    </tr>
                                </c:forEach>
                            </c:if>

                            <c:if test="${cate=='un'}" >
                                <c:forEach  items="${categorie}"  begin="${posi}" var="c" end="${posi}">

                                    <tr class="produit">

                                        <c:forEach items="${c.getStockproduitMSList()}"var="rr">

                                            <td><c:out value="${c.getNomCategorie()}"/> </td>
                                            <td class="code"><c:out value="${rr.getCodeProduit()}"/></td>
                                            <td><c:out value="${rr.getDesignation()}"/></td>
                                            <td><f:formatNumber value="${rr.getQuantite()}" type="NUMBER"/></td>
                                            <td><f:formatNumber value="${rr.getPrixUnitaire()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                            <td><f:formatNumber value="${r.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                            <td><f:formatDate value="${rr.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                                        </tr>

                                    </c:forEach>
                                </c:forEach>
                            </c:if>
                            <c:if test="${produit=='produit'}" >

                                <tr class="produit">


                                    <td><c:out value="${nom_cat}"/> </td>

                                    <td class="code"><c:out value="${stock.getCodeProduit()}"/></td>
                                    <td><c:out value="${stock.getDesignation()}"/></td>
                                    <td><c:out value="${stock.getQuantite()}"/></td>
                                    <td><f:formatNumber value="${stock.getPrixUnitaire()} "type="CURRENCY"/></td>
                                    <td><f:formatNumber value="${stock.getPrixTotal()}"type="CURRENCY"/></td>
                                    <td><f:formatDate value="${stock.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                                </tr>

                            </c:if>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th colspan="7" style="text-align: center">Total: <f:formatNumber value="${t}"type="CURRENCY" currencySymbol="FCFA"/></th>

                            </tr>
                        </tfoot>
                    </table> 


                </c:if>

            </div>

        </div>
    </div>
</div>
