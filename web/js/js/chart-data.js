var randomScalingFactor = function () {
    return Math.round(Math.random() * 1000)
};

function view(data) {

    var lineChartData = {
        labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
        datasets: [
            {
                label: "My First dataset",
                fillColor: "rgba(220,220,220,0.2)",
                strokeColor: "rgba(220,220,220,1)",
                pointColor: "rgba(220,220,220,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: [data[0][0], data[0][1], data[0][2], data[0][3], data[0][4], data[0][5], data[0][6], data[0][7], data[0][8], data[0][9], data[0][10], data[0][11]]
            },
            {
                label: "My Second dataset",
                fillColor: "rgba(48, 164, 255, 0.2)",
                strokeColor: "rgba(48, 164, 255, 1)",
                pointColor: "rgba(48, 164, 255, 1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(48, 164, 255, 1)",
                data: [data[1][0], data[1][1], data[1][2], data[1][3], data[1][4], data[1][5], data[1][6], data[1][7], data[1][8], data[1][9], data[1][10], data[1][11]]
            }
        ]

    };
    var chart1 = document.getElementById("line-chart").getContext("2d");
    var myLine = new Chart(chart1).Line(lineChartData, {
        responsive: true
    });




    var barChartData = {
        labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
        datasets: [
            {
                fillColor: "rgba(220,220,220,0.5)",
                strokeColor: "rgba(220,220,220,0.8)",
                highlightFill: "rgba(220,220,220,0.75)",
                highlightStroke: "rgba(220,220,220,1)",
                data: [data[2][0], data[2][1], data[2][2], data[2][3], data[2][4], data[2][5], data[2][6], data[2][7], data[2][8], data[2][9], data[2][10], data[2][11]]

            },
            {
                fillColor: "rgba(48, 164, 255, 0.2)",
                strokeColor: "rgba(48, 164, 255, 0.8)",
                highlightFill: "rgba(48, 164, 255, 0.75)",
                highlightStroke: "rgba(48, 164, 255, 1)",
                data: [data[3][0], data[3][1], data[3][2], data[3][3], data[3][4], data[3][5], data[3][6], data[3][7], data[3][8], data[3][9], data[3][10], data[3][11]]
            }
        ]

    };

    var chart2 = document.getElementById("bar-chart").getContext("2d");
   var barchar = new Chart(chart2).Bar(barChartData, {
        responsive: true
    });
}
var pieData = [
    {
        value: 100,
        color: "#30a5ff",
        highlight: "#62b9fb",
        label: "Blue"
    },
    {
        value: 50,
        color: "#ffb53e",
        highlight: "#fac878",
        label: "Orange"
    },
    {
        value: 100,
        color: "#1ebfae",
        highlight: "#3cdfce",
        label: "Teal"
    },
    {
        value: 120,
        color: "#f9243f",
        highlight: "#f6495f",
        label: "Red"
    }

];

var doughnutData = [
    {
        value: 300,
        color: "#30a5ff",
        highlight: "#62b9fb",
        label: "Blue"
    },
    {
        value: 50,
        color: "#ffb53e",
        highlight: "#fac878",
        label: "Orange"
    },
    {
        value: 100,
        color: "#1ebfae",
        highlight: "#3cdfce",
        label: "Teal"
    },
    {
        value: 120,
        color: "#f9243f",
        highlight: "#f6495f",
        label: "Red"
    }

];

window.onload = function () {

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


    var chart3 = document.getElementById("doughnut-chart").getContext("2d");
    window.myDoughnut = new Chart(chart3).Doughnut(doughnutData, {responsive: true
    });
    var chart4 = document.getElementById("pie-chart").getContext("2d");
    window.myPie = new Chart(chart4).Pie(pieData, {responsive: true
    });

};