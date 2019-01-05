package server.services;

public enum option {
    Admin, Client;

    public String value(){
        return name();
    }

    public static option fromvalue(String v){
        return valueOf(v);
    }
}