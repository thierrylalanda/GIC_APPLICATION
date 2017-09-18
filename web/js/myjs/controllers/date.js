



function getSeconde(time) {
    if (time.getSeconds() < 10)
    {
        return "0" + time.getSeconds();
    } else
        return time.getSeconds();
}
var mois = ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'];
function timer1() {

    setTimeout(function () {
        var time = new Date();
        var horloge = $("#horloge");
        var date = time.getDate() + "   " + mois[time.getMonth()] + "   " + time.getFullYear();
        if (time.getMinutes() < 10)
        {
            horloge.html("<div class='dd'>"+date + "<br>  " + time.getHours() + " : 0" + time.getMinutes() + " : " + getSeconde(time)+"</div>");
        } else
        {
            horloge.html("<div class='dd'>"+date + "<br>  " + time.getHours() + " : " + time.getMinutes() + " : " + getSeconde(time)+"</div>");
        }
        timer1();
    }, 1000);

}
setTimeout(function () {
    timer1();
},1000);


    