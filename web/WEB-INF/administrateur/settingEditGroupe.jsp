<%-- 
    Document   : settingEditGroupe
    Created on : 15 mai 2017, 15:06:07
    Author     : lalanda
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="page-title">
    Mise A Jour DU Groupe
</h3>
<ul class="breadcrumb">
    <li>
        <a href="#"><i class="icon-home">Home</i></a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="personnel?vue=settinggroupe&action=allgroupe">Groupes</a>
        <span class="divider">/</span>
    </li>
    <li class="active">
        Mise A jour
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


<div class="widget blue-box">
    <div class="widget-title">
        Details et Mise a jour du groupe
    </div>
    <div class="widget-body">
        <ul class="nav nav-pills">
            <li class="active"><a href="#home-pills" data-toggle="tab">Details du groupe</a>
            </li>
            <c:if test="${not empty sessionScope.lien57 or sessionScope.GeneralAdministrateur == 'OK'}">
                <li><a href="#profile-pills" data-toggle="tab">Mise a jour du groupe</a></li>
                </c:if>
        </ul>

        <div class="tab-content">
            <div class="tab-pane fade in active" id="home-pills">
                <h4>Details du groupe</h4>
                <div class="row-fluid">
                    <table class="table table-bordered table-hover table-striped ">
                        <thead>
                            <tr >
                                <th>Nom Groupe</th>
                                <th>Type</th>
                                <th>Pages du groupe</th>
                                <th>Membres</th>
                            </tr>
                        </thead>
                        <tbody>
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
                                <td id="all-pages">

                                    <select class="input-lg" id="articlesCateories">
                                        <c:forEach items="${groupe.getPermissionsList()}" var="page">
                                            <option>${page.getPage().getNomPage()}</option>

                                        </c:forEach>

                                    </select>
                                </td>
                                <td id="all-members"><a href="#" class="btn btn-default">${groupe.getLoginList().size()}</a></td>

                            </tr>

                        </tbody>
                    </table>
                    <div class="space20"></div>
                    <div class="row-fluid" id="" >
                        <div class="span12">
                            <table class="table table-bordered table-hover table-striped simple_print"cellspacing="0" width="100%">
                                <thead>
                                    <tr >

                                        <th>Nom Prenom</th>
                                        <th>Matricule</th>
                                        <th>Fonction</th>
                                        <th>telephone</th>
                                        <th>E-mail</th>
                                        <th>Service</th>
                                        <th>role</th>
                                        <th>options</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${groupe.getLoginList()}" var="pers">

                                        <tr class="produit">

                                            <td > <c:out value="${pers.getPersonnel().getNomPrenom()}"/> </td>
                                            <td class="code"><c:out value="${pers.getPersonnel().getMatricule()}"/></td>
                                            <td><c:out value="${pers.getPersonnel().getFonction()}"/></td>
                                            <td > <c:out value="${pers.getPersonnel().getTelephone()}"/> </td>
                                            <td > <c:out value="${pers.getPersonnel().getEmail()}"/> </td>
                                            <td > <c:out value="${pers.getPersonnel().getService().getNomService()}"/> </td>
                                            <td > <c:out value="${pers.getPersonnel().getRole()}"/> </td>
                                            <td >
                                                <div class="btn-group">
                                                    <a title="" href="SettingPersonnal?action=editpersonnel&vue=editePersonnel&id=${pers.getPersonnel().getIdPersonnel()}&controleur=control" class="btn btn-primary">  <span class="icon"> <i class="icon-edit"></i> </span></a>  

                                                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                                        <a class="btn btn-danger delete" href="#" name="SettingPersonnal?action=DelettePersonnel&vue=perso&personnel=${pers.getPersonnel().getIdPersonnel()}" data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>
                                                    </c:if>
                                                </div>
                                            </td>
                                        </tr>


                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade" id="profile-pills">
                <h4>Mettre a jour le groupe</h4>
                <div class="space20"></div>
                <div class="row-fluid">
                    <form method="POST" action="personnel?action=misajourGroupe&idgroupe=${groupe.getIdGroupes()}&vue=settinggroupe">
                        <div class="row-fluid">
                            <div class="control-group form-group span4">
                                <label class="control-label">Type de Groupe</label>
                                <div class="controls ">

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
                                    <c:if test="${groupe.getNiveau()==5 and sessionScope.GeneralAdministrateur == 'OK'}" >
                                        Administrateur Regional
                                    </c:if>
                                </div>
                            </div>
                            <div class="control-group form-group span4">
                                <label class="control-label">Nom Du groupe</label>
                                <div class="controls ">
                                    <input type="text" class="span12 form-control"disabled name="groupe" placeholder="nom site" value="${groupe.getNomGroupe()}"/>

                                </div>
                            </div>

                        </div>
                        <div class="row-fluid">
                            <div class="control-group span12 form-group">
                                <input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwUKMTk5MjI0ODUwOWRkJySmk0TGHOhSY+d9BU9NHeCKW6o=" />

                                <table style="width: 100%;" class="">
                                    <tr>
                                        <td style="width: 35%">

                                            <div class="d-sel-filter">
                                                <h4><strong> pages non permises</strong></h4>
                                                <span>Recherche:</span>
                                                <input type="text" id="box1Filter" />
                                                <button type="button" class="btn" id="box1Clear">X</button>
                                            </div>

                                            <select id="box1View" multiple="multiple" name="pages" style="height:300px;width:100%">
                                                <c:forEach items="${allniveau}" var="page">
                                                    <option value="${page.getIdPage()}">${page.getNomPage()}</option>

                                                </c:forEach>
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
                                        <td style="width: 35%">

                                            <div class="d-sel-filter">
                                                <h4><strong>toutes les pages permises du groupe</strong></h4>
                                                <span>Recherche:</span>
                                                <input type="text" id="box2Filter" />
                                                <button type="button" class="btn" id="box2Clear">X</button>
                                            </div>

                                            <select id="box2View" multiple="multiple" name="pagesGroupe" style="height:300px;width:100%;">
                                                <c:forEach items="${pagegroupe}" var="pages">
                                                    <option value="${pages.getPage().getIdPage()}">${pages.getPage().getNomPage()}</option>

                                                </c:forEach>
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
                            <button type="submit" class="btn btn-large btn-success span6">SAVE</button>
                        </div>
                    </form>
                </div>

                <div class="space20"></div>
                <div class="space20"></div>
            </div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
            <div class="space20"></div>
        </div>
    </div>
</div>



