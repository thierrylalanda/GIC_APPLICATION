<%-- 
    Document   : addStock
    Created on : 19 mai 2017, 07:28:36
    Author     : lalanda
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="widget-body span12">
    <div class="bs-docs-example">
        <ul class="nav nav-tabs" id="myTab">
            <li class="<c:if test="${forme == 'ONE'}">active</c:if>"><a data-toggle="tab" href="#h1">Depuis Un Formulaire</a></li>
            <li class="<c:if test="${forme == 'NON'}">active</c:if>"><a data-toggle="tab" href="#pass">Depuis un Fichier</a></li>

            </ul>
            <div class="tab-content" >
                <div class="space15"></div>
                <div id="h1" class="tab-pane fade <c:if test="${forme == 'ONE'}">in active</c:if>">
                <c:if test="${empty updateStock}">
                    <form class="form-inline" method="post" role="form" action="AjouterStockMS?vue=createMagS&action=formulaire&niveau=5" >
                        <div class="row-fluid">

                            <div class="row-fluid">
                                <div class="control-group span4">
                                    <label class="control-label">Nom Magasin</label>
                                    <div class="controls ">
                                        <select class="input-lg form-control span12 magasinbyregion" name="id_magasin" required >
                                            <option value="${magasin.getIdMagasin()}">${magasin.getNomMagasin()}</option>
                                            <c:forEach items="${MagasinsS}" var="ms">
                                                <c:if test="${magasin.getIdMagasin() != ms.getIdMagasin() }">
                                                    <option value="${ms.getIdMagasin()}">${ms.getNomMagasin()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>
                                <div class="control-group span4">
                                    <label class="control-label"> Categorie</label>
                                    <div class="controls ">
                                        <select class="form-control span12" id="categorietoarticles" required name="categorie">
                                            <option value="${categos.getIdCategorie()}" selected>${categos.getNomCategorie()}</option>
                                            <c:forEach items="${categoriesMS}" var="cat">
                                                <c:if test="${cat.getIdCategorie() != categos.getIdCategorie() }">
                                                    <option value="${cat.getIdCategorie()}">${cat.getNomCategorie()}</option>
                                                </c:if>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>
                                <div class="control-group span4">
                                    <label class="control-label"> Article</label>
                                    <div class="controls ff" id="">
                                        <select class="form-control exampleSelect span12" id="articlesCateorie" required name="designation">

                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="control-group span4">
                                    <label class="control-label"> Quantite</label>
                                    <div class="controls ">
                                        <input type="number" class="form-control span12" required name="quantite" />	
                                    </div>
                                </div>


                                <div class="control-group form-group span4">
                                    <label class="control-label">Date Livraison</label>
                                    <div class="controls ">
                                        <input type="text" class=" form-control span12 datepicker" name="date" required placeholder="date attribution">
                                    </div>
                                </div>
                                <div class="span4">
                                    <div class="control-group ">
                                        <button type="submit" class="btn btn-success btn-large span12 btn-lg" style="margin-top: 22px" >Enregistrer</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:if>
                <c:if test="${not empty updateStock}">
                    <form class="form-inline" method="post" role="form" action="AjouterStockMS?vue=createMagS&action=SaveudatestockMS&niveau=5&id_stock=${stockup.getIdStock()}" >
                        <div class="row-fluid">

                            <div class="row-fluid">
                                <div class="control-group form-group span4">
                                    <label class="control-label">Nom Magasin</label>
                                    <div class="controls ">
                                        <select class="input-lg form-control span12 magasinbyregion" name="id_magasin" required >
                                            <option value="${magasin.getIdMagasin()}">${magasin.getNomMagasin()}</option>
                                            <c:forEach items="${MagasinsS}" var="ms">
                                                <c:if test="${magasin.getIdMagasin() != ms.getIdMagasin()}">
                                                    <option value="${ms.getIdMagasin()}">${ms.getNomMagasin()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>
                                <div class="control-group span4">
                                    <label class="control-label"> Categorie</label>
                                    <div class="controls ">
                                        <select class="form-control span12" id="categorietoarticles" required name="categorie">
                                            <option value="${stockup.getCategorie().getIdCategorie()}" selected>${stockup.getCategorie().getNomCategorie()}</option>
                                            <c:forEach items="${categoriesMS}" var="cat">
                                                <c:if test="${cat.getIdCategorie() != stockup.getCategorie().getIdCategorie()}">
                                                    <option value="${cat.getIdCategorie()}">${cat.getNomCategorie()}</option>
                                                </c:if>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>
                                <div class="control-group span4">
                                    <label class="control-label"> Article</label>
                                    <div class="controls ff" id="">
                                        <select class="form-control exampleSelect span12" id="articlesCateorie" required name="designation">
                                            <option value="${designation.getIdArticle()}">${designation.getDesignation()}</option>
                                            <c:forEach items="${articles}" var="art">
                                                <c:if test="${art.getIdArticle() != designation.getIdArticle()}">
                                                    <option value="${art.getIdArticle()}">${art.getDesignation()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                            </div>

                            <div class="row-fluid">
                                <div class="control-group span4">
                                    <label class="control-label"> Quantite</label>
                                    <div class="controls ">
                                        <input type="number" class="form-control span12" value="${stockup.getQuantite()}" required name="quantite" />	
                                    </div>
                                </div>


                                <div class="control-group form-group span4">
                                    <label class="control-label">Date Livraison</label>
                                    <div class="controls ">
                                        <input type="text" class=" form-control span12 datepicker" name="date" required placeholder="date attribution">
                                    </div>
                                </div>
                                <div class="span4">
                                    <div class="control-group ">
                                        <button type="submit" class="btn btn-success btn-large span12 btn-lg" style="margin-top: 22px" >SAVE</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:if>

                <div class="space20"></div>
                <div class="row-fluid">
                    <div class="widget orange">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i> Stocks Enregistrers</h4>
                            <span class="tools">
                                <a href="javascript:;" class="icon-chevron-down"></a>
                            </span>
                        </div>
                        <div class="widget-body">
                            <div class="bs-docs-example">
                                <div class="space15"></div>
                                <div class="space15"></div>
                                <div class="row-fluid">
                                    <table class="table table-hover table-bordered simple_print"cellspacing="0" width="100%" >
                                        <thead>
                                            <tr>
                                                <th>CATEGORIE</th>
                                                <th>CODE</th>
                                                <th>DESIGNATION</th>
                                                <th>QUANTITE</th>
                                                <th>PRIX UNITAIRE</th>
                                                <th>PRIX TOTAL</th>
                                                <th>DATE LIVRAISON</th>
                                                    <c:if test="${not empty isform}">
                                                    <th>Option</th>
                                                    </c:if>
                                            </tr>
                                        </thead>
                                        <tbody id="tbody">
                                            <c:forEach items="${listeStock}"var="r">
                                                <tr class="produit">
                                                    <td scope="row"> <c:out value="${r.getCategorie().getNomCategorie()}"/> </td>
                                                    <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                                                    <td><c:out value="${r.getDesignation()}"/></td>
                                                    <td><f:formatNumber value="${r.getQuantite()}" type="NUMBER"/></td>
                                                    <td><f:formatNumber value="${r.getPrixUnitaire()}" type="CURRENCY"/></td>
                                                    <td><f:formatNumber value="${r.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                                    <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                                                    <c:if test="${not empty isform}">
                                                        <td > 
                                                            <div class="btn-group">
                                                                <a title="" href="AjouterStockMS?vue=createMagS&action=udatestockMS&niveau=5&id_stock=${r.getIdStock()}&id_magasin=${r.getCategorie().getMagasinSecondaire().getIdMagasin()}&designation=${r.getDesignation()}" class="btn btn-primary" >  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                                                <a class="btn btn-danger"  href="AjouterStockMS?vue=createMagS&action=deleteArticleformulaire&niveau=5&id_stock=${r.getIdStock()}&id_magasin=${r.getCategorie().getMagasinSecondaire().getIdMagasin()}"data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>

                                                            </div>
                                                        </td>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <hr>
                                <a href="AjouterStockMS?vue=createMagS&action=finis&niveau=5&id_magasin=1"><button type="button" class="btn-lg span4 btn-success">Fini</button></a>
                                <div class="space15"></div>
                                <div class="space15"></div>
                                <c:if test="${not empty infomessage}">
                                    <div class="alert alert-success text-center "><strong><c:out value="${infomessage}"/></strong></div>
                                        </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="pass" class="tab-pane fade <c:if test="${forme == 'NON'}">in active</c:if>">

                    <div class="row-fluid">
                        <form method="post" role="form"  id="addstockform" enctype="multipart/form-data" action="UploadFile?vue=createMagS&niveau=5&quel=MS">
                            <div class="row-fluid">
                                <div class="control-group form-group span5">
                                    <label class="control-label">Magasin</label>
                                    <div class="controls ">
                                        <select class="input-lg form-control idmagasin" id=""  name="magasin" required >
                                        <c:forEach items="${MagasinsS}" var="MS">
                                            <option value="${MS.getIdMagasin()}">${MS.getNomMagasin()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="control-group form-group span4">
                                <label class="control-label">Insere un fichier</label>
                                <div class="controls ">
                                    <input type="file" class="form-control" name="uploadFile"/>
                                </div>
                            </div> 
                        </div>
                        <div class="space15"></div>
                        <div class="space15"></div>
                        <div class="row-fluid">
                            <div class="control-group span12">
                                <button  class="btn btn-success btn-large btn-block btn-lg" id="addstockbtn" >Inserer</button>
                            </div>
                        </div>
                    </form>
                    <div class="space15"></div>
                    <div class="space15"></div>
                    <p class="text-center text-info text-capitalize"><strong>Voici Un Example Du Formatage Du Fichier Excel</strong></p>
                    <p class="text-danger" style="color: red">Rassurer Vous Que le Fichier Excel Que Vous avez charger est Sous cette forme:</p>

                    <img src="images/stoc.png" alt="Exemple de fichier Excel"/>
                </div>

            </div>


        </div>
    </div>
</div>
