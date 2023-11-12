<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Account" %>
<%@page import="dbaccess.Trade_CategoryDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
                        <a href="index.jsp" class="nav-item nav-link active">Home</a>
                        <a href="about.jsp" class="nav-item nav-link">About</a>
                        <a href="DispatcherController?action=forums" class="nav-item nav-link">Forums</a>
                        <a href="DispatcherController?action=trade" class="nav-item nav-link">Trade</a>
                        <a href="contact.jsp" class="nav-item nav-link">Contact</a>
                    </div>

                </div>
            </nav>
        </div>
        <!-- Navbar End -->
        <c:set var="listOfTrade" value="${requestScope.TRADES}" />

        <!-- Staff Dashboard Start -->
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-12">
                    <h1>Staff Dashboard</h1>
                    <div class="row mb-3">
                        <div class="col-md-12 text-center">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Tổng bài viết trao đổi và mua bán</h5>
                                    <p class="card-text">${listOfTrade.size()}</p>
                                    <a class="btn btn-primary btn-block" href="DispatcherController?action=staff-manage">Quay lại</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Manage Threads Start -->
                    <!-- Thread List -->
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="accordion" id="threadAccordion">
                                <!-- Thread 1 -->
                                <c:forEach var="trade" items="${listOfTrade}">
                                    <div class="card">
                                        <div class="card-header d-flex align-content-center justify-content-between" id="thread${trade.id}">
                                            <div class="header-post">
                                                <h4 class="ml-2">
                                                    Bài viết của: ${trade.author_id}
                                                </h4>
                                                <h2 class="mb-0">
                                                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseThread${trade.id}" aria-expanded="true" aria-controls="collapseThread${trade.id}">
                                                        ${trade.title}
                                                    </button>
                                                </h2>
                                            </div>
                                            <div class="header-action">
                                                <a class="btn btn-success" href="DispatcherController?action=handle-trade&btn=approve&id=${trade.id}"}>Chấp nhận</a>
                                                <button class="btn btn-danger" data-toggle="modal" data-target="#rejectPostModal${trade.id}">Từ chối</button>
                                            </div>
                                        </div>
                                        <div class="modal fade" id="rejectPostModal${trade.id}" tabindex="-1" role="dialog" aria-labelledby="rejectPostModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <form action="DispatcherController" method="POST">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="rejectPostModalLabel">Từ chối bài viết</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <input type="hidden" name="action" value="handle-trade" />
                                                            <input type="hidden" name="id" value="${trade.id}"/>
                                                            <div class="form-group">
                                                                <label for="rejectReason">Lí do</label>
                                                                <textarea name="reason" class="form-control" id="rejectReason" rows="3"></textarea>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="submit" value="reject" name="btn" class="btn btn-danger">Từ chối</button>
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="collapseThread${trade.id}" class="collapse" aria-labelledby="thread${trade.id}" data-parent="#threadAccordion">
                                            <div class="card-body">
                                                <div class="card">

                                                    <div class="card-body">
                                                        <!-- Thread Information -->
                                                        <h5>${trade.title}</h5>
                                                        <div class="mb-3 ml-3">
                                                            <p>Tác giả: <strong>${trade.author_id}</strong></p>
                                                            <p class="text-truncate">${trade.content}</p>
                                                            <p>Thể Loại: ${Trade_CategoryDAO.getTradeCategory(trade.cate_id).name}</p>
                                                            <p>Trạng thái: ${trade.status}</p>
                                                        </div>

                                                        <!-- Thread Actions (e.g., Delete, Edit) -->
                                                        <div class="row">
                                                            <div class="col-md-10"></div>
                                                            <div class="col-md-2 d-flex justify-content-end">
                                                                <a href="DispatcherController?action=trade-details&edit=staff&id=${trade.id}" class="btn btn-primary">Xem chi tiết</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                <c:if test="${empty listOfTrade}">
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
        </div>
        <!-- Staff Dashboard End -->



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
