
<%-- 
    Document   : Listeproduit
    Created on : 19 nov. 2016, 15:53:48
    Author     : messi
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set value="${magasin.getIdMagasin()}" var="id_magasinMSSat" scope="session"></c:set>
    <h3 class="page-title">
        Espace Magasin Secondaire
    </h3>
    <ul class="breadcrumb">
        <li>

            <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
            <span class="divider">/</span>
        </li>
        <li class="active">
            <a href="admin?vue=magS&action=getAllMagS"><i class="icon-list"></i> Magasins</a>
            <span class="divider">/</span>
        </li>
        <li class="active">
            <a href="#"><i class="icon-edit-sign"></i> Edition Magasin Secondaire</a>

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
<c:if test="${!empty stock}">
    <c:set var="size" value="${stock.size()}" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.critique}">

    <c:set var="critique" value="${stock.size()}" scope="session"/>
    <a  href="commanderecus?vue=editeMagasinS&action=allclient&id_magasin=${magasin.getIdMagasin()}&niveau=5" class="btn btn-danger popovers"   data-trigger="hover" data-placement="top" data-content="passer une commande pour le magasin" data-original-title="stock critique" id="pulsate-regular">alert <c:out value="${stock.size()}" default="${sessionScope.size}"/> produit(s) critique(s) en stock </a>
    <div class="space20"></div>
</c:if>
<c:if test="${!empty stockw}">
    <c:set var="size" value="${stockw.size()}" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.warmin}">

    <c:set var="warming" value="${stockw.size()}" scope="session"/>
    <a  href="commanderecus?vue=editeMagasinS&action=allclient&id_magasin=${magasin.getIdMagasin()}&niveau=5" class="btn btn-danger popovers"   data-trigger="hover" data-placement="top" data-content="passer une commande pour le magasin" data-original-title="stock warming" id="pulsate-regular">alert <c:out value="${stockw.size()}" default="${sessionScope.size}"/> produit(s) Warming en stock </a>
    <div class="space20"></div>
</c:if>

<div class="row-fluid">
    <!--BEGIN METRO STATES-->
    <div class="metro-nav">
        <ul class="nav nav-tabs" id="myTab">

            <div class="metro-nav-block   nav-block-green">
                <a  href="admin?action=getOnMagS&vue=editeMagasinS&idMagasin=${magasin.getIdMagasin()}" data-original-title="">
                    <div class="text-center ">
                        <i class="icon-info"></i>
                    </div>
                    <div class="status" style="margin-bottom: 10px">Infos: ${magasin.getNomMagasin()}</div>
                </a>
            </div>

            <c:if test="${ empty isnew}">
                <c:if test="${not empty sessionScope.lien18 or not empty sessionScope.lien19 or not empty sessionScope.lien20 or not empty sessionScope.lien21 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-grey">
                        <a href="admin?action=getEtatMagS&vue=editeMagasinS&idMagasin=${magasin.getIdMagasin()}" data-original-title="">
                            <div class="text-center">
                                <i class="icon-eye-open"></i>
                            </div>
                            <div class="status" style="margin-bottom: 10px">Etats: ${magasin.getNomMagasin()}</div>
                        </a>
                    </div>
                </c:if>
                <c:if test="${not empty sessionScope.lien22 or not empty sessionScope.lien23 or not empty sessionScope.lien24  or sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-purple">
                        <a  href="listeproduit?action=getInventaireMagSAdmin&vue=editeMagasinS&id_magasin=${magasin.getIdMagasin()}&niveau=5" data-original-title="">
                            <div class="text-center">
                                <i class="icon-archive"></i>
                            </div>
                            <div class="status"style="margin-bottom: 10px">Inventaire: ${magasin.getNomMagasin()}</div>
                        </a>
                    </div>
                </c:if>
                <c:if test="${not empty sessionScope.lien25 or not empty sessionScope.lien26 or not empty sessionScope.lien27  or sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-blue">
                        <a  href="commanderecus?vue=editeMagasinS&action=allclient&id_magasin=${magasin.getIdMagasin()}&niveau=5" data-original-title="">
                            <div class="text-center">
                                <i class="icon-tasks"></i>
                            </div>
                            <div class="status"style="margin-bottom: 10px">operations De Traitements</div>
                        </a>
                    </div>
                </c:if>
                <c:if test="${not empty sessionScope.lien28 or not empty sessionScope.lien29 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-red">
                        <a  href="RedirectionVue?&vue=editeMagasinS&id_magasin=${magasin.getIdMagasin()}&niveau=5" data-original-title="">
                            <div class="text-center">
                                <i class="icon-signal"></i>
                            </div>
                            <div class="status"style="margin-bottom: 10px">Statistiques</div>
                        </a>
                    </div>
                </c:if>
                <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-red">
                        <a  href="RedirectionVue?&vue=editeMagasinS&id_magasin=${magasin.getIdMagasin()}&niveau=5&budget=oui" data-original-title="">
                            <div class="text-center">
                                <i class="icon-money"></i>
                            </div>
                            <div class="status"style="margin-bottom: 10px">Statistiques Budgetaires</div>
                        </a>
                    </div>
                </c:if>

                <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                    <div class="metro-nav-block  nav-block-red">
                        <a  href="RedirectionVue?&vue=editeMagasinS&id_magasin=${magasin.getIdMagasin()}&niveau=5&rapport=oui" data-original-title="">
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
                                    <li>MAGASIGNIER		:  <strong><a href="admin?action=editpersonnel&vue=editePersonnel&id=${personnel.getIdPersonnel()}"> <c:out value="${personnel.getNomPrenom()}"/></a></strong></li>
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
    <c:import url="etatsMagasinS.jsp"/>
</c:if>
<c:if test="${activeInventaire == 'OK'}">
    <c:import url="InventaireMagasinS.jsp"/>
</c:if>
<c:if test="${StatistiqueMS == 'OK' and empty StatistiqueBMS and empty rapport }">
    <c:import url="statistiqueMS.jsp"/>                     
</c:if>
<c:if test="${StatistiqueBMS == 'OK' }">
    <c:import url="statistiqueBMS.jsp"/>                     
</c:if>
<c:if test="${CommandeClient == 'OK'}">
    <c:import url="TraitementMS.jsp"/>
</c:if>
<c:if test="${rapport=='ok'}">
    <c:import url="rapportMS.jsp"/>
</c:if>
<!-- END PAGE CONTENT-->
