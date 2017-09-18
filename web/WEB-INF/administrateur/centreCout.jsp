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
    Espace Centre de cout
</h3>
<ul class="breadcrumb">
    <li>

        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#"><i class="icon-group"></i> Centre Cout</a>

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
        <h4><i class="icon-reorder"></i> Espace Centre De Cout</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">

            <h4>nouveau centre de cout</h4>
            <div class="row-fluid">
                <c:if test="${empty update}">


                    <div class="row-fluid">


                        <form action="personnel?action=addcentrecout&vue=centre cout" id="fileForm" method="post">
                            <div class="row-fluid">
                                <div class="control-group span4">
                                    <label class="control-label">centre de coute</label>
                                    <div class="controls">
                                        <input type="text" placeholder="centre de cout" class="default span12" required name="centrecout" id="">
                                    </div>
                                </div>
                                <div class="span4">
                                    <div class="control-group ">
                                        <button type="submit" class="btn btn-success btn-large span12 btn-lg" style="margin-top: 22px" >Ajouter</button>
                                    </div>
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
                <c:if test="${not empty update}">
                    <div class="row-fluid">
                        <form action="personnel?action=updatecentrecout&vue=centre cout&id_cc=${cc.getIdCout()}" id="fileForm" method="post">
                            <div class="row-fluid ">
                                <div class="control-group span4">

                                    <label class="control-label">centre de cout</label>
                                    <div class="controls">

                                        <input type="text" class="default span12"  value="${cc.getLibelle()}" name="centrecout" id="">
                                    </div>
                                </div>
                                <div class="span4">
                                    <div class="control-group ">
                                        <button type="submit" class="btn btn-success btn-large span12 btn-lg" style="margin-top: 22px" >SAVE</button>
                                    </div>
                                </div>

                            </div>
                        </form>
                    </div>
                </c:if>
                <table class="table table-bordered table-hover table-striped simple_print"  cellspacing="0" width="100%">
                    <thead>
                        <tr >
                            <th>centre de cout</th>

                            <th>option</th>

                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${centreCout}" var="cc">



                            <tr class="produit">
                                <td > <c:out value="${cc.getLibelle()}"/> </td>
                                <td > 
                                    <div class="btn-group">
                                        <a title="" href="personnel?action=editecentrecout&vue=centre cout&id_cc=${cc.getIdCout()}" class="btn btn-primary" >  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                        <a class="btn btn-danger delete-insert"  href="#" name="personnel?action=deletecentrecout&vue=centre cout&id_cc=${cc.getIdCout()}" name="" data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>

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
