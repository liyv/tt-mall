<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/27
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="padding:10px 10px 10px 10px">
    <form id="contentEditForm" class="itemForm" method="post">
        <input type="hidden" name="categoryId"/>
        <input type="hidden" name="id"/>
        <table cellpadding="5">
            <tr>
                <td>内容标题:</td>
                <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width: 280px;"/></td>
            </tr>
            <tr>
                <td>内容子标题:</td>
                <td><input class="easyui-textbox" type="text" name="subTitle" style="width: 280px;"/></td>
            </tr>
            <tr>
                <td>内容描述:</td>
                <td><input class="easyui-textbox" name="titleDesc" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"/>
                </td>
            </tr>
            <tr>
                <td>URL:</td>
                <td><input class="easyui-textbox" type="text" name="url" style="width: 280px;"/></td>
            </tr>
            <tr>
                <td>图片:</td>
                <td>
                    <input type="hidden" name="pic" />
                    <a href="javascript:void(0)" class="easyui-linkbutton onePicUpload">图片上传</a>
                </td>
            </tr>
            <tr>
                <td>图片2:</td>
                <td>
                    <input type="hidden" name="pic2" />
                    <a href="javascript:void(0)" class="easyui-linkbutton onePicUpload">图片上传</a>
                </td>
            </tr>
            <tr>
                <td>内容:</td>
                <td>
                    <textarea style="width:800px;height:300px;visibility:hidden;" name="content"></textarea>
                </td>
            </tr>
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="contentAddPage.submitForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="contentAddPage.clearForm()">重置</a>
    </div>
</div>
<script type="text/javascript">
    var contentAddEditor ;
    $(function(){
        contentAddEditor = TT.createEditor("#contentEditForm [name=content]");
        TT.initOnePicUpload();
        // $("#contentEditForm [name=categoryId]").val($("#contentCategoryTree").tree("getSelected").id);
    });

    var contentAddPage  = {
        submitForm : function (){
            if(!$('#contentEditForm').form('validate')){
                $.messager.alert('提示','表单还未填写完成!');
                return ;
            }
            contentAddEditor.sync();

            $.post("/content/update",$("#contentEditForm").serialize(), function(res){
                if(res.success){
                    TT.closeCurrentWindow();
                    $.messager.alert('提示','新增内容成功!');
                    $("#content_table").datagrid("reload");
                }
            });
        },
        clearForm : function(){
            $('#contentEditForm').form('reset');
            contentAddEditor.html('');
        }
    };
</script>
</body>
</html>