<%-- 
    Document   : UpdateProduits
    Created on : 10 déc. 2016, 17:04:40
    Author     : lalanda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>


<h3 class="page-title">
    ${vue}
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

        
<!-- BEGIN INLINE TABS PORTLET-->
<div class="widget black">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i> Faite Vos Inventaires</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">

            <table class="table  table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                <thead >
                    <tr>
                    <tr>
                        <th>CATEGORIE</th>
                        <th>CODE</th>
                        <th>DESIGNATION</th>
                        <th>QUANTITE</th>
                        <th class="hidden">QUANTITES</th>
                        <th>PRIX UNITAIRE</th>
                        <th>PRIX TOTAL</th>
                        <th>DATE LIVRAISON</th>
                    </tr>
                    </tr>
                </thead>
                <tbody id="tbody">

                    <c:forEach items="${listeStockMP}"var="r">
                        <tr class="produits">
                            <td > <c:out value="${r.getCategorie().getNomCategorie()}"/> </td>
                            <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                            <td><c:out value="${r.getDesignation()}"/></td>
                            <td class="qteu"><f:formatNumber value="${r.getQuantite()}"type="NUMBER"/></td>
                            <td class="qteuc hidden"><input class="newvalcm" type='number' id='qteu'/></td>
                            <td><f:formatNumber value="${r.getPrixUnitaire()}"type="NUMBER" /></td>
                            <td><f:formatNumber value="${r.getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA" maxFractionDigits="${0}" groupingUsed="${true}"/></td>
                            <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                        </tr>

                    </c:forEach>

                </tbody>
            </table> 

        </div>
    </div>
</div>






