<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="widget-body">
    <div class="bs-docs-example">
        <ul class="nav nav-tabs" id="myTab">
            <li class="active"><a data-toggle="tab" href="#c1">Commande Recus Pour: ${personnel.getNomPrenom()}</a></li>
            <li><a data-toggle="tab" href="#budget">BUDGET DU MAGASIN: ${nom_Magasin}</a></li>
            <li><a data-toggle="tab" href="#mag">STOCKS MAGASIN: ${nom_Magasin}</a></li>


        </ul>
        <div class="tab-content" >


            <div id="c1" class="tab-pane fade in active">
                <div class="row-fluid">

                    <h2>Commande Recus Par le Controleur Pour Approbation</h2>

                    <div class="widget black">
                        <div class="widget-title" style="background-color: #23527c">
                            <h4><i class="icon-reorder"></i> commandes Envoiyer Par Le Magasin: ${nom_Magasin}</h4>

                        </div>
                        <div class="widget-body ">
                            <div class="space15"></div>
                            <h4>Commandes Recus Pour Traitement</h4>
                            <table class="table table-bordered table-hover table-striped simple_print" id=""cellspacing="0" width="100%">
                                <thead class="text-primary">
                                    <tr>
                                        <th>CATEGORIE</th>
                                        <th>CODE</th>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE EN STOCK</th>
                                        <th>QUANTITE COMMANDER</th>                  
                                        <th>DATE LIVRAISON</th>
                                        <th>DERNIERE LIVRAISON</th>
                                        <th>FOURNISSEUR</th>

                                    </tr>
                                </thead> 
                                <tbody>
                                    <c:forEach items="${listeCMP}" var="lis">  
                                        <tr class="produits">

                                            <td class="categories"><c:out value="${lis.getTypeProduit()}"/></td>
                                            <td class="code"><c:out value="${lis.getCodeProduit()}"/></td>
                                            <td class="designation"><c:out value="${lis.getDesignation()}"/></td>
                                            <td class="qte"><f:formatNumber value="${lis.getQuantiteEnStock()}" type="NUMBER"/></td>
                                            <td class="qteu"><f:formatNumber value="${lis.getQuantiteCommande()}" type="NUMBER"/></td>                                         
                                            <td><f:formatDate value="${lis.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           
                                            <td class="pt"><f:formatDate value="${lis.getDerniereLivraison()}"  type="Date" dateStyle="MEDIUM" /></td>
                                            <td class="founisseur"><c:out value="${lis.getIdSA().getNomFounisseur()}"/></td>
                                        </tr>

                                    </c:forEach>

                                </tbody>
                            </table> 
                        </div>
                    </div>
                </div>
            </div>
            <div id="budget" class="tab-pane fade in">
                <div class="row-fluid">

                    <h2>BUDGET Du MAGASIN: ${nom_Magasin}</h2>

                    <div class="widget black">
                        <div class="widget-title" style="background-color: #23527c">
                            <h4><i class="icon-reorder"></i> Budget Disponible Pour Le Magasin: ${nom_Magasin}</h4>

                        </div>
                        <div class="widget-body ">
                            <div class="space15"></div>
                            <h4>BUDGET DISPONIBLE</h4>
                            <table class="table table-bordered table-hover table-striped " id=""cellspacing="0" width="100%">
                                <thead class=" thead-inverse">
                                    <tr>
                                        <th>CATEGORIE</th>
                                        <th>MONTANT</th>
                                        <th>ATTRIBUER LE</th>
                                        <th>EXPIRE LE</th>                                         
                                    </tr>
                                </thead>
                                <tbody id="tbody">

                                    <c:forEach items="${budget}" var="b">

                                        <tr class="produit">
                                            <td scope="row"> <c:out value="${b.getType()}"/> </td>
                                            <td><f:formatNumber value="${b.getMontant()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                            <td><f:formatDate value="${b.getDateAtribution()}" type="Date" dateStyle="MEDIUM"/></td>
                                            <td><f:formatDate value="${b.getDateExpiration()}" type="Date" dateStyle="MEDIUM"/></td>                                                                        
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div id="mag" class="tab-pane fade in">
                <div class="row-fluid">

                    <h2>STOCKS DU MAGASIN: ${nom_Magasin}</h2>

                    <div class="widget black">
                        <div class="widget-title" style="background-color: #23527c">
                            <h4><i class="icon-reorder"></i> Stock Disponible Pour Le Magasin: ${nom_Magasin}</h4>

                        </div>
                        <div class="widget-body ">
                            <div class="space15"></div>
                            <h4>STOCKS DISPONIBLE</h4>
                            <table class="table table-bordered table-hover table-striped " id=""cellspacing="0" width="100%">
                                <thead class=" thead-inverse">
                                    <tr>
                                        <th>CATEGORIE</th>
                                        <th>CODE</th>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE</th>                        
                                        <th>DATE LIVRAISON</th>
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>