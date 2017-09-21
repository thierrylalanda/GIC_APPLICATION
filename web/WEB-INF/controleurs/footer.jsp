<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Load javascripts at bottom, this will reduce page load time -->

<script src="admin/js/jquery-1.8.3.min.js"></script>

<script src="admin/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="admin/assets/jquery-slimscroll/jquery-ui-1.9.2.custom.min.js"></script>
<script src="admin/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="admin/assets/fullcalendar/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="admin/assets/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="admin/js/jquery.blockui.js"></script>
<script type="text/javascript" src="admin/assets/gritter/js/jquery.gritter.js"></script>

<script src="admin/assets/flot/jquery.flot.js" type="text/javascript"></script>
<script src="admin/assets/flot/jquery.flot.pie.js" type="text/javascript"></script>
<script src="admin/assets/flot/jquery.flot.resize.js" type="text/javascript"></script>


<!-- ie8 fixes -->
<!--[if lt IE 9]>
<script src="admin/js/excanvas.js"></script>
<script src="admin/js/respond.js"></script>
<![endif]-->


<script src="admin/js/gritter.js" type="text/javascript"></script>
<script type="text/javascript" src="admin/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="admin/assets/uniform/jquery.uniform.min.js"></script>
<script src="js/js/lumino.glyphs.js" type="text/javascript"></script>
<script src="js/js/chart.min.js" type="text/javascript"></script>
<script src="js/js/easypiechart-data.js" type="text/javascript"></script>
<script src="js/js/easypiechart.js" type="text/javascript"></script>
<script src="admin/js/external-dragging-calendar.js" type="text/javascript"></script>
<script src="assets/glDatePicker-2.0/glDatePicker.js" type="text/javascript"></script>



<c:if test="${vue=='rapport' or vue=='Historique MS' or vue=='Historique MP'}">
    <script src="js/myjs/controllers/stat-camanber.js" type="text/javascript"></script>

    <script src="admin/code/highcharts.js"></script>
    <script src="admin/code/highcharts-3d.js"></script>
    <script src="admin/code/modules/exporting.js"></script>


    <script src="admin/code/themes/dark-green.js" type="text/javascript"></script>

    <script src="js/myjs/controllers/stat-chart.js" type="text/javascript"></script>
</c:if>

<script src="admin/pdfmake-master/build/pdfmake.min.js" type="text/javascript"></script>
    <script src="admin/pdfmake-master/build/vfs_fonts.js" type="text/javascript"></script>
    <script src="admin/pdfmake-master/src/printer.js" type="text/javascript"></script>
    <script src="admin/pdfmake-master/examples/tables.js" type="text/javascript"></script>
<c:if test="${vue=='statistiques' or vue=='Historique MS' or vue=='Historique MP' or vue=='rapport'}">
    
    <script src="js/myjs/controllers/pesoTable.js" type="text/javascript"></script>
</c:if>
<c:if test="${vue=='Historique MS'}">
    <script src="js/myjs/controllers/statBudget.js" type="text/javascript"></script>
</c:if>

<script src="js/myjs/controllers/simple_print.js" type="text/javascript"></script>
<script src="js/myjs/controllers/notifications.js" type="text/javascript"></script>


<c:if test="${state == 'OK'}">
    <script>
        $(document).ready(function () {

            function getKeys(data) {
                var obj = data,
                        keys = new Array();
                if (Object.keys) {
                    keys = Object.keys(obj);
                } else {
                    for (var k in obj) {
                        keys.push(k);
                    }
                }
                return keys;
            }

            function putlegende(text, color) {
                var legende = $(".legende");

                var elt = '<div class="control-group span3 form-group">' +
                        '<label class="control-label"></label>' +
                        '<div class="controls">' +
                        '<label class="badge" style="background-color: ' + color + ';height:22px">' + text + '</label>' +
                        '</div>' +
                        '</div>';

                legende.append(elt);
            }
            function getDataSetsLine(data) {
                var dataset = new Array();
                //data[0][getKeys(data)[0]][0]  data[i][getKeys(data)[i]][0]

                for (var i = 0; i < data.length; i++) {

                    var dat = new Array();
                    dat.push(data[i][getKeys(data)[i]][1][0]);
                    dat.push(data[i][getKeys(data)[i]][1][1]);
                    dat.push(data[i][getKeys(data)[i]][1][2]);
                    dat.push(data[i][getKeys(data)[i]][1][3]);
                    dat.push(data[i][getKeys(data)[i]][1][4]);
                    dat.push(data[i][getKeys(data)[i]][1][5]);
                    dat.push(data[i][getKeys(data)[i]][1][6]);
                    dat.push(data[i][getKeys(data)[i]][1][7]);
                    dat.push(data[i][getKeys(data)[i]][1][8]);
                    dat.push(data[i][getKeys(data)[i]][1][9]);
                    dat.push(data[i][getKeys(data)[i]][1][10]);
                    dat.push(data[i][getKeys(data)[i]][1][11]);

                    putlegende(getKeys(data)[i], randomColor(i));

                    var donnees = {
                        fillColor: randomColor(i),
                        strokeColor: randomColor(i),
                        pointColor: randomColor(i),
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: randomColor(i),
                        data: dat[0]
                    };
                    //alert(data[i][getKeys(data)[i]][1]);
                    dataset.push(donnees);
                }

                var lineChartData = {
                    labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
                    datasets: dataset

                };
                var chart1 = document.getElementById("lineMP").getContext("2d");
                var myLine = new Chart(chart1).Line(lineChartData, {
                    responsive: true
                });
                return dataset;
            }

            function randomColor(i) {
                var colo = ["#edc240", "#afd8f8", "#cb4b4b", "#4da74d", "#9440ed"];
                var j = i % 5;
                var color = ["rgba(48, 164, 255, 1)", "rgba(220,220,220,1)", "rgba(48, 164, 255, 1)", "rgba(48, 164, 255, 1)", "rgba(48, 164, 255, 1)"];
                return colo[j];
            }

            function getDataSetsBar(data) {
                var datasets = new Array(); //data[i][getKeys(data)[i]][1]
                //alert(getElement(data[1][getKeys(data)[1]][1]));                 
                for (var i = 0; i < data.length; i++) {
                    var dat = new Array();
                    dat.push(data[i][getKeys(data)[i]][0][0]);
                    dat.push(data[i][getKeys(data)[i]][0][1]);
                    dat.push(data[i][getKeys(data)[i]][0][2]);
                    dat.push(data[i][getKeys(data)[i]][0][3]);
                    dat.push(data[i][getKeys(data)[i]][0][4]);
                    dat.push(data[i][getKeys(data)[i]][0][5]);
                    dat.push(data[i][getKeys(data)[i]][0][6]);
                    dat.push(data[i][getKeys(data)[i]][0][7]);
                    dat.push(data[i][getKeys(data)[i]][0][8]);
                    dat.push(data[i][getKeys(data)[i]][0][9]);
                    dat.push(data[i][getKeys(data)[i]][0][10]);
                    dat.push(data[i][getKeys(data)[i]][0][11]);

                    var donnees = {
                        fillColor: randomColor(i),
                        strokeColor: randomColor(i),
                        highlightFill: "rgba(220,220,220,0.75)",
                        highlightStroke: randomColor(i),
                        data: dat[0]
                    };
                    datasets.push(donnees);
                }
                var barChartData = {
                    labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
                    datasets: datasets

                };
                var chart2 = document.getElementById("barMP").getContext("2d");
                var barchar = new Chart(chart2).Bar(barChartData, {
                    responsive: true
                });
                return datasets;
            }


            function drawbarchartMP(data) {
                Highcharts.chart('barchar', {
                    chart: {
                        type: 'column',
                        options3d: {
                            enabled: true,
                            alpha: 1,
                            beta: 25,
                            depth: 70
                        }
                    },
                    title: {
                        text: 'Stattistiques en entrée'
                    },
                    subtitle: {
                        text: 'du magasin'
                    },
                    plotOptions: {
                        column: {
                            depth: 25
                        }
                    },
                    xAxis: {
                        categories: Highcharts.getOptions().lang.shortMonths
                    },
                    yAxis: {
                        title: {
                            text: "Consommation"
                        }
                    },
                    series: getseries(data)
                });

            }
            function getseries(data) {
                var dataset = new Array();

                for (var i = 0; i < data.length; i++) {
                    var dat = new Array();

                    dat.push(data[i][getKeys(data)[i]][0][0]);
                    dat.push(data[i][getKeys(data)[i]][0][1]);
                    dat.push(data[i][getKeys(data)[i]][0][2]);
                    dat.push(data[i][getKeys(data)[i]][0][3]);
                    dat.push(data[i][getKeys(data)[i]][0][4]);
                    dat.push(data[i][getKeys(data)[i]][0][5]);
                    dat.push(data[i][getKeys(data)[i]][0][6]);
                    dat.push(data[i][getKeys(data)[i]][0][7]);
                    dat.push(data[i][getKeys(data)[i]][0][8]);
                    dat.push(data[i][getKeys(data)[i]][0][9]);
                    dat.push(data[i][getKeys(data)[i]][0][10]);
                    dat.push(data[i][getKeys(data)[i]][0][11]);

                    //putlegende(getKeys(data)[i], randomColor(i));
                    var donnees = {
                        name: getKeys(data)[i],
                        data: dat[0]
                    };

                    dataset.push(donnees);
                }


                return dataset;
            }
            function viewMP(data) {
                getDataSetsLine(data);
                drawbarchartMP(data);
            }
            function getKeys(data) {
                var obj = data,
                        keys = new Array();
                for (var i = 0; i < data.length; i++) {
                    for (var k in obj[i]) {
                        keys.push(k);
                    }

                }
                return keys;
            }



            window.onload = function () {

                function chargerMP() {

                    // on lance une requête AJAX
                    $.ajax({
                        url: "Statistique",
                        type: 'POST',
                        success: function (data) {
                            //alert(data);
                            viewMP(data);

                        }
                    });
                    // on relance la fonction
                    // on exécute le chargement toutes les 5 secondes
                }
                chargerMP();
            };

        });</script>
    </c:if>

<c:if test="${stat == 'OK'}">
    <script>


        function drawbarchart(data) {
            Highcharts.chart('barchar', {
                chart: {
                    type: 'column',
                    options3d: {
                        enabled: true,
                        alpha: 1,
                        beta: 25,
                        depth: 70
                    }
                },
                title: {
                    text: 'Stattistiques en entrée'
                },
                subtitle: {
                    text: 'du magasin'
                },
                plotOptions: {
                    column: {
                        depth: 25
                    }
                },
                xAxis: {
                    categories: Highcharts.getOptions().lang.shortMonths
                },
                yAxis: {
                    title: {
                        text: "Consommation"
                    }
                },
                series: getseries(data)
            });

        }
        function getseries(data) {
            var dataset = new Array();

            for (var i = 0; i < data.length; i++) {
                var dat = new Array();

                dat.push(data[i][getKeys(data)[i]][0][0]);
                dat.push(data[i][getKeys(data)[i]][0][1]);
                dat.push(data[i][getKeys(data)[i]][0][2]);
                dat.push(data[i][getKeys(data)[i]][0][3]);
                dat.push(data[i][getKeys(data)[i]][0][4]);
                dat.push(data[i][getKeys(data)[i]][0][5]);
                dat.push(data[i][getKeys(data)[i]][0][6]);
                dat.push(data[i][getKeys(data)[i]][0][7]);
                dat.push(data[i][getKeys(data)[i]][0][8]);
                dat.push(data[i][getKeys(data)[i]][0][9]);
                dat.push(data[i][getKeys(data)[i]][0][10]);
                dat.push(data[i][getKeys(data)[i]][0][11]);

                //putlegende(getKeys(data)[i], randomColor(i));
                var donnees = {
                    name: getKeys(data)[i],
                    data: dat[0]
                };

                dataset.push(donnees);
            }


            return dataset;
        }


        function getKeys(data) {
            var obj = data,
                    keys = new Array();
            if (Object.keys) {
                keys = Object.keys(obj);
            } else {
                for (var k in obj) {
                    keys.push(k);
                }
            }
            return keys;
        }

        function putlegende(text, color) {
            var legende = $(".legende");

            var elt = '<div class="control-group span3 form-group">' +
                    '<label class="control-label"></label>' +
                    '<div class="controls">' +
                    '<label class="badge" style="background-color: ' + color + ';height:22px">' + text + '</label>' +
                    '</div>' +
                    '</div>';

            legende.append(elt);
        }







        function getDataSetsLine(data) {
            var dataset = new Array();
            //data[0][getKeys(data)[0]][0]  data[i][getKeys(data)[i]][0]

            for (var i = 0; i < data.length; i++) {
                var dat = new Array();
                dat.push(data[i][getKeys(data)[i]][1][0]);
                dat.push(data[i][getKeys(data)[i]][1][1]);
                dat.push(data[i][getKeys(data)[i]][1][2]);
                dat.push(data[i][getKeys(data)[i]][1][3]);
                dat.push(data[i][getKeys(data)[i]][1][4]);
                dat.push(data[i][getKeys(data)[i]][1][5]);
                dat.push(data[i][getKeys(data)[i]][1][6]);
                dat.push(data[i][getKeys(data)[i]][1][7]);
                dat.push(data[i][getKeys(data)[i]][1][8]);
                dat.push(data[i][getKeys(data)[i]][1][9]);
                dat.push(data[i][getKeys(data)[i]][1][10]);
                dat.push(data[i][getKeys(data)[i]][1][11]);

                putlegende(getKeys(data)[i], randomColor(i));
                var donnees = {
                    fillColor: randomColor(i),
                    strokeColor: randomColor(i),
                    pointColor: randomColor(i),
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: randomColor(i),
                    data: dat[0]
                };

                dataset.push(donnees);
            }

            var lineChartData = {
                labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
                datasets: dataset

            };
            var chart1 = document.getElementById("line").getContext("2d");
            var myLine = new Chart(chart1).Line(lineChartData, {
                responsive: true
            });
            return dataset;
        }

        function randomColor(i) {
            var colo = ["#edc240", "#afd8f8", "#cb4b4b", "#4da74d", "#9440ed"];
            var j = i % 5;
            var color = ["rgba(48, 164, 255, 1)", "rgba(220,220,220,1)", "rgba(48, 164, 255, 1)", "rgba(48, 164, 255, 1)", "rgba(48, 164, 255, 1)"];
            return colo[j];
        }

        function getDataSetsBar(data) {
            var datasets = new Array(); //data[i][getKeys(data)[i]][1]
            //alert(getElement(data[1][getKeys(data)[1]][1]));                 
            for (var i = 0; i < data.length; i++) {
                var dat = new Array();
                dat.push(data[i][getKeys(data)[i]][0][0]);
                dat.push(data[i][getKeys(data)[i]][0][1]);
                dat.push(data[i][getKeys(data)[i]][0][2]);
                dat.push(data[i][getKeys(data)[i]][0][3]);
                dat.push(data[i][getKeys(data)[i]][0][4]);
                dat.push(data[i][getKeys(data)[i]][0][5]);
                dat.push(data[i][getKeys(data)[i]][0][6]);
                dat.push(data[i][getKeys(data)[i]][0][7]);
                dat.push(data[i][getKeys(data)[i]][0][8]);
                dat.push(data[i][getKeys(data)[i]][0][9]);
                dat.push(data[i][getKeys(data)[i]][0][10]);
                dat.push(data[i][getKeys(data)[i]][0][11]);

                var donnees = {
                    fillColor: randomColor(i),
                    strokeColor: randomColor(i),
                    highlightFill: "rgba(220,220,220,0.75)",
                    highlightStroke: randomColor(i),
                    data: dat[0]
                };
                datasets.push(donnees);
            }
            var barChartData = {
                labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
                datasets: datasets

            };
            var chart2 = document.getElementById("bar").getContext("2d");
            var barchar = new Chart(chart2).Bar(barChartData, {
                responsive: true
            });
            return datasets;
        }

        function view(data) {
            getDataSetsLine(data);
            //drawlinechart(data);
            drawbarchart(data);
        }
        function getKeys(data) {
            var obj = data,
                    keys = new Array();
            for (var i = 0; i < data.length; i++) {
                for (var k in obj[i]) {
                    keys.push(k);
                }

            }
            return keys;
        }


        function charger() {

            // on lance une requête AJAX
            $.ajax({
                url: "Statistique",
                type: 'POST',
                success: function (data) {
                    //alert(data);
                    view(data);

                }
            });
            // on relance la fonction
            // on exécute le chargement toutes les 5 secondes
        }
        charger();
    </script> 
</c:if>

<script src="admin/js/jquery.scrollTo.min.js"></script>

<script src="js/dist/js/bootstrap-multiselect.js" type="text/javascript"></script>

<script src="admin/assets/fancybox/source/jquery.fancybox.pack.js"></script>

<!-- MEGAFOLIO PRO GALLERY JS FILES  -->
<script type="text/javascript" src="admin/assets/metr-folio/js/jquery.metro-gal.plugins.min.js"></script>
<script type="text/javascript" src="admin/assets/metr-folio/js/jquery.metro-gal.megafoliopro.js"></script>
<script type="text/javascript" src="admin/assets/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript" src="admin/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="admin/assets/data-tables/DT_bootstrap.js"></script>
<script src="admin/js/jquery.scrollTo.min.js"></script>

<!--common script for all pages-->
<script src="admin/js/common-scripts.js"></script>


<script src="js/jquery.dataTables.js" type="text/javascript"></script>  
<script src="js/dataTables.responsive.js" type="text/javascript"></script>
<script src="js/dataTables.buttons.js" type="text/javascript"></script>          
<script src="js/dataTables.select.js" type="text/javascript"></script>   
<script src="js/buttons.colVis.js" type="text/javascript"></script>
<script src="js/jszip.js" type="text/javascript"></script>
<script src="js/buttons.jqueryui.js" type="text/javascript"></script>
<script src="js/buttons.html5.js" type="text/javascript"></script>
<script src="js/buttons.print.js" type="text/javascript"></script>
<script src="js/dataTables.autoFill.js" type="text/javascript"></script>
<script src="js/buttons.flash.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/print.css" type="text/css" media="print" />

<script src="js/produit.js" type="text/javascript"></script>

<script src="js/ajaxrequette.js" type="text/javascript"></script>
<script src="js/effets.js" type="text/javascript"></script>
<script>

        $('.simpletabless').DataTable({
            dom: "Bfrtip",
            responsive: true,
            autoFill: true,
            info: true,
            //scrollY: 250,
            paging: true,
            // stateSave: true,
            header: true,
            lengthChange: true,
            //keys: true,
            //select: true,
            lengthMenu: [
                [10, 25, 50, -1],
                ['10 lignes', '25 lignes', '50 lignes', 'Monter Tous']

            ],
            //  stateSave: true,
            buttons: [
                // 'columnsToggle',
                {
                    extend: 'colvis',
                    collectionLayout: ' three-column ',
                    text: 'Colonne',
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
                            message: 'charly messi Decca',
                            //title: 'Data export',
                            // text: '<i class="fa fa-file-pdf-o"></i>',
                            titleAttr: 'PDF',
                            orientation: 'landscape',
                            //pageSize: 'LEGAL',
                            download: 'open',
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
                    message: 'merci charly',
                    // autoPrint: false,
                    // title: function(){return 'foo.title'; },
                    //  footer: true,

                    customize: function (win) {
                        $(win.document.body).addClass('display').css('font-size', '10pt')
                                .prepend(
                                        '<img src="data:image/png;base64,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" style="position:absolute; top:5; left:0; bottom:150; margin-bottom:150px;" />'
                                        );

                        $(win.document.body).find('h1').css('text-align', 'center');
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
            }});

        $('.tags').keypress(function () {
            $.ajax({
                url: "Auto",
                type: "post",
                data: '',
                success: function (data) {
                    $(".tags").autocomplete({
                        source: data
                    });
                }, error: function (data, status, er) {
                    console.log(data + "_" + status + "_" + er);
                }
            });
        });
        $('.tag').keypress(function () {
            $.ajax({
                url: "autocompleteproduit",
                type: "post",
                data: '',
                success: function (data) {
                    $(".tag").autocomplete({
                        source: data
                    });
                }, error: function (data, status, er) {
                    console.log(data + "_" + status + "_" + er);
                }
            });
        });
</script>


<script type="text/javascript">

    jQuery(document).ready(function () {

        var api = jQuery('.metro-gal-container').megafoliopro(
                {
                    filterChangeAnimation: "pagebottom", // fade, rotate, scale, rotatescale, pagetop, pagebottom,pagemiddle
                    filterChangeSpeed: 400, // Speed of Transition
                    filterChangeRotate: 99, // If you ue scalerotate or rotate you can set the rotation (99 = random !!)
                    filterChangeScale: 0.6, // Scale Animation Endparameter
                    delay: 20,
                    defaultWidth: 980,
                    paddingHorizontal: 10,
                    paddingVertical: 10,
                    layoutarray: [9, 11, 5, 3, 7, 12, 4, 6, 13]		// Defines the Layout Types which can be used in the Gallery. 2-9 or "random". You can define more than one, like {5,2,6,4} where the first items will be orderd in layout 5, the next comming items in layout 2, the next comming items in layout 6 etc... You can use also simple {9} then all item ordered in Layout 9 type.
                });
        // FANCY BOX ( LIVE BOX) WITH MEDIA SUPPORT
        jQuery(".fancybox").fancybox();
        // THE FILTER FUNCTION
        jQuery('.filter').click(function () {
            jQuery('.filter').each(function () {
                jQuery(this).removeClass("selected")
            });
            api.megafilter(jQuery(this).data('category'));
            jQuery(this).addClass("selected");
        });
    });</script>


<script>
    $(document).ready(function () {

        $('#example-getting-started').multiselect({
            enableFiltering: true,
            maxHeight: 400,
            dropUp: true,
            buttonText: function (options, select) {
                if (options.length === 0) {
                    return 'Aucun Controleur Selectionner ...';
                } else if (options.length > 3) {
                    return 'plus de 3 cotroleur selectionner!';
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
        $('.articleselect').multiselect({
            enableFiltering: true,
            maxHeight: 400,
            dropUp: true,
            buttonText: function (options, select) {
                if (options.length === 0) {
                    return 'Aucun Articles Selectionner ...';
                } else if (options.length > 3) {
                    return 'plus de 3 Articles selectionner!';
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
        $('.datepicker').datepicker({
            weekStart: 1,
            color: '{color}',
            buttonImageOnly: true

        });
        $('#detaill').click(function () {
            $('.detail').toggle();
        });
        $('.detail').hide();
    });</script>      


<script type="text/javascript" src="admin/assets/bootstrap-toggle-buttons/static/js/jquery.toggle.buttons.js"></script>

<script type="text/javascript" src="admin/assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
<script type="text/javascript" src="admin/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
<script type="text/javascript" src="admin/assets/clockface/js/clockface.js"></script>
<script type="text/javascript" src="admin/assets/jquery-tags-input/jquery.tagsinput.min.js"></script>

<script type="text/javascript" src="admin/assets/bootstrap-daterangepicker/date.js"></script>
<script type="text/javascript" src="admin/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="admin/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript" src="admin/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
<script src="js/myjs/controllers/ContollerPersonnels.js" type="text/javascript"></script>

<c:if test="${vue=='Accueil' or vue=='notification'}">
    <script src="js/myjs/controllers/notifications.js" type="text/javascript"></script>
</c:if>



<!--script for this page-->
<script src="admin/js/form-component.js"></script>
<!-- END JAVASCRIPTS -->

<script language="javascript" type="text/javascript">

    $(function () {

        $.configureBoxes();
    });</script>

<c:if test="${vue=='Accueil'}">

    <script>

        $('.date').glDatePicker();
        var mois = ["Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"];
        var getspecialDay = function () {

            var response = new Array();
            $.post(
                    "agenda",
                    {action: "get_special_event", idPersonnel: $(".notification").attr("id")},
                    function (data) {

                        console.log(data);
                        if (data.length === 0) {

                            drawCalendar(response);
                        } else {

                            for (var i = 0; i < data.length; i++) {
                                var result = {date: new Date(parseInt(data[i][0]), parseInt(data[i][1]) - 1, parseInt(data[i][2])), data: {message: data[i][3]}, repeatMonth: false};
                                response.push(result);
                            }

                            //console.log(response);
                            drawCalendar(response);

                        }
                    },
                    "json"
                    );

            //alert("vous avez des ");
            return response;

        };

        getspecialDay();
        //affichage du calendrier;
        function drawCalendar(daySpecial) {

            $('#exempletest').glDatePicker(
                    {showAlways: true,
                        cssName: 'darkneon',
                        selectedDate: new Date(),
                        zIndex: 0,
                        specialDates: daySpecial,
                        onClick: function (target, cell, date, data) {
                            target.val(date.getFullYear() + ' - ' +
                                    (date.getMonth() + 1) + ' - ' +
                                    date.getDate());
                            if (data !== null) {
                                alert(data.message + '\n le ' + date.getDate() + " " + mois[(date.getMonth())] + " " + date.getFullYear());
                            }
                            $('.date').val(date.getDate() + '/ ' +
                                    (date.getMonth() + 1) + ' /' +
                                    date.getFullYear());
                        }});
        }</script>
    </c:if>
    <c:if test="${vue == 'agenda'}">

    <script type="text/javascript">
        function getAll() {
            $('#external-events div.external-event').each(function () {

                // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
                // it doesn't need to have a start or end
                var eventObject = {
                    title: $.trim($(this).text()) // use the element's text as the event title
                };

                // store the Event Object in the DOM element so we can get to it later
                $(this).data('eventObject', eventObject);

                // make the event draggable using jQuery UI
                $(this).draggable({
                    zIndex: 999,
                    revert: true, // will cause the event to go back to its
                    revertDuration: 0  //  original position after the drag
                });

            });

        }
        $("#new_work").keypress(function (event) {
            if (event.keyCode === 13) {
                var val = $(this).val();
                $(this).val("");
                $("#external-events").prepend("<div class='external-event label'>" + val + "</div>");
                getAll();
            }

        });

    </script>
    <script src="admin/js/external-dragging-calendar.js" type="text/javascript"></script>   

</c:if>