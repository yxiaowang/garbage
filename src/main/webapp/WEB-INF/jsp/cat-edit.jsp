<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
    <form id="catEditForm" class="itemForm" method="post">
        <input type="hidden" name="id"/>
        <input type="hidden" name="catStatus"/>
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
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
    </div>
</div>
<script type="text/javascript">
    function submitForm() {
        if (!$('#catEditForm').form('validate')) {
            $.messager.alert('提示', '表单还未填写完成!');
            return;
        }
        $.post("/cat/update", $("#catEditForm").serialize(), function (data) {
            if (data.status == 200) {
                $.messager.alert('提示', '修改分类目录成功!', 'info', function () {
                    $("#catEditWindow").window('close');
                    $("#catList").datagrid("reload");
                });
            }
        });
    }
</script>
