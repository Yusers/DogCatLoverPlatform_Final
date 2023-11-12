<%-- 
    Document   : tradingpage
    Created on : Oct 4, 2023, 2:16:48 PM
    Author     : overw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dbaccess.MediaDAO" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Flaticon Font -->
        <link href="lib/flaticon/font/flaticon.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">

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
                            <c:otherwise>
                                <div class="dropdown">
                                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fa fa-user"></i> ${us.user_id}
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="viewprofile.jsp">View Profile</a>
                                        <c:if test="${us.role eq 'ADMIN'}">
                                            <a class="dropdown-item" href="DispatcherController?action=manage">Dashboard</a>
                                        </c:if>
                                        <c:if test="${us.role eq 'STAFF'}">
                                            <a class="dropdown-item" href="DispatcherController?action=staff-manage">Dash board</a>
                                        </c:if>
                                        <a class="dropdown-item" href="DispatcherController?action=my-post">My Posts</a>
                                        <a class="dropdown-item" href="LoadConversationController">Chat</a>
                                        <a class="dropdown-item" href="DispatcherController?action=logout">Log out</a>
                                    </div>
                                </div>
                            </c:otherwise>
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

        <!-- Navbar Start -->
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
                        <a href="index.jsp" class="nav-item nav-link">Home</a>
                        <a href="about.jsp" class="nav-item nav-link">About</a>
                        <a href="DispatcherController?action=forums" class="nav-item nav-link">Forums</a>
                        <a href="DispatcherController?action=trade" class="nav-item nav-link active">Trade</a>
                        <a href="contact.jsp" class="nav-item nav-link">Contact</a>
                    </div>

                </div>
            </nav>
        </div>
        <!-- Navbar End -->
        <c:set var="trade" value="${requestScope.TRADE}" />
        <!-- Trading Start -->
        <div class="container mt-5 mb-5">
            <span>
                <label for="heading"><strong>Chỉnh sửa tiêu đề ở đây</strong></label>
            </span>
            <h2 id="heading" class="mb-4 col-md-12"><input style="border: none; width: 100%" type="text" value="${trade.title}" /></h2>

            <div class="row">
                <div class="col-md-8">
                    <div class="col-12">
                        <div class="form-group">
                            <br/>
                            <button type="button" onclick="previewFiles()" class="btn btn-primary col-4" data-toggle="modal" data-target="#filePreviewModal">
                                Xem trước hình ảnh
                            </button>
                        </div>
                        <input type="hidden" id="editedImageURL" name="editedImageURL">
                    </div>
                    <div id="carouselExampleIndicators" class="carousel slide col-12">
                        <ol class="carousel-indicators">
                            <c:forEach var="url" items="${media.url}" varStatus="loop">
                                <li data-target="#carouselExampleIndicators" data-slide-to="${loop.index}" class="${loop.index == 0 ? 'active' : ''}"></li>
                                </c:forEach>
                        </ol>
                        <c:set var="listMedia" value="${MediaDAO.getAllMedia(trade.id)}" />
                        <div class="carousel-inner">
                            <c:forEach var="media" items="${listMedia}" varStatus="loop">
                                <div class="carousel-item ${loop.index == 0 ? 'active' : ''}">
                                    <label for="inputfile${loop.index}">
                                        <div class="card" style="height: 500px">
                                            <img id="previewImage${loop.index}" style="object-fit: cover; height: 100%" src="${media.url}" class="card-img-top img-fluid" alt="${media.file_name}">
                                        </div>
                                    </label>
                                    <input name="image" type="file" class="custom-file-input" id="inputfile${loop.index}" onchange="previewFile(${loop.index})">
                                </div>
                            </c:forEach>
                        </div>


                        <a style="color: black" class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
                <c:set var="author" value="${requestScope.AUTHOR}" />
                <div class="col-md-4 justify-content-center">
                    <form action="DispatcherController" method="POST">
                        <input type="hidden" name="action" value="create-conversation" />
                        <input type="hidden" name="sender_id" value="${us.user_id}" />
                        <input type="hidden" name="receiver_id" value="${trade.author_id}" />
                        <input type="hidden" name="topic" value="${trade.title} | ${trade.author_id} | ${us.user_id}" />
                        <div class="user">
                            <div class="user-info">
                                <h5>Tên: ${author.fullname}</h5>
                                <p>Email: ${author.email}</p>
                                <p>Số điện thoại: ${author.phone_number}</p>    
                            </div>
                            <div class="user-action">
                                <c:if test="${not empty us.role}"><button type="submit" class="btn btn-primary btn-block" ${(us.user_id == trade.author_id || us.status ne 'Active')? 'disabled' : ''}>${us.status ne 'Active' ? 'Có vẻ bạn đã bị cấm chat' : 'Nhắn tin cho người bán'}</button></c:if>
                                <c:if test="${empty us.role}"><a class="btn btn-primary btn-block" href="login.jsp">Nhắn tin cho người bán</a></c:if>
                                </div>
                            </div>
                        </form>
                        <br>
                        <div class="trade-post-details">
                            <h5>Mô tả</h5>
                            <div class="form-floating">
                                <label for="content"><strong>Chỉnh sửa nội dung bấm vào đây</strong></label>
                                <textarea rows='${trade.content.length() > 300 ? 15 : 6} ' class="form-control custom-input" placeholder="${trade.content}" name="content" id="content">${trade.content}</textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Trading End -->

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
                        <&a class="text-white font-weight-bold" href=""></a>
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

        <script>
            function previewFile(index) {
                var preview = document.getElementById('previewImage' + index);
                var fileInput = document.getElementById('inputfile' + index);
                var file = fileInput.files[0];

                var reader = new FileReader();

                reader.onloadend = function () {
                    preview.src = reader.result;
                };

                if (file) {
                    reader.readAsDataURL(file);
                } else {
                    preview.src = '${empty post.image ? 'assets/img/no-img.jpg' : post.image}';
                }
            }

            function showLoginPrompt() {
                var confirmation = confirm("You must be logged in to Chat. Do you want to go to the login page?");
                if (confirmation) {
                    window.location.href = "login.jsp"; // Điều hướng đến trang đăng nhập
                }
            }
        </script>
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

