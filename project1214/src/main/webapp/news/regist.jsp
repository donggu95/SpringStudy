<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>글 등록</title>
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
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
   $(function() { // (1)
      // 제이쿼리의 문법 구조: 무엇을(선택자를).어떻게(함수)~~
      $("#bt_regist").click(function() {
         // jquery로 폼 전송
         $("form").attr({
            "action":"/news/write.jsp",
            "method":"post"
         });
         $("form").submit();
      });
      $("#bt_list").click(function() {
         location.href="/news/list.jsp";
      });
   });
</script>
</head>
<body>

   <h3>글 등록</h3>

   <div class="container">
      <form action="/action_page.php">
         <input type="text" name="title" placeholder="Your title.."> 
         <input type="text" name="writer" placeholder="Your name..">
         <textarea name="content" placeholder="Write something.." style="height: 200px"></textarea>
         <input type="button" value="등록" id="bt_regist">
         <input type="button" value="목록" id="bt_list">
      </form>
   </div>

</body>
</html>