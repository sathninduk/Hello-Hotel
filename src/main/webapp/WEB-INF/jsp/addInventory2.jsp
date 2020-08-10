<%--
  Created by IntelliJ IDEA.
  User: Rivindu Chamath
  Date: 21-May-20
  Time: 2:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <link rel="icon" type="image/png" href="../../images/icons/gdfgd.png"/>
    <!-- Bootstrap -->
    <link href="../../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="../../build/css/custom.min.css" rel="stylesheet">
    <!-- Datatables -->
    <link href="../../vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="../../vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="../../vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="../../vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">

    <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(new Date());
    %>
    <style>
        .large-btn {
            height: 90px;
            width: 100%;
            font-family: "Playfair Display", Georgia, "Times New Roman", serif;
            font-weight: bolder;
            font-size: 27px;
        }


        .large-btn:hover {
            color: #0f0f0f;
            transform: scale(1.05, 1.1);
            border-color: #0f0f0f !important;
            border-width: 2px !important;
            transition: 0.8s ease;
            -webkit-transition: 0.8s ease;
            -moz-transition: 0.8s ease;
        }


        #chartdiv {
            position: relative;
            top: 40px;
            width: 100%;
            height: 400px;
        }

        #chartdiv1 {
            position: relative;
            top: 40px;
            width: 100%;
            height: 400px;
        }
    </style>
</head>

<body class="nav-md" style="cursor: pointer">

<div class="container body">
    <div class="main_container">

        <!-- Side header -->
        <jsp:include page="sideHeader.jsp"/>
        <!-- /Side header -->

        <!-- Top header -->
        <jsp:include page="topHeader.jsp"/>
        <!-- /Top header -->


        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>HR Department
                            <small>Welcome To Hotel Hareesha</small>
                        </h3>
                    </div>

                    <div class="title_right">
                        <script>

                            function formatTime() {
                                now = new Date();
                                hour = now.getHours();
                                min = now.getMinutes();
                                sec = now.getSeconds();

                                if (document.clock.sivamtime[0].checked) {
                                    if (min <= 9) {
                                        min = "0" + min;
                                    }
                                    if (sec <= 9) {
                                        sec = "0" + sec;
                                    }
                                    if (hour > 12) {
                                        hour = hour - 12;
                                        add = " p.m.";
                                    } else {
                                        hour = hour;
                                        add = " a.m.";
                                    }
                                    if (hour == 12) {
                                        add = " p.m.";
                                    }
                                    if (hour == 0) {
                                        hour = "12";
                                    }

                                    document.clock.sivam.value = ((hour <= 9) ? "0" + hour : hour) + ":" + min + ":" + sec + add;
                                }

                                if (document.clock.sivamtime[1].checked) {
                                    if (min <= 9) {
                                        min = "0" + min;
                                    }
                                    if (sec <= 9) {
                                        sec = "0" + sec;
                                    }
                                    if (hour < 10) {
                                        hour = "0" + hour;
                                    }
                                    document.clock.sivam.value = hour + ':' + min + ':' + sec;
                                }

                                setTimeout("formatTime()", 1000);
                            }

                            window.onload = formatTime;

                        </script>
                        <form name="clock" style="float: right">
                            <table class="clock" width="135">
                                <tr>
                                    <td class="clock2">
                                    </td>
                                </tr>
                                <tr>
                                    <h6 style="color:#73879C; float:right;border: none;background-color: #f6f6f6">
                                        <input style="color:#73879C; float:right;border: none;background-color: #f6f6f6"
                                               class="clock2" type="text" name="sivam" size="12"><br>
                                        <p><%=date%>
                                        </p>
                                    </h6>

                                </tr>
                                <tr>
                                    <td>
                                        <label class="clock3" for="1"><input type="radio" style="display: none" id="1"
                                                                             name="sivamtime" checked></label><br>
                                        <label class="clock3" for="2"><input type="radio" style="display: none" id="2"
                                                                             name="sivamtime"></label>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <%--Table--%>
                    <div class="col-12 col-sm-12 col-md-12 col-lg-7 col-xl-7">
                        <div class="row">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Find Users
                                        <small>User Page</small>
                                    </h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                        </li>
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                               aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                <a class="dropdown-item" href="#">Settings 1</a>
                                            </div>
                                        </li>
                                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="card-box table-responsive">
                                                <table id="datatable-buttons" class="table table-striped table-bordered">
                                                    <thead class="thead-light">
                                                    <tr>
                                                        <th>Account Id</th>
                                                        <th>Department Id</th>
                                                        <th>Department Name</th>
                                                        <th>Date</th>
                                                        <th>Income</th>
                                                        <th>Expenses</th>
                                                    </tr>

                                                    </thead>
                                                    <tbody>

                                                        <tr>
                                                            <td>AC001</td>
                                                            <td>D001</td>
                                                            <td>Kitchen</td>
                                                            <td>2020/09/19</td>
                                                            <td>2234433.4</td>
                                                            <td>12333.00</td>
                                                        </tr>
                                                        <tr>
                                                            <td>AC002</td>
                                                            <td>D001</td>
                                                            <td>banquet</td>
                                                            <td>2020/09/19</td>
                                                            <td>2234433.4</td>
                                                            <td>12333.00</td>
                                                        </tr>

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <div class="col-12 col-sm-12 col-md-12 col-lg-5 col-xl-5">
                            <form method="POST"  action="/saveUser" name="saveUser">

                                <div class="form-group">

                                    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                        <label >Account ID</label>
                                        <input type="text" class="form-control"
                                               required="required" name="userId"
                                               id="accountId" placeholder="Account ID"/></div>

                                    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                        <label for="departmentId">Department ID</label>
                                        <input type="text" class="form-control"
                                               required="required" name="name"
                                               id="departmentId" placeholder="Department ID"/></div>
                                </div>

                                <div class="form-group">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12"><br>
                                        <label for="departmentId">Department Name</label>
                                        <input type="text" class="form-control"
                                               required="required" name="position"
                                               id="departmentName" placeholder="Department Name"/></div>

                                    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12"> <br>
                                        <label for="departmentId">Date</label>
                                        <input type="date" class="form-control"
                                               required="required" name="address"
                                               id="address" placeholder="Date"/></div>
                                </div>

                                <div class="form-group">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12"> <br>
                                        <label for="departmentId">Date From</label>
                                        <input type="number" class="form-control"
                                               required="required" name="password"
                                               id="password" placeholder="Date from"/></div>


                                    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12"> <br>
                                        <label for="departmentId">Date To </label>
                                        <input type="number" class="form-control"
                                               required="required" name="expenses"
                                               id="expenses" placeholder="Date to"/></div>


                                </div>


                                <button type='submit' class="btn btn-dark" style="width: 50%; top: 20px; position: relative" value="Register">
                                    Submit
                                </button>
                                <button type='reset' class="btn btn-outline-success" style="top: 20px; position: relative" value="">Report</button>
                                <button type='reset' class="btn btn-outline-success" style="top: 20px; position: relative" value="">Reset</button>

                            </form>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--/////////////////////////////////////////////////--%>
</div>
<!-- /page content -->

<!-- footer content -->
<jsp:include page="footer.jsp"/>
<!-- /footer content -->

</div>
</div>

<!-- jQuery -->
<script src="../../vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="../../vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>

<!-- Datatables -->
<script src="../../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<%--Show Print Buttons--%>
<script src="../../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="../../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
<script src="../../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
<script src="../../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="../../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="../../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
<script src="../../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
<%--Responsive Table--%>
<script src="../../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="../../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
<%--<script src="../../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>--%>
<%--Print--%>
<script src="../../vendors/jszip/dist/jszip.min.js"></script>
<!-- Custom Theme Scripts -->
<script src="../../build/js/custom.min.js"></script>

</body>
</html>