package com.slapps.sljobs.ui.jobactivity;

public class jobmodel {

    private String name;
    private String sinhala;
    private String tamil;
    private String english;

    public jobmodel(String name, String sinhala, String tamil, String english) {
        this.name = name;
        this.sinhala = sinhala;
        this.tamil = tamil;
        this.english = english;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinhala() {
        return sinhala;
    }

    public void setSinhala(String sinhala) {
        this.sinhala = sinhala;
    }

    public String getTamil() {
        return tamil;
    }

    public void setTamil(String tamil) {
        this.tamil = tamil;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }



}
