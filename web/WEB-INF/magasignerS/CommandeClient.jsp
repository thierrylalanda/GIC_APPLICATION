<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="space20"></div>
<h3 class="page-title">
    Faire Une Sortie Pour Un Personnel
</h3>
<ul class="breadcrumb">
    <li>
        <a href="RedirectionVue?vue=Accueil"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">Sorti Pour Personnel</a>

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

<div class="widget black">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i>Sorti Pour Personnel</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <form class="form-horizontal search-result" method="post" action="commande_client?vue=Sortir pour consommation&action=show">
                <div class="control-group">
                    <label class="control-label">Search</label>
                    <div class="controls">
                        <input type="text" class="input-xxlarge tags" name="client" placeholder="Entrer le Nom Du Personnel Ici">
                        <p class="help-block">la recherche est Autocomplete en moin de (0.10 seconds) </p>
                    </div>
                    <button type="submit" class="btn ">SEARCH</button>
                </div>
            </form>
            <c:if test="${not empty messag}">
                <div class="alert alert-block alert-danger" style="">

                    <h4>Erreur !</h4>
                    <c:out value="Erreur: ${messag.getMessage()}"/> !

                </div>
            </c:if>
            <c:if test="${not empty unlock}">
                <div class="row-fluid responsive">    
                    <form method="post" action="commande_client?vue=Commande Interne&action=ajouter&interne=no&niveau=2" class="form-inline" id="commandeClit">

                        <div class="form-group responsive">
                            <div class="span12">
                                <div class="control-group span4">
                                    <label class="control-label"> Vous!</label>
                                    <div class="controls ">
                                        <input type="text" id="client" required name="client" value="${nom_client}" class="span12 input-sm form-control tags " placeholder="Nom du Client" <c:if test="${edit=='ok'}" ><c:out value="disabled"></c:out> </c:if>/>

                                            </div>
                                        </div>

                                        <div class="control-group span4">
                                            <label class="control-label"> Categorie</label>
                                            <div class="controls ">
                                                <select class="form-control span12" id="categorietoarticles" required name="categorie">
                                                    <option value="${categos.getIdCategorie()}" selected>${categos.getNomCategorie()}</option>
                                            <c:forEach items="${sessionScope.categoriee}" var="cc">  
                                                <option value="${cc.getIdCategorie()}">${cc.getNomCategorie()}</option>  
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>
                                <div class="control-group span4" >
                                    <label class="control-label"> Article</label>
                                    <div class="controls ff" id="">
                                        <select class="form-control exampleSelect span12" id="articlesCateorie" required name="designation"style="margin-left: 0px">

                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">

                                <div class="control-group span4">
                                    <label class="control-label"> Quantite</label>
                                    <div class="controls ">
                                        <input type="number" id="quantite" class=" form-control  span12"  placeholder="Quantite" required name="quantite" />

                                    </div>
                                </div>
                                <div class="control-group span4">
                                    <label class="control-label"> Vos Appariels</label>
                                    <div class="controls ">
                                        <select id="select" class=" form-control span12" name="appariel" placeholder="Vos Appareils" required>
                                            <c:forEach items="${personnel.getApparielList()}" var="cc">  
                                                <option >${cc.getNumeroParck()}</option>  
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>
                                <div class="control-group span4">
                                    <div class="controls ">
                                        <button type="submit" class="btn btn-success btn-lg btn-large span12 btn-primary"style="margin-top: 22px;" >Envoyer</button>

                                    </div>
                                </div>
                            </div>


                        </div>

                    </form>
                </div>

                <hr>
                <div class="row-fluid responsive">    
                    <table class="table table-hover table-responsive table-bordered simple_print" cellspacing="0" width="100%">
                        <thead class="text-primary">
                            <tr>
                                <th>CATEGORIE</th>
                                <th>CODE</th>
                                <th>DESIGNATION</th>
                                <th>QUANTITE</th>
                                <th class="hidden">QUANTITE</th>
                                <th>PRIX UNITAIRE</th>
                                <th>PRIX TOTAL</th>
                                <th>APPARIEL</th>

                            </tr>
                        </thead>
                        <tbody id="tbody">
                            <c:forEach items="${listeoperation}" var="list">  
                                <tr class="produits">

                                    <td class="categories col-lg-3" ><c:out value="${list.getCategorie()}"/></td>
                                    <td class="code"><c:out value="${list.getCodeProduit()}"/></td>
                                    <td class="designation"><c:out value="${list.getDesignation()}"/></td>
                                    <td class="qteu"><f:formatNumber value="${list.getQuantite()}" type="NUMBER"/></td>
                                    <td class="qteuc hidden"><input class="newvalcc" type='text' id='qteu'  /></td>
                                    <td class="pu"><f:formatNumber value="${list.getPrix()}" type="CURRENCY"/></td>
                                    <td class="pt"><f:formatNumber value="${list.getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                    <c:if test="${not empty list.getAppariel()}">
                                        <td class="app"><c:out value="${list.getAppariel()}"/></td>
                                    </c:if>    

                                </tr>
                            </c:forEach>

                        </tbody>
                    </table> 
                </div>
                <hr>
                <div class="row-fluid">
                    <button  class="btn btn-success btn-lg btn-large save" style="margin-right: 50px"><i class="icon-save"></i> Enregistrer</button>

                    <button  class="btn btn-danger btn-lg btn-large " id="deleteOneCMS"><i class="icon-remove-sign"></i> Supprimer</button>

                </div>

            </c:if>
            <c:if test="${not empty commandesEncour}">
                <h2><i class="icon-shopping-cart"></i> Vos Commandes Non Réceptionnés</h2>
                <div class="widget black">
                    <div class="widget-title">
                        <h4><i class="icon-reorder"></i>Vos Commandes Non Réceptionnés</h4>
                        <span class="tools">
                            <a href="javascript:;" class="icon-chevron-down"></a>

                        </span>
                    </div>
                    <div class="widget-body">
                        <div class="bs-docs-example">
                            <table class="table table-hover table-responsive table-bordered simpletable"cellspacing="0" width="100%" >
                                <thead class="text-primary">
                                    <tr>
                                        <th>DESIGNATION</th>
                                        <th>QUANTITE</th>
                                        <th>APPARIEL</th>
                                        <th>DATE</th>
                                        <th>Etat</th>
                                    </tr>
                                </thead>
                                <tbody id="tbody">
                                    <c:set var="perso" value="${0}"></c:set>
                                    <c:forEach items="${commandesEncour}" var="list">  
                                        <tr class="produits">
                                            <td class="designation"><c:out value="${list.getDesignations()}"/></td>
                                            <td class="qteu"><f:formatNumber value="${list.getQuantite()}" type="NUMBER"/></td>
                                            <td class="app"><c:out value="${list.getCodeAppareil().getNumeroParck()}"/></td>
                                            <td class="date"><f:formatDate value="${list.getDate()}" type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM"/></td>
                                            <td class="app"><c:out value="${list.getEtatCommande()}"/></td>
                                        </tr>
                                        <c:set var="perso" value="${list.getIdPersonnel().getIdPersonnel()}"></c:set>
                                    </c:forEach>

                                </tbody>
                            </table> 
                            <hr>

                            <a href="Commande_All_Client?vue=Sortir pour consommation&action=ApprobationCommandePersonnel&magasin=MS&personnel=${perso}">  <button type="button" class="btn btn-success  btn-primary btn-lg btn-large " ><i class="icon-user"></i> Oui J'ais récupéré ces Articles</button></a>

                        </div>
                        <div class="space20"></div>
                    </div>
                </div>
            </c:if>
            <hr>
            <c:if test="${not empty message.getMessage()}">
                <div class="alert alert-danger span12 text-center "><strong><c:out value="${message.getMessage()}"/></strong></div>
                    </c:if>
            <div class="space20"></div>
            <div class="space20"></div>
        </div>
    </div>
</div>

