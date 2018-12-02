package com.example.samsung.android_project.DataStruct;

import java.io.Serializable;

public class Law implements Serializable {
    public static String[] contentName={
            "법령일련번호","현행연혁코드","법령명한글","법령약칭명","법령ID","공포일자","공포번호",
            "제개정구분명","소관부처명","소관부처코드","법령구분명","시행일자","자법타법여부","법령상세링크"};
    private String[] content={"","","","","","","","","","","","","",""};

    public Law(){}

    public void setContent(String name,String c){
        for(int i=0;i<contentName.length;i++){
            if(contentName[i].equals(name)){
                content[i]=c;
            }
        }
    }
    public String getContent(int i){
        return content[i];
    }
    public String toString(){
        StringBuffer result=new StringBuffer();
        for(int i=0;i<contentName.length;i++){
            result.append(contentName[i]);
            result.append("-");
            result.append(content[i]);
            result.append("\n");
        }
        return result.toString();
    }

}
