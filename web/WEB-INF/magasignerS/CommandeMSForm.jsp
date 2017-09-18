<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="page-title">
    Passer Une Commande A Un Magasin Principal
</h3>
<ul class="breadcrumb">
    <li>
        <a href="RedirectionVue?vue=Accueil"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">Commandes Encours</a>

    </li>

    <li class="pull-right search-wrap">
        <form action="#" class="hidden-phone">
            <div class="input-append search-input-area">
                <input class="" id="appendedInputButton" type="text">
                <button class="btn" type="button"><i class="icon-search"></i> </button>
            </div>
        </form>
    </li>
</ul>

<div class="widget black">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i> Passer Vos Commandes Au Magasin Principal Ici</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <form method="post" action="commandeMS?vue=Commande produits&action=create" class="form-inline">

                <div class="row-fluid">
                    <div class="control-group span3">
                        <label class="control-label"> Magasin</label>
                        <div class="controls ">
                            <select  class=" form-control span12"name="fournisseur" required style="margin-right: 50px">
                                <option value="${magasinMP.getIdMagasin()}" >${magasinMP.getNomMagasin()}</option>
                                <c:forEach items="${fournisseur}" var="cc">  
                                    <option  value="${cc.getIdMagasin()}" >${cc.getNomMagasin()}</option>  
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="control-group span2">
                        <label class="control-label"> Categorie</label>
                        <div class="controls ">
                            <select class="form-control span12" id="categorietoarticles" required name="categorie" style="margin-left: auto">
                                <option value="${categos.getIdCategorie()}" selected>${categos.getNomCategorie()}</option>
                                <c:forEach items="${sessionScope.categoriee}" var="cc">  
                                    <option value="${cc.getIdCategorie()}">${cc.getNomCategorie()}</option>  
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="control-group span4" >
                        <label class="control-label"> Article</label>
                        <div class="controls ff " id="">
                            <select class="form-control exampleSelect " id="articlesCateorie" required name="designation"style="margin-right: auto">

                            </select>
                        </div>
                    </div>
                                
                    <div class="control-group span2">
                        <label class="control-label"> Quantite</label>
                        <div class="controls ">
                            <input type="number" id="quantite" class=" form-control span12"  placeholder="Quantite" required name="quantite" style="margin-left:  0px; margin-right: 60px"/>
                        </div>
                    </div>
                                <div class="control-group span1" ></div>
                </div>
                <div class="row-fluid">

                    <div class="control-group span4">
                        <div class="controls ">
                            <button type="submit" class="btn btn-success  "style="margin-top: 22px" ><span class="icon icon-shopping-cart"></span></button>

                        </div>
                    </div>
                </div>
            </form>
            <div class="space20"></div>

            <div class="row-fluid">    
                <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                    <thead class="text-primary">
                        <tr>
                            <th>CATEGORIE</th>
                            <th>CODE</th>
                            <th>DESIGNATION</th>
                            <th>QUANTITE EN STOCK</th>
                            <th>QUANTITE COMMANDER</th>
                            <th class="hidden">QUANTITE</th>
                            <th>COMMANDER LE</th>
                            <th>DERNIERE LIVRAISON</th>


                        </tr>
                    </thead> 
                    <tbody>
                        <c:forEach items="${listt}" var="liss">  
                            <tr class="produits">

                                <td class="categories"><c:out value="${liss.getTypeProduit()}"/></td>
                                <td class="code"><c:out value="${liss.getCodeProduit()}"/></td>
                                <td class="designation"><c:out value="${liss.getDesignation()}"/></td>
                                <td class="qte"><f:formatNumber value="${liss.getQuantiteEnStock()}" type="NUMBER"/></td>
                                <td class="qteu"><f:formatNumber value="${liss.getQuantiteCommande()}" type="NUMBER"/></td>
                                <td class="qteuc hidden"><input class="newvalcms" type='text' id='qteu'  /></td>                       
                                <td><f:formatDate value="${liss.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           
                                <td class="pt"><f:formatDate value="${liss.getDerniereLivraison()}"  type="Date" dateStyle="MEDIUM" /></td>

                            </tr>

                        </c:forEach>

                    </tbody>
                </table> 

            </div>
            <div class="space20"></div>

            <div class="row-fluid">
                <div class="form-actions">
                    <a href="commandeMS?action=save&vue=Commande produits"data-toggle="tooltip" title='Cliquer ici pour Envoyer cette commande' >  <button type="submit" class="btn   btn-success" style="margin-right: 60px"><i class="icon-save"></i> </button></a>
                    <button type="button" class="btn  btn-danger disabled" value="0" data-toggle="tooltip" title='Cliquer ici pour Supprimer La commande Selectionner'  id="deleteMS"><i class="icon-trash"></i></button>
                </div>

            </div>
            <c:if test="${not empty message}">
                <div class="alert alert-danger text-center ">
                    <strong>${message.getMessage()}</strong>
                </div>
            </c:if>

        </div>
        <div class="space20"></div>
        <div class="space20"></div>
        <div class="space20"></div>
    </div>
</div>
