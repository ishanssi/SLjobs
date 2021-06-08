package com.slapps.sljobs.ui.gazzatteactivity;

class gazettemodel {

    String date;
    String link;
    String imageurl;

    public gazettemodel(String date, String link, String imageurl) {
        this.date = date;
        this.link = link;
        this.imageurl = imageurl;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
