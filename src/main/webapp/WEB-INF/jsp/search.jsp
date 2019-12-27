<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <style>
        .center{
            text-align: center;
        }
        .resultHead{
            border: 1px solid #dddddd;background: #e5efff;height: 2em;line-height: 2em;
            font-weight: bold;
        }
        .resultTHead{
            border: 1px solid #dddddd;background: #f4f4f4;height: 2em;
        }
        .resultTable{
            margin-top: 10px;
            border: 1px solid #a5b8e7;background: #fff;
        }
    </style>
    <script type="text/javascript">
        function dateFormat(date) {
            return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
        }
        function exaggerate(data) {
            html = "<thead class=\"resultTHead\">\n" +
                "                <tr>\n" +
                "                    <th style=\"width: 8%;text-align: center\">垃圾分类项ID</th>\n" +
                "                    <th style=\"width: 8%;text-align: center\">分类名称</th>\n" +
                "                    <th style=\"width: 8%;text-align: center\">分类目录ID</th>\n" +
                "                    <th style=\"width: 12%;text-align: center\">详细信息</th>\n" +
                "                    <th style=\"width: 12%;text-align: center\">主要事项</th>\n" +
                "                    <th style=\"width: 10%;text-align: center\" >处理方式</th>\n" +
                "                    <th style=\"width: 10%;text-align: center\">logo</th>\n" +
                "                    <th style=\"width: 8%;text-align: center\">二维码</th>\n" +
                "                    <th style=\"width: 10%;text-align: center\">状态</th>\n" +
                "                    <th style=\"width: 10%;text-align: center\">创建日期</th>\n" +
                "                    <th style=\"width: 10%;text-align: center\">更新日期</th>\n" +
                "                </tr>\n" +
                "            </thead>";
            for(var i=0; i < data.length; i++){
                var created = dateFormat(new Date(data[i].created));
                var updated = dateFormat(new Date(data[i].updated));
                html += "<tr>\n" +
                    "                <td class=\"center\">"+ data[i].id +"</td>\n" +
                    "                <td class=\"center\">"+ data[i].itemName +"</td>\n" +
                    "                <td class=\"center\">"+ data[i].cid +"</td>\n" +
                    "                <td class=\"center\">"+ data[i].itemDesc +"</td>\n" +
                    "                <td class=\"center\">"+ data[i].matters +"</td>\n" +
                    "                <td class=\"center\">"+ data[i].disposal +"</td>\n" +
                    "                <td class=\"center\">"+ data[i].logo +"</td>\n" +
                    "                <td class=\"center\">"+ data[i].qrCode +"</td>\n" +
                    "                <td class=\"center\">正常</td>\n" +
                    "                <td class=\"center\">"+ created +"</td>\n" +
                    "                <td class=\"center\">"+ updated +"</td>\n" +
                    "            </tr>";
            }
            return html;
        }

        function catSearch() {
            var html="";
            var nameCn = $("#nameCn").val();
            $.ajax({
                type:"get",
                async:false,//同步请求
                url:"/item/findItemByCatCnName/" + nameCn,
                success:function (data) {
                    $("#searchResult").html(exaggerate(data));
                }
            });
        }

        function keywordSearch() {
            var html="";
            var keyword = $("#keyword").val();
            $.ajax({
                type:"get",
                async:false,//同步请求
                url:"/item/findItemByKeyword/" + keyword,
                success:function (data) {
                    $("#searchResult").html(exaggerate(data));
                }
            });
        }

        function labelSearch() {
            var html="";
            var content = $("#content").val();
            alert(content);
            $.ajax({
                type:"get",
                async:false,//同步请求
                url:"/item/findItemByLabelContent/" + content,
                success:function (data) {
                    $("#searchResult").html(exaggerate(data));
                }
            });

        }
    </script>
    <div  style="margin-top: 5px;">
        <tr style="margin-top: 10px;">
            <input style="margin-left: 20px;" id="keyword" placeholder="关键词查询" />
            <button style="margin-left: 5px;" id="keyword-btn" onclick="keywordSearch()">搜索</button>
        </tr>

        <tr style="margin-top: 10px;">
            <input id="nameCn" placeholder="分类目录查询"/>
            <button style="margin-left: 5px;" id="cat-btn" onclick="catSearch()">搜索</button>
        </tr>
        <tr style="margin-top: 10px;">
            <input style="margin-left: 20px;"  id="content" placeholder="标签查询"/>
            <button style="margin-left: 5px;" id="label-btn" onclick="labelSearch()">搜索</button>
        </tr>
    </div>
    <div class="resultTable"><div class="resultHead">

            &nbsp;&nbsp;查询结果
        </div>
        <table id = "searchResult">
            <thead class="resultTHead">
                <tr>
                    <th style="width: 8%;text-align: center">垃圾分类项ID</th>
                    <th style="width: 8%;text-align: center">分类名称</th>
                    <th style="width: 8%;text-align: center">分类目录ID</th>
                    <th style="width: 12%;text-align: center">详细信息</th>
                    <th style="width: 12%;text-align: center">主要事项</th>
                    <th style="width: 10%;text-align: center" >处理方式</th>
                    <th style="width: 10%;text-align: center">logo</th>
                    <th style="width: 8%;text-align: center">二维码</th>
                    <th style="width: 10%;text-align: center">状态</th>
                    <th style="width: 10%;text-align: center">创建日期</th>
                    <th style="width: 10%;text-align: center">更新日期</th>
                </tr>
            </thead>

            <tr>
                <td class="center">1</td>
                <td class="center">1</td>
                <td class="center">1</td>
                <td class="center">1</td>
                <td class="center">1</td>
                <td class="center">1</td>
                <td class="center">1</td>
                <td class="center">1</td>
                <td class="center">1</td>
                <td class="center">1</td>
                <td class="center">1</td>
            </tr>
        </table>
    </div>
