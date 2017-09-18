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
                        <c:if test="${not empty sessionScope.lien22  or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${sorti == 'OK'}">active</c:if>"><a data-toggle="tab" href="#mouvement">Mouvements Sorti Produits: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien23  or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${sorti == 'ONE'}">active</c:if>"><a data-toggle="tab" href="#mouvementEntrer">Mouvements D'Entrer Produits: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien24  or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li><a data-toggle="tab" href="#historique">Historiques Inventaire: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                    </ul>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="tab-content" id="myTabContent">
                        <div id="mouvement" class="tab-pane fade  <c:if test="${sorti == 'OK'}">in active</c:if>">
                                <div class="row-fluid span12 color-red " >
                                    <form method="post" action="listeproduit?action=one_produit&vue=editeMagasinS&id_magasin=${magasin.getIdMagasin()}&niveau=5" class="form-inline">

                                    <!-- Les champs texte avec le code "onclick" déclenchant le script -->
                                    <label for="date1 "class="form-control-label " >DU</label>
                                    <input class="datepicker form-control"required name="date1" type="text"/>       
                                    <label for="date2 "class="form-control-label " >AU</label>
                                    <input class="datepicker form-control" name="date2"  required type="text"/>  
                                    <label for="designation "class="form-control-label " >Designation</label>
                                    <input type="text"   class=" tag " required id="designation" name="designation"/>  


                                    <button type="submit" class="btn btn-success  btn-primary pull-righ " id="sendcc" >Search</button>

                                </form>
                                <div class="form-control span11">
                                    <a  href="listeproduit?action=getInventaireMagSAdmin&vue=editeMagasinS&id_magasin=${magasin.getIdMagasin()}&niveau=5"><button class="btn btn-lg btn-large bg-info text-capitalize pull-right " style="color: whitesmoke;background-color: orangered">Tous!!</button></a>
                                </div>
                            </div>

                            <div class="space20"></div>
                            <div class="space20"></div>



                            <c:if test="${not empty table}">
                                <div class="row-fluid text-center">
                                    <h2 class="text-info text-capitalize text-center text-primary" > Historique de Sortir : <c:if test="${empty designation}"><c:out value="Pour Tous Les Produits"/></c:if><c:out value="${designation}"/></h2>
                                    </div>
                                    <table class="table table-hover table-responsive table-bordered simple_print "cellspacing="0" width="100%">
                                        <thead class="text-primary">
                                            <tr>                
                                                <th>CODE</th>
                                                <th>DESIGNATION</th>
                                                <th>DATE Sortir</th>                
                                                <th>STOCK Init</th>               
                                                <th>QUANTITE</th>
                                                <th>STOCK Apres</th> 
                                                <th>PERSONEL</th>
                                                <th>OPERATEUR</th>        
                                            </tr>
                                        </thead>
                                        <tbody id="tbody">
                                        <c:set var="t" value="${0}"/>
                                        <c:forEach items="${operation}" var="com">
                                            <tr class="produits">                  

                                                <td class="code"><c:out value="${com.getCodeProduit()}"/></td>
                                                <td class="pt"><c:out value="${com.getDesignation()}"/></td>
                                                <td class="date"><f:formatDate value="${com.getDate()}" type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM"/></td>
                                                <td class="qte"><f:formatNumber value="${com.getStock()}"type="NUMBER"/></td>
                                                <td class="qte"><f:formatNumber value="${com.getQuantite()}"type="NUMBER"/></td>
                                                <td class="qte"><f:formatNumber value="${com.getStockApres()}"type="NUMBER"/></td>
                                                <td class="personel"><c:out value="${com.getPersonnel().getNomPrenom()}"/></td>
                                                <td class="personel"><c:out value="${com.getOperateur()}"/></td>                    
                                            </tr>
                                            <c:set var="t" value="${t+com.getQuantite()}"/>
                                        </c:forEach>

                                    </tbody>
                                    <tfoot>
                                        <tr  class="text-capitalize text-center text-success text-primary text-info">
                                            <td colspan="8">
                                                La Quantite Total Commander est de:<f:formatNumber value="${t}" type="NUMBER"/>
                                            </td>

                                        </tr>
                                    </tfoot>              
                                </table>
                            </c:if> 

                        </div>

                        <div id="mouvementEntrer" class="tab-pane fade <c:if test="${sorti == 'ONE'}">in active</c:if>">
                                <div class="row-fluid span12 color-red " >
                                    <form method="post" action="listeproduit?action=one_produit&vue=editeMagasinS&id_magasin=${magasin.getIdMagasin()}&niveau=5&entrer=yes" class="form-inline">

                                    <!-- Les champs texte avec le code "onclick" déclenchant le script -->
                                    <label for="date1 "class="form-control-label " >DU</label>
                                    <input class="datepicker form-control"required name="date1" type="text"/>       
                                    <label for="date2 "class="form-control-label " >AU</label>
                                    <input class="datepicker form-control" name="date2"  required type="text"/>  
                                    <label for="designation "class="form-control-label " >Designation</label>
                                    <input type="text"   class=" tag " required id="designation" name="designation"/>  


                                    <button type="submit" class="btn btn-success  btn-primary pull-righ " id="sendcc" >Search</button>

                                </form>
                                <div class="form-control span11">
                                    <a  href="listeproduit?action=getInventaireMagSAdmin&vue=editeMagasinS&id_magasin=${magasin.getIdMagasin()}&niveau=5&entrer=yes"><button class="btn btn-lg btn-large bg-info text-capitalize pull-right " style="color: whitesmoke;background-color: orangered">Tous!!</button></a>
                                </div>
                            </div>

                            <div class="space20"></div>
                            <div class="space20"></div>

                            <c:if test="${not empty table}">
                                <h2 class="text-info text-capitalize text-center text-primary" > Historique D'entrer :<c:if test="${empty designation}"><c:out value="Pour Tous Les Produits"/></c:if> <c:out value="${designation}"/></h2>
                                    <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                                        <thead class="text-primary">
                                            <tr>
                                                <th>CODE</th>
                                                <th>DESIGNATION</th>
                                                <th>DATE D'Entrer</th>                
                                                <th>STOCK Initial</th>               
                                                <th>QUANTITE Recus</th>
                                                <th>STOCK Apres</th> 
                                                <th>FOURNISSEUR</th>
                                                <th>RECEPTEUR</th>        
                                            </tr>
                                        </thead>
                                        <tbody id="tbody">
                                        <c:set var="t" value="${0}"/>
                                        <c:forEach items="${listetransfert}" var="co">
                                            <tr class="produits">                  

                                                <td class="code"><c:out value="${co.getIdTransfert().getCodeProduit()}"/></td>
                                                <td class="pt"><c:out value="${co.getIdTransfert().getDesignation()}"/></td>
                                                <td class="date"><f:formatDate value="${co.getDateOperation()}" type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM"/></td>
                                                <td class="qte"><f:formatNumber value="${co.getQuantiteInit()}"type="NUMBER"/></td>
                                                <td class="qte"><f:formatNumber value="${co.getIdTransfert().getQuantite()}"type="NUMBER"/></td>
                                                <td class="qte"><f:formatNumber value="${co.getQuantiteApres()}"type="NUMBER"/></td>
                                                <td class="personel"><c:out value="${co.getIdTransfert().getMp().getNomMagasin()}"/></td>
                                                <td class="personel"><c:out value="${co.getOperateur()}"/></td>                    
                                            </tr>
                                            <c:set var="t" value="${t+co.getIdTransfert().getQuantite()}"/>
                                        </c:forEach>

                                    </tbody>
                                    <tfoot>
                                        <tr  class="text-capitalize text-center text-success text-primary text-info">
                                            <td colspan="8">
                                                La Quantite Total Recus est de:<f:formatNumber value="${t}" type="NUMBER"/>
                                            </td>

                                        </tr>
                                    </tfoot>              
                                </table>
                            </c:if> 

                        </div>

                        <div id="historique" class="tab-pane fade">
                            <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
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
                                    <c:forEach items="${historique}" var="lis">  
                                        <tr class="produits">

                                            <td class="categories"><c:out value="${lis. getCategorie()}"/></td>
                                            <td class="code"><c:out value="${lis.getCodeProduit()}"/></td>
                                            <td><c:out value="${lis.getDesignation()}"/></td>
                                            <td class="qte"><c:out value="${lis.getLastQuantite()}"/></td>
                                            <td class="pu"><f:formatNumber value="${lis.getNewQuantite()}" type="NUMBER"/></td>
                                            <td class="pu"><f:formatNumber value="${lis. getEcatQuantite()}" type="NUMBER"/></td>
                                            <td class="pu"><f:formatNumber value="${lis. getPrixUnitaire()}" type="CURRENCY"currencySymbol="FCFA"/></td>
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
        </div>
        <!-- END INLINE TABS PORTLET-->
    </div>
</div>