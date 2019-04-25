var TT = {
    //初始化选择类目组件
    initItemCategory: function (data) {
        //添加点击事件
        var selectItemCatObj = $(".selectItemCat");
        if (data && data.cid) {
            selectItemCatObj.after("<span style='margin-left: 10px'>" + data.cid + "</span>");
        }else {
            selectItemCatObj.after("<span style='margin-left: 10px'></span>");
        }
        selectItemCatObj.unbind('click').click(function () {
            var _ele = $(this);
            //使用jQuery创建window ,并弹出
            $("<div>").css({padding: "5px"}).html("<ul>")
                .window({
                    width: '500',
                    height: "450",
                    modal: true,
                    closed: true,
                    minimizable: false,
                    maximizable: false,
                    iconCls: 'icon-save',
                    title: '选择类目',
                    onOpen: function () {
                        var _win = this;
                        $("ul", _win).tree({
                            url: '/item/listCategoryByPid',
                            method: 'get',
                            animate: true,
                            loadFilter: function (res) {
                                if (res.success) {
                                    return res.data;
                                } else {
                                    return null;
                                }
                            },
                            onClick: function (node) {
                                if ($(this).tree("isLeaf", node.target)) {
                                    //填写cid
                                    _ele.parent().find("[name=cid]").val(node.id).next().text(node.text);
                                    // _ele.parent().find('span').text(node.text);//后面的作用是什么
                                    $(_win).window('close');
                                    if (data && data.fun){
                                        data.fun.call(this, node);
                                    }
                                }
                            }
                        });
                    },
                    onClose: function () {
                    }
                }).window('open');
        });
    },
    kindEditorParams: {
        filePostName: "uploadFile",//指定上传文件参数名称
        uploadJson: 'upload/picture',//指定上传文件请求的url
        dir: "image"//上传类型
    },
    //初始化富文本编辑器
    createEditor: function () {
        return KindEditor.create("#desc", TT.kindEditorParams);
    },
    //初始化图片上传组件
    initPictureUpload: function () {
        var element = $(".picFileUpload");
        element.siblings("div.pics").remove();
        element.after('\<div class="pics">\<ul></ul>\</div>');
        //回显图片
        //todo
        //给 上传按钮绑定 click 事件
        element.click(function () {
            var form = $(this).parentsUntil("form").parent('form');
            //打开图片上传窗口
            KindEditor.editor(TT.kindEditorParams).loadPlugin('multiimage', function () {
                var editor = this;
                editor.plugin.multiImageDialog({
                    clickFn: function (urlList) {
                        var imgArray = [];
                        KindEditor.each(urlList, function (i, data) {
                            imgArray.push(data.url);
                            form.find(".pics ul").append("<li><a href='" + data.url + "'target='_blank'><img src='" + data.url + "'width='80' height='50'/></a></li>");
                        });
                        form.find("[name=image]").val(imgArray.join(","));
                        editor.hideDialog();
                    }
                })
            })
        });
    }
};