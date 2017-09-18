
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="space20"></div>
<h4 class="page-title">
    Tableau de bord Du Magasin : ${ sessionScope.magasinMS.getNomMagasin()}
</h4>
<ul class="breadcrumb">
    <li>
        <a href="RedirectionVue?vue=Accueil"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">consommation et budget</a>

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

<div class="row-fluid ">
    <div class="container">
        <!-- BEGIN CHART PORTLET-->
        <div class="widget black">
            <div class="widget-title">
                <h4><i class="icon-reorder"> </i> evolution  consommations et budgetaire du: ${magasin.getNomMagasin()}</h4>
                <span class="tools">
                    <a href="javascript:;" class="icon-chevron-down"></a>
                </span>
            </div>
            <div class="widget-body">
                <div class="bs-docs-example">
                    <ul class="nav nav-tabs" id="myTab">

                        <li class="active"><a data-toggle="tab" href="#consommation">consommation</a></li>
                        <li class=""><a data-toggle="tab" href="#budget">Budget</a></li>

                    </ul>
                    <div class="tab-content" id="myTabContent">
                        <div id="consommation" class="tab-pane fade in active">
                            <h2>legende</h2>
                            <div class="row-fluid legende">

                            </div>
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <c:if test="${not empty sessionScope.StatistiqueEntrerMS }">
                                <h2 class="text-center">Statistique Sortir Des Produits Pour L'annee 2017</h2>

                                <div class="text-center">
                                    <canvas id="line" height="300" width="1100"></canvas>
                                </div>
                                <hr>
                            </c:if>
                            <div class="space20"></div>
                            <div class="space20"></div>
                            <c:if test="${not empty sessionScope.StatistiqueSortiMS}">
                                <h2 class="text-center">Statistique D'Entrer Des Produits Pour L'annee 2017</h2>

                                <div id="barchar" class="row-fluid"></div>
                                <hr>
                            </c:if>
                        </div>

                        <div id="budget" class="tab-pane fade in">
                            <c:import url="/WEB-INF/administrateur/statistiqueBMS.jsp"/>
                        </div>
                    </div>
                </div>


            </div>
        </div>
        <!-- END CHART PORTLET-->
    </div>
</div>