<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<table cellpadding="5" style="margin-left: 30px" id="itemParamAddTable" class="itemParam">
    <tr>
        <td>商品类目:</td>
        <td><a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
            <input type="hidden" name="cid" style="width: 280px;"></input>
        </td>
    </tr>
    <tr class="hide addGroupTr">
        <td>规格参数:</td>
        <td>
            <ul>
                <li><a href="javascript:void(0)" class="easyui-linkbutton addGroup">添加分组</a></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <a href="javascript:void(0)" class="easyui-linkbutton submit">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton close">关闭</a>
        </td>
    </tr>
</table>
<div class="itemParamAddTemplate" style="display: none;">
    <li class="param">
        <ul>
            <li>
                <input class="easyui-textbox" style="width: 150px;" name="group"/>&nbsp;<a href="javascript:void(0)"
                                                                                           class="easyui-linkbutton addParam"
                                                                                           title="添加参数"
                                                                                           data-options="plain:true,iconCls:'icon-add'"></a>
            </li>
            <li>
                <span>|-------</span><input style="width: 150px;" class="easyui-textbox" name="param"/>&nbsp;<a
                    href="javascript:void(0)" class="easyui-linkbutton delParam" title="删除"
                    data-options="plain:true,iconCls:'icon-cancel'"></a>
            </li>
        </ul>
    </li>
</div>
<script style="text/javascript">
    //页面加载成功之后调用function()方法，即调用TAOTAO类中的initItemCat方法，传入的参数是js对象，fun为key，后面的函数为value
    $(function(){
    TAOTAO.initItemCat({
    fun:function(node){
    $(".addGroupTr").hide().find(".param").remove();
    // 判断选择的目录是否已经添加过规格
    //这里的data变量就是ajax请求响应的数据
    $.getJSON("/item/param/query/itemcatid/" + node.id,function(data){
    if(data.status == 200 && data.data){
    $.messager.alert("提示", "该类目已经添加，请选择其他类目。", undefined, function(){
    $("#itemParamAddTable .selectItemCat").click();
    });
    return ;
    }
    $(".addGroupTr").show();
    });
    }
    });

    $(".addGroup").click(function(){
    //添加一个组
    var temple = $(".itemParamAddTemplate li").eq(0).clone();
    $(this).parent().parent().append(temple);
    temple.find(".addParam").click(function(){
    var li = $(".itemParamAddTemplate li").eq(2).clone();
    //删除一个组
    li.find(".delParam").click(function(){
    $(this).parent().remove();
    });
    li.appendTo($(this).parentsUntil("ul").parent());
    });
    //删除一个参数
    temple.find(".delParam").click(function(){
    $(this).parent().remove();
    });
    });

    $("#itemParamAddTable .close").click(function(){
    $(".panel-tool-close").click();
    });
    //提交按钮事件
    $("#itemParamAddTable .submit").click(function(){
    var params = [];
    //取出name=group中的数据
    var groups = $("#itemParamAddTable [name=group]");
    //循环
    groups.each(function(i,e){
    //p保存了每个组中的param
    var p = $(e).parentsUntil("ul").parent().find("[name=param]");
    var _ps = [];
    //循环，将每个组的param添加到_ps数组中
    p.each(function(_i,_e){
    var _val = $(_e).siblings("input").val();
    if($.trim(_val).length>0){
    _ps.push(_val);
    }
    });
    //sibling方法兄弟方法
    var _val = $(e).siblings("input").val();
    //将group和group下的params一起保存到params数组，生成json数据
    if($.trim(_val).length>0 && _ps.length > 0){
    params.push({
    "group":_val,
    "params":_ps
    });
    }
    });
    var url = "/item/param/save/"+$("#itemParamAddTable [name=cid]").val();
    //请求方式post,请求参数'paramData'，对应的值为JSON.stringify(params)，这是把js的json对象转为字符串
    $.post(url,{"paramData":JSON.stringify(params)},function(data){
    if(data.status == 200){
    $.messager.alert('提示','新增商品规格成功!',undefined,function(){
    //提示完之后，关闭窗口
    $(".panel-tool-close").click();
    //重新加载datagrid即分页显示列表
    $("#itemParamList").datagrid("reload");
    });
    }
    });
    });
    });
</script>