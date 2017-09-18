
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row-fluid">
    <div class="span12">
        <!-- BEGIN INLINE TABS PORTLET-->
        <div class="widget orange">
            <div class="widget-title" style="background-color: #0070a3">
                <h4><i class="icon-reorder"></i> Etats Consommations Du Personnels</h4>
                <span class="tools">


                </span>
            </div>
            <div class="widget-body">

                <form class="form-horizontal search-result" method="post" action="search?vue=editeMagasinS&id_magasin=${magasin.getIdMagasin()}&admin=yes">
                    <div class="control-group">
                        <label class="control-label">Search</label>
                        <div class="controls">
                            <input type="text" class="input-xxlarge tags" required name="nom" placeholder="Entrer le Nom Du Personnel Ici">
                            <p class="help-block">la recherche est Autocomplete en moin de (0.10 seconds) </p>
                        </div>
                        <button type="submit" class="btn ">SEARCH</button>
                    </div>
                </form>
                <c:if test="${not empty message}">
                    <div class="alert alert-block alert-danger" style="">

                        <h4>Erreur !</h4>
                        <c:out value="Erreur: ${message.getMessage()}"/> !

                    </div>
                </c:if>
                <c:if test="${not empty cps}">
                    <div class="widget-body">
                        <div class="bs-docs-example">
                            <ul class="nav nav-tabs" id="myTab">
                                <li class="active"><a data-toggle="tab" href="#perso">Information Sur: ${client.getNomPrenom()}</a></li>
                                <li><a data-toggle="tab" href="#opera">Etats De Consommations De: ${client.getNomPrenom()}</a></li>


                            </ul>
                            <div class="tab-content" id="myTabContent">
                                <div id="perso" class="tab-pane fade in active">
                                    <div class="classic-search">
                                        <c:if test="${infoPerso == 'OK'}">
                                            <div class="row-fluid">
                                                <div class="span8 bio tab-content">


                                                    <div class="space15"></div>

                                                    <h2>${client.getNomPrenom()}</h2>

                                                    <p><label>Matricule </label>:  ${client.getMatricule()}</p>
                                                    <p><label>Grade </label>: ${client.getFonction()}</p>
                                                    <p><label>Telephone </label>: ${client.getTelephone()}</p>
                                                    <p><label>Email</label>: ${client.getEmail()}</p>
                                                    <p><label>Region </label>: ${client.getService().getSite().getRegion().getNomRegion()}</p>
                                                    <p><label>Site </label>: ${client.getService().getSite().getNomSite()}</p>
                                                    <p><label>Service </label>: ${client.getService().getNomService()}</p>
                                                    <hr>


                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <div id="opera" class="tab-pane fade">

                                        <div class="classic-search">
                                            <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
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
                                                <tfoot>
                                                    <tr  class="text-capitalize text-center text-success text-primary text-info">
                                                        <td colspan="8">
                                                            La Somme Total est de:<f:formatNumber value="${total}" type="CURRENCY" currencySymbol="FCFA"/>
                                                        </td>

                                                    </tr>
                                                </tfoot>
                                                <tbody id="tbody">
                                                    <c:forEach items="${cps}" var="com">
                                                        <tr class="produits">
                                                            <td class="categories"><c:out value="${com.getCategorie()}"/></td>
                                                            <td class="code"><c:out value="${com.getCodeProduit()}"/></td>
                                                            <td class="pt"><c:out value="${com.getDesignation()}"/></td>
                                                            <td class="qte"><f:formatNumber value="${com.getQuantite()}" type="NUMBER"/></td>
                                                            <td class="pu"><f:formatNumber value="${com.getPrix()}" type="NUMBER"/></td>
                                                            <td class="pt"><f:formatNumber value="${com.getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA" maxFractionDigits="0"/></td>
                                                            <td class="pt"><c:out value="${com.getAppariel()}"/></td>
                                                            <td class="date"><f:formatDate value="${com.getDate()}" type="Date" dateStyle="MEDIUM"/></td>
                                                        </tr>
                                                    </c:forEach>

                                                </tbody>

                                            </table>
                                        </div>
                                    </div>           
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
        <!-- END INLINE TABS PORTLET-->
    </div>
</div>