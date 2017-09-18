
function getPage(serveur, param, idResponse) {
    $.post(
            serveur,
            param,
            function (data) {

                $(".exampleSelect").html("");
                $(idResponse).html("");
                sessionStorage.setItem("data", data);
                //alert(data);
                var k;
                for (var i = 0; i < data.length; i++) {

                    if (data[i][0] !== 99) {
                        $(idResponse).append("<option value=" + data[i][0] + " selected >" + data[i][1] + "</option>");
                    } else {
                        k = i;
                        break;
                    }
                }
                for (k = i + 1; k < data.length; k++) {
                    $(idResponse).append("<option  value=" + data[k][0] + " >" + data[k][1] + "</option>");
                }
                $("#ff").html($(idResponse));
                // $(".ff").html($(idResponse));
                $(".exampleSelect").attr('multiple', 'multiple');
                //  $(".example").multiselect();
                //  $(".example").multiselect('rebuild');
                $('.exampleSelect').multiselect({
                    enableFiltering: true,
                    maxHeight: 200,
                    buttonClass: 'btn-primary btn',
                    buttonWidth: '370px',
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
                            return 'Aucune page Selectionner ...';
                        } else if (options.length > 3) {
                            return 'plus de 3 pages selectionner!';
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

            },
            "json"
            );
}


function getMany(serveur, param, idResponse) {
    $.post(
            serveur,
            param,
            function (data) {
                $(idResponse).html("");
                sessionStorage.setItem("data", data);
                console.log(data);
                for (var i = 0; i < data.length; i++) {
                    //alert(data[i][1]);
                    $(idResponse).append("<option value=" + data[i][0] + ">" + data[i][1] + "</option>");
                }


            },
            "json"
            );
}

function getCat(serveur, param, idResponse) {
    $.post(
            serveur,
            param,
            function (data) {

                $(".exampleSelect").html("");
                $(idResponse).html("");
                sessionStorage.setItem("data", data);
                //alert(data);
                var k;
                for (var i = 0; i < data.length; i++) {

                    if (data[i][0] !== 99) {
                        $(idResponse).append("<option value=" + data[i][0] + ">" + data[i][1] + "</option>");
                    } else {
                        k = i;
                        break;
                    }
                }
                for (k = i + 1; k < data.length; k++) {
                    $(idResponse).append("<option  value=" + data[k][0] + " >" + data[k][1] + "</option>");
                }
                $(".ff").html($(idResponse));
                // $(".ff").html($(idResponse));
                $(".exampleSelect").attr('multiple', false);
                //  $(".example").multiselect();
                //  $(".example").multiselect('rebuild');
                $('.exampleSelect').multiselect({
                    enableFiltering: true,
                    maxHeight: 200,
                    buttonClass: 'btn-primary btn',
                    buttonWidth: '300px',
                    //selectAllText: true,
                    includeSelectAllOption: false,
                    enableCaseInsensitiveFiltering: true,
                    filterPlaceholder: 'Recherhe',
                    // selectAllValue: 'multiselect-all',
                    // selectedClass: null,
                    // dropRight: true,
                    //dropUp: true,
                    buttonText: function (options, select) {
                        if (options.length === 0) {
                            return 'Aucune page Selectionner ...';
                        } else if (options.length > 3) {
                            return 'plus de 3 pages selectionner!';
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

            },
            "json"
            );
}



$(document).ready(function () {
    var personnelMat;
    var groupes;
    var disabled = false;
    var link;
    var iduser = sessionStorage.getItem("iduser");

    $(".delete-insert").click(function (e) {
        e.preventDefault();
        var form = $("#ConfirmForm");
        link = $(this).attr("name");
        var response = confirm("voulez-vous reellement supprimer?");

        if (response) {

            document.location = link;
        }

        //var linksubmit=link+"#myModal3";
        //$(this).attr("href", "#myModal3");

        //alert(link);

    });



    $(".statAll").click(function (e) {
        e.preventDefault();
        document.location = $(this).val();

    });



    $("#basic_validate .row .control-group .controls > input").focus(function () {

        $("#basic_validate .row .control-group .controls > input").each(function (index) {
            if ($(this).val() === '') {
                disabled = true;
                //alert($(this).val());

            }
        });
        if (disabled) {

            $("#nexttape").addClass("disabled");
        } else
            $("#nexttape").removeClass("disabled");
    });
    //affectation d'un personnel
    $(".affectation").click(function (e) {

        e.preventDefault();
        var idaffectation = $(this).val();
        var idregion = $("#region_service").val();
        //alert(idregion);
        var idregion = $("#region_service").val();
        if (idregion !== undefined) {
            idregion = $("#region_service").val();
        } else {
            idregion = $("#region").val();
        }
//alert(idregion);
        $(".magasin").addClass("hidden");
        $(".typecontrol").addClass("hidden");
        $("#role_personnel").html("");
        $("#magasin").html("");
        if (idaffectation === "4") {
            $(".typecontrol").removeClass("hidden");
            var param = {action: "getRole", vue: "vue", idAffectation: idaffectation, region: idregion};
            getMany("admin", param, "#role_personnel");
        }
        if (idaffectation === "3") {
            $(".magasin").removeClass("hidden");
            var param = {action: "getRole", vue: "vue", idAffectation: idaffectation, region: idregion};
            getMany("admin", param, "#magasin");
        }
        if (idaffectation === "2") {
            $(".magasin").removeClass("hidden");
            var param = {action: "getRole", vue: "vue", idAffectation: idaffectation, region: idregion};
            getMany("admin", param, "#magasin");
        }


        var param = {action: "getGroupesByNiveau", vue: "vue", idAffectation: idaffectation, region: idregion};
        getMany("admin", param, "#selectgroupe");
    });
    //obtenir les magasins a partir de la region
    $('#regiontomagasin').click(function () {

        var valeur = $(this).val();
        var param = {action: "getMagasinByRegion", vue: "vue", idRegion: valeur};
        getPage("admin", param, ".magasinbyregion");
    });
    //obtenir les articles d'une categorie new
    $('#categorietoarticles').click(function (e) {
        e.preventDefault();
        var valeur = $(this).val();
        //var mag = $(".magasinbyregion");
        //alert(valeur);
        var param = {action: "getArticlesByCategorie", vue: "vue", idCategorie: valeur};
        getCat("admin", param, "#articlesCateorie");
    });
    $('#categorietoarticlesMP').click(function (e) {
        e.preventDefault();
        var valeur = $(this).val();
        //var mag = $(".magasinbyregion");
        //alert(valeur);
        var param = {action: "getArticlesByCategorieMP", vue: "vue", idCategorie: valeur};
        getCat("admin", param, "#articlesCateorieMP");
    });
    //obtenir les categories d'un magasin
    $('.magasinbyregion').click(function () {

        var valeur = $(this).val();
        var param = {action: "getTypeMagasin", vue: "vue", id_magasin: valeur};
        getPage("admin", param, "#categorietoarticles");
    });
    $('#magasinexpediteur').click(function () {
        var valeur = $(this).val();
        var param = {action: "getTypeMagasin", vue: "vue", id_magasin: valeur};
        getPage("admin", param, "#box1View");
    });
    //obtenir les categories d'un magasin Principal

    $('.magasinPtocat').click(function () {

        var valeur = $(this).val();
        var param = {action: "getTypeMagasinP", vue: "vue", id_magasin: valeur};
        getPage("admin", param, "#categorietoarticlesMP");
    });
    $('#magasinexpediteurMp').click(function () {

        var valeur = $(this).val();
        var param = {action: "getTypeMagasinP", vue: "vue", id_magasin: valeur};
        getPage("admin", param, "#box1View");
    });
    $('.magasinPbyregion').click(function () {

        var valeur = $(this).val();
        var param = {action: "getTypeMagasinP", vue: "vue", id_magasin: valeur};
        getPage("admin", param, "#categorie");
    });
    $('.pageniveau').click(function () {

        var valeur = $(this).val();
        var param = {action: "getPagesBySousGroupe", vue: "vue", sousGroupe: valeur};
        alert(valeur);
        getPage("admin", param, ".pagesGroupe");
    });
    $(".niveaux").click(function (e) {

        e.preventDefault();
        var idaffectation = $(this).val();
        // alert(idaffectation);

        $.post(
                "admin",
                {action: "getPagesByNiveau", vue: "vue", niveau: idaffectation},
                function (data) {
                    $("#pagegroupe").html("");
                    $(".exampleSelect").html("");
                    // alert(data);

                    $("#pagegroupe").append('<option  value=' + data[1] + '>' + data[2] + '</option>');
                    $(".exampleSelect").attr('multiple', 'multiple');
                },
                "json"
                );
    });
    $("#newgroupe").addClass("hidden");
    $(".groupe").focus(function (e) {

        e.preventDefault();
        var idgroupe = $(".groupe").val();
        var param = {action: "getPages", vue: "vue", idGroupe: idgroupe};
        getMany("admin", param, ".pagesGroupe");
    });
    $("#fichier").click(function (e) {
        $(".adbyfichier").removeClass("hidden");
    });
    $("#formulaire").click(function (e) {
        $(".adbyfichier").addClass("hidden");
    });
    //pour obtenir les groupes d'un niveau

    $(".niveau").click(function (e) {

        e.preventDefault();
        var niveau = $(this).val();
        $.post(
                "admin",
                {action: "getGroupesByNiveau", vue: "vue", niveau: niveau},
                function (data) {
                    $("#selectgroupe").html("");
                    if (data !== "aucun") {
                        //console.log(data[2]);
                        $("#selectgroupe").append('<option class="site" value=' + data[1] + '>' + data[2] + '</option>');
                    } else
                        $("#selectgroupe").html("");
                },
                "json"
                );
    });
    $("#site_service").click(function (e) {

        e.preventDefault();
        var site = $(this).val();
        var param = {action: "getServiceBySite", vue: "vue", site: site};
        getMany("admin", param, "#service");
    });
    //pour obtenir les sites d'une region
    $(".regionAP").click(function (e) {

        e.preventDefault();
        var region = $(this).val();
        $("#magasigniers").html("");
        var param = {action: "getSites", vue: "vue", region: region};
        getMany("admin", param, "#site_services");
    });
    //pour obtenir les Directions
    $("#site").click(function (e) {

        e.preventDefault();
        var site = $(this).val();
        var param = {action: "getDirections", vue: "vue", site: site};
        getMany("admin", param, "#direction");
    });
    $("#region_service").click(function (e) {
        e.preventDefault();
        var region = $(this).val();
        var param = {action: "getSites", vue: "vue", region: region};
        getMany("admin", param, "#site_service");
    });
    $("#site_service").click(function (e) {
        e.preventDefault();
        var site = $(this).val();
        var param = {action: "getDirections", vue: "vue", site: site};
        getMany("admin", param, "#direction_service");
    });
    //pour obtenir les Service

    $("#direction").click(function (e) {

        e.preventDefault();
        var direction = $(this).val();
        var param = {action: "getServices", vue: "vue", direction: direction};
        getMany("admin", param, "#service");
    });

    $("#service").click(function (e) {

        e.preventDefault();

        var service = $(this).val();
        var param = {action: "getPersonnelsByService", vue: "vue", service: service};
        getMany("admin", param, "#personnels");
    });
    // pour obtenir les personnel a partir du site   
    $("#site_service").click(function (e) {

        e.preventDefault();
        var site = $(this).val();
        var param = {action: "getMagasigniersByService", vue: "vue", site: site};
        getMany("admin", param, "#magasigniers");
    });
    $("#site_services").click(function (e) {

        e.preventDefault();
        var site = $(this).val();
        var param = {action: "getMagasigniersByService", vue: "vue", site: site};
        getMany("admin", param, "#personne");
    });
    $(".region_mag").click(function (e) {

        e.preventDefault();
        var region = $(this).val();
        var param = {action: "getMagasinsByRegion", vue: "vue", region: region};
        getMany("admin", param, ".magasin");
    });
    $(".region_magP").click(function (e) {

        e.preventDefault();
        var region = $(this).val();
        //alert(region);
        var param2 = {action: "getMagasinsPByRegion", vue: "vue", region: region};
        getMany("admin", param2, ".magasinP");
    });
    //obtenir les pages a partir d'un sous groupe

    $(".sousgroupes").click(function (e) {

        e.preventDefault();
        var sousGroupe = $(this).val();
        var param = {action: "getPagesBySousGroupe", vue: "vue", sousGroupe: sousGroupe};
        getPage("admin", param, ".pagesGroupe");
    });
    //obtenir les pages a partir d'un niveau pour ajouter un nouveau groupe
    $(".pageniveau").click(function (e) {

        e.preventDefault();
        var sousGroupe = $(this).val();
        var param = {action: "getPagesByNiveau", vue: "vue", niveau: sousGroupe};
        getMany2("admin", param, ".pagesGroupe");
    });
    $(".region_site").click(function (e) {
        e.preventDefault();
        var region = $(this).val();
        //alert(region);
        var param = {action: "getSites", vue: "vue", region: region};
        getMany("admin", param, "#site");
    });
    //pour obtenir les Personnels
    $("#region").click(function (e) {

        e.preventDefault();
        var region = $(this).val();
        $.post(
                "admin",
                {action: "getPersonnels", vue: "vue", region: region},
                function (data) {
                    $("#magasinier").html("");
                    console.log(data);
                    for (var i = 0; i < data.length; i++) {
                        console.log(data[i]);
                        console.log(data[i][1]);
                        $("#magasinier").append('<option class="site" value=' + data[i][0] + '>' + data[i][1] + '</option>');
                    }




                },
                "json"
                );
    });
    $("#updateDE").click(function (e) {

        e.preventDefault();
        $(" .hidden").removeClass("hidden");
        $(this).addClass("hidden");
        $(".show").addClass("hidden");
        $("#saveDE").removeClass("hidden");
    });
    //sauvegarde des mise a jours des donnees de l'entreprise du personnels
    $("#saveDE").click(function (e) {
        e.preventDefault();
        var param = {};
        $(" input").each(function (index) {
            if ($(this).val() !== '') {

            }
        });
    });
    //pour afficher tous les personnels dans groupe all-members
    $("#all-members").click(function (e) {
        $("#see-all-members").toggleClass("hidden");
    });
    $("#all-pages").click(function (e) {
        $("#see-all-members").addClass("hidden");
        $("#see-all-pages").toggleClass("hidden");
    });
    //lors du click pour faire apparraitre les donnees
    $(".tr td a").click(function (e) {
        var id = "#" + $(this).attr("href").toString();
        e.preventDefault();
        $(this).animate({"margin-left": "7px"}, "slow");
        $(".donnees").fadeOut("slow").addClass("hide");
        $(id).removeClass("hide").fadeIn("slow");
    });
    $(".tr td a").hover(
            function () {
                $(this).animate({"margin-left": "7px"}, "slow");
            },
            function () {
                $(this).animate({"margin-left": "1px"}, "slow");
            });
    //authentification pour supprimer un personnel
    $("#confirmAuth").click(function (e) {

        e.preventDefault();
        var param = {action: "delete", idPersonnel: personnelMat, user: $("#userConfirm").val(), password: $("#passwordConfirm").val()};
        var oldpersonnel = new Personnel("personnel");
        oldpersonnel.delete(param);
        if (sessionStorage.getItem("status") === "success") {

            $("#deletePersonnel").modal("hide");
            $("#formdeletePersonnel").modal("hide");
            $.gritter.add({
                title: 'confirmation',
                text: 'personnel supprimer avec success',
                sticky: false
            });
        }
    });
    $("#ajoutpersobtn").click(function (e) {

        $("#mesappareils").toggleClass("hidden");
    });
    //pour afficher les personnels dans la vue update service 
    var view = 0;
    $(".viewPersonnel").hide();
    $(".viewPersonnelbtn").click(function (e) {
        if (view === 0) {
            $(".viewPersonnel").show();
            $("#eyes").removeClass("icon-eye-open").addClass("icon-eye-close");
            view = 1;
        } else {
            $(".viewPersonnel").hide();
            $("#eyes").removeClass("icon-eye-close").addClass("icon-eye-open");
            view = 0;
        }

    });
    $('.datatable').dataTable({
        "paginate": true,
        "sort": true
    });
    $('select ').focusout(function () {
        var input = $(this).val();
        //alert($(this).children("option:selected").html());
        $("#" + $(this).attr("id") + "final").html($(this).children("option:selected").html());
    });
    $('input').focusout(function () {
        var input = $(this).val();
        $("#" + $(this).attr("id") + "final").html($(this).val());
    });
    $('table.tt tbody tr:odd').addClass('odd');
    $('table.tt tbody tr:even').addClass('even');
});

