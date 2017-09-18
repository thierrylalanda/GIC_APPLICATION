/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $.ajax({
        url: "impression",
        data: {action: "bordereauP"},
        type: 'POST',
        dataType: 'json',
        success: function (data) {

            simplePrint(data);

        }
    });


    function getDate() {
        var date = new Date();
        var jour = date.getDate();
        var mois = (date.getMonth() + 1);
        if (date.getDate() < 10) {
            jour = "0" + date.getDate();
        }

        if (date.getMonth() < 10) {
            mois = "0" + (date.getMonth() + 1);
        }
        return jour + "/" + mois + "/" + date.getFullYear();
    }

    function simplePrint(data) {

        var region;
        var titre;
        if ($(".notification").attr("region") === "") {
            region = " ";

        } else {

            region = " region " + $(".notification").attr("region");

        }



        titre = $(".page-title").html();




        var header = '<div class="header_top" style="border:2px solid black;padding:5px;margin-bottom:5px">' +
                '<span style="text-align:left">' +
                '<img src="data:image/png;base64,' + data[0] + '" style=" left:0;padding-right:10px;height:70px" />' +
                '</span>' +
                '<div class="region pull-right" style="margin-right:5px;margin-top:10px;"><i>' + region + '</i></div>' +
                '<h3 class="header_title" style="text-align:center;padding-right:10px;font-size:0.7em"> ' + data[1] + '</h3>' +
                '</div><br>' +
                '<h3 style="text-align:center;padding-right:10px;font-size:0.7em"> ' + titre + '</h3>';

        var datable = $('.simple_print').DataTable({
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
                                    image: 'data:image/png;base64,' + data[0] + ' style=" left:0;padding-right:10px;height:20px"'
                                });
                                doc.content.splice(0, 0, {
                                    margin: [40, 0, 0, 0],
                                    alignment: 'center',
                                    text: "" + data[1]

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
                    titleAttr: 'Imprimer',
                    message: '',
                    autoPrint: true,
                    title: function () {
                        return " ";
                    },
                    //footer: true,
                    customize: function (win) {
                        $(win.document.body).addClass('display').css('font-size', '1.2em').prepend(header);
                        $(win.document.body).find('h3').css('text-align', 'center');

                        $(win.document.body).find('.header_top').css('height', '100px');
                        $(win.document.body).find('.header_title').css('margin-top', '-10px');
                        $(win.document.body).find('tbody').css({'font-size': '0.7em', "text-align": "center", "padding-top": "0px"});
                        $(win.document.body).find('tbody tr td,tr th').css({'font-size': '0.7em', "padding-top": "0px", "padding-bottom": "0px"});
                        $(win.document.body).find('thead').css({'font-size': '0.8em', "text-align": "center"});
                        $(win.document.body).find('div.region').css('text-align', 'right');
                        // $(win.document.body).addClass('display').css('font-size', '1.2em').append(tfoot);
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
    }

});