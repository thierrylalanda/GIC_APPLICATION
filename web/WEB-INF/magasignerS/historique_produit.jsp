<%-- 
    Document   : EtatMS
    Created on : 29 janv. 2017, 22:17:22
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h3 class="page-title">
    Suivi d'inventaires Periodique d'un Article 
</h3>
<ul class="breadcrumb">
    <li>
        <a href="RedirectionVue?vue=Accueil"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">mouvement Produits</a>

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
        <h4><i class="icon-reorder"></i> Mouvement Produits Magasin: ${magasin.getNomMagasin()}</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <ul class="nav nav-tabs" id="myTab">
                <c:if test="${not empty sessionScope.MouvSortiMS}">
                    <li class="<c:if test="${Sorti == 'OK'}">active</c:if>"><a data-toggle="tab" href="#mouvement">Mouvements Sorti Produits: ${magasin.getNomMagasin()}</a></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.MouvEntrerMS}">
                    <li class="<c:if test="${entrerSS == 'ONE'}">active</c:if>"><a data-toggle="tab" href="#mouvementEntrer">Mouvements D'Entrer Produits: ${magasin.getNomMagasin()}</a></li>
                    </c:if>
            </ul>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="tab-content" id="myTabContent">
                <c:if test="${not empty sessionScope.MouvSortiMS}">
                    <div id="mouvement" class="tab-pane fade  <c:if test="${Sorti == 'OK'}">in active</c:if>">
                            <div class="row-fluid color-red " >
                                <form method="post" action="listeproduit?vue=Mouvement Produits&action=one_produit" class="form-inline">

                                    <!-- Les champs texte avec le code "onclick" déclenchant le script -->
                                    <label for="date1 "class="form-control-label " >DU</label>
                                    <input class="datepicker form-control"required name="date1" type="text"/>       
                                    <label for="date2 "class="form-control-label " >AU</label>
                                    <input class="datepicker form-control" name="date2"  required type="text"/>  
                                    <label for="date1 "class="form-control-label " >Designation</label>
                                    <select class="form-control OneSelect" required name="designation">
                                    <c:forEach items="${stockMS}" var="cc">  
                                        <option >${cc.getDesignation()}</option>  
                                    </c:forEach>

                                </select>

                                <button type="submit" class="btn btn-success  btn-primary pull-righ " id="sendcc"style="margin-left: 50px" >Search</button>

                            </form>
                            <div class="form-control span11 hidden">
                                <a  href="listeproduit?vue=Mouvement Produits&action=all_produit"><button class="btn btn-lg btn-large bg-info text-capitalize pull-right " style="color: whitesmoke;background-color: orangered">Tous Les Mouvements!!</button></a>
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
                </c:if>
                <c:if test="${not empty sessionScope.MouvSortiMS}">
                    <div id="mouvementEntrer" class="tab-pane fade <c:if test="${entrerSS == 'ONE'}">in active</c:if>">

                            <div class="row-fluid  color-red " >

                                <form method="post" action="listeproduit?vue=Mouvement Produits&action=one_produit&entrerS=yes" class="form-inline">
                                    <!-- Les champs texte avec le code "onclick" déclenchant le script -->
                                    <label for="date1 "class="form-control-label " >DU</label>
                                    <input class="datepicker form-control"required name="date1" type="text"/>       
                                    <label for="date2 "class="form-control-label " >AU</label>
                                    <input class="datepicker form-control" name="date2"  required type="text"/>  
                                    <label for="date1 "class="form-control-label " >Designation</label>
                                    <select class="form-control OneSelect" required name="designation" >
                                    <c:forEach items="${stockMS}" var="cc">  
                                        <option >${cc.getDesignation()}</option>  
                                    </c:forEach>

                                </select>

                                <button type="submit" class="btn btn-success  btn-primary pull-righ " id="sendcc" style="margin-left: 50px">Search</button>

                            </form>
                            <div class="form-control span11 hidden">
                                <a  href="listeproduit?vue=Mouvement Produits&action=all_produit&entrerS=yes"><button class="btn btn-lg btn-large bg-info text-capitalize pull-right " style="color: whitesmoke;background-color: orangered">Tous Les Mouvements!!</button></a>
                            </div>
                        </div>

                        <div class="space20"></div>
                        <div class="space20"></div>

                        <div class="row-fluid" >
                            <c:if test="${not empty info}">
                                <h2 class="text-info text-capitalize text-center text-primary" > Historique D'entrer :<c:if test="${empty designation}"><c:out value="Pour Tous Les Produits"/></c:if> <c:out value="${designation}"/></h2>
                                    <table class="table table-hover table-responsive table-bordered simpletable"cellspacing="0" width="100%">
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
                </c:if>
            </div>
        </div>
    </div>
</div>

