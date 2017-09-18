<%-- 
    Document   : editePersonnel
    Created on : 25 mars 2017, 00:56:15
    Author     : lalanda
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="row-fluid">
    <!-- BEGIN INLINE TABS PORTLET-->
    <div class="widget orange">
        <div class="widget-title">
            <h4><i class="icon-reorder"></i> Details Personnels</h4>
            <span class="tools">
                <a href="javascript:;" class="icon-chevron-down"></a>
                <a href="javascript:;" class="icon-remove"></a>
            </span>               
        </div>
        <div class="widget-body">
            <div class="bs-docs-example">
                <ul class="nav nav-tabs" id="myTab">
                    <li class="active"><a data-toggle="tab" href="#home">Vos Donnees</a></li>
                    <li><a data-toggle="tab" href="#profile">Mettre A jour</a></li>
                    <li><a data-toggle="tab" href="#dropdown1">Vos Appareils</a></li>
                    <li><a data-toggle="tab" href="#dropdown7"> Vos Commandes En Cour</a></li>
                    <li><a data-toggle="tab" href="#dropdown3">Vos Operations</a></li>                            
                </ul>
                <div class="tab-content row-fluid" id="myTabContent">
                    <div id="home" class="tab-pane fade in active">
                        <div class=" profile span12">
                            <div class="span2">
                                <a data-toggle="tab" href="#p1" class="profile-features active">
                                    <i class=" icon-user"></i>
                                    <p class="info">Profile</p>
                                </a>
                                <a data-toggle="tab" href="#login" class="profile-features ">
                                    <i class=" icon-lock"></i>
                                    <p class="info">Login</p>
                                </a>
                            </div>
                            <div class="span10">
                                <div class="profile-head">
                                    <div class="span4">
                                        <h1>${personnel.getNomPrenom()}</h1>
                                        <p>${personnel.getFonction()} Aux <a href="#">Brasseries Du Cameroun</a></p>
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
                                <div class="row-fluid">
                                    <div class="span8 bio tab-content">

                                        <div id="p1" class="tab-pane fade in active">
                                            <div class="space15"></div>

                                            <h2>Bio Graph</h2>

                                            <p><label>Matricule </label>:  ${personnel.getMatricule()}</p>
                                            <p><label>Grade </label>: ${personnel.getFonction()}</p>
                                            <p><label>Telephone </label>: ${personnel.getTelephone()}</p>
                                            <p><label>Email</label>: ${personnel.getEmail()}</p>
                                            <p><label>Region </label>: ${personnel.getService().getSite().getRegion().getNomRegion()}</p>
                                            <p><label>Site </label>: ${personnel.getService().getSite().getNomSite()}</p>
                                            <p><label>Service </label>: ${personnel.getService().getNomService()}</p>
                                            <hr>
                                        </div>
                                        <div id="login" class="tab-pane fade ">
                                            <div class="space15"></div>

                                            <h2>Login</h2>

                                            <p><label>User Name </label>:  ${personnel.getLoginList().getUsername()}</p>
                                            <p><label>Password </label>:  ${personnel.getLoginList().getPassword()}</p>
                                            <p><label>Accès </label>: ${personnel.getRole()}</p>
                                            <hr>
                                        </div>

                                    </div>

                                </div>
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
                            <table class="table table-striped table-hover table-bordered simpletable">
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

                                    <c:forEach items="${personnel.getOperationConsommateurList()}" var="app">
                                        <tr>
                                            <td class=""> ${app.getIdOperation()}</td>
                                            <td class=""> ${app.getCategorie()}</td>
                                            <td class=""> ${app.getCodeProduit()}</td>
                                            <td class=""> ${app.getDesignation()}</td>                                                                      
                                            <td class=""> ${app.getQuantite()}</td>
                                            <td class=""><f:formatNumber value="${app.getPrix()}" type="CURRENCY" currencySymbol="FCFA"/></td> 
                                            <td class=""> <f:formatNumber value="${app.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                            <td class=""><f:formatDate value="${app.getDate()}" type="Date" dateStyle="MEDIUM"/></td>
                                            <td class=""> ${app.getMagasin().getNomMagasin()}</td>                                                                        
                                            <td class=""> ${app.getOperateur()}</td>
                                        </tr>
                                    </c:forEach>


                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div id="dropdown7" class="tab-pane fade">
                        <div class="row-fluid">
                            <h4>Commandes En Cours De Traitements</h4>
                            <table class="table table-bordered table-hover table-striped " >
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
                                    <c:forEach items="${personnel.getCommandePersonnelList()}" var="app">
                                        <tr>
                                            <td> ${app.getIdCommande()}</td>
                                            <td> ${app.getDesignations()}</td>
                                            <td> ${app.getCodeAppareil().getNumeroParck()}</td>
                                            <td> ${app.getQuantite()}</td>
                                            <td> <f:formatDate value="${app.getDate()}" type="BOTH" /></td>
                                            <td> ${app.getIdMS().getNomMagasin()}</td>
                                            <td> ${app.getEtatCommande()}</td>
                                        </tr>
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

