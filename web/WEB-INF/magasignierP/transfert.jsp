<%-- 
    Document   : BonEntre
    Created on : 22 nov. 2016, 15:13:03
    Author     : messi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h3 class="page-title">
    Suivi de Transfert du Magasin Principal vers le Magasin Secondaire
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
<div class="widget black">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i> Espace Traitement Commande: ${magasin.getNomMagasin()}</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">

            <c:if test="${not empty modifier}">
                <div class="row-fluid">
                    <form method="post" action="CommandeRecusMP?vue=Suivi Transfert&action=saveedit" class="form-inline">

                        <label style="margin-left: 50px" for="code">CODE</label>
                        <input style="margin-right: 50px" type="text" class=" form-control  "id="code" disabled="true" value="${transf.getCodeProduit()}"  name="code" />


                        <label  for="designation">DESIGNATION</label>
                        <input style="margin-right: 50px" type="text" class=" form-control  " disabled="true" value="${transf.getDesignation()}" id="designation" name="designation" />

                        <input type="text" class=" form-control  hidden"id="code"  value="${transf.getIdTransfert()}"  name="id" />

                        <div class="space20"></div> 
                        <div class="span12">
                            <label style="margin-left: 50px" for="quantite">QUANTITE</label>
                            <input style="margin-right: 50px" type="number" class=" form-control  "  value="${transf.getQuantite()}" id="quantite" name="quantite" />



                            <label for="raison">Votre Observation :</label>
                            <textarea style="margin-right: 50px" type="text" id="raison" class="form-control" required="true" name="raison"></textarea>



                            <button type="submit" class="btn btn-success  btn-primary pull-righ " >Envoyer</button>

                        </div>
                    </form>
                </div>
            </c:if>
            <div class="space20"></div> 
            <div class="space20"></div> 
            <div class="row-fluid ">  
                <table class="table table-bordered table-hover table-responsive simple_print"cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th class="hidden">id</th>
                            <th>CATEGORIE</th>
                            <th>CODE</th>
                            <th>DESIGNATION</th>
                            <th>QUANTITE</th>
                            <th>PRIX UNITAIRE</th>
                            <th>PRIX TOTAL</th>
                            <th>DATE LIVRAISON</th>                  
                            <th>Etat du transfert</th>
                            <th>Raison</th>
                            <th>Options</th>
                        </tr>
                    </thead>
                    <tbody id="tbody">
                        <c:forEach items="${transfert}" var="transf">
                            <tr class="produits">
                                <td class="hidden"> <c:out value="${transf.getIdTransfert()}"/></td>
                                <td> <c:out value="${transf.getTypeProduit()}"/></td>
                                <td class="code"> <c:out value="${transf.getCodeProduit()}"/></td>
                                <td> <c:out value="${transf.getDesignation()}"/></td>
                                <td> <f:formatNumber value="${transf.getQuantite()}" type="NUMBER"/></td>
                                <td> <c:out value="${transf.getPrixUnitaire()}"/></td>
                                <td> <f:formatNumber value="${transf.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA" maxFractionDigits="0"/></td>
                                <td> <f:formatDate value="${transf.getDate()}" type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM"/></td>                      
                                <td> <c:out value="${transf.getEtat()}"/></td>
                                <td> <c:out value="${transf.getCommentaireMS()}"/></td>

                                <td class="categories">
                                    <div class="btn-group">
                                        <a title="" href="CommandeRecusMP?idtransfert=${transf.getIdTransfert()}&action=edittransfert&vue=Suivi Transfert" class="btn btn-primary">  <span class="icon"> <i class="icon-edit"></i> </span></a>  

                                        <a class="btn btn-danger" href="CommandeRecusMP?idtransfert=${transf.getIdTransfert()}&action=deletetransfert&vue=Suivi Transfert" ><span class="icon"> <i class="icon-trash"></i> </span></a>

                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> <br>
                <c:if test="${not empty message }">
                    <div class="alert alert-danger text-center ">
                        <strong>${message.getMessage()}</strong>
                    </div>
                </c:if>

            </div>
        </div>
    </div>
</div>
