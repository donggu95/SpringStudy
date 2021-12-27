<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	$("input[type='button']").click(function(){
		regist();
	});
});

function regist(){
	$("form").attr({
		action :"/member/join",
		method :"post"
	});
	$("form").submit();
}
</script>
</head>
<body>

<h3>회원가입</h3>

<div class="container">
	<form>
		<input type="text" name="id" placeholder="Your id">
		<input type="text" name="pass" placeholder="Your password">
		<input type="text" name="name" placeholder="Your name..">
		<input type="text" name="email" placeholder="Your email">
		<input type="button" value="Submit">
	</form>
</div>

</body>
</html>