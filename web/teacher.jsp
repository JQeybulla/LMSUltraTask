<%@ page import="domain.UsersInfo" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25.05.2023
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Teacher</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <link rel="stylesheet" href="./resources/css/teacher.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/themes/redmond/jquery-ui.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/css/ui.jqgrid.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.0/bootstrap-table.min.css" rel="stylesheet" />
    <link href="https://rawgit.com/vitalets/x-editable/master/dist/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/jquery.jqgrid.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.0/bootstrap-table.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.9.1/extensions/editable/bootstrap-table-editable.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.9.1/extensions/export/bootstrap-table-export.js"></script>
    <script src="https://rawgit.com/hhurz/tableExport.jquery.plugin/master/tableExport.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.9.1/extensions/filter-control/bootstrap-table-filter-control.js"></script>
</head>
<body>
<!-- nav start  -->
<section id="Nav">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12">
                <div class="username">
                    <p>
                        <%
                            UsersInfo user = (UsersInfo) request.getSession().getAttribute("user");
                            out.print(user.getUsername());
                        %>
                    </p>
                    <a href="/UltraJava_war/logout"><button class="btn btn-primary">Çıxış</button></a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- nav end  -->

<section id="Body">
    <div class="row">
        <div class="col-2 p-0">
            <div class="leftNav">
                <ul>
                    <li id="groups">Qruplar</li>
                    <li id="examsTeacher">Imtahanlar</li>
                    <li>Fennler</li>
                    <li>Ev tapsiriqlari</li>
                    <!-- <li>Shikayet formu</li> -->
                </ul>
            </div>
        </div>
        <div class="col-8 p-0">
            <div class="operations">
                <div class="icons">
                    <div class="icon" id="addItem">
                        <i class="fas fa-plus-circle"></i>
                    </div>
                    <div class="icon" id="edit">
                        <i class="fas fa-edit"></i>
                    </div>
                    <div class="icon" id="removeItem">
                        <i class="fas fa-times"></i>
                    </div>
                </div>
            </div>
            <div class="mainContent" id="mainContent">
                <div class="overlay">
                    <div class="addExamForm" id="addExamForm">
                        <form action="">
                            <label for="questionsBody">Sualin shertini elave edin:</label> <br>
                            <textarea type="text" name="" id="questionsBody" class="question"></textarea>

                            <label for="A">A varianti</label>
                            <input type="text" name="" id="A" class="answer">

                            <label for="B">B varianti</label>
                            <input type="text" name="" id="B" class="answer">

                            <label for="C">C varianti</label>
                            <input type="text" name="" id="C" class="answer">

                            <label for="D">D varianti</label>
                            <input type="text" name="" id="D" class="answer">
                        </form>
                        <div class="buttons">
                            <button id="next" class="btn">Novbeti</button>
                            <button id="finishCreateExamBtn" class="btn">Yarat</button>
                        </div>
                    </div>
                </div>
                <div class="containerH">
                    <div id="toolbar">
                        <select class="form-control">
                            <option value="">Export Basic</option>
                            <option value="all">Export All</option>
                            <option value="selected">Export Selected</option>
                        </select>
                    </div>

                    <table id="table"
                           class="table table-striped table-hover">
                        <thead id="myHead">
                        <tr>
                            <th data-field="state" data-checkbox="true"></th>
                            <th data-field="prenom" data-filter-control="input" data-sortable="false">ID</th>
                            <th data-field="date" data-filter-control="input" data-sortable="true">Name</th>
                            <th data-field="examen" data-filter-control="input" data-sortable="true">Faculty</th>
                            <th data-field="note" data-sortable="true" data-filter-control="input">Telebe sayi</th>
                        </tr>
                        </thead>
                        <tbody id="myBody">
                        </tbody>
                    </table>

                    <!-- edit From -->
                    <div id="editModal" class="modal">
                        <div class="modal-content">
                            <span class="close">&times;</span>
                            <form>
                                <label for="nameInput">Name of group</label>
                                <input id="nameInput" type="text" placeholder="Name">

                                <label for="facultySelect">Faculty</label>
                                <select id="facultySelect">
                                    <option value="faculty1">Faculty 1</option>
                                    <option value="faculty2">Faculty 2</option>
                                    <option value="faculty3">Faculty 3</option>
                                </select>
                                <button class="btn btn-primary" id="saveChanges">Save</button>
                            </form>
                        </div>
                    </div>

                    <div class="addExam" id="addExam">
                        <button class="btn btn-primary" id="addExamButton">Imtahan elave et</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-2 p-0">
            <div class="rightNav">

            </div>
        </div>
    </div>

</section>
<script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" integrity="sha384-rOA1PnstxnOBLzCLMcre8ybwbTmemjzdNlILg8O7z1lUkLXozs4DHonlDtnE7fpc" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<!-- <script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script> -->
<script src="./resources/js/teacher.js"></script>
</body>
</html>