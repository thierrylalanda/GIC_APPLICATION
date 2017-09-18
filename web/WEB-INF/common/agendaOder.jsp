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

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
    <!-- BEGIN HEAD -->

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



