package com.example.mouse.myneu.View;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mouse.myneu.R;
import com.example.mouse.myneu.Util.Constant;
import com.example.mouse.myneu.Util.SPUtils;
import com.example.mouse.myneu.bean.Position;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobInfoFragment extends BaseFragment {

    private ImageView posiDetailPostImg;
    private TextView posiDetailPostName;
    private TextView posiDetailPersonNums;
    private TextView posiDetailCourseNums;
    private TextView posiDetailCourseHours;
    private TextView posiDetailPostDesc;
    private Button posiDetailBegin;

    private Position position;
    private String courseName = "";
    private int courseId = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_job_info;
    }

    @Override
    protected void initViews(View view) {
        posiDetailPostImg = findView(R.id.posi_detail_postImg);
        posiDetailPostName = findView(R.id.posi_detail_postName);
        posiDetailPersonNums = findView(R.id.posi_detail_personNums);
        posiDetailCourseNums = findView(R.id.posi_detail_courseNums);
        posiDetailCourseHours = findView(R.id.posi_detail_courseHours);
        posiDetailPostDesc = findView(R.id.posi_detail_postDesc);
        posiDetailBegin = findView(R.id.posi_detail_begin);
    }

    @Override
    protected void initData() {
        Intent intent = getActivity().getIntent();
        this.position = (Position) intent.getSerializableExtra("bean_position");
        if (this.position != null) {
            posiDetailPostName.setText(position.getPostName());
            posiDetailPersonNums.setText("已有" + position.getPersonNums() + "人学习本课程");
            posiDetailCourseNums.setText("总课程：" + position.getCourseNums() + "门");
            posiDetailCourseHours.setText("总课时：" + position.getCourseHours() + "h");
            posiDetailPostDesc.setText("岗位描述：\n" + position.getPostDesc());
            ImageLoader.getInstance().displayImage(position.getPostUrl(), posiDetailPostImg);
        }

        Map<String, String> map = new HashMap();
        map.put("Cookie", (String) SPUtils.get(getContext(), "jsessionid", ""));

        OkHttpUtils
                .get()
                .url(Constant.URL_POSITION_COURSES)
                .headers(map)
                .addParams("postid", String.valueOf(position.getPostId()))
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return response.body().string();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("",e.getMessage());
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        String json = (String) response;

                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            JSONArray postStageResList = jsonObject.getJSONArray("postStageResList");
                            JSONObject object = postStageResList.getJSONObject(0);
                            JSONArray childStageList = object.getJSONArray("childStageList");
                            JSONObject childObject = childStageList.getJSONObject(0);
                            JSONArray childStageResList = childObject.getJSONArray("childStageResList");
                            JSONObject childStageObject = childStageResList.getJSONObject(0);

                            courseName = childStageObject.getString("resName");
                            courseId = childStageObject.getInt("resId");

                        } catch (JSONException e) {
                            Log.e("",e.getMessage());
                        }

                    }
                });

    }

    @Override
    protected void initEvents(View view) {

    }

}
