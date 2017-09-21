
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!-- BEGIN INLINE TABS PORTLET-->
<div class="widget orange">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i> Rapport du magasin</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">




        <form action="parametre?action=getRepporting&vue=rien" class="non-region hidden-phone" id_magasin="${categories.get(0).getMagasinSecondaire().getIdMagasin()}">   
            <label id="nom_magasin" class="hidden">${categories.get(0).getMagasinSecondaire().getNomMagasin()}</label>
            <div class="control-group span3">
                <label class="control-label">Categorie</label>
                <div class="controls ">
                    <select name="categorie" id="" class="">
                        <c:forEach items="${categories}" var="cate">
                            <option value="${cate.getIdCategorie()}">${cate.getNomCategorie()}</option>
                        </c:forEach>
                    </select>	

                </div>
            </div>

            <div class="span1">
                <label class="control-label">Tous</label>
                <div class="controls ">
                    <input type="radio" name="choix" value="tous" checked/>

                </div>

            </div>
            <div class="span1">

                <label class="control-label">Personnel</label>
                <div class="controls ">
                    <input type="radio" name="choix" value="non"/>

                </div>
            </div>
            <div class="input-append input-prepend  ">
                <label class="control-label">Interval</label>
                <input class="reservation" name="interval" id="" placeholder="interval de temps" type="text">
                <button class="btn btn-primary search-catego" type="submit"><i class="icon-search"></i> </button>
            </div>



        </form>




        <c:if test="${var==true}">
            <table titre="raport sur les ${cat.getNomCategorie()}" class="table table-hover table-responsive table-bordered table-rapport " categori="${cat.getIdCategorie()}"cellspacing="0" width="100%">
                <thead >
                    <tr>
                        <th>Articles</th>

                        <th>QUANTITE</th>
                        <th>Prix Total</th>



                    </tr>
                </thead>

                <tbody class="stat-perso">


                </tbody>
                <tfoot style="color: #2fade7">

                </tfoot>
            </table>
        </c:if>
        <a class="btn btn-inverse   hidden-print print-pdf " style="margin-left: 400px;margin-bottom: 10px;border-radius: 5px">Imprimer <i class="icon-print icon-big"></i></a>





    </div>
</div>