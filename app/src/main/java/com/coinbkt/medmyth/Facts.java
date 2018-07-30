package com.coinbkt.medmyth;

public class Facts {
    private String facts;
    private int thumbnail;

    public Facts(){

    }

    public Facts(String facts, int thumbnail){
        this.facts = facts;
        this.thumbnail = thumbnail;
    }

    public String getFacts() {
        return facts;
    }

    public void setFacts(String facts) {
        this.facts = facts;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}
