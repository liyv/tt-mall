<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/25
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body class="easyui-layout">
<table id="param_list"></table>
<script type="text/javascript">
    $(function () {
        $("#param_list").datagrid({
            url: 'item/param/list',
            loadFilter: function (res) {
                if (res.success) {
                    return res.data;
                } else {
                    return null;
                }
            },
            method: 'get',
            columns: [[
                {field: 'id', title: 'Code', width: 100, hidden: true},
                {field: '', title: '全选', align: 'center', checkbox: true},
                {field: 'categoryName', title: '商品类目', width: 100, align: 'center'},
                {field: 'paramData', title: '规格数据', width: 100, align: 'center'},
                {field: 'createDate', title: '创建日期', width: 100, align: 'center'},
                {field: 'updateDate', title: '更新日期', width: 100, align: 'center'}
            ]],
            pagination: true,
            fitColumns: true,
            toolbar: [{
                iconCls: 'icon-add',
                text: '新增',
                handler: function () {
                    alert('edit')
                }
            }, '-', {
                iconCls: 'icon-edit',
                text: '编辑',
                handler: function () {
                    alert('help')
                }
            }, '-', {
                iconCls: 'icon-cancel',
                text: '删除',
                handler: function () {
                    alert('help')
                }
            }],
            rownumbers: true
        })
    });
</script>

</body>
</html>
