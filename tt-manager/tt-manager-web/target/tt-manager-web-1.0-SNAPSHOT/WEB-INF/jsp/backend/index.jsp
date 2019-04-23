<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>淘淘商城后台管理</title>
    <%@include file="/WEB-INF/jsp/common/head.jsp" %>
</head>
<body class="easyui-layout">

<div data-options="region:'north',title:'淘淘商城后台管理系统'" style="height: 100px;">
    <%--<img src="/resources/image/google.png">--%>
    <div style="width:400px;height:70px;padding-left:20px;font-weight:bold;float: left;line-height: 70px;font-size: 20px">
        淘淘商城后台管理系统
    </div>
    <div style="width:200px;height:70px;float:right;line-height: 70px;padding-right: 20px;font-size: 15px">
        你好,<%--${sessionScope.get()}--%>欢迎登录
    </div>
</div>
<div data-options="region:'south',title:'底部声明'" style="height: 100px;">
    <div style="height: 70px;line-height: 70px;text-align: center;color: grey;">
        Copyright &copy; 2019 淘淘商城
    </div>
</div>
<div data-options="region:'west',title:'菜单'" style="width: 200px;">
    <ul id="left_menu_tree" class="easyui-tree">
    </ul>
</div>
<div data-options="region:'center',title:'内容'" style="padding: 5px;">
    <div id="content_tab" class="easyui-tabs" data-options="fit:true">
        <div title="Tab1" style="padding:20px">
            tab1
        </div>
    </div>
</div>


</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<!-- easyui js 以"/"开头的成为全路径-->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/lib/easyui/jquery.easyui.min.js"></script>
<%--引入 commons.js--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/script/common.js"></script>


<script type="text/javascript" charset="UTF-8"
        src="${pageContext.request.contextPath}/resources/lib/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="UTF-8"
        src="${pageContext.request.contextPath}/resources/lib/kindeditor/lang/zh-CN.js"></script>

<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<%--开始编写交互逻辑--%>
<script type="text/javascript">
    $(function () {
        var menuData = null;
        $('#left_menu_tree').tree({
            url: 'backend/menuList',
            method:'get',
            loadFilter: function (res) {
                if (res.success) {
                    menuData = res.data;
                    return res.data;
                } else {
                    return null;
                }
            },
            onClick: function (node) {
                //tab .jsp 中的内部必须写在<body>中
                if (!node.url) {
                    return;
                }
                //tab 是否已显示
                var contentTab = $('#content_tab');
                var tabItem = contentTab.tabs('getTab', node.text);
                if (!tabItem) {
                    contentTab.tabs('add', {
                        title: node.text,
                        selected: true,
                        href: node.url,
                        closable: true
                    });
                } else {
                    contentTab.tabs('select', node.text);
                }
            }
        });
    });
</script>
</html>
