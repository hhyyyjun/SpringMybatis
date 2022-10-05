<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
	Stellar by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>board</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
<style>
	#imgSize{
	width : 100px;
	height : 100px;
	background-size : no-repeat contain;
	}
</style>
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">

			<!-- Introduction -->
			<section id="intro" class="main">
				<div class="spotlight">
					<div class="content">
						<header class="major">
							<h2>board</h2>
						</header>
						<div class="table-wrapper">
							<!-- 글 변경/삭제 -->
							<form action="updateBoard.do" method="POST" enctype="multipart/form-data">
								<table>
									<thead>
										<tr>
											<th>title</th>
											<th>content</th>
											<th>writer</th>
											<th>fileInput</th>
											<th>image</th>
											<th>regdate</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input type="text" name="title" value="${data.title}"></td>
											<td><input type="text" name="content" value="${data.content}"></td>
											<td><input type="text" value="${data.writer}" disabled></td>
											<td><input type="file" name="uploadFile" value="${data.fileName}"></td>
											<c:choose>
												<c:when test="${data.fileName!=null}" >
													<td><img id="imgSize" src="images/${data.fileName}"></td>
												</c:when>
												<c:otherwise>
													<td><input type="text" value="이미지X" disabled></td>
												</c:otherwise>
											</c:choose>
											<td>${data.regdate}</td>
										</tr>
										<tr>
											<td colspan="3" align="right"><input type="submit"
												class="button primary" value="UPDATE">&nbsp;&nbsp;&nbsp;
												<a href="deleteBoard.do" class="button primary">DELETE</a></td>
										</tr>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="3"></td>
											<td><a href="main.do">go to main</a></td>
										</tr>
									</tfoot>
								</table>
							</form>
							<!-- 글 변경/삭제 -->
						</div>
					</div>
				</div>
			</section>

		</div>

		<!-- Footer -->
		<footer id="footer">
			<section>
				<h2>Aliquam sed mauris</h2>
				<p>Sed lorem ipsum dolor sit amet et nullam consequat feugiat
					consequat magna adipiscing tempus etiam dolore veroeros. eget
					dapibus mauris. Cras aliquet, nisl ut viverra sollicitudin, ligula
					erat egestas velit, vitae tincidunt odio.</p>
				<ul class="actions">
					<li><a href="generic.html" class="button">Learn More</a></li>
				</ul>
			</section>
			<section>
				<h2>Etiam feugiat</h2>
				<dl class="alt">
					<dt>Address</dt>
					<dd>1234 Somewhere Road &bull; Nashville, TN 00000 &bull; USA</dd>
					<dt>Phone</dt>
					<dd>(000) 000-0000 x 0000</dd>
					<dt>Email</dt>
					<dd>
						<a href="#">information@untitled.tld</a>
					</dd>
				</dl>
				<ul class="icons">
					<li><a href="#" class="icon brands fa-twitter alt"><span
							class="label">Twitter</span></a></li>
					<li><a href="#" class="icon brands fa-facebook-f alt"><span
							class="label">Facebook</span></a></li>
					<li><a href="#" class="icon brands fa-instagram alt"><span
							class="label">Instagram</span></a></li>
					<li><a href="#" class="icon brands fa-github alt"><span
							class="label">GitHub</span></a></li>
					<li><a href="#" class="icon brands fa-dribbble alt"><span
							class="label">Dribbble</span></a></li>
				</ul>
			</section>
			<p class="copyright">
				&copy; Untitled. Design: <a href="https://html5up.net">HTML5 UP</a>.
			</p>
		</footer>

	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>