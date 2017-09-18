<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="page-title">
    Espace Appariels
</h3>
<ul class="breadcrumb">
    <li>

        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li class="active">
        <a href="#"><i class="icon-desktop"></i> Appariels</a>

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

<div class="widget orange">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i> Espace Appariels Personnels</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <ul class="nav nav-tabs" id="myTab">
                <c:if test="${ not empty sessionScope.lien12 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <li class="active"><a data-toggle="tab" href="#home">LISTE Des Appariels Du Personnels</a></li>
                    </c:if>
                    <c:if test="${sessionScope.lien13 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <li><a data-toggle="tab" href="#dropdown1">Ajouter Un Appariel</a></li>
                    </c:if>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div id="home" class="tab-pane fade in active">
                    <table class="table table-bordered table-hover table-striped display simple_print" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th>Numero</th>
                                <th>type</th>
                                <th>lieu</th>
                                <th>fabricant</th>
                                <th>numero serie</th>
                                <th>model</th>
                                <th>proprietaire</th>
                                <th>Options</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty sessionScope.GeneralAdministrateur}">
                                <c:forEach items="${appariels}" var="app">
                                    <c:if test="${sessionScope.personnel.getService().getSite().getRegion().getIdRegion() == app.getProprietaire().getService().getSite().getRegion().getIdRegion() or empty app.getProprietaire().getService().getSite().getRegion().getIdRegion()}">
                                        <c:if test="${app.getNumeroSerie() != '000000'}">
                                            <tr class="">
                                                <td class=""> ${app.getNumeroParck()}</td>
                                                <td class=""> ${app.getTypeAppareil().getNom()}</td>
                                                <td class=""> ${app.getLieu()}</td>
                                                <td class=""> ${app.getFabricant()}</td>
                                                <td class=""> ${app.getNumeroSerie()}</td>
                                                <td class=""> ${app.getModel()}</td>
                                                <td class=""> ${app.getProprietaire().getNomPrenom()}</td>
                                                <td>
                                                    <div class="btn-group">
                                                        <a title="" href="SettingPersonnal?vue=EditAppariel&action=selectApparielToEdite&numeroParck=${app.getNumeroParck()}" class="btn btn-primary">  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                                        <c:if test="${not empty sessionScope.GeneralAdministrateur == 'OK'}">
                                                            <a class="btn btn-danger delete-insert"  href="#" name="SettingPersonnal?vue=Appariels&action=deletteAppariel&numeroParck=${app.getNumeroParck()}" data-toggle="modal" data-target=""> <span class="icon"> <i class="icon-trash"></i> </span></a>
                                                        </c:if>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${not empty sessionScope.GeneralAdministrateur}">

                                <c:forEach items="${appariels}" var="app">
                                    <c:if test="${app.getNumeroSerie() != '000000'}">
                                        <tr class="">
                                            <td class=""> ${app.getNumeroParck()}</td>
                                            <td class=""> ${app.getTypeAppareil().getNom()}</td>
                                            <td class=""> ${app.getLieu()}</td>
                                            <td class=""> ${app.getFabricant()}</td>
                                            <td class=""> ${app.getNumeroSerie()}</td>
                                            <td class=""> ${app.getModel()}</td>
                                            <td class=""> ${app.getProprietaire().getNomPrenom()}</td>

                                            <td >
                                                <div class="btn-group">
                                                    <a title="" href="SettingPersonnal?vue=EditAppariel&action=selectApparielToEdite&numeroParck=${app.getNumeroParck()}" class="btn btn-primary">  <span class="icon"> <i class="icon-edit"></i> </span></a>  

                                                    <a class="btn btn-danger delete-insert" href="SettingPersonnal?vue=Appariels&action=deletteAppariel&numeroParck=${app.getNumeroParck()}" data-toggle="modal" data-target=""> <span class="icon"> <i class="icon-trash"></i> </span></a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
                <div id="dropdown1" class="tab-pane fade">
                    <c:import url="addAppareil.jsp"/>
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





