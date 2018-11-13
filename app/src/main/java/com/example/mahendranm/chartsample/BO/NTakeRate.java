package com.example.mahendranm.chartsample.BO;

import java.util.ArrayList;

public class NTakeRate {
    ArrayList< ArrayList<String> > labels = new ArrayList < ArrayList<String>> ();
    private String name;
    private String uom;
    ArrayList < ArrayList<String> > scores = new ArrayList < ArrayList<String> > ();

    public ArrayList<ArrayList<String>> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<ArrayList<String>> labels) {
        this.labels = labels;
    }

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

    public ArrayList<ArrayList<String>> getScores() {
        return scores;
    }

    public void setScores(ArrayList<ArrayList<String>> scores) {
        this.scores = scores;
    }
}
