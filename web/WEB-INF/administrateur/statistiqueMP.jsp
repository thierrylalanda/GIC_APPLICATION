
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<div class="row-fluid">
    <div class="span12">
        <!-- BEGIN CHART PORTLET-->
        <div class="widget orange">
            <div class="widget-title">
                <h4><i class="icon-reorder"> </i> Statistiques Entrer/Sortis Des Produits Du: ${magasin.getNomMagasin()}</h4>
                <span class="tools">
                    <a href="javascript:;" class="icon-chevron-down"></a>

                </span>
            </div>
            <div class="widget-body">

                <h2>legende</h2>
                <div class="row-fluid legende">





                </div>
                <div class="space20"></div>
                <div class="space20"></div>
                <c:if test="${not empty sessionScope.lien45 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <h2 class="text-center">Statistique Sortir Des Produits Pour L'annee 2017</h2>
                    <div class="text-center">
                        <canvas id="lineMP" height="300" width="1100"></canvas>
                    </div>
                    <hr>
                </c:if>
                <div class="space20"></div>
                <div class="space20"></div>
                <c:if test="${not empty sessionScope.lien46 or sessionScope.GeneralAdministrateur == 'OK'}">
                    <h2 class="text-center">Statistique De D'Entrer Des Produits Pour L'annee 2017</h2>

                    <div id="barchar" class="row-fluid"></div>
                    <hr>
                </c:if>
            </div>
        </div>
        <!-- END CHART PORTLET-->
    </div>
</div>