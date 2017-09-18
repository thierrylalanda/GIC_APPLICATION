<%-- 
    Document   : sendmessage
    Created on : 14 avr. 2017, 16:40:40
    Author     : messi
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<h3 class="page-title">
    Envoyer Un E-Mail
</h3>
<ul class="breadcrumb">
    <li>
        <a href="RedirectionVue?vue=Accueil"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">E-Mail</a>

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
    <div class="span12">
        <!-- BEGIN  widget-->
        <div class="widget black">
            <div class="widget-title">
                <h4><i class="icon-reorder"></i> Envoyer Un Mail</h4>
                <span class="tools">
                    <a href="javascript:;" class="icon-chevron-down"></a>

                </span>
            </div>
            <div class="widget-body form">
                <!-- BEGIN FORM-->
                <form action="#" class="form-horizontal" method="get">
                    <div class="row-fluid">
                        <div class="control-group">

                            Recepteur  <input type="text" class=" form-control  span6 tags" name="to" />
                        </div>
                    </div>
                    <div class="space20"></div>
                    <div class="row-fluid">
                        <div class="control-group span12">

                            Copy  <input type="text" class=" form-control span6 tags" placeholder="Mettre Quelqu'un En Copy"  name="copy"style="margin-right: 50px;margin-left: 30px" />


                            Copy <input type="text" class=" form-control span4 pull-righ tags"placeholder="Mettre Quelqu'un En Copy" name="copy2"  />
                        </div>
                    </div>
                    <div class="space20"></div>
                    <div class="row-fluid">
                        <c:if test="${not empty message}">

                            <strong  class="alert alert-danger  text-center span5 pull-right ">${message.getMessage()}</strong>

                        </c:if>
                        Objet  <input type="text" class=" form-control  span6"  name="subject" placeholder="Objet Du Mail" style="margin-right: 50px;margin-left: 30px"/>
                        Password <input type="password" autocomplete="off" class=" form-control span4 pull-righ"placeholder="Mot De Passe De Votre Compte Outlook" name="password"  />
                    </div>
                    <div class="space20"></div>
                    <div class="control-group">


                        <textarea class="span12 wysihtmleditor5" name="message" rows="5"></textarea>

                    </div>

                    <hr>
                    <button type="reset" class="btn btn-danger btn-lg  pull-righ "style="margin-left: 50px;margin-right: 50px" >Cancel</button>
                    <button type="submit" class="btn btn-success btn-lg btn-primary pull-righ "style="margin-left: 50px" >Envoyer</button>
                </form>
                <!-- END FORM-->
            </div>
        </div>
        <!-- END EXTRAS widget-->
    </div>
</div>

