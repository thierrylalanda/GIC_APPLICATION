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
    Espace Budget
</h3>
<ul class="breadcrumb">
    <li>

        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#"><i class="icon-group"></i> Budget</a>

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
        <h4><i class="icon-reorder"></i> Espace Budget</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <ul class="nav nav-tabs" id="myTab">


                <li class="active"><a href="#add-budget" data-toggle="tab">Budgets</a> </li>

            </ul>
            <div class="tab-content" id="myTabContent">

                <div id="add-budget" class="tab-pane fade in active">
                    <c:if test="${not empty updatebudget}">
                        <h3 class="text-info">mettre a jour le budget</h3>
                    </c:if>
                    <c:if test="${empty updatebudget}">
                        <h3 class="text-info">Creer un nouveau budget</h3>
                    </c:if>
                    <div class="row-fluid">
                        <div class=" span12 text-center text-info"><h2><span class="icon"> <i class="icon-dollar"></i> Budgets</span></h2></div>
                                    <c:if test="${not empty updatebudget}">
                                        <c:import url="/WEB-INF/administrateur/updateBudget.jsp"/>
                                    </c:if>
                                    <c:if test="${empty updatebudget}">


                            <form role="form" action="personnel?action=addBudgetMagasin&vue=budget" method="post">
                                <div class="row-fluid">

                                    <div class="control-group form-group span4 ">
                                        <label class="control-label">Region</label>
                                        <div class="controls ">
                                            <select name="region" class="form-control span12" id="regiontomagasin">
                                                <c:forEach items="${regions}" var="reg">
                                                    <option value="${reg.getIdRegion()}" class="region">${reg.getNomRegion()}</option>

                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group form-group span4 ">
                                        <label class="control-label">Magasin</label>
                                        <div class="controls ">
                                            <select name="id_Magasin" class="form-control magasinbyregion span12" id="">

                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group form-group span4 ">
                                        <label class="control-label">Type de budget</label>
                                        <div class="controls ">
                                            <select name="type_budget" class="form-control span12" id="categorietoarticles">

                                            </select>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group form-group span4 ">
                                            <label class="control-label">Montant</label>
                                            <div class="controls ">
                                                <input type="number" class="  form-control span12" name="montant" required placeholder="Montant"/>

                                            </div>
                                        </div>

                                        <div class="control-group form-group span4 float-label-control">
                                            <label class="control-label" for="">date Attribution</label>
                                            <div class="controls ">
                                                <input type="text" class="datepicker span12 form-control" name="dateAttribution" required placeholder="date Attribution"/>

                                            </div>
                                        </div>
                                        <div class="control-group form-group span4 float-label-control">
                                            <label class="control-label" for="">date expiration</label>
                                            <div class="controls ">
                                                <input type="text" class="datepicker span12 form-control" name="dateExpiration" required placeholder="date expiration"/>

                                            </div>
                                        </div>
                                    </div>
                                </div>    

                                <div class="row-fluid">
                                    <div class="control-group form-group text-center  float-label-control">
                                        <div class="controls ">
                                            <button type="submit" class="btn btn-large btn-success span12">enregistre</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:if>

                    </div>
                    <div class="space20"></div>

                    <div class="row-fluid">
                        <table class="table table-bordered table-hover table-striped simple_print"cellspacing="0" width="100%">
                            <thead>
                                <tr >
                                    <th>magasin</th>
                                    <th>budget pour</th>
                                    <th>montant alloue</th>
                                    <th>montant restant</th>
                                    <th>date Attribution</th>
                                    <th>date Expiration</th>
                                    <th>Options</th>

                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach  items="${butget}" var="bud">
                                    <tr class="">
                                        <td >${bud.getMagasin().getNomMagasin()}</td>
                                        <td >${bud.getTypeBudget()}</td>
                                        <td class="pt"><f:formatNumber value="${bud.getMontant()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                        <td class="pt"><f:formatNumber value="${bud.getMontantRestant()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                        <td><f:formatDate value="${bud.getDateDatribution()}" type="Date" dateStyle="MEDIUM"/></td>
                                        <td><f:formatDate value="${bud.getDateExpiration()}" type="Date" dateStyle="MEDIUM"/></td>
                                        <td >
                                            <div class="btn-group">
                                                <a title="" href="personnel?action=updatebudget&vue=budget&id_budget=${bud.getIdBudget()}" class="btn btn-primary" >  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                                <a class="btn btn-danger delete-insert" href="#" name="personnel?action=deletebudget&vue=budget&id_budget=${bud.getIdBudget()}"data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>
                                            </div>
                                        </td>

                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="space20"></div>

            </div>
        </div>
    </div>
</div>

