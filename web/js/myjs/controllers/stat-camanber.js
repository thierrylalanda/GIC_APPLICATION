$(document).ready(function () {


    $("#region").click(function () {
        $(".titre").text(" LA REGION " + $(this).children("option:selected").text());
        var param = {action: "allStatBudgaireRegion", region: $(this).val()};
        statcamanber(param);
    });
    $(".titre").text(" LA REGION " + $("#region").children("option:selected").text());
    var param = {action: "allStatBudgaireRegion", region: $("#region").val()};
    statcamanber(param);
    function getQuantite(res, lab) {

        alert(lab);
        var qte = 0;
        for (var i = 1; i < res.length; i++)
        {

            if (res[i][0] === lab) {
                qte = res[i][1];
            }



        }
        return qte;
    }


    function statcamanber(param) {
        $.ajax({
            url: "tableau_de_Bord_Budgetaire",
            data: param,
            type: 'POST',
            success: function (data) {
                console.log(data);
                var div = $(".stat-camanber .stat");
                div.html("");
                for (var i = 0; i < data.length; i++)
                {
                    var datas = new Array();
                    var k = 0;
                    if (data[i].length === 2) {
                        datas[k] = [data[i][1][0] + " : " + data[i][1][1] + "%", 100];
                    } else {


                        for (var j = 1; j < data[i].length; j++)
                        {

                            if (data[i][j][1] === 0) {

                                datas[k] = [data[i][j][0] + " : " + data[i][j][1] + "%", 0.1];
                            } else {
                                datas[k] = [data[i][j][0] + " : " + data[i][j][1] + "%", data[i][j][1]];
                            }

                            k++;
                        }
                    }


                    if (data[i][1][1] !== 0 || data[i][1][1] === 0) {

                        var label = '<h5 style="text-align: center" class="categorie">' + data[i][0] + '</h5>';
                        // div.append(label);
                        var camanber = '<div id="graph' + i + '" class="graph' + i + '  chart"></div><br><hr><br>';
                        div.append(camanber);
                        var clas = ".graph" + i;
                        var m = 0;
                        // alert(getQuantite(data[i],data[i][0]));
                        Highcharts.chart('graph' + i, {
                            chart: {
                                type: 'pie',
                                options3d: {
                                    enabled: true,
                                    alpha: 55,
                                    beta: 0
                                }
                            },
                            title: {
                                text: 'evolution consommation  bugetaire du magasin ' + label
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
                                    name: 'pourcentage',
                                    data: datas
                                }]
                        });
                    }
                }
            }
        });
    }












});