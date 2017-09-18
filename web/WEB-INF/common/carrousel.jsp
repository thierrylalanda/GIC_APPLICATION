
<!-- #region Jssor Slider Begin -->
<!-- Generator: Jssor Slider Maker -->
<!-- Source: https://www.jssor.com -->
<script src="js/myjs/controllers/jssor.slider-25.2.0.min.js" type="text/javascript"></script>

<script type="text/javascript">
    jssor_1_slider_init = function () {

        var jssor_1_options = {
            $AutoPlay: 1,
            $AutoPlaySteps: 4,
            $SlideDuration: 160,
            $SlideWidth: 200,
            $SlideSpacing: 3,
            $Cols: 5,
            $Align: 390,
            $ArrowNavigatorOptions: {
                $Class: $JssorArrowNavigator$,
                $Steps: 5
            },
            $BulletNavigatorOptions: {
                $Class: $JssorBulletNavigator$
            }
        };

        var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);

        /*#region responsive code begin*/
        /*remove responsive code if you don't want the slider scales while window resizing*/
        function ScaleSlider() {
            var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
            if (refSize) {
                refSize = Math.min(refSize, 980);
                jssor_1_slider.$ScaleWidth(refSize);
            } else {
                window.setTimeout(ScaleSlider, 30);
            }
        }
        ScaleSlider();
        $Jssor$.$AddEvent(window, "load", ScaleSlider);
        $Jssor$.$AddEvent(window, "resize", ScaleSlider);
        $Jssor$.$AddEvent(window, "orientationchange", ScaleSlider);
        /*#endregion responsive code end*/
    };
</script>
<style>
    .jssorb057 .i {position:absolute;cursor:pointer;}
    .jssorb057 .i .b {fill:none;stroke:#fff;stroke-width:2000;stroke-miterlimit:10;stroke-opacity:0.4;}
    .jssorb057 .i:hover .b {stroke-opacity:.7;}
    .jssorb057 .iav .b {stroke-opacity: 1;}
    .jssorb057 .i.idn {opacity:.3;}

    .jssora073 {display:block;position:absolute;cursor:pointer;}
    .jssora073 .a {fill:#ddd;fill-opacity:.7;stroke:#000;stroke-width:160;stroke-miterlimit:10;stroke-opacity:.7;}
    .jssora073:hover {opacity:.8;}
    .jssora073.jssora073dn {opacity:.4;}
    .jssora073.jssora073ds {opacity:.3;pointer-events:none;}
</style>
<div id="jssor_1" style="position:relative;margin:0 auto;top:0px;left:0px;width:980px;height:350px;overflow:hidden;visibility:hidden;">
    <!-- Loading Screen -->
    <div data-u="loading" style="position:absolute;top:0px;left:0px;background:url('image/loading.gif') no-repeat 50% 50%;background-color:rgba(0, 0, 0, 0.7);"></div>
    <div data-u="slides" style="cursor:default;position:relative;top:0px;left:0px;width:980px;height:350px;overflow:hidden;">
        <div>
            <img data-u="image" src="image/BY_001.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/BY_002.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/BY_003.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/SI_010.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/BY_005.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/SI_009.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/BY_001.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/SI_001.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/BY_005.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/SI_005.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/BY_002.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/SI_007.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/BY_006.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/SI_006.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/BY_005.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/SI_004.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/BY_002.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/BY_006.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/SI_011.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/BY_001.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/SI_003.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/BY_005.jpg" />
        </div>
        <div>
            <img data-u="image" src="image/SI_010.jpg" />
        </div>
        <a data-u="any" href="https://www.jssor.com" style="display:none">bootstrap slider</a>
        <ul id="plus" >

            
            <li class='choix-photo'><a href="#" style="background-color:black ; font-size:15px;"  class="text-primary">photo de profil</a></li>
            
            <li class=''><a href="#" style="background-color:black ; font-size:15px;"  class="text-primary">mes infos</a></li>
            <li class=''><a href="#" style="background-color:black ; font-size:15px;"  class="text-primary">mes annonces</a></li>
        </ul> 
    </div>
    <!-- Bullet Navigator -->
    <div data-u="navigator" class="jssorb057" style="position:absolute;bottom:12px;right:12px;" data-autocenter="1" data-scale="0.5" data-scale-bottom="0.75">
        <div data-u="prototype" class="i" style="width:16px;height:16px;">
            <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
            <circle class="b" cx="8000" cy="8000" r="5000"></circle>
            </svg>
        </div>
    </div>
    <!-- Arrow Navigator -->
    <div data-u="arrowleft" class="jssora073" style="width:50px;height:50px;top:0px;left:30px;" data-autocenter="2" data-scale="0.75" data-scale-left="0.75">
        <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
        <path class="a" d="M4037.7,8357.3l5891.8,5891.8c100.6,100.6,219.7,150.9,357.3,150.9s256.7-50.3,357.3-150.9 l1318.1-1318.1c100.6-100.6,150.9-219.7,150.9-357.3c0-137.6-50.3-256.7-150.9-357.3L7745.9,8000l4216.4-4216.4 c100.6-100.6,150.9-219.7,150.9-357.3c0-137.6-50.3-256.7-150.9-357.3l-1318.1-1318.1c-100.6-100.6-219.7-150.9-357.3-150.9 s-256.7,50.3-357.3,150.9L4037.7,7642.7c-100.6,100.6-150.9,219.7-150.9,357.3C3886.8,8137.6,3937.1,8256.7,4037.7,8357.3 L4037.7,8357.3z"></path>
        </svg>
    </div>
    <div data-u="arrowright" class="jssora073" style="width:50px;height:50px;top:0px;right:30px;" data-autocenter="2" data-scale="0.75" data-scale-right="0.75">
        <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
        <path class="a" d="M11962.3,8357.3l-5891.8,5891.8c-100.6,100.6-219.7,150.9-357.3,150.9s-256.7-50.3-357.3-150.9 L4037.7,12931c-100.6-100.6-150.9-219.7-150.9-357.3c0-137.6,50.3-256.7,150.9-357.3L8254.1,8000L4037.7,3783.6 c-100.6-100.6-150.9-219.7-150.9-357.3c0-137.6,50.3-256.7,150.9-357.3l1318.1-1318.1c100.6-100.6,219.7-150.9,357.3-150.9 s256.7,50.3,357.3,150.9l5891.8,5891.8c100.6,100.6,150.9,219.7,150.9,357.3C12113.2,8137.6,12062.9,8256.7,11962.3,8357.3 L11962.3,8357.3z"></path>
        </svg>
    </div>
</div>
<script type="text/javascript">jssor_1_slider_init();</script>
<script src="admin/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $("#plus").hide();
    $("#jssor_1").hover(function(){
        $("#plus").show({"duration":1000,'easy':"slow",'opacity':0.1});
       },function(){

        $("#plus").hide({"duration":1000,'easy':"slow",'opacity':0.1});
       });
    
</script>