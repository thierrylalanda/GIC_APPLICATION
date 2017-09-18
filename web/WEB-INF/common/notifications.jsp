
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- BEGIN HEADER -->


<!-- BEGIN TAB PORTLET-->


<!-- BEGIN PAGE CONTAINER-->

<div class="span9">


    <!-- END THEME CUSTOMIZER-->
    <!-- BEGIN PAGE TITLE & BREADCRUMB-->





    <div class="row-fluid">
        <!-- BEGIN PROFILE PORTLET-->
        <div class=" profile span12">
            <div class="span2">
                <a href="#" class="profile-features ">
                    <i class=" icon-user"></i>
                    <p class="info">vous</p>
                </a>

                <a href="notifications?action=getAllNotifications&vue=notification&recepteur=${sessionScope.id}" class="profile-features <c:if test="${one=='one' ||all=='all'}">active</c:if>" >
                        <i class=" icon-bell-alt"></i>
                        <p class="info">NOTIFICATIONS</p>
                    </a>

                </div>
                <div class="span10">
                    <div class="profile-head">
                        <div class="span8">
                            <h1>${sessionScope.personnel.getNomPrenom()}</h1>
                        <p>${personnel.getFonction()} Aux <a href="#">Brasseries Du Cameroun</a></p>
                    </div>

                    <div class="span4">
                        <ul class="social-link-pf">
                            <li><a href="#">
                                    <i class="icon-facebook"></i>
                                </a></li>
                            <li><a href="#">
                                    <i class="icon-twitter"></i>
                                </a></li>
                            <li><a href="#">
                                    <i class="icon-linkedin"></i>
                                </a></li>
                        </ul>
                    </div>
                </div>
                <div class="space15"></div>
                <div class="row-fluid">


                    <div class="span12 bio">
                        <div class="tab-content">
                            <div class="tab-pane fade in <c:if test="${allM=='all'}">active</c:if>" id="messages">
                                <c:if test="${allM=='all'}">
                                    <div class="widget blue">
                                        <div class="widget-title">
                                            <h4><i class="icon-bar-chart"></i> Messages</h4>
                                            <span class="tools">
                                                <a href="javascript:;" class="icon-chevron-down"></a>
                                                <a href="javascript:;" class="icon-remove"></a>
                                            </span>
                                        </div>

                                        <div class="widget-body "  id="paging_container3">
                                            <div class="alt_page_navigation"></div>

                                            <ul class="alt_content list-unstyled">

                                                <c:forEach items="${allMessages}" var="notifi">
                                                    <li>
                                                        <div class="alert alert-block alert-info fade in">
                                                            <button data-dismiss="alert" class="close" type="button">×</button>

                                                            <p class="alert-heading">
                                                                <c:if test="${notifi.getExpediteurMessage().getIdPersonnel()==sessionScope.id}" >
                                                                    vous
                                                                </c:if>
                                                                <c:if test="${notifi.getExpediteurMessage().getIdPersonnel()!=sessionScope.id}" >
                                                                    ${notifi.getExpediteurMessage().getNomPrenom()}
                                                                </c:if>

                                                                <strong class="pull-right">envoye le ${notifi.getTimeMessage().getDate()}/${notifi.getTimeMessage().getMonth()}/${notifi.getTimeMessage().getYear()} </strong>
                                                            </p>


                                                            <p>
                                                            <h5> <em> ${notifi.getTextMessage()}</em></h5>
                                                            </p><br>
                                                            <c:if test="${notifi.getExpediteurMessage().getIdPersonnel()!=sessionScope.id}" >
                                                                <a href="#" class="" onclick="getMessagesExpediteur(${notifi.getExpediteurMessage().getIdPersonnel()},${notifi.getRecepteurMessage().getIdPersonnel()})">repondre</a>


                                                            </c:if>
                                                        </div>
                                                    </li>
                                                </c:forEach>
                                            </ul> 
                                            <div class="alt_page_navigation"></div>
                                        </div>
                                    </div>

                                </c:if>

                            </div>
                            <div class="tab-pane fade in <c:if test="${one=='one' ||all=='all'}">active</c:if>" id="notifications">

                                    <ul class="activities">
                                    <c:if test="${one=='one'}">


                                        <div class="widget blue">
                                            <div class="widget-title">
                                                <h4><i class="icon-bar-chart"></i> Notifications de Mr ${expediteur.getNomPrenom()}</h4>
                                                <span class="tools">
                                                    <a href="javascript:;" class="icon-chevron-down"></a>
                                                    <a href="javascript:;" class="icon-remove"></a>
                                                </span>
                                            </div>
                                            <div class="widget-body" id="paging_container3">

                                                <div class="alt_page_navigation"></div>

                                                <ul class="alt_content list-unstyled">

                                                    <c:forEach items="${newNotifications}" var="notif">
                                                        <li>
                                                            <div class="alert alert-block alert-info fade in">

                                                                <h4 class="alert-heading">
                                                                    <c:if test="${notif.getExpediteur()==sessionScope.id}" >
                                                                        vous
                                                                    </c:if>
                                                                    <c:if test="${notif.getExpediteur()!=sessionScope.id}" >
                                                                        ${expediteur.getNomPrenom()}
                                                                    </c:if>
                                                                    <em class="pull-right">envoye le ${notif.getDate()} </em>
                                                                </h4>


                                                                <p>
                                                                <h5> <em> ${notif.getMessage()}</em></h5>
                                                                </p><br>
                                                            </div>
                                                        </li> 
                                                    </c:forEach>

                                                </ul>    
                                                <div class="alt_page_navigation"></div>



                                            </div>
                                        </div>

                                    </c:if>

                                    <c:if test="${all=='all'}">
                                        <div class="widget blue">
                                            <div class="widget-title">
                                                <h4><i class="icon-bar-chart"></i> Notifications </h4>
                                                <span class="tools">
                                                    <a href="javascript:;" class="icon-chevron-down"></a>
                                                    <a href="javascript:;" class="icon-remove"></a>
                                                </span>
                                            </div>
                                            <div class="widget-body " id="paging_container3">

                                                <h2>Toutes Vos Notifications</h2>
                                                <div class="alt_page_navigation"></div>

                                                <ul class="alt_content list-unstyled">

                                                    <c:forEach items="${allNotifications}" var="notif">
                                                        <li>
                                                            <div class="alert alert-block alert-info fade in">

                                                                <p class="alert-heading">
                                                                    <c:if test="${notif.getExpediteur()==sessionScope.id}" >
                                                                        vous
                                                                    </c:if>
                                                                    <c:if test="${notif.getExpediteur()!=sessionScope.id}" >
                                                                        ${expediteur.getNomPrenom()}
                                                                    </c:if>
                                                                    <span class="pull-right">envoye le ${notif.getDate()} </span>
                                                                </p>
                                                                <p>
                                                                    ${notif.getMessage()}
                                                                </p><br>
                                                            </div>
                                                        </li>
                                                    </c:forEach>




                                                </ul>	
                                                <div class="pagination pagination-small pagination-centered">
                                                    <ul>
                                                        <li><a href="#">«</a></li>
                                                        <li><a href="#">1</a></li>
                                                        <li><a href="#">2</a></li>
                                                        <li><a href="#">3</a></li>
                                                        <li><a href="#">4</a></li>
                                                        <li><a href="#">»</a></li>
                                                    </ul>
                                                </div>
                                                <div class="alt_page_navigation"></div>
                                            </div>
                                        </div>
                                    </c:if>

                                </ul>

                                <div class="space20"></div>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- END PROFILE PORTLET-->
    </div>
</div>
