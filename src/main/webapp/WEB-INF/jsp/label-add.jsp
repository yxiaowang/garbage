<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--富文本编辑器--%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
    <form id="labelAddForm" class="itemForm" method="post">
        <table cellpadding="5">
            <tr>
                <td>内容:</td>
                <td><input class="easyui-textbox" name="content"
                           data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 280px;"></input></td>
            </tr>
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
                TAOTAO.changeItemParam(node, "itemAddForm");
            }
        });
    });

    //提交表单
    function submitForm() {
        //有效性验证
        if (!$('#labelAddForm').form('validate')) {
            $.messager.alert('提示', '表单还未填写完成!');
            return;
        }
        //同步文本框中的商品描述，由于富文本编辑器不是form表单中内容，需要同步到form表单
        //$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
        $.post("/label/save", $("#labelAddForm").serialize(), function (data) {
            //这里的data数据是响应的json数据
            if (data.status == 200) {
                $.messager.alert('提示', '新增分类成功!');
                $("#labelList").datagrid("reload");
                clearForm();
            }
        });
    }

    function clearForm() {
        $('#labelAddForm').form('reset');
        itemAddEditor.html('');
    }
</script>
