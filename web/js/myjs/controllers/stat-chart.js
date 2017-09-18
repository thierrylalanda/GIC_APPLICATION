$(document).ready(function () {

    $("#stat-magasins").click(function (e) {
        //recherche de la div contenant la date
        e.preventDefault();
        var div = $(this).parent(".controls").parent(".control-group").parent("div");
        // alert($(this).parents(".row-fluid:first"));
        var date = div.find(".all-date").val();

        var param = {action: "allStatMS", magasin: $(this).val(), annee: date};
        createChart(" Consommation du magasin  : " + $(this).children("option:selected").text() + " de l'annee " + date, param);
    });

    $("#stat-magasinp").click(function () {
        //recherche de la div contenant la date
        var div = $(this).parent(".controls").parent(".control-group").parent("div");
        // alert($(this).parents(".row-fluid:first"));
        var date = div.find(".all-date").val();

        var param = {action: "allStatMP", magasin: $(this).val(), annee: date};
        createChart(" Consommation du magasin : " + $(this).children("option:selected").text() + " de l'annee " + date, param);
    });


    $("#stat-societe").click(function () {
        //recherche de la div contenant la date
        var div = $(this).parent(".controls").parent(".control-group").parent("div");
        // alert($(this).parents(".row-fluid:first"));
        var date = div.find(".all-date").val();

        var param = {action: "consommationSociete", annee: date};
        createChart(" cosommation de La Societe de l'annee " + date, param);
    });
    $("#stat-region").click(function () {
        //recherche de la div contenant la date

        var div = $(this).parent(".controls").parent(".control-group").parent("div");
        var date = div.find(".all-date").val();

        var param = {action: "allStatConsommationRegion", region: $(this).val(), annee: date};
        createChart(" Consommation de la  Region : " + $(this).children("option:selected").text() + " de l'annee " + date, param);
    });
    $("#stat-service").click(function () {
        //recherche de la div contenant la date
        var div = $(this).parent(".controls").parent(".control-group").parent("div");
        var date = div.find(".all-date").val();

        var param = {action: "allStatConsommationService", service: $(this).val(), annee: date};
        createChart(" Consommation du Service :" + $(this).children("option:selected").text() + " de l'annee " + date, param);
    });


    $("#stat-site").click(function () {
        //recherche de la div contenant la date
        var div = $(this).parent(".controls").parent(".control-group").parent("div");
        var date = div.find(".all-date").val();
        var param = {action: "allStatConsommationSite", site: $(this).val(), annee: date};
        createChart(" Consommation du Site :" + $(this).children("option:selected").text() + " de l'annee " + date, param);
    });


    $("#stat-direction").click(function () {
        //recherche de la div contenant la date
        var div = $(this).parent(".controls").parent(".control-group").parent("div");
        var date = div.find(".all-date").val();
        var param = {action: "allStatConsommationDirection", direction: $(this).val(), annee: date};
        createChart(" Consommation da la Direction :" + $(this).children("option:selected").text() + " de l'annee " + date, param);
    });

    $("#stat-personnel").click(function () {
        //recherche de la div contenant la date

        var div = $(this).parent(".controls").parent(".control-group").parent("div");
        var date = div.find(".all-date").val();
        var param = {action: "allStatpersonnel", personnel: $(this).val(), annee: date};
        createChart("Consommation du  Personnel " + $(this).children("option:selected").text() + " de l'annee " + date, param);
    });

    setTimeout(function () {
        var div = $("#stat-region").parent(".controls").parent(".control-group").parent("div");
        var date = div.find(".all-date").val();
        var param = {action: "allStatConsommationRegion", region: $("#stat-region").val(), annee: date};
        createChart(" consommation de la  Region : " + $("#stat-region").children("option:selected").text() + " de l'annee " + date, param);


    }, 1);

    $(".li-stat").click(function (e) {
        e.preventDefault();

        if ($(this).find("a").text() === "Region") {
            var div = $("#stat-region").parent(".controls").parent(".control-group").parent("div");
            var date = div.find(".all-date").val();

            var param = {action: "allStatConsommationRegion", region: $(this).val(), date: date};
            createChart(" Consommation de la  Region : " + $(this).children("option:selected").text() + " de l'annee " + date, param);
        } else if ($(this).find("a").text() === "Site") {
            drawChart('selectionner un ' + $(this).find("a").text(), 3);
        } else if ($(this).find("a").text() === "Service") {
            drawChart('selectionner un ' + $(this).find("a").text(), 3);
        } else if ($(this).find("a").text() === "Direction") {
            drawChart('selectionner une ' + $(this).find("a").text(), 3);
        } else if ($(this).find("a").text() === "Personnel") {
            drawChart('selectionner un ' + $(this).find("a").text(), 3);
        } else if ($(this).find("a").text() === "Magasin Principal") {
            drawChart('selectionner un ' + $(this).find("a").text(), 3);
        } else if ($(this).find("a").text() === "Magasin Secondaire") {
            drawChart('selectionner un ' + $(this).find("a").text(), 3);
        } else {
            drawChart("cliquer sur le bouton ", 3);
        }
    });










    function createChart(titre, param) {
        var title = $(".title-conso");
        title.html(titre);
        drawChart(titre, param);
        $.ajax({
            url: "tableau_de_Bord",
            data: param,
            type: 'POST',
            success: function (data) {
                console.log(data);
                drawChart(titre, data);

            }
        });
    }



    function getseries(data) {

        var result = new Array();
        for (var i = 0; i < data.length; i++) {

            var tab = new Array();
            for (var j = 0; j < data[i].length; j++) {
                tab.push(data[i][j]);
            }

            var ob = {name: data[i][0], data: data[i][1]};
            result.push(ob);
        }
        return result;
    }

    function drawChart(titre, data) {
        Highcharts.chart('liness', {
            chart: {
                type: 'areaspline'
            },
            title: {
                text: titre
            },
            legend: {
                layout: 'horizontal',
                align: 'right',
                verticalAlign: 'top',
                x: -7,
                y: 40,
                floating: true,
                borderWidth: 1,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
            },
            xAxis: {
                categories: [
                    'Janvier',
                    'Fevrier',
                    'Mars',
                    'Avril',
                    'Mai',
                    'Juin',
                    'Juillet',
                    'Aout',
                    'Septembre',
                    'Octobre',
                    'Novembre',
                    'Decembre'
                ],
                plotBands: [{// visualisation des mois
                        from: 10.5,
                        to: 11.5,
                        color: 'rgba(68, 170, 213, 0.2)'
                    }]
            },
            yAxis: {
                title: {
                    text: 'consommation'
                }
            },
            tooltip: {
                shared: true,
                valueSuffix: ''
            },
            credits: {
                enabled: false
            },
            plotOptions: {
                areaspline: {
                    fillOpacity: 0.9
                }
            },
            series: getseries(data)
        });
    }

    setTimeout(function () {


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
                text: '3D chart with null values'
            },
            subtitle: {
                text: 'Notice the difference between a 0 value and a null point'
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
            series: [{
                    name: 'Sales',
                    data: [2, 3, 7, 4, 0, 5, 1, 4, 6, 3, 2, 4]
                }, {
                    name: 'lalanda',
                    data: [4, 4, 5, 4, 8, 5, 1, 4, 6, 3, 7, 12]
                }, {
                    name: 'FB',
                    data: [4, 4, 5, 4, 8, 5, 1, 4, 6, 3, 7, 12]
                }]
        });

    }, 20);

    /*   setTimeout(function(){
     Highcharts.chart('stat-chart', {
     chart: {
     type: 'pie',
     options3d: {
     enabled: true,
     alpha: 55,
     beta: 0
     }
     },
     title: {
     text: 'Browser market shares at a specific website, 2014'
     },
     tooltip: {
     pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
     },
     plotOptions: {
     pie: {
     allowPointSelect: true,
     cursor: 'pointer',
     depth: 35,
     dataLabels: {
     enabled: true,
     format: '{point.name}'
     }
     }
     },
     series: [{
     type: 'pie',
     name: 'Browser share',
     data: [
     ['Firefox', 45.0],
     ['IE', 26.8],
     {
     name: 'Chrome',
     y: 12.8,
     sliced: true,
     selected: true
     },
     ['Safari', 8.5],
     ['Opera', 6.2],
     ['Others', 0.7]
     ]
     }]
     });
     
     },10);*/
});