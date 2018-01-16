<%@page import="java.util.ArrayList"%>
<%@page pageEncoding="UTF-8"%>

   	<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<!DOCTYPE html>
<html lang="en">
<head>
<title>Ecoleaves</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Growing Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
	SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
<link href="css/style.css" type="text/css" rel="stylesheet" media="all">   
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="all" property="" />
<!-- //Custom Theme files -->
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
<!-- js -->
<script src="js/jquery-2.2.3.min.js"></script>
<!-- //js -->
<!-- smooth scrolling -->
<!-- web-fonts -->

<!-- //web-fonts -->
	<link href="css/events.css" type="text/css" rel="stylesheet" media="all">
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<!-- banner -->
	<div class="banner">  
		<div class="header agileinfo-header"><!-- header -->
			<nav class="navbar navbar-default">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>

					</div> 
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<div class="logo">
							<a href="index2.jsp"><img src="LogoEcoLeaves.png" alt="" style="width: 65%;"/></a>
						</div>
						<ul class="nav navbar-nav navbar-left">
							<li><a href="index2.jsp" class="btn w3ls-hover">Accueil</a></li>
							<li><a href="events_list.jsp" class="btn w3ls-hover">Événement</a></li>
							<li><a href="troc.jsp" class="w3ls-hover active">Échange</a></li>
							<li><a href="#" class="btn w3ls-hover">Discussion</a></li>
							<li><a href="contact.jsp" class="btn w3ls-hover">Contact</a></li>
						</ul>
						<div class="social-icon">
							<a href="http://localhost:8089/FinalProject/" class="social-button facebook" style="color: #91c53c;">
								<%= request.getSession().getAttribute("login") %>
								<i class="fa fa-power-off" aria-hidden="true" title="Déconnexion"></i>
							</a> 
						</div> 
						<div class="clearfix"> </div>
					</div><!-- //navbar-collapse --> 
				</div><!-- //container-fluid -->
			</nav>
		</div><!-- //header -->	
		<!-- banner-text -->

		<!-- //banner-text -->    
	</div>
	<!-- //banner --> 
<!-- welcome -->

	<div class="row" style="
    margin: 0;
">
		<div class="col-md-2">
			<div class="profile-sidebar">

				<!-- SIDEBAR MENU -->
				<div class="profile-usermenu">
					<ul class="nav">
						<li >
							<a href="troc.jsp">
								<i class="glyphicon glyphicon-list-alt"></i>
								Liste des produits </a>
						</li>
						<li class="active">
							<a href="form_add_troc.jsp">
								<i class="	glyphicon glyphicon-plus"></i>
								Ajouter un produit </a>
						</li>

					</ul>
				</div>
				<!-- END MENU -->
			</div>
		</div>
		<div class="col-md-8">
			<!--event-->
			<form action="troc" method="post" class="form-horizontal">
				<fieldset>

					<!-- Form Name -->
					<legend>PRODUCTS</legend>



					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="product_name">Nom du produit</label>
						<div class="col-md-4">
							<input id="product_name" name="nom" placeholder="Nom du produit" class="form-control input-md" required="" type="text">

						</div>
					</div>

					<!-- Select Basic -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="product_categorie">Catégorie</label>
						<div class="col-md-4">
							<select id="country" name="categorie" class="form-control">
								<option value="AUTRE">AUTRE</option>
								<option value="MULTIMEDIA">MULTIMEDIA</option>
								<option value="MATERIEL PROFESSIONNEL">MATERIEL PROFESSIONNEL</option>
								<option value="VEHICULES">VEHICULES</option>
								<option value="VÃªtements & Chaussures">VÉTEMENTS & CHAUSSURES</option>
								<option value="Accessoires">ACCESOIRES</option>
								<option value="LOISIRS">LOISIRS</option>
								
							</select>
						</div>
					</div>


					<!-- Textarea -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="product_description">Description du produit</label>
						<div class="col-md-4">
							<textarea class="form-control" id="product_description" name="description"></textarea>
						</div>
					</div>
					<!-- File Button -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="filebutton">Photo du produit</label>
						<div class="col-md-4">
							<input id="filebutton" name="photo" class="input-file" type="file">
						</div>
					</div>
					<input name="creator" type="hidden" value="<%= request.getSession().getAttribute("login") %>">
					<!-- Button -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="singlebutton"></label>
						<div class="col-md-4">
							<button id="singlebutton" name="submit" class="btn btn-primary">Enregistrer</button>
						</div>
					</div>
					<!-- Text input-->

				</fieldset>
			</form>




		<div class="newsletter">
		<div class="container">
			<div class="w3ls-heading">
				<h3 class="h-two">Newsletter</h3>
				<p class="sub two">Manquez-vous ce qui est important? inscrivez vous dans notre Newsletter</p>
			</div>
			<form action="#" method="post">
				<input type="text" name="email" placeholder="Entez votre Email..." required="">
				<input type="submit" value="Subscribe">
				<div class="clearfix"> </div>
			</form>
		</div>
	</div>
		</div>
	</div>
	<!-- //welcome -->

<!-- //testimonials -->

	<!-- newsletter -->

	<!-- //newsletter -->
	<!-- bootstrap-pop-up -->

<!-- //bootstrap-pop-up -->

	<!-- footer start here --> 
	<div class="footer-agile">
		<div class="container">
			<div class="footer-agileinfo"> 
				<div class="col-md-5 col-sm-5 footer-wthree-grid"> 
					<div class="agileits-w3layouts-tweets">
						<h5><a href="index.html"><i aria-hidden="true"><img style="width: 30%;" src="LogoEcoLeaves.png"></i></a></h5>
						<div class="social-icon footerw3ls">
							<a href="#" class="social-button facebook"><i class="fa fa-facebook"></i></a>
							<a href="#" class="social-button twitter"><i class="fa fa-twitter"></i></a>
							<a href="#" class="social-button google"><i class="fa fa-google-plus"></i></a>
						</div>
					</div>
					<p>Vous pouvez nous trouver aussi sur les les mÃ©dias sociaux</p>
				</div> 
				<div class="col-md-3 col-sm-3 footer-wthree-grid">
					<h3>AccÃ¨s rapide</h3>
					<ul>
						<li><a href="index2.jsp"><span class="glyphicon glyphicon-menu-right"></span> Accueil</a></li>
						<li><a href="events_list.jsp"><span class="glyphicon glyphicon-menu-right"></span> Événement</a></li>
						<li><a href="troc.jsp"><span class="glyphicon glyphicon-menu-right"></span> Échange</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-menu-right"></span> Discussion</a></li>
					</ul>
				</div> 	 
				<div class="col-md-4 col-sm-4 footer-wthree-grid">
					<h3>Ã propos de nous</h3>
					<p>
						Le Lorem Ipsum est simplement du faux texte employÃ© dans la composition et la mise en page avant
						impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les annÃ©es 1500,
					</p>
				</div>
				<div class="clearfix"> </div>		
			</div>
			<div class="copy-right"> 
				<p>© 2017 EcoLeaves . All rights reserved </p>
			</div>
		</div>
	</div> 
	<!-- //footer end here -->  
	<!-- FlexSlider --> 
	<script defer src="js/jquery.flexslider.js"></script>
		<script type="text/javascript">
			$(window).load(function(){
			  $('.flexslider').flexslider({
				animation: "slide",
				start: function(slider){
				  $('body').removeClass('loading');
				}
			  });
			});
		</script>
	<!-- End-slider-script --> 
<!-- Flexslider-js for-testimonials -->
<script type="text/javascript">
							$(window).load(function() {
								$("#flexiselDemo1").flexisel({
									visibleItems:1,
									animationSpeed: 1000,
									autoPlay: true,
									autoPlaySpeed: 3000,    		
									pauseOnHover: true,
									enableResponsiveBreakpoints: true,
									responsiveBreakpoints: { 
										portrait: { 
											changePoint:480,
											visibleItems: 1
										}, 
										landscape: { 
											changePoint:640,
											visibleItems:1
										},
										tablet: { 
											changePoint:768,
											visibleItems: 1
										}
									}
								});
								
							});
					</script>
					<script type="text/javascript" src="js/jquery.flexisel.js"></script>
<!-- //Flexslider-js for-testimonials -->


	<!-- start-smooth-scrolling -->
	<script type="text/javascript" src="js/move-top.js"></script>
	<script type="text/javascript" src="js/easing.js"></script>	
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
			
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
		</script>
	<!-- //end-smooth-scrolling   -->	
	<!-- smooth-scrolling-of-move-up -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
			var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
			};
			*/
			
			$().UItoTop({ easingType: 'easeOutQuart' });
			
		});
	</script>
	<!-- //smooth-scrolling-of-move-up -->   
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap.js"></script>

	<script v-text="text/javascript">
        $('li').click(function(){

            $(this).addClass('active')
                .siblings()
                .removeClass('active');

        });

	</script>
</body>
</html>