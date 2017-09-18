
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8" />
        <title>Écran verrouillé</title>
        <META http-equiv="refresh" content="<%= session.getMaxInactiveInterval()%>; url=/GCI_APPLICATION/login.jsp"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <link href="admin/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link href="admin/assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
        <link href="admin/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="admin/css/style.css" rel="stylesheet" />
        <link href="admin/css/style-responsive.css" rel="stylesheet" />
        <link href="admin/css/style-${sessionScope.couleur}.css" rel="stylesheet" id="style_color" />
    </head>
    <!-- END HEAD -->
    <!-- BEGIN BODY -->
    <body class="lock">
        <div class="lock-header">
            <!-- BEGIN LOGO -->
            <a class="center" id="logo" href="#">
                <img class="center" alt="logo" src="images/gci.png" style="height: 120px; width: 350px;border-radius: 25px;margin-bottom: 0px"><br>
                <br><img class="center" alt="GIC" src="images/Compufirst Logo.png" style="height: 50px; width: 350px;border-radius: 25px;margin-bottom: 0px">
            </a>
            <!-- END LOGO -->
        </div>
        <div class="lock-wrap">
            <div class="metro single-size gray">
                <img src="photo/logo.png" alt="" style="height: 165px" >
            </div>
            <div class="metro double-size blue" >
                <h1><c:out value="${sessionScope.personnel.getNomPrenom()}"/></h1>
                <p><c:out value="${sessionScope.personnel.getEmail()}"/></p>
            </div>
            <div class="metro double-size green">
                <form  method="POST" autocomplete="off" action="authentification">
                    <div class="input-append lock-input">
                        <input type="password" class="" placeholder="Password" name="password" value="${sessionScope.personnel.getLoginList().getPassword()}">
                        <button type="submit"name="user" value="${sessionScope.personnel.getLoginList().getUsername()}" class="btn tarquoise"><i class=" icon-arrow-right"></i></button>
                    </div>
                </form>

            </div>
            <div class="metro single-size red">
                <div class="locked">
                    <i class="icon-lock"></i>
                    <span>Fermer</span>
                </div>
            </div>
            <div class="metro double-size gray ">
                <a href="<c:url value="authentification?action=faut"/>" class="user-position">
                    <i class="icon-user"></i>
                    <span>Nouvelle Session</span>
                </a>
            </div>
            <div class="metro double-size orange">
                <a href="javascript:;" class="user-position" data-target="#pwdModal" data-backdrop="false" data-toggle="modal">
                    <i class="icon-key"></i>
                    <span>Password Oublier ?</span>
                </a>
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
                                    <form  method="POST" autocomplete="off" action="Json">
                                        <div class="text-center">

                                            <p>Si vous avez oublié votre mot de passe, vous pouvez le réinitialiser ici.</p>
                                            <div class="panel-body">
                                                <fieldset>
                                                    <div class="form-group">
                                                        <input class="form-control input-lg input-large focused span12"required placeholder="Registration number" name="matricule" type="text">
                                                        <input class="form-control input-lg input-large focused span12"required placeholder="E-mail Address" name="email" type="email">
                                                    </div>
                                                    <input class="btn btn-lg btn-primary btn-block span12" value="Envoyer mon mot de passe" type="submit">
                                                </fieldset>
                                            </div>
                                        </div>
                                    </form>
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
        <script src="admin/js/jquery-1.8.3.min.js"></script>
        <script src="admin/assets/bootstrap/js/bootstrap.min.js"></script>

    </body>
</html>
