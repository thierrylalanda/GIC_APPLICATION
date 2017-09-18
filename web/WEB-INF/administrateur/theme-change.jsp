<%-- 
    Document   : theme-change
    Created on : 14 juin 2017, 12:18:59
    Author     : messi
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${not empty sessionScope.lien51 or sessionScope.GeneralAdministrateur == 'OK'}">
    <div id="theme-change" class="hidden-phone">
        <i class="icon-cogs"></i>
        <span class="settings">
            <span class="text">Theme Color:</span>
            <span class="colors">
                <span class="color-yellow" data-style="yellow"></span>
                <span class="color-default" data-style="default"></span>
                <span class="color-green" data-style="green"></span>
                <span class="color-gray" data-style="gray"></span>
                <span class="color-purple" data-style="purple"></span>
                <span class="color-red" data-style="red"></span>
            </span>
        </span>
    </div>
</c:if>