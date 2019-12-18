package com.njupt.garbage.common.pojo;

public class EUTreeNode {
    private Long id;
    private String text; //节点的名称
    private String state; //节点的状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
