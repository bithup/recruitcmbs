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
<form method="" action="" >
    <div >
        <h3>使用申请</h3>
        <ul>
            <li>
                <span>姓名<i style="color:#ff0000;">*</i></span>
                <input type="text" name="" value="" class="username">
            </li>
            <li>
                <span>手机<i style="color:#ff0000;">*</i></span>
                <input type="text" name="" value="" maxlength="11" class="userphone">
            </li>
            <li>
                <span>邮箱</span>
                <input type="text" name="" value="" class="useremail">
            </li>
            <li>
                <span>留言</span>
                <textarea name="" id="" placeholder="请输入留言，例如需要软件的种类" class="usercomment"></textarea>
            </li>
        </ul>
        <input type="submit" name="" value="确认提交" class="sub_btn">
        <i class="shutdown">x</i>
    </div>
</form>
<script>
    $(".form").submit(function(){

        var $username=$(".username").val(),
                $userphone=$(".userphone").val(),
                $useremail=$(".useremail").val(),
                $usercomment=$(".usercomment").val();

        var PhoneReg = /^1[0-9]{10}$/;
        //var PhoneReg = /^0{0,1}(13[0-9]|15[0-9]|153|156|18[7-9])[0-9]{8}$/ ,-->//手机正则
        EmailReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; //邮件正则
        if($username==""){
            alert("请输入姓名！");
            return false;
        }
        if($userphone==""){
            alert("请输入手机号！");
            return false;
        }
        if(!PhoneReg.test($userphone)){
            alert("请输入正确的手机号！");
            return false;
        }
        if(!$useremail==""){
            if(!EmailReg.test($useremail)){
                alert("请输入正确的邮箱！");
                return false;
            }
        }

        var postdata='relationman='+$username+'&relationtel='+$userphone+'&email='+$useremail+'&note='+$usercomment;
        $.post("http://192.168.3.23:8080/basic/jxcReturn.htm",postdata,function(data){
            alert(0);
        });
    });
</script>
</body>
</html>