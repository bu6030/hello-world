package com.example.demo;

import javax.validation.constraints.*;

public class ValidationRequest {
    @Max(value=50,message = "count最大小于等于50")
    @Min(value=10,message = "count最小大于等于10")
    private int count;
    @NotNull(message = "age不能为空")
    private String age;
    @NotBlank(message = "name不能为空或者空格")
    private String name;
    @Size(min=10, max=20,message = "content字符长度在10-20之间")
    private String content;
    @NotEmpty(message = "remark不能为空或（允许为空格）")
    private String remark;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ValidationRequest{" +
                "count=" + count +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
