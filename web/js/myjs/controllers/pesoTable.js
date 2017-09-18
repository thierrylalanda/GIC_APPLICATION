/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    var societe;
    $.ajax({
        url: "impression",
        data: {action: "bordereauP"},
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            societe = data;


        }
    });


    var select = $(".all-date");
    var now = new Date();
    for (var i = 2017; i <= now.getFullYear(); i++) {
        var option;
        if (i === now.getFullYear()) {
            option = '<option selected>' + i + '</option>';

        } else {

            option = '<option >' + i + '</option>';
        }
        select.append(option);

    }

    $(".region_service").click(function (e) {
        e.preventDefault();
        $(".personnels").html("");
        $(".service").html("");
        var region = $(this).val();

        var param = {action: "getSites", vue: "vue", region: region};
        getMany("admin", param, ".site_service");
    });

    $(".region_magp").click(function (e) {
        e.preventDefault();

        var region = $(this).val();

        var param = {action: "getRole", vue: "vue", idAffectation: 3, region: region};
        getMany("admin", param, ".magasinP");
    });

    $(".region_mags").click(function (e) {
        e.preventDefault();

        var region = $(this).val();

        var param = {action: "getRole", vue: "vue", idAffectation: 2, region: region};
        getMany("admin", param, ".magasinS");
    });


    $(".site_service").click(function (e) {

        e.preventDefault();
        $(".personnels").html("");
        var site = $(this).val();
        var param = {action: "getServiceBySite", vue: "vue", site: site};
        getMany("admin", param, ".service");
    });
    $(".service").click(function (e) {

        e.preventDefault();

        var service = $(this).val();
        var param = {action: "getPersonnelsByService", vue: "vue", service: service};
        getMany("admin", param, ".personnels");
    });

    //pour obtenir les Directions
    $(".region").click(function (e) {

        e.preventDefault();
        var region = $(this).val();
        var param = {action: "getDirectionByRegion", vue: "vue", region: region};
        getMany("admin", param, ".direction");
    });
    var datable;
    var cat;
    $(".li-table").click(function (e) {
        e.preventDefault();
        cat = $(this).children("a").text();
        $(".table-rapport").addClass("hidden");
        $(".stat-perso").html("");
        if ($(".table-rapport").hasClass("perso")) {
            $(".table-rapport tfoot").html("");
            //datable.DataTable();
            $(".table-rapport").removeCLass("perso");

        }
    });

    function parseDate(date) {
        var dat = new Array();
        dat = date.split("-");
        return " du " + dat[0] + " au " + dat[1];
    }

    $(".search-catego").click(function (e) {
        e.preventDefault();
        $(".table-rapport").removeClass("hidden");
        if ($(".table-rapport").hasClass("persos")) {
            $(".table-rapport").removeCLass("persos");

        }



        var form = $(this).parent("div").parent("form");
        //alert($(this).parent("form").find(".reservation").val());
        var date = form.find(".reservation").val();

        if (form.hasClass("non-region")) {

            form.attr("action", form.attr("action") + "&id_magasin=" + form.attr("id_magasin"));

        }

        if (form.hasClass("non-regionp")) {

            form.attr("action", form.attr("action") + "&id_magasinP=" + form.attr("id_magasinP"));

        }

        var title_end = " " + form.find("select:last").find("option:selected").text();
        $.ajax({
            url: form.attr("action"),
            data: form.serialize(),
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                //alert(data);
                $(".stat-perso").html("");
                $(".table-rapport tfoot").html("");
                console.log(data);
                var tfoot;


                for (var i = 0; i < data.length - 1; i++)

                {
                    if (data[i][1] !== 0) {



                        var tr = document.createElement("tr");
                        var article = document.createElement("td");
                        var qte = document.createElement("td");
                        var total = document.createElement("td");
                        article.innerHTML = data[i][0];
                        qte.innerHTML = data[i][1];
                        total.innerHTML = data[i][2];
                        tr.appendChild(article);
                        tr.appendChild(qte);
                        tr.appendChild(total);

                        $(".stat-perso").append(tr);
                    }

                }
                var tfoot = "<br> Total: " + data[data.length - 1];
                $(".table-rapport tfoot").html(tfoot);
                $(".table-rapport").addClass("perso");
                if (data.length !== 0) {
                    impression(parseDate(date), tfoot, title_end);
                }

            }
        });

    });


    function addperso() {
        $("#stat-perso").html("");
        $.ajax({
            url: "tableau_de_Bord_Budgetaire",
            data: {action: "allStatUtilisateur", region: $("#region_service").val()},
            type: 'POST',
            dataType: 'json',
            success: function (data) {

                for (var i = 0; i < data.length; i++)

                {
                    var tr = document.createElement("tr");
                    var nom = document.createElement("td");
                    nom.innerHTML = data[i][0];
                    tr.appendChild(nom);
                    for (var j = 2; j < data[i].length; j++)
                    {
                        for (var k = 0; k < data[i][j].length; k++)
                        {
                            var td = document.createElement("td");
                            td.innerHTML = "<a class='detail-perso'  nom='" + data[i][0] + "' href='tableau_de_Bord_Budgetaire?action=DetailleStatUtilisateur&vue=statistiques&nomutilisateur=" + data[i][1] + "&categorie=" + data[i][j][k][0] + "'>" + data[i][j][k][1] + "</a> ";
                            tr.appendChild(td);
                        }



                    }

                    //var tdb = document.createElement("td");
                    //tdb.innerHTML = "<a class='btn btn-primary delete'  href='SettingPersonnal?action=DelettePersonnel&vue=perso&personnel=" + data[i][1] + "&categorie=" + data[i][0] + "'  ><span class='icon'> <i class='icon-eye-open'></i> </span></a> ";
                    //tr.appendChild(tdb);

                    $("#stat-perso").append(tr);
                }
                impression();
            }
        });
    }
    addperso();


    function impression(date, tfoot, title_end) {
        var region;
        var titre;
        if ($(".notification").attr("region") === "") {
            region = " ";

        } else {

            region = " region " + $(".notification").attr("region");

        }

        if ($(".perso").attr("titre") === undefined) {
            titre = " ";

        } else {

            titre = $(".perso").attr("titre") + $(".li-cat.active").find("a").text() + date;

        }
        var titreT = $(".page-title").text();

        var header = '<div class="header_top" style="border:2px solid black;padding:5px;margin-bottom:5px">' +
                '<span style="text-align:left">' +
                '<img src="data:image/png;base64,' + societe[0] + '" style=" left:0;padding-right:10px;height:70px" />' +
                '</span>' +
                '<div class="region pull-right" style="margin-right:5px;margin-top:10px;"><i>' + region + '</i></div>' +
                '<h3 class="header_title" style="text-align:center;padding-right:10px;font-size:0.7em"> ' + societe[1] + '</h3>' +
                '</div><br>' +
                '<h3 style="text-align:center;padding-right:10px;font-size:0.7em"> ' + titre + title_end + '</h3>';
        '</div>';
        setTimeout(function () {



            datable = $('.perso').DataTable({
                dom: "Bfrtip",
                responsive: true,
                autoFill: true,
                // info: true,
                //  scrollY: 250,
                //paging: false,
                // stateSave: true,
                header: true,
                lengthChange: true,
                //keys: true,
                //select: true,
                lengthMenu: [
                    [10, 25, 50, -1],
                    ['10 lignes', '25 lignes', '50 lignes', 'Tous']

                ],
                //  stateSave: true,
                buttons: [
                    // 'columnsToggle',
                    {
                        extend: 'colvis',
                        collectionLayout: ' three-column ',
                        text: 'Colonnes',
                        postfixButtons: ['colvisRestore'],
                        columns: ':visible'
                    },
                    {
                        extend: 'colvisGroup',
                        text: 'Montrer Tous',
                        show: ':hidden'
                    },
                    'pageLength',
                    {
                        extend: 'collection',
                        text: 'Exporter',
                        autoClose: true,
                        buttons: [
                            'copy',
                            {
                                extend: 'excel',
                                title: 'Data export',
                                extension: '.xls',
                                // text: '<i class="fa fa-file-excel-o"></i>', 
                                titleAttr: 'Excel',
                                exportOptions: {
                                    columns: ' :visible'
                                },
                                columnDefs: [{targets: -1,
                                        visible: false}]
                            },
                            'csv',
                            {
                                extend: 'pdfHtml5',
                                message: function () {
                                    return "";
                                },
                                title: titre,
                                //text: '<i class="fa fa-file-pdf-o icon icon-print"></i>',
                                titleAttr: 'PDF',
                                orientation: 'portrait',
                                pageSize: 'A4',
                                download: 'open',
                                customize: function (doc) {



                                    doc.content.splice(0, 0, {
                                        margin: [0, -20, 0, 0],
                                        alignment: 'left',
                                        image: 'data:image/png;base64,' + societe[0] + ' style=" left:0;padding-right:10px;height:20px"'
                                    });
                                    doc.content.splice(0, 0, {
                                        margin: [40, 0, 0, 0],
                                        alignment: 'center',
                                        text: "" + societe[1]

                                    });
                                    doc.content.splice(0, 0, {
                                        margin: [0, -10, 0, 0],
                                        alignment: 'right',
                                        text: "" + region

                                    });

                                },
                                exportOptions: {
                                    columns: ' :visible'
                                },
                                columnDefs: [{targets: -1,
                                        visible: false}]
                            }

                        ]
                    }, {
                        extend: 'print',
                        text: 'Imprimer',
                        titleAttr: 'Imprimertout',
                        message: '',
                        autoPrint: true,
                        title: function () {
                            return " ";
                        },
                        //footer: true,
                        customize: function (win) {
                            $(win.document.body).addClass('display').css('font-size', '1.2em').prepend(header);
                            $(win.document.body).find('h3').css('text-align', 'center');
                            $(win.document.body).find('tbody').css({'font-size': '0.7em', "text-align": "center", "padding-top": "0px"});
                            $(win.document.body).find('tbody tr td,tr th').css({'font-size': '0.7em', "padding-top": "0px", "padding-bottom": "0px"});
                            $(win.document.body).find('thead').css({'font-size': '0.8em', "text-align": "center"});
                            $(win.document.body).find('div.region').css('text-align', 'right');
                            $(win.document.body).addClass('display').css('font-size', '1.2em').append(tfoot);


                            $(win.document.body).find('h3').css('text-align', 'center');
                            $(win.document.body).find('.header_top').css('height', '70px');
                            $(win.document.body).find('.header_title').css('margin-top', '-30px');
                            $(win.document.body).find('tbody').css({'font-size': '0.7em', "text-align": "center", "padding-top": "0px"});
                            $(win.document.body).find('tbody tr td,tr th').css({'font-size': '0.7em', "padding-top": "0px", "padding-bottom": "0px"});
                            $(win.document.body).find('thead').css({'font-size': '0.8em', "text-align": "center"});
                            $(win.document.body).find('div.region').css('text-align', 'right');
                        },
                        exportOptions: {
                            columns: ' :visible'

                        },
                        columnDefs: [{targets: -1,
                                visible: false}]


                    }

                ],
                language: {
                    buttons: {
                        copyTitle: 'Ajouté au presse-papiers',
                        copyKeys: 'Appuyez sur <i>ctrl</i> ou <i>\u2318</i> + <i>C</i> pour copier les données du tableau à votre presse-papiers. <br><br>Pour annuler, cliquez sur ce message ou appuyez sur Echap.',
                        copySuccess: {
                            _: '%d lignes copiées',
                            1: '1 ligne copiée',
                            pageLength: 'montrer'
                        }
                    },
                    processing: "Traitement en cours...",
                    search: "Rechercher&nbsp;:",
                    lengthMenu: "Afficher _MENU_ &eacute;l&eacute;ments",
                    info: "Affichage de l'&eacute;lement _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
                    infoEmpty: "Affichage de l'&eacute;lement 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
                    infoFiltered: "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
                    infoPostFix: "",
                    loadingRecords: "Chargement en cours...",
                    zeroRecords: "Aucun &eacute;l&eacute;ment &agrave; afficher",
                    emptyTable: "Aucune donnée disponible dans le tableau",
                    paginate: {
                        first: "Premier",
                        previous: "Pr&eacute;c&eacute;dent",
                        next: "Suivant",
                        last: "Dernier"
                    },
                    aria: {
                        sortAscending: ": activer pour trier la colonne par ordre croissant",
                        sortDescending: ": activer pour trier la colonne par ordre décroissant"
                    }
                }
            });
            datable.$('tr').css('font-size', '12px');


        }, 1);
    }






});