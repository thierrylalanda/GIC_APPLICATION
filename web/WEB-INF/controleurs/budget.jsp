
<%-- 
    Document   : Listeproduit
    Created on : 19 nov. 2016, 15:53:48
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-lg-12 view ">

    <div class="bs-docs-example">
        <ul class="nav nav-tabs">
            <li class="<c:if test="${statmagasin == 'oui'}">in active</c:if>"><a href="ValiderCommande?action=budgetRegional&vue=Budget Regional">Par Magasin</a></li>
            <li class="<c:if test="${statmagasin == 'non'}">in active</c:if>"><a href="ValiderCommande?action=budgetRegionalCategorie&vue=Budget Regional">Par Categorie De Produit</a></li>


            </ul>
            <div class="space20"></div>
            <div class="tab-content" id="myTabContent">

                <div id="mouvement" class="tab-pane fade  <c:if test="${statmagasin == 'oui'}">in active</c:if>">
                    <div class="row-fluid">
                        <table class="table table-bordered table-hover table-striped simple_print"cellspacing="0" width="100%">
                            <thead>
                                <tr >
                                    <th>magasin</th>
                                    <th>budget pour</th>
                                    <th>montant alloue</th>
                                    <th>montant restant</th>
                                    <th>date Attribution</th>
                                    <th>date Expiration</th>

                                </tr>
                            </thead>
                            <tbody>
                            <c:set var="t" value="${0}"/>
                            <c:forEach  items="${budgetRegional}" var="bud">
                                <tr class="">
                                    <td >${bud.magasin.getNomMagasin()}</td>
                                    <td >${bud.getTypeBudget()}</td>
                                    <td class="pt"><f:formatNumber value="${bud.getMontant()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                    <td class="pt"><f:formatNumber value="${bud.getMontantRestant()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                    <td><f:formatDate value="${bud.getDateDatribution()}" type="Date" dateStyle="MEDIUM"/></td>
                                    <td><f:formatDate value="${bud.getDateExpiration()}" type="Date" dateStyle="MEDIUM"/></td>
                                </tr>
                                <c:set value="${bud.getMontantRestant() + t}" var="t"/>
                            </c:forEach>

                        </tbody>
                        <tfoot>
                            <tr  class="text-capitalize text-center text-success text-primary text-info">
                                <td colspan="6">
                                    Montant Total Restant Du Budget Est De : <f:formatNumber value="${t}" type="CURRENCY" currencySymbol="FCFA"/>
                                </td>
                            </tr>
                        </tfoot>  
                    </table>
                </div>
            </div>

            <div id="" class="tab-pane fade  <c:if test="${statmagasin == 'non'}">in active</c:if>">
                <c:set value="${idMS}" var="id_magasinMSS" scope="session"></c:set>
                    <div class="row-fluid">
                        <div class="span12">
                            <!-- BEGIN CHART PORTLET-->
                            <div class="widget black">
                                <div class="widget-title">

                                    <h4><i class="icon-reorder"> </i> Statistique Budgetaire De la region  du: ${sessionScope.personnel.getService().getSite().getRegion().getNomRegion()}</h4>
                                <span class="tools">
                                    <a href="javascript:;" class="icon-chevron-down"></a>

                                </span>
                            </div>
                            <div class="widget-body">
                                <div class="space20"></div>
                                <div class="space20"></div>
                                <c:set  value="${['green','purple','terques','red']}" var="t"/>
                                <c:set  value="${0}" var="i"/>
                                <h2 class="text-center">Etat Du Budget Pour L'annee 2017</h2>
                                <div class="row-fluid">

                                    <c:forEach items="${budgets}" var="bud">
                                        <div class="span3">
                                            <div class="pricing-table ${t[i]}">
                                                <div class="pricing-head ">
                                                    <h3> ${bud.getTypeBudget()} </h3>
                                                    <c:set  value="${(i+1)%4}" var="i"/>
                                                </div>
                                                <ul>
                                                    <li><strong>Budget </strong> <f:formatNumber value="${bud.getMontant()}"type="CURRENCY" currencySymbol="FCFA"/></li>
                                                    <li><strong>Utiliser </strong> <f:formatNumber value="${bud.getMontant()-bud.getMontantRestant()}"type="CURRENCY" currencySymbol="FCFA"/> 
                                                        <br><span class="badge "> ${(bud.getMontant()-bud.getMontantRestant())/bud.getMontant() *100} %</span></li>
                                                        <c:if test="${bud.getMontantRestant()< 0}">
                                                        <li><strong>Reste </strong> <f:formatNumber value="0"type="CURRENCY" currencySymbol="FCFA"/> </li>

                                                    </c:if>
                                                    <c:if test="${bud.getMontantRestant()> 0}">
                                                        <li><strong>Reste </strong> <f:formatNumber value="${bud.getMontantRestant()}"type="CURRENCY" currencySymbol="FCFA"/> 
                                                            <br><span class="badge "> ${(bud.getMontantRestant())/bud.getMontant() *100} % </span></li>
                                                        </c:if>    
                                                        <c:if test="${bud.getMontantRestant()< 0}">
                                                        <li class="" style="background-color: red;"><strong>deficit </strong> <f:formatNumber value="${bud.getMontantRestant()}"type="CURRENCY" currencySymbol="FCFA"/>  </li>
                                                        </c:if>

                                                </ul>

                                            </div>
                                        </div>

                                    </c:forEach>




                                </div>
                                <hr>
                            </div>
                        </div>
                        <!-- END CHART PORTLET-->
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>
