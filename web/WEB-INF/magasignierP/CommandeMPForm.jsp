
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
    Passer Une Commande à Un Fournisseur 
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
                        <c:if test="${not empty sessionScope.PasserCommandeMP }">
                            <li class="active"><a data-toggle="tab" href="#transfert">Passer Votre Commande</a></li>
                            </c:if>


                    </ul>
                    <div class="space10"></div>
                    <div class="tab-content" id="myTabContent">
                        <div id="transfert" class="tab-pane fade in active">

                            <div class="row-fluid responsive"> 

                                <form method="post" action="passerCommande?vue=Commande&action=create" class="form-inline">

                                    <div class="row-fluid">

                                        <div class="control-group form-group  span3">
                                            <label class="control-label">Fournisseur</label>
                                            <div class="controls ">
                                                <select  class=" form-control span12"name="fournisseur" required>
                                                    <c:forEach items="${sessionScope.founisseurs}" var="c">  
                                                        <option >${c.getNomFounisseur()}</option>  
                                                    </c:forEach>

                                                </select>               


                                            </div>
                                        </div>

                                        <div class="control-group span3">
                                            <label class="control-label"> Categorie</label>
                                            <div class="controls ">
                                                <select class="form-control span12" id="categorietoarticlesMP" required name="categorie">
                                                    <option value="${categos.getIdCategorie()}" selected >${categos.getNomCategorie()}</option>
                                                    <c:forEach items="${sessionScope.categorieMP}" var="cc">  
                                                        <option value="${cc.getIdCategorie()}">${cc.getNomCategorie()}</option>  
                                                    </c:forEach>

                                                </select>
                                            </div>


                                        </div>

                                        <div class="control-group span4">
                                            <label class="control-label"> Article</label>
                                            <div class="controls ff" id="">
                                                <select class="form-control exampleSelect span12" id="articlesCateorieMP" required name="designation">

                                                </select>
                                            </div>
                                        </div>

                                        <div class="control-group span2">
                                            <label class="control-label"> Quantite</label>
                                            <div class="controls ">
                                                <input type="number" class="form-control span12" required name="quantite" />	
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row-fluid">

                                        <div class="control-group span1">
                                            <div class="controls ">
                                                <button type="submit" class="btn btn-success btn-primary"style="margin-top: 22px;" ><span class="icon icon-shopping-cart"></span></button>

                                            </div>
                                        </div>

                                </form>

                            </div>
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <div class="row-fluid responsive">    
                                <table class="table table-bordered table-hover table-striped display simple_print"cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>CATEGORIE</th>
                                            <th>CODE</th>
                                            <th>DESIGNATION</th>
                                            <th>QUANTITE EN STOCK</th>
                                            <th>QUANTITE COMMANDER</th>
                                            <th class="hidden">QUANTITE</th>
                                            <th>PRIX UNITAIRE</th>  
                                            <th>PRIX TOTAL</th>  
                                            <th>DATE</th>
                                            <th>DERNIERE LIVRAISON</th>
                                            <th>FOURNISSEUR</th>
                                        </tr>
                                    </thead> 
                                    <tbody>
                                        <c:set var="t" value="${0}"/>
                                        <c:forEach items="${list}" var="lis">  
                                            <tr class="produits">

                                                <td class="categories"><c:out value="${lis.getTypeProduit()}"/></td>
                                                <td class="code"><c:out value="${lis.getCodeProduit()}"/></td>
                                                <td class="designation"><c:out value="${lis.getDesignation()}"/></td>
                                                <td class="qte"><f:formatNumber value="${lis.getQuantiteEnStock()}" type="NUMBER"/></td>
                                                <td class="qteu"><f:formatNumber value="${lis.getQuantiteCommande()}" type="NUMBER"/></td>
                                                <td class="qteuc hidden"><input class="newvalCMP" type='number' id='qteu'  /></td>   
                                                <td class="qteu"><f:formatNumber value="${lis.getPrixUnitaire()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                                <td class="qteu"><f:formatNumber value="${lis.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                                <td><f:formatDate value="${lis.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           
                                                <td class="pt"><f:formatDate value="${lis.getDerniereLivraison()}"  type="Date" dateStyle="MEDIUM" /></td>
                                                <td class="founisseur"><c:out value="${lis.getIdSA().getNomFounisseur()}"/></td>
                                                <c:set var="tt" value="${lis.getPrixUnitaire() * lis.getQuantiteCommande()}"/>
                                                <c:set var="t" value="${t + tt}"/> </tr>
                                            </c:forEach>

                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th colspan="10" style="text-align: center">Total: <f:formatNumber value="${t}"type="CURRENCY" currencySymbol="FCFA"/></th>

                                        </tr>
                                    </tfoot>
                                </table> 
                            </div>
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <c:if test="${not empty message}">
                                <div class="alert alert-success  span12 text-center ">
                                    <strong>${message.getMessage()}</strong>
                                </div>
                            </c:if>


                            <div class="row-fluid">
                                <div class="span12">
                                    <div class="span2">

                                        <button  class="btn bouton btn-danger disabled btn-lg" id="deleteMP"><i class="icon-remove"></i> supprimer</button>

                                    </div>
                                    <div class="span10">
                                        <form method="post" action="passerCommande?action=save&vue=Commande" class="form-inline">

                                            <div class="form-group">
                                                <label for="example-getting-started" class="form-control-label">Responsable Controle </label>
                                                <select id="example-getting-started" multiple="multiple" class="input-mini form-control"  name="responsable" required>
                                                    <c:forEach items="${valideur}" var="cc">  

                                                        <option value="${cc.getIdPersonnel()}">${cc.getNomPrenom()}</option>  
                                                    </c:forEach>

                                                </select>
                                                <button  style="margin-left: 50px" type="submit" class="btn btn-success  btn-lg btn-large pull-righ " ><span class="icon icon-save"></span></button>
                                            </div>   
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="space20"></div>
                            <div class="space20"></div>


                        </div>
                    </div>
                </div>


            </div>
        </div>

    </div>

</div>

</div>



