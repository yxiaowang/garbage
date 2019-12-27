<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<table class="easyui-datagrid" id="userList" title="垃圾分类列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/user/list',method:'get',pageSize:30,toolbar:toolbar">
    <%--这个是请求的url，自带page和rows参数--%>
    <%--请求的url：http://localhost:8080/item/list?page=1&rows=30 --%>
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',width:60">用户ID</th>
        <th data-options="field:'username',width:200">用户名</th>
        <th data-options="field:'type',width:200">用户权限(1-只读，0-读写, -1-管理员)</th>
        <th data-options="field:'created',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
        <th data-options="field:'updated',width:130,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
    </tr>
    </thead>
</table>

<script>

    function getSelectionsIds() {
        var userList = $("#userList");
        var sels = userList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

    var toolbar = [{
        text: '添加编辑权限',
        iconCls: 'icon-add',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert('提示', '未选中用户!');
                return;
            }
            $.messager.confirm('确认', '确定添加ID为 ' + ids + ' 的用户吗？', function (r) {
                if (r) {
                    var params = {"ids": ids, "type":0};
                    $.post("/user/setPermission", params, function (data) {
                        if (data.status == 200) {
                            $.messager.alert('提示', '操作成功!', undefined, function () {
                                $("#userList").datagrid("reload");
                            });
                        }
                    });
                }
            });
        }
        }, {
        text: '删除编辑权限',
        iconCls: 'icon-cancel',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert('提示', '未选中用户!');
                return;
            }
            $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的用户权限吗？', function (r) {
                if (r) {
                    var params = {"ids": ids, "type" : 1};
                    $.post("/user/setPermission", params, function (data) {
                        if (data.status == 200) {
                            $.messager.alert('提示', '删除成功!', undefined, function () {
                                $("#userList").datagrid("reload");
                            });
                        }
                    });
                }
            });
        }
    }];
</script>