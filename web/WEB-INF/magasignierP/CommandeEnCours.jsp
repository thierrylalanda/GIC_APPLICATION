
<%-- 
    Document   : EtatPeriodiqueMP
    Created on : 19 févr. 2017, 14:18:13
    Author     : messi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="page-title">
   Suivi des Commandes des Fournisseurs
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
                    <a href="javascript:;" class="icon-remove"></a>
                </span>
            </div>
            <div class="widget-body">
                <div class="bs-docs-example">
                    <div class="space10"></div>
                    <div class="tab-content" id="myTabContent">
                        <table class="table table-bordered table-hover table-striped display simple_print"cellspacing="0" width="100%">
                            <thead class="text-primary">
                                <tr>
                                    <th>CATEGORIE</th>
                                    <th>CODE</th>
                                    <th>DESIGNATION</th>
                                    <th>QUANTITE EN STOCK</th>
                                    <th>QUANTITE COMMANDER</th>
                                    <th>PRIX UNITAIRE</th>
                                    <th>PRIX TOTAL</th>
                                    <th>DATE</th>
                                    <th>DERNIERE LIVRAISON</th>
                                    <th>FOURNISSEUR</th>
                                    <th>ETAT</th>
                                </tr>
                            </thead> 
                            <tbody>
                                <c:set var="t" value="${0}"/>
                                <c:forEach items="${listNonOk}" var="li">  
                                    <tr class="produits">

                                        <td class="categories"><c:out value="${li.getTypeProduit()}"/></td>
                                        <td class="code"><c:out value="${li.getCodeProduit()}"/></td>
                                        <td class="designation"><c:out value="${li.getDesignation()}"/></td>
                                        <td class="qte"><f:formatNumber value="${li.getQuantiteEnStock()}" type="NUMBER"/></td>
                                        <td class="quantiter"><f:formatNumber value="${li.getQuantiteCommande()}" type="NUMBER"/></td>   
                                        <td class="qteu"><f:formatNumber value="${li.getPrixUnitaire()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                        <td class="qteu"><f:formatNumber value="${li.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                        <td><f:formatDate value="${li.getDate()}" type="Date" dateStyle="SHORT"/></td>                           
                                        <td class="pt"><f:formatDate value="${li.getDerniereLivraison()}"  type="Date" dateStyle="SHORT" /></td>
                                        <td class="founisseur"><c:out value="${li.getIdSA().getNomFounisseur()}"/></td>
                                        <td class="etat"><c:out value="${li.getEtat()}"/></td>
                                        <c:set var="tt" value="${li.getPrixUnitaire() * li.getQuantiteCommande()}"/>
                                        <c:set var="t" value="${t + tt}"/>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                        <p  style="text-align: center">Total: <f:formatNumber value="${t}"type="CURRENCY" currencySymbol="FCFA"/></p>

                        <div class="space20"></div>
                        <div class="space20"></div>
                        <div class="space20"></div>
                        <c:if test="${empty listNonOk}">
                            <div class="alert alert-danger  span12 text-center ">
                                <strong>Aucune Commande Pour le Moment</strong>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>

</div>



