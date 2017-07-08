<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" href="https://unpkg.com/bootstrap@3.3.7/dist/css/bootstrap.min.css">
</head>
<body>
<div class="form-group">
	<label for="txt-title">제목</label>
	<input type="text" id="txt-title" class="form-control">
</div>
<div class="form-group">
	<label for="txt-contents">내용</label>
	<textarea type="text" id="txt-contents" class="form-control" rows="10"></textarea>
</div>
<div class="board-btns">
	<button id="btn-save" class="btn btn-success">저장</button>
	<button id="btn-cancel" class="btn btn-default">취소</button>
</div>
<script type="text/javascript" src="https://unpkg.com/jquery@3.2.1/dist/jquery.min.js"></script>
<script type="text/javascript" src="https://unpkg.com/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/write.js"></script>
</body>
</html>