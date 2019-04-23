<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body class="easyui-layout">
<table id="query_table"></table>
<script type="text/javascript">
    $(function () {
        $('#query_table').datagrid({
            url:'item/list',
            loadFilter: function(res){
                if (res.success){
                    return res.data;
                } else {
                    return null;
                }
            },
            columns:[[
                {field:'id',title:'Code',width:100,hidden:true},
                {field:'',title:'全选',align:'center',checkbox:true},
                {field:'title',title:'商品名称',width:100,align:'center'},
                {field:'sellPoint',title:'销售点',width:100,align:'center'},
                {field:'price',title:'价格',width:100,align:'center'},
                {field:'num',title:'数量',width:100,align:'center'},
                {field:'barcode',title:'二维码',width:100,align:'center'},
                {field:'image',title:'图片',width:100,align:'center'},
                {field:'cid',title:'商品种类',width:100,align:'center'},
                {field:'status',title:'状态',width:100,align:'center'},
                {field:'created',title:'创建时间',width:100,align:'center'},
                {field:'updated',title:'更新时间',width:100,align:'center'}
            ]],
            pagination:true,
            fitColumns:true,
            toolbar: [{
                iconCls: 'icon-add',
                text:'新增',
                handler: function(){alert('edit')}
            },'-',{
                iconCls: 'icon-edit',
                text:'编辑',
                handler: function(){alert('help')}
            },'-',{
                iconCls: 'icon-cancel',
                text:'删除',
                handler: function(){alert('help')}
            },'-',{
                iconCls: 'icon-redo',
                text:'下架',
                handler: function(){alert('help')}
            },'-',{
                iconCls: 'icon-undo',
                text:'上架',
                handler: function(){alert('help')}
            }],
            rownumbers:true
        });
    });
</script>
</body>

</html>
