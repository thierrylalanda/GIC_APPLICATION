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
    Espace Type Appariels Parc Informatique
</h3>
<ul class="breadcrumb">
    <li>

        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#"><i class="icon-group"></i> Type Appareil</a>

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
        <h4><i class="icon-reorder"></i> Espace Type Appareil</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">

            <h4>nouveau type d'appareil</h4>
            <div class="row-fluid">
                <c:if test="${empty updatetype}">
                    <div class="row-fluid">
                        <form action="personnel?action=addTypeAppareil&vue=typeappareil" id="fileForm" method="post">
                            <div class="control-group span4">
                                <label class="control-label">type</label>
                                <div class="controls">
                                    <input type="text" placeholder="type appareil" class="default span12" required name="type_appareil" id="">
                                </div>
                            </div>
                            <div class="span4">
                                <div class="control-group ">
                                    <button type="submit" class="btn btn-success btn-large  btn-lg" style="margin-top: 22px" >Enregistrer</button>
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
                <c:if test="${not empty updatetype}">
                    <div class="row-fluid">
                        <form action="personnel?action=updateTypeAppareil&vue=typeappareil&id_type=${typeapp.getId()}" id="fileForm" method="post">
                            <div class="row-fluid ">
                                <div class="control-group span4">

                                    <label class="control-label">type</label>
                                    <div class="controls">

                                        <input type="text" class="default span12"  value="${typeapp.getNom()}" name="type_categorie" id="">
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
                <table class="table table-bordered table-hover table-striped simple_print"  cellspacing="0" width="100%">
                    <thead>
                        <tr >
                            <th>Type Appareil</th>

                            <th>option</th>

                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${typeappareil}" var="app">



                            <tr class="produit">
                                <td > <c:out value="${app.getNom()}"/> </td>
                                <td > 
                                    <div class="btn-group">
                                        <a title="" href="personnel?action=editTypeAppareil&vue=typeappareil&id_type=${app.getId()}" class="btn btn-primary" >  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                        <a class="btn btn-danger delete-insert"  name="personnel?action=deleteTypeAppareil&vue=typeappareil&id_type=${app.getId()}" href="#" data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>

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
