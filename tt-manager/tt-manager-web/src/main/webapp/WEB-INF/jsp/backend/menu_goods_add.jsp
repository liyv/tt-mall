<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>

</head>
<body class="easyui-layout">

<div style="padding: 10px 10px">
    <form id="itemAddForm" class="itemForm" method="post">
        <table cellpadding=5">
            <tr>
                <td>
                    <label>商品类目:</label>
                </td>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
                    <input type="hidden" name="cid"/>
                    <span></span>
                </td>
            </tr>
            <tr>
                <td><label>商品标题:</label></td>
                <td>
                    <input class="easyui-textbox" type="text" name="title" data-options="required:true"
                           style="width:280px"/>
                </td>
            </tr>
            <tr>
                <td>商品卖点:</td>
                <td><input class="easyui-textbox" name="sellPoint" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"/></td>
            </tr>
            <tr>
                <td>商品价格:</td>
                <td><input class="easyui-numberbox" type="text" name="priceView" data-options="min:1,max:99999999,precision:2,required:true" />
                    <input type="hidden" name="price"/>
                </td>
            </tr>
            <tr>
                <td>库存数量:</td>
                <td><input class="easyui-numberbox" type="text" name="num" data-options="min:1,max:99999999,precision:0,required:true" /></td>
            </tr>
            <tr>
                <td>条形码:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="barcode" data-options="validType:'length[1,30]'" />
                </td>
            </tr>
            <tr>
                <td>商品图片:</td>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
                    <input type="hidden" name="image"/>
                </td>
            </tr>
            <tr>
                <td>商品描述:</td>
                <td>
                    <textarea style="width:800px;height:300px;visibility:hidden;" id="desc" name="desc"></textarea>
                </td>
            </tr>
            <tr >
                <td></td>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton " onclick="submitForm()">提交</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton ">重置</a>
                </td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">
    var itemAddEditor;
    $(function () {
        //商品种类
        TT.initItemCategory();
        //富文本编辑器
        itemAddEditor =TT.createEditor();
        TT.initPictureUpload();
    });

    //提交表单
    function submitForm() {
        //有效性验证
        if (!$('#itemAddForm').form('validate')) {
            $.messager.alert('提示', '表单还未填写完成!');
            return;
        }
        //取商品价格,单位为"分",相当于扩大100倍
        var originPrice = $("#itemAddForm [name=priceView]").val();
        $("#itemAddForm [name=price]").val(originPrice * 100);
        //同步文本框中的商品描述
        itemAddEditor.sync();
        //取商品的规格 TODO
        // var paramJson=[];
        // $("#itemAddForm .params li")

        //ajax 的post 方式提交表单
        $.post("/item/saveItem",$('#itemAddForm').serialize(),function (data) {
            console.log(data);
        });
    }
</script>
</body>

</html>
