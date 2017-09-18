<%-- 
    Document   : editePersonnel
    Created on : 25 mars 2017, 00:56:15
    Author     : lalanda
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="page-title">
    Votre Profil
</h3>
<ul class="breadcrumb">
    <li>
        <a href="RedirectionVue?vue=Accueil"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">Profil</a>

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
<div class="row-fluid">
    <!-- BEGIN INLINE TABS PORTLET-->
    <div class="widget black">
        <div class="widget-title">
            <h4><i class="icon-reorder"></i> Details Personnel</h4>
            <span class="tools">
                <a href="javascript:;" class="icon-chevron-down"></a>
                <a href="javascript:;" class="icon-remove"></a>
            </span>               
        </div>
        <div class="widget-body">
            <div class="bs-docs-example">
                <ul class="nav nav-tabs" id="myTab">
                    <c:if test="${ not empty sessionScope.InfoMS}">
                        <li class="active"><a data-toggle="tab" href="#home">Vos Donnees</a></li>
                        </c:if>
                        <c:if test="${not empty sessionScope.ChangePasseMS}">
                        <li><a data-toggle="tab" href="#profile">Changer Votre Mot De Passe</a></li>
                        </c:if>
                        <c:if test="${ not empty sessionScope.ApparielMS}">
                        <li><a data-toggle="tab" href="#dropdown1">Vos Appareils</a></li>
                        </c:if>
                        <c:if test="${not empty sessionScope.CommandeCourMS}">
                        <li><a data-toggle="tab" href="#dropdown7"> Vos Commandes En Cour</a></li>
                        </c:if>
                        <c:if test="${not empty sessionScope.OperationMS}">
                        <li><a data-toggle="tab" href="#dropdown3">Vos Operations</a></li>     
                        </c:if>                       
                </ul>
                <div class="tab-content row-fluid" id="myTabContent">
                    <div id="home" class="tab-pane fade in active">
                        <div class=" profile span12">
                            <div class="span2">
                                <a data-toggle="tab" href="#p1" class="profile-features active">
                                    <i class=" icon-user"></i>
                                    <p class="info">Profil</p>
                                </a>
                                <c:if test="${not empty sessionScope.InfoMS}">
                                    <a data-toggle="tab" href="#login" class="profile-features ">
                                        <i class=" icon-lock"></i>
                                        <p class="info">Login</p>
                                    </a>
                                </c:if>
                            </div>
                            <div class="span10">
                                <div class="profile-head">
                                    <div class="span8">
                                        <h1>${personnell.getNomPrenom()}</h1>
                                        <p>${personnell.getFonction()} Aux <a href="#">${sessionScope.societes.getNomSociete()}</a></p>
                                    </div>

                                    <div class="span4">
                                        <ul class="social-link-pf">
                                            <li><a href="#">
                                                    <i class="icon-facebook"></i>
                                                </a></li>
                                            <li><a href="#">
                                                    <i class="icon-twitter"></i>
                                                </a></li>
                                            <li><a href="#">
                                                    <i class="icon-linkedin"></i>
                                                </a></li>
                                        </ul>
                                    </div>
                                </div>

                                <c:if test="${ not empty sessionScope.InfoMS}">
                                    <div class="row-fluid">
                                        <div class="span8 bio tab-content">

                                            <div id="p1" class="tab-pane fade in active">
                                                <div class="space15"></div>

                                                <h2>Bio Graph</h2>

                                                <p><label>Matricule </label>:  ${personnell.getMatricule()}</p>
                                                <p><label>Grade </label>: ${personnell.getFonction()}</p>
                                                <p><label>Telephone </label>: ${personnell.getTelephone()}</p>
                                                <p><label>Email</label>: ${personnell.getEmail()}</p>
                                                <p><label>Region </label>: ${personnell.getService().getSite().getRegion().getNomRegion()}</p>
                                                <p><label>Site </label>: ${personnell.getService().getSite().getNomSite()}</p>
                                                <p><label>Service </label>: ${personnell.getService().getNomService()}</p>
                                                <hr>
                                            </div>
                                            <div id="login" class="tab-pane fade ">
                                                <div class="space15"></div>

                                                <h2>Login</h2>

                                                <p><label>User Name </label>:  ${personnell.getLoginList().getUsername()}</p>
                                                <p><label>Password </label>:  ${personnell.getLoginList().getPassword()}</p>
                                                <p><label>Accès </label>: ${personnell.getRole()}</p>
                                                <hr>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <div id="dropdown1" class="tab-pane fade">
                        <c:import url="updateAppariel.jsp"/>
                    </div>

                    <div id="profile" class="tab-pane fade">
                        <c:import url="updatePersonnel.jsp"/>
                    </div>
                    <div id="dropdown3" class="tab-pane fade">
                        <h4>Listes Des Operations</h4>
                        <div class="row-fluid">
                            <table class="table table-striped table-hover table-bordered simpletable"cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>Numero </th>
                                        <th>Categorie</th>
                                        <th>Code</th>
                                        <th>Designation</th>                                                                  
                                        <th>Quantite</th>
                                        <th>PU</th>
                                        <th>PT</th>
                                        <th>Date</th>
                                        <th>Magasin</th>
                                        <th>Operateur</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach items="${personnell.getOperationConsommateurList()}" var="app">
                                        <tr>
                                            <td class=""> ${app.getIdOperation()}</td>
                                            <td class=""> ${app.getCategorie()}</td>
                                            <td class=""> ${app.getCodeProduit()}</td>
                                            <td class=""> ${app.getDesignation()}</td>                                                                      
                                            <td class=""> ${app.getQuantite()}</td>
                                            <td class=""> ${app.getPrix()}</td>
                                            <td class=""> ${app.getPrixTotal()}</td>
                                            <td class=""> ${app.getDate()}</td>
                                            <td class=""> ${app.getMagasin().getNomMagasin()}</td>                                                                        
                                            <td class=""> ${app.getOperateur()}</td>
                                        </tr>
                                    </c:forEach>


                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div id="dropdown7" class="tab-pane fade">

                        <h4>Commandes En Cours De Traitements</h4>
                        <table class="table table-bordered table-hover table-striped simpletable" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>Numero </th>
                                    <th>Designation</th>
                                    <th>Code</th>
                                    <th>Quantite</th>
                                    <th>Date</th>
                                    <th>Magasin</th>
                                    <th>Etat Commande</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty commandesEncour}">
                                    <c:forEach items="${personnell.getCommandePersonnelList()}" var="app">
                                        <c:if test="${app.getEtatCommande() != 'traiter'}">
                                            <tr>
                                                <td class=""> ${app.getIdCommande()}</td>
                                                <td class=""> ${app.getDesignations()}</td>
                                                <td class=""> ${app.getCodeAppareil().getNumeroParck()}</td>
                                                <td class=""> ${app.getQuantite()}</td>
                                                <td class=""> ${app.getDate()}</td>
                                                <td class=""> ${app.getIdMS().getNomMagasin()}</td>
                                                <td class=""> ${app.getEtatCommande()}</td>

                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:if>

                                <c:if test="${!commandesEncour.isEmpty()}">
                                    <c:forEach items="${commandesEncour}" var="app">
                                        <tr>
                                            <td class=""> ${app.getIdCommande()}</td>
                                            <td class=""> ${app.getDesignations()}</td>
                                            <td class=""> ${app.getCodeAppareil().getNumeroParck()}</td>
                                            <td class=""> ${app.getQuantite()}</td>
                                            <td class=""> ${app.getDate()}</td>
                                            <td class=""> ${app.getIdMS().getNomMagasin()}</td>
                                            <td class=""> ${app.getEtatCommande()}</td>

                                        </tr>
                                    </c:forEach>
                                </c:if>

                            </tbody>
                        </table>
                        <hr>
                        <c:if test="${!commandesEncour.isEmpty()}">
                            <a href="Commande_All_Client?vue=profile&action=ApprobationCommandePersonnel&magasin=MS&personnel=${personnell.getIdPersonnel()}">  <button type="button" class="btn btn-success  btn-primary btn-lg btn-large " ><i class="icon-user"></i> Oui J'ais récupéré ces Articles</button></a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

