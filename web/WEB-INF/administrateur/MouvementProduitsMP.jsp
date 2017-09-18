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
                        <c:if test="${not empty sessionScope.lien44 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${sortie == 'OK'}">active</c:if>"><a data-toggle="tab" href="#mouvement">Mouvement Produits En Sorti: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien43 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="<c:if test="${sortie == 'ON'}">active</c:if>"><a data-toggle="tab" href="#historique">Mouvements Produits En Entrer: ${magasin.getNomMagasin()}</a></li>
                            </c:if>
                    </ul>

                    <div class="space20"></div>
                    <div class="tab-content" id="myTabContent">

                        <div id="mouvement" class="tab-pane fade  <c:if test="${sortie == 'OK'}">in active</c:if>">
                                <div class="row-fluid span12 color-red " >
                                    <form method="post" action="ListeProduitMP?action=one_produit&vue=editeMagP&id_magasinP=${magasin.getIdMagasin()}&niveau=5&mouv=yes" class="form-inline">

                                    <!-- Les champs texte avec le code "onclick" déclenchant le script -->
                                    <label for="date1 "class="form-control-label " >DU</label>
                                    <input class="datepicker form-control"required name="date1" type="text"/>       
                                    <label for="date2 "class="form-control-label " >AU</label>
                                    <input class="datepicker form-control" name="date2"   type="text"/>  
                                    <label for="designation "class="form-control-label " >Designation</label>
                                    <input type="text"   class=" tag " id="designation" name="designation"/>  


                                    <button type="submit" class="btn btn-success  btn-primary pull-righ " id="sendcc" >Search</button>

                                </form>
                                <div class="form-control span11">
                                    <a  href="ListeProduitMP?action=all_produit&vue=editeMagP&id_magasinP=${magasin.getIdMagasin()}&niveau=5&mouv=yes"><button class="btn btn-lg btn-large bg-info text-capitalize pull-right " style="color: whitesmoke;background-color: orangered">Tous!!</button></a>
                                </div>
                            </div>

                            <div class="space20"></div>
                            <div class="space20"></div>


                            <div class="row-fluid text-center">
                                <h2 class="text-info text-capitalize text-center text-primary" > Historique de Sortir : <c:if test="${empty designation}"><c:out value="Pour Tous Les Produits"/></c:if><c:out value="${designation}"/></h2>
                                </div>
                                <table class="table table-hover table-responsive table-bordered simple_print "cellspacing="0" width="100%">
                                    <thead class="text-primary">
                                        <tr>
                                            <th>CATEGORIE</th>
                                            <th>CODE</th>
                                            <th>DESIGNATION</th>
                                            <th>DATE Sortir</th>                
                                            <th>STOCK Init</th>               
                                            <th>QUANTITE</th>
                                            <th>STOCK Apres</th> 
                                            <th>MAGASIN</th>
                                            <th>OPERATEUR</th>        
                                        </tr>
                                    </thead>
                                    <tbody id="tbody">
                                    <c:set var="t" value="${0}"/>
                                    <c:forEach items="${operation}" var="com">
                                        <tr class="produits">                  
                                            <td class="categories"><c:out value="${com.getIdTransfert().getTypeProduit()}"/></td>
                                            <td class="code"><c:out value="${com.getIdTransfert().getCodeProduit()}"/></td>
                                            <td class="pt"><c:out value="${com.getIdTransfert().getDesignation()}"/></td>
                                            <td class="date"><f:formatDate value="${com.getDateOperation()}" type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM"/></td>
                                            <td class="qte"><f:formatNumber value="${com.getQuantiteInit()}"type="NUMBER"/></td>
                                            <td class="qte"><f:formatNumber value="${com.getIdTransfert().getQuantite()}"type="NUMBER"/></td>
                                            <td class="qte"><f:formatNumber value="${com.getQuantiteApres()}"type="NUMBER"/></td>
                                            <td class="personel"><c:out value="${com.getIdTransfert().getMp().getNomMagasin()}"/></td>
                                            <td class="personel"><c:out value="${com.getOperateur()}"/></td>                   
                                        </tr>
                                        <c:set var="t" value="${t+com.getIdTransfert().getQuantite()}"/>
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

                            <div class="space20"></div>
                            <div class="space20"></div>


                        </div>


                        <div id="historique" class="tab-pane fade <c:if test="${sortie == 'ON'}">in active</c:if>">
                                <div class="row-fluid span12 color-red " >
                                    <form method="post" action="ListeProduitMP?action=one_produit&vue=editeMagP&id_magasinP=${magasin.getIdMagasin()}&niveau=5" class="form-inline">

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
                                    <a  href="ListeProduitMP?action=all_produit&vue=editeMagP&id_magasinP=${magasin.getIdMagasin()}&niveau=5"><button class="btn btn-lg btn-large bg-info text-capitalize pull-right " style="color: whitesmoke;background-color: orangered">Tous!!</button></a>
                                </div>
                            </div>

                            <div class="space20"></div>
                            <div class="space20"></div>


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

                                            <td class="code"><c:out value="${co.getCodeProduit()}"/></td>
                                            <td class="pt"><c:out value="${co.getDesignation()}"/></td>
                                            <td class="date"><f:formatDate value="${co.getDate()}" type="DATE" dateStyle="MEDIUM" /></td>
                                            <td class="qte"><f:formatNumber value="${co.getQuantiteInit()}"type="NUMBER"/></td>
                                            <td class="qte"><f:formatNumber value="${co.getQuantite()}"type="NUMBER"/></td>
                                            <td class="qte"><f:formatNumber value="${co.getQuantiteApres()}"type="NUMBER"/></td>
                                            <td class="personel"><c:out value="${co.getIdFounisseur().getNomFounisseur()}"/></td>
                                            <td class="personel"><c:out value="${co.getRecepteur()}"/></td>                    
                                        </tr>
                                        <c:set var="t" value="${t+co.getQuantite()}"/>
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

                        </div>

                        <div class="space20"></div>
                        <div class="space20"></div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END INLINE TABS PORTLET-->
    </div>
</div>