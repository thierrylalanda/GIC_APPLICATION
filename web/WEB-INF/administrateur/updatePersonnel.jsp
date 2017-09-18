<%-- 
    Document   : addPersonnel
    Created on : 25 mars 2017, 02:07:02
    Author     : lalanda
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="widget-body">
    <div class="bs-docs-example">
        <ul class="nav nav-tabs" id="myTab">
            <c:if test="${not empty sessionScope.lien4 or sessionScope.GeneralAdministrateur == 'OK'}">
                <li class="active"><a data-toggle="tab" href="#h1">Data Personnal</a></li>
                </c:if>
                <c:if test="${not empty sessionScope.lien5 or sessionScope.GeneralAdministrateur == 'OK'}">
                <li><a data-toggle="tab" href="#pass">Change Password</a></li>
                </c:if>
                <c:if test="${not empty sessionScope.lien6 or sessionScope.GeneralAdministrateur == 'OK'}">
                <li><a data-toggle="tab" href="#access">Change Groupe</a></li>
                </c:if>
                <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                <li><a data-toggle="tab" href="#newPass">Nouveau Login</a></li>
                </c:if>
                <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                <li><a data-toggle="tab" href="#userName">Change User Name</a></li>
                </c:if>
                <c:if test="${empty defautAppareil}">
                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                    <li><a data-toggle="tab" href="#appareil">Appareil Par Defaut</a></li>
                    </c:if>
                </c:if>
        </ul>
        <div class="tab-content" >
            <div id="h1" class="tab-pane fade in active">

                <div class="row-fluid">

                    <h2>Modifier les Donnees De: ${personnel.getNomPrenom()}</h2>

                    <div class="widget ">
                        <div class="widget-title" >
                            <h4><i class="icon-reorder"></i> Change Data for this User: ${personnel.getNomPrenom()}</h4>
                        </div>
                        <div class="widget-body ">
                            <div class="space15"></div>
                            <form class="form-horizontal" method="POST" role="form" action="SettingPersonnal?vue=editePersonnel&action=updatedatapersonnel&idPersonne=${personnel.getIdPersonnel()}">
                                <div class="row-fluid">
                                    <div class="control-group span6">
                                        <label class="control-label">Region</label>
                                        <div class="controls ">
                                            <select name="region" id="region_service" class="input-lg" >
                                                <option value="${personnel.getService().getSite().getRegion().getIdRegion()}" class="affectation">${personnel.getService().getSite().getRegion().getNomRegion()}</option>
                                                <c:forEach items="${regions}" var="region">
                                                    <c:if  test="${personnel.getService().getSite().getRegion().getIdRegion() != region.getIdRegion()}">
                                                        <option value="${region.getIdRegion()}" class="affectation">${region.getNomRegion()}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>	

                                        </div>
                                    </div>
                                    <div class="control-group span6">
                                        <label class="control-label" for="direction">Diretion</label>
                                        <div class="controls ">
                                            <select name="direction" id="" disabled class="form-control input-lg "  >
                                                <option>${personnel.getService().getDirection().getNomDirection()}</option>
                                            </select>	

                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="control-group span6">
                                        <label class="control-label">Nom</label>
                                        <div class="controls ">
                                            <input type="text" class=" input-lg form-control" name="nom" value="${personnel.getNomPrenom()}" placeholder="Votre Nom">

                                        </div>
                                    </div>
                                    <div class="control-group span6">
                                        <label class="control-label"> Email</label>
                                        <div class="controls ">
                                            <input type="email" class=" input-lg form-control" name="email" value="${personnel.getEmail()}" id="email" placeholder="Email">

                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="control-group span6">
                                        <label class="control-label" for="site">Site</label>
                                        <div class="controls ">
                                            <select name="site" id="site_service" class="form-control input-lg" >
                                                <option>${personnel.getService().getSite().getNomSite()}</option>
                                            </select>	

                                        </div>
                                    </div>


                                    <div class="control-group span6">
                                        <label class="control-label"> Service</label>
                                        <div class="controls ">
                                            <select name="service" id="service" class="form-control input-lg">
                                                <option value="${personnel.getService().getIdService()}">${personnel.getService().getNomService()}</option>
                                            </select>	
                                        </div>

                                    </div>    
                                </div>
                                <div class="row-fluid">
                                    <div class="control-group form-group span6">
                                        <label class="control-label">Telephone</label>
                                        <div class="controls ">
                                            <input type="text" class=" input-lg form-control" name="phone" value="${personnel.getTelephone()}" placeholder="Telephone">
                                        </div>
                                    </div>

                                    <div class="control-group span6">
                                        <label class="control-label"> Matricule</label>
                                        <div class="controls ">
                                            <input type="text" class=" input-lg form-control" value="${personnel.getMatricule()}" name="matricule" placeholder="Matricule">
                                        </div>
                                    </div>
                                </div>

                                <div class="row-fluid">
                                    <div class="control-group span6">
                                        <label class="control-label">Fonction</label>
                                        <div class="controls ">
                                            <input type="text" class=" input-lg form-control" name="fonction" value="${personnel.getFonction()}" placeholder="fontion">

                                        </div>
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button type="submit" class="btn btn-success btn-large btn-lg">Save Change</button>
                                    <button type="reset"  class="btn btn-large btn-lg btn-danger">Cancel</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>

            <div id="pass" class="tab-pane fade in ">
                <div class="row-fluid">

                    <h2>Change Password For: ${personnel.getNomPrenom()}</h2>

                    <div class="widget black">
                        <div class="widget-title" style="background-color: #23527c">
                            <h4><i class="icon-reorder"></i> Change Password for this User: ${personnel.getNomPrenom()}</h4>

                        </div>
                        <div class="widget-body ">
                            <div class="space15"></div>
                            <form class="form-horizontal" method="POST" action="SettingPersonnal?vue=editePersonnel&action=updateLoginpersonnel&idPersonne=${personnel.getIdPersonnel()}&niveau=5">
                                <div class="control-group">
                                    <label class="control-label">Current Password</label>
                                    <div class="controls">
                                        <input type="password" name="password1" required class="span6 ">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">New Password</label>
                                    <div class="controls">
                                        <input type="password" required class="span6 "name="password">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Re-type New Password</label>
                                    <div class="controls">
                                        <input type="password" required class="span6 " name="password2">
                                    </div>
                                </div>

                                <div class="form-actions">
                                    <button type="submit" class="btn btn-success btn-large btn-lg">Change Password</button>
                                    <button type="reset"  class="btn btn-large btn-lg btn-danger">Cancel</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div id="appareil" class="tab-pane fade in ">
                <div class="row-fluid">

                    <h2>Créé Un Appariel Pour: ${personnel.getNomPrenom()}</h2>

                    <div class="widget black">
                        <div class="widget-title" style="background-color: #23527c">
                            <h4><i class="icon-reorder"></i>Appareil Par Defaut: ${personnel.getNomPrenom()}</h4>

                        </div>
                        <div class="widget-body ">
                            <div class="space15"></div>
                            <form class="form-horizontal" method="POST" action="SettingPersonnal?vue=editePersonnel&action=AddAppareilDefaut&idPersonne=${personnel.getIdPersonnel()}&niveau=5">

                                <button type="submit" class="btn btn-success btn-large btn-lg center span6">Cliquer Ici</button>
                            </form>
                            <div class="space20"></div>
                            <div class="space20"></div>

                        </div>
                    </div>

                </div>
            </div>

            <div id="userName" class="tab-pane fade in ">
                <div class="row-fluid">

                    <h2>Change User Name For: ${personnel.getNomPrenom()}</h2>

                    <div class="widget black">
                        <div class="widget-title" style="background-color: #23527c">
                            <h4><i class="icon-reorder"></i> Change User-Name for this User: ${personnel.getNomPrenom()}</h4>

                        </div>
                        <div class="widget-body ">
                            <div class="space15"></div>
                            <form class="form-horizontal" method="POST" action="SettingPersonnal?vue=editePersonnel&action=updateUserNamepersonnel&idPersonne=${personnel.getIdPersonnel()}&niveau=5">
                                <div class="control-group">
                                    <label class="control-label">New User Name</label>
                                    <div class="controls">
                                        <input type="text" required class="span6 "name="user">
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button type="submit" class="btn btn-success btn-large btn-lg">Change UserName</button>
                                    <button type="reset"  class="btn btn-large btn-lg btn-danger">Cancel</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div id="newPass" class="tab-pane fade in ">
                <div class="row-fluid">

                    <h2>Nouveau Login Pour: ${personnel.getNomPrenom()}</h2>

                    <div class="widget black">
                        <div class="widget-title" style="background-color: #23527c">
                            <h4><i class="icon-reorder"></i>Nouveau Login: ${personnel.getNomPrenom()}</h4>

                        </div>
                        <div class="widget-body ">
                            <div class="space15"></div>
                            <form class="form-horizontal" method="POST" action="SettingPersonnal?vue=editePersonnel&action=newLoginforpersonnel&idPersonne=${personnel.getIdPersonnel()}&niveau=5">
                                <div class="control-group">
                                    <label class="control-label">User Name</label>
                                    <div class="controls">
                                        <input type="text" name="user_name" required class="span6 ">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">New Password</label>
                                    <div class="controls">
                                        <input type="password" required class="span6 "name="password">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Re-type New Password</label>
                                    <div class="controls">
                                        <input type="password" required class="span6 " name="password2">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Groupe Par Défaut</label>
                                    <div class="controls">
                                        <select name="groupe" class="span6 " >
                                            <c:forEach items="${sessionScope.All_Groupe}" var="gr">
                                                <c:if  test="${gr.getNiveau() == 1}">
                                                    <option value="${gr.getIdGroupes()}" class="select">${gr.getNomGroupe()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-actions">
                                    <button type="submit" class="btn btn-success btn-large btn-lg">OK</button>
                                    <button type="reset"  class="btn btn-large btn-lg btn-danger">Cancel</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div id="access" class="tab-pane fade in ">
                <div class="row-fluid">                  

                    <h2 id="userid" class="${personnel.getIdPersonnel()}">Change Groupe or Access Pages Groupe for ${personnel.getNomPrenom()}</h2>

                    <div class="widget ">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i> Change Permissions Groupe for this User: ${personnel.getNomPrenom()}</h4>

                        </div>
                        <div class="widget-body ">
                            <div class="space15"></div>
                            <div class="row-fluid">
                                <form name="form1" id="form1" method="POST" action="SettingPersonnal?vue=editePersonnel&action=updatepersonnel&idPersonne=${personnel.getIdPersonnel()}">
                                    <div class="row-fluid">
                                        <div class="control-group form-group span4">
                                            <label class="control-label">Niveau Access</label>
                                            <div class="controls ">
                                                <select name="affectation" id=""  type="text" class="form-control span12 affectation" >
                                                    <c:choose >
                                                        <c:when test="${personnel.getLoginList().getNiveauAcces() == 1}">
                                                            <option value="${personnel.getLoginList().getNiveauAcces()}" class="affectation">personnel simple</option>
                                                        </c:when>
                                                        <c:when test="${personnel.getLoginList().getNiveauAcces() == 2}">
                                                            <option value="${personnel.getLoginList().getNiveauAcces()}" class="affectation">magasinier secondaire</option>
                                                        </c:when>
                                                        <c:when test="${personnel.getLoginList().getNiveauAcces() == 3}">
                                                            <option value="${personnel.getLoginList().getNiveauAcces()}" class="affectation">magasinier principal</option>
                                                        </c:when>
                                                        <c:when test="${personnel.getLoginList().getNiveauAcces() == 4}">
                                                            <option value="${personnel.getLoginList().getNiveauAcces()}" class="affectation">controlleur</option>
                                                        </c:when>
                                                        <c:when test="${personnel.getLoginList().getNiveauAcces() == 5}">
                                                            <option value="${personnel.getLoginList().getNiveauAcces()}" class="affectation">Administrateur</option>
                                                        </c:when>
                                                    </c:choose>
                                                    <c:if  test="${personnel.getLoginList().getNiveauAcces() != 1}">
                                                        <option value="1" class="affectation">personnel simple</option>
                                                    </c:if>
                                                    <c:if  test="${personnel.getLoginList().getNiveauAcces() != 2}">
                                                        <option value="2" class="affectation">magasinier secondaire</option>
                                                    </c:if>
                                                    <c:if  test="${personnel.getLoginList().getNiveauAcces() != 3}">
                                                        <option value="3" class="affectation">magasinier principal</option>  
                                                    </c:if>
                                                    <c:if  test="${personnel.getLoginList().getNiveauAcces() != 4}">
                                                        <option value="4" class="affectation">controlleur</option> 
                                                    </c:if>
                                                    <c:if  test="${personnel.getLoginList().getNiveauAcces() != 5 and sessionScope.GeneralAdministrateur == 'OK'}">
                                                        <option value="5" class="affectation">Administrateur Regional</option> 
                                                    </c:if>

                                                </select>	
                                            </div>
                                        </div>
                                        <div class="control-group form-group span4">
                                            <label class="control-label">Groupe</label>
                                            <div class="controls " id="groupe">
                                                <select  class="input-lg span12 selectgroupes" id="selectgroupe"  name="groupe">
                                                    <option class="" value="${personnel.getLoginList().getGroupes().getIdGroupes()}">${personnel.getLoginList().getGroupes().getNomGroupe()}</option>
                                                    <c:forEach items="${groupes}" var="page">
                                                        <c:if test="${personnel.getLoginList().getGroupes().getIdGroupes() != page.getIdGroupes()}">
                                                            <c:if test="${personnel.getLoginList().getGroupes().getNiveau() == page.getNiveau()}">
                                                                <option class="" value="${page.getIdGroupes()}" >${page.getNomGroupe()}</option>
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                        </div>
                                        <div class="control-group form-group span4 magasin hidden">
                                            <label class="control-label"> Magasin</label>
                                            <div class="controls ">
                                                <select name="magasin" id="magasin" class="form-control  span12">
                                                    <c:if test="${not empty personnel.getAffectationmagasinPList()[0].getMagasinP().getIdMagasin()}">
                                                        <option value="${personnel.getAffectationmagasinPList().get(0).getMagasinP().getIdMagasin()}"></option>
                                                    </c:if>
                                                    <c:if test="${not empty personnel.getAffectationmagasinSList()[0].getMagasinS().getIdMagasin()}">
                                                        <option value="${personnel.getAffectationmagasinSList().get(0).getMagasinS().getIdMagasin()}"></option>
                                                    </c:if>
                                                </select>	
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group form-group span4 typecontrol hidden">
                                            <label class="control-label"> Type control</label>
                                            <div class="controls ">
                                                <select name="type_controle" id="role_personnel" class="form-control span12">
                                                    <c:if test="${not empty personnel.getAffectationControleursList()[0].getResponsableValidation().getIdResponsableValidation()}">
                                                        <option value="${personnel.getAffectationControleursList().get(0).getResponsableValidation().getIdResponsableValidation()}"></option>
                                                    </c:if>
                                                </select>	
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
                                                            <span>Recherche:</span>
                                                            <input type="text" id="box1Filter" />
                                                            <button type="button" class="btn" id="box1Clear">X</button>
                                                        </div>

                                                        <select id="box1View" multiple="multiple" name="" style="height:300px;width:100%">
                                                            <c:forEach items="${pagegroupe}" var="page">
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
                                                            <span>Recherche:</span>
                                                            <input type="text" id="box2Filter" />
                                                            <button type="button" class="btn" id="box2Clear">X</button>
                                                        </div>

                                                        <select id="box2View" multiple="multiple" name="pages" style="height:300px;width:100%;"<c:if  test="${not empty personnel.getLoginList().getGroupes().getRegion().getNomRegion()}">disabled</c:if>>
                                                            <c:forEach items="${personnel.getLoginList().getGroupes().getPermissionsList()}" var="page">
                                                                <option value="${page.getPage().getIdPage()}">${page.getPage().getNomPage()}</option>

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

                                    <div class="space20"></div>
                                    <div class="form-actions mtop20 text-center">
                                        <button type="submit" class="btn btn-success btn-large btn-lg"style="margin-right: 100px">Save Change</button>
                                        <button type="reset"  class="btn btn-large btn-lg btn-danger">Cancel</button>
                                    </div>

                                </form>
                            </div>
                            <div class="space20"></div>
                            <div class="space20"></div>

                        </div>
                    </div>
                </div>
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
        </div>
    </div>






</div>











