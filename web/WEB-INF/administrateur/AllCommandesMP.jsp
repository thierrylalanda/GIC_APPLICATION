
<%-- 
    Document   : EtatPeriodiqueMP
    Created on : 19 févr. 2017, 14:18:13
    Author     : messi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<div class="row-fluid">
    <div class="span12">
        <!-- BEGIN INLINE TABS PORTLET-->
        <div class="widget orange">
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
                        <c:if test="${not empty sessionScope.lien35 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="active"><a data-toggle="tab" href="#commandeNonrecep">Commandes Non Receptionners</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien36 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li ><a data-toggle="tab" href="#commandereceptionner">Commandes Deja Receptionner</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien37 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li ><a data-toggle="tab" href="#commandeTraiter">Commandes Traités</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien38 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li ><a data-toggle="tab" href="#transfert">Suivi Des Transferts Effectuers</a></li>
                            </c:if>
                    </ul>
                    <div class="space10"></div>
                    <div class="tab-content" id="myTabContent">
                        <div id="commandeNonrecep" class="tab-pane fade in active">


                            <table class="table table-hover table-responsive table-bordered simple_print" cellspacing="0" width="100%">
                                <thead class="text-primary">
                                    <tr>
                                        <th>CATEGORIE</th>
                                        <th>CODE</th>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE EN STOCK</th>
                                        <th>QUANTITE COMMANDER</th>                  
                                        <th>DATE LIVRAISON</th>
                                        <th>DERNIERE LIVRAISON</th>
                                        <th>FOURNISSEUR</th>
                                        <th>ETAT</th>
                                    </tr>
                                </thead> 
                                <tbody>
                                    <c:forEach items="${listNonOk}" var="li">  
                                        <tr class="produits">

                                            <td class="categories"><c:out value="${li.getTypeProduit()}"/></td>
                                            <td class="code"><c:out value="${li.getCodeProduit()}"/></td>
                                            <td class="designation"><c:out value="${li.getDesignation()}"/></td>
                                            <td class="qte"><f:formatNumber value="${li.getQuantiteEnStock()}" type="NUMBER"/></td>
                                            <td class="quantiter"><f:formatNumber value="${li.getQuantiteCommande()}" type="NUMBER"/></td>                                           
                                            <td><f:formatDate value="${li.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           
                                            <td class="pt"><f:formatDate value="${li.getDerniereLivraison()}"  type="Date" dateStyle="MEDIUM" /></td>
                                            <td class="founisseur"><c:out value="${li.getIdSA().getNomFounisseur()}"/></td>
                                            <td class="etat"><c:out value="${li.getEtat()}"/></td>
                                        </tr>

                                    </c:forEach>

                                </tbody>
                            </table>
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <c:if test="${not empty message2}">
                                <div class="alert alert-success  span12 text-center ">
                                    <strong>${message2.getMessage()}</strong>
                                </div>
                            </c:if>
                        </div>
                        <div id="commandereceptionner" class="tab-pane fade">

                            <table class="table table-hover table-responsive table-bordered simple_print" cellspacing="0" width="100%">
                                <thead class="text-primary">
                                    <tr>
                                        <th>CATEGORIE</th>
                                        <th>CODE</th>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE EN STOCK</th>
                                        <th>QUANTITE COMMANDER</th>                  
                                        <th>DATE LIVRAISON</th>
                                        <th>DERNIERE LIVRAISON</th>
                                        <th>FOURNISSEUR</th>
                                        <th>ETAT</th>
                                            <c:forEach items="${lit}" var="vv">                                 
                                            <th>${vv.getValidation().getAffectationControleursList().get(1).getPersonnel().getNomPrenom()}</th>
                                            <th>Observation</th>   
                                            </c:forEach>

                                    </tr>
                                </thead> 
                                <tbody>
                                    <c:forEach items="${list}" var="lis">  
                                        <tr class="produits">

                                            <td class="categories"><c:out value="${lis.getTypeProduit()}"/></td>
                                            <td class="code"><c:out value="${lis.getCodeProduit()}"/></td>
                                            <td class="designation"><c:out value="${lis.getDesignation()}"/></td>
                                            <td class="qte"><f:formatNumber value="${lis.getQuantiteEnStock()}" type="NUMBER"/></td>
                                            <td class="quantiter"><f:formatNumber value="${lis.getQuantiteCommande()}" type="NUMBER"/></td>                                           
                                            <td><f:formatDate value="${lis.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           
                                            <td class="pt"><f:formatDate value="${lis.getDerniereLivraison()}"  type="Date" dateStyle="MEDIUM" /></td>
                                            <td class="founisseur"><c:out value="${lis.getIdSA().getNomFounisseur()}"/></td>
                                            <td class="etat"><c:out value="${lis.getEtat()}"/></td>
                                            <c:forEach items="${lis.getVisaChefList()}" var="visa">                                 
                                                <td class="visa"><c:out value="${visa.getDecision()}"/></td>
                                                <td class="visa"><c:out value="${visa.getObservation()}"/></td>

                                            </c:forEach>

                                        </tr>

                                    </c:forEach>

                                </tbody>
                            </table> 
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <c:if test="${not empty message3}">
                                <div class="alert alert-success  span12 text-center ">
                                    <strong>${message3.getMessage()}</strong>
                                </div>
                            </c:if>
                        </div>
                        <div id="commandeTraiter" class="tab-pane fade">
                            <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                                <thead class="text-primary">
                                    <tr>
                                        <th>CATEGORIE</th>
                                        <th>CODE</th>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE EN STOCK</th>
                                        <th>QUANTITE COMMANDER</th>                  
                                        <th>DATE LIVRAISON</th>
                                        <th>DERNIERE LIVRAISON</th>
                                        <th>FOURNISSEUR</th>
                                            <c:forEach items="${li}" var="v">                                 
                                            <th>${v.getValidation().getAffectationControleursList().get(0).getPersonnel().getNomPrenom()}</th>
                                            <th>Observation</th>   
                                            </c:forEach>

                                    </tr>
                                </thead> 
                                <tbody>
                                    <c:forEach items="${listesOK}" var="lis">  
                                        <tr class="produits">

                                            <td class="categories"><c:out value="${lis.getTypeProduit()}"/></td>
                                            <td class="code"><c:out value="${lis.getCodeProduit()}"/></td>
                                            <td class="designation"><c:out value="${lis.getDesignation()}"/></td>
                                            <td class="qte"><f:formatNumber value="${lis.getQuantiteEnStock()}" type="NUMBER"/></td>
                                            <td class="quantiter"><f:formatNumber value="${lis.getQuantiteCommande()}" type="NUMBER"/></td>                                           
                                            <td><f:formatDate value="${lis.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           
                                            <td class="pt"><f:formatDate value="${lis.getDerniereLivraison()}"  type="Date" dateStyle="MEDIUM" /></td>
                                            <td class="founisseur"><c:out value="${lis.getIdSA().getNomFounisseur()}"/></td>
                                            <c:forEach items="${lis.getVisaChefList()}" var="a"> 
                                                <td class="visa"><c:out value="${a.getDecision()}"/></td>
                                                <td class="visa"><c:out value="${a.getObservation()}"/></td>                         
                                            </c:forEach>

                                        </tr>

                                    </c:forEach>

                                </tbody>
                            </table> 
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <c:if test="${not empty message1}">
                                <div class="alert alert-success  span12 text-center ">
                                    <strong>${message1.getMessage()}</strong>
                                </div>
                            </c:if>
                        </div>
                        <div id="transfert" class="tab-pane fade">
                            <table class="table tabl table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                                <thead class="text-primary ">
                                    <tr>
                                        <th class="hidden">id</th>
                                        <th>CATEGORIE</th>
                                        <th>CODE</th>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE</th>
                                        <th>PRIX UNITAIRE</th>
                                        <th>PRIX TOTAL</th>
                                        <th>DATE LIVRAISON</th>                  
                                        <th>Etat du transfert</th>
                                        <th>Raison</th>

                                    </tr>
                                </thead>
                                <tbody id="tbody">
                                    <c:forEach items="${transfert}" var="transf">
                                        <tr class="produits">
                                            <td class="hidden"> <c:out value="${transf.getIdTransfert()}"/></td>
                                            <td> <c:out value="${transf.getTypeProduit()}"/></td>
                                            <td class="code"> <c:out value="${transf.getCodeProduit()}"/></td>
                                            <td> <c:out value="${transf.getDesignation()}"/></td>
                                            <td> <f:formatNumber value="${transf.getQuantite()}" type="NUMBER"/></td>
                                            <td> <c:out value="${transf.getPrixUnitaire()}"/></td>
                                            <td> <f:formatNumber value="${transf.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA" maxFractionDigits="0"/></td>
                                            <td> <f:formatDate value="${transf.getDate()}" type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM"/></td>                      
                                            <td> <c:out value="${transf.getEtat()}"/></td>
                                            <td> <c:out value="${transf.getCommentaireMS()}"/></td>

                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table> <br>
                            <c:if test="${not empty message4 }">
                                <div class="alert alert-danger  col-lg-12 text-center ">
                                    <strong>${message4.getMessage()}</strong>
                                </div>
                            </c:if>

                        </div>
                    </div>
                </div>


            </div>
        </div>

    </div>

</div>

</div>


