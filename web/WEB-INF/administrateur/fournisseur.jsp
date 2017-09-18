<%-- 
    Document   : SettingResponsable
    Created on : 15 mai 2017, 12:04:47
    Author     : lalanda
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h3 class="page-title">
    Parametre
</h3>
<ul class="breadcrumb">
    <li>
        <a href="#">Home</a>
        <span class="divider">/</span>
    </li>
    <li class="active">
        <a href="#">Parametre</a>

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

<div class="widget green-box">
    <div class="widget-title text-capitalize">
        Autres Configurations
    </div>
    <div class="widget-body">
        <ul class="nav nav-pills">
            <li class="active"><a href="#all-fournisseurs" data-toggle="tab">Fournisseurs</a></li>
                <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">

            </c:if>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade in active" id="all-fournisseurs">
                <c:import url="/WEB-INF/administrateur/settingEditFournisseur.jsp"/>

            </div>


        </div>
    </div>
</div>
