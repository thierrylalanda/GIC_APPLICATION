
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8" />
        <title>Login</title>
        <link rel="icon" href="images/gci.png" >
        <META http-equiv="refresh" content="<%= session.getMaxInactiveInterval()%>; url=/GCI_APPLICATION/login.jsp"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <link href="admin/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link href="admin/assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
        <link href="admin/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="admin/css/style.css" rel="stylesheet" />
        <link href="admin/css/style-responsive.css" rel="stylesheet" />
        <link href="admin/css/style-default.css" rel="stylesheet" id="style_color" />
        <script src="admin/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    </head>
    <!-- END HEAD -->
    <!-- BEGIN BODY -->
    <body class="lock" >

        <div class="lock-header">

            <!-- BEGIN LOGO -->
            <a class="center" id="logo">
                <img class="center" alt="GIC" src="images/gci.png" style="height: 120px; width: 350px;border-radius: 25px;margin-bottom: -50px;margin-right: 0px">

            </a>
            <!-- END LOGO -->

        </div>

        <div class="login-wrap">

            <div class="metro single-size red" >

                <img class="center" style="height: 150px; width: 150px; margin-bottom: -5px;margin-top: 0px" alt="logo de L'entreprise lageur:105 hauteur:109" src="photo/${sessionScope.societe.getLogo()}">

            </div>

            <form id="loginForm" method="POST" action="authentification" autocomplete="off" novalidate="novalidate">
                <div class="metro double-size green">

                    <div class="input-append lock-input">

                        <input type="text" class="" placeholder="nom utilisateur"  value="" name="user" required>
                    </div>

                </div>
                <div class="metro double-size red">

                    <div class="input-append lock-input">

                        <input type="password" class="" placeholder="mot de passe" value="" name="password" required>
                    </div>

                </div>
                <div class="metro single-size yellow login" >

                    <button type="submit" class="btn login-btn">
                        Login
                        <i class=" icon-long-arrow-right"></i>
                    </button>

                </div>
            </form>

            <div class="metro double-size ">
                <img src="image/img3.jpg" style="height: 150px;width: 100%" class="img-responsive img-rounded" />
            </div>
            <div class="metro single-size deep-red">
                <img src="image/img4.jpg" style="height: 150px;width: 100%" class="img-responsive img-rounded" />
            </div>
            <c:if test="${empty message and empty show}">
                <div class="metro double-size blue">
                    <img src="image/img5.jpg" style="height: 150px;width: 100%" class="img-responsive img-rounded" />
                </div>
            </c:if>
            <c:if test="${not empty message}">
                <div class="metro double-size red">
                    <a href="#" class="social-link">
                        <i class="icon-eye-open"></i>
                        <span>${message.getMessage()}</span>
                    </a>
                </div>
            </c:if>
            <c:if test="${not empty show}">
                <div class="metro double-size green">
                    <a href="#" class="social-link">
                        <i class="icon-info-sign"style="margin-top: -20px;"></i>
                        <span>Voici Vos Identifiants</span>
                        <p class="text-danger" style="color: red">User Name: ${personnel.getLoginList().getUsername()}</p>
                        <p class="text-capitalize text-danger"style="color: red;">PassWord: ${personnel.getLoginList().getPassword()}</p>
                    </a>
                </div>
            </c:if>

            <div class="metro single-size purple">
                <img src="image/img2.jpg" style="height: 150px;width: 100%" class="img-responsive img-rounded" />
            </div>
            <div class="login-footer">
                <div class="remember-hint pull-left">
                    <input type="checkbox" id=""> Souviens-toi de moi
                </div>
                <div class="forgot-hint pull-right">
                    <a id="forget-password" class="" href=""data-target="#pwdModal" data-toggle="modal"> Password Oublier ?</a>
                </div>
            </div>
        </div>

        <div id="pwdModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h1 class="text-center">Quel est mon mot de passe?</h1>
                    </div>
                    <div class="modal-body">
                        <div class="row-fluid">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="text-center">

                                        <p>Si vous avez oublié votre mot de passe, vous pouvez le réinitialiser ici.</p>
                                        <div class="panel-body">
                                            <form  method="POST" autocomplete="off" action="Json">
                                                <fieldset>
                                                    <div class="form-group">
                                                        <input class="form-control input-lg input-large focused span12"required placeholder="Registration number" name="matricule" type="text">
                                                        <input class="form-control input-lg input-large focused span12"required placeholder="E-mail Address" name="email" type="email">
                                                    </div>
                                                    <input class="btn btn-lg btn-primary btn-block span12" value="Envoyer mon mot de passe" type="submit">
                                                </fieldset>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="row-fluid">
                            <button class="btn " data-dismiss="modal" aria-hidden="true">Fermer</button>
                        </div>	
                    </div>
                </div>
            </div>
        </div>
        <div id="footer" style="background-color: #000000">
            <img class="pull-left" alt="GIC" src="images/Compufirst Logo.png" style="height: 30px; width: 160px;margin-top :0px !important;margin-left: 2px">
            <div class="" style="margin-right: 80px">MCD GLOBAL SERVICE&nbsp;&nbsp;&nbsp;&nbsp; <a href="#"> Licenses  </a> <a class="pull-right text-success" href="#"> &copy; 2018, made with <i class="icon-heart "></i> by MCD GLOBAL SERVICE  </a></div>
        </div>
        <script src="admin/js/jquery-1.8.3.min.js"></script>
        <script src="admin/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="js/myjs/controllers/accueil.js" type="text/javascript"></script>
        <script src="js/myjs/lib/jaaulde-cookies.js" type="text/javascript"></script>
        <script src="js/myjs/controllers/function.js" type="text/javascript"></script>


    </body>
    <!-- END BODY -->
</html>
