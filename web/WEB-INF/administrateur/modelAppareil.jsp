<%-- 
    Document   : typeAppareil
    Created on : 17 août 2017, 10:43:38
    Author     : Administrateur
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="page-title">
    Espace Model Appariels Parc Informatique
</h3>
<ul class="breadcrumb">
    <li>

        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#"><i class="icon-group"></i> Model Appareil</a>

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
        <h4><i class="icon-reorder"></i> Espace Model Appareil</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">

            <h4>nouveau Model d'appareil</h4>
            <div class="row-fluid">
                <c:if test="${empty updateModel}">


                    <div class="row-fluid">


                        <form action="personnel?action=addModelAppareil&vue=Model Appareil" id="fileForm" method="post">
                            <div class="control-group span4">
                                <label class="control-label">Model Appareil</label>
                                <div class="controls">
                                    <input type="text" placeholder="Model appareil" class="default span12" required name="model" id="">
                                </div>
                            </div>
                            <div class="span4">
                                <div class="control-group ">
                                    <button type="submit" class="btn btn-success btn-large  btn-lg" style="margin-top: 22px" >AJOUTER</button>
                                </div>
                            </div>
                        </form>
                        <c:if test="${not empty message}">
                            <div class="alert alert-danger  span12 text-center ">
                                <strong>${message.getMessage()}</strong>
                            </div>
                        </c:if>
                    </div>
                </c:if>

            </div>
            <div class="row-fluid">
                <c:if test="${not empty updateModel}">
                    <div class="row-fluid">
                        <form action="personnel?action=updateModelAppareil&vue=Model Appareil&id_model=${model.getIdModel()}" id="fileForm" method="post">
                            <div class="row-fluid ">
                                <div class="control-group span4">

                                    <label class="control-label">Model</label>
                                    <div class="controls">
                                        <input type="text" class="default span12"  value="${model.getNomModel()}" name="model" id="">
                                    </div>
                                </div>



                                <div class="span4">
                                    <div class="control-group ">
                                        <button type="submit" class="btn btn-success btn-large btn-lg" style="margin-top: 22px" >SAVE</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:if>
                <table class="table table-bordered table-hover table-striped simple_print">
                    <thead>
                        <tr >
                            <th>Model Appareil</th>

                            <th>option</th>

                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${models}" var="app">



                            <tr class="produit">
                                <td > <c:out value="${app.getNomModel()}"/> </td>
                                <td > 
                                    <div class="btn-group">
                                        <a title="" href="personnel?action=editeModelAppareil&vue=Model Appareil&id_model=${app.getIdModel()}" class="btn btn-primary" >  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                        <a class="btn btn-danger delete-insert" href="#"  name="personnel?action=deleteModelAppareil&vue=Model Appareil&id_model=${app.getIdModel()}" data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>

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
