package com.example.safetynest;

public class Model {



    private String header;
    private String desc;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

   // public int getImgname() {
   //     return imgname;
   // }

    //public void setImgname(int imgname) {
    //    this.imgname = imgname;
    //}

   // private int imgname;


    Model(){}
    public Model(String header, String desc) {
        this.header=header;
        this.desc=desc;

    }


}
