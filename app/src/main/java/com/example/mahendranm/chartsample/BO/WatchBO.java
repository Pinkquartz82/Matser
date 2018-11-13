package com.example.mahendranm.chartsample.BO;

import java.util.ArrayList;

public class WatchBO {

        private String status;
        ArrayList < Object > company_code = new ArrayList < Object > ();
        private String metric_name;
        private String week_day;
        ArrayList < Object > country_code = new ArrayList < Object > ();
        private float number_of_days;
        Data DataObject;


        // Getter Methods

        public String getStatus() {
            return status;
        }

        public String getMetric_name() {
            return metric_name;
        }

        public String getWeek_day() {
            return week_day;
        }

        public float getNumber_of_days() {
            return number_of_days;
        }

        public Data getData() {
            return DataObject;
        }

        // Setter Methods

        public void setStatus(String status) {
            this.status = status;
        }

        public void setMetric_name(String metric_name) {
            this.metric_name = metric_name;
        }

        public void setWeek_day(String week_day) {
            this.week_day = week_day;
        }

        public void setNumber_of_days(float number_of_days) {
            this.number_of_days = number_of_days;
        }

        public void setData(Data dataObject) {
            this.DataObject = dataObject;
        }


}






