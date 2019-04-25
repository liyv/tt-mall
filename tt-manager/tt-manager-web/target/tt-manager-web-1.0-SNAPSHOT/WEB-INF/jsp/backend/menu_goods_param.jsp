<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>

</head>
<body class="easyui-layout">
<div style="padding:10px 10px">
    <table cellpadding="5" style="margin-left: 30px" id="itemParamAddTable" class="itemParam">
        <tr>
            <td>
                商品类目:
            </td>
            <td>
                <a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
                <input type="hidden" name="cid"/>
                <span></span>
            </td>
        </tr>
        <tr class="hide addGroupTr">
            <td>规格参数:</td>
            <td>
                <a href="javascript:void(0)" class="easyui-linkbutton addGroup">添加分组</a>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td id="paramTd">
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <a href="javascript:void(0)" class="easyui-linkbutton submit">提交</a>
                <a href="javascript:void(0)" class="easyui-linkbutton close">关闭</a>
            </td>
        </tr>
    </table>
    <div class="itemParamAddTemplate" style="display: none">
        <li class="param">
            <ul>
                <li>
                    <input class="easyui-textbox" type="text" style="width:150px;" name="group"/>&nbsp;<a
                        href="javascript:void(0)"
                        class="easyui-linkbutton addParam"
                        title="添加参数"
                        data-options="plain:true,iconCls:'icon-add'"></a>
                </li>
                <li style="margin-top: 10px">
                    <span>|---------</span><input type="text" class="easyui-textbox" style="width:150px;" name="param"/>&nbsp;<a
                        href="javascript:void(0)" class="easyui-linkbutton delParam" title="删除"
                        data-options="plain:true,iconCls:'icon-cancel'"></a>
                </li>
            </ul>
        </li>
    </div>
</div>
<%--开始编写交互逻辑--%>
<script type="text/javascript">
    $(function () {
        TT.initItemCategory({
            fun: function (node) {
                //作用是什么
                $(".addGroupTr").hide().find(".param").remove();
                $.getJSON("/item/param/query/itemcatid/" + node.id, function (data) {
                    if (data.success && data.data) {
                        $.messager.alert("提示", "该类目已经添加,请选择其他类目.", undefined, function () {
                            $("#itemParamAddTable .selectItemCat").click();
                        })
                    } else {
                        $(".addGroupTr").show();
                    }
                });
            }
        });
        var paramTdDom = $("#paramTd");//存放分组框的tr
        $(".addGroup").unbind('click').click(function () {
            //模板 li
            var template = $(".itemParamAddTemplate>li>ul").clone();
            // paramTdDom.append(template);
            template.find("input").attr('type', 'text');
            template.find(".addParam").click(function () {
                var li = template.children().eq(1).clone();
                li.find(".delParam").click(function () {
                    $(this).parent().remove();
                });
                var ul = $(this).parentsUntil("ul").parent();
                ul.append(li);
            });
            paramTdDom.append(template);
        });

        //关闭 tab
        $("#itemParamAddTable .close").click(function () {
            closeTab();
        });

        function closeTab() {
            var tabs = $('#content_tab');
            var tab = tabs.tabs('getSelected');
            var index = tabs.tabs('getTabIndex', tab);
            tabs.tabs('close', index);
        }

        $("#itemParamAddTable .submit").click(function () {
            var params = [];
            var groups = $("#itemParamAddTable [name=group]");
            groups.each(function (i, e) {
                //group 下的所有input
                var paramInputs = $(e).parentsUntil("ul").parent().find("[name=param]");
                var paramVals = [];
                paramInputs.each(function (i2, e2) {
                    var _val = $(e2).siblings("input").val();//分组项的数据
                    if ($.trim(_val).length > 0) {
                        paramVals.push(_val);
                    }
                });
                var _val = $(e).siblings("input").val();//组的数据
                if ($.trim(_val).length > 0 && paramVals.length > 0) {
                    params.push({
                        "group": _val,
                        "params": paramVals
                    });
                }
            });
            var url = "/item/param/save/" + $("#itemParamAddTable [name=cid]").val();
            $.post(url, {"paramData": JSON.stringify(params)}, function (res) {
                console.log(res);
                if (res.success) {
                    $.messager.alert("提示", "添加成功!", undefined, function () {
                        closeTab();
                    });
                }
            });
        });
    });
</script>
</body>
</html>