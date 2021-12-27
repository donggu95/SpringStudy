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
	$("input[type=button]").click(function(){
		$("#form1").attr("action","/notice/regist.jsp"); // action 지정
		$("#form1").attr("method","post"); // 전송메소드는 post
		$("form1").attr({
			
		});
		$("#form1").submit(); // 전송
	});
});

</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form id="form1">
  
    <input type="text" id="fname" name="writer" placeholder="Your name..">
    <input type="text" id="lname" name="title" placeholder="Your last name..">
    <textarea id="subject" name="content" placeholder="Write something.." style="height:200px"></textarea>
    <input type="button" value="Submit">
    
  </form>
</div>

</body>
</html>