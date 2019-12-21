<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--富文本编辑器--%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
    <form id="catAddForm" class="itemForm" method="post">
        <table cellpadding="5">
            <tr>
                <td>垃圾分类父类目:</td>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
                    <input type="hidden" name="cid" style="width: 280px;"></input>
                </td>
            </tr>
            <tr>
                <td>分类目录中文标题:</td>
                <td><input class="easyui-textbox" type="text" name="nameCn" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>分类目录英文标题:</td>
                <td><input class="easyui-textbox" type="text" name="nameEn" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>分类目录描述:</td>
                <td><input class="easyui-textbox" name="catDesc"
                           data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 280px;"></input></td>
            </tr>
            <tr>
                <td>logo:</td>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传logo</a>
                    <input type="hidden" name="image"/>
                </td>
            </tr>
            <%--            <tr>--%>
            <%--                <td>商品描述:</td>--%>
            <%--                <td>--%>
            <%--                    <textarea style="width:800px;height:300px;visibility:hidden;" name="desc"></textarea>--%>
            <%--                </td>--%>
            <%--            </tr>--%>
            <%--            <tr class="params hide">--%>
            <%--                <td>商品规格:</td>--%>
            <%--                <td>--%>

            <%--                </td>--%>
            <%--            </tr>--%>
        </table>
    </form>
    <div style="padding:5px">
        <%--javascript:void(0)不会整体刷新页面，知识点击该按钮，会执行响应的事件--%>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
    </div>
</div>
<script type="text/javascript">
    var itemAddEditor;
    //页面初始化完毕后执行此方法
    $(function () {
        //初始化类目选择和图片上传器
        TAOTAO.init({
            fun: function (node) {
                //根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
                TAOTAO.changeItemParam(node, "catAddForm");
            }
        });
    });

    //提交表单
    function submitForm() {
        //有效性验证
        if (!$('#catAddForm').form('validate')) {
            $.messager.alert('提示', '表单还未填写完成!');
            return;
        }

        //同步文本框中的商品描述，由于富文本编辑器不是form表单中内容，需要同步到form表单
        //ajax的post方式提交表单
        $.post("/cat/save", $("#catAddForm").serialize(), function (data) {
            //这里的data数据是响应的json数据
            if (data.status == 200) {
                $.messager.alert('提示', '新增分类目录成功!');
                clearForm();
            }

        });
    }

    function clearForm() {
        $('#catAddForm').form('reset');
        itemAddEditor.html('');
    }
</script>
