
<%-- 
    Document   : Listeproduit
    Created on : 19 nov. 2016, 15:53:48
    Author     : messi
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set value="${magasin.getIdMagasin()}" var="id_magasinMPSat" scope="session"></c:set>
    <h3 class="page-title">
        Espace Magasin Secondaire
    </h3>
    <ul class="breadcrumb">
        <li>

            <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
            <span class="divider">/</span>
        </li>
        <li class="active">
            <a href="admin?vue=magP&action=getAllMagP"><i class="icon-list"></i> Magasins</a>
            <span class="divider">/</span>
        </li>
        <li class="active">
            <a href="#"><i class="icon-edit-sign"></i> Edition Magasin</a>

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

<c:if test="${!empty stockp}">
    <c:set var="size" value="${stockp.size()}" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.critiques}">

    <c:set var="critique" value="${stockp.size()}" scope="session"/>
    <a  href="CommandeRecusMP?vue=editeMagP&action=AllCommande&id_magasinP=${magasin.getIdMagasin()}&niveau=5" class="btn btn-danger popovers"   data-trigger="hover" data-placement="top" data-content="passer une commande pour le magasin" data-original-title="stock critique" id="pulsate-regular">alert <c:out value="${stockp.size()}" default="${sessionScope.size}"/> produit(s) critique(s) en stock </a>
    <div class="space20"></div>
</c:if>
<c:if test="${!empty stockwp}">
    <c:set var="size" value="${stockwp.size()}" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.warming}">

    <c:set var="warming" value="${stockwp.size()}" scope="session"/>
    <a  href="CommandeRecusMP?vue=editeMagP&action=AllCommande&id_magasinP=${magasin.getIdMagasin()}&niveau=5" class="btn btn-danger popovers"   data-trigger="hover" data-placement="top" data-content="passer une commande pour le magasin" data-original-title="stock warming" id="pulsate-regular">alert <c:out value="${stockwp.size()}" default="${sessionScope.size}"/> produit(s) Warming en stock </a>
    <div class="space20"></div>
</c:if>

<div class="row-fluid">
    <!--BEGIN METRO STATES-->
    <div class="metro-nav">
        <ul class="nav nav-tabs" id="myTab">
            <div class="metro-nav-block   nav-block-green">
                <a  href="ListeProduitMP?action=seeInfoMP&vue=editeMagP&niveau=5&id_magasinP=${magasin.getIdMagasin()}" data-original-title="">
                    <div class="text-center ">
                        <i class="icon-info"></i>
                    </div>
                    <div class="status" style="margin-bottom: 10px">Infos: ${magasin.getNomMagasin()}</div>
                </a>
            </div>
            <c:if test="${empty isnew}">
                <c:if test="${not empty sessionScope.lien33 or not empty sessionScope.lien34 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-grey">
                        <a href="ListeProduitMP?action=all&vue=editeMagP&niveau=5&id_magasinP=${magasin.getIdMagasin()}&info=non" data-original-title="">
                            <div class="text-center">
                                <i class="icon-eye-open"></i>
                            </div>
                            <div class="status" style="margin-bottom: 10px">Etats: ${magasin.getNomMagasin()}</div>
                        </a>
                    </div>
                </c:if>
                <c:if test="${not empty sessionScope.lien35 or not empty sessionScope.lien36 or not empty sessionScope.lien37 or not empty sessionScope.lien38 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-yellow">
                        <a  href="passerCommande?&vue=editeMagP&id_magasinP=${magasin.getIdMagasin()}&niveau=5&action=consulterCommandeMP" data-original-title="">
                            <div class="text-center">
                                <i class="icon-shopping-cart"></i>
                            </div>
                            <div class="status"style="margin-bottom: 10px">Commandes: ${magasin.getNomMagasin()}</div>
                        </a>
                    </div>
                </c:if>
                <c:if test="${not empty sessionScope.lien39 or not empty sessionScope.lien40 or not empty sessionScope.lien41 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-blue">
                        <a  href="CommandeRecusMP?vue=editeMagP&action=AllCommande&id_magasinP=${magasin.getIdMagasin()}&niveau=5" data-original-title="">
                            <div class="text-center">
                                <i class="icon-tasks"></i>
                            </div>
                            <div class="status"style="margin-bottom: 10px">operations De Traitements</div>
                        </a>
                    </div>
                </c:if>
                <c:if test="${not empty sessionScope.lien42 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-purple">

                        <a  href="MisaJourProduitMP?action=historique&vue=editeMagP&id_magasinP=${magasin.getIdMagasin()}&niveau=5" data-original-title="">
                            <div class="text-center">
                                <i class="icon-archive"></i>
                            </div>
                            <div class="status"style="margin-bottom: 10px">Historiques Inventaires: ${magasin.getNomMagasin()}</div>
                        </a>
                    </div>
                </c:if>
                <c:if test="${not empty sessionScope.lien43 or not empty sessionScope.lien44 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-purple">
                        <a  href="ListeProduitMP?action=all_produit&vue=editeMagP&niveau=5&id_magasinP=${magasin.getIdMagasin()}&mouv=yes" data-original-title="">
                            <div class="text-center">
                                <i class="icon-eye-open"></i>
                            </div>
                            <div class="status"style="margin-bottom: 10px">Mouvements Produits: ${magasin.getNomMagasin()}</div>
                        </a>
                    </div>
                </c:if>
                <c:if test="${not empty sessionScope.lien45 or not empty sessionScope.lien46 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-red">
                        <a  href="ListeProduitMP?&vue=editeMagP&id_magasinP=${magasin.getIdMagasin()}&niveau=5&action=statistiqueMP" data-original-title="">
                            <div class="text-center">
                                <i class="icon-signal"></i>
                            </div>
                            <div class="status"style="margin-bottom: 10px">Statistiques</div>
                        </a>
                    </div>
                </c:if>

                <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-red">
                        <a  href="ListeProduitMP?&vue=editeMagP&action=rapport&id_magasinP=${magasin.getIdMagasin()}&niveau=5" data-original-title="">
                            <div class="text-center">
                                <i class="icon-money"></i>
                            </div>
                            <div class="status"style="margin-bottom: 10px">Repporting</div>
                        </a>
                    </div>
                </c:if>

            </c:if>
        </ul>
    </div>

    <div class="space10"></div>
    <!--END METRO STATES-->
</div>
<c:if test="${info == 'OK'}">
    <div id="info" class="tab-pane fade in active">
        <div class="row-fluid">
            <div class="span12">
                <!-- BEGIN BLANK PAGE PORTLET-->

                <div class="widget purple">
                    <div class="widget-title">
                        <h4><i class="icon-edit"></i> Information sur: ${magasin.getNomMagasin()}</h4>
                        <span class="tools">
                            <a href="javascript:;" class="icon-chevron-down"></a>
                            <a href="javascript:;" class="icon-remove"></a>
                        </span>
                    </div>
                    <div class="widget-body">


                        <div class="row-fluid">
                            <div class="span12">
                                <div class="text-center">
                                    <img alt="" src="photo/${sessionScope.societe.getLogo()}">
                                </div>
                                <hr>

                            </div>
                        </div>
                        <div class="space20"></div>
                        <div class="row-fluid invoice-list">
                            <div class="span4">
                                <h4>LOCALISATION:</h4>

                                <ul class="unstyled">
                                    <li>REGION		: <strong><c:out value="${magasin.getSite().getRegion().getNomRegion()}"/></strong></li>
                                    <li>SITE		: <strong><c:out value="${magasin.getSite().getNomSite()}"/></strong></li>

                                </ul>

                            </div>
                            <div class="span4">
                                <h4>INFO MAGASIN</h4>

                                <ul class="unstyled">
                                    <li>NOM MAGASIN		: <strong><c:out value=" ${magasin.getNomMagasin()}"/></strong></li>
                                    <li>DESCRIPTION		: <strong><c:out value="${magasin.getDescription()}"/></strong></li>
                                    <li>MAGASIGNIER			:  <strong><a href="admin?action=editpersonnel&vue=editePersonnel&matricule=${magasin.getAffectationmagasinPList()[0].getPersonnel().getMatricule()}"> <c:out value="${magasin.getAffectationmagasinPList()[0].getPersonnel().getNomPrenom()}"/></a></strong></li>

                                </ul>

                            </div>

                        </div>

                    </div>

                </div>
            </div>
            <!-- END BLANK PAGE PORTLET-->
        </div>
    </div>
</c:if>

<c:if test="${active == 'OK'}">
    <c:import url="etatsMagasinP.jsp"/>
</c:if>

<c:if test="${AllcommandesMP == 'OK'}">
    <c:import url="AllCommandesMP.jsp"/>

</c:if>

<c:if test="${StatistiqueMP == 'OK'}">                      
    <c:import url="statistiqueMP.jsp"/>
</c:if>

<c:if test="${mouvementP == 'OK'}">                      
    <c:import url="MouvementProduitsMP.jsp"/>
</c:if>
<c:if test="${TraitementMP == 'OK'}">                      
    <c:import url="OperationTraitementMP.jsp"/>
</c:if>
<c:if test="${rapportmp=='ok'}">
    <c:import url="rapportMP.jsp"/>
</c:if>

<c:if test="${InventaireMP == 'OK'}">

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
                    <div class="space20"></div>

                    <table class="table table-hover table-responsive table-bordered simple_print " cellspacing="0" width="100%">
                        <thead class="text-primary">
                            <tr>
                                <th>Categorie</th>
                                <th>Code</th>
                                <th>Designation</th>
                                <th>Quantite Initiale</th>
                                <th>Nouvelle Quantite</th>
                                <th>Ecart Stock</th>
                                <th>Prix Unitaire</th>
                                <th>Deficits</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <tbody id="tbody">
                            <c:forEach items="${listEcartMP}" var="lis">  
                                <tr class="produits">

                                    <td class="categories"><c:out value="${lis. getCategorie()}"/></td>
                                    <td class="code"><c:out value="${lis.getCodeProduit()}"/></td>
                                    <td><c:out value="${lis.getDesignation()}"/></td>
                                    <td class="qte"><c:out value="${lis.getLastQuantite()}"/></td>
                                    <td class="pu"><f:formatNumber value="${lis.getNewQuantite()}" type="NUMBER"/></td>
                                    <td class="pu"><f:formatNumber value="${lis. getEcatQuantite()}" type="NUMBER"/></td>
                                    <td class="pu"><f:formatNumber value="${lis. getPrixUnitaire()}" type="CURRENCY"/></td>
                                    <td class="pu"><f:formatNumber value="${lis. getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                    <td><f:formatDate value="${lis.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           
                                </tr>

                            </c:forEach>

                        </tbody>
                    </table> 
                </div>
            </div>
        </div>
    </div>


</c:if>
<!-- END PAGE CONTENT-->
