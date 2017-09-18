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
        <a href="#"><i class="icon-group"></i> Fabriquant Appareil</a>

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
        <h4><i class="icon-reorder"></i> Espace Fabriquant Appareil</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">

            <h4>nouveau Fabriquant d'appareil</h4>
            <div class="row-fluid">
                <c:if test="${empty updateFabriquant}">


                    <div class="row-fluid">


                        <form action="personnel?action=addFabriquantAppareil&vue=Fabriquant Appareil" id="fileForm" method="post">
                            <div class="control-group span4">
                                <label class="control-label">Fabriquant</label>
                                <div class="controls">
                                    <input type="text" placeholder="Fabriquant d'appareil" class="default span12" required name="fabriquant" id="">
                                </div>
                            </div>
                            <div class="span4">
                                <div class="control-group ">
                                    <button type="submit" class="btn btn-success btn-large btn-lg" style="margin-top: 22px" >Ajouter</button>
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
                <c:if test="${not empty updateFabriquant}">
                    <div class="row-fluid">
                        <form action="personnel?action=updateFabriquantAppareil&vue=Fabriquant Appareil&id_fabriquant=${fabriquant.getIdFabriquant()}" id="fileForm" method="post">
                            <div class="row-fluid ">
                                <div class="control-group span4">
                                    <label class="control-label">Fabriquant</label>
                                    <div class="controls">
                                        <input type="text" class="default span12"  value="${fabriquant.getNomFabriquant()}" name="fabriquant" required id="">
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
                            <th>Fabriquant Appareil</th>

                            <th>option</th>

                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${fabriquantappareil}" var="app">



                            <tr class="produit">
                                <td > <c:out value="${app.getNomFabriquant()}"/> </td>
                                <td > 
                                    <div class="btn-group">
                                        <a title="" href="personnel?action=editfabriquantAppareil&vue=Fabriquant Appareil&id_fabriquant=${app.getIdFabriquant()}" class="btn btn-primary" >  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                        <a class="btn btn-danger delete-insert" href="#"  name="personnel?action=deletefabriquantAppareil&vue=Fabriquant Appareil&id_fabriquant=${app.getIdFabriquant()}"data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>

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
