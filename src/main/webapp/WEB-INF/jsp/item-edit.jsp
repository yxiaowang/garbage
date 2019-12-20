<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
    <form id="itemeEditForm" class="itemForm" method="post">
        <input type="hidden" name="id"/>
        <input type="hidden" name="itemStatus"/>
        <table cellpadding="5">
            <tr>
                <td>垃圾分类类目:</td>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
                    <input type="hidden" name="cid" style="width: 280px;"></input>
                </td>
            </tr>
            <tr>
                <td>分类标题:</td>
                <td><input class="easyui-textbox" type="text" name="itemName" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>垃圾分类描述:</td>
                <td><input class="easyui-textbox" name="itemDesc"
                           data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 280px;"></input></td>
            </tr>
            <tr>
                <td>主要事项:</td>
                <td><input class="easyui-textbox" name="matters"
                           data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 280px;"></input></td>
            </tr>
            <tr>
                <td>处理方式:</td>
                <td><input class="easyui-textbox" name="disposal"
                           data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 280px;"></input></td>
            </tr>
            <tr>
                <td>标签:</td>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择标签</a>
                    <input type="hidden" name="cid" style="width: 280px;"></input>
                </td>
            </tr>
            <tr>
                <td>二维码:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="qrCode" data-options="validType:'length[1,30]'"/>
                </td>
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
        if (!$('#itemeEditForm').form('validate')) {
            $.messager.alert('提示', '表单还未填写完成!');
            return;
        }
        $.post("/item/update", $("#itemeEditForm").serialize(), function (data) {
            if (data.status == 200) {
                $.messager.alert('提示', '修改商品成功!', 'info', function () {
                    $("#itemEditWindow").window('close');
                    $("#itemList").datagrid("reload");
                });
            }
        });
    }
</script>
