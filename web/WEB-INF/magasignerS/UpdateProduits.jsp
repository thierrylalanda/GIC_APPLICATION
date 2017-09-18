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
    Espace Inventaire Du Magasin
</h3>
<ul class="breadcrumb">
    <li>
        <a href="RedirectionVue?vue=Accueil"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">Inventaire</a>

    </li>

    <li class="pull-right search-wrap">
        <form action="#" class="hidden-phone">
            <div class="input-append search-input-area">
                <input class="" id="appendedInputButton" type="text">
                <button class="btn" type="button"><i class="icon-search"></i> </button>
            </div>
        </form>
    </li>
</ul>
<div class="widget black">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i>Effectuer Vos Inventaires </h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <div class="row-fluid">  

                <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                    <thead class="text-primary">

                        <tr>
                            <th>CATEGORIE</th>
                            <th>CODE</th>
                            <th>DESIGNATION</th>
                            <th>QUANTITE</th>
                            <th class="hidden">QUANTITE</th>
                            <th>PRIX UNITAIRE</th>
                            <th>PRIX TOTAL</th>
                            <th>DATE LIVRAISON</th>
                        </tr>

                    </thead>
                    <tbody id="tbody">
                        <c:forEach items="${stockMS}"var="r">
                            <tr class="produits">
                                <td > <c:out value="${r.getCategorie().getNomCategorie()}"/> </td>
                                <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                                <td><c:out value="${r.getDesignation()}"/></td>
                                <td class="qteu"><f:formatNumber value="${r.getQuantite()}"type="NUMBER"/></td>
                                <td class="qteuc hidden"><input class="newval focus" autofocus="true" type='number' id=''  /></td>
                                <td><f:formatNumber value="${r.getPrixUnitaire()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                <td><f:formatNumber value="${r.getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
            </div>
        </div>
    </div>
</div>






