

<%-- 
    Document   : controlleur
    Created on : 18 avr. 2017, 16:50:59
    Author     : lalanda
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<div class="widget orange">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i>Evolution Consommation Et Budgetaire</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <ul class="nav nav-tabs" id="myTab">
                <li class="active"><a href="#all-region" data-toggle="tab">EVOLUTION CONSOMMATION</a></li>
                <li class=""><a href="#all-budget" data-toggle="tab">EVOLUTION BUDGETAIRE</a></li>


            </ul>
            <div class="tab-content" id="myTabContent">
                <div id="all-budget" class="tab-pane fade ">
                    <div class="row-fluid">
                        <div class="span12">
                            <div class="widget">
                                <div class="widget-title">
                                    <h4><i class="icon-reorder"></i>STATISTIQUES BUDGETAIRES</h4>
                                    <span class="tools">

                                    </span>
                                </div>
                                <div class="widget-body">
                                    <div class="row-fluid">
                                        <div class="control-group span3">
                                            <label class="control-label">Region</label>
                                            <div class="controls ">
                                                <select name="region" id="region">

                                                    <c:forEach items="${regions}" var="co">
                                                        <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                            <option class="region" value="${co.getIdRegion()}" selected>
                                                                ${co.getNomRegion()}
                                                            </option> 
                                                        </c:if>
                                                        <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                            <option  class="region" value="${co.getIdRegion()}">
                                                                ${co.getNomRegion()}
                                                            </option> 
                                                        </c:if>

                                                    </c:forEach>	
                                                </select>	

                                            </div>
                                        </div>






                                    </div>
                                    <div class="row-fluid stat-camanber">
                                        <h4 style="text-align: center"><u >STATISTIQUE BUGETAIRE POUR <span class="titre"></span></u></h4>
                                        <div class="row-fluid stat">

                                        </div>
                                        <hr>

                                        <div class="row-fluid " id="stat-chart">

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>


                <div id="all-region" class="tab-pane fade in active">

                    <div class="row-fluid">
                        <div class="span12">
                            <div class="widget red">
                                <div class="widget-title">
                                    <h4><i class="icon-reorder"></i>RAPPORT DES CONSOMMATIONS</h4>
                                    <span class="tools">

                                    </span>
                                </div>
                                <div class="widget-body">
                                    <div class="bs-docs-example">
                                        <ul class="nav nav-tabs" id="myTabp">
                                            <li class="li-stat "><a data-toggle="tab" href='#rapport_societe'>Societe</a></li>
                                            <li class="li-stat active"><a data-toggle="tab" href='#rapport_region'>Region</a></li>
                                            <li class="li-stat "><a data-toggle="tab" href='#rapport_direction'>Direction</a></li>
                                            <li class="li-stat "><a data-toggle="tab" href='#rapport_site'>Site</a></li>
                                            <li class="li-stat "><a data-toggle="tab" href='#rapport_service'>Service</a></li>
                                            <li class="li-stat "><a data-toggle="tab" href='#rapport_mags'>Magasin Secondaire</a></li>
                                            <li class="li-stat "><a data-toggle="tab" href='#rapport_magp'>Magasin Principal</a></li>
                                            <li class="li-stat "><a data-toggle="tab" href='#rapport_personnel'>Personnel</a></li>

                                        </ul>
                                        <div class="tab-content" id="myTabContent2">
                                            <div id='rapport_mags' class="tab-pane fade">
                                                <div class="row-fluid">

                                                    <div class="span3">
                                                        <label class="control-label" for="">Annee</label>
                                                        <div class="controls ">
                                                            <select class="all-date" name="" id="">

                                                            </select>

                                                        </div>
                                                    </div>

                                                    <div class="control-group span3">
                                                        <label class="control-label">Region</label>
                                                        <div class="controls ">
                                                            <select name="" id="" class="region_mags">

                                                                <c:forEach items="${regions}" var="co">
                                                                    <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                                        <option value="${co.getIdRegion()}" selected>
                                                                            ${co.getNomRegion()}
                                                                        </option> 
                                                                    </c:if>
                                                                    <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                                        <option  value="${co.getIdRegion()}">
                                                                            ${co.getNomRegion()}
                                                                        </option> 
                                                                    </c:if>

                                                                </c:forEach>	
                                                            </select>	

                                                        </div>
                                                    </div>

                                                    <div class="control-group span3">
                                                        <label class="control-label">Magasin</label>
                                                        <div class="controls ">
                                                            <select name="magasin" id="stat-magasins" class="magasinS">

                                                            </select>	

                                                        </div>
                                                    </div>



                                                </div>
                                            </div>
                                            <div id='rapport_magp' class="tab-pane fade">
                                                <div class="row-fluid">

                                                    <div class="span3">
                                                        <label class="control-label" for="">Annee</label>
                                                        <div class="controls ">
                                                            <select class="all-date" name="" id="">

                                                            </select>

                                                        </div>
                                                    </div>

                                                    <div class="control-group span3">
                                                        <label class="control-label">Region</label>
                                                        <div class="controls ">
                                                            <select name="" id="" class="region_magp">

                                                                <c:forEach items="${regions}" var="co">
                                                                    <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                                        <option value="${co.getIdRegion()}" selected>
                                                                            ${co.getNomRegion()}
                                                                        </option> 
                                                                    </c:if>
                                                                    <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                                        <option  value="${co.getIdRegion()}">
                                                                            ${co.getNomRegion()}
                                                                        </option> 
                                                                    </c:if>

                                                                </c:forEach>	
                                                            </select>	

                                                        </div>
                                                    </div>

                                                    <div class="control-group span3">
                                                        <label class="control-label">Magasin</label>
                                                        <div class="controls ">
                                                            <select name="magasin" id="stat-magasinp" class="magasinP">

                                                            </select>	

                                                        </div>
                                                    </div>



                                                </div>
                                            </div>
                                            <div id='rapport_personnel' class="tab-pane fade">
                                                <div class="row-fluid">
                                                    <div class="span3">
                                                        <label class="control-label" for="">Annee</label>
                                                        <div class="controls ">
                                                            <select class="all-date" name="" id="">

                                                            </select>

                                                        </div>
                                                    </div>    

                                                    <div class="span3">
                                                        <label class="control-label" for="region">Region</label>
                                                        <div class="controls ">
                                                            <div class="input-append ">
                                                                <select class="region_service" name="" id="region_service">

                                                                    <c:forEach items="${regions}" var="co">
                                                                        <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                                            <option value="${co.getIdRegion()}" selected>
                                                                                ${co.getNomRegion()}
                                                                            </option> 
                                                                        </c:if>
                                                                        <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                                            <option  value="${co.getIdRegion()}">
                                                                                ${co.getNomRegion()}
                                                                            </option> 
                                                                        </c:if>

                                                                    </c:forEach>	
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="control-group span3">
                                                        <label for="stat-site" class="control-label">Site</label>
                                                        <div class="controls ">
                                                            <select name="" id="" class="input-lg site_service" >


                                                            </select>	

                                                        </div>
                                                    </div>
                                                    <div class="control-group span3">
                                                        <label class="control-label" for="service">Service</label>
                                                        <div class="controls ">
                                                            <select name="" id="" class="service form-control input-lg "  >

                                                            </select>	

                                                        </div>
                                                    </div>





                                                    <div class="control-group span3 " style="margin-left: 0px">
                                                        <label class="control-label" for="stat-service">personnel</label>
                                                        <div class="controls ">
                                                            <select name="personnel" id="stat-personnel" class="personnels form-control input-lg "  >

                                                            </select>	

                                                        </div>
                                                    </div>


                                                    <div class="span1  hidden">
                                                        <label class="control-label" for="">categorie</label>
                                                        <div class="controls ">
                                                            <div class="input-append ">
                                                                <input type="text" class="disabled hidden" name="categorie" value="${cat.getTypeCategorie()}"/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                            <div id='rapport_societe' class="tab-pane fade in ">
                                                <div class="row-fluid">


                                                    <div class="span3">
                                                        <label class="control-label" for="">Annee</label>
                                                        <div class="controls ">
                                                            <select class="all-date" name="" id="">

                                                            </select>

                                                        </div>
                                                    </div>
                                                    <div class="control-group span3">

                                                        <div class="controls ">
                                                            <a class="btn btn-primary" style="margin-top: 25px" id="stat-societe"><span class="icon icon-search"></span></a>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                            <div id='rapport_region' class="tab-pane fade in active">
                                                <div class="row-fluid">

                                                    <div class="span3">
                                                        <label class="control-label" for="">Annee</label>
                                                        <div class="controls ">
                                                            <select class="all-date" name="" id="">

                                                            </select>

                                                        </div>
                                                    </div>

                                                    <div class="control-group span2">
                                                        <label class="control-label">Region</label>
                                                        <div class="controls ">
                                                            <select name="region" id="stat-region">

                                                                <c:forEach items="${regions}" var="co">
                                                                    <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                                        <option value="${co.getIdRegion()}" selected>
                                                                            ${co.getNomRegion()}
                                                                        </option> 
                                                                    </c:if>
                                                                    <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                                        <option  value="${co.getIdRegion()}">
                                                                            ${co.getNomRegion()}
                                                                        </option> 
                                                                    </c:if>

                                                                </c:forEach>	
                                                            </select>	

                                                        </div>
                                                    </div>



                                                </div>
                                            </div>


                                            <div id='rapport_direction' class="tab-pane fade in ">
                                                <div class="row-fluid">
                                                    <div class="span3">
                                                        <label class="control-label" for="">Annee</label>
                                                        <div class="controls ">
                                                            <select class="all-date" name="" id="">

                                                            </select>

                                                        </div>
                                                    </div>
                                                    <div class="control-group span3">
                                                        <label class="control-label">Region</label>
                                                        <div class="controls ">
                                                            <select name="region" id="" class="region">

                                                                <c:forEach items="${regions}" var="co">
                                                                    <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                                        <option value="${co.getIdRegion()}" selected>
                                                                            ${co.getNomRegion()}
                                                                        </option> 
                                                                    </c:if>
                                                                    <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                                        <option  value="${co.getIdRegion()}">
                                                                            ${co.getNomRegion()}
                                                                        </option> 
                                                                    </c:if>

                                                                </c:forEach>	
                                                            </select>	

                                                        </div>
                                                    </div>
                                                    <div class="control-group span3">
                                                        <label for="stat-direction" class="control-label">Direction</label>
                                                        <div class="controls ">
                                                            <select name="region" id="stat-direction" class="input-lg direction" >


                                                            </select>	

                                                        </div>
                                                    </div>




                                                </div>
                                            </div>


                                            <div id='rapport_site' class="tab-pane fade in ">
                                                <div class="row-fluid">
                                                    <div class="span3">
                                                        <label class="control-label" for="">Annee</label>
                                                        <div class="controls ">
                                                            <select class="all-date" name="" id="">

                                                            </select>

                                                        </div>
                                                    </div>
                                                    <div class="control-group span3">
                                                        <label class="control-label">Region</label>
                                                        <div class="controls ">
                                                            <select name="region" id="" class="region_service">

                                                                <c:forEach items="${regions}" var="co">
                                                                    <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                                        <option value="${co.getIdRegion()}" selected>
                                                                            ${co.getNomRegion()}
                                                                        </option> 
                                                                    </c:if>
                                                                    <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                                        <option  value="${co.getIdRegion()}">
                                                                            ${co.getNomRegion()}
                                                                        </option> 
                                                                    </c:if>

                                                                </c:forEach>	
                                                            </select>	

                                                        </div>
                                                    </div>

                                                    <div class="control-group span3">
                                                        <label for="stat-site" class="control-label">Site</label>
                                                        <div class="controls ">
                                                            <select name="region" id="stat-site" class="input-lg site_service" >


                                                            </select>	

                                                        </div>
                                                    </div>



                                                </div>
                                            </div>


                                            <div id='rapport_service' class="tab-pane fade in ">
                                                <div class="row-fluid">
                                                    <div class="span3">
                                                        <label class="control-label" for="">Annee</label>
                                                        <div class="controls ">
                                                            <select class="all-date" name="" id="">

                                                            </select>

                                                        </div>
                                                    </div>
                                                    <div class="control-group span3">
                                                        <label class="control-label">Region</label>
                                                        <div class="controls ">
                                                            <select name="region" id="" class="region_service">

                                                                <c:forEach items="${regions}" var="co">
                                                                    <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                                        <option value="${co.getIdRegion()}" selected>
                                                                            ${co.getNomRegion()}
                                                                        </option> 
                                                                    </c:if>
                                                                    <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                                        <option  value="${co.getIdRegion()}">
                                                                            ${co.getNomRegion()}
                                                                        </option> 
                                                                    </c:if>

                                                                </c:forEach>	
                                                            </select>	

                                                        </div>
                                                    </div>

                                                    <div class="control-group span3">
                                                        <label for="stat-site" class="control-label">Site</label>
                                                        <div class="controls ">
                                                            <select name="region" id="" class="input-lg site_service" >


                                                            </select>	

                                                        </div>
                                                    </div>
                                                    <div class="control-group span3">
                                                        <label class="control-label" for="stat-service">Service</label>
                                                        <div class="controls ">
                                                            <select name="direction" id="stat-service" class="form-control input-lg service"  >

                                                            </select>	

                                                        </div>
                                                    </div>


                                                </div>
                                            </div>
                                        </div>
                                    </div>



                                    <div class="row-fluid">
                                        <div class="span12">
                                            <!-- BEGIN CHART PORTLET-->



                                            <div id="liness" height="400" width="1100"></div>
                                            <div class="space20"></div>
                                            <div class="space20"></div>

                                            <hr>



                                            <!-- END CHART PORTLET <div id="barchar" height="400" width="1100"></div> -->
                                        </div>
                                    </div>


                                </div>
                            </div>

                        </div>
                    </div>

                </div>

            </div>


            <div class="space20"></div>

        </div>
    </div>
</div>


