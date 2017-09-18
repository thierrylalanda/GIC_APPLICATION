<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="page-title">
    Espace Personnel
</h3>
<ul class="breadcrumb">
    <li>

        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li class="active">
        <a href="#"><i class="icon-group"></i> Personnels</a>

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
        <h4><i class="icon-reorder"></i> Espace Personnels</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <ul class="nav nav-tabs" id="myTab">
                <c:if test="${not empty sessionScope.lien1 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <li class="active"><a data-toggle="tab" href="#home">LISTE Du Personnels</a></li>
                    </c:if>
                    <c:if test="${ not empty sessionScope.lien2 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <li><a data-toggle="tab" href="#profile">Ajouter Un Personnel</a></li>
                    </c:if>
                    <c:if test="${ not empty sessionScope.AddpersonnelForme or sessionScope.GeneralAdministrateur == 'OK'}">
                    <li><a data-toggle="tab" href="#formulairePerso">Ajouter Un Simple Personnel Depuis Un Fichier</a></li>
                    </c:if>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div id="home" class="tab-pane fade in active">
                    <table class="table table-bordered table-hover table-striped display simple_print" cellspacing="0" width="100%" id="example">
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
                            <c:if test="${ empty sessionScope.GeneralAdministrateur}">
                                <c:forEach items="${personnels}" var="pers">
                                    <c:if test="${sessionScope.personnel.getService().getSite().getRegion().getIdRegion() == pers.getService().getSite().getRegion().getIdRegion()}">
                                        <c:if test="${pers.getMatricule() != '000000'}">
                                            <tr class="produit">

                                                <td > <c:out value="${pers.getNomPrenom()}"/> </td>
                                                <td class="code"><c:out value="${pers.getMatricule()}"/></td>
                                                <td><c:out value="${pers.getFonction()}"/></td>
                                                <td > <c:out value="${pers.getTelephone()}"/> </td>
                                                <td > <c:out value="${pers.getEmail()}"/> </td>
                                                <td > <c:out value="${pers.getService().getNomService()}"/> </td>
                                                <td > <c:out value="${pers.getRole()}"/> </td>
                                                <td >
                                                    <div class="btn-group">
                                                        <a title="" href="SettingPersonnal?action=editpersonnel&vue=editePersonnel&id=${pers.getIdPersonnel()}&controleur=control" class="btn btn-primary">  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                                        <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                                            <a class="btn btn-danger delete" href="#" name="SettingPersonnal?action=DelettePersonnel&vue=perso&personnel=${pers.getIdPersonnel()}" data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>
                                                        </c:if>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${not empty sessionScope.GeneralAdministrateur}">
                                <c:forEach items="${personnels}" var="pers">
                                    <c:if test="${pers.getMatricule() != '000000'}">
                                        <tr class="produit">

                                            <td > <c:out value="${pers.getNomPrenom()}"/> </td>
                                            <td class="code"><c:out value="${pers.getMatricule()}"/></td>
                                            <td><c:out value="${pers.getFonction()}"/></td>
                                            <td > <c:out value="${pers.getTelephone()}"/> </td>
                                            <td > <c:out value="${pers.getEmail()}"/> </td>
                                            <td > <c:out value="${pers.getService().getNomService()}"/> </td>
                                            <td > <c:out value="${pers.getRole()}"/> </td>
                                            <td >
                                                <div class="btn-group">
                                                    <a title="" href="SettingPersonnal?action=editpersonnel&vue=editePersonnel&id=${pers.getIdPersonnel()}&controleur=control" class="btn btn-primary">  <span class="icon"> <i class="icon-edit"></i> </span></a>  

                                                    <a class="btn btn-danger delete" href="#" name="SettingPersonnal?action=DelettePersonnel&vue=perso&personnel=${pers.getIdPersonnel()}" data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table> 
                </div>
                <div id="profile" class="tab-pane fade">
                    <c:import url="addPersonnel.jsp"/>
                </div>
                <div id="formulairePerso" class="tab-pane fade">

                    <div class="row-fluid">
                        <form method="post" role="form"  id="addstockform" enctype="multipart/form-data" action="UploadFilePersonnel?vue=perso">
                            <div class="row-fluid">
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

                        <img src="image/peso.png" alt="Exemple de fichier Excel"/>
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


