<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<div class="easyui-panel" title="Nested Panel" data-options="width:'100%',minHeight:500,noheader:true,border:false"
     style="padding:10px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',split:false" style="width:250px;padding:5px">
            <ul id="contentCategoryTree" class="easyui-tree"
                data-options="url:'/content/categoryList',animate: true,method : 'GET'">
            </ul>
        </div>
        <div data-options="region:'center'" style="padding:5px">
            <table id="content_table"></table>
            <%-- <table class="easyui-datagrid" id="contentList" data-options="toolbar:contentListToolbar,singleSelect:false,collapsible:true,pagination:true,method:'get',pageSize:20,url:'/content/query/list',queryParams:{categoryId:0}">
                 <thead>
                 <tr>
                     <th data-options="field:'id',width:30">ID</th>
                     <th data-options="field:'title',width:120">内容标题</th>
                     <th data-options="field:'subTitle',width:100">内容子标题</th>
                     <th data-options="field:'titleDesc',width:120">内容描述</th>
                     <th data-options="field:'url',width:60,align:'center',formatter:TAOTAO.formatUrl">内容连接</th>
                     <th data-options="field:'pic',width:50,align:'center',formatter:TAOTAO.formatUrl">图片</th>
                     <th data-options="field:'pic2',width:50,align:'center',formatter:TAOTAO.formatUrl">图片2</th>
                     <th data-options="field:'created',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
                     <th data-options="field:'updated',width:130,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
                 </tr>
                 </thead>
             </table>--%>
        </div>
    </div>
</div>

<%--开始编写交互逻辑--%>
<script type="text/javascript">
    $(function () {
        $('#content_table').datagrid({
            url: 'content/list',
            queryParams: {
                categoryId: '89'
            },
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
                {field: 'categoryId', title: 'Code', width: 100, hidden: true},
                {field: '', title: '全选', align: 'center', checkbox: true},
                {field: 'title', title: '内容标题', width: 120, align: 'center'},
                {field: 'subTitle', title: '内容子标题', width: 100, align: 'center'},
                {field: 'titleDesc', title: '内容描述', width: 100, align: 'center'},
                {field: 'url', title: '内容连接', width: 100, align: 'center', formatter: TT.formatUrl},
                {field: 'pic', title: '图片', width: 100, align: 'center', formatter: TT.formatUrl},
                {field: 'pic2', title: '图片2', width: 100, align: 'center', formatter: TT.formatUrl},
                {field: 'created', title: '创建时间', width: 100, align: 'center', formatter: TT.formatDateTime},
                {field: 'updated', title: '更新时间', width: 100, align: 'center', formatter: TT.formatDateTime}
            ]],
            pagination: true,
            fitColumns: true,
            toolbar: [{
                iconCls: 'icon-add',
                text: '新增',
                handler: function () {
                    var $tree = $("#contentCategoryTree");
                    var selected = $tree.tree("getSelected");
                    var isSelected = false;
                    if (selected) {
                        if ($tree.tree("isLeaf", selected.target)) {
                            isSelected = true;
                        }
                    }
                    if (isSelected) {
                        TT.createWindow({
                            // url:"backend/menu_goods_add"
                            url: "backend/content_add"
                        })
                    } else {
                        $.messager.alert('提示', '请选择内容分类项');
                    }
                }
            }, '-', {
                iconCls: 'icon-edit',
                text: '编辑',
                handler: function () {
                    var ids = TT.getDataGridSelectedRow("#content_table");
                    if (ids.length == 0) {
                        $.messager.alert("提示", "请选择一项内容");
                        return;
                    }
                    if (ids.length > 1) {
                        $.messager.alert("提示", "请只选择一项内容");
                        return;
                    }
                    TT.createWindow({
                        url: 'backend/content_edit',
                        onLoad: function () {
                            var data = $("#content_table").datagrid("getSelections")[0];
                            $("#contentEditForm").form("load", data);

                            // 实现图片
                            if (data.pic) {
                                $("#contentEditForm [name=pic]").after("<a href='" + data.pic + "' target='_blank'><img src='" + data.pic + "' width='80' height='50'/></a>");
                            }
                            if (data.pic2) {
                                $("#contentEditForm [name=pic2]").after("<a href='" + data.pic2 + "' target='_blank'><img src='" + data.pic2 + "' width='80' height='50'/></a>");
                            }

                            contentAddEditor.html(data.content);
                        }
                    })
                }
            }, '-', {
                iconCls: 'icon-cancel',
                text: '删除',
                handler: function () {
                    var ids = TT.getDataGridSelectedRow("#content_table");
                    if (ids.length == 0) {
                        $.messager.alert("提示", "请选择一项内容");
                        return;
                    }
                    var categoryId= $("#content_table").datagrid("getSelections")[0].categoryId;
                    $.messager.confirm("删除", "您确定删除这些数据吗?", function (r) {
                        if (r) {
                            $.post("content/delete", {'ids': ids,'categoryId':categoryId}, function (res) {
                                if (res.success){
                                    $.messager.alert("提示","删除完成!");
                                    $("#content_table").datagrid("reload");
                                } else {
                                    $.messager.alert("提示","删除失败!");
                                }
                            })
                        }
                    })
                }
            }],
            rownumbers: true
        });

        //菜单点击事件
        var $tree = $("#contentCategoryTree");
        var $dataGrid = $("#content_table");
        $tree.tree({
            onClick: function (node) {
                if ($tree.tree("isLeaf", node.target)) {
                    $dataGrid.datagrid('reload', {
                        categoryId: node.id
                    })
                }
            }
        })
    });
</script>
</body>

</html>
