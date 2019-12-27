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
    <div  style="margin-top: 5px;">
        <tr style="margin-top: 10px;">
            <input id="cat-search" placeholder="分类目录查询"/>
            <button style="margin-left: 5px;" id="cat-btn" data-options="attributes:{'url':'cat-search'}">搜索</button>
        </tr>
        <tr style="margin-top: 10px;">
            <input style="margin-left: 20px;"  id="label-search" placeholder="标签查询"/>
            <button style="margin-left: 5px;" id="label-btn">搜索</button>
        </tr>
        <tr style="margin-top: 10px;">
            <input style="margin-left: 20px;" id="pic-searching" placeholder="图片查询"/>
            <button style="margin-left: 5px;" id="pic-btn">搜索</button>
        </tr>
    </div>

