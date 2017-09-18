<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="id_magasinMPP" value="${magasin.getIdMagasin()}" scope="session"/>
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
                        <c:if test="${not empty sessionScope.lien39  or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${trait == 'OK'}">active</c:if>"><a data-toggle="tab" href="#commandePerso">Commandes Recus Par: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien40 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${borderauH == 'OK'}">active</c:if>"><a data-toggle="tab" href="#transfertRecus">Historiques Bordereau: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien41 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${commanderPour == 'OK'}">active</c:if>"><a data-toggle="tab" href="#commanderPour">Passer Une Commande Pour: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                    </ul>

                    <div class="space20"></div>
                    <div class="tab-content" id="myTabContent">
                        <div id="commandePerso" class="tab-pane fade  <c:if test="${trait == 'OK'}">in active</c:if>">
                                <div class="row-fluid">
                                    <div class="span3">
                                        <h4 class="title grey">Sections  Magasin Secondaire</h4>
                                        <div class="clearfix">
                                            <ul class="nav nav-list faq-list">
                                                <li>
                                                    <a class="active" href="#"><i class=" icon-signin"></i> Magasins Secondaires </a>
                                                </li>
                                            <c:if test="${empty commandeur}" >

                                                <li class="alert alert-block alert-danger" style="margin-left: -20px;margin-right: -15px">
                                                    <h4>Oups !</h4>
                                                    <c:out value="Aucune Magasin n'a Passer De Commande Pour L'instant..."/> <i class="icon-dropbox"></i>
                                                </li>

                                            </c:if>

                                            <c:forEach items="${commandeur}" var="client">
                                                <li><a href="CommandeRecusMP?idMS=${client.getIdMagasin()}&action=lister&vue=editeMagP&id_magasinP=${magasin.getIdMagasin()}&niveau=5"><i class="icon-home"></i> ${client.getNomMagasin()}</a></li>
                                                </c:forEach>


                                        </ul>
                                    </div>
                                </div>
                                <c:if test="${action=='detaill'}">
                                    <div class="span9">
                                        <h4>Commandes De: ${Onecommande.get(0).getIdMS().getNomMagasin()}</h4>
                                        <div id="accordion" class="accordion in collapse" style="height: auto;">

                                            <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                                                <thead class="text-primary">
                                                    <tr>
                                                        <th>CATEGORIE</th>
                                                        <th>CODE</th>
                                                        <th>DESIGNATION</th>
                                                        <th>QUANTITE Init</th>
                                                        <th>DERNIERE LIVRAISON</th>
                                                        <th>QUANTITE </th>                                                       
                                                        <th>Commander le</th>
                                                        <th>Etat</th>

                                                    </tr>
                                                </thead> 
                                                <tbody>
                                                    <c:forEach items="${Onecommande}" var="lis">  
                                                        <tr>
                                                            <c:forTokens var="token" items="${lis.getTypeProduit()}" delims="I" begin="${0}" end="${0}">
                                                                <td class="categories"><c:out value="${token}"/></td>
                                                            </c:forTokens>
                                                            <td class="code"><c:out value="${lis.getCodeProduit()}"/></td>
                                                            <td class="designation"><c:out value="${lis.getDesignation()}"/></td>
                                                            <td class="qte"><f:formatNumber value="${lis.getQuantiteEnStock()}" type="NUMBER"/></td>
                                                            <td class="pt"><f:formatDate value="${lis.getDerniereLivraison()}"  type="DATE" dateStyle="MEDIUM" /></td>
                                                            <td class="qteu"><f:formatNumber value="${lis.getQuantiteCommande()}" type="NUMBER"/></td>                                                           
                                                            <td><f:formatDate value="${lis.getDate()}" type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM"/></td>                           
                                                            <td class="categories"><c:out value="${lis.getEtatCommande()}"/></td>

                                                        </tr>

                                                    </c:forEach>

                                                </tbody>
                                            </table>
                                        </div>

                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <div id="transfertRecus" class="tab-pane fade <c:if test="${borderauH == 'OK'}">in active</c:if>">
                                <table class="table table-hover table-responsive  table-bordered simple_print"cellspacing="0" width="100%">
                                    <thead class="text-primary">
                                        <tr>
                                            <th>FOURNISSEUR</th>
                                            <th>CATEGORIE</th>
                                            <th>CODE</th>
                                            <th>DESIGNATION</th>
                                            <th>QUANTITE</th>
                                            <th>Prix Unitaire</th>
                                            <th>Prix Toatal</th>
                                            <th>DATE LIVRAISON</th>

                                        </tr>
                                    </thead> 
                                    <tbody>
                                    <c:forEach items="${listes}" var="lis">  
                                        <tr class="produits">
                                            <td class="founisseur"><c:out value="${lis.getIdFounisseur().getNomFounisseur()}"/></td>
                                            <td class="categories"><c:out value="${lis.getTypeProduit()}"/></td>
                                            <td class="code"><c:out value="${lis.getCodeProduit()}"/></td>
                                            <td class="designation"><c:out value="${lis.getDesignation()}"/></td>
                                            <td class="qteu"><f:formatNumber value="${lis.getQuantite()}" type="NUMBER"/></td>    
                                            <td class="qteu"><f:formatNumber value="${lis.getPrixUnitaire()}" type="NUMBER"/></td>
                                            <td class="qteu"><f:formatNumber value="${lis.getPrixTotal()}" type="NUMBER"/></td>
                                            <td><f:formatDate value="${lis.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           

                                        </tr>

                                    </c:forEach>

                                </tbody>
                            </table> 
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <c:if test="${not empty messageB }">
                                <div class="alert alert-danger  span12 text-center ">
                                    <strong>${messageB.getMessage()}</strong>
                                </div>
                            </c:if>
                            <div class="space20"></div>
                        </div>

                        <div id="commanderPour" class="tab-pane fade <c:if test="${commanderPour == 'OK'}">in active</c:if>">
                            <c:import url="commandeMagasinP.jsp"/>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END INLINE TABS PORTLET-->
    </div>
</div>