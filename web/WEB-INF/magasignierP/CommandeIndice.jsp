
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
    Suivi de Validation des Commandes par les Controleurs
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
                    <ul class="nav nav-tabs" id="myTab">
                        <c:if test="${not empty sessionScope.CommandeTraiterMP }">
                            <li ><a  href="passerCommande?vue=CommandeTraiter&action=init">Commande Entièrement Traité</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.CommandeRecepMP}">
                            <li class="active"><a href="passerCommande?vue=Etat Traitement Commandes Passés&action=init">Commande En Cour De Traitement</a></li>
                            </c:if>

                    </ul>
                    <div class="space10"></div>
                    <div class="tab-content" id="myTabContent">
                        <div  class="tab-pane fade in active">

                            <table class="table table-bordered table-hover  table-striped display simple_print"cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>CATEGORIE</th>
                                        <th>CODE</th>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE EN STOCK</th>
                                        <th>QUANTITE COMMANDER</th>  
                                        <th>PRIX TOTAL</th>
                                        <th>DATE</th>
                                        <th>DERNIERE LIVRAISON</th>
                                        <th>FOURNISSEUR</th>
                                        <th>ETAT</th>
                                            <c:forEach items="${listt.get(0).getVisaChefList()}" var="vv" >                                 
                                            <th>${vv.getControleur().getNomPrenom()}</th>
                                            <th>Observation</th>   
                                            </c:forEach>

                                    </tr>
                                </thead> 
                                <tbody>
                                    <c:set var="t" value="${0}"/>
                                    <c:forEach items="${listt}" var="liss">  
                                        <tr class="produits">

                                            <td class="categories"><c:out value="${liss.getTypeProduit()}"/></td>
                                            <td class="code"><c:out value="${liss.getCodeProduit()}"/></td>
                                            <td class="designation"><c:out value="${liss.getDesignation()}"/></td>
                                            <td class="qte"><f:formatNumber value="${liss.getQuantiteEnStock()}" type="NUMBER"/></td>
                                            <td class="quantiter"><f:formatNumber value="${liss.getQuantiteCommande()}" type="NUMBER"/></td>  
                                            <td class="qteu"><f:formatNumber value="${liss.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                            <td><f:formatDate value="${liss.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           
                                            <td class="pt"><f:formatDate value="${liss.getDerniereLivraison()}"  type="Date" dateStyle="MEDIUM" /></td>
                                            <td class="founisseur"><c:out value="${liss.getIdSA().getNomFounisseur()}"/></td>
                                            <td class="etat"><c:out value="${liss.getEtat()}"/></td>
                                            <c:forEach items="${liss.getVisaChefList()}" var="visa" >                                 
                                                <td class="visa"><c:out value="${visa.getDecision()}"/></td>
                                                <td class="visa"><c:out value="${visa.getObservation()}"/></td>
                                            </c:forEach>
                                            <c:set var="tt" value="${liss.getPrixUnitaire() * liss.getQuantiteCommande()}"/>
                                            <c:set var="t" value="${t + tt}"/>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table> 
                            <p  style="text-align: center">Total: <f:formatNumber value="${t}"type="CURRENCY" currencySymbol="FCFA"/></p>

                            <div class="space20"></div>
                            <div class="space20"></div>
                            <c:if test="${not empty message3}">
                                <div class="alert alert-danger  span12 text-center ">
                                    <strong>${message3.getMessage()}</strong>
                                </div>
                            </c:if>
                        </div>
                        <div class="space20"></div>
                    </div>
                </div>


            </div>
        </div>

    </div>

</div>

</div>





