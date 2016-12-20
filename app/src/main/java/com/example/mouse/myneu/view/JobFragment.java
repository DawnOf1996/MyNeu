package com.example.mouse.myneu.View;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.mouse.myneu.Adapter.JobAdapter;
import com.example.mouse.myneu.JobDetailActivity;
import com.example.mouse.myneu.R;
import com.example.mouse.myneu.Util.Constant;
import com.example.mouse.myneu.Util.SPUtils;
import com.example.mouse.myneu.bean.Position;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobFragment extends BaseFragment {
    private RecyclerView rcJob;
    private JobAdapter mAdapter;
    private List<Position> mData = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_job;
    }

    @Override
    protected void initViews(View view) {
        rcJob = findView(R.id.job_course);
        rcJob.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {

        Map<String, String> map = new HashMap();
        map.put("Cookie", (String) SPUtils.get(getContext(), "jsessionid", ""));

        OkHttpUtils
                .post()
                .url(Constant.URL_POSITION_LIST)
                .headers(map)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return response.body().string();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        mData.clear();
                        String json = (String) response;


                        try {
                            JSONObject object = new JSONObject(json);
                            JSONArray indexPositionList = object.getJSONArray("indexPositionList");
                            if (indexPositionList == null || indexPositionList.length()==0) {
                                toast("请重新登录");
                                return;
                            }
                            for (int i = 0; i < indexPositionList.length(); i++) {
                                JSONObject positionObject = indexPositionList.getJSONObject(i);
                                Position position = new Position();
                                position.setVideoNums(positionObject.getInt("videoNums"));
                                position.setStudy(positionObject.getInt("isStudy"));
                                position.setCourseNums(positionObject.getInt("courseNums"));
                                position.setPostName(positionObject.getString("postName"));
                                position.setPostDesc(positionObject.getString("postDesc"));
                                position.setPostUrl(positionObject.getString("postUrl"));
                                position.setCourseHours(positionObject.getInt("courseHours"));
                                position.setPersonNums(positionObject.getInt("personNums"));
                                position.setStudyDays(positionObject.getString("studyDays"));
                                position.setTestNums(positionObject.getInt("testNums"));
                                position.setPostId(positionObject.getInt("postId"));
                                mData.add(position);
                            }
                            mAdapter = new JobAdapter(getContext(), mData);
                            Log.d("","获得岗位数据"+mData.size()+"条");
                            rcJob.setAdapter(mAdapter);

                            mAdapter.setOnItemClickListener(new JobAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Intent intent = new Intent(getActivity(), JobDetailActivity.class);
                                    intent.putExtra("bean_position", mData.get(position));
                                    startActivity(intent);
                                }

                                @Override
                                public void onItemLongClick(View view, int position) {}
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


    }

    @Override
    protected void initEvents(View view) {

    }
}
