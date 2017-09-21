/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    function getKeys(data) {
        var obj = data[0];
        var keys = new Array();
        if (Object.keys) {
            keys = Object.keys(obj);
        } else {
            for (var k in obj) {
                keys.push(k);
            }
        }

        return keys;
    }
    function getwidth(data) {
        var obj = data[0];
        var keys = new Array();
        for (var k in obj) {
            keys.push("*");
        }

        return keys;
    }

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
        return jour + "/" + mois + "/" + date.getFullYear() + "  " + date.getHours() + " h " + date.getMinutes() + " mn " + date.getSeconds();
    }
    function getContent(donnee) {
        var content = new Array();
        content.push({
            // if you specify width, image will scale proportionally
            image: testImageDataUrl,
            width: 70,
            height: 70,
            style: 'img'

        });
        content.push({text: "region " + $(".notification").attr("region"), style: 'subheader'});
        content.push({text: societeDataUrl, style: 'header'});
        content.push({text: 'Consommation des ' + categorie + ' ' + title_header, style: 'title'});
        content.push({text: 'periode ' + parseDate(periode), style: 'subtitle'});
        if (this.alldata.length !== 0) {

            for (var j = 0; j < this.alldata.length; j++) {
                this.table = getBody(this.alldata[j].data);
                content.push({text: this.alldata[j].legende, fontSize: 10, bold: true, margin: [0, 15, 0, 3]});
                content.push({
                    style: 'tableExample',
                    table: {
                        widths: this.table.widht,
                        body: this.table.body
                    },
                    layout: {
                        hLineWidth: function (i, node) {
                            return (i === 0 || i === node.table.body.length) ? 2 : 1;
                        },
                        vLineWidth: function (i, node) {
                            return (i === 0 || i === node.table.widths.length) ? 2 : 1;
                        },
                        hLineColor: function (i, node) {
                            return (i === 0 || i === node.table.body.length) ? 'white' : 'white';
                        },
                        vLineColor: function (i, node) {
                            return (i === 0 || i === node.table.widths.length) ? 'white' : 'white';
                        },
                        // paddingLeft: function(i, node) { return 4; },
                        // paddingRight: function(i, node) { return 4; },
                        // paddingTop: function(i, node) { return 2; },
                        // paddingBottom: function(i, node) { return 2; },
                        // fillColor: function (i, node) { return null; }
                        fillColor: function (i, node) {
                            return (i === 0) ? '#4A8BC2' : (i % 2 === 0 && i !== 0) ? '#CCCCCC' : null;
                        }
                    }
                });
                content.push({
                    style: 'tableFooter',
                    table: {
                        widths: ["*", 'auto'],
                        body: [[{text: 'Total', style: ''},
                                {text: this.table.total + ' FCFA', style: ''}]]
                    },
                    layout: {
                        hLineWidth: function (i, node) {
                            return (i === 0 || i === node.table.body.length) ? 2 : 1;
                        },
                        vLineWidth: function (i, node) {
                            return (i === 0 || i === node.table.widths.length) ? 2 : 1;
                        },
                        hLineColor: function (i, node) {
                            return (i === 0 || i === node.table.body.length) ? 'white' : 'white';
                        },
                        vLineColor: function (i, node) {
                            return (i === 0 || i === node.table.widths.length) ? 'white' : 'white';
                        },
                        // paddingLeft: function(i, node) { return 4; },
                        // paddingRight: function(i, node) { return 4; },
                        // paddingTop: function(i, node) { return 2; },
                        // paddingBottom: function(i, node) { return 2; },
                        // fillColor: function (i, node) { return null; }
                        fillColor: function (i, node) {
                            return (i === 0) ? '#31a6b1' : null;
                        }
                    }
                });
            }
        }
        return content;
    }
    function getBody(data) {
        var body = new Array();
        var cles = getKeys(data);
        var _header = new Array();
        for (var k = 0; k < cles.length; k++) {
            _header.push({text: cles[k], style: 'tableHeader'});
        }

        body.push(_header);
        var total = 0;
        for (var i = 0; i < data.length; i++) {

            var _content = new Array();
            for (var j = 0; j < cles.length; j++) {
                if (cles[j] === "PT") {
                    total += parseInt(data[i].PT);
                }

//alert(data[i]['PT']);
                _content.push({text: data[i][cles[j]], style: 'tableContent'});
            }


            body.push(_content);
        }

        return {body: body, widht: getwidth(data), total: total};
    }


    function drawTable() {

        this.alldata = [{legende: "service helpdesk", data: [{qte: 20, PT: 2500, Article: 'PRC14', personnel: "lalanda"},
                    {qte: 20, PT: 2500, Article: 'PRC14', personnel: "lalanda"},
                    {qte: 20, PT: 2500, Article: 'PRC14', personnel: "lalanda"}]
            },
            {legende: "service Informatique", data: [{qte: 20, PT: 2500, Article: 'PRC14', personnel: "lalanda"},
                    {qte: 20, PT: 2500, Article: 'PRC14', personnel: "lalanda"},
                    {qte: 20, PT: 2500, Article: 'PRC14', personnel: "lalanda"},
                    {qte: 20, PT: 2900, Article: 'PRC14', personnel: "thierry"}]
            }];
        $(".all-table").html("");
        $(".all-table").hide();
        for (var i = 0; i < this.alldata.length; i++) {

            var table = document.createElement("table");
            var thead = document.createElement("thead");
            var tbody = document.createElement("tbody");
            var tfoot = document.createElement("tfoot");
            var caption = document.createElement("caption");


            table.setAttribute("class", "table table-hover table-responsive table-bordered table-rapport rapport");
            caption.innerHTML = this.alldata[i].legende;
            var head = getKeys(this.alldata[i].data);
            table.appendChild(caption);
            var trh = document.createElement("tr");
            //boucle pour l'en-tete
            for (var j = 0; j < head.length; j++) {
                var th = document.createElement("th");
                th.innerHTML = head[j];
                trh.appendChild(th);
            }
            thead.appendChild(trh);
            table.appendChild(thead);
            var total = 0;
            //boucle pour charger le body
            for (var k = 0; k < this.alldata[i].data.length; k++) {
                var trb = document.createElement("tr");
                //boucle pour une ligne du body
                for (var l = 0; l < head.length; l++) {
                    if (head[l] === "PT") {
                        total += parseInt(this.alldata[i].data[k].PT);
                    }
                    var td = document.createElement("td");
                    td.innerHTML = this.alldata[i].data[k][head[l]];
                    trb.appendChild(td);
                }
                tbody.appendChild(trb);
            }
            var trf = document.createElement("tr");
            var thf = document.createElement("td");
            thf.innerHTML = "Total";

            thf.setAttribute("colspan", head.length - 1);

            var tht = document.createElement("td");

            tht.innerHTML = total + " FCFA";
            trf.appendChild(thf);
            trf.appendChild(tht);
            tfoot.appendChild(trf);

            table.appendChild(tbody);
            table.appendChild(tfoot);

            $(".all-table").append(table);

        }

        $(".all-table").slideDown("'slow'");
        setTimeout(function () {

            //$("#lalanda").addClass(""); 
            $(".rapport").css({"margin-bottom": "70px"});

            $(".rapport").find("caption").css({"border": " 5px black", fontSize: 20});
            $(".rapport").find("tbody").find("tr:odd").css({backgroundColor: "#CCCCCC"});
            $(".rapport").find("tfoot").find("tr").css({backgroundColor: "#e5bc64", "font-weight": "bold", fontSize: 12});
            $(".rapport").find("thead").find("tr").css({backgroundColor: "#4A8BC2", fontSize: 12}).animate({"fontWeight": '30px'}, 1234);
        }, 20);
    }


    var title = " de la Region ";
    var title_header;
    var categorie;
    var periode;
    var societe;
    var testImageDataUrl;
    var societeDataUrl;
    setTimeout(function () {
        $.ajax({
            url: "impression",
            data: {action: "bordereauP"},
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                societe = data;
                testImageDataUrl = 'data:image/png;base64,' + data[0];
                societeDataUrl = data[1];
                console.log(data);
            }
        });
    }, 10);
    var testImageDataUrl;
    function printPdf(data) {
        var docDefinition;
        if (this.alldata.length !== 0) {


            docDefinition = {
                info: {
                    title: 'GIC DOCUMENT',
                    author: 'john doe',
                    subject: 'subject of document',
                    keywords: 'keywords for document'
                },
                pageMargins: [20, 30, 20, 30],
                pageSize: 'A4',
                footer: function (currentPage, pageCount) {
                    var tfoo = {
                        columns: [
                            {text: "" + getDate(), alignment: 'left', style: 'footerleft'},
                            {text: "GIC ", alignment: 'center', style: 'footercenter'},
                            {text: currentPage.toString() + ' / ' + pageCount, alignment: 'right', style: 'footer'}


                        ]};
                    return tfoo;
                },
                content: getContent(data),
                styles: {
                    img: {
                        margin: [0, -20, 0, -9]
                    },
                    header: {
                        fontSize: 9,
                        bold: true,
                        margin: [170, 40, 0, -9]
                    },
                    subheader: {
                        fontSize: 12,
                        bold: true,
                        margin: [440, -60, 0, 0]
                    },
                    title: {
                        fontSize: 12,
                        bold: true,
                        margin: [40, 20, 0, 5]
                    },
                    subtitle: {
                        fontSize: 10,
                        bold: false,
                        margin: [140, 10, 0, 5]
                    },
                    tableExample: {
                        margin: [0, 5, 0, 15]
                    },
                    tableHeader: {
                        bold: true,
                        fontSize: 12


                    },
                    tableFooter: {
                        bold: true,
                        fontSize: 12,
                        margin: [0, 7, 0, 7]


                    },
                    tableContent: {
                        bold: false,
                        fontSize: 9


                    },
                    footer: {
                        bold: true,
                        fontSize: 8,
                        margin: [0, 0, 20, 0]


                    },
                    footerleft: {
                        bold: true,
                        fontSize: 8,
                        margin: [20, 0, 0, 0]


                    },
                    footercenter: {
                        bold: true,
                        fontSize: 8,
                        margin: [0, 0, 0, 0]



                    }
                },
                defaultStyle: {
                    // alignment: 'justify'
                }
            };
        } else {
            docDefinition = {
                info: {
                    title: 'GIC DOCUMENT',
                    author: 'john doe',
                    subject: 'subject of document',
                    keywords: 'keywords for document'
                },
                pageMargins: [20, 30, 20, 30],
                pageSize: 'A4',
                footer: function (currentPage, pageCount) {
                    var tfoo = {
                        columns: [
                            {text: "" + getDate(), alignment: 'left', style: 'footerleft'},
                            {text: "GIC ", alignment: 'center', style: 'footercenter'},
                            {text: currentPage.toString() + ' / ' + pageCount, alignment: 'right', style: 'footer'}


                        ]};
                    return tfoo;
                },
                watermark: {text: "aucune consommation", color: "blue", opacity: 0.3, bold: true},
                content: getContent(data),
                styles: {
                    img: {
                        margin: [0, -20, 0, -9]
                    },
                    header: {
                        fontSize: 9,
                        bold: true,
                        margin: [170, 40, 0, -9]
                    },
                    subheader: {
                        fontSize: 12,
                        bold: true,
                        margin: [440, -60, 0, 0]
                    },
                    title: {
                        fontSize: 12,
                        bold: true,
                        margin: [40, 20, 0, 5]
                    },
                    subtitle: {
                        fontSize: 10,
                        bold: false,
                        margin: [140, 10, 0, 5]
                    },
                    tableExample: {
                        margin: [0, 5, 0, 15]
                    },
                    tableHeader: {
                        bold: true,
                        fontSize: 12


                    },
                    tableFooter: {
                        bold: true,
                        fontSize: 12,
                        margin: [0, 7, 0, 7]


                    },
                    tableContent: {
                        bold: false,
                        fontSize: 9


                    },
                    footer: {
                        bold: true,
                        fontSize: 8,
                        margin: [0, 0, 20, 0]


                    },
                    footerleft: {
                        bold: true,
                        fontSize: 8,
                        margin: [20, 0, 0, 0]


                    },
                    footercenter: {
                        bold: true,
                        fontSize: 8,
                        margin: [0, 0, 0, 0]



                    }
                },
                defaultStyle: {
                    // alignment: 'justify'
                }
            };
        }
        pdfMake.createPdf(docDefinition).open();
    }
    $(".print-pdf").hide();
    $(".print-pdf").click(function () {

        printPdf(this.alldata);
    });
    var select = $(".all-date");
    var now = new Date();
//obtenir automatiquement les dates a partir de 2017
    for (var i = 2017; i <= now.getFullYear(); i++) {
        var option;
        if (i === now.getFullYear()) {
            option = '<option selected>' + i + '</option>';
        } else {

            option = '<option >' + i + '</option>';
        }
        select.append(option);
    }

        $('.cat_select').multiselect({
            enableFiltering: true,
            maxHeight: 200,
            buttonClass: 'btn-primary btn',
            buttonWidth: '230px',
            //dropUp: true,
            //selectAllText: true,
            includeSelectAllOption: true,
            enableCaseInsensitiveFiltering: true,
            filterPlaceholder: 'Recherhe',
            buttonText: function (options, select) {
                if (options.length === 0) {
                    return 'Aucune  Selection ...';
                }  else {
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
    function getMulti(serveur, param, name) {
    $.post(
            serveur,
            param,
            function (data) {

                $(".all_select").html("");
               
               $(".ff").html("");
                var k;
               var select=document.createElement("select");
               select.setAttribute("class","input-lg site_service all_select");
               select.setAttribute("name",name);
                for (k = 0; k < data.length; k++) {
                    var option=document.createElement("option");
                    option.setAttribute("value",data[k][0]);
                    
                    option.innerHTML=data[k][1];
                    select.appendChild(option);
                }
                $(".ff").html(select);
               
                $(".all_select").attr('multiple', "multiple");
                //  $(".example").multiselect();
                //  $(".example").multiselect('rebuild');
                $('.all_select').multiselect({
                    enableFiltering: true,
                    maxHeight: 200,
                    buttonClass: 'btn-primary btn',
                    buttonWidth: '230px',
                    //selectAllText: true,
                    includeSelectAllOption: true,
                    enableCaseInsensitiveFiltering: true,
                    filterPlaceholder: 'Recherhe',
                    // selectAllValue: 'multiselect-all',
                    // selectedClass: null,
                    // dropRight: true,
                    //dropUp: true,
                    buttonText: function (options, select) {
                        if (options.length === 0) {
                            return 'Aucune  Selection ...';
                        }  else {
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

            },
            "json"
            );
}
//obtenir tous les sites de la region
    $(".region_service").click(function (e) {
        e.preventDefault();
        $(".personnels").html("");
        $(".service").html("");
        var region = $(this).val();
        var param = {action: "getSites", vue: "vue", region: region};
        if($(this).hasClass("for_end")){
            getMulti("admin", param, "site");
        }else{
         
            getMany("admin", param, ".site_service");
        }
        
    });
    
    $(".region_magp").click(function (e) {
        e.preventDefault();
        var region = $(this).val();
        var param = {action: "getRole", vue: "vue", idAffectation: 3, region: region};
        if($(this).hasClass("for_end")){
            getMulti("admin", param, "site");
        }else{
         
            getMany("admin", param, ".magasinP");
        }
        
    });
    $(".region_mags").click(function (e) {
        e.preventDefault();
        var region = $(this).val();
        var param = {action: "getRole", vue: "vue", idAffectation: 2, region: region};
        getMany("admin", param, ".magasinS");
    });
    $(".site_service").click(function (e) {

        e.preventDefault();
        alert("LALANDA");
        $(".personnels").html("");
        var site = $(this).val();
        var param = {action: "getServiceBySite", vue: "vue", site: site};
        getMany("admin", param, ".service");
    });
    $(".service").click(function (e) {

        e.preventDefault();
        var service = $(this).val();
        var param = {action: "getPersonnelsByService", vue: "vue", service: service};
        getMany("admin", param, ".personnels");
    });
    //pour obtenir les Directions
    $(".region").click(function (e) {

        e.preventDefault();
        var region = $(this).val();
        var param = {action: "getDirectionByRegion", vue: "vue", region: region};
        getMulti("admin", param, "direction");
    });
    var datable;
    var cat;
    $(".li-table").click(function (e) {
        e.preventDefault();
        title = "";
        if ($(this).children("a").html() === "Region") {
            title = " de la Region ";
        } else if ($(this).children("a").html() === "Site") {
            title = " du Site ";
        } else if ($(this).children("a").html() === "Service") {
            title = " du Service ";
        } else if ($(this).children("a").html() === "Direction") {
            title = " de la Direction ";
        } else if ($(this).children("a").html() === "Personnel") {
            title = " du Personnel ";
        } else if ($(this).children("a").html() === "Magasin Principal") {
            title = " du Magasin Principal ";
        } else if ($(this).children("a").html() === "Magasin Secondaire") {
            title = " du Magasin Secondaire ";
        } else {
            title = " de la Region ";
        }
        $(".print-pdf").hide();
        cat = $(this).children("a").text();
        $(".table-rapport").hide();
        $(".stat-perso").html("");
        if ($(".table-rapport").hasClass("perso")) {
            $(".table-rapport tfoot").html("");
            //datable.DataTable();
            $(".table-rapport").removeCLass("perso");
        }
    });
    function parseDate(date) {
        var dat = new Array();
        dat = date.split("-");
        return " du " + dat[0] + " au " + dat[1];
    }

    function savedata(data) {
        this.alldata = data;
         drawTable();
        //alert(alldata[0].data);
    }
    $(".search-catego").click(function (e) {
        e.preventDefault();
        $(".table-rapport").removeClass("hidden");
        if ($(".table-rapport").hasClass("persos")) {
            $(".table-rapport").removeCLass("persos");
        }



        var form = $(this).parents("form:first");
        this.action = form.attr("action");
        //alert($(this).parent("form").find(".reservation").val());
        var date = form.find(".reservation").val();
        periode = form.find(".reservation").val();
        var select = form.find("select:last").find("option:selected").text();
        //title += select;

        categorie = form.find("[name=categorie]").find("option:selected").text();
        if (form.hasClass("non-region")) {

            form.attr("action", form.attr("action") + "&id_magasin=" + form.attr("id_magasin"));
            title = " du Magasin " + $("#nom_magasin").html();
        }

        if (form.hasClass("non-regionp")) {

            form.attr("action", form.attr("action") + "&id_magasinP=" + form.attr("id_magasinP"));
            title = " du Magasin " + $("#nom_magasin").html();
        }
        title_header = title + select;
        var title_end = " " + form.find("select:last").find("option:selected").text();
        var vide = false;
        $.each(form.find("input"), function () {
            if ($(this).val() === "") {
                $(this).css({
                    borderColor: 'red',
                    color: 'red'
                });
                vide = true;
            } else {
                $(this).css({
                    borderColor: '',
                    color: ''
                });
            }
        });
        if (!vide) {
            if (form.find("[name=choix]:checked").val() === "tous") {

                form.attr("action", $(this).attr("name"));
                $.ajax({
                    url: form.attr("action"),
                    data: form.serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (data) {
                        //alert(data);

                        console.log(data);
                        savedata(data);
                    }

                });
                form.attr("action", this.action);
                $(".print-pdf").slideDown(1233);
            } else {

                form.submit();
            }
        }
    });
    function addperso() {
        $("#stat-perso").html("");
        $.ajax({
            url: "tableau_de_Bord_Budgetaire",
            data: {action: "allStatUtilisateur", region: $("#region_service").val()},
            type: 'POST',
            dataType: 'json',
            success: function (data) {

                for (var i = 0; i < data.length; i++)

                {
                    var tr = document.createElement("tr");
                    var nom = document.createElement("td");
                    nom.innerHTML = data[i][0];
                    tr.appendChild(nom);
                    for (var j = 2; j < data[i].length; j++)
                    {
                        for (var k = 0; k < data[i][j].length; k++)
                        {
                            var td = document.createElement("td");
                            td.innerHTML = "<a class='detail-perso'  nom='" + data[i][0] + "' href='tableau_de_Bord_Budgetaire?action=DetailleStatUtilisateur&vue=statistiques&nomutilisateur=" + data[i][1] + "&categorie=" + data[i][j][k][0] + "'>" + data[i][j][k][1] + "</a> ";
                            tr.appendChild(td);
                        }



                    }

                    //var tdb = document.createElement("td");
                    //tdb.innerHTML = "<a class='btn btn-primary delete'  href='SettingPersonnal?action=DelettePersonnel&vue=perso&personnel=" + data[i][1] + "&categorie=" + data[i][0] + "'  ><span class='icon'> <i class='icon-eye-open'></i> </span></a> ";
                    //tr.appendChild(tdb);

                    $("#stat-perso").append(tr);
                }
                impression();
            }
        });
    }
    addperso();
    function impression(date, tfoot, title_end) {
        var region;
        var titre;
        if ($(".notification").attr("region") === "") {
            region = " ";
        } else {

            region = " region " + $(".notification").attr("region");
        }

        if ($(".perso").attr("titre") === undefined) {
            titre = " ";
        } else {

            titre = $(".perso").attr("titre") + $(".li-cat.active").find("a").text() + date;
        }
        var titreT = $(".page-title").text();
        var header = '<div class="header_top" style="border:2px solid black;padding:5px;margin-bottom:5px">' +
                '<span style="text-align:left">' +
                '<img src="data:image/png;base64,' + societe[0] + '" style=" left:0;padding-right:10px;height:70px" />' +
                '</span>' +
                '<div class="region pull-right" style="margin-right:5px;margin-top:10px;"><i>' + region + '</i></div>' +
                '<h3 class="header_title" style="text-align:center;padding-right:10px;font-size:0.7em"> ' + societe[1] + '</h3>' +
                '</div><br>' +
                '<h3 style="text-align:center;padding-right:10px;font-size:0.7em"> ' + titre + title_end + '</h3>';
        '</div>';
        setTimeout(function () {



            datable = $('.perso').DataTable({
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
                                        image: 'data:image/png;base64,' + societe[0] + ' style=" left:0;padding-right:10px;height:20px"'
                                    });
                                    doc.content.splice(0, 0, {
                                        margin: [40, 0, 0, 0],
                                        alignment: 'center',
                                        text: "" + societe[1]

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
                        titleAttr: 'Imprimertout',
                        message: '',
                        autoPrint: true,
                        title: function () {
                            return " ";
                        },
                        //footer: true,
                        customize: function (win) {
                            $(win.document.body).addClass('display').css('font-size', '1.2em').prepend(header);
                            $(win.document.body).find('h3').css('text-align', 'center');
                            $(win.document.body).find('tbody').css({'font-size': '0.7em', "text-align": "center", "padding-top": "0px"});
                            $(win.document.body).find('tbody tr td,tr th').css({'font-size': '0.7em', "padding-top": "0px", "padding-bottom": "0px"});
                            $(win.document.body).find('thead').css({'font-size': '0.8em', "text-align": "center"});
                            $(win.document.body).find('div.region').css('text-align', 'right');
                            $(win.document.body).addClass('display').css('font-size', '1.2em').append(tfoot);
                            $(win.document.body).find('h3').css('text-align', 'center');
                            $(win.document.body).find('.header_top').css('height', '70px');
                            $(win.document.body).find('.header_title').css('margin-top', '-30px');
                            $(win.document.body).find('tbody').css({'font-size': '0.7em', "text-align": "center", "padding-top": "0px"});
                            $(win.document.body).find('tbody tr td,tr th').css({'font-size': '0.7em', "padding-top": "0px", "padding-bottom": "0px"});
                            $(win.document.body).find('thead').css({'font-size': '0.8em', "text-align": "center"});
                            $(win.document.body).find('div.region').css('text-align', 'right');
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
        }, 1);
    }






});