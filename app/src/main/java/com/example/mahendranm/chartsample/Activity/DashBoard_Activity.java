package com.example.mahendranm.chartsample.Activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mahendranm.chartsample.Adapter.ContentAdapter;
import com.example.mahendranm.chartsample.AsyncCall.AsyncService;
import com.example.mahendranm.chartsample.BO.Data;
import com.example.mahendranm.chartsample.BO.NCustLiab;
import com.example.mahendranm.chartsample.BO.NRevenue;
import com.example.mahendranm.chartsample.BO.NTPV;
import com.example.mahendranm.chartsample.BO.NTakeRate;
import com.example.mahendranm.chartsample.BO.NegativeBal;
import com.example.mahendranm.chartsample.BO.WatchBO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.highsoft.highcharts.Common.HIChartsClasses.HIChart;
import com.highsoft.highcharts.Common.HIChartsClasses.HICredits;
import com.highsoft.highcharts.Common.HIChartsClasses.HIDataLabels;
import com.highsoft.highcharts.Common.HIChartsClasses.HIExporting;
import com.highsoft.highcharts.Common.HIChartsClasses.HIItemStyle;
import com.highsoft.highcharts.Common.HIChartsClasses.HILabels;
import com.highsoft.highcharts.Common.HIChartsClasses.HILegend;
import com.highsoft.highcharts.Common.HIChartsClasses.HILine;
import com.highsoft.highcharts.Common.HIChartsClasses.HIMarker;
import com.highsoft.highcharts.Common.HIChartsClasses.HIOptions;
import com.highsoft.highcharts.Common.HIChartsClasses.HIPlotLines;
import com.highsoft.highcharts.Common.HIChartsClasses.HIPlotOptions;
import com.highsoft.highcharts.Common.HIChartsClasses.HISeries;
import com.highsoft.highcharts.Common.HIChartsClasses.HIStyle;
import com.highsoft.highcharts.Common.HIChartsClasses.HITitle;
import com.highsoft.highcharts.Common.HIChartsClasses.HITooltip;
import com.highsoft.highcharts.Common.HIChartsClasses.HIXAxis;
import com.highsoft.highcharts.Common.HIChartsClasses.HIYAxis;
import com.highsoft.highcharts.Common.HIColor;
import com.highsoft.highcharts.Core.HIChartView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.mahendranm.chartsample.R;


import org.json.JSONArray;
import org.json.JSONObject;

public class DashBoard_Activity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    List<String> _ContentList;
    AsyncService asyncService;

    // This is for Button
    AppCompatTextView txt_Day7, txt_Day14, txt_Day21;

    // This is for Action bar Text
    AppCompatTextView txt_date, txt_Current, txt_Current1, txt_Current2, txt_Current3,
            txt_Prior, txt_Prior1, txt_Prior2, txt_Prior3, txt_HealthCare,
            txt_HealthCare1, txt_HealthCare2, txt_HealthCare3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashbord_activity);
        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);


        // new Code defualt selection

        txt_date = findViewById(R.id.Date);

        txt_Current = findViewById(R.id.Current);
        txt_Current1 = findViewById(R.id.Current1);
        txt_Current2 = findViewById(R.id.Current2);
        txt_Current3 = findViewById(R.id.Current3);

        txt_Prior = findViewById(R.id.Prior);
        txt_Prior1 = findViewById(R.id.Prior1);
        txt_Prior2 = findViewById(R.id.Prior2);
        txt_Prior3 = findViewById(R.id.Prior3);

        txt_HealthCare = findViewById(R.id.HealthCare);
        txt_HealthCare1 = findViewById(R.id.HealthCare1);
        txt_HealthCare2 = findViewById(R.id.HealthCare2);
        txt_HealthCare3 = findViewById(R.id.HealthCare3);

        txt_Day7 = findViewById(R.id.Day7);
        txt_Day7.setOnClickListener(this);
        txt_Day14 = findViewById(R.id.Day14);
        txt_Day14.setOnClickListener(this);
        txt_Day21 = findViewById(R.id.Day21);
        txt_Day21.setOnClickListener(this);
        txt_Day7.setBackgroundResource(R.color.colorPrimary);
        txt_Day7.setTextColor(getResources().getColor(R.color.lineOrange));
        txt_Day14.setTextColor(getResources().getColor(R.color.buttonColor));
        txt_Day14.setBackgroundResource(R.color.colorPrimaryDark);
        txt_Day21.setTextColor(getResources().getColor(R.color.lineOrange));
        txt_Day21.setBackgroundResource(R.color.colorPrimary);


        // old code start here down
        _ContentList = new ArrayList<>();
        _ContentList.add("$ ntpv");
        _ContentList.add("$ ntpv");
        _ContentList.add("$ ntpv");
        _ContentList.add("$ ntpv");
        _ContentList.add("$ ntpv");


        ///////////////////////////////////////
        ///////////// First Chart ////////////
        ///////////////////////////////////////

        HIChartView chartView = (HIChartView) findViewById(R.id.hc);
        HIChartView chartView1 = (HIChartView) findViewById(R.id.hc1);


        HIOptions options = new HIOptions();
        HIChart chart = new HIChart();
        chart.setType("line");
        chart.getAnimation();
        chart.setMargin(new ArrayList<>(Arrays.asList(30, 10, 50, 10)));

        HITooltip tooltip = new HITooltip();
        tooltip.setEnabled(false);
        options.setTooltip(tooltip);
        options.setChart(chart);

        HIExporting exporting = new HIExporting();
        exporting.setEnabled(false);
        options.setExporting(exporting);

        HICredits credits = new HICredits();
        credits.setEnabled(false);
        options.setCredits(credits);

        HIStyle titleStyle = new HIStyle();
        titleStyle.setColor("navy");
        titleStyle.setFontSize("12px");
        titleStyle.setFontFamily("Arial");

        HITitle title = new HITitle();
        title.setText("$ nTPV (million)");
        title.setStyle(titleStyle);

        options.setTitle(title);

        String categories[] = new String[]{"Wed, 0", "Thu, 1", "Fri, 2", "Sat, 3", "Sun, 4", "Mon, 5", "Tue, 6", "Wed, 7", "Thu, 8"};

        HIMarker HIddenmarkers = new HIMarker();
        HIddenmarkers.setEnabled(false);

        HIPlotLines plotlines = new HIPlotLines();
        plotlines.setDashStyle("Dash");
        plotlines.setWidth(0.5);
        plotlines.setValue(0);

        final HIXAxis xaxis = new HIXAxis();
        xaxis.setLabels(new HILabels());
        xaxis.setTickmarkPlacement("on");
        HIStyle xaxisStyle = new HIStyle();
        xaxisStyle.setFontSize("9px");
        xaxisStyle.setFontFamily("Arial");
        xaxisStyle.setColor("black");
        xaxisStyle.setFontWeight("bold");
        xaxis.getLabels().setStyle(xaxisStyle);
        xaxis.getLabels().setY(3);
        xaxis.getLabels().setStep(2);
        xaxis.getLabels().setRotation(270);
        xaxis.setMin(0.5);
        xaxis.setCategories(new ArrayList<>(Arrays.asList(categories)));

        options.setXAxis(new ArrayList<HIXAxis>() {{
            add(xaxis);
        }});

        final HIYAxis yaxis = new HIYAxis();
        yaxis.setLineWidth(1);
        yaxis.setGridLineWidth(1);
        yaxis.setLabels(new HILabels());
        yaxis.setMin(0);
        HIStyle yaxisStyle = new HIStyle();
        yaxisStyle.setFontFamily("Arial");
        yaxisStyle.setFontSize("0px");
        yaxis.getLabels().setStyle(yaxisStyle);
        yaxis.getLabels().setX(-4);
        yaxis.setTitle(new HITitle());
        yaxis.getTitle().setText("");
        yaxis.setGridLineDashStyle("dash");
        yaxis.setGridLineWidth(0.5);
        yaxis.setLineWidth(0.5);
        yaxis.setLineColor(HIColor.initWithRGBA(255, 0, 0, 1));
        yaxis.setGridLineColor(HIColor.initWithRGBA(217, 217, 217, 1));

        options.setYAxis(new ArrayList<HIYAxis>() {{
            add(yaxis);
        }});

        HIStyle dataLableStyle = new HIStyle();
        dataLableStyle.setFontSize("10px");
        HIDataLabels dataLabels = new HIDataLabels();
        dataLabels.setEnabled(true);
        dataLabels.setCrop(false);
        dataLabels.setOverflow(false);
        dataLabels.setStyle(dataLableStyle);

//// For chart legend style //

        HIItemStyle itemStyle = new HIItemStyle();
        itemStyle.setFontWeight("1");
        itemStyle.setFontSize("10px");

        HILegend legend = new HILegend();
        legend.setAlign("right");
        legend.setItemDistance(5);
        legend.setPadding(0);
        legend.setItemMarginBottom(-9);
        legend.setItemStyle(itemStyle);

        HILine line1 = new HILine();
        line1.setName("Current");
        line1.setShowInLegend(true);

        line1.setDataLabels(dataLabels);
        line1.setColor(HIColor.initWithRGBA(0, 155, 255, 1));
        line1.getDataLabels().setY(-30);
        line1.setLineWidth(1);

        line1.getDataLabels().setColor(HIColor.initWithRGBA(0, 48, 135, 1));
        line1.getDataLabels().setRotation(270);
        line1.setData(new ArrayList<>(Arrays.asList(43934, 52503, 57177, 69658, 77031, 89931, 97133, 43934)));

        HILine line2 = new HILine();
        line2.setName("7dma");
        line2.setMarker(HIddenmarkers);
        line2.setLineWidth(1);
        line2.setColor(HIColor.initWithRGBA(89, 89, 0, 1));
        line2.setData(new ArrayList<>(Arrays.asList(34916, 24064, 39742, 49851, 32490, 20282, 58121, 74340)));

        HILine line3 = new HILine();
        line3.setName("PriorYR");
        line3.setLineWidth(1);
        line3.setMarker(HIddenmarkers);
        line3.setColor(HIColor.initWithRGBA(255, 101, 0, 1));
        line3.setData(new ArrayList<>(Arrays.asList(11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387)));

        HILine line4 = new HILine();
        line4.setName("PriorYR 7dma");
        line4.setLineWidth(1);
        line4.setMarker(HIddenmarkers);
        line4.setColor(HIColor.initWithRGBA(187, 42, 117, 1));
        line4.setData(new ArrayList<>(Arrays.asList(12908, 5948, 8105, 79058, 35031, 100931, 18274, 18111)));

        HIPlotOptions plotOptions = new HIPlotOptions();

        HISeries series = new HISeries();

        plotOptions.setSeries(series);
        options.setSeries(new ArrayList<HISeries>(Arrays.asList(line1, line2, line3, line4)));
        options.setLegend(legend);
        chartView.setOptions(options);


//        ///////////////////////////////////////
//        ///////////// Second Chart ////////////
//        ///////////////////////////////////////
//

        HIOptions options1 = new HIOptions();
        HIChart chart1 = new HIChart();
        chart1.setType("line");
        chart1.getAnimation();
        chart1.setMargin(new ArrayList<>(Arrays.asList(30, 10, 50, 10)));

        HITooltip tooltip1 = new HITooltip();
        tooltip1.setEnabled(false);
        options1.setTooltip(tooltip1);
        options1.setChart(chart1);

        HIExporting exporting1 = new HIExporting();
        exporting1.setEnabled(false);
        options1.setExporting(exporting1);

        HICredits credits1 = new HICredits();
        credits1.setEnabled(false);
        options1.setCredits(credits1);

        HITitle title1 = new HITitle();
        title1.setText("$ nTPV(Healthscore)");
        title1.setStyle(titleStyle);
        options1.setTitle(title1);

        String categories1[] = new String[]{"Wed, 17", "Thu, 18", "Fri, 19", "Sat, 20", "Sun, 21", "Mon, 22", "Tue, 23", "Wed, 24", "Thu, 25"};

        final HIXAxis xaxis1 = new HIXAxis();
        xaxis1.setLabels(new HILabels());
        xaxis1.setTickmarkPlacement("on");
        HIStyle xaxisStyle1 = new HIStyle();
        xaxisStyle1.setFontSize("9px");
        xaxisStyle1.setFontFamily("Arial");
        xaxisStyle1.setColor("black");
        xaxisStyle1.setFontWeight("bold");
        xaxis1.getLabels().setStyle(xaxisStyle1);
        xaxis1.getLabels().setY(3);
        xaxis1.getLabels().setStep(2);
        xaxis1.getLabels().setRotation(270);

        xaxis1.setMin(0.5);

        xaxis1.setCategories(new ArrayList<>(Arrays.asList(categories1)));
        options1.setXAxis(new ArrayList<HIXAxis>() {{
            add(xaxis1);
        }});

        final HIYAxis yaxis1 = new HIYAxis();
        yaxis1.setLineWidth(1);
        yaxis1.setGridLineWidth(1);
        yaxis1.setLabels(new HILabels());
        HIStyle yaxisStyle1 = new HIStyle();

        yaxis1.setStartOnTick(false);
        yaxis1.setEndOnTick(false);

        yaxisStyle1.setFontFamily("Arial");
        yaxisStyle1.setFontSize("0px");
        yaxis1.getLabels().setStyle(yaxisStyle1);
        yaxis1.getLabels().setX(-4);
        yaxis1.setTitle(new HITitle());
        yaxis1.getTitle().setText("");
        options1.setYAxis(new ArrayList<HIYAxis>() {{
            add(yaxis1);
        }});

        HIDataLabels dataLabels1 = new HIDataLabels();
        dataLabels1.setEnabled(true);

        HILine line11 = new HILine();
        line11.setName("PriorYR");
        line11.setLineWidth(1);
        line11.setMarker(HIddenmarkers);
        line11.setColor(HIColor.initWithRGBA(89, 89, 0, 1));
        line11.setData(new ArrayList<>(Arrays.asList(11744, 17722, 16005, 19771, 20185, 24377, 22147, 29387)));

        HIMarker redMarker = new HIMarker();
        redMarker.setEnabled(true);
        redMarker.setSymbol("circle");
        redMarker.setLineWidth(2);
        redMarker.setRadius(4);
        redMarker.setLineWidth(1);
        redMarker.setLineColor(HIColor.initWithRGBA(0, 155, 255, 1));
        redMarker.setFillColor(HIColor.initWithName("red"));

        HILine line22 = new HILine();
        line22.setShowInLegend(true);
        line22.setName("1-199");
        line22.setLineWidth(1);
        line22.setMarker(redMarker);
        line22.setColor(HIColor.initWithRGBA(255, 104, 0, 1));

        HIMarker orangeMarker = new HIMarker();
        orangeMarker.setEnabled(true);
        orangeMarker.setSymbol("circle");
        orangeMarker.setLineWidth(2);
        orangeMarker.setRadius(4);
        orangeMarker.setLineWidth(1);
        orangeMarker.setLineColor(HIColor.initWithRGBA(0, 155, 255, 1));
        orangeMarker.setFillColor(HIColor.initWithName("orange"));

        HILine line33 = new HILine();
        line33.setName("200-499");
        line33.setLineWidth(1);
        line33.setMarker(orangeMarker);
        line33.setShowInLegend(true);
        line33.setColor(HIColor.initWithRGBA(255, 104, 0, 1));

        HIMarker yellowMarker = new HIMarker();
        yellowMarker.setEnabled(true);
        yellowMarker.setSymbol("circle");
        yellowMarker.setLineWidth(2);
        yellowMarker.setRadius(4);
        yellowMarker.setLineWidth(1);
        yellowMarker.setLineColor(HIColor.initWithRGBA(0, 155, 255, 1));
        yellowMarker.setFillColor(HIColor.initWithName("yellow"));

        HILine line44 = new HILine();
        line44.setName("500-749");
        line44.setLineWidth(1);
        line44.setMarker(yellowMarker);
        line44.setColor(HIColor.initWithRGBA(255, 104, 0, 1));

        HIMarker blueMarker = new HIMarker();
        blueMarker.setEnabled(true);
        blueMarker.setSymbol("circle");
        blueMarker.setLineWidth(2);
        blueMarker.setRadius(4);
        blueMarker.setLineWidth(1);
        blueMarker.setLineColor(HIColor.initWithRGBA(0, 155, 255, 1));
        blueMarker.setFillColor(HIColor.initWithRGBA(0, 131, 0, 1));

        HILine line55 = new HILine();
        line55.setName("7dma");
        line55.setShowInLegend(true);

        line55.setDataLabels(dataLabels1);
        line55.setColor(HIColor.initWithRGBA(0, 155, 255, 1));
        line55.getDataLabels().setY(-30);
        line55.setLineWidth(1);
        line55.setMarker(blueMarker);
        line55.getDataLabels().setColor(HIColor.initWithRGBA(0, 48, 135, 1));
        line55.getDataLabels().setRotation(270);
        line55.setData(new ArrayList<>(Arrays.asList(14916, 14064, 19742, 19851, 22490, 20282, 28121, 24340)));

        options1.setSeries(new ArrayList<HISeries>(Arrays.asList(line11, line22, line33, line44, line55)));
        options1.setLegend(legend);
        chartView1.setOptions(options1);

        try {
            asyncService = (AsyncService) new AsyncService(this, DashBoard_Activity.this).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendResponse(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);

            // here need to convert jsonobject to normal string object
            //convert json object to Gson object
            String result = jsonObject.toString();
            Gson gson = new Gson();
            WatchBO watchBO = gson.fromJson(result, WatchBO.class);



            // Watch Bo values is been null
            // so we customize the json response to our BO class
            JSONObject jsonObject1 = (JSONObject) jsonObject.get("data");
            JSONObject jsonObject2 = (JSONObject) jsonObject1.get("nTakeRate");
            JSONObject jsonObject3 = (JSONObject) jsonObject1.get("nTPV");
            JSONObject jsonObject4 = (JSONObject) jsonObject1.get("nCustLiab");
            JSONObject jsonObject5 = (JSONObject) jsonObject1.get("nRevenue");
            JSONObject jsonObject6 = (JSONObject) jsonObject1.get("NegativeBal");


// ntakeRate
            NTakeRate nTakeRate = new NTakeRate();

            ArrayList<ArrayList<String>> labels21 = new ArrayList<>();
            ArrayList<ArrayList<String>> scores21 = new ArrayList<>();

            JSONArray jsonArray = jsonObject2.getJSONArray("labels");
            JSONArray jsonArray1 = jsonObject2.getJSONArray("scores");
            String name = String.valueOf(((JSONObject) jsonObject1.get("nTakeRate")).get("name"));
            String uom = String.valueOf(((JSONObject) jsonObject1.get("nTakeRate")).get("uom"));
            int i = 0;
            while (i < jsonArray.length()) {
                ArrayList<String> labels = new ArrayList<>();
                ArrayList<String> labels1 = new ArrayList<>();
                String sb = jsonArray.get(i).toString();
                String sb1 = sb.replace("\",\"", "&");
                String sb2 = sb1.replace("[\"", "");
                String sb3 = sb2.replace("\"]", "");
                String[] splits = sb3.split("&");
                int k = 0;
                while (k < splits.length) {
                    labels1.add(splits[k]);
                    k++;
                }

                labels.addAll(labels1);
                labels21.add(labels);
                i++;
            }


            int j = 0;
            while (j < jsonArray1.length()) {
                ArrayList<String> scores = new ArrayList<>();
                ArrayList<String> scores1 = new ArrayList<>();
                String sb = jsonArray1.get(j).toString();
                String sb1 = sb.replace("\",\"", "&");
                String sb2 = sb1.replace("[\"", "");
                String sb3 = sb2.replace("\"]", "");
                String[] splits = sb3.split("&");
                int k = 0;
                while (k < splits.length) {
                    scores1.add(splits[k]);
                    k++;
                }
                sb = null;
                scores.addAll(scores1);
                scores21.add(scores);
                j++;
            }

            nTakeRate.setLabels(labels21);
            nTakeRate.setName(name);
            nTakeRate.setUom(uom);
            nTakeRate.setScores(scores21);


// Ntpv
            NTPV ntpv = new NTPV();
            ArrayList<ArrayList<String>> ntpvlabels21 = new ArrayList<>();
            ArrayList<ArrayList<String>> ntpvscores21 = new ArrayList<>();

            JSONArray jsonArray2 = jsonObject3.getJSONArray("labels");
            JSONArray jsonArray3 = jsonObject3.getJSONArray("scores");
            String name1 = String.valueOf(((JSONObject) jsonObject1.get("nTPV")).get("name"));
            String uom1 = String.valueOf(((JSONObject) jsonObject1.get("nTPV")).get("uom"));
            int l = 0;
            while (l < jsonArray2.length()) {
                ArrayList<String> ntpvlabels = new ArrayList<>();
                ArrayList<String> ntpvlabels1 = new ArrayList<>();
                String sb = jsonArray2.get(l).toString();
                String sb1 = sb.replace("\",\"", "&");
                String sb2 = sb1.replace("[\"", "");
                String sb3 = sb2.replace("\"]", "");
                String[] splits = sb3.split("&");
                int k = 0;
                while (k < splits.length) {
                    ntpvlabels1.add(splits[k]);
                    k++;
                }

                ntpvlabels.addAll(ntpvlabels1);
                ntpvlabels21.add(ntpvlabels);
                l++;
            }


            int m = 0;
            while (m < jsonArray3.length()) {
                ArrayList<String> ntpvscores = new ArrayList<>();
                ArrayList<String> ntpvscores1 = new ArrayList<>();
                String sb = jsonArray3.get(m).toString();
                String sb1 = sb.replace("\",\"", "&");
                String sb2 = sb1.replace("[\"", "");
                String sb3 = sb2.replace("\"]", "");
                String[] splits = sb3.split("&");
                int k = 0;
                while (k < splits.length) {
                    ntpvscores1.add(splits[k]);
                    k++;
                }
                ntpvscores.addAll(ntpvscores1);
                ntpvscores21.add(ntpvscores);
                m++;
            }

            ntpv.setLabels(ntpvlabels21);
            ntpv.setName(name1);
            ntpv.setUom(uom1);
            ntpv.setScores(ntpvscores21);

// NRevenue
            NRevenue nRevenue = new NRevenue();

            ArrayList<ArrayList<String>> nRevenuelabels21 = new ArrayList<>();
            ArrayList<ArrayList<String>> nRevenuescores21 = new ArrayList<>();

            JSONArray jsonArray4 = jsonObject5.getJSONArray("labels");
            JSONArray jsonArray5 = jsonObject5.getJSONArray("scores");
            String name2 = String.valueOf(((JSONObject) jsonObject1.get("nRevenue")).get("name"));
            String uom2 = String.valueOf(((JSONObject) jsonObject1.get("nRevenue")).get("uom"));
            int n = 0;
            while (n < jsonArray4.length()) {
                ArrayList<String> nRevenuelabels = new ArrayList<>();
                ArrayList<String> nRevenuelabels1 = new ArrayList<>();
                String sb = jsonArray4.get(n).toString();
                String sb1 = sb.replace("\",\"", "&");
                String sb2 = sb1.replace("[\"", "");
                String sb3 = sb2.replace("\"]", "");
                String[] splits = sb3.split("&");
                int k = 0;
                while (k < splits.length) {
                    nRevenuelabels1.add( splits[k]);
                    k++;
                }

                nRevenuelabels.addAll(nRevenuelabels1);
                nRevenuelabels21.add(nRevenuelabels);
                n++;
            }


            int w = 0;
            while (w < jsonArray5.length()) {
                ArrayList<String> nRevenuescores = new ArrayList<>();
                ArrayList<String> nRevenuescores1 = new ArrayList<>();
                String sb = jsonArray5.get(w).toString();
                String sb1 = sb.replace("\",\"", "&");
                String sb2 = sb1.replace("[\"", "");
                String sb3 = sb2.replace("\"]", "");
                String[] splits = sb3.split("&");
                int k = 0;
                while (k < splits.length) {
                    nRevenuescores1.add(splits[k]);
                    k++;
                }
                nRevenuescores.addAll(nRevenuescores1);
                nRevenuescores21.add(nRevenuescores);
                w++;
            }

            nRevenue.setLabels(nRevenuelabels21);
            nRevenue.setName(name2);
            nRevenue.setUom(uom2);
            nRevenue.setScores(nRevenuescores21);

//NCustLib
            NCustLiab nCustLiab = new NCustLiab();
            ArrayList<ArrayList<String>> nCustliblabels21 = new ArrayList<>();
            ArrayList<ArrayList<String>> nCustlibscores21 = new ArrayList<>();

            JSONArray jsonArray6 = jsonObject4.getJSONArray("labels");
            JSONArray jsonArray7 = jsonObject4.getJSONArray("scores");
            String name3 = String.valueOf(((JSONObject) jsonObject1.get("nCustLiab")).get("name"));
            String uom3 = String.valueOf(((JSONObject) jsonObject1.get("nCustLiab")).get("uom"));
            int q = 0;
            while (q < jsonArray6.length()) {
                ArrayList<String> nCustliblabels = new ArrayList<>();
                ArrayList<String> nCustliblabels1 = new ArrayList<>();
                String sb = jsonArray6.get(q).toString();
                String sb1 = sb.replace("\",\"", "&");
                String sb2 = sb1.replace("[\"", "");
                String sb3 = sb2.replace("\"]", "");
                String[] splits = sb3.split("&");
                int k = 0;
                while (k < splits.length) {
                    nCustliblabels1.add(splits[k]);
                    k++;
                }

                nCustliblabels.addAll(nCustliblabels1);
                nCustliblabels21.add(nCustliblabels);
                q++;
            }


            int r = 0;
            while (r < jsonArray7.length()) {
                ArrayList<String> nCustlibscores = new ArrayList<>();
                ArrayList<String> nCustlibscores1 = new ArrayList<>();
                String sb = jsonArray7.get(r).toString();
                String sb1 = sb.replace("\",\"", "&");
                String sb2 = sb1.replace("[\"", "");
                String sb3 = sb2.replace("\"]", "");
                String[] splits = sb3.split("&");
                int k = 0;
                while (k < splits.length) {
                    nCustlibscores1.add(splits[k]);
                    k++;
                }
                nCustlibscores.addAll(nCustlibscores1);
                nCustlibscores21.add(nCustlibscores);
                r++;
            }

            nCustLiab.setLabels(nCustliblabels21);
            nCustLiab.setName(name3);
            nCustLiab.setUom(uom3);
            nCustLiab.setScores(nCustlibscores21);


//NegativeBal
            NegativeBal negativeBal = new NegativeBal();
            ArrayList<ArrayList<String>> negativeBallabels21 = new ArrayList<>();
            ArrayList<ArrayList<String>> negativeBalscores21 = new ArrayList<>();

            JSONArray jsonArray8 = jsonObject6.getJSONArray("labels");
            JSONArray jsonArray9 = jsonObject6.getJSONArray("scores");
            String name4 = String.valueOf(((JSONObject) jsonObject1.get("NegativeBal")).get("name"));
            String uom4 = String.valueOf(((JSONObject) jsonObject1.get("NegativeBal")).get("uom"));
            int t = 0;
            while (t < jsonArray8.length()) {
                ArrayList<String> negativeBallabels = new ArrayList<>();
                ArrayList<String> negativeBallabels1 = new ArrayList<>();
                String sb = jsonArray8.get(t).toString();
                String sb1 = sb.replace("\",\"", "&");
                String sb2 = sb1.replace("[\"", "");
                String sb3 = sb2.replace("\"]", "");
                String[] splits = sb3.split("&");
                int k = 0;
                while (k < splits.length) {
                    negativeBallabels1.add(splits[k]);
                    k++;
                }

                negativeBallabels.addAll(negativeBallabels1);
                negativeBallabels21.add(negativeBallabels);
                t++;
            }


            int u = 0;
            while (u < jsonArray9.length()) {
                ArrayList<String> negativeBalscores = new ArrayList<>();
                ArrayList<String> negativeBalscores1 = new ArrayList<>();
                String sb = jsonArray9.get(u).toString();
                String sb1 = sb.replace("\",\"", "&");
                String sb2 = sb1.replace("[\"", "");
                String sb3 = sb2.replace("\"]", "");
                String[] splits = sb3.split("&");
                int k = 0;
                while (k < splits.length) {
                    negativeBalscores1.add(splits[k]);
                    k++;
                }
                negativeBalscores.addAll(negativeBalscores1);
                negativeBalscores21.add(negativeBalscores);
                u++;
            }

            negativeBal.setLabels(negativeBallabels21);
            negativeBal.setName(name4);
            negativeBal.setUom(uom4);
            negativeBal.setScores(negativeBalscores21);


            Data data = new Data();
            data.setNTakeRateObject(nTakeRate);
            data.setNTPVObject(ntpv);
            data.setNRevenueObject(nRevenue);
            data.setNCustLiabObject(nCustLiab);
            data.setNegativeBalObject(negativeBal);
            watchBO.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // new Method if onClick method implement on top will notify us warrning
    // so we need to alt+enter this method will create automatically

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.Day7:
                txt_Day7.setBackgroundResource(R.color.colorPrimaryDark);
                txt_Day7.setTextColor(getResources().getColor(R.color.buttonColor));
                txt_Day14.setTextColor(getResources().getColor(R.color.lineOrange));
                txt_Day14.setBackgroundResource(R.color.colorPrimary);
                txt_Day21.setTextColor(getResources().getColor(R.color.lineOrange));
                txt_Day21.setBackgroundResource(R.color.colorPrimary);
                break;

            case R.id.Day14:
                txt_Day7.setBackgroundResource(R.color.colorPrimary);
                txt_Day7.setTextColor(getResources().getColor(R.color.lineOrange));
                txt_Day14.setTextColor(getResources().getColor(R.color.buttonColor));
                txt_Day14.setBackgroundResource(R.color.colorPrimaryDark);
                txt_Day21.setTextColor(getResources().getColor(R.color.lineOrange));
                txt_Day21.setBackgroundResource(R.color.colorPrimary);
                break;

            case R.id.Day21:
                txt_Day7.setBackgroundResource(R.color.colorPrimary);
                txt_Day7.setTextColor(getResources().getColor(R.color.lineOrange));
                txt_Day14.setTextColor(getResources().getColor(R.color.lineOrange));
                txt_Day14.setBackgroundResource(R.color.colorPrimary);
                txt_Day21.setTextColor(getResources().getColor(R.color.buttonColor));
                txt_Day21.setBackgroundResource(R.color.colorPrimaryDark);
                break;

            default:
                txt_Day7.setBackgroundResource(R.color.colorPrimary);
                txt_Day7.setTextColor(getResources().getColor(R.color.lineOrange));
                txt_Day14.setTextColor(getResources().getColor(R.color.buttonColor));
                txt_Day14.setBackgroundResource(R.color.colorPrimaryDark);
                txt_Day21.setTextColor(getResources().getColor(R.color.lineOrange));
                txt_Day21.setBackgroundResource(R.color.colorPrimary);
                break;

        }


    }
}

