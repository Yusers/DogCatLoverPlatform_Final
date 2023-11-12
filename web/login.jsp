<%-- 
    Document   : Login.jsp
    Created on : Oct 1, 2023, 6:46:00 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng Nhập | Cat Dog Lover Website</title>

        <!-- Google Web Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet"> 
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Favicon -->
        <link href="icon/icons8-pet-lover-96.png" rel="icon">
        <!-- Flaticon Font -->
        <link href="lib/flaticon/font/flaticon.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">

        <style>

            label {
                color: white;
            }

            .login {
                background: url(https://picsum.photos/id/1004/5616/3744) no-repeat   center center #505050;
                box-shadow: 0px 30px 60px -5px #000;
            }

            .active {
                border-bottom: 2px solid #1161ed;
            }

            .nonactive {
                color: rgba(255, 255, 255, 0.2);
            }

            h2:first-child {
                padding-left: 0px;
            }

            .text {
                border: none;
                width: 89%;
                padding: 10px 20px;
                display: block;
                height: 15px;
                border-radius: 20px;
                background: rgba(255, 255, 255, 0.1);
                border: 2px solid rgba(255, 255, 255, 0);
                overflow: hidden;
                margin-top: 15px;
                transition: all 0.5s ease-in-out;
            }

            .text:focus {
                outline: 0;
                border: 2px solid rgba(255, 255, 255, 0.5);
                border-radius: 20px;
                background: rgba(0, 0, 0, 0);
            }

            .text:focus + span {
                opacity: 0.6;
            }

            input[type="text"],
            input[type="password"] {
                font-family: 'Montserrat', sans-serif;
                color: #fff;
                color: #fff;
                width: 100%;
                padding: 10px 20px;
                display: block;
                height: 39px;
                border-radius: 20px;
                margin-top: 30px;
            }



            input {
                display: inline-block;
                padding-top: 20px;
                font-size: 14px;
            }

            .custom-checkbox:checked {
                background-color: rgba(17, 97, 237, 1);
            }

            .custom-checkbox:checked:after {
                content: '\2714';
                font-size: 10px;
                position: absolute;
                top: 1px;
                left: 4px;
                color: #fff;
            }

            .custom-checkbox:focus {
                outline: none;
            }

            .signin {
                background-color: #fe5600;
                color: #FFF;
                width: 100%;
                padding: 10px 20px;
                display: block;
                height: 39px;
                border-radius: 20px;
                margin-top: 30px;
                transition: all 0.5s ease-in-out;
                border: none;
                text-transform: uppercase;
            }

            .signin:hover {
                background: #4082f5;
                box-shadow: 0px 4px 35px -5px #4082f5;
                cursor: pointer;
            }

            .signin:focus {
                outline: none;
            }

            hr {
                border: 1px solid rgba(255, 255, 255, 0.1);
            }
            .a-login {
                text-align: center;
                display: block;
                position: relative;
                text-decoration: none;
                color: rgb(86 255 251 / 80%);
            }

            .error {
                margin-left: 35%;
            }
        </style>

    </head>
    <body>
        <!-- Topbar Start -->
        <div class="container-fluid">
            <div class="row bg-secondary py-2 px-lg-5">
                <div class="col-lg-6 text-center text-lg-left mb-2 mb-lg-0">
                    <div class="d-inline-flex align-items-center">
                        <a class="text-white pr-3" href="">FAQs</a>
                        <span class="text-white">|</span>
                        <a class="text-white px-3" href="">Help</a>
                        <span class="text-white">|</span>
                        <a class="text-white pl-3" href="">Support</a>
                    </div>
                </div>
                <div class="col-lg-6 text-center text-lg-right">
                    <div class="d-inline-flex align-items-center">
                        <a style="text-align: center" class="text-white pl-3" href="login.jsp">
                            <i class="fa fa-user"></i> Log in
                        </a>
                    </div>
                </div>
            </div>
            <div class="row py-3 px-lg-5">
                <div class="col-lg-4">
                    <a href="" class="navbar-brand d-none d-lg-block">
                        <h1 class="m-0 display-5 text-capitalize"><span class="text-primary">Dog&Cat</span>Lover</h1>
                    </a>
                </div>
                <div class="col-lg-8 text-center text-lg-right">
                    <div class="d-inline-flex align-items-center">
                        <div class="d-inline-flex flex-column text-center pr-3 border-right">
                            <h6>Support Hours</h6>
                            <p class="m-0">8.00AM - 9.00PM</p>
                        </div>
                        <div class="d-inline-flex flex-column text-center px-3 border-right">
                            <h6>Email Us</h6>
                            <p class="m-0">info@gmail.com</p>
                        </div>
                        <div class="d-inline-flex flex-column text-center pl-3">
                            <h6>Call Us</h6>
                            <p class="m-0">+012 345 6789</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->

        <div class="container-fluid p-0">
            <nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-lg-5">
                <a href="" class="navbar-brand d-block d-lg-none">
                    <h1 class="m-0 display-5 text-capitalize font-italic text-white"><span class="text-primary">Dog&Cat</span>Lover</h1>
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between px-3" id="navbarCollapse">
                    <div class="navbar-nav mr-auto py-0">
                        <a href="index.jsp" class="nav-item nav-link active">Home</a>
                        <a href="about.jsp" class="nav-item nav-link">About</a>
                        <a href="DispatcherController?action=forums" class="nav-item nav-link">Forums</a>
                        <a href="DispatcherController?action=trade" class="nav-item nav-link">Trade</a>
                        <!--                        
                        <div class="nav-item dropdown">
                            <a href="tradepage.jsp" class="nav-link dropdown-toggle" data-toggle="dropdown">Trade</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="#" class="dropdown-item">Dog</a>
                                <a href="#" class="dropdown-item">Cat</a>
                                <a href="#" class="dropdown-item">Items</a>
                            </div>
                        </div>
                        -->
                        <a href="contact.jsp" class="nav-item nav-link">Contact</a>
                    </div>

                </div>
            </nav>
        </div>
        <!-- Navbar End -->
        <br>
        <!-- Log In -->
        <div class="login col-md-6 mx-auto mb-5" style="">
            <br>
            <div class="header-login">
                <h2 style="color: white; text-align: center" class="active">ĐĂNG NHẬP</h2>
            </div>
            <br>
            <form action="DispatcherController" method="post">
                <input type="hidden" name="action" value="login" />
                <input type="text" class="text" name="userid" id="username" required="">
                <label for="username"><span>Tài khoản</span></label>
                <br>
                <br>
                <input type="password" class="text" name="password" id="password" required="">
                <label for="password"><span>Mật khẩu</span></label>
                <br>
                <label class="error">${requestScope.ERROR}</label>
                <br>
                <div>
                    <input class="signin" type="submit" value="Đăng Nhập">
                </div>
                <hr>
                <a class="a-login col-md-6 mx-auto" href="#">Quên mật khẩu?</a>
                <br>
                <a class="a-login col-md-6 mx-auto" href="signup.jsp">Đăng ký</a>
            </form>
        </div>

        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-white mt-5 py-5 px-sm-3 px-md-5">
            <div class="row pt-5">
                <div class="col-lg-4 col-md-12 mb-5">
                    <h1 class="mb-3 display-5 text-capitalize text-white"><span class="text-primary">Dog&Cat</span>Lover</h1>
                    <p class="m-0">Chung toi hi vong nen tang nay se giup ban trong viec cham soc thu cung va hay tham gia dien dan de ban co the tham gia trao doi voi nha nhu trao doi cho, meo, do dung cua cho hoac meo va dich vu cham soc thu cung</p>
                </div>
                <div class="col-lg-8 col-md-12">
                    <div class="row">
                        <div class="col-md-4 mb-5">
                            <h5 class="text-primary mb-4">Get In Touch</h5>
                            <p><i class="fa fa-map-marker-alt mr-2"></i>Nha van hoa sinh vien, Tp.Thu Duc, VN</p>
                            <p><i class="fa fa-phone-alt mr-2"></i>+012 345 67890</p>
                            <p><i class="fa fa-envelope mr-2"></i>info@example.com</p>
                        </div>
                        <div class="col-md-4 mb-5">
                            <h5 class="text-primary mb-4">Popular Links</h5>
                            <div class="d-flex flex-column justify-content-start">
                                <a class="text-white mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Home</a>
                                <a class="text-white mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Forums</a>
                                <a class="text-white mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Trade</a>
                                <a class="text-white" href="#"><i class="fa fa-angle-right mr-2"></i>Contact Us</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid text-white py-4 px-sm-3 px-md-5" style="background: #111111;">
            <div class="row">
                <div class="col-md-6 text-center text-md-left mb-3 mb-md-0">
                    <p class="m-0 text-white">
                        &copy; <a class="text-white font-weight-bold" href="#"> Donate</a> de giup tui minh phat trien them nha. All Rights Reserved.
                    </p>
                </div>
                <div class="col-md-6 text-center text-md-right">
                    <ul class="nav d-inline-flex">
                        <li class="nav-item">
                            <a class="nav-link text-white py-0" href="#">Privacy</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white py-0" href="#">Terms</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white py-0" href="#">FAQs</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white py-0" href="#">Help</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- Footer End -->

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/tempusdominus/js/moment.min.js"></script>
        <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>
</html>

