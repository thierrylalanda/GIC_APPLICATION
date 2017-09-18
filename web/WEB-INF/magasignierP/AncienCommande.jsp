<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h3 class="page-title">
    Historique des Commandes des Fournisseurs
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



<div class="row-fluid">
    <div class="span12">
        <!-- BEGIN INLINE TABS PORTLET-->
        <div class="widget black">
            <div class="widget-title">
                <h4><i class="icon-reorder"></i> Espace Magasin: ${magasin.getNomMagasin()}</h4>
                <span class="tools">
                    <a href="javascript:;" class="icon-chevron-down"></a>
                </span>
            </div>
            <div class="widget-body">
                <div class="bs-docs-example">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a data-toggle="tab" href="#bordereau">Historiques Commandes Fournisseur</a></li>
                    </ul>

                    <div class="tab-content" id="myTabContent">
                        <div id="bordereau" class="tab-pane fade in active">

                            <table class="table table-bordered table-hover table-striped display simple_print"cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>CATEGORIE</th>
                                        <th>CODE</th>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE EN STOCK</th>
                                        <th>QUANTITE COMMANDER</th>
                                        <th>PRIX UNITAIRE</th>  
                                        <th>PRIX TOTAL</th>  
                                        <th>DATE LIVRAISON</th>
                                        <th>DERNIERE LIVRAISON</th>
                                        <th>FOURNISSEUR</th>
                                        <th>Livraison</th>
                                    </tr>
                                </thead> 
                                <tbody>
                                    <c:forEach items="${listCommandeOK}" var="liss">  
                                        <tr class="produits">

                                            <td class="categories"><c:out value="${liss.getTypeProduit()}"/></td>
                                            <td class="code"><c:out value="${liss.getCodeProduit()}"/></td>
                                            <td class="designation"><c:out value="${liss.getDesignation()}"/></td>
                                            <td class="qte"><f:formatNumber value="${liss.getQuantiteEnStock()}" type="NUMBER"/></td>
                                            <td class="qteu"><f:formatNumber value="${liss.getQuantiteCommande()}" type="NUMBER"/></td>
                                            <td class="qteu"><f:formatNumber value="${liss.getPrixUnitaire()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                            <td class="qteu"><f:formatNumber value="${liss.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                            <td><f:formatDate value="${liss.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           
                                            <td class="pt"><f:formatDate value="${liss.getDerniereLivraison()}"  type="Date" dateStyle="MEDIUM" /></td>
                                            <td class="founisseur"><c:out value="${liss.getIdSA().getNomFounisseur()}"/></td>
                                            <td class="founisseur"><c:out value="${liss.getLivraison()}"/></td>
                                        </tr>

                                    </c:forEach>

                                </tbody>
                            </table> 

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END INLINE TABS PORTLET-->
    </div>
</div>
