<%-- 
    Document   : ConfirmUser
    Created on : 19 juil. 2017, 07:43:37
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="deleteCategorie" class="modal hide fade" data-backdrop="false" tabindex="-1" role="dialog" aria-labelledby="ModalLabel3" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="ModalLabel3">selectionner la categorie a supprimer</h3>
    </div>
    <div class="modal-body">
        <form class="cmxform form-horizontal" id="delCat" method="post" action="">


            <div class="control-group form-group">
                <label class="control-label label-categorie">categories a supprimer</label>
                <div class="controls " id="">
                    <select name="id_categorie"   id="allcategorieMag"   class="form-control catetodelete">

                    </select>
                </div>
            </div>

            <div class="control-group article-hidden hidden">

                <label class="control-label">article a supprimer</label>
                <div class="controls ff">

                    <select  class="form-control exampleSelect" id="articlestodelete" name="idarticle" required>

                    </select>
                </div>
            </div>
            <br>
            <div class="form-actions">
                <button class="btn btn-success confirmDelCat" name="" id="confirmation"type="">Supprimer</button>
                <button class="btn btn-success confirmDelart hidden" name="" id="confirmationart"type="">Supprimer article</button>
            </div>

        </form>
    </div>
    <div class="modal-footer">

        <button class="btn close-modal" data-dismiss="modal" aria-hidden="true">annuler</button>

    </div>
</div>
<script src="admin/js/jquery-1.8.3.min.js"></script>
<script src="js/myjs/controllers/addCategorie.js" type="text/javascript"></script>