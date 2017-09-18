<%-- 
    Document   : controlleur
    Created on : 18 avr. 2017, 16:50:59
    Author     : lalanda
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="page-title">
    Espace Personnel Controleur
</h3>
<ul class="breadcrumb">
    <li>

        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#"><i class="icon-group"></i> Controleurs</a>

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
        <h4><i class="icon-reorder"></i> Espace Controleur</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <ul class="nav nav-tabs" id="myTab">
                <li class="active"><a data-toggle="tab" href="#home">LISTE DES CONTROLEURS</a></li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div id="home" class="tab-pane fade in active">
                    <table class="table table-bordered table-hover table-striped simple_print">
                        <thead>
                            <tr >
                                <th>Nom Prenom</th>
                                <th>Matricule</th>
                                <th>Fonction</th>
                                <th>telephone</th>
                                <th>E-mail</th>
                                <th>Service</th>
                                <th>role</th>
                                <th>description</th>
                                <th>options</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty sessionScope.GeneralAdministrateur}">
                                <c:forEach items="${responsables}" var="pers">
                                    <c:if test="${sessionScope.personnel.getService().getSite().getRegion().getIdRegion() == pers.getPersonnel().getService().getSite().getRegion().getIdRegion()}">
                                        <tr class="produit">
                                            <td > <c:out value="${pers.getPersonnel().getNomPrenom()}"/> </td>
                                            <td class="code"><c:out value="${pers.getPersonnel().getMatricule()}"/></td>
                                            <td><c:out value="${pers.getPersonnel().getFonction()}"/></td>
                                            <td > <c:out value="${pers.getPersonnel().getTelephone()}"/> </td>
                                            <td > <c:out value="${pers.getPersonnel().getEmail()}"/> </td>
                                            <td > <c:out value="${pers.getPersonnel().getService().getNomService()}"/> </td>
                                            <td > <c:out value="${pers.getPersonnel().getRole()}"/> </td>
                                            <td > <c:out value="${pers.getResponsableValidation().getDescription()}"/> </td>
                                            <td >
                                                <div class="btn-group">
                                                    <a title="" href="SettingPersonnal?action=editpersonnel&vue=editePersonnel&id=${pers.getPersonnel().getIdPersonnel()}&controleur=yes" class="btn btn-primary">  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                                        <a class="btn btn-danger delete-insert" href="#" name="SettingPersonnal?action=DelettePersonnel&vue=perso&personnel=${pers.getPersonnel().getIdPersonnel()}" data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>
                                                    </c:if>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${not empty sessionScope.GeneralAdministrateur}">
                                <c:forEach items="${responsables}" var="pers">

                                    <tr class="produit">
                                        <td > <c:out value="${pers.getPersonnel().getNomPrenom()}"/> </td>
                                        <td class="code"><c:out value="${pers.getPersonnel().getMatricule()}"/></td>
                                        <td><c:out value="${pers.getPersonnel().getFonction()}"/></td>
                                        <td > <c:out value="${pers.getPersonnel().getTelephone()}"/> </td>
                                        <td > <c:out value="${pers.getPersonnel().getEmail()}"/> </td>
                                        <td > <c:out value="${pers.getPersonnel().getService().getNomService()}"/> </td>
                                        <td > <c:out value="${pers.getPersonnel().getRole()}"/> </td>
                                        <td > <c:out value="${pers.getResponsableValidation().getDescription()}"/> </td>
                                        <td >
                                            <div class="btn-group">
                                                <a title="" href="SettingPersonnal?action=editpersonnel&vue=editePersonnel&id=${pers.getPersonnel().getIdPersonnel()}&controleur=yes" class="btn btn-primary">  <span class="icon"> <i class="icon-edit"></i> </span></a>  

                                                <a class="btn btn-danger delete-insert" href="#" name="SettingPersonnal?action=DelettePersonnel&vue=perso&personnel=${pers.getPersonnel().getIdPersonnel()}" data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


