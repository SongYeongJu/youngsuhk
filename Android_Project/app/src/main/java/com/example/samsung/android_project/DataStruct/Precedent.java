package com.example.samsung.android_project.DataStruct;

import java.io.Serializable;

public class Precedent implements Serializable {
    public static String[] contentName={
            "판례일련번호", "사건명", "사건번호", "선고일자", "법원명", "법원종류코드",
            "사건종류명", "사건종류코드", "판결유형", "선고", "판례상세링크", };
    private String[] pContent={"","","","","","","","","","",""};

    public void setpContent(String string,String c){
        for(int i=0;i<contentName.length;i++){
            if(string.equals(contentName[i])){
                    pContent[i]=c;
            }
        }
    }
    public String getPContent(int i){
        return pContent[i];
    }
    public String toString(){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<contentName.length;i++){
            //sb.append(contentName[i]);
            //sb.append("-");
            sb.append(pContent[i]);
            sb.append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
    public Precedent(){
    }
}
