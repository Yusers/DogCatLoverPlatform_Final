<%-- 
    Document   : about
    Created on : Oct 1, 2023, 10:28:30 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin | Cat Dog Lover Website</title>

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
                                        <c:if test="${us.role eq 'ADMIN'}">
                                            <a class="dropdown-item" href="DispatcherController?action=manage">Dashboard</a>
                                        </c:if>
                                        <c:if test="${us.role eq 'STAFF'}">
                                            <a class="dropdown-item" href="DispatcherController?action=staff-manage">Dash board</a>
                                        </c:if>
                                        <a class="dropdown-item" href="DispatcherController?action=my-post">My Posts</a>
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
                        <a href="#" class="nav-item nav-link">Contact</a>
                    </div>

                </div>
            </nav>
        </div>
        <!-- Navbar End -->

        <!-- Admin Start -->
        <c:set var="listOfMember" value="${requestScope.MEMBERS}" />
        <c:set var="listOfStaff" value="${requestScope.STAFFS}" />
        <div class="container-lg mt-3">
            <div class="row">
                <div class="col-md-12">
                    <h1>Dashboard</h1>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Total Members</h5>
                                    <p class="card-text">${listOfMember.size()}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Total Staff</h5>
                                    <p class="card-text">${listOfStaff.size()}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <a class="btn btn-primary" href="DispatcherController?action=create-staff">Create Staff</a>
                        <div class="card-body">
                            <h5 class="card-title">Manage Staffs</h5>
                            <form action="SearchStaffController" method="post">
                                <div class="input-group mb-3">
                                    <input type="text" name="search" class="form-control ml-2" placeholder="Search Staffs ..." aria-describedby="button-addon2">
                                    <button class="btn btn-outline-primary" type="submit" id="button-addon2">Button<i class="fas fa-search"></i></button>
                                </div>
                            </form>
                            <c:if test="${not empty staffs}">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Staff ID</th>
                                            <th>Staff Name</th>
                                            <th>Staff Email</th>
                                            <th>Staff Phone</th>
                                            <th>Staff Status</th>
                                            <th class="text-center">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Rows for members go here -->
                                        <c:forEach var="staff" items="${staffs}">
                                            <tr>
                                                <td>${staff.user_id}</td>
                                                <td><a href="DispatcherController?action=manage&actions=viewprofile&usname=${staff.user_id}">${staff.fullname}</a></td>
                                                <td>${staff.email}</td>
                                                <td>${staff.phone_number}</td>
                                                <td>${staff.status}</td>
                                                <td class="text-center">
                                                    <!--<button class="btn btn-warning"><a href="editprofileuser.jsp">Edit</a></button>-->
                                                    <form action="ActionController" method="post">
                                                        <input type="hidden" name="username" value="${staff.user_id}"/>
                                                        <button name="action" value="delete" class="btn btn-danger">
                                                            <i class="fas fa-trash"></i>
                                                        </button>
                                                        <button name="action" value="ban" class="btn btn-danger" data-toggle="modal" data-target="#modal-promote">
                                                            <i class="fas fa-lock"></i>
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>

                            <c:if test="${empty staffs}">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Staff ID</th>
                                            <th>Staff Name</th>
                                            <th>Staff Email</th>
                                            <th>Staff Phone</th>
                                            <th>Staff Status</th>
                                            <th class="text-center">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Rows for members go here -->
                                        <c:forEach var="s" items="${listOfStaff}">
                                            <tr>
                                                <td>${s.user_id}</td>
                                                <td><a href="DispatcherController?action=manage&actions=viewprofile&usname=${s.user_id}">${s.fullname}</a></td>
                                                <td>${s.email}</td>
                                                <td>${s.phone_number}</td>
                                                <td>${s.status}</td>
                                                <td class="text-center">
                                                    <!--<button class="btn btn-warning"><a href="editprofileuser.jsp">Edit</a></button>-->
                                                    <form action="ActionController" method="post">
                                                        <input type="hidden" name="username" value="${s.user_id}"/>
                                                        <button name="action" value="delete" class="btn btn-danger">
                                                            <i class="fas fa-trash"></i>
                                                        </button>
                                                        <button name="action" value="ban" class="btn btn-danger" data-toggle="modal" data-target="#modal-promote">
                                                            <i class="fas fa-lock"></i>
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Manage Members</h5>
                            <form action="SearchMemberController" method="post">
                                <div class="input-group mb-3">
                                    <input type="text" name="search" class="form-control ml-2" placeholder="Search Staffs ..." aria-describedby="button-addon2">
                                    <button class="btn btn-outline-primary" type="submit" id="button-addon2">Button<i class="fas fa-search"></i></button>
                                </div>
                            </form>
                            <c:if test="${not empty members}">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Member ID</th>
                                            <th>Member Name</th>
                                            <th>Member Email</th>
                                            <th>Member Phone</th>
                                            <th>Member Status</th>
                                            <th class="text-center">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Rows for members go here -->
                                        <c:forEach var="member" items="${members}">
                                            <tr>
                                                <td>${member.user_id}</td>
                                                <td><a href="DispatcherController?action=manage&actions=viewprofile&usname=${member.user_id}">${member.fullname}</a></td>
                                                <td>${member.email}</td>
                                                <td>${member.phone_number}</td>
                                                <td>${member.status}</td>
                                                <td class="text-center">
                                                    <!--<button class="btn btn-warning"><a href="editprofileuser.jsp">Edit</a></button>-->
                                                    <form action="ActionController" method="post">
                                                        <input type="hidden" name="username" value="${member.user_id}"/>
                                                        <button name="action" value="delete" class="btn btn-danger">
                                                            <i class="fas fa-trash"></i>
                                                        </button>
                                                        <button name="action" value="ban" class="btn btn-danger" data-toggle="modal" data-target="#modal-promote">
                                                            <i class="fas fa-lock"></i>
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>

                            <c:if test="${empty members}">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Member ID</th>
                                            <th>Member Name</th>
                                            <th>Member Email</th>
                                            <th>Member Phone</th>
                                            <th>Member Status</th>
                                            <th class="text-center">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Rows for members go here -->
                                        <c:forEach var="m" items="${listOfMember}">
                                            <tr>
                                                <td>${m.user_id}</td>
                                                <td><a href="DispatcherController?action=manage&actions=viewprofile&usname=${m.user_id}">${m.fullname}</a></td>
                                                <td>${m.email}</td>
                                                <td>${m.phone_number}</td>
                                                <td>${m.status}</td>
                                                <td class="text-center">
                                                    <!--<button class="btn btn-warning"><a href="editprofileuser.jsp">Edit</a></button>-->
                                                    <form action="ActionController" method="post">
                                                        <input type="hidden" name="username" value="${m.user_id}"/>
                                                        <button name="action" value="delete" class="btn btn-danger">
                                                            <i class="fas fa-trash"></i>
                                                        </button>
                                                        <button name="action" value="ban" class="btn btn-danger" data-toggle="modal" data-target="#modal-promote">
                                                            <i class="fas fa-lock"></i>
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Admin End -->

        <!-- Modal -->
        <!-- Modal Promote -->
        <!--        <div class="modal fade" id="modal-promote" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">Ban User</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">How many day?</span>
                                    </div>
                                    <input type="number" class="form-control" id="numberInput">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">How many hours?</span>
                                    </div>                               
                                    <input type="number" class="form-control" id="numberInput">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>-->

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
