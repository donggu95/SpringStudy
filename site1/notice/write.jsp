<%@ page contentType="text/html;charset=utf-8"%>
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
<script src="https://cdn.ckeditor.com/4.17.1/standard/ckeditor.js"></script>
<script>
//문서내의 content라는 이름을 가진 요소에 편집기를 적용!!
function init(){
	CKEDITOR.replace("content" );
}

//입력양식을 서버로 전송하자!!
function regist(){
	//자바스크립트는 db연동 능력이 있어서도 안된다.
	//따라서, db연동에 대해 직접하지 말고, 서버 요청해야 한다.
	//사용자가 입력한 데이터들을 서버로 전송하기!!

	//문서내의 폼을 전송한다!!
	//자바스크립트는 원칙상, 문서에 불과한 HTML 태그와는 통신이 불가능하다..
	//이를 해결하려면, HTML의 모든 태그마다 1:1 대응하는 객체가 필요한데, 이러한 객체를 가리켜 
	//문서의 모든 태그를 객체화 시켰다고 하여 Document Object Model 의 약자인 DOM이라고 한다..
	
	//메모리에 모여진, 돔객체들을 접근하기 위해서는 document 객체의 getElementById() 메서드를 사용할 수 있다...
	var form1=document.getElementById("form1");
	form1.method="post";//서버가 이해할 수 있도록...
	form1.action="/notice/regist.jsp"; //서버에 요청할 주소
	form1.submit(); //전송
}
</script>

</head>
<body onLoad="init();">

<h3>Contact Form</h3>

<div class="container">
  <form id="form1">
    <label for="fname">First Name</label>
    <input type="text" name="title" placeholder="제목..">

    <label for="lname">Last Name</label>
    <input type="text"  name="writer" placeholder="작성자..">

    <label for="subject">Subject</label>
    <textarea  name="content" placeholder="Write something.."
	style="height:200px"></textarea>
	<!--  submit은 버튼의 기능 + 자동전송이 이미 포함-->
    <input type="button" value="글등록" onClick="regist()">
    <input type="button" value="목록보기" onClick="location.href='/notice/list.jsp';">
  </form>
</div>

</body>
</html>
