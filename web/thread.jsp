<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dbaccess.CommentDAO" %>


<!DOCTYPE html>
<c:set var="post" value="${requestScope.POST}" />
<c:set var="cate" value="${requestScope.CATE}" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${post.title}</title>
        <!-- Favicon -->
        <link href="img/icons8-pet-lover-16.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Flaticon Font -->
        <link href="lib/flaticon/font/flaticon.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- css custom -->
        <link rel="stylesheet" href="./assets/css/thread.css"/>

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <style>
            .cus-comment {
                margin-left: 4rem;
            }

            .cus-comment:hover {
                cursor: pointer;
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
                        <a href="DispatcherController?action=forums" class="nav-item nav-link active">Forums</a>
                        <a href="DispatcherController?action=trade" class="nav-item nav-link">Trade</a>
                        <a href="contact.jsp" class="nav-item nav-link">Contact</a>
                    </div>

                </div>
            </nav>
        </div>
        <!-- Navbar End -->
        <c:set var="us" value="${sessionScope.USER}"/>

        <!-- Threads -->
        <div class="container mt-5">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="index.jsp">Trang chủ</a></li>
                    <li class="breadcrumb-item"><a href="DispatcherController?action=forums">Diễn Đàn</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Bài Đăng Về -> ${cate.name}</li>
                </ol>
            </nav>
            <!-- Thread Title -->
            <h1>${post.title}</h1>

            <!-- Thread Author -->
            <div class="row align-items-center mb-4">
                <div class="col-md-12">
                    <div class="alert alert-info">
                        <div class="author">
                            <svg xmlns="http://www.w3.org/2000/svg" height="2em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M304 128a80 80 0 1 0 -160 0 80 80 0 1 0 160 0zM96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM49.3 464H398.7c-8.9-63.3-63.3-112-129-112H178.3c-65.7 0-120.1 48.7-129 112zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3z"/></svg>
                            <div class="author-container">
                                <div class="author-info">
                                    <c:if test="${us.role != null}"><h6><a href="DispatcherController?action=manage&actions=viewprofile&usname=${post.author_id}">${post.author_id}</a></h6></c:if>
                                    <c:if test="${us.role == null}"><h6><a href="login.jsp">${post.author_id}</a></h6></c:if>
                                    <p>${post.created_at}</p>
                                </div>
                                <div class="post-action">
                                    <a href="#"><svg xmlns="http://www.w3.org/2000/svg" height="1.8em" viewBox="0 0 384 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M0 48C0 21.5 21.5 0 48 0l0 48V441.4l130.1-92.9c8.3-6 19.6-6 27.9 0L336 441.4V48H48V0H336c26.5 0 48 21.5 48 48V488c0 9-5 17.2-13 21.3s-17.6 3.4-24.9-1.8L192 397.5 37.9 507.5c-7.3 5.2-16.9 5.9-24.9 1.8S0 497 0 488V48z"/></svg></a>
                                    <a href="#"><svg xmlns="http://www.w3.org/2000/svg" height="1.8em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M224 0c-17.7 0-32 14.3-32 32V49.9C119.5 61.4 64 124.2 64 200v33.4c0 45.4-15.5 89.5-43.8 124.9L5.3 377c-5.8 7.2-6.9 17.1-2.9 25.4S14.8 416 24 416H424c9.2 0 17.6-5.3 21.6-13.6s2.9-18.2-2.9-25.4l-14.9-18.6C399.5 322.9 384 278.8 384 233.4V200c0-75.8-55.5-138.6-128-150.1V32c0-17.7-14.3-32-32-32zm0 96h8c57.4 0 104 46.6 104 104v33.4c0 47.9 13.9 94.6 39.7 134.6H72.3C98.1 328 112 281.3 112 233.4V200c0-57.4 46.6-104 104-104h8zm64 352H224 160c0 17 6.7 33.3 18.7 45.3s28.3 18.7 45.3 18.7s33.3-6.7 45.3-18.7s18.7-28.3 18.7-45.3z"/></svg></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Thread Content -->
            <div class="row">
                <div class="col-md-12">
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="alert alert-secondary" role="alert">
                                Chúng tôi rất vui mừng khi chào đón bạn vào cộng đồng của chúng tôi. Đây là nơi để chia sẻ ý kiến, hỏi đáp và kết nối với những người có sở thích tương tự. 
                                Hãy không ngần ngại tham gia vào các cuộc trò chuyện và đóng góp ý kiến của bạn.
                            </div>
                            <!-- Thread img -->
                            <c:if test="${not empty post.image}">                            
                                <img style="height: 22rem; object-fit: scale-down;" src="${post.image}" class="card-img-top thread-img" alt="${post.title}"><br>
                            </c:if>
                            <pre>
                                ${post.content.trim()}
                            </pre>
                            <!--<img src="./assets/img/blog-2.jpg" class="card-img-top thread-img" alt="blog-1"><br>-->
                        </div>
                    </div>
                </div>
            </div>
            <!-- Sidebar with Replies -->
            <div class="row">
                <!-- Replies Section -->
                <div class="col-md-12 replies">
                    <div class="comment">
                        <div class="user-info">
                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M304 128a80 80 0 1 0 -160 0 80 80 0 1 0 160 0zM96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM49.3 464H398.7c-8.9-63.3-63.3-112-129-112H178.3c-65.7 0-120.1 48.7-129 112zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3z"/></svg>
                            <span><c:if test="${us.role != null}"><a href="DispatcherController?action=manage&actions=viewprofile&usname=${us.user_id}">${us.user_id != null ? us.user_id : "Guess"}</a></c:if>
                                <c:if test="${us.role == null}"><a href="login.jsp">${us.user_id != null ? us.user_id : "Guess"}</a></c:if></span>
                            </div>
                            <form action="DispatcherController" method="POST">
                                <input type="hidden" name="action" value="comment" />
                                <input type="hidden" name="post-id" value="${post.id}" />
                            <input type="hidden" name="author-id" value="${us.user_id}" />
                            <input type="hidden" name="parent-id" value="0" />
                            <c:choose>
                                <c:when test="${empty us.role}">
                                    <input placeholder="Write a comment..." value="" type="text" class="form-control" aria-label="Sizing example input" disabled aria-describedby="inputGroup-sizing-sm">
                                    <button class="btn btn-primary mt-2" type="button" onclick="showLoginPrompt()">Comment</button>
                                </c:when>
                                <c:when test="${post.status eq 'Created' or post.status eq 'Rejected'}">
                                    <input placeholder="This post need Approve first" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" disabled>
                                </c:when>
                                <c:when test="${us.status ne 'Active'}">
                                    <input placeholder="You are not allowed to comments" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" disabled>
                                </c:when>
                                <c:otherwise>
                                    <input id="commentInput" name="content" required="*" placeholder="Write a comment..." type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
                                    <button class="btn btn-primary mt-2" type="submit">Comment</button>
                                </c:otherwise>
                            </c:choose>
                        </form>
                    </div>
                    <!-- Reply 1 -->
                    <c:forEach items="${requestScope.COMMENTS}" var="comment">
                        <c:choose>
                            <c:when test="${comment.parent_id == 0}">
                                <!-- This is a top-level comment -->
                                <div class="comment">
                                    <div class="user-info">
                                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M304 128a80 80 0 1 0 -160 0 80 80 0 1 0 160 0zM96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM49.3 464H398.7c-8.9-63.3-63.3-112-129-112H178.3c-65.7 0-120.1 48.7-129 112zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3z"/></svg>
                                        <span><c:if test="${us.role != null}"><a href="DispatcherController?action=manage&actions=viewprofile&usname=${comment.author_id}">${comment.author_id}</a></c:if>
                                            <c:if test="${us.role == null}"><a href="login.jsp">${comment.author_id}</a></c:if></span>
                                        </div>
                                        <p>${comment.content}</p>
                                    <a class="cus-comment text-primary" data-toggle="collapse" data-target="#replyFormTop${comment.id}">Reply</a>
                                    <div class="mt-1 ml-4 collapse" id="replyFormTop${comment.id}">
                                        <div class="user-info">
                                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M304 128a80 80 0 1 0 -160 0 80 80 0 1 0 160 0zM96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM49.3 464H398.7c-8.9-63.3-63.3-112-129-112H178.3c-65.7 0-120.1 48.7-129 112zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3z"/></svg>
                                            <span><a href="DispatcherController?action=manage&actions=viewprofile&usname=NhatNTM">${us.user_id != null ? us.user_id : "Guess"}</a></span>
                                        </div>
                                        <form action="DispatcherController" method="POST">
                                            <input type="hidden" name="action" value="comment" />
                                            <input type="hidden" name="post-id" value="${post.id}" />
                                            <input type="hidden" name="author-id" value="${us.user_id}" />
                                            <input type="hidden" name="parent-id" value="${comment.id}" />
                                            <c:choose>
                                                <c:when test="${empty us.role}">
                                                    <input placeholder="Write a comment..." value="" type="text" class="form-control" aria-label="Sizing example input" disabled aria-describedby="inputGroup-sizing-sm">
                                                    <button class="btn btn-primary mt-2" type="button" onclick="showLoginPrompt()">Comment</button>
                                                </c:when>
                                                <c:when test="${us.role eq 'ADMIN' or us.role eq 'STAFF'}">
                                                    <input placeholder="You are not allowed to comment." type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" disabled>
                                                </c:when>
                                                <c:otherwise>
                                                    <input id="commentInput" name="content" required="*" placeholder="Write a comment..." type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
                                                    <button class="btn btn-primary mt-2" type="submit">Comment</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </div>
                                    <c:forEach var="sub" items="${CommentDAO.getSubComment(post.id, comment.id)}">
                                        <div class="sub-reply pl-4">
                                            <div class="user-info">
                                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M304 128a80 80 0 1 0 -160 0 80 80 0 1 0 160 0zM96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM49.3 464H398.7c-8.9-63.3-63.3-112-129-112H178.3c-65.7 0-120.1 48.7-129 112zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3z"/></svg>
                                                <span><a href="DispatcherController?action=manage&actions=viewprofile&usname=NhatNTM">${sub.author_id}</a></span>
                                            </div>
                                            <p>${sub.content}</p>
                                            <a class="cus-comment text-primary" data-toggle="collapse" data-target="#replyForm${comment.id}">Reply</a>
                                        </div>
                                    </c:forEach>
                                    <div class="mt-1 pl-4 collapse" id="replyForm${comment.id}">
                                        <div class="user-info">
                                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M304 128a80 80 0 1 0 -160 0 80 80 0 1 0 160 0zM96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM49.3 464H398.7c-8.9-63.3-63.3-112-129-112H178.3c-65.7 0-120.1 48.7-129 112zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3z"/></svg>
                                            <span><a href="DispatcherController?action=manage&actions=viewprofile&usname=NhatNTM">${us.user_id != null ? us.user_id : "Guess"}</a></span>
                                        </div>
                                        <form action="DispatcherController" method="POST">
                                            <input type="hidden" name="action" value="comment" />
                                            <input type="hidden" name="post-id" value="${post.id}" />
                                            <input type="hidden" name="author-id" value="${us.user_id}" />
                                            <input type="hidden" name="parent-id" value="${comment.id}" />
                                            <c:choose>
                                                <c:when test="${empty us.role}">
                                                    <input placeholder="Write a comment..." value="" type="text" class="form-control" aria-label="Sizing example input" disabled aria-describedby="inputGroup-sizing-sm">
                                                    <button class="btn btn-primary mt-2" type="button" onclick="showLoginPrompt()">Comment</button>
                                                </c:when>
                                                <c:when test="${us.role eq 'ADMIN' or us.role eq 'STAFF'}">
                                                    <input placeholder="You are not allowed to comment." type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" disabled>
                                                </c:when>
                                                <c:otherwise>
                                                    <input id="commentInput" name="content" required="*" placeholder="Write a comment..." type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
                                                    <button class="btn btn-primary mt-2" type="submit">Comment</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- end Threads -->

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
            $(document).ready(function () {
                $(".show-sub-replies").click(function () {
                    $(this).siblings(".sub-replies").toggle();
                });
            });

            function showLoginPrompt() {
                var confirmation = confirm("You must be logged in to comment. Do you want to go to the login page?");
                if (confirmation) {
                    window.location.href = "login.jsp"; // Điều hướng đến trang đăng nhập
                }
            }

            function comment() {
                // Thực hiện xử lý bình luận ở đây
                var commentInput = document.getElementById("commentInput");
                if (commentInput.value.trim() !== '') {
                    alert("Comment submitted");
                    commentInput.value = ''; // Xóa nội dung comment trong trường nhập
                } else {
                    alert("Please enter a comment before submitting.");
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
