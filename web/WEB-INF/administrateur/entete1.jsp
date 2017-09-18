<%-- 
    Document   : entete1
    Created on : 12 juin 2017, 11:04:14
    Author     : lalanda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!-- BEGIN HEADER -->
<div id="header" class="navbar navbar-inverse navbar-fixed-top">
    <!-- BEGIN TOP NAVIGATION BAR -->
    <div class="navbar-inner">
        <div class="container-fluid">
            <!--BEGIN SIDEBAR TOGGLE-->
            <div class="sidebar-toggle-box hidden-phone">
                <div class="icon-reorder tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
            </div>
            <!--END SIDEBAR TOGGLE-->
            <!-- BEGIN LOGO -->
            <a class="brand" href="#" >
                <img alt="Brasseries Du Cameroun" src="photo/${sessionScope.societe.getLogo()}" style="height: 40px; width: 160px; margin-top: -10px">
            </a>
            <!-- END LOGO -->
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->
            <a class="btn btn-navbar collapsed" id="main_menu_trigger" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="arrow"></span>
            </a>
            <!-- END RESPONSIVE MENU TOGGLER -->
            <div id="top_menu" class="nav notify-row">
                <!-- BEGIN NOTIFICATION -->
                <ul class="nav top-menu">
                    <!-- BEGIN SETTINGS -->
                    <!-- END SETTINGS -->
                    <!-- BEGIN INBOX DROPDOWN -->
                    <li class="dropdown" id="header_inbox_bar">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-envelope-alt"></i>
                            <span class="badge badge-important size-message">0</span>
                        </a>
                        <ul class="dropdown-menu extended inbox message-list " name="${personnel.getIdPersonnel()}">
                        </ul>
                    </li>
                    <!-- END INBOX DROPDOWN -->
                    <!-- BEGIN NOTIFICATION DROPDOWN -->
                    <li class="dropdown" id="header_notification_bar">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                            <i class="icon-bell-alt"></i>
                            <span class="badge badge-warning nombreNotifs"></span>
                        </a>
                        <ul class="dropdown-menu extended notification" id="${personnel.getIdPersonnel()}">
                        </ul>
                    </li>
                    <!-- END NOTIFICATION DROPDOWN -->

                </ul>
            </div>
            <!-- END  NOTIFICATION -->
            <div class="top-nav ">
                <ul class="nav pull-right top-menu" >
                    <!-- BEGIN SUPPORT -->
                    <li class="dropdown mtop5">

                        <a class="dropdown-toggle element" data-placement="bottom" data-toggle="tooltip" href="#" data-original-title="Chat">
                            <i class="icon-comments-alt"></i>
                        </a>
                    </li>
                    <c:if test="${not empty sessionScope.lien61 or sessionScope.GeneralAdministrateur == 'OK'}">
                        <li class="dropdown mtop5">
                            <a class="dropdown-toggle element" data-placement="bottom" data-toggle="tooltip" href="admin?vue=Contact&action=getAll" data-original-title="Envoyer Un Mail">
                                <i class="icon-mail-forward"></i>
                            </a>
                        </li>
                    </c:if>
                    <!-- END SUPPORT -->
                    <!-- BEGIN USER LOGIN DROPDOWN -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                            <span class="icon-user"> ${sessionScope.personnel.getNomPrenom()} </span>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu extended logout">
                            <c:if test="${not empty sessionScope.lien49 or sessionScope.GeneralAdministrateur == 'OK'}">
                                <li><a href="admin?vue=profile&action=profileAdmin"><i class="icon-user"></i> Mon Profile</a></li> 
                                </c:if>
                                <c:if test="${not empty sessionScope.lien50 or sessionScope.GeneralAdministrateur == 'OK'}">
                                <li><a href="authentification?action=faut"><i class="icon-off"></i> Deconnexion</a></li>
                                </c:if>                         
                        </ul>
                    </li>
                    <!-- END USER LOGIN DROPDOWN -->
                </ul>
                <!-- END TOP NAVIGATION MENU -->
            </div>
        </div>
    </div>
    <!-- END TOP NAVIGATION BAR -->
</div>
<!-- BEGIN TAB PORTLET-->






