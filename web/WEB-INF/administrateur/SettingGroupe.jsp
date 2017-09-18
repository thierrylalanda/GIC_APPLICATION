
<%-- 
    Document   : controlleur
    Created on : 18 avr. 2017, 16:50:59
    Author     : lalanda
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="page-title">
    Espace Gestion Des Droits D'acces
</h3>
<ul class="breadcrumb">
    <li>

        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#"><i class="icon-group"></i> Groupes</a>

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

<div class="widget orange">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i>Gestion Des Droits D'acces</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <ul class="nav nav-tabs" id="myTab">
                <li class="active"><a href="#all-direction" data-toggle="tab">Groupes</a></li>

                <li><a href="#add-direction" data-toggle="tab">Creer Un Groupe</a> </li>

            </ul>
            <div class="tab-content" id="myTabContent">
                <div id="all-direction" class="tab-pane fade in active">
                    <h4>Tous les Groupes</h4>
                    <table class="table table-bordered table-hover table-striped simple_print"  cellspacing="0" width="100%">
                        <thead>
                            <tr >
                                <th>Nom Groupe</th>
                                <th>Type Groupe</th>
                                <th>Region</th>
                                <th>Total Pages</th>
                                <th>Total Membres</th>
                                <th>Options</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}" >
                                <c:forEach items="${groupes}" var="groupe">
                                    <c:if test="${not empty groupe.getRegion().getNomRegion()}" >
                                        <tr class="produit">

                                            <td >${groupe.getNomGroupe()}</td>
                                            <td >
                                                <c:if test="${groupe.getNiveau()==1}" >
                                                    Personnel
                                                </c:if>
                                                <c:if test="${groupe.getNiveau()==2}" >
                                                    Magasignier Secondaire
                                                </c:if>
                                                <c:if test="${groupe.getNiveau()==3}" >
                                                    Magasignier Principal
                                                </c:if>
                                                <c:if test="${groupe.getNiveau()==4}" >
                                                    Controlleur
                                                </c:if>
                                                <c:if test="${groupe.getNiveau()==5}" >
                                                    Administrateur
                                                </c:if>

                                            </td>
                                            <c:if test="${not empty groupe.getRegion().getNomRegion()}" >
                                                <td>${groupe.getRegion().getNomRegion()}</td>
                                            </c:if>
                                            <c:if test="${empty groupe.getRegion().getNomRegion()}" >
                                                <td>Aucune</td>
                                            </c:if>

                                            <td>${groupe.getPermissionsList().size()}</td>
                                            <td>${groupe.getLoginList().size()}</td>
                                            <td >
                                                <div class="btn-group">
                                                    <c:if test="${not empty groupe.getRegion() or sessionScope.GeneralAdministrateur == 'OK'}">
                                                        <a title="" href="personnel?action=editgroupe&vue=settingeditgroupe&idgroupe=${groupe.getIdGroupes()}" class="btn btn-success" >  <span class="icon"> <i class="icon-pencil"></i> </span></a>  
                                                    </c:if>
                                                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                                        <c:if test="${not empty groupe.getRegion().getNomRegion()}">
                                                            <a class="btn btn-danger" href="personnel?action=deleteGroupe&vue=settinggroupe&idgroupe=${groupe.getIdGroupes()}" data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>
                                                        </c:if>
                                                    </c:if>

                                                </div>
                                            </td>

                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien56}" >
                                <c:forEach items="${groupes}" var="groupe">
                                    <c:if test="${groupe.getRegion().getNomRegion() == sessionScope.adminRegion}" >
                                        <tr class="produit">

                                            <td >${groupe.getNomGroupe()}</td>
                                            <td >
                                                <c:if test="${groupe.getNiveau()==1}" >
                                                    Personnel
                                                </c:if>
                                                <c:if test="${groupe.getNiveau()==2}" >
                                                    Magasignier Secondaire
                                                </c:if>
                                                <c:if test="${groupe.getNiveau()==3}" >
                                                    Magasignier Principal
                                                </c:if>
                                                <c:if test="${groupe.getNiveau()==4}" >
                                                    Controlleur
                                                </c:if>
                                                <c:if test="${groupe.getNiveau()==5}" >
                                                    Administrateur
                                                </c:if>

                                            </td>
                                            <c:if test="${not empty groupe.getRegion().getNomRegion()}" >
                                                <td>${groupe.getRegion().getNomRegion()}</td>
                                            </c:if>
                                            <c:if test="${empty groupe.getRegion().getNomRegion()}" >
                                                <td>Aucune</td>
                                            </c:if>

                                            <td>${groupe.getPermissionsList().size()}</td>
                                            <td>${groupe.getLoginList().size()}</td>
                                            <td >
                                                <div class="btn-group">
                                                    <c:if test="${not empty groupe.getRegion().getNomRegion() or sessionScope.GeneralAdministrateur == 'OK'}">
                                                        <a title="" href="personnel?action=editgroupe&vue=settingeditgroupe&idgroupe=${groupe.getIdGroupes()}" class="btn btn-success" >  <span class="icon"> <i class="icon-pencil"></i> </span></a>  
                                                    </c:if>
                                                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                                        <c:if test="${not empty groupe.getRegion().getNomRegion()}">
                                                            <a class="btn btn-danger delete-insert"   href="#" name="personnel?action=deleteGroupe&vue=settinggroupe&idgroupe=${groupe.getIdGroupes()}" data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-remove-circle"></i> </span></a>
                                                        </c:if>
                                                    </c:if>

                                                </div>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
                <div id="add-direction" class="tab-pane fade">
                    <h3 class="text-info">nouveau Groupe</h3>
                    <form action="personnel?action=addGroupe&vue=settinggroupe"  method="POST" >
                        <div class="row-fluid">
                            <div class="control-group form-group span4">
                                <label class="control-label">Region</label>
                                <div class="controls ">
                                    <select name="region" class="form-control span12">
                                        <option></option>
                                        <c:if test="${empty sessionScope.GeneralAdministrateur}">
                                            <c:forEach items="${regions}" var="region">
                                                <c:if test="${region.getNomRegion() == sessionScope.adminRegion}" >
                                                    <option value="${region.getIdRegion()}" class="">${region.getNomRegion()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                            <c:forEach items="${regions}" var="region">
                                                <option value="${region.getIdRegion()}" class="">${region.getNomRegion()}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div class="control-group form-group span4">
                                <label class="control-label">Type de Groupe</label>
                                <div class="controls ">
                                    <select name="typeGroupe" class="form-control span12"  id="getallpagesgroupe">
                                        <option></option>
                                        <option value="3" class="">magasinier principal</option>
                                        <option value="2" class="">magasinier secondaire</option>
                                        <option value="4" class="">Controleurs</option>
                                        <option value="1" class="">Personnels</option>
                                        <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                            <option value="5" class="">Administrateur Regional</option>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div class="control-group form-group span4">
                                <label class="control-label">Nom Du groupe</label>
                                <div class="controls ">
                                    <input type="text" class="form-control span12"required name="nomGroupe" placeholder="Nom du Groupe"/>

                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="control-group span12 form-group">
                                <input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwUKMTk5MjI0ODUwOWRkJySmk0TGHOhSY+d9BU9NHeCKW6o=" />

                                <table style="width: 100%;" class=""cellspacing="0" width="100%">
                                    <tr>
                                        <td style="width: 35%" class="box">

                                            <div class="d-sel-filter">
                                                <span>Recherche:</span>
                                                <input type="text" id="box1Filter" />
                                                <button type="button" class="btn" id="box1Clear">X</button>
                                            </div>

                                            <select id="box1View" multiple="multiple" name="pages" style="height:300px;width:100%">

                                            </select><br/>

                                            <span id="box1Counter" class="countLabel"></span>

                                            <select id="box1Storage">

                                            </select>
                                        </td>
                                        <td style="width: 30%; vertical-align: middle;text-align: center">
                                            <button id="to2" class="btn" type="button">&nbsp;>&nbsp;</button>

                                            <button id="allTo2" class="btn" type="button">&nbsp;>>&nbsp;</button>

                                            <button id="allTo1" class="btn" type="button">&nbsp;<<&nbsp;</button>

                                            <button id="to1" class="btn" type="button">&nbsp;<&nbsp;</button>
                                        </td>

                                        <td style="width: 35%" class="box">

                                            <div class="d-sel-filter">
                                                <span>Recherche:</span>
                                                <input type="text" id="box2Filter" />
                                                <button type="button" class="btn" id="box2Clear">X</button>
                                            </div>

                                            <select id="box2View" multiple="multiple" name="pagesGroupe" style="height:300px;width:100%;">

                                            </select><br/>

                                            <span id="box2Counter" class="countLabel"></span>

                                            <select id="box2Storage">

                                            </select>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>

                        <hr>           
                        <div class="space20"></div>
                        <div class="text-center  span12" style="margin-left: 300px">
                            <button type="submit" class="btn btn-large btn-success span6">Enregistre</button>
                        </div>
                    </form>

                </div>

                <div class="space20"></div>

            </div>
        </div>
    </div>
</div>
