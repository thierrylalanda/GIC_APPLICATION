
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="space20"></div>
<div class="space20"></div>
<div class="space20"></div>
<h3 class="page-title">
    Etablir L'etat De Consommation Pour Un Personnel Au Niveau Du: <strong class="nom_magasin">${ sessionScope.magasinMS.getNomMagasin()}</strong>
</h3>
<ul class="breadcrumb">
    <li>
        <a href="RedirectionVue?vue=Accueil"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">Consommation Personnel</a>

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
<!-- BEGIN INLINE TABS PORTLET-->

<div class="widget black">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i> Etat Consommations Du Personnel</h4>
        <span class="tools">


        </span>
    </div>
    <div class="widget-body">

        <form class="form-horizontal search-result" method="post" action="search?vue=Bon de Sortir">
            <div class="control-group">
                <label class="control-label">Search</label>
                <div class="controls">
                    <input type="text" class="input-xxlarge tags" name="nom" placeholder="Entrer le Nom Du Personnel Ici">
                    <p class="help-block">la recherche est Autocomplete en moin de (0.10 seconds) </p>
                </div>
                <button type="submit" class="btn ">SEARCH</button>
            </div>
        </form>
        <c:if test="${not empty message}">
            <div class="alert alert-block alert-danger" style="">


                <c:out value="Oups! ${message.getMessage()}"/> !

            </div>
        </c:if>
        <c:if test="${not empty cps}">
            <div class="widget-body">
                <div class="bs-docs-example">
                    <ul class="nav nav-tabs" id="myTab">
                        <li ><a data-toggle="tab" href="#perso">Information Sur: ${client.getNomPrenom()}</a></li>
                        <li class="active"><a data-toggle="tab" href="#opera">Etats De Consommations De: ${client.getNomPrenom()}</a></li>


                    </ul>
                    <div class="tab-content" id="myTabContent">
                        <div id="perso" class="tab-pane fade"cellspacing="0" width="100%">
                            <div class="classic-search">

                                <div class="row-fluid">
                                    <div class="span8 bio tab-content">


                                        <div class="space15"></div>

                                        <h2 class="name_user">${client.getNomPrenom()}</h2>

                                        <p><label>Matricule </label>:  ${client.getMatricule()}</p>
                                        <p><label>Grade </label>: ${client.getFonction()}</p>
                                        <p><label>Telephone </label>: ${client.getTelephone()}</p>
                                        <p><label>Email</label>: ${client.getEmail()}</p>
                                        <p><label class="">Region </label>: <label class="region_user"> ${client.getService().getSite().getRegion().getNomRegion()}</label></p>
                                        <p><label class="">Site </label>: <label class="site_user">${client.getService().getSite().getNomSite()}</label></p>
                                        <p><label class="">Service </label>: <label class="service_user">${client.getService().getNomService()}</label></p>
                                        <hr>


                                    </div>
                                </div>

                            </div>
                        </div>
                        <div id="opera" class="tab-pane fade in active">
                            <form action="bordereau_expedition_All_Users?action=commandePeriodique&vue=Bon de Sortir&personnel=${client.getIdPersonnel()}&niveau=2" class="" method="post"> 
                                <div class="row-fluid">
                                    <div class="span3">
                                        <label class="control-label" for="">Interval</label>
                                        <div class="controls ">
                                            <div class="input-prepend">
                                                <span class="add-on"><i class="icon-calendar"></i></span>
                                                <input id=""  name="interval" type="text" class="reservation m-ctrl-medium" />
                                            </div>
                                            <div class="input-append ">

                                            </div>
                                        </div>
                                    </div>    




                                    <div class="span1"></div>

                                    <div class="span1">
                                        <button type="submit" class="btn btn-primary " style="margin-top: 25px">
                                            <span class="icon icon-search "></span>
                                        </button>
                                    </div>


                                </div>
                            </form>

                            <table class="table table-hover table-responsive table-bordered bordereauPersonnel"cellspacing="0" width="100%">
                                <thead >
                                    <tr>
                                        <th>CATEGORIE</th>
                                        <th>CODE</th>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE</th>
                                        <th>PRIX UNITAIRE</th>
                                        <th>PRIX TOTAL</th>
                                        <th>APPARIEL</th>
                                        <th>DATE</th>

                                    </tr>
                                </thead>

                                <tbody id="tbody">
                                    <c:set var="t" value="${0}"/>
                                    <c:forEach items="${cps}" var="com">
                                        <tr>
                                            <td class="categories"><c:out value="${com.getCategorie()}"/></td>
                                            <td class="code"><c:out value="${com.getCodeProduit()}"/></td>
                                            <td class="pt"><c:out value="${com.getDesignation()}"/></td>
                                            <td class="qte"><f:formatNumber value="${com.getQuantite()}" type="NUMBER"/></td>
                                            <td class="pu"><f:formatNumber value="${com.getPrix()}" type="NUMBER"/></td>
                                            <td class="pt"><f:formatNumber value="${com.getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA" maxFractionDigits="0"/></td>
                                            <td class="pt"><c:out value="${com.getAppariel()}"/></td>
                                            <td class="date"><f:formatDate value="${com.getDate()}" type="Date" dateStyle="MEDIUM"/></td>
                                            <c:set var="t" value="${t + com.getPrixTotal()}"/>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                                <tfoot>
                                    <tr  class="text-capitalize text-center text-success text-primary text-info">
                                        <td colspan="8">
                                            La Somme Total est de:<label class="total_commande"><f:formatNumber value="${t}" type="CURRENCY" currencySymbol="FCFA"/></label>
                                        </td>

                                    </tr>
                                </tfoot>
                            </table>

                        </div>           
                    </div>
                </div>
            </div>

        </c:if>
    </div>
</div>

<!-- END INLINE TABS PORTLET-->
