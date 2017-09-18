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
    Mis a Jour Du Personnel
</h3>
<ul class="breadcrumb">
    <li>
        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="admin?vue=perso&action=getAll"><i class="icon-group"></i> Personnels</a>
        <span class="divider">/</span>
    </li>
    <li class="active">
        <i class="icon-edit-sign"></i><i class="icon-user"></i> Update Personnal
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

<div class="widget orange" >
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
                <c:if test="${not empty sessionScope.lien3 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <li class="active"><a data-toggle="tab" href="#home">donnees Personnel</a></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.lien4 or not empty sessionScope.lien5 or not empty sessionScope.lien6  or sessionScope.GeneralAdministrateur == 'OK'}">
                    <li><a data-toggle="tab" href="#profile">Mettre A jour</a></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.lien7 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <li><a data-toggle="tab" href="#dropdown1">Appareils</a></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.lien8 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <li><a data-toggle="tab" href="#dropdown2">Commandes En Cour</a></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.lien9 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <li><a data-toggle="tab" href="#dropdown3">Operations</a></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.lien11 or sessionScope.GeneralAdministrateur == 'OK'}">
                        <c:if test="${controle == 'yes'}">
                        <li><a data-toggle="tab" href="#dropdown4">Commande En Attente</a></li>
                        </c:if>
                    </c:if>
            </ul>
            <div class="tab-content" id="myTabContent">
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
                            <a data-toggle="tab" href="#groupe" class="profile-features ">
                                <i class=" icon-group"></i>
                                <p class="info">Groupe</p>
                            </a>

                        </div>
                        <div class="span10">
                            <div class="profile-head">
                                <div class="span8">
                                    <h1>${personnel.getNomPrenom()}</h1>
                                    <p>${personnel.getFonction()} Aux <a href="#"> ${sessionScope.societe.getNomSociete()}</a></p>
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
                                    <div id="login" class="tab-pane fade in">
                                        <div class="space15"></div>

                                        <h2>Login</h2>

                                        <p><label>User Name </label>:  ${personnel.getLoginList().getUsername()}</p>
                                        <p><label>Password </label>:  ${personnel.getLoginList().getPassword()}</p>
                                        <p><label>Accès </label>: ${personnel.getRole()}</p>

                                        <hr>
                                    </div>
                                    <div id="groupe" class="tab-pane fade in">
                                        <div class="space15"></div>

                                        <h2>Groupe</h2>

                                        <p><label>Groupe </label>:  ${personnel.getLoginList().getGroupes().getNomGroupe()}</p>
                                        <p><label>Membres </label>:  ${personnel.getLoginList().getGroupes().getLoginList().size()}</p>
                                        <p><label>Pages </label>: ${personnel.getLoginList().getGroupes().getPermissionsList().size()}/${pages.size()}</p>

                                        <hr>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div id="dropdown1" class="tab-pane fade">
                    <table class="table table-bordered table-hover table-striped display simple_print" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th>Numero Parck</th>
                                <th>type</th>
                                <th>Localisation</th>
                                <th>numero serie</th>
                                <th>model</th>
                                <th>Fabricant</th>
                                <th>Utilisateur</th>
                                <th>Options</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${personnel.getApparielList()}" var="app">
                                <c:if test="${app.getNumeroSerie() != '000000'}">
                                    <tr class="">
                                        <td class=""> ${app.getNumeroParck()}</td>
                                        <td class=""> ${app.getTypeAppareil().getNom()}</td>
                                        <td class=""> ${app.getLieu()}</td>
                                        <td class=""> ${app.getNumeroSerie()}</td>
                                        <td class=""> ${app.getModel()}</td>
                                        <td class=""> ${app.getFabricant()}</td>
                                        <td class=""> ${app.getProprietaire().getNomPrenom()}</td>
                                        <td>
                                            <div class="btn-group">
                                                <a title="" href="SettingPersonnal?vue=EditAppariel&action=selectApparielToEdite&numeroParck=${app.getNumeroParck()}" class="btn btn-primary">  <span class="icon"> <i class="icon-edit"></i> </span></a>  

                                                <a class="btn btn-danger delete"  href="SettingPersonnal?vue=Appariels&action=deletteAppariel&numeroParck=${app.getNumeroParck()}" data-toggle="modal" data-target=""> <span class="icon"> <i class="icon-trash"></i> </span></a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div id="profile" class="tab-pane fade">
                    <c:import url="updatePersonnel.jsp"/>
                </div>
                <div id="dropdown3" class="tab-pane fade">
                    <h4>Listes Des Operations</h4>
                    <table class="table table-striped table-hover table-bordered simple_print" cellspacing="0" width="100%">
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
                            <c:set var="t" value="${0}"/>
                            <c:forEach items="${personnel.getOperationConsommateurList()}" var="app">
                                <tr>
                                    <td class=""> ${app.getIdOperation()}</td>
                                    <td class=""> ${app.getCategorie()}</td>
                                    <td class=""> ${app.getCodeProduit()}</td>
                                    <td class=""> ${app.getDesignation()}</td>          
                                    <td class="qteu"><f:formatNumber value="${app.getQuantite()}" type="NUMBER"/></td>
                                    <td class="qteu"><f:formatNumber value="${app.getPrix()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                    <td class="qteu"><f:formatNumber value="${app.getPrixTotal()}" type="CURRENCY" currencySymbol="FCFA"/></td>
                                    <td><f:formatDate value="${app.getDate()}" type="Date" dateStyle="MEDIUM"/></td>
                                    <td class=""> ${app.getMagasin().getNomMagasin()}</td>                                                                        
                                    <td class=""> ${app.getOperateur()}</td>
                                    <c:set var="tt" value="${app.getPrix() * app.getQuantite()}"/>
                                    <c:set var="t" value="${t + tt}"/>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <p  style="text-align: center">Total: <f:formatNumber value="${t}"type="CURRENCY" currencySymbol="FCFA"/></p>
                </div>
                <div id="dropdown2" class="tab-pane fade">
                    <h4>Commandes En Cours De Traitements</h4>
                    <table class="table table-bordered table-hover table-striped simple_print"cellspacing="0" width="100%">
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
                                <c:if test="${app.getEtatCommande() != 'traiter'}">
                                    <tr>
                                        <td class=""> ${app.getIdCommande()}</td>
                                        <td class=""> ${app.getDesignations()}</td>
                                        <td class=""> ${app.getCodeAppareil().getNumeroParck()}</td>
                                        <td class=""> ${app.getQuantite()}</td>
                                        <td class="pt"><f:formatDate value="${app.getDate()}"  type="Date" dateStyle="MEDIUM" /></td>
                                        <td class=""> ${app.getIdMS().getNomMagasin()}</td>
                                        <td class=""> ${app.getEtatCommande()}</td>

                                    </tr>
                                </c:if>
                            </c:forEach>


                        </tbody>
                    </table>
                </div>
                <div id="dropdown4" class="tab-pane fade">

                    <c:import url="controlCommande.jsp"/>
                </div>


            </div>
        </div>
    </div>
</div>



