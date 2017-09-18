<%-- 
    Document   : BonSortie
    Created on : 3 déc. 2016, 14:22:38
    Author     : lalanda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h3 class="page-title">
    Traiter Les Commandes Des Personnels 
</h3>
<ul class="breadcrumb">
    <li>
        <a href="RedirectionVue?vue=Accueil"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">Commandes Recus Pour Traitement</a>

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
        <h4><i class="icon-reorder"></i>Commandes Recus</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <div class="row-fluid">
                <div class="span3">
                    <h4 class="title grey">Sections Personnels</h4>
                    <div class="clearfix">
                        <ul class="nav nav-list faq-list">
                            <li>
                                <a class="active" href="#"><i class=" icon-signin"></i> Personnels </a>
                            </li>
                            <c:if test="${empty listepersonnel}" >

                                <li class="alert alert-block alert-danger" style="margin-left: -15px;margin-right: -15px">
                                    <h4>Oups !</h4>
                                    <c:out value="Aucun Personnel n'a Passer De Commande"/> <i class="icon-dropbox"></i>
                                </li>

                            </c:if>
                            <c:forEach items="${listepersonnel}" var="client">
                                <li><a href="commanderecus?idclient=${client.getIdPersonnel()}&action=lister&vue=Réception commande client"><i class="icon-user"></i> ${client.getNomPrenom()}</a></li>
                                </c:forEach>


                        </ul>
                    </div>
                </div>
                <c:if test="${action=='detaill'}">
                    <div class="span9">
                        <h4>Commandes De: ${clients.getNomPrenom()}</h4>
                        <div id="accordion" class="accordion in collapse" style="height: auto;">
                            <c:if test="${not empty updateCommande}">
                                <form class="form-inline" method="post" role="form" action="commanderecus?vue=Réception commande client&action=SaveupdateQuantite&id_Commande=${commandeOne.getIdCommande()}" >
                                    <div class="control-group">
                                        <label class="control-label"> New Quantite</label>
                                        <div class="controls ">
                                            <input type="number" class="form-control" value="${commandeOne.getQuantite()}" required name="quantite" />	

                                            <button type="submit" class="btn btn-success  btn-primary " >Enregistrer</button>
                                        </div>
                                    </div>
                                </form>
                            </c:if>
                            <table class="table table-hover table-bordered table-striped responsive simple_print" cellspacing="0" width="100%">
                                <thead class="text-primary">
                                    <tr>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE</th>                         
                                        <th>APPARIEL</th>
                                        <th>DATE</th>
                                        <th>Option</th>

                                    </tr>
                                </thead>
                                <tbody id="tbody">
                                    <c:forEach items="${commande}" var="com">
                                        <tr class="produits">                               
                                            <td class="pt"><c:out value="${com.getDesignations()}"/></td>
                                            <td class="qte"><f:formatNumber value="${com.getQuantite()}" type="NUMBER"/></td>                              
                                            <td class="pt"><c:out value="${com.getCodeAppareil().getNumeroParck()}"/></td>
                                            <td class="date"><f:formatDate value="${com.getDate()}" type="DATE" dateStyle="FULL"/></td>
                                            <td>
                                                <div class="btn-group">
                                                    <a title="" href="commanderecus?vue=Réception commande client&action=updateQuantite&id_Commande=${com.getIdCommande()}" class="btn btn-primary">  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                                    <c:if test="${not empty sessionScope.GeneralAdministrateur == 'OK'}">
                                                        <a class="btn btn-danger "  href="commanderecus?vue=Réception commande client&action=deleteCommandePerso&id_Commande=${com.getIdCommande()}" data-toggle="modal" data-target=""> <span class="icon"> <i class="icon-trash"></i> </span></a>
                                                    </c:if>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>

                            </table>

                        </div>
                        <div class="text-center">
                            <p class="btn btn-primary " id="detaill"> details De La Commande ...<i class="icon-arrow-down"></i></p>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="space20"></div>
                        <div class=" "> 
                            <table class="table table-hover table-bordered table-striped simple_print"cellspacing="0" width="100%" >
                                <thead class="text-primary">
                                    <tr>
                                        <th>Categorie</th>
                                        <th>Code</th>
                                        <th>Designation</th>
                                        <th>Quantite</th>
                                        <th>Prix Unitaire</th>
                                        <th>Prix Total</th>
                                        <th>Appariel</th>
                                        <th>Date</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr  class="text-capitalize text-center text-success text-primary text-info">
                                        <td colspan="8">
                                            La Somme Total est de:<f:formatNumber value="${total}" type="CURRENCY" currencySymbol="FCFA"/>
                                        </td>

                                    </tr>
                                </tfoot> 
                                <tbody id="tbody">
                                    <c:forEach items="${list}" var="com">
                                        <tr class="produits">
                                            <td class="categories"><c:out value="${com.getCategorie()}"/></td>
                                            <td class="code"><c:out value="${com.getCodeProduit()}"/></td>
                                            <td class="pt"><c:out value="${com.getDesignation()}"/></td>
                                            <td class="qte"><f:formatNumber value="${com.getQuantite()}"type="NUMBER"/></td>
                                            <td class="pu"><f:formatNumber value="${com.getPrix()}"type="NUMBER"/></td>
                                            <td class="pt"><f:formatNumber value="${com.getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA" maxFractionDigits="0"/></td>
                                            <td class="pt"><c:out value="${com.getAppariel()}"/></td>
                                            <td class="date"><f:formatDate value="${com.getDate()}" type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM"/></td>
                                        </tr>
                                    </c:forEach>

                                </tbody>

                            </table> 
                        </div>
                        <div class="space20"></div>
                        <a href="commanderecus?action=traiter&vue=Réception commande client"data-toggle="tooltip" title='traiter cette commande' >  <button  class="btn btn-success btn-lg btn-large"><i class="icon-save"></i> Traiter la commande</button></a>

                    </div>
                </c:if>

                <c:if test="${not empty message}">
                    <div class="alert alert-warning span10 text-center "><strong><c:out value="${message.getMessage()}"/></strong></div>   
                        </c:if>
            </div>
        </div>
    </div>
</div>

