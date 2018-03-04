package com.example.peacock.myapplicationfragmentdemo.model;

/**
 * Created by peacock on 6/15/16.
 */
public class DashBoarditem {
    private String itenname;
    private String font;
    private String ID;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int position;
    public DashBoarditem(String itenname, String font ) {
        this.itenname = itenname;
        this.font = font;
    }

    public DashBoarditem(String ID, String itenname, String font,int position ) {
        this.itenname = itenname;
        this.font = font;
        this.ID=ID;
        this.position=position;
    }

    public DashBoarditem(String ID,String itenname, String font) {
        this.itenname = itenname;
        this.font = font;
        this.ID = ID;
    }

    public void setID(String id){
        this.ID=id;
    }
    public String getID(){
        return ID;
    }

    public void setItenname(String itenname){
        this.itenname=itenname;
    }
    public String getItenname(){
        return itenname;
    }
    public String getImgfont(){

        return  font;
    }
    public void setImgfont(String font){

        this.font=font;
    }

}
