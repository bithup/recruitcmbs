<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>标题</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="stylesheet" href="/resources/ui/site/css/reset.css">
	<link rel="stylesheet" href="/resources/ui/site/css/demo.css">
	<script src="/resources/ui/site/js/jquery-1.8.3.min.js"></script>
	<script src="/resources/ui/site/js/ort.js"></script>

</head>
<body>
	<div class="con_top">
		<div class="numContent">
			<span id="numContent">教育放上去推广人:${account}为你服务</span>
		</div>
		<div class="num_wrapper">
			<h3 style="margin-top: 30px">邀请口令：</h3>
			<div class="num"><span id="num">${label}${promotionId}</span></div>
			<div class="remind_icon"><img src="/resources/ui/site/images/guide.png" alt=""></div>
		</div>
		<p class="remind">请长按复制邀请口令，再点击下一步</p>
	</div>

	<div class="btn">
		<div class="next_btn">
			<button class="com_btn" id="com_btn">企业版</button>
			<button class="per_btn" id="per_btn">求职版</button>
		</div>
		<div class="show_btn" id="show_btn">
			<button class="open_app" id="open_app">已经安装，立刻绑定</button>
			<button class="download_app"
					id="download_app">立即安装</button>
		</div>
	</div>
	<div class="body_mask">
		<h1 class="num1"><i>点击右上角的</i><img src="/resources/ui/site/images/sheng.png" alt=""></h1>
		<h1 class="num2">选择在浏览器中打开此页面</h1>
		<div class="guide"><img src="/resources/ui/site/images/arrow.png" alt=""></div>
	</div>
	<script>
		$(function(){

			var oH=$(document).height()+"px";
			$(".body_mask").css("height",oH);

			//判断终端
			var u = navigator.userAgent;
			var ua = window.navigator.userAgent.toLowerCase();
			var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
			var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

			if(ua.match(/MicroMessenger/i) == 'micromessenger'||ua.match(/WeiBo/i) == "weibo"||ua.match(/QQ/i) == "qq"){//微信
				$(".body_mask").show();
			}else if (isAndroid) {//安卓
				$(".com_btn,.per_btn").css("background","#ec5c3d");
				$("#com_btn").click(function(){//企业版
					if(confirm("您是否已经复制口令？")){
						$(".next_btn").hide();
						$("#show_btn").show();
						$(".remind_icon").hide();
						$(".remind").html("如果您点击按钮没有反应，请直接打开App");

						//打开app
						$("#open_app").click(function(){
							window.location="链接放这里";
						});

						//下载app
						$("#download_app").click(function(){
							window.location="链接放这里";
						});
					}else{
						return;
					}
				});

				$("#per_btn").click(function(){//求职版
					if(confirm("您是否已经复制口令？")){
						$(".next_btn").hide();
						$("#show_btn").show();
						$(".remind_icon").hide();
						$(".remind").html("如果您点击按钮没有反应，请直接打开App");

						//打开app
						$("#open_app").click(function(){
							window.location="链接放这里";
						});

						//下载app
						$("#download_app").click(function(){
							window.location="链接放这里";
						});
					}else{
						return;
					}
				});
			} else {//ios及其他
				$("#num").on("copy",function(){
					$(".com_btn,.per_btn").css("background","#ec5c3d");
					$("#com_btn").click(function(){//企业版
						$(".next_btn").hide();
						$("#show_btn").show();
						$(".remind_icon").hide();
						$(".remind").html("如果您点击按钮没有反应，请直接打开App");
						//打开app
						$("#open_app").click(function(){
							window.location="链接放这里";
						});

						//下载app
						$("#download_app").click(function(){
							window.location="链接放这里";
						});

					});

					$("#per_btn").click(function(){//求职版
						$(".next_btn").hide();
						$("#show_btn").show();
						$(".remind_icon").hide();
						$(".remind").html("如果您点击按钮没有反应，请直接打开App");
						//打开app
						$("#open_app").click(function(){
							window.location="链接放这里";
						});

						//下载app
						$("#download_app").click(function(){
							window.location="链接放这里";
						});

					});

				});
			}
		});
	</script>
</body>
</html>