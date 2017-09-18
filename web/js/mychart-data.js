$(document).ready(function(){
    var viewLineChartData=function(response){
        var lineChartData = {
			labels : ['Janvier','Février','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','Decembre'],
			datasets : [
				{
					label: "My First dataset",
					fillColor : "rgba(220,220,2,0.5)",
					strokeColor : "rgba(220,220,2,0.8)",
					pointColor : "rgba(220,220,2,0.8)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(220,220,2,0.8)",
					data : [response[0],response[1],response[2],response[3],response[4],response[5],response[6],response[7],response[8],response[9],response[10],response[11]]
				},
                                
				{
					label: "My Second dataset",
					fillColor : "rgba(48, 164, 255, 0.2)",
					strokeColor : "rgba(48, 164, 255, 1)",
					pointColor : "rgba(48, 164, 255, 1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(48, 164, 255, 1)",
					data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
				},
				{
					label: "troisieme graphe",
					fillColor : "rgba(48, 164, 255, 0.2)",
					strokeColor : "rgba(48, 164, 2, 1)",
					pointColor : "rgba(48, 164, 2, 1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(48, 164, 255, 1)",
					data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
				}
			]

		};
                var chart1 = document.getElementById("line-chart").getContext("2d");
	           window.myLine = new Chart(chart1).Line(lineChartData, {
		   responsive: true
	         });
                       
    };
    
   var randomScalingFactor = function(){ return Math.round(Math.random()*1000);};
        $.post(
                      'Statistique', // Un script java que l'on va créer juste après
                            {
                    //  action : "stat"
                            },
                            function(response){
                                alert(response);
                                viewLineChartData(response);
                                
                                 },
                            'json' 
                            ); 
               
       
   
       

	
	
		
	var barChartData = {
			labels : ['Janvier','Février','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','Decembre'],
			datasets : [
				{
					fillColor : "rgba(220,220,2,0.5)",
					strokeColor : "rgba(220,220,2,0.8)",
					highlightFill: "rgba(220,220,220,0.75)",
					highlightStroke: "rgba(220,220,220,1)",
					data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
				},
				{
					fillColor : "rgba(48, 164, 255, 0.2)",
					strokeColor : "rgba(48, 164, 255, 0.8)",
					highlightFill : "rgba(48, 164, 255, 0.75)",
					highlightStroke : "rgba(48, 164, 255, 1)",
					data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
				},
				{
					fillColor : "rgba(48, 164, 2, 0.2)",
					strokeColor : "rgba(48, 164, 2, 0.8)",
					highlightFill : "rgba(48, 164, 255, 0.75)",
					highlightStroke : "rgba(48, 164, 255, 1)",
					data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
				}
			]
	
		};

	var pieData = [
				{
					value: 300,
					color:"#30a5ff",
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
						color:"#30a5ff",
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


	
	
	var chart2 = document.getElementById("bar-chart").getContext("2d");
	window.myBar = new Chart(chart2).Bar(barChartData, {
		responsive : true
	});
	var chart3 = document.getElementById("doughnut-chart").getContext("2d");
	window.myDoughnut = new Chart(chart3).Doughnut(doughnutData, {responsive : true
	});
	var chart4 = document.getElementById("pie-chart").getContext("2d");
	window.myPie = new Chart(chart4).Pie(pieData, {responsive : true
	});
	

            
       
			
}); 




/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


