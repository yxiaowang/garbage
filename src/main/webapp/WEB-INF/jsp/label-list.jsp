<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<table class="easyui-datagrid" id="labelList" title="垃圾分类列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/label/list',method:'get',pageSize:30,toolbar:toolbar">
    <%--这个是请求的url，自带page和rows参数--%>
    <%--请求的url：http://localhost:8080/item/list?page=1&rows=30 --%>
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',width:60">标签ID</th>
        <th data-options="field:'content',width:200">标签内容</th>
        <th data-options="field:'labelStatus',width:60,align:'center',formatter:TAOTAO.formatItemStatus">状态</th>
        <th data-options="field:'createTime',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
        <th data-options="field:'updateTime',width:130,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
    </tr>
    </thead>
</table>

<div id="labelEditWindow" class="easyui-window" title="编辑垃圾分类项"
     data-options="modal:true,closed:true,iconCls:'icon-save',href:'/label-edit'"
     style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds() {
        var labelList = $("#labelList");
        var sels = labelList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

    var toolbar = [{
        text: '新增',
        iconCls: 'icon-add',
        handler: function () {
            $(".tree-title:contains('新增垃圾分类项')").parent().click();
        }
    }, {
        text: '编辑',
        iconCls: 'icon-edit',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert('提示', '必须选择一个分类才能编辑!');
                return;
            }
            if (ids.indexOf(',') > 0) {
                $.messager.alert('提示', '只能选择一个分类!');
                return;
            }

            $("#labelEditWindow").window({
                onLoad: function () {
                    //回显数据
                    var data = $("#labelList").datagrid("getSelections")[0];
                    $("#labelEditForm").form("load", data);
                }
            }).window("open");
        }
    }, {
        text: '删除',
        iconCls: 'icon-cancel',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert('提示', '未选中分类!');
                return;
            }
            $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的分类吗？', function (r) {
                if (r) {
                    var params = {"ids": ids};
                    $.post("/label/delete", params, function (data) {
                        if (data.status == 200) {
                            $.messager.alert('提示', '删除成功!', undefined, function () {
                                $("#labelList").datagrid("reload");
                            });
                        }
                    });
                }
            });
        }
    }];
</script>