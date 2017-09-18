/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    //alert("ok");
    // data
    /*var data = [
     { label: "Series1",  data: 10},
     { label: "Series2",  data: 30},
     { label: "Series3",  data: 90},
     { label: "Series4",  data: 70},
     { label: "Series5",  data: 80},
     { label: "Series6",  data: 110}
     ];*/
    /*var data = [
     { label: "Series1",  data: [[1,10]]},
     { label: "Series2",  data: [[1,30]]},
     { label: "Series3",  data: [[1,90]]},
     { label: "Series4",  data: [[1,70]]},
     { label: "Series5",  data: [[1,80]]},
     { label: "Series6",  data: [[1,0]]}
     ];*/
    $.ajax({
        url: "listeproduit?action=etatButgetMS",
        data: {},
        type: "post",
        dataType: 'json',
        success: function (data) {
            console.log(data);
        }
    });

    var data = [];
    var series = Math.floor(Math.random() * 10) + 1;
    for (var i = 0; i < series; i++)
    {
        data[i] = {label: "Series" + (i + 1), data: Math.floor(Math.random() * 100) + 1};
    }

    $.plot($("#graph6"), data,
            {
                series: {
                    pie: {
                        show: true,
                        radius: 1,
                        label: {
                            show: true,
                            radius: 2 / 3,
                            formatter: function (label, series) {
                                return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">' + label + '<br/>' + Math.round(series.percent) + '%</div>';
                            },
                            threshold: 0.1
                        }
                    }
                },
                legend: {
                    show: false
                }
            });



});

