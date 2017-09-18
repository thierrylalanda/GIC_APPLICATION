
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
                </span>
            </div>
            <div class="widget-body">
                <div class="bs-docs-example">
                    <ul class="nav nav-tabs" id="myTab">
                        <c:if test="${not empty sessionScope.lien33 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${sorti == 'OK'}">active</c:if>"><a data-toggle="tab" href="#stock">Stocks: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien34 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${sorti == 'ONE'}">active</c:if>"><a data-toggle="tab" href="#sorti">Etats De Consommations General: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                    </ul>
                    <div class="tab-content" id="myTabContent">
                        <div id="stock" class="tab-pane fade  <c:if test="${sorti == 'OK'}">in active</c:if>">
                                <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                                    <thead class="text-primary">

                                        <tr>
                                            <th>CATEGORIE</th>
                                            <th>CODE</th>
                                            <th>DESIGNATION</th>
                                            <th>QUANTITE</th>                                      
                                            <th>PRIX UNITAIRE</th>
                                            <th>PRIX TOTAL</th>
                                            <th>DATE LIVRAISON</th>
                                        </tr>

                                    </thead>
                                    <tbody id="tbody">
                                    <c:set var="t" value="${0}"/>
                                    <c:forEach items="${stockMP}"var="r">
                                        <tr class="produits">
                                            <td > <c:out value="${r.getCategorie().getNomCategorie()}"/> </td>
                                            <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                                            <td><c:out value="${r.getDesignation()}"/></td>
                                            <td class="qteu"><f:formatNumber value="${r.getQuantite()}"type="NUMBER"/></td>                                                
                                            <td><f:formatNumber value="${r.getPrixUnitaire()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                            <td><f:formatNumber value="${r.getPrixUnitaire() * r.getQuantite()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                            <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                                            <c:set var="tt" value="${r.getPrixUnitaire() * r.getQuantite()}"/>
                                            <c:set var="t" value="${t + tt}"/>  </tr>

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
                            <c:import url="BonSortiMP.jsp"/>
                        </div>

                    </div>
                </div>
                <div class="space20"></div>
                <div class="space20"></div>
            </div>
        </div>
        <!-- END INLINE TABS PORTLET-->
    </div>
</div>