package com.tmall.project5.util;

public class BeanGenerator {
    public static void main(String[] args) {
        String[] mapper = new String[] {"book", "comment", "courseAttendance", "course", "courseMaterials", "courseNotice", "courseQna", "free", "member", "notice", "register", "storage", "teacher"};

        for(int i=0; i< mapper.length; i++){
            String m = mapper[i];
            System.out.println("@Bean");
            System.out.println("public "+capitalize(m)+"Service "+m+"Service() {return new "+capitalize(m)+"ServiceImpl();}");
            System.out.println();
        }
    }

    public static String capitalize(String str){
        String cap = str.substring(0, 1).toUpperCase()+str.substring(1);
        return cap;
    }
}
