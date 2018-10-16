package com.example.qiaoxian.mycoursewithasynclistview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.myListView);
        new MyRequestData().execute();
    }

    public class MyRequestData extends AsyncTask<Void,Integer,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return request("http://www.imooc.com/api/teacher?type=2&page=1");
        }

        private String request(String urlString) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                String responseMessage = httpURLConnection.getResponseMessage();
                String result = null;
                if(responseCode == HttpURLConnection.HTTP_OK){
                    InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection
                            .getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while((line = bufferedReader.readLine())!=null){
                        stringBuilder.append(line);
                    }

                    result =  stringBuilder.toString();
                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            LessonResult lessonResult = new LessonResult();
            try {
                JSONObject jsonObject = new JSONObject(result);
                lessonResult.setmStatus(jsonObject.getInt("status"));
                lessonResult.setmMessage(jsonObject.getString("msg"));
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                List<LessonResult.LessonDetail> lessonDetailList = new ArrayList<>();

                for(int i = 0;i<jsonArray.length();i++){
                    LessonResult.LessonDetail lessonDetail = new LessonResult.LessonDetail();
                    JSONObject tempJOBJ = (JSONObject)jsonArray.get(i);
                    lessonDetail.setmName(tempJOBJ.getString("name"));
                    lessonDetailList.add(lessonDetail);
                }
                lessonResult.setmListLessonDetail(lessonDetailList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            listView.setAdapter(new MyAdapter(lessonResult.getmListLessonDetail(),MainActivity.this));
        }
    }
}
