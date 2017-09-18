<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="row-fluid">
    <div class="span12">
        <!-- BEGIN INLINE TABS PORTLET-->
        <div class="widget black">
            <div class="widget-title">
                <h4><i class="icon-reorder"></i> Espace Magasin: ${nom_Magasin}</h4>
                <span class="tools">
                    <a href="javascript:;" class="icon-chevron-down"></a>

                </span>
            </div>
            <div class="widget-body">
                <div class="bs-docs-example">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a data-toggle="tab" href="#transfert">Commandes: ${nom_Magasin}</a></li>
                        <li ><a data-toggle="tab" href="#commandeTraiter">Stock: ${nom_Magasin}</a></li>

                    </ul>
                    <div class="space10"></div>
                    <div class="tab-content" id="myTabContent">

                        <div class="space20"></div>
                        <div id="commandeTraiter" class="tab-pane fade">
                            <table class="table table-hover table-bordered simple_print"cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>CATEGORIE</th>
                                        <th>CODE</th>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE</th>                        
                                        <th>DATE</th>
                                    </tr>
                                </thead>
                                <tbody id="tbody">

                                    <c:forEach items="${produitMP}" var="c">

                                        <tr class="produit">

                                            <c:forEach items="${c.getStockproduitMPList()}"var="r">

                                                <td scope="row"> <c:out value="${c.getNomCategorie()}"/> </td>
                                                <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                                                <td><c:out value="${r.getDesignation()}"/></td>
                                                <td><f:formatNumber value="${r.getQuantite()}" type="NUMBER" /></td>                               
                                                <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                                            </tr>

                                        </c:forEach>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <c:if test="${not empty message}">
                                <div class="alert alert-success  span12 text-center ">
                                    <strong>${message.getMessage()}</strong>
                                </div>
                            </c:if>
                        </div>
                        <div id="transfert" class="tab-pane fade in active">

                            <div class="row-fluid responsive"> 

                                <table id="sample_3" class="table table-hover table-bordered table-striped simple_print" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th style="width:8px;"><input type="checkbox" class="group-checkable" data-set="#sample_3 .checkboxes" /></th>
                                            <th>CATEGORIE</th>
                                            <th>CODE</th>
                                            <th>DESIGNATION</th>
                                            <th>QUANTITE COMMANDER</th>  
                                            <th>PRIX UNITAIRE</th>  
                                            <th>PRIX TOTAL</th>  
                                            <th>QUANTITE EN STOCK</th>
                                            <th>DATE</th>
                                            <th>DERNIERE LIVRAISON</th>
                                            <th>FOURNISSEUR</th>


                                        </tr>
                                    </thead> 
                                    <tbody>
                                        <c:set var="t" value="${0}"/>
                                        <c:forEach items="${listeCMP}" var="lis">  
                                            <tr class="odd gradeX" name="${lis.getIdCommande()}">
                                                <td><input type="checkbox" class="checkboxes" value="${lis.getIdCommande()}" /></td>
                                                <td class="categories"><c:out value="${lis.getTypeProduit()}"/></td>
                                                <td class="code"><c:out value="${lis.getCodeProduit()}"/></td>
                                                <td class="designation"><c:out value="${lis.getDesignation()}"/></td>
                                                <td class="qteu"><f:formatNumber value="${lis.getQuantiteCommande()}" type="NUMBER"/></td>
                                                <td class=""><f:formatNumber value="${lis.getPrixUnitaire()}" type="NUMBER" currencySymbol="FCFA"/></td>
                                                <td class=""><f:formatNumber value="${lis.getPrixTotal()}" type="NUMBER" currencySymbol="FCFA"/></td>
                                                <td class="qte"><f:formatNumber value="${lis.getQuantiteEnStock()}" type="NUMBER"/></td>
                                                <td><f:formatDate value="${lis.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           
                                                <td class="pt"><f:formatDate value="${lis.getDerniereLivraison()}"  type="Date" dateStyle="MEDIUM" /></td>
                                                <td class="founisseur"><c:out value="${lis.getIdSA().getNomFounisseur()}"/></td>

                                                <c:set var="tt" value="${lis.getPrixUnitaire() * lis.getQuantiteCommande()}"/>
                                                <c:set var="t" value="${t + tt}"/>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table> 

                                <p  style="text-align: center">Total: <f:formatNumber value="${t}"type="CURRENCY" currencySymbol="FCFA"/></p>

                                <div class="space20"></div>
                                <div class="row-fluid">
                                    <form method="post" action="ValiderCommande?vue=Commandes a Traiter" class="form-inline form-group ">

                                        <div class="control-group span6">
                                            <a class="btn btn-success saveall tooltips"  data-toggle="tooltip" title='Valider les commandes selectionnés'><span class="icon"> <i class="icon-save"></i> </span></a>

                                            <a class="btn btn-danger deleteCMP tooltips" data-placement="bottom" data-toggle="tooltip" title='Rejeter les commandes selectionnés'><span class="icon"> <i class="icon-eject"></i> </span></a>
                                            <textarea placeholder="raison du rejet" class="form-hide hidden form-inline form-group-lg  input-lg" style="min-width: 119px;min-height:79px ;max-height: 70px;max-width: 120px" type="text" name="raison" ></textarea>
                                            <button type="submit" class="btn btn-primary form-hide hidden" >OK</button>
                                        </div>


                                    </form>

                                </div>

                                <div class="space20"></div>

                            </div>

                            <div class="space20"></div>
                            <c:if test="${not empty message}">
                                <div class="alert alert-success  span12 text-center ">
                                    <strong>${message.getMessage()}</strong>
                                </div>
                            </c:if>

                        </div>
                    </div>
                </div>


            </div>
        </div>

    </div>

</div>

