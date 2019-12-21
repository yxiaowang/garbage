<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<table class="easyui-datagrid" id="itemList" title="垃圾分类列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/item/list',method:'get',pageSize:30,toolbar:toolbar">
    <%--这个是请求的url，自带page和rows参数--%>
    <%--请求的url：http://localhost:8080/item/list?page=1&rows=30 --%>
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',width:60">垃圾分类项ID</th>
        <th data-options="field:'itemName',width:200">分类名称</th>
        <th data-options="field:'cid',width:100">分类目录ID</th>
        <th data-options="field:'itemDesc',width:100">详细信息</th>
        <th data-options="field:'matters',width:100">主要事项</th>
        <th data-options="field:'disposal',width:100">处理方式</th>
        <th data-options="field:'logo',width:100">logo</th>
        <th data-options="field:'qrCide',width:100">二维码</th>
        <th data-options="field:'itemStatus',width:60,align:'center',formatter:TAOTAO.formatItemStatus">状态</th>
        <th data-options="field:'created',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
        <th data-options="field:'updated',width:130,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
    </tr>
    </thead>
</table>

<div id="itemEditWindow" class="easyui-window" title="编辑垃圾分类项"
     data-options="modal:true,closed:true,iconCls:'icon-save',href:'/item-edit'"
     style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds() {
        var itemList = $("#itemList");
        var sels = itemList.datagrid("getSelections");
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

            $("#itemEditWindow").window({
                onLoad: function () {
                    //回显数据
                    var data = $("#itemList").datagrid("getSelections")[0];
                    $("#itemEditForm").form("load", data);
                    TAOTAO.init({
                        "pics": data.image,
                        "cid": data.cid,
                        fun: function (node) {
                            TAOTAO.changeItemParam(node, "itemEditForm");
                        }
                    });
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
                    $.post("/item/delete", params, function (data) {
                        if (data.status == 200) {
                            $.messager.alert('提示', '删除成功!', undefined, function () {
                                $("#itemList").datagrid("reload");
                            });
                        }
                    });
                }
            });
        }
    }];
</script>