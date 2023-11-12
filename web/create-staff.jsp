<%-- 
    Document   : signup
    Created on : Oct 2, 2023, 4:34:42 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng Ký | Cat Dog Lover Website</title>
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

            .registry {
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
                height: 30px;
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

            .signup {
                background-color: #fe5600;
                color: #FFF;
                width: 100%;
                padding: 10px 20px;
                display: block;
                height: 39px;
                border-radius: 20px;
                transition: all 0.5s ease-in-out;
                border: none;
                text-transform: uppercase;
            }

            .signup:hover {
                background: #4082f5;
                box-shadow: 0px 4px 35px -5px #4082f5;
                cursor: pointer;
            }

            .signup:focus {
                outline: none;
            }

            hr {
                border: 1px solid rgba(255, 255, 255, 0.1);
            }
            .a-registry {
                text-align: center;
                display: block;
                position: relative;
                text-decoration: none;
                color: rgb(86 255 251 / 80%);
            }

            label {
                color: white;
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
                        <!-- GET SESSION -->
                        <c:set var="us" value="${sessionScope.USER}" />
                        <c:choose>
                            <c:when test="${us == null}">
                                <a style="text-align: center" class="text-white pl-3" href="login.jsp">
                                    <i class="fa fa-user"></i> Log in
                                </a>
                            </c:when>
                            <c:when test="${us != null}">
                                <div class="dropdown">
                                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fa fa-user"></i> ${us.user_id}
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="viewprofile.jsp">View Profile</a>
                                        <c:if test="${us.role eq 'ADMIN'}">
                                            <a class="dropdown-item" href="DispatcherController?action=manage">Dashboard</a>
                                            <a class="dropdown-item" href="create-staff.jsp">Create new Staff</a>
                                        </c:if>
                                        <a class="dropdown-item" href="#">My Posts</a>
                                        <a class="dropdown-item" href="LoadConversationController">Chat</a>
                                        <a class="dropdown-item" href="DispatcherController?action=logout">Log out</a>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
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
        <!-- Sign Up -->
        <div class="registry col-md-6 mx-auto mb-5">
            <br>
            <div class="header-signup">
                <h2 style="color: white; text-align: center" class="active">Tạo nhân viên mới</h2>
            </div>
            <br>
            <form action="CreateStaffController" method="post">
                <input type="hidden" name="action" value="register"/>
                <div>
                    <label for="fullname">Họ và tên: </label>
                    <input type="text" id="fullname" class="text" name="fullname" required>
                    <label class="error">${requestScope.ERR_FULLNAME}</label>
                </div>
                <br>
                <div>
                    <label for="phonenumber">Số điện thoại: </label>
                    <input type="text" id="phonenumber" class="text" name="phonenumber" required>
                    <label class="error">${requestScope.ERR_PHONE}</label>
                </div>
                <br>
                <div>
                    <label for="username">Email: </label>
                    <input type="text" id="username" class="text" name="email" required>
                    <label class="error">${requestScope.ERR_EMAIL}</label>
                </div>
                <br>
                <!--                <div>
                                    <label for="password">Mật khẩu:</label>
                                    <input type="password" id="password" class="text" name="password" required>
                                    <label class="error">${requestScope.ERR_PASSWORD}</label>
                                </div>
                                <br>-->
                <hr>
                <div>
                    <input class="signup" type="submit" value="Đăng ký">
                </div>
                <br>
            </form>
        </div>
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