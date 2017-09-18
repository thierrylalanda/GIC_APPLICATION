/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getPages(serveur, param, idResponse) {
    $.post(
            serveur,
            param,
            function (data) {

                $(".exampleSelect").html("");
                $(idResponse).html("");
                sessionStorage.setItem("data", data);
                var k;
                for (var i = 0; i < data.length; i++) {

                    if (data[i][0] !== 99) {
                        $(idResponse).append("<option value=" + data[i][0] + " >" + data[i][1] + "</option>");
                    } else {
                        k = i;
                        break;
                    }
                }
                for (k = i + 1; k < data.length; k++) {
                    $(idResponse).append("<option  value=" + data[k][0] + " >" + data[k][1] + "</option>");
                }
                $(".fff").html($(idResponse));
                $(".exampleSelect").attr('multiple', 'multiple');
                //  $(".example").multiselect();
                //  $(".example").multiselect('rebuild');
                $('.exampleSelect').multiselect({
                    enableFiltering: true,
                    maxHeight: 200,
                    buttonClass: 'btn-primary btn',
                    buttonWidth: '290px',
                    //selectAllText: true,
                    includeSelectAllOption: true,
                    enableCaseInsensitiveFiltering: true,
                    filterPlaceholder: 'Recherhe',
                    // selectAllValue: 'multiselect-all',
                    // selectedClass: null,
                    // dropRight: true,
                    //dropUp: true,
                    buttonText: function (options, select) {
                        if (options.length === 0) {
                            return 'Aucune categorie Selectionner ...';
                        } else if (options.length > 4) {
                            return 'plus de 4 categories selectionner!';
                        } else {
                            var labels = [];
                            options.each(function () {
                                if ($(this).attr('label') !== undefined) {
                                    labels.push($(this).attr('label'));
                                } else {
                                    labels.push($(this).html());
                                }
                            });
                            return labels.join(', ') + '';
                        }
                    }

                });
            }
    ,
            "json"
            );
}


$(document).ready(function () {

    var idmag = 0;
//ajouter une categorie
    $(".addcategoriebtn").click(function (e) {
        e.preventDefault();
        idmag = $(this).attr("name");
        $(".confirmCat").attr("name", "mags");
        if (idmag === null) {
            $(".close-modal").trigger("click");
            $.gritter.add({
                title: 'attention',
                text: 'choisir au prealable un magasin',
                sticky: false
            });
        } else {

            var param = {action: "allCatNotMag", vue: "vue", id_magasin: idmag};
            getPages("admin", param, "#allcategorieNotMag");
            $(this).attr("href", "#addCategorie");
        }


    });
    $(".confirmCat").click(function (e) {
        e.preventDefault();
        var nomcat = $("#cate").val();
        var form = $("#addCat");
        var url = "";
        var allcat = $("#allcategorieNotMag").val();
        if ($(".confirmCat").attr("name") === "mags") {
            url = "admin?action=addCategorieForMS&id_Magasin=" + idmag;
        } else {
            url = "admin?action=addCategorieForMP&id_Magasin=" + idmag;
        }
        $.ajax({
            url: url,
            data: $(form).serialize(),
            type: $(form).attr("method"),
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if (data.length !== 0) {

                    $("#example-getting-starte").html("");
                    for (var l = 0; l < data.length; l++) {
                        $("#example-getting-starte").append("<option value=" + data[l][0] + ">" + data[l][1] + "</option>");
                    }

                    $(".close-modal").trigger("click");
                    $.gritter.add({
                        title: 'success',
                        text: 'categorie ajouter avec success ajouter',
                        sticky: false
                    });
                } else {

                    $.gritter.add({
                        title: 'error',
                        text: 'impossible de creer cette categorie entrez un nom valide',
                        sticky: false
                    });
                }

            }
        });
    });
    //pour supprimer une categorie d'un magasin

    $(".deletecategoriebtn").click(function (e) {
        e.preventDefault();
        idmag = $(this).attr("name");
        $(".article-hidden").addClass("hidden");
        if ($(".confirmDelCat").hasClass("hidden")) {
            $(".confirmDelCat").removeClass("hidden");

            $(".confirmDelart").addClass("hidden");
        }

        $("#confirmation").attr("name", "mags");
        if (idmag === null) {
            $(".close-modal").trigger("click");
            $.gritter.add({
                title: 'attention',
                text: 'choisir au prealable un magasin',
                sticky: false
            });
        } else {
            $("#allcategorieMag").html($("#example-getting-starte").html());
            var param = {action: "allCatMag", vue: "vue", id_magasin: idmag};
            //getPages("admin", param, "#allcategorieMag");
            $(this).attr("href", "#deleteCategorie");
        }


    });


    $(".confirmDelCat").click(function (e) {
        e.preventDefault();
        //var nomcat = $("#cate").val();
        var form = $("#delCat");
        var url = "";
        //var allcat = $("#allcategorieMag").val();
        if ($(".confirmDelCat").attr("name") === "mags") {

            url = "admin?action=deleteCategorieForMS&id_Magasin=" + idmag;
        } else {
            url = "admin?action=deleteCategorieForMP&id_Magasin=" + idmag;
        }
        $.ajax({
            url: url,
            data: $(form).serialize(),
            type: $(form).attr("method"),
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if (data.length !== 0) {

                    $("#example-getting-starte").html("");
                    for (var l = 0; l < data.length; l++) {
                        $("#example-getting-starte").append("<option value=" + data[l][0] + ">" + data[l][1] + "</option>");
                    }

                    $(".close-modal").trigger("click");
                    $.gritter.add({
                        title: 'success',
                        text: 'categorie suprimer avec success ajouter',
                        sticky: false
                    });
                } else {

                    $.gritter.add({
                        title: 'error',
                        text: 'impossible de creer cette categorie entrez un nom valide',
                        sticky: false
                    });
                }

            }
        });
    });



// ajouter les categories dans un magasin principal
    $(".addcategoriebtnMP").click(function (e) {
        e.preventDefault();
        idmag = $(this).attr("name");
        $(".confirmCat").attr("name", "magp");
        if (idmag === null) {
            $(".close-modal").trigger("click");
            $.gritter.add({
                title: 'attention',
                text: 'choisir au prealable un magasin',
                sticky: false
            });
        } else {
            //$("#allcategorieMag").html($("#example-getting-starte").html());
            var param = {action: "allCatNotMagMP", vue: "vue", id_magasin: idmag};
            getPages("admin", param, "#allcategorieNotMag");
            $(this).attr("href", "#addCategorie");
        }


    });

//suppression pour un magasin principal

    $(".deletecategoriebtnMP").click(function (e) {
        e.preventDefault();
        idmag = $(this).attr("name");

        if ($(".confirmDelCat").hasClass("hidden")) {
            $(".confirmDelCat").removeClass("hidden");
            $("#confirmation").attr("name", "magp");
            $(".confirmDelart").addClass("hidden");
        }
        $(".article-hidden").addClass("hidden");

        if (idmag === null) {
            $(".close-modal").trigger("click");
            $.gritter.add({
                title: 'attention',
                text: 'choisir au prealable un magasin',
                sticky: false
            });
        } else {
            $("#allcategorieMag").html($("#example-getting-starte").html());
            var param = {action: "allCatMagMP", vue: "vue", id_magasin: idmag};
            //getPages("admin", param, "#allcategorieMag");
            $(this).attr("href", "#deleteCategorie");
        }


    });
    // supprimer un article d'une categorie d'un magasin principal
    $(".deletearticlebtnMP").click(function (e) {
        e.preventDefault();

        idmag = $(this).attr("name");


        if ($(".confirmDelart").hasClass("hidden")) {
            $("#confirmation").addClass("hidden");
            $(".confirmDelart").removeClass("hidden").attr("name", "magp");
            $(".catetodelete").removeAttr("id").attr({id: "allcategorieMagMP"});

        }
        $(".catetodelete").attr({id: "allcategorieMagMP"});
        $(".article-hidden").removeClass("hidden");
        if (idmag === null) {
            $(".close-modal").trigger("click");
            $.gritter.add({
                title: 'attention',
                text: 'choisir au prealable un magasin',
                sticky: false
            });
        } else {
            $("#allcategorieMagMP").html($("#example-getting-starte").html());
            var param = {action: "allCatMagMP", vue: "vue", id_magasin: idmag};
            //getPages("admin", param, "#allcategorieMag");
            $(this).attr("href", "#deleteCategorie");
        }


    });

    // obtenir les articles d'une categorie quelque soit le magasin

    $('.catetodelete').click(function (e) {
        e.preventDefault();
        var valeur = $(this).val();

        if ($(".confirmDelart").attr("name") === "mags") {
            // alert("lalanda");
            var param = {action: "getArticlesByCategorie", vue: "vue", idCategorie: valeur};
            getCat("admin", param, "#articlestodelete");
        } else {

            var param = {action: "getArticlesByCategorieMP", vue: "vue", idCategorie: valeur};
            getCat("admin", param, "#articlestodelete");
        }


    });



    //pour supprimer un article d'une categorie d'un magasin secondaire

    $(".deletearticlebtn").click(function (e) {
        e.preventDefault();
        idmag = $(this).attr("name");

        if ($(".confirmDelart").hasClass("hidden")) {
            $("#confirmation").addClass("hidden");
            $(".confirmDelart").removeClass("hidden").attr("name", "mags");
            $(".catetodelete").removeAttr("id").attr({id: "allcategorieMag"});

        }
        $(".catetodelete").removeAttr("id").attr({id: "allcategorieMag"});
        $(".article-hidden").removeClass("hidden");
        if (idmag === null) {
            $(".close-modal").trigger("click");
            $.gritter.add({
                title: 'attention',
                text: 'choisir au prealable un magasin',
                sticky: false
            });
        } else {
            $("#allcategorieMag").html($("#example-getting-starte").html());
            var param = {action: "allCatMag", vue: "vue", id_magasin: idmag};
            //getPages("admin", param, "#allcategorieMag");
            $(this).attr("href", "#deleteCategorie");
        }


    });


    //confirmation de suppressio d'un article
    $(".confirmDelart").click(function (e) {
        e.preventDefault();
        //var nomcat = $("#cate").val();
        var form = $("#delCat");
        var url = "";
        //var allcat = $("#allcategorieMag").val();
        if ($(".confirmDelart").attr("name") === "mags") {
            url = "parametre?action=deletteArticleStockMS&id_Magasin=" + idmag;
        } else {
            url = "parametre?action=deletteArticleStockMP&id_Magasin=" + idmag;
        }
        $.ajax({
            url: url,
            data: $(form).serialize(),
            type: $(form).attr("method"),
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if (data.length !== 0) {

                    $("#example-getting-starte").html("");
                    for (var l = 0; l < data.length; l++) {
                        $("#example-getting-starte").append("<option value=" + data[l][0] + ">" + data[l][1] + "</option>");
                    }


                    $.gritter.add({
                        title: 'article suprimer avec success',
                        text: '',
                        sticky: false
                    });
                    $(".close-modal").trigger("click");
                } else {

                    $.gritter.add({
                        title: 'impossible de supprimer cet article',
                        text: '',
                        sticky: false
                    });
                }

            }
        });
    });


});