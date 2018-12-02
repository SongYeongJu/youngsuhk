package com.example.samsung.android_project.DataStruct;

import java.io.Serializable;

public class Precedent implements Serializable {
    // 판례의 정보를 저장하는 data class

    // contentName의 순서대로 pContent에 내용을 저장함
    public static String[] contentName={
            "판례일련번호", "사건명", "사건번호", "선고일자", "법원명", "법원종류코드",
            "사건종류명", "사건종류코드", "판결유형", "선고", "판례상세링크", };
    private String[] pContent={"","","","","","","","","","",""};

    // string에 따라서 알맞은 위치에 c를 pContent에 저장함
    public void setpContent(String string,String c){
        for(int i=0;i<contentName.length;i++){
            if(string.equals(contentName[i])){
                    pContent[i]=c;
            }
        }
    }

    // i 에 따라서 pContent[i]를 리턴함
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
