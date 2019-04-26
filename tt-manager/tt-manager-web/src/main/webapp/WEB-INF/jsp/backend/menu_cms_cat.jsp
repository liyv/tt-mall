<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body class="easyui-layout">
<div class="content_panel">
    <ul id="content_tree" class="easyui-tree"></ul>
</div>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onclick="append()" data-options="iconCls:'icon-add'">新增</div>
    <div onclick="edit()" data-options="iconCls:'icon-edit'">修改</div>
    <div onclick="remove()" data-options="iconCls:'icon-remove'">删除</div>
</div>
<%--开始编写交互逻辑--%>
<script type="text/javascript">
    var selectedNode;
    $(function () {
        $(".content_panel").css({"padding-left": "20px", "padding-top": "15px"});
        //初始化树形结构
        $("#content_tree").tree({
            url: '/content/categoryList',
            method: 'get',
            animate: true,
            /*  loadFilter: function (res) {
                  if (res.success) {
                      return res.data;
                  } else {
                      return null;
                  }
              },*/
            onContextMenu: function (e, node) {
                e.preventDefault();
                // select the node
                $('#content_tree').tree('select', node.target);
                // console.log(node.target);
                selectedNode = node.target;
                // display context menu
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });
            },
            onAfterEdit: function (node) {
                var $_tree = $(this);
                if (node.id == 0) {
                    //新增节点
                    $.post("/content/category/create", {parentId: node.parentId, name: node.text}, function (res) {
                        if (res.success) {
                            $_tree.tree('update', {
                                target: node.target,
                                id: res.data.id
                            });
                        } else {
                            $.messager.alert("提示", "创建" + node.text + "分类失败!");
                        }

                    });
                } else {
                    $.post("/content/category/update", {id: node.id, name: node.text}, function (res) {
                        if (res.success) {
                            $.messager.alert("提示", "更新成功!");
                        } else {
                            $.messager.alert("错误", "更新失败!");
                        }
                    })
                }
            }
        });
    });

    function append() {
        var $tree = $('#content_tree');
        var selected = $tree.tree('getSelected');	// get getSelected nodes
        $tree.tree('append', {
            parent: (selected ? selected.target : null),
            data: [{
                id: 0,
                text: '新建分类',
                parentId: selected.id
            }]
        });
        var _node = $tree.tree('find', 0);
        $tree.tree("select", _node.target).tree('beginEdit', _node.target);
    }

    function edit() {
        var $tree = $('#content_tree');
        var selected = $tree.tree('getSelected');	// get getSelected nodes
        $tree.tree('beginEdit', selected.target);
    }

    function remove() {
        var $tree = $('#content_tree');
        var selected = $tree.tree('getSelected');
        $.messager.confirm("删除确认", "确定删除名为" + selected.text + " 的分类吗?", function (r) {
            if (r) {
                $.post("/content/category/" + selected.id + "/delete", undefined, function (res) {
                    if (res.success) {
                        $.messager.alert("提示", "删除成功!");
                        $tree.tree('remove', selected.target);
                    } else {
                        $.messager.alert("提示", "删除失败!");
                    }
                })
            }
        })
    }
</script>
</body>
</html>
