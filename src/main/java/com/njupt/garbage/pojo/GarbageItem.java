package com.njupt.garbage.pojo;

import java.util.Date;

public class GarbageItem {
    private Long id;

    private String name;

    private String logo;

    private String matters;

    private String disposal;

    private String qrCode;

    private Long cid;

    private Byte status;

    private Date created;

    private Date updated;

    private String desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getMatters() {
        return matters;
    }

    public void setMatters(String matters) {
        this.matters = matters == null ? null : matters.trim();
    }

    public String getDisposal() {
        return disposal;
    }

    public void setDisposal(String disposal) {
        this.disposal = disposal == null ? null : disposal.trim();
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}