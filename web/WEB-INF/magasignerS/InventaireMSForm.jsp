<%-- 
    Document   : UpdateProduits
    Created on : 10 déc. 2016, 17:04:40
    Author     : lalanda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>


<h3 class="page-title">
    Espace Historique Inventaire Du Magasin

</h3>
<ul class="breadcrumb">
    <li>
        <a href="RedirectionVue?vue=Accueil"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#"> Historique Inventaires</a>

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
        <h4><i class="icon-reorder"></i>Voir Vos Historiques Inventaires</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <table class="ui celled table table-hover table-responsive table-bordered simple_print "cellspacing="0" width="100%" >
                <thead class="text-primary">
                    <tr>
                        <th>Categorie</th>
                        <th>Code</th>
                        <th>Designation</th>
                        <th>Quantite Initiale</th>
                        <th>Nouvelle Quantite</th>
                        <th>Ecart Stock</th>
                        <th>Prix Unitaire</th>
                        <th>Deficits</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody id="tbody">
                    <c:forEach items="${historique}" var="lis">  
                        <tr class="produits">

                            <td class="categories"><c:out value="${lis. getCategorie()}"/></td>
                            <td class="code"><c:out value="${lis.getCodeProduit()}"/></td>
                            <td><c:out value="${lis.getDesignation()}"/></td>
                            <td class="qte"><c:out value="${lis.getLastQuantite()}"/></td>
                            <td class="pu"><f:formatNumber value="${lis.getNewQuantite()}" type="NUMBER"/></td>
                            <td class="pu"><f:formatNumber value="${lis. getEcatQuantite()}" type="NUMBER"/></td>
                            <td class="pu"><f:formatNumber value="${lis. getPrixUnitaire()}" type="CURRENCY"currencySymbol="FCFA"/></td>
                            <td class="pu"><f:formatNumber value="${lis. getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                            <td><f:formatDate value="${lis.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           


                        </tr>

                    </c:forEach>

                </tbody>
            </table> 

        </div>
    </div>
</div>
