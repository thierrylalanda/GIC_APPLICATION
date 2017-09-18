<%-- 
    Document   : ConfirmUser
    Created on : 19 juil. 2017, 07:43:37
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="addCategorie" class="modal hide fade" tabindex="-1" data-backdrop="false" role="dialog" aria-labelledby="ModalLabel3" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="ModalLabel3">nouvelle categorie</h3>
    </div>
    <div class="modal-body">
        <form class="cmxform form-horizontal" id="addCat" method="post" action="">

            <div class="control-group form-group">
                <label class="control-label">categories existantes</label>
                <div class="controls fff" id="">
                    <select name="allcategorie"  multiple="multiple" id="allcategorieNotMag"   class="form-control exampleSelect">

                    </select>
                </div>
            </div>
            <br>
            <div class="form-actions">
                <button class="btn btn-success confirmCat" name="" type="">ajouter</button>
            </div>

        </form>
    </div>
    <div class="modal-footer">

        <button class="btn close-modal" data-dismiss="modal" aria-hidden="true">annuler</button>

    </div>
</div>
