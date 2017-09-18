
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="page-title">
    Notifications
</h3>
<ul class="breadcrumb">
    <li>
        <a href="#"><i class="icon-home"></i> Accueil</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a title="" href="admin?vue=magP&action=getAllMagP" class="" >Notification</a>  
        <span class="divider">/</span>
    </li>
    <li class="active">
        Commandes Du Magasin
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
                    <div class="span4">
                        <h1>${sessionScope.personnel.getLoginList().getUsername()}</h1>
                    <p>service <a href="#">${sessionScope.personnel.getService().getNomService()}</a></p>
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

                                            <ul class="alt_content " style="list-style: none">

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

                                            <ul class="alt_content list-unstyled" style="list-style: none">

                                                <c:forEach items="${allNotifications}" var="notif">
                                                    <li class="">
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

