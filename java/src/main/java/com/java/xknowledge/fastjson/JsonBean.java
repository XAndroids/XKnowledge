package com.java.xknowledge.fastjson;

class JsonBean {
    public int id = 1000;
    public String name = "li";
    public Ext ext = new Ext(234, "com.fastjson");

    static class Ext {
        public int pid;
        public String pName;

        public Ext(int pid, String pName) {
            this.pid = pid;
            this.pName = pName;
        }
    }
}
