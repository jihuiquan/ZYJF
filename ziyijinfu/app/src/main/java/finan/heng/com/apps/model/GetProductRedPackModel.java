package finan.heng.com.apps.model;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/20 16:14
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class GetProductRedPackModel implements Serializable {




    private List<BonusesByTypeBean> bonusesByType;

    public List<BonusesByTypeBean> getBonusesByType() {
        return bonusesByType;
    }

    public void setBonusesByType(List<BonusesByTypeBean> bonusesByType) {
        this.bonusesByType = bonusesByType;
    }

    public static class BonusesByTypeBean {
        private String          id;
        private String          userId;
        private String          title;
        private String          type;
        private String          source;
        private String          bonuses;
        private String          prerequisite;
        private String          serviceConditions;
        private String          timeLimit;
        private String          endTime;
        private String          serviceConditionsLimitType;//0代表天，1代表月
        private String          serviceConditionsLimitValue;
        private String balance;//余额

        public float getFloatBalance(){
            float a = 0;
            try {
                a = Float.parseFloat(balance);
            }catch (Exception e){
                e.printStackTrace();
            }
            return a;

        }
        public String getServiceConditionsLimitType() {
            return serviceConditionsLimitType;
        }

        public void setServiceConditionsLimitType(String serviceConditionsLimitType) {
            this.serviceConditionsLimitType = serviceConditionsLimitType;
        }

        public String getServiceConditionsLimitValue() {
            return serviceConditionsLimitValue;
        }

        public void setServiceConditionsLimitValue(String serviceConditionsLimitValue) {
            this.serviceConditionsLimitValue = serviceConditionsLimitValue;
        }

        public boolean isSelect;
        private String receiveTime;
        private String          status;

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getBonuses() {
            return bonuses;
        }
        public float getBonusesFloat() {
            float value = 0;
            try {
                value = Float.parseFloat(bonuses);
            }catch (Exception e){
                e.printStackTrace();
            }

            return value;
        }
        public void setBonuses(String bonuses) {
            this.bonuses = bonuses;
        }



        public void setPrerequisite(String prerequisite) {
            this.prerequisite = prerequisite;
        }

        public String getServiceConditions() {
            return serviceConditions;
        }

        public void setServiceConditions(String serviceConditions) {
            this.serviceConditions = serviceConditions;
        }

        public String getTimeLimit() {
            return timeLimit;
        }

        public void setTimeLimit(String timeLimit) {
            this.timeLimit = timeLimit;
        }

        public String getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(String receiveTime) {
            this.receiveTime = receiveTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class ReceiveTimeBean {
            private String nanos;
            private String time;
            private String minutes;
            private String seconds;
            private String hours;
            private String month;
            private String year;
            private String timezoneOffset;
            private String day;
            private String date;

            public String getNanos() {
                return nanos;
            }

            public void setNanos(String nanos) {
                this.nanos = nanos;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getMinutes() {
                return minutes;
            }

            public void setMinutes(String minutes) {
                this.minutes = minutes;
            }

            public String getSeconds() {
                return seconds;
            }

            public void setSeconds(String seconds) {
                this.seconds = seconds;
            }

            public String getHours() {
                return hours;
            }

            public void setHours(String hours) {
                this.hours = hours;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public String getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(String timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
