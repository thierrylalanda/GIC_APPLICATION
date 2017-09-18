/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var form = $("#formtemplate");
    var confirmpassword = false;
    var confirmusername = false;
    var confirmlengt = false;
    $("input[type=password],input[type=text]").keyup(function () {
        var ucase = new RegExp("[A-Z]+");
        var lcase = new RegExp("[a-z]+");
        var num = new RegExp("[0-9]+");
        if ($("#password1").val().length >= 8) {
            $("#8char").removeClass("icon-remove");
            $("#8char").addClass("icon-ok");
            $("#8char").css("color", "#00A41E");
        } else {
            $("#8char").removeClass("icon-ok");
            $("#8char").addClass("icon-remove");
            $("#8char").css("color", "#FF0004");
        }

        if ($("#usernamex").val().length >= 8) {
            $(".char").removeClass("icon-remove");
            confirmlengt = true;
            $(".char").addClass("icon-ok");
            $(".char").css("color", "#00A41E");
        } else {
            confirmlengt = false;
            $(".char").removeClass("icon-ok");
            $(".char").addClass("icon-remove");
            $(".char").css("color", "#FF0004");
        }

        if (num.test($("#usernamex").val())) {
            $("#number").removeClass("icon-remove");
            confirmusername = true;
            $("#number").addClass("icon-ok");
            $("#number").css("color", "#00A41E");
        } else {
            confirmusername = false;
            $("#number").removeClass("icon-ok");
            $("#number").addClass("icon-remove");
            $("#number").css("color", "#FF0004");
        }

        if (ucase.test($("#password1").val())) {
            $("#ucase").removeClass("icon-remove");
            $("#ucase").addClass("icon-ok");
            $("#ucase").css("color", "#00A41E");
        } else {
            $("#ucase").removeClass("icon-ok");
            $("#ucase").addClass("icon-remove");
            $("#ucase").css("color", "#FF0004");
        }

        if (lcase.test($("#password1").val())) {
            $("#lcase").removeClass("icon-remove");
            $("#lcase").addClass("icon-ok");
            $("#lcase").css("color", "#00A41E");
        } else {
            $("#lcase").removeClass("icon-ok");
            $("#lcase").addClass("icon-remove");
            $("#lcase").css("color", "#FF0004");
        }

        if (num.test($("#password1").val())) {
            $("#num").removeClass("icon-remove");
            $("#num").addClass("icon-ok");
            $("#num").css("color", "#00A41E");
        } else {
            $("#num").removeClass("icon-ok");
            $("#num").addClass("icon-remove");
            $("#num").css("color", "#FF0004");
        }

        if ($("#password1").val() === $("#password2").val() && $("#password1").val() !== "") {
            confirmpassword = true;
            $("#pwmatch").removeClass("icon-remove");
            $("#pwmatch").addClass("icon-ok");
            $("#pwmatch").css("color", "#00A41E");
        } else {
            $("#pwmatch").removeClass("icon-ok");
            $("#pwmatch").addClass("icon-remove");
            $("#pwmatch").css("color", "#FF0004");
        }
    });
    var confirm;
    $("#usernamex").focusout(function () {
        $.ajax({
            url: 'SettingPersonnal',
            data: {action: "getUsername", username: $(this).val()},
            type: "POST",
            dataType: 'text',
            success: function (data) {

                if (data === "OK") {

                    $(this).addClass("icon - ok");

                }
                if (data !== "OK") {
                    $(this).removeClass("icon - remove");
                    $.gritter.add({
                        title: 'Erreur',
                        text: "nom utilisateur non disponible",
                        sticky: false
                    });
                }

            }
        });
    });
    $("#Confirmpassword").focusout(function () {
        if (confirm) {
            $(this).parent(".controls").parent(".control-group").addClass("success");
            $(this).parent(".controls").addClass("input-icon").append('<span data-original-title="correct" class="input-success tooltips"><i class="icon-ok"></i></span>');
        } else {
            $(this).parent(".controls").parent(".control-group").addClass("error");
            $(this).parent(".controls").addClass("input-icon").append('<span data-original-title="incorrect" class="input-error tooltips"><i class="icon-exclamation-sign"></i></span>');
        }
    });
    $("#Confirmpassword").focusin(function () {

        $(this).parent(".controls").parent(".control-group").removeClass("success").removeClass("error");
        $(this).parent(".controls").addClass("input-icon").children("span").remove();
    });
//pour soumettre le formulaire
    $(form).find("input[type]").keyup(function () {
        if ($(this).val() === "") {
            $(this).css({
                borderColor: 'red',
                color: 'red'
            });
        } else {
            $(this).css({
                borderColor: '',
                color: ''
            });
        }
    });
    $(form).find("input[type]").focusout(function () {
        if ($(this).val() === "") {
            $(this).css({
                borderColor: 'red',
                color: 'red'
            });
        } else {
            $(this).css({
                borderColor: '',
                color: ''
            });
        }
    });
//confirmation de l'ajout d'un personnel
    $('.finish').click(function (e) {
        e.preventDefault();
        // alert($("input[name=confirm]:checked").val());
        var vide = false;
        var form = $(this).parent("ul").parent("#tabsleft").parent("#formtemplate");
        $.each($(form).find("input[type]"), function () {
            if ($(this).val() === "") {
                $(this).css({
                    borderColor: 'red',
                    color: 'red'
                });
                vide = true;
            } else {
                $(this).css({
                    borderColor: 'green',
                    color: 'green'
                });
            }
        });
        $.each($(form).find("select"), function () {
            if ($(this).val() === "") {
                $(this).css({
                    borderColor: 'red',
                    color: 'red'
                });
                vide = true;
            } else {
                $(this).css({
                    borderColor: 'green',
                    color: 'green'
                });
            }
        });
        if (!vide) {

            if (confirmpassword) {
//alert($(form).serialize());
                if ($("input[name=confirm]:checked").val() === "oui") {


                    $.ajax({
                        url: $(form).attr("action"),
                        data: $(form).serialize(),
                        type: $(form).attr("method"),
                        dataType: 'text',
                        success: function (data) {
                            console.log(data);
                            if (data === "OK") {

                                $.gritter.add({
                                    title: 'confirmation',
                                    text: 'personnel ajouter avec success',
                                    sticky: false
                                });
                                setTimeout(function () {
                                    //$(".getallpersonnel").trigger("click");
                                    document.location = "admin?vue=perso&action=getAll";
                                }, 2000);
                                //alert(data);
                            }
                            if (data !== "OK") {
                                $.gritter.add({
                                    title: 'Erreur',
                                    text: data,
                                    sticky: false
                                });
                            }

                        }
                    });
                } else {
                    $.gritter.add({
                        title: 'veuillez confirmer les donnees saisies',
                        text: ' ',
                        sticky: false
                    });
                }
            } else {
                $.gritter.add({
                    title: 'confirmation',
                    text: 'confirmation mot de passe incorrecte',
                    sticky: false
                });
            }




        } else {

            $.gritter.add({
                title: 'verification',
                text: 'veuillez remplir tous les champ',
                sticky: false
            });
        }





    });
//confirmation de l'ajout d'un magasin
    $('.finishM').click(function (e) {
        e.preventDefault();
        var vide = false;
        var form = $(this).parent("ul").parent("#tabsleft").parent("#formtemplate");
        $.each($(form).find("input[type]"), function () {
            if ($(this).val() === "") {
                $(this).css({
                    borderColor: 'red',
                    color: 'red'
                });
                vide = true;
            } else {
                $(this).css({
                    borderColor: 'green',
                    color: 'green'
                });
            }
        });
        if (!vide) {


//alert($(form).serialize());
            $.ajax({
                url: $(form).attr("action"),
                data: $(form).serialize(),
                type: $(form).attr("method"),
                dataType: 'text',
                success: function (data) {
                    alert(data);
                    $.gritter.add({
                        title: 'confirmation',
                        text: 'magasin ajouter avec success',
                        sticky: false
                    });
                    setTimeout(function () {
                        location.reload();
                    }, 3000);
                }
            });
        } else {

            $.gritter.add({
                title: 'verification',
                text: 'veuillez remplir tous les champs',
                sticky: false
            });
        }





    });
    $('#addstockbtn').click(function (e) {
        e.preventDefault();
        var form = $("#addstockform");
        var idMagasin = $(".idmagasin").val();
        form.attr("action", form.attr("action") + "&magasin=" + idMagasin);
        form.submit();
    });
    var extensionsValides = new Array("png");
    function getExtension(filename)
    {
        var parts = filename.split(".");
        return (parts[(parts.length - 1)]);
    }

    function verifTaille(file) {
// la taille maximale doit etre de 3:o
        if (file.size > 3 * 1024 * 1024 * 1024) {
            $.gritter.add({
// (string | mandatory) the heading of the notification
                title: 'taille!',
                // (string | mandatory) the text inside the notification
                text: "Votre logo doit  avoir une taille d'au plus 3Ko "
            });
            return false;
        } else
            return true;
    }


// vérifie l'extension d'un fichier uploadé
// champ : id du champ type file
// listeExt : liste des extensions autorisées
    function verifFileExtension(champ, listeExt)
    {
        filename = document.getElementById(champ).value.toLowerCase();
        fileExt = getExtension(filename);
        for (i = 0; i < listeExt.length; i++)
        {
            if (fileExt === listeExt[i])
            {
//alert("OK");
                return (true);
            }
        }
        var extension = " ";
        for (j = 0; j < listeExt.length; j++) {
            extension += " " + listeExt[j];
        }

//alert("Votre logo doit être au formats suivants "+extension);
        $.gritter.add({
// (string | mandatory) the heading of the notification
            title: 'erreur d\'extension!',
            // (string | mandatory) the text inside the notification
            text: "Votre logo doit être au formats suivants " + extension
        });
        return (false);
    }


    var f = document.getElementById('file');
    //pour soumettre la photo
    $('#submitfile').click(function (e) {
        e.preventDefault();
        var form = $("#fileForm");
        var file = f.files[0];
        if (f.value !== "") {
            if (verifFileExtension("file", extensionsValides)) {
                if (verifTaille(file)) {
                    form.submit();
                }

            }
        } else {
            $.gritter.add({
// (string | mandatory) the heading of the notification
                title: 'champs vide!',
                // (string | mandatory) the text inside the notification
                text: "veuillez entre une photo  "
            });
        }


    });
    // La fonction est à ajouter dans le fichier additionnal-methods.js
    function maxfilesize(element, params) {
        var elementsize;
        try {
            elementsize = element.files[0].size;
        } catch (e) {
            var browserInfo = navigator.userAgent.toLowerCase();
            if (browserInfo.indexOf("msie") === -1) {
                var fso = new ActiveXObject("Scripting.FileSystemObject");
                elementsize = fso.getFile(element.value).size;
            } else {
                return true;
            }
        }
        var size = params[0], typesize = params[1];
        if (typesize === "Ko") {
            size *= 1024;
        } else if (typesize === "Mo") {
            size *= 1024 * 1024;
        } else if (typesize === "Go") {
            size *= 1024 * 1024 * 1024;
        }
        return this.optional(element) || elementsize < size;
    }

    //soumettre le formulaire d'insertion de la societe
    $("#submitsociete").click(function (e) {
        e.preventDefault();
        var form = $("#societeform");
        form.attr("action", form.attr("action") + "&" + form.serialize());
        form.submit();
    });
});
