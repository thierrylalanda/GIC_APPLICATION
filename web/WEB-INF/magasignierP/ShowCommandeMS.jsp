<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h3 class="page-title">
    Traiter les Commandes des Magasins Secondaires
</h3>
<ul class="breadcrumb">
    <li>
        <a href="StatistiqueMP?vue=Accueil&action=autre"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">${vue}</a>

    </li>

    <li class="pull-right search-wrap">
        <form action="" class="hidden-phone">
            <div class="input-append search-input-area">
                <input class="" id="appendedInputButton" type="text">
                <button class="btn" type="button"><i class="icon-search"></i> </button>
            </div>
        </form>
    </li>
</ul>

<div class="row-fluid">
    <div class="span12">
        <!-- BEGIN INLINE TABS PORTLET-->
        <div class="widget black">
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
                        <li class="active"><a data-toggle="tab" href="#commandePerso">Commandes Recus </a></li>
                        <li><a data-toggle="tab" href="#transf">Transferts Effectues</a></li>
                    </ul>

                    <div class="tab-content" id="myTabContent">
                        <div id="commandePerso" class="tab-pane fade in active">
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
                                                <li><a href="CommandeRecusMP?idMS=${client.getIdMagasin()}&action=lister&vue=Traitement Commande"><i class="icon-home"></i> ${client.getNomMagasin()}</a></li>
                                                </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <div class="space15"></div>
                                <div class="span12">
                                    <c:if test="${not empty modifiers}">

                                        <form method="post" action="CommandeRecusMP?vue=Traitement Commande&action=savetransf" class="form-inline">

                                            <input type="text" value="${cms.getDesignation()}"  class="  form-control  " disabled="true" id="designation" name="designation"/>             

                                            <input type="text"  class=" form-control " value="${cms.getCodeProduit()}" style="margin-right: 30px" disabled="true"  />

                                            <input type="number" id="quantite" class=" form-control" value="${cms.getQuantiteCommande()}"style="margin-right: 30px"  required name="quantite" />

                                            <textarea type="text"   class="  form-control  "  id="designation" placeholder="Donnee une Raison" name="raison" style="margin-right: 30px"></textarea>             

                                            <button type="submit" class="btn btn-success  btn-primary pull-righ " >Envoyer</button>

                                            <input type="text"  class=" form-control hidden" value="${cms.getIdCommande()}"  name="id" />

                                            <input type="text"  class=" form-control hidden " value="${idMS}"  name="idMS" />

                                            <input type="text" class=" form-control  hidden" value="${cms.getCodeProduit()}"   name="code" />

                                        </form>

                                    </c:if>

                                    <c:if test="${not empty modifier}">

                                        <form method="post" action="CommandeRecusMP?vue=Traitement Commande&action=refuser" class="form-inline">

                                            <input type="text" value="${cms.getDesignation()}"  class=" form-control tag  " disabled="true" id="designation" name="designation"/>             

                                            <input type="text"  class=" form-control  " value="${cms.getCodeProduit()}"  disabled="true"  />

                                            <label for="quantite "class="form-control-label " >Quantite</label>
                                            <input type="number"   class=" form-control  "disabled="true" required id="quantite" value="${cms.getQuantiteCommande()}"style="margin-right: 30px" name="quantite"/>             

                                            <textarea type="text"   class=" form-control" required id="designation"placeholder="Donnee une Raison" name="raison" style="margin-right: 30px"></textarea>             

                                            <button type="submit" class="btn btn-success  btn-primary " >Envoyer</button>

                                            <input type="text" class=" form-control hidden " value="${cms.getIdCommande()}"  name="id" />

                                        </form>

                                    </c:if>
                                </div>
                            </div>
                            <c:if test="${action=='detaill'}">

                                <div class="row-fluid">  
                                    <div class="span12">  
                                        <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>CATEGORIE</th>
                                                    <th>CODE</th>
                                                    <th>DESIGNATION</th>
                                                    <th>QUANTITE Init</th>
                                                    <th>DERNIERE LIVRAISON</th>
                                                    <th>QUANTITE </th>
                                                    <th class="hidden">QUANTITE</th>
                                                    <th>Commander le</th>
                                                    <th>Etat</th>
                                                    <th>valider</th>

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
                                                        <td class="qteuc hidden"><input class="newvaltransf focus" autofocus="true" type='number' id='qteu' /></td>                       
                                                        <td><f:formatDate value="${lis.getDate()}" type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM"/></td>                           
                                                        <td class="categories"><c:out value="${lis.getEtatCommande()}"/></td>
                                                        <td class="raison td">
                                                            <div class="btn-group">
                                                                <a title="" href="CommandeRecusMP?action=traiter&vue=Traitement Commande&id=${lis.getIdCommande()}&code=${lis.getCodeProduit()}&idMS=${lis.getIdMS().getIdMagasin()}" class="btn btn-success">  <span class="icon"> <i class="icon-save"></i> </span></a>  
                                                                <a title="" href="CommandeRecusMP?action=edit&vue=Traitement Commande&id=${lis.getIdCommande()}&code=${lis.getCodeProduit()}&idMS=${lis.getIdMS().getIdMagasin()}" class="btn btn-primary">  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                                                <a title="" href="CommandeRecusMP?action=refus&vue=Traitement Commande&id=${lis.getIdCommande()}" class="btn btn-danger"  >  <span class="icon"> <i class="icon-eject"></i> </span></a>  

                                                            </div>

                                                            <c:set value="${lis.getIdMS().getIdMagasin()}" var="idMS" scope="page"></c:set>
                                                            </td>
                                                        </tr>

                                                </c:forEach>

                                            </tbody>
                                        </table> <br><br>
                                        <div>
                                            <a href="CommandeRecusMP?action=traiterTous&vue=Traitement Commande" ><p  class="btn bouton btn-success btn-lg btn-large save " ><span class="icon-ok"> Valider Tous</span></p> </a>
                                        </div>
                                    </div>

                                </div><br><br>

                                <c:if test="${not empty message}">
                                    <div class="alert alert-success  text-center ">
                                        <strong>${message.getMessage()}</strong>
                                    </div><br><br>
                                </c:if>

                            </c:if> 
                        </div>

                        <div id="transf" class="tab-pane fade ">
                            <table class="table tabl table-hover table-responsive  table-bordered bordereauMS" idMP="${transfertStocks[0].getMp().getIdMagasin()}" idMS="${transfertStocks[0].getMs().getIdMagasin()}" cellspacing="0" width="100%">
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
                                        <th>Etat du transfert</th>
                                        <th>Commantaire</th>
                                    </tr>
                                </thead>
                                <tbody id="tbody">
                                    <c:set var="t" value="${0}"/>
                                    <c:forEach items="${transfertStocks}" var="transf">
                                        
                                        <tr class="produit">
                                            <td> <c:out value="${transf.getTypeProduit()}"/></td>
                                            <td class="code"> <c:out value="${transf.getCodeProduit()}"/></td>
                                            <td> <c:out value="${transf.getDesignation()}"/></td>
                                            <td> <f:formatNumber value="${transf.getQuantite()}" type="NUMBER"/></td>
                                            <td> <c:out value="${transf.getPrixUnitaire()}"/></td>
                                            <td> <f:formatNumber value="${transf.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA" maxFractionDigits="0"/></td>
                                            <td> <f:formatDate value="${transf.getDate()}" type="Date" dateStyle="MEDIUM"/></td>
                                            <td> <c:out value="${transf.getVisaMP()}"/></td>
                                            <td> <c:out value="${transf.getVisaMS()}"/></td>
                                            <td> <c:out value="${transf.getCommentaireMP()}"/></td>
                                            <c:set var="t" value="${t + transf.getPrixTotal()}"/>
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
        </div>
        <!-- END INLINE TABS PORTLET-->
    </div>
</div>
