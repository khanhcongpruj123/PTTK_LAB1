<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order</title>
</head>
<body>
<div align="center">
  <h1>Order Form</h1>
  <form action="<%= request.getContextPath() %>/order" method="post">
   <table style="with: 80%">
    <tr>
     <td>City</td>
     <td><input type="text" name="city" /></td>
    </tr>
    <tr>
     <td>District</td>
     <td><input type="text" name="district" /></td>
    </tr>
    <tr>
     <td>Commune</td>
     <td><input type="text" name="commune" /></td>
    </tr>
    <tr>
     <td>Street</td>
     <td><input type="text" name="street" /></td>
    </tr>
    <tr>
     <td>Number</td>
     <td><input type="text" name="number" /></td>
    </tr>
   </table>
   <input type="submit" value="Submit" />
  </form>
 </div>
</body>
</html>