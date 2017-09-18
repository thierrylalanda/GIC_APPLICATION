<%-- 
    Document   : Agenda
    Created on : 25 juil. 2017, 10:47:37
    Author     : Administrateur
--%>



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
    Espace Agenda
</h3>
<ul class="breadcrumb">
    <li>

        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i>Accueil</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#"><i class="icon-group"></i> Agenda</a>

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
    <div class="span3">

        <div class="widget yellow">
            <div class="widget-title">
                <h4><i class="icon-calendar"></i>Liste des notes</h4>
            </div>
            <div class="widget-body">
                <div id='external-events'>

                    <p>
                        <input type='text' placeholder="ajouter une note" id="new_work" maxlength="25" style="max-width: 150px" />
                    </p>
                    <p>
                        <input type='checkbox' id='drop-remove' />
                        <label for="drop-remove"> retirer apres insertion</label>
                    </p>
                </div>
            </div>
        </div>


    </div>
    <div class="span9 responsive" data-tablet="span9 fix-margin" data-desktop="span9">
        <!-- BEGIN CALENDAR PORTLET-->
        <div class="widget yellow">
            <div class="widget-title">
                <h4><i class="icon-calendar"></i>Agenda</h4>
                <span class="tools">
                    <a href="javascript:;" class="icon-chevron-down"></a>
                    <a href="javascript:;" class="icon-remove"></a>
                </span>
            </div>
            <div class="widget-body">
                <div id="agenda" class="has-toolbar"></div>
            </div>
        </div>
        <!-- END CALENDAR PORTLET-->
    </div>
</div>





