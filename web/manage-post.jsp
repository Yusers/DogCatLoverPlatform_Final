<%-- 
    Document   : manage-threads
    Created on : Oct 11, 2023, 12:30:58 AM
    Author     : overw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dbaccess.Post_CategoryDAO" %>

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
        <style>
            .custom-btn:hover {
                cursor: text;
                text-decoration: none;
                border: 1px solid black;
                color: black;
            }

            .custom-title-post:hover {
                cursor: pointer;
                opacity: 0.8;
            }

            .toast-container {
                position: absolute;
                bottom: 2%;
                right: 0;
                z-index: 9999; /* Adjust this value if necessary */
                padding: 0.5rem 2rem;
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
                                        <c:if test="${us.role == 'ADMIN'}">
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
                        <a href="DispatcherController?action=trade" class="nav-item nav-link">Trade</a>
                        <a href="contact.jsp" class="nav-item nav-link">Contact</a>
                    </div>
                </div>
            </nav>
        </div>
        <!-- Navbar End -->
        <c:set var="author" value="${requestScope.AUTHOR}" />
        <!-- Manage Threads Start -->
        <div class="container mt-5">
            <h1 class="mb-4">Quản lý bài viết</h1>

            <!-- Thread List -->
            <div class="card">
                <div class="card-header">
                    Các bài viết của <strong style="color: red;">${author eq us.user_id ? 'bạn' : author}</strong>
                </div>
                <div class="row">
                    <div class="card-body col-md-6">
                        <h3>Bài viết về trao đổi & mua bán</h3>
                        <div class="accordion" id="threadAccordion">
                            <c:set var="posts" value="${requestScope.TRADES}" />
                            <!-- Thread 1 -->
                            <c:forEach var="post" items="${posts}">
                                <div class="card shadow p-1 bg-black rounded">
                                    <div class="card-header custom-title-post" id="thread${post.id}" data-toggle="collapse" data-target="#collapseThread${post.id}" aria-expanded="true" aria-controls="collapseThread${post.id}">
                                        <h6>
                                            Bài viết: <strong>${post.title}</strong>
                                        </h6>
                                        <c:if test="${post.status eq 'Created'}">
                                            <p>Trạng thái: <strong style="color: blue;">Đang chờ duyệt</strong></p>
                                        </c:if>
                                        <c:if test="${post.status eq 'Rejected'}">
                                            <p>Trạng thái: <strong style="color: red;">Bị từ chối</strong></p>
                                        </c:if>
                                        <c:if test="${post.status eq 'Approved'}">
                                            <p>Trạng thái: <strong style="color: green;">Đã duyệt</strong></p>
                                        </c:if>
                                    </div>

                                    <div id="collapseThread${post.id}" class="collapse" aria-labelledby="thread${post.id}" data-parent="#threadAccordion">
                                        <div class="card-body">
                                            <div class="card shadow p-1 bg-black rounded">
                                                <div class="card-body">
                                                    <!-- Thread Information -->
                                                    <div class="mb-3">
                                                        <h5 class="mb-3">Tiêu đề: ${post.title}</h5>
                                                        <p class="text-truncate"><strong>Nội dung:</strong> ${post.content}</p>
                                                        <p><strong>Thể Loại:</strong> ${Post_CategoryDAO.getPostCategory(post.cate_id).name}</p>
                                                        <c:if test="${post.status eq 'Created'}">
                                                            <p><strong>Trạng thái:</strong> <strong style="color: blue;">Đang chờ duyệt</strong></p>
                                                        </c:if>
                                                        <c:if test="${post.status eq 'Rejected'}">
                                                            <p><strong>Trạng thái:</strong> <strong style="color: red;">Bị từ chối</strong></p>
                                                            <p><strong>Lí do:</strong> <strong style="color: red;">${post.rejected_reason}</strong></p>
                                                            </c:if>
                                                            <c:if test="${post.status eq 'Approved'}">
                                                            <p><strong>Trạng thái:</strong> <strong style="color: green;">Đã duyệt</strong></p>
                                                        </c:if>
                                                    </div>

                                                    <!-- Thread Actions (e.g., Delete, Edit) -->
                                                    <div class="row d-flex">
                                                        <div class="btn-group col-md-6 d-flex justify-content-start">
                                                            <a href="DispatcherController?action=thread&id=${post.id}&edit=true" class="btn btn-primary">Chỉnh sửa</a>
                                                            <a href="DispatcherController?action=trade-delete&id=${post.id}" class="btn btn-danger">Xóa</a>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-4 d-flex align-bottom justify-content-end">
                                                            <c:if test="${us.user_id eq author}"><a href="DispatcherController?action=trade-details&id=${post.id}" class="btn btn-primary">Xem chi tiết</a></c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                            </c:forEach>
                            <c:if test="${empty posts}">
                                <h4>Chưa có bài viết</h4>
                                <c:if test="${us.role ne 'STAFF'}">
                                    <a class="custom-btn form-control" href="${us.user_id != null ? 'create-post.jsp' : 'login.jsp'}">Create post...</a>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                    <div class="card-body col-md-6">
                        <h3>Bài viết trên diễn đàn</h3>
                        <div class="accordion" id="threadAccordion">
                            <c:set var="posts" value="${requestScope.POSTS}" />
                            <!-- Thread 1 -->
                            <c:forEach var="post" items="${posts}">
                                <div class="card shadow p-1 bg-black rounded">
                                    <div class="card-header custom-title-post" id="thread${post.id}" data-toggle="collapse" data-target="#collapseThread${post.id}" aria-expanded="true" aria-controls="collapseThread${post.id}">
                                        <h6>
                                            Bài viết: <strong>${post.title}</strong>
                                        </h6>
                                        <c:if test="${post.status eq 'Created'}">
                                            <p>Trạng thái: <strong style="color: blue;">Đang chờ duyệt</strong></p>
                                        </c:if>
                                        <c:if test="${post.status eq 'Rejected'}">
                                            <p>Trạng thái: <strong style="color: red;">Bị từ chối</strong></p>
                                        </c:if>
                                        <c:if test="${post.status eq 'Approved'}">
                                            <p>Trạng thái: <strong style="color: green;">Đã duyệt</strong></p>
                                        </c:if>
                                    </div>

                                    <div id="collapseThread${post.id}" class="collapse" aria-labelledby="thread${post.id}" data-parent="#threadAccordion">
                                        <div class="card-body">
                                            <div class="card shadow p-1 bg-black rounded">

                                                <div class="card-body">
                                                    <!-- Thread Information -->
                                                    <div class="mb-3">
                                                        <h5 class="mb-3">Tiêu đề: ${post.title}</h5>
                                                        <p class="text-truncate"><strong>Nội dung:</strong> ${post.content}</p>
                                                        <p><strong>Thể Loại:</strong> ${Post_CategoryDAO.getPostCategory(post.cate_id).name}</p>
                                                        <c:if test="${post.status eq 'Created'}">
                                                            <p><strong>Trạng thái:</strong> <strong style="color: blue;">Đang chờ duyệt</strong></p>
                                                        </c:if>
                                                        <c:if test="${post.status eq 'Rejected'}">
                                                            <p><strong>Trạng thái:</strong> <strong style="color: red;">Bị từ chối</strong></p>
                                                            <p><strong>Lí do:</strong> <strong style="color: red;">${post.rejected_reason}</strong></p>
                                                            </c:if>
                                                            <c:if test="${post.status eq 'Approved'}">
                                                            <p><strong>Trạng thái:</strong> <strong style="color: green;">Đã duyệt</strong></p>
                                                        </c:if>
                                                    </div>

                                                    <!-- Thread Actions (e.g., Delete, Edit) -->
                                                    <div class="row">
                                                        <div class="btn-group col-md-6 d-flex justify-content-start">
                                                            <a href="DispatcherController?action=thread&id=${post.id}&edit=true" class="btn btn-primary">Chỉnh sửa</a>
                                                            <a href="DispatcherController?action=thread-delete&id=${post.id}" class="btn btn-danger">Xóa</a>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-4">
                                                            <c:if test="${us.user_id eq author}"><a href="DispatcherController?action=thread&id=${post.id}" class="btn btn-primary">Xem chi tiết</a></c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                            </c:forEach>
                            <c:if test="${empty posts}">
                                <h4>Chưa có bài viết</h4>
                                <c:if test="${us.role ne 'STAFF'}">
                                    <a class="custom-btn form-control" href="${us.user_id != null ? 'create-post.jsp' : 'login.jsp'}">Create post...</a>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:set var="status" value="${requestScope.STATUS}"/>
        <c:set var="action" value="${requestScope.ACTION}"/>
        <c:set var="msg" value="${requestScope.MSG}"/>
        <div class="toast toast-container" id="deleteToast" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000" >
            <div class="toast-header">
                <strong class="mr-auto ${status eq true ? 'text-success' : 'text-danger'}">${action} ${status eq true ? 'Success' : 'Failed'}</strong>
                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="toast-body">
                <strong>${msg}</strong>
            </div>
        </div>
        <!-- Add this to your JSP page, after including Bootstrap -->
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
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var deleteSuccess = <%= request.getAttribute("STATUS") != null %>;
                if (deleteSuccess) {
                    var deleteToast = new bootstrap.Toast(document.getElementById('deleteToast'));
                    deleteToast.show();
                }
            });
        </script>
        <!-- About End -->

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

