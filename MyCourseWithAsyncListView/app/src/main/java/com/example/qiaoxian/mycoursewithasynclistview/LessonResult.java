package com.example.qiaoxian.mycoursewithasynclistview;

import java.util.ArrayList;
import java.util.List;

public class LessonResult {
    private int mStatus;
    private String mMessage;
    private List<LessonDetail> mListLessonDetail = new ArrayList<>();

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public List<LessonDetail> getmListLessonDetail() {
        return mListLessonDetail;
    }

    public void setmListLessonDetail(List<LessonDetail> mListLessonDetail) {
        this.mListLessonDetail = mListLessonDetail;
    }




    public static class LessonDetail{
        private String mName;
        private String mDescription;

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public String getmDescription() {
            return mDescription;
        }

        public void setmDescription(String mDescription) {
            this.mDescription = mDescription;
        }
    }

}
