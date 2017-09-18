<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Load javascripts at bottom, this will reduce page load time -->

<script src="admin/js/jquery-1.8.3.min.js"></script>
<script src="admin/assets/fullcalendar/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="admin/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="admin/assets/jquery-slimscroll/jquery-ui-1.9.2.custom.min.js"></script>
<script src="admin/assets/bootstrap/js/bootstrap.min.js"></script>

<script src="admin/assets/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="admin/js/jquery.blockui.js"></script>

<!-- ie8 fixes -->
<!--[if lt IE 9]>
<script src="admin/js/excanvas.js"></script>
<script src="admin/js/respond.js"></script>
<![endif]-->
<script type="text/javascript" src="admin/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="admin/assets/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript" src="admin/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="admin/assets/data-tables/DT_bootstrap.js"></script>
<script src="js/myjs/controllers/notifications.js" type="text/javascript"></script>
<script src="js/js/lumino.glyphs.js" type="text/javascript"></script>
<script src="js/js/chart.min.js" type="text/javascript"></script>
<script src="js/js/easypiechart-data.js" type="text/javascript"></script>
<script src="js/js/easypiechart.js" type="text/javascript"></script>
<script src="admin/js/external-dragging-calendar.js" type="text/javascript"></script>  
<script src="assets/glDatePicker-2.0/glDatePicker.js" type="text/javascript"></script>

<script src="js/myjs/controllers/notifications.js" type="text/javascript"></script>

 <script src="js/myjs/controllers/simple_print.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        $('.date').glDatePicker();
        var mois = ["Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"];
        var getSpecialDay = function () {
            var response = new Array();

            $.post(
                    "agenda",
                    {action: "get_special_event", idPersonnel: sessionStorage.getItem("iduser")},
                    function (data) {
                        console.log(data);

                        if (data.length === 0) {

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
        getSpecialDay();
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
        }





        $('.carousel').carousel();

    });

</script>

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
<c:if test="${state == 'OK'}">
    <script>
        $(document).ready(function () {
            function viewMP(data) {
                var lineChartData = {
                    labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
                    datasets: [
                        {
                            fillColor: "rgba(220,220,220,0.5)",
                            strokeColor: "rgba(220,220,220,1)",
                            pointColor: "rgba(220,220,220,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(220,220,220,1)",
                            data: [data[0][0], data[0][1], data[0][2], data[0][3], data[0][4], data[0][5], data[0][6], data[0][7], data[0][8], data[0][9], data[0][10], data[0][11]]
                        },
                        {
                            fillColor: "rgba(151,187,205,0.5)",
                            strokeColor: "rgba(151,187,205,1)",
                            pointColor: "rgba(151,187,205,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(48, 164, 255, 1)",
                            data: [data[1][0], data[1][1], data[1][2], data[1][3], data[1][4], data[1][5], data[1][6], data[1][7], data[1][8], data[1][9], data[1][10], data[1][11]]
                        }
                    ]

                };
                var chart1 = document.getElementById("lineMP").getContext("2d");
                var myLine = new Chart(chart1).Line(lineChartData, {
                    responsive: true
                });

                var barChartData = {
                    labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
                    datasets: [
                        {
                            fillColor: "rgba(220,220,220,0.5)",
                            strokeColor: "rgba(220,220,220,1)",
                            highlightFill: "rgba(220,220,220,0.75)",
                            highlightStroke: "rgba(220,220,220,1)",
                            data: [data[2][0], data[2][1], data[2][2], data[2][3], data[2][4], data[2][5], data[2][6], data[2][7], data[2][8], data[2][9], data[2][10], data[2][11]]
                        },
                        {
                            fillColor: "rgba(151,187,205,0.5)",
                            strokeColor: "rgba(151,187,205,1)",
                            highlightFill: "rgba(48, 164, 255, 0.75)",
                            highlightStroke: "rgba(48, 164, 255, 1)",
                            data: [data[3][0], data[3][1], data[3][2], data[3][3], data[3][4], data[3][5], data[3][6], data[3][7], data[3][8], data[3][9], data[3][10], data[3][11]]
                        }
                    ]

                };
                var chart2 = document.getElementById("barMP").getContext("2d");
                var barchar = new Chart(chart2).Bar(barChartData, {
                    responsive: true
                });


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
        function view(data) {
            var lineChartData = {
                labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
                datasets: [
                    {
                        fillColor: "rgba(220,220,220,0.5)",
                        strokeColor: "rgba(220,220,220,1)",
                        pointColor: "rgba(220,220,220,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(220,220,220,1)",
                        data: [data[0][0], data[0][1], data[0][2], data[0][3], data[0][4], data[0][5], data[0][6], data[0][7], data[0][8], data[0][9], data[0][10], data[0][11]]
                    },
                    {
                        fillColor: "rgba(151,187,205,0.5)",
                        strokeColor: "rgba(151,187,205,1)",
                        pointColor: "rgba(151,187,205,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(48, 164, 255, 1)",
                        data: [data[1][0], data[1][1], data[1][2], data[1][3], data[1][4], data[1][5], data[1][6], data[1][7], data[1][8], data[1][9], data[1][10], data[1][11]]
                    }
                ]

            };
            var chart1 = document.getElementById("line").getContext("2d");
            var myLine = new Chart(chart1).Line(lineChartData, {
                responsive: true
            });
            var barChartData = {
                labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
                datasets: [
                    {
                        fillColor: "rgba(220,220,220,0.5)",
                        strokeColor: "rgba(220,220,220,1)",
                        highlightFill: "rgba(220,220,220,0.75)",
                        highlightStroke: "rgba(220,220,220,1)",
                        data: [data[2][0], data[2][1], data[2][2], data[2][3], data[2][4], data[2][5], data[2][6], data[2][7], data[2][8], data[2][9], data[2][10], data[2][11]]
                    },
                    {
                        fillColor: "rgba(151,187,205,0.5)",
                        strokeColor: "rgba(151,187,205,1)",
                        highlightFill: "rgba(48, 164, 255, 0.75)",
                        highlightStroke: "rgba(48, 164, 255, 1)",
                        data: [data[3][0], data[3][1], data[3][2], data[3][3], data[3][4], data[3][5], data[3][6], data[3][7], data[3][8], data[3][9], data[3][10], data[3][11]]
                    }
                ]

            };
            var chart2 = document.getElementById("bar").getContext("2d");
            var barchar = new Chart(chart2).Bar(barChartData, {
                responsive: true
            });
        }
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
        };
    </script> 
</c:if>

<script src="admin/js/jquery.scrollTo.min.js"></script>

<script src="js/dist/js/bootstrap-multiselect.js" type="text/javascript"></script>

<script src="admin/assets/fancybox/source/jquery.fancybox.pack.js"></script>

<!-- MEGAFOLIO PRO GALLERY JS FILES  -->
<script type="text/javascript" src="admin/assets/metr-folio/js/jquery.metro-gal.plugins.min.js"></script>
<script type="text/javascript" src="admin/assets/metr-folio/js/jquery.metro-gal.megafoliopro.js"></script>

<!--common script for all pages-->
<script src="admin/js/common-scripts.js"></script>
<!--script for this page-->

<script src="admin/js/form-wizard.js"></script>

<script src="admin/js/dynamic-table.js"></script>

<script src="js/jquery.dataTables.js" type="text/javascript"></script>  
<script src="js/dataTables.responsive.js" type="text/javascript"></script>
<script src="js/dataTables.buttons.js" type="text/javascript"></script>          
<script src="js/dataTables.select.js" type="text/javascript"></script>   
<script src="js/buttons.colVis.js" type="text/javascript"></script>
<script src="js/jszip.js" type="text/javascript"></script>
<script src="js/pdfmake.js" type="text/javascript"></script>
<script src="js/vfs_fonts.js" type="text/javascript"></script>
<script src="js/buttons.jqueryui.js" type="text/javascript"></script>
<script src="js/buttons.html5.js" type="text/javascript"></script>
<script src="js/buttons.print.js" type="text/javascript"></script>
<script src="js/dataTables.autoFill.js" type="text/javascript"></script>
<script src="js/buttons.flash.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/print.css" type="text/css" media="print" />

<script src="js/produit.js" type="text/javascript"></script>

<script src="admin/js/editable-table.js"></script>
<!-- END JAVASCRIPTS -->  

<script src="js/myjs/controllers/ContollerPersonnels.js" type="text/javascript"></script>
<script src="js/ajaxrequette.js" type="text/javascript"></script>
<script src="js/effets.js" type="text/javascript"></script>
<script>
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
        jQuery(document).ready(function () {
            EditableTable.init();
        });</script>


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


<!--script for this page-->
<script src="admin/js/form-component.js"></script>
<!-- END JAVASCRIPTS -->

<script language="javascript" type="text/javascript">

    $(function () {

        $.configureBoxes();
    });
</script>
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