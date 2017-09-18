<%-- 
    Document   : message
    Created on : 29 avr. 2017, 20:25:47
    Author     : messi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <title>error</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"> 

    <link href="temp/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="temp/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />

    <link href="temp/css/font-awesome.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">

    <link href="temp/css/style.css" rel="stylesheet" type="text/css" />

</head>

<body>
${sessionScope.lienAccueil}
    <div class="navbar navbar-fixed-top">

        <div class="navbar-inner">

            <div class="container">

                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>

                <a class="brand" href="#">
                   <img src="images/logo.png" alt=""/> 				
                </a>		

                <div class="nav-collapse">
                    <ul class="nav pull-right">

                        <li class="">						
                            <a href="${sessionScope.lienAccueil}" class="">
                                <i class="icon-chevron-left"></i>
                                Back to Home Page 
                            </a>

                        </li>
                    </ul>

                </div><!--/.nav-collapse -->	

            </div> <!-- /container -->

        </div> <!-- /navbar-inner -->

    </div> <!-- /navbar -->



    <div class="container">

        <div class="row">

            <div class="span12">

                <div class="error-container">
                    <h1>404</h1>

                    <h2>Page introuvable ou Déplacer</h2>

                    <div class="error-details">
                        Désolé, une erreur est survenue! Pourquoi ne pas essayer de revenir à la <a href="${sessionScope.lienAccueil}">Page d'accueil</a> Ou contactez votre administrateur

                    </div> <!-- /error-details -->

                    <div class="error-actions">
                        <a href="${sessionScope.lienAccueil}" class="btn btn-large btn-primary">
                            <i class="icon-chevron-left"></i>
                            &nbsp;
                            Retour à la page d'Accueil						
                        </a>



                    </div> <!-- /error-actions -->

                </div> <!-- /error-container -->			

            </div> <!-- /span12 -->

        </div> <!-- /row -->

    </div> <!-- /container -->


    <script src="temp/js/jquery-1.7.2.min.js"></script>
    <script src="temp/js/bootstrap.js"></script>

</body>

</html>