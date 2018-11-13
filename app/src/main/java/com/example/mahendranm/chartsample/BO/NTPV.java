package com.example.mahendranm.chartsample.BO;

import java.util.ArrayList;

public class NTPV {
    ArrayList< ArrayList<String>> labels = new ArrayList < > ();
    private String name;
    private String uom;
    ArrayList < ArrayList<String> > scores = new ArrayList <  > ();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public ArrayList<ArrayList<String>> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<ArrayList<String>> labels) {
        this.labels = labels;
    }

    public ArrayList<ArrayList<String>> getScores() {
        return scores;
    }

    public void setScores(ArrayList<ArrayList<String>> scores) {
        this.scores = scores;
    }
}
