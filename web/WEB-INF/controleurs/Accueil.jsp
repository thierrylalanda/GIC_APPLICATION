<%-- 
    Document   : Accueil
    Created on : 14 janv. 2017, 22:42:37
    Author     : lalanda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<META http-equiv="refresh" content="<%= session.getMaxInactiveInterval()%>; url=/GCI_APPLICATION/login.jsp"/>

<!-- BEGIN SAMPLE FORMPORTLET-->

<div class="span8 contain">

    <div class="span12">
        <p>Presentation de l'entreprise</p>

        <p>
        <div id="gallery-wrapper">

        </div>
        Lorem ipsum dolor sit amet, consectetur adipisicing  elit, sed do eiusmod tempor incididunt ut labore et
        dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
        ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
        fugiat nulla pariatur.</p><p class="text-success">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
            ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
            fugiat nulla pariatur.</p>
    </div>

    <div class="span12" style="padding:0px; margin:0px; background-color:#fff;font-family:'Open Sans',sans-serif,arial,helvetica,verdana">
        <c:import url="/WEB-INF/common/carrousel.jsp"/>
    </div>
    <h4>Presentation Argumente de l'entreprise</h4>
    <div class="span12">
        <div class="span3">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing  elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                fugiat nulla pariatur.</p>
        </div>
        <div class="span3">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing  elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                fugiat nulla pariatur.</p>
        </div>
        <div class="span3">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing  elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                fugiat nulla pariatur.</p>
        </div>
        <div class="span3">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing  elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                fugiat nulla pariatur.</p>
        </div>
    </div>

    <div class="span12">
        <div class="span6">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing  elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                fugiat nulla pariatur.</p>
        </div>
        <div class="span6">
            <div class="span12">
                <div class="span4">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing  elit, sed do eiusmod tempor incididunt ut labore et
                        dolore magna aliqua. Ut .</p>
                </div>
                <div class="span4">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing  elit, sed do eiusmod tempor incididunt ut labore et
                        dolore magna aliqua. Ut .</p>
                </div>
                <div class="span4">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing  elit, sed do eiusmod tempor incididunt ut labore et
                        dolore magna aliqua. Ut.</p>
                </div>
            </div>
            <div class="span12">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing  elit, sed do eiusmod tempor incididunt ut labore et
                    dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                    ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                    fugiat nulla pariatur.</p>
            </div>
        </div>
    </div>
</div>
<div class="span3 widgets pull-right ">
    <div class="span12">

    </div>
    <h5>service support et helpdesk </h5>
    <div class="carousel slide span12 box" style="">
        <div class="carousel-inner">
            <div class="item active">
                <img src="image/SI_001.jpg" width="260px" height="215px" class="img-responsive img-rounded" /><br/>
            </div>

            <div class="item">
                <img src="image/SI_002.jpg" width="260px" height="215px" class="img-responsive img-rounded" /><br/>
            </div>
            <div class="item">
                <img src="image/SI_003.jpg" width="260px" height="215px" class="img-responsive img-rounded" /><br/>
            </div>
            <div class="item">
                <img src="image/SI_004.jpg" width="260px" height="215px" class="img-responsive img-rounded" /><br/>
            </div>

            <div class="item">
                <img src="image/SI_006.jpg" width="260px" height="215px" class="img-responsive img-rounded" /><br/>
            </div>
            <div class="item">
                <img src="image/SI_007.jpg" width="260px" height="215px" class="img-responsive img-rounded" /><br/>
            </div>
            <div class="item">
                <img src="image/SI_008.jpg" width="260px" height="215px" class="img-responsive img-rounded" /><br/>
            </div>
            <div class="item">
                <img src="image/SI_009.jpg" width="260px" height="215px" class="img-responsive img-rounded" /><br/>
            </div>
            <div class="item">
                <img src="image/SI_010.jpg" width="260px" height="215px" class="img-responsive img-rounded" /><br/>
            </div>
            <div class="item">
                <img src="image/SI_011.jpg" width="260px" height="215px" class="img-responsive img-rounded" /><br/>
            </div>
        </div>

    </div>

    <div class="span12">
        <c:if test="${sessionScope.agenda.size()!=0}">


            <a  href="ValiderCommande?vue=agenda&action=autre" class="btn btn-danger popovers"   data-trigger="hover" data-placement="top" data-content="consulter votre Agenda vous avez quelque chose de prevue aujourd'hui" data-original-title="alarm" id="pulsate-regular">
                <span class='icon icon-calendar' ></span> Mon Agenda
            </a>
            <div class="space20"></div>
        </c:if>
        <c:if test="${sessionScope.agenda.size()==0}">


            <a  href="ValiderCommande?vue=agenda&action=autre" class="btn btn-primary popovers"   data-trigger="hover" data-placement="top" data-content="rien  de prevue aujourd'hui" data-original-title="alarm" id="pulsate-regular">
                <span class='icon icon-calendar' ></span> Mon Agenda
            </a>
            <div class="space20"></div>
        </c:if>


    </div>
    <div class="span12 ">

        <h4>Agenda</h4>



        <div id="exempletest" class="cal"></div>


    </div>



</div> 


