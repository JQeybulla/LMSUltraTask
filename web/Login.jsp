<%@ page import="web.Constant" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12.05.2023
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login Form</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <link rel="stylesheet" href="./resources/css/style.css">
  <link rel="stylesheet" href="./resources/css/registration.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
  <script src="./resources/js/app.js"></script>
</head>
<body>
<!-- nav start  -->
<section id="navbar">
  <div class="container">
    <div class="row">
      <div class="col-4">
        <div class="left">
          Oxuyan
        </div>
      </div>
      <div class="col-4">
        <!-- <div class="middle">
            <ul>
                <li>Imtahanlar</li>
                <li>Bloq</li>
                <li>Diger</li>
            </ul>
        </div> -->
      </div>
      <div class="col-4">
        <div class="right">
          <ul>
            <!-- <li><a href="login.html"><button class="btn">Daxil ol</button></a></li> -->
            <li><a href="registration.html"><button class="btn btn-reg">Qeydiyyat</button></a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- nav end  -->


<!-- registration form start  -->
<section id="Registration">
  <div class="col-5">
    <div class="container">
      <div class="regForm">
        <h1><span style="color: #1084da;">Xoş Gəldiniz!</span></h1>
        <p>Daxil olmaq üçün əvvəlcə qeydiyyatdan keçmək lazımdır.</p>
        <div class="buttons">
          <button class="btn facebook"><i class="fab fa-facebook"></i> Facebook</button>
          <button class="btn google"><i class="fab fa-google"></i> Google</button>
          <button class="btn apple"><i class="fab fa-apple"></i> Apple</button>
        </div>
        <p>Ve ya</p>
        <form action="login?action=login" method="post" class="">
          <!-- <input type="text" name="" id="" placeholder="Ad soyad"> -->
          <input type="text" name="username" id="" placeholder="username">
          <input type="password" name="password" id="" placeholder="Shifre">
<%--          <input type="password" name="" id="" placeholder="Shifreni tesdiqle">--%>

          <div class="checkBox">
            <input class="form-check-input" type="checkbox" value="" id="agreementCheck">
            <label class="form-check-label" for="agreementCheck">
              İstifadəçi razılaşmasıni qəbul edirəm.
            </label>
          </div>
              <%
                String message = (String) request.getSession().getAttribute(Constant.MESSAGE);
                if (message != null) {
                  %><p style="color: red"><%= message %></p><%
                }
              %>
          <input type="submit" class="btn btn-primary" value="Daxil Ol" name="" id="">
        </form>
      </div>
    </div>

  </div>
</section>
<!-- registration form end  -->




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
</body>
</html>