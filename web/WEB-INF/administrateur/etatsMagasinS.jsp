<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                        <c:if test="${not empty sessionScope.lien18  or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${sorti == 'OK'}">active</c:if>"><a data-toggle="tab" href="#stock">Stock Magasin: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                            <c:if test="${ not empty sessionScope.lien19 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${sorti == 'ONE'}">active</c:if>"><a data-toggle="tab" href="#sorti">Etat De Consommation: Personnels</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien20 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${sorti == 'TWO'}">active</c:if>"><a data-toggle="tab" href="#conso">Etat De Consommation Général: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien21 or not empty sessionScope.lien19 or not empty sessionScope.lien20 or not empty sessionScope.lien21 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class=""><a data-toggle="tab" href="#commande">Voir Les Commandes: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                    </ul>
                    <div class="tab-content" id="myTabContent">
                        <div id="stock" class="tab-pane fade  <c:if test="${sorti == 'OK'}">in active</c:if>">
                                <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                                    <thead class="text-primary">
                                        <tr>
                                        <tr>
                                            <th>CATEGORIE</th>
                                            <th>CODE</th>
                                            <th>DESIGNATION</th>
                                            <th>QUANTITE</th>                                      
                                            <th>PRIX UNITAIRE</th>
                                            <th>PRIX TOTAL</th>
                                            <th>DATE LIVRAISON</th>
                                        </tr>
                                        </tr>
                                    </thead>
                                    <tbody id="tbody">
                                    <c:set var="t" value="${0}"/>
                                    <c:forEach items="${stocksMS}"var="r">
                                        <tr class="produits">
                                            <td > <c:out value="${r.getCategorie().getNomCategorie()}"/> </td>
                                            <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                                            <td><c:out value="${r.getDesignation()}"/></td>
                                            <td class="qteu"><f:formatNumber value="${r.getQuantite()}"type="NUMBER"/></td>                                                
                                            <td><f:formatNumber value="${r.getPrixUnitaire()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                            <td><f:formatNumber value="${r.getPrixUnitaire() * r.getQuantite()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                            <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                                            <c:set var="tt" value="${r.getPrixUnitaire() * r.getQuantite()}"/>
                                            <c:set var="t" value="${t + tt}"/>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th colspan="7" style="text-align: center">Total: <f:formatNumber value="${t}"type="CURRENCY" currencySymbol="FCFA"/></th>

                                    </tr>
                                </tfoot>
                            </table> 
                        </div>

                        <div id="sorti" class="tab-pane fade  <c:if test="${sorti == 'ONE'}">in active</c:if>">
                            <c:import url="BonsortiMS.jsp"/>
                        </div>

                        <div id="conso" class="tab-pane fade <c:if test="${sorti == 'TWO'}">in active</c:if>">
                            <c:import url="EtatGeneralMS.jsp"/>
                        </div>

                        <div id="commande" class="tab-pane fade">
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <table class="table table-hover table-responsive table-bordered tableUnique"cellspacing="0" width="100%">
                                <thead class="text-primary">
                                    <tr>
                                        <th>CATEGORIE</th>
                                        <th>CODE</th>
                                        <th>DESIGNATION</th>
                                        <th>DERNIERE LIVRAISON</th>
                                        <th>QUANTITE EN STOCK</th>
                                        <th>QUANTITE COMMANDER</th>               
                                        <th>Commander le</th>                
                                        <th>ETAT</th>

                                    </tr>
                                </thead> 
                                <tbody>
                                    <c:set var="t" value="${0}"/>
                                    <c:forEach items="${magasin.getCommandeMSList()}" var="lis">  
                                        <tr class="produits">

                                            <td class="categories"><c:out value="${lis.getTypeProduit()}"/></td>
                                            <td class="code"><c:out value="${lis.getCodeProduit()}"/></td>
                                            <td class="designation"><c:out value="${lis.getDesignation()}"/></td>
                                            <td class="pt"><f:formatDate value="${lis.getDerniereLivraison()}"  type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM" /></td>
                                            <td class="qte"><f:formatNumber value="${lis.getQuantiteEnStock()}" type="NUMBER"/></td>
                                            <td class="q"><f:formatNumber value="${lis.getQuantiteCommande()}" type="NUMBER"/></td>                                       
                                            <td><f:formatDate value="${lis.getDate()}" type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM"/></td>                                              
                                            <td class="categories"><c:out value="${lis.getEtatCommande()}"/></td>
                                            <c:set var="tt" value="${lis.getPrixUnitaire() * lis.getQuantite()}"/>
                                            <c:set var="t" value="${t + tt}"/>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th colspan="8" style="text-align: center">Total: <f:formatNumber value="${t}"type="CURRENCY" currencySymbol="FCFA"/></th>

                                    </tr>
                                </tfoot>
                                </tbody>
                            </table> 
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <c:if test="${empty magasin.getCommandeMSList()}">
                                <div class="alert alert-warning  span12 text-center ">
                                    <strong><c:out value="${magasin.getNomMagasin()} n'a passer Aucune commande pour le Moment..."/></strong>
                                </div>
                            </c:if>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- END INLINE TABS PORTLET-->
    </div>
</div>