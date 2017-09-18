package com.upic.common.base.condition;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class BaseCondition implements Serializable {
    private Long id;

    private Date creatTime;

    private Date creatTimeTo;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    public BaseCondition() {
    }

    public BaseCondition(Long id, Date creatTime, Date creatTimeTo, String field1, String field2, String field3, String field4, String field5) {
        this.id = id;
        this.creatTime = creatTime;
        this.creatTimeTo = creatTimeTo;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getCreatTimeTo() {
        return creatTimeTo;
    }

    public void setCreatTimeTo(Date creatTimeTo) {
        this.creatTimeTo = creatTimeTo;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }
}
