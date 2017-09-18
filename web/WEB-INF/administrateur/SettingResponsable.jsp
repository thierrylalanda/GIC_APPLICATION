
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
    Espace Responsable De Validation Des Commandes
</h3>
<ul class="breadcrumb">
    <li>

        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#"><i class="icon-group"></i> Controle</a>

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
                <li class="active"><a href="#all-direction" data-toggle="tab">Bureau De control</a></li>

                <li><a href="#add-direction" data-toggle="tab">Ajouter Un  Bureau De Control</a> </li>

            </ul>
            <div class="tab-content" id="myTabContent">
                <div id="all-direction" class="tab-pane fade in active">
                    <c:if test="${not empty updaterespo}">
                        <div class="row-fluid">
                            <form action="personnel?action=updateResponsable&vue=settingresponsable&id_responsable=${responsable.getIdResponsableValidation()}" method="post">
                                <div class="row-fluid">
                                    <div class="control-group form-group span4">
                                        <label class="control-label">Type De Control</label>
                                        <div class="controls ">
                                            <input type="text" value="${responsable.getDescription()}" class="span12  form-control" name="type_control" required placeholder="Exp: Control De Gestion"/>

                                        </div>
                                    </div>

                                    <div class="control-group form-group span4">
                                        <label class="control-label">Niveau De Priorité</label>
                                        <div class="controls ">
                                            <select name="priorite" class="form-control span12" >
                                                <option value="1">Niveau 1</option>
                                                <option value="2">Niveau 2</option>
                                                <option value="3">Niveau 3</option>
                                                <option value="4">Niveau 4</option>
                                                <option value="5">Niveau 5</option>
                                                <option value="6">Niveau 6</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="span4">
                                        <div class="control-group ">
                                            <button type="submit" class="btn btn-success btn-large span12 btn-lg" style="margin-top: 22px" >Update</button>
                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </c:if>
                    <h3>Tous Les Bureau De Control</h3>
                    <table class="table table-bordered table-hover table-striped simple_print"  cellspacing="0" width="100%">
                        <thead>
                            <tr >
                                <th>Type</th>
                                <th>Niveau De Priorité</th>
                                <th>options</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${respo}" var="respo">
                                <tr class="produit">
                                    <td > ${respo.getDescription()}</td>
                                    <td > ${respo.getNiveau()}</td>
                                    <td >
                                        <div class="btn-group">
                                            <a title="" href="personnel?action=editResponsable&vue=settingresponsable&id_responsable=${respo.getIdResponsableValidation()}" class="btn btn-primary">  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                            <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                                <a class="btn btn-danger delete-insert"  href="#" name="personnel?action=deleteResponsable&vue=settingresponsable&id_responsable=${respo.getIdResponsableValidation()}" data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>
                                            </c:if>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div id="add-direction" class="tab-pane fade">
                    <h3 class="text-info">nouveau Bureau De Control</h3>
                    <div class="row-fluid">

                        <form action="personnel?action=addResponsable&vue=settingresponsable" method="post">
                            <div class="row-fluid">
                                <div class="control-group form-group span4">
                                    <label class="control-label">Type De Control</label>
                                    <div class="controls ">
                                        <input type="text" class="span12  form-control" name="type_control" required placeholder="Exp: Control De Gestion"/>

                                    </div>
                                </div>

                                <div class="control-group form-group span4">
                                    <label class="control-label">Niveau De Priorité</label>
                                    <div class="controls ">
                                        <select name="priorite" class="form-control span12" >
                                            <option value="1">Niveau 1</option>
                                            <option value="2">Niveau 2</option>
                                            <option value="3">Niveau 3</option>
                                            <option value="4">Niveau 4</option>
                                            <option value="5">Niveau 5</option>
                                            <option value="6">Niveau 6</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="span4">
                                    <div class="control-group ">
                                        <button type="submit" class="btn btn-success btn-large span12 btn-lg" style="margin-top: 22px" >Enregistrer</button>
                                    </div>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>

                <div class="space20"></div>

            </div>
        </div>
    </div>
</div>



