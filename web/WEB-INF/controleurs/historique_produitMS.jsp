<%-- 
    Document   : EtatMS
    Created on : 29 janv. 2017, 22:17:22
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- BEGIN INLINE TABS PORTLET-->
<c:set value="${idMS}" var="id_magasin" scope="session"></c:set>
    <div class="widget black">
        <div class="widget-title">
            <h4><i class="icon-reorder"></i> Espace Magasin: ${magasin.getNomMagasin()}</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <ul class="nav nav-tabs">
                <c:if test="${not empty sessionScope.StockMSControl}">
                    <li ><a href="listeproduit?vue=Historique MS&action=rien&niveau=4&id_magasin=${idMS}">Stocks Produits</a></li>
                    </c:if>
                    <c:if test="${ not empty sessionScope.ConsGeneralMSControl }">
                    <li ><a href="listeproduit?vue=Historique MS&action=rien&niveau=4&id_magasin=${idMS}&etat=ok">Etat Consommation General</a></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.MouvEMSControl or not empty sessionScope.MouvSMSControl}">
                    <li ><a href="listeproduit?vue=Historique MS&action=all_produit&niveau=4&id_magasin=${idMS}&mouv=ok">Mouvements Des Produits</a></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.StatistiqueMS}">
                    <li ><a href="RedirectionVue?vue=Historique MS&niveau=4&id_magasin=${idMS}">Statistiques Consommation Produits</a></li>
                    </c:if>
                    <c:if test="${1 == 1}">
                    <li ><a href="ValiderCommande?vue=Historique MS&id_magasin=${idMS}&action=redirect">Statistiques Budgetaire : ${magasin.getNomMagasin()}</a></li>
                    </c:if>
                    <c:if test="${1 == 1}">
                    <li ><a href="RedirectionVue?vue=Historique MS&niveau=4&id_magasin=${idMS}&rapportms=oui">Repporting : ${magasin.getNomMagasin()}</a></li>
                    </c:if>
            </ul>
            <c:if test="${historique == 'OK'}">
                <ul class="nav nav-tabs" id="myTab">
                    <c:if test="${not empty sessionScope.MouvSMSControl}">
                        <li class="<c:if test="${Sorti == 'OK'}">active</c:if>"><a data-toggle="tab" href="#mouvement">Mouvements Sorti Produits: ${magasin.getNomMagasin()}</a></li>
                        </c:if>
                        <c:if test="${not empty sessionScope.MouvEMSControl}">
                        <li class="<c:if test="${entrerSS == 'ONE'}">active</c:if>"><a data-toggle="tab" href="#mouvementEntrer">Mouvements D'Entrer Produits: ${magasin.getNomMagasin()}</a></li>
                        </c:if>
                </ul>
                <div class="space20"></div>
                <div class="space20"></div>
                <div class="tab-content" id="myTabContent">
                    <div id="mouvement" class="tab-pane fade  <c:if test="${Sorti == 'OK'}">in active</c:if>">
                            <div class="row-fluid color-red " >
                                <form method="post" action="listeproduit?vue=Historique MS&action=one_produit&niveau=4&id_magasin=${idMS}&mouv=ok" class="form-inline">

                                <!-- Les champs texte avec le code "onclick" déclenchant le script -->
                                <label for="date1 "class="form-control-label " >DU</label>
                                <input class="datepicker form-control"required name="date1" type="text"/>       
                                <label for="date2 "class="form-control-label " >AU</label>
                                <input class="datepicker form-control" name="date2"  required type="text"/>  
                                <label for="designation "class="form-control-label " >Designation</label>
                                <input type="text"   class=" tag " id="designation" name="designation"/>  


                                <button type="submit" class="btn btn-success  btn-primary pull-righ " id="sendcc" >Search</button>

                            </form>
                            <div class="form-control span11">
                                <a  href="listeproduit?vue=Historique MS&action=all_produit&niveau=4&id_magasin=${idMS}&mouv=ok"><button class="btn btn-lg btn-large bg-info text-capitalize pull-right " style="color: whitesmoke;background-color: orangered">Mouvement General</button></a>
                            </div>
                        </div>

                        <div class="space20"></div>
                        <div class="space20"></div>
                        <div class="row-fluid">
                            <c:if test="${not empty info}">

                                <h2 class="text-info text-capitalize text-center text-primary" > Historique de Sortir : <c:if test="${empty designation}"><c:out value="Pour Tous Les Produits"/></c:if><c:out value="${designation}"/></h2>


                                    <table class="table table-hover table-responsive table-bordered simple_print "cellspacing="0" width="100%">
                                        <thead>
                                            <tr>      
                                                <th>CATEGORIE</th>
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
                                                <td class="code"><c:out value="${com.getCategorie()}"/></td>
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
                                            <td colspan="9">
                                                La Quantite Total Commander est de:<f:formatNumber value="${t}" type="NUMBER"/>
                                            </td>

                                        </tr>
                                    </tfoot>              
                                </table>

                            </c:if> 
                        </div>
                    </div>

                    <div id="mouvementEntrer" class="tab-pane fade <c:if test="${entrerSS == 'ONE'}">in active</c:if>">

                            <div class="row-fluid  color-red " >
                                <form method="post" action="listeproduit?vue=Historique MS&action=one_produit&niveau=4&id_magasin=${idMS}&mouv=ok&entrerS=yes" class="form-inline">

                                <!-- Les champs texte avec le code "onclick" déclenchant le script -->
                                <label for="date1 "class="form-control-label " >DU</label>
                                <input class="datepicker form-control"required name="date1" type="text"/>       
                                <label for="date2 "class="form-control-label " >AU</label>
                                <input class="datepicker form-control" name="date2"  required type="text"/>  
                                <label for="designation "class="form-control-label " >Designation</label>
                                <input type="text"   class=" tag " id="designation" name="designation"/>  


                                <button type="submit" class="btn btn-success  btn-primary pull-righ " id="sendcc" >Search</button>

                            </form>
                            <div class="form-control span11">
                                <a  href="listeproduit?vue=Historique MS&action=all_produit&niveau=4&id_magasin=${idMS}&rapportms=ouimouv=ok&entrerS=yes"><button class="btn btn-lg btn-large bg-info text-capitalize pull-right " style="color: whitesmoke;background-color: orangered">Mouvement General</button></a>
                            </div>
                        </div>

                        <div class="space20"></div>
                        <div class="space20"></div>

                        <div class="row-fluid" >
                            <c:if test="${not empty info}">
                                <h2 class="text-info text-capitalize text-center text-primary" > Historique D'entrer :<c:if test="${empty designation}"><c:out value="Pour Tous Les Produits"/></c:if> <c:out value="${designation}"/></h2>
                                    <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                                        <thead class="text-primary">
                                            <tr>
                                                <th>CATEGORIE</th>
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
                                                <td class="code"><c:out value="${co.getIdTransfert().getTypeProduit()}"/></td>
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
                                            <td colspan="9">
                                                La Quantite Total Recus est de:<f:formatNumber value="${t}" type="NUMBER"/>
                                            </td>

                                        </tr>
                                    </tfoot>              
                                </table>

                            </c:if> 
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${stock == 'OK'}">
                <div class="row-fluid">  

                    <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                        <thead class="text-primary">
                            <tr>
                            <tr>
                                <th>CATEGORIE</th>
                                <th>CODE</th>
                                <th>DESIGNATION</th>
                                <th>QUANTITE</th>
                                <th class="hidden">QUANTITE</th>
                                <th>PRIX UNITAIRE</th>
                                <th>PRIX TOTAL</th>
                                <th>DATE LIVRAISON</th>
                            </tr>
                            </tr>
                        </thead>
                        <tbody id="tbody">
                            <c:forEach items="${categorie}" var="c">



                                <c:forEach items="${c.getStockproduitMSList()}"var="r">
                                    <tr class="produits">
                                        <td > <c:out value="${c.getNomCategorie()}"/> </td>
                                        <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                                        <td><c:out value="${r.getDesignation()}"/></td>
                                        <td class="qteu"><f:formatNumber value="${r.getQuantite()}"type="NUMBER"/></td>
                                        <td class="qteuc hidden"><input class="newval focus" autofocus="true" type='number' id='qteu'  /></td>
                                        <td><f:formatNumber value="${r.getPrixUnitaire()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                        <td><f:formatNumber value="${r.getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                        <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                                    </tr>

                                </c:forEach>
                            </c:forEach>
                        </tbody>
                    </table> 
                </div>
            </c:if>
            <c:if test="${statBudgetMS == 'data'}">
                <c:import url="/WEB-INF/controleurs/statistiqueBudgetaireMS.jsp"/>
            </c:if>
            <c:if test="${etat == 'OK'}">
                <c:import url="/WEB-INF/controleurs/EtatMS.jsp"/>
            </c:if>
            <c:if test="${stat == 'OK'}">
                <c:import url="/WEB-INF/controleurs/statistiqueMS.jsp"/>
            </c:if>

            <c:if test="${rapportms == 'ok'}">
                <c:import url="/WEB-INF/controleurs/rapportMS.jsp"/>
            </c:if>

        </div>
    </div>
</div>

