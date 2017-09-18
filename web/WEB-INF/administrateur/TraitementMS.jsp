<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="id_magasinMSS" value="${magasin.getIdMagasin()}" scope="session"/>
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
                        <c:if test="${ not empty sessionScope.lien25 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${trait == 'OK'}">active</c:if>"><a data-toggle="tab" href="#commandePerso">Commandes Du Personnels Recus Par: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                            <c:if test="${ not empty sessionScope.lien26 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li ><a data-toggle="tab" href="#transfertRecus">Transtfert Recus Par: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                            <c:if test="${ not empty sessionScope.lien27 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${commanderPour == 'OK'}">active</c:if>"><a data-toggle="tab" href="#commanderPour">Passer Une Commande Pour: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                    </ul>

                    <div class="space20"></div>
                    <div class="tab-content" id="myTabContent">
                        <div id="commandePerso" class="tab-pane fade  <c:if test="${trait == 'OK'}">in active</c:if>">
                                <div class="row-fluid">
                                    <div class="span3">
                                        <h4 class="title grey">Sections Personnels</h4>
                                        <div class="clearfix">
                                            <ul class="nav nav-list faq-list">
                                                <li>
                                                    <a class="active" href="#"><i class=" icon-signin"></i> Personnels </a>
                                                </li>
                                            <c:if test="${empty listepersonnel}" >

                                                <li class="alert alert-block alert-danger" style="margin-left: -20px;margin-right: -15px">
                                                    <h4>Oups !</h4>
                                                    <c:out value="Aucune Personnel n'a Passer De Commande"/> <i class="icon-dropbox"></i>
                                                </li>

                                            </c:if>
                                            <c:forEach items="${listepersonnel}" var="client">
                                                <li><a href="commanderecus?idclient=${client.getIdPersonnel()}&action=lister&vue=editeMagasinS&id_magasin=${magasin.getIdMagasin()}&niveau=5"><i class="icon-user"></i> ${client.getNomPrenom()}</a></li>
                                                </c:forEach>


                                        </ul>
                                    </div>
                                </div>
                                <c:if test="${action=='detaill'}">
                                    <div class="span9">
                                        <h4>Commandes De: ${clients.getNomPrenom()}</h4>
                                        <div id="accordion" class="accordion in collapse" style="height: auto;">

                                            <table class="table table-hover table-bordered table-striped responsive " >
                                                <thead class="text-primary">
                                                    <tr>
                                                        <th>DESIGNATION</th>
                                                        <th>QUANTITE</th>                         
                                                        <th>APPARIEL</th>
                                                        <th>DATE</th>

                                                    </tr>
                                                </thead>
                                                <tbody id="tbody">
                                                    <c:forEach items="${commande}" var="com">
                                                        <tr class="produits">                               
                                                            <td class="pt"><c:out value="${com.getDesignations()}"/></td>
                                                            <td class="qte"><f:formatNumber value="${com.getQuantite()}" type="NUMBER"/></td>                              
                                                            <td class="pt"><c:out value="${com.getCodeAppareil().getNumeroParck()}"/></td>
                                                            <td class="date"><f:formatDate value="${com.getDate()}" type="DATE" dateStyle="FULL"/></td>
                                                        </tr>
                                                    </c:forEach>

                                                </tbody>

                                            </table>

                                        </div>

                                    </div>

                                    <div class="text-center">
                                        <p class="btn btn-primary " id="detaill"> details ...</p>
                                    </div>

                                    <div class="space20"></div>
                                    <div class="row-fluid  detail" > 
                                        <table class="table table-hover table-bordered table-striped simple_print " >
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
                                </c:if>
                            </div>

                        </div>


                        <div id="transfertRecus" class="tab-pane fade">
                            <table class="table tabl table-hover table-responsive table-bordered tableUnique">
                                <thead class="text-primary thead-inverse">
                                    <tr>
                                        <th>CATEGORIE</th>
                                        <th>CODE</th>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE</th>
                                        <th>PRIX UNITAIRE</th>
                                        <th>PRIX TOTAL</th>
                                        <th>DATE LIVRAISON</th>
                                        <th>Visa Fournisseur</th>
                                        <th>Observation</th>
                                        <th>Etat du transfert</th>

                                    </tr>
                                </thead>
                                <tbody id="tbody">
                                    <c:forEach items="${transfert}" var="transf">
                                        <tr class="produits">
                                            <td> <c:out value="${transf.getTypeProduit()}"/></td>
                                            <td class="code"> <c:out value="${transf.getCodeProduit()}"/></td>
                                            <td> <c:out value="${transf.getDesignation()}"/></td>
                                            <td> <f:formatNumber value="${transf.getQuantite()}" type="NUMBER"/></td>
                                            <td> <c:out value="${transf.getPrixUnitaire()}"/></td>
                                            <td> <f:formatNumber value="${transf.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA" maxFractionDigits="0"/></td>
                                            <td> <f:formatDate value="${transf.getDate()}" type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM"/></td>
                                            <td> <c:out value="${transf.getVisaMP()}"/></td>
                                            <td> <c:out value="${transf.getCommentaireMP()}"/></td>
                                            <td> <c:out value="${transf.getVisaMS()}"/></td>

                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <c:if test="${not empty message }">
                                <div class="alert alert-danger  span12 text-center ">
                                    <strong>${message.getMessage()}</strong>
                                </div>
                            </c:if>
                            <div class="space20"></div>
                        </div>

                        <div id="commanderPour" class="tab-pane fade <c:if test="${commanderPour == 'OK'}">in active</c:if>">
                            <c:import url="commandeMagasinS.jsp"/>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END INLINE TABS PORTLET-->
    </div>
</div>