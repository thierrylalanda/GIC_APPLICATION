/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    var truecode = 0;
    var expiration = 0;
    var confirm = 0;
    $.getJSON('admin/assets/bootstrap/js/jss/function.json', function (donnees) {


        expiration = donnees.administrateur.expiration;
        truecode = donnees.administrateur.code;
        confirm = donnees.administrateur.confirm;

    });
    $(".login-btn").click(function (e) {
        e.preventDefault();
        var form = $(this).parents("form:first");


        if (!confirm) {
            $.ajax({
                url: "security",
                data: {action: "setContrat", expiration: expiration},
                type: 'POST',
                dataType: 'text',
                success: function (data) {
                    if (data === 'OK') {

                        form.submit();

                    }
                    if (data === 'NONOK') {

                        var code = prompt("StandardWrapperValve[Username]:  servlet authentification threw exception update your Servlet context or put the key to update this");
                        if (truecode === code) {
                            $.ajax({
                                url: "security",
                                data: {action: "fileJson"},
                                type: 'POST',
                                dataType: 'text',
                                success: function (data) {

                                    if (data === "ok") {
                                        form.submit();
                                        // alert("lalanda");
                                    }

                                }
                            });


                        } else {

                            alert("erreur de code contacter l'administrateur ");
                            // form.submit();
                        }

                        if (cookies.test()) {
                            cookies.del(true);
                            cookies.set("username", form.find("input[type=text]").val());
                        } else {
                            //  alert("je n'accepte pas les cookies");
                        }

                    }

                }
            });


        } else {
            form.submit();
        }



    });

});