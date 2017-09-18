<%-- 
    Document   : ConfirmUser
    Created on : 19 juil. 2017, 07:43:37
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="myModal3" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel3">Confirmer Votre Identité</h3>
    </div>
    <div class="modal-body">

        <div class="alert alert-block alert-error alerts fade in">
            <button data-dismiss="alert" class="close" type="button">×</button>
            <h4 class="alert-heading">Error!</h4>
            <p>
                Vos informations sont incorrectes
            </p>
        </div>
        <form class="cmxform form-horizontal" id="ConfirmForm" method="post" action="">

            <div class="control-group ">
                <label for="username" class="control-label">User Name</label>
                <div class="controls">
                    <input class="span6 " id="username" name="user" type="text" />
                </div>
            </div>
            <div class="control-group ">
                <label for="password" class="control-label">Password</label>
                <div class="controls">
                    <input class="span6 " id="password" name="password" type="password" />
                </div>
            </div>
            <div class="form-actions">
                <button class="btn btn-success confirm" type="">Confirmer</button>
            </div>

        </form>
    </div>
    <div class="modal-footer">

        <button class="btn" data-dismiss="modal" aria-hidden="true">Annuler</button>

    </div>
</div>
<script src="js/myjs/controllers/ConfirmUser.js" type="text/javascript"></script>