package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.config.Config;
import com.taiwan.oomatcher.entity.Implementation;
import com.taiwan.oomatcher.entity.Request.CompanionRequirmentRequest;
import com.taiwan.oomatcher.http.HttpUtils;
import com.taiwan.oomatcher.popup.SelectImplementationPopup;
import com.taiwan.oomatcher.utils.Tools;
import com.taiwan.oomatcher.widget.FlowLayout;
import com.taiwan.oomatcher.widget.RangeSeekBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.taiwan.oomatcher.R.id.occupation;

/**
 * 作 者: huanghuojun
 * 描 述: 发布任务陪伴人员设置
 * 版 本:
 * 创建日期: 2017/12/20 22:45
 */

public class CompanionRequirementActivity extends BaseActivity {

    RelativeLayout mTitleMainRl;
    ImageView mTitleLeftTxt;
    TextView mTitleRightTxt;
    TextView mTitleCenterTxt;
    TextView mOk;
    RadioGroup mSexSelect;
    RadioButton mCrTitleSex1;
    RadioButton mCrTitleSex2;
    RadioButton mCrTitleSex3;
    TextView mAgeMin;
    TextView mAgeMax;
    RangeSeekBar mAge;
    TextView mHeightMin;
    TextView mHeightMax;
    RangeSeekBar mHeight;
    TextView mWeightMin;
    TextView mWeightMax;
    RangeSeekBar mWeight;
    ImageView mLanguageAdd;
    RelativeLayout mOccupation;
    TextView mOccupationContent;
    RelativeLayout mNationality;
    TextView mNationalityContent;
    RelativeLayout mHairColor;
    TextView mHairColorContent;
    RelativeLayout mEyeColor;
    TextView mEyeColorContent;
    RadioGroup mAuthenticationSelect;
    RadioButton mAuthentication1;
    RadioButton mAuthentication12;
    private Context mContext;
    private RelativeLayout tempRl;
    private CompanionRequirmentRequest mItem = null;
    private List<Implementation> occupationDataList;
    private List<Implementation> nationalityDataList;
    private List<Implementation> hairColorDataList;
    private List<Implementation> eyeColorDataList;
    private Map<String, String> labelMap = new HashMap<String, String>();
    private boolean isFrist = true;
    private boolean isSelectAdd = true;
    private FlowLayout mFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        mItem = (CompanionRequirmentRequest) getIntent().getSerializableExtra("item");
        System.out.println(mItem == null);
        mAge.setRange(18, 40);
        mHeight.setRange(145, 230);
        mWeight.setRange(35, 200);
        if(mItem == null){
            isFrist = true;
            initDefaultValue();
        }else{
            isFrist = false;
            getAll();
            initValue();
        }
        mAge.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
                int minInt = (int) min;
                int maxInt = (int) max;
                mItem.setMaxAge(maxInt);
                mItem.setMinAge(minInt);
                mAgeMin.setText(minInt + "");
                mAgeMax.setText(maxInt + "");
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
        mHeight.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
                int minInt = (int) min;
                int maxInt = (int) max;
                mItem.setMaxHeight(maxInt);
                mItem.setMinHeight(minInt);
                mHeightMin.setText(minInt + "cm");
                mHeightMax.setText(maxInt + "cm");
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
        mWeight.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
                int minInt = (int) min;
                int maxInt = (int) max;
                mItem.setMaxWeight(maxInt);
                mItem.setMinWeight(minInt);
                mWeightMin.setText(minInt + "kg");
                mWeightMax.setText(maxInt + "kg");
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
    }

    public void addLabel(String name, String code) {
        if (labelMap.containsKey(name)) {
            return;
        }
        labelMap.put(name, code);
        TextView mTextView = new TextView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 10, 5);
        mTextView.setLayoutParams(params);
        mTextView.setBackgroundResource(R.drawable.btn_circle_shape);
        mTextView.setText(name + "  ×");
        mTextView.setTextColor(Color.parseColor("#ffffff"));
        mTextView.setTextSize(12f);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
                String tsString = textView.getText().toString();
                labelMap.remove(tsString.subSequence(0, tsString.length() - "  ×".length()));
                mFlowLayout.removeView(v);
            }
        });
        mFlowLayout.addView(mTextView);
    }

    private void initDefaultValue(){
        mItem = new CompanionRequirmentRequest();
        mAgeMin.setText("18");
        mAgeMax.setText("25");
        mAge.setValue(18, 25);
        mHeightMin.setText("145cm");
        mHeightMax.setText("230cm");
        mHeight.setValue(145, 230);
        mWeightMin.setText("35kg");
        mWeightMax.setText("200kg");
        mWeight.setValue(35, 200);
    }
    private void initValue(){
        if(mItem.getGenderCode().equals("1")){
            mCrTitleSex2.setChecked(true);
        }else if(mItem.getGenderCode().equals("2")){
            mCrTitleSex1.setChecked(true);
        }else{
            mCrTitleSex3.setChecked(true);
        }
        if(mItem.getAuthFlag() == 0){
            mAuthentication1.setChecked(true);
        }else{
            mAuthentication12.setChecked(true);
        }
        mAge.setValue(mItem.getMinAge(),mItem.getMaxAge());
        mHeight.setValue(mItem.getMinHeight(),mItem.getMaxHeight());
        mWeight.setValue(mItem.getMinWeight(),mItem.getMaxWeight());
        mAgeMin.setText(mItem.getMinAge()+"");
        mAgeMax.setText(mItem.getMaxAge()+"");
        mHeightMin.setText(mItem.getMinHeight()+"cm");
        mHeightMax.setText(mItem.getMaxHeight()+"cm");
        mWeightMin.setText(mItem.getMinWeight()+"kg");
        mWeightMax.setText(mItem.getMaxWeight()+"kg");
        if(occupationDataList != null){
            for(int i = 0; i < occupationDataList.size(); i++){
                Implementation temp = occupationDataList.get(i);
                if(temp.getDicItemCode().equals(mItem.getOccupationCode())){
                    mOccupationContent.setText(temp.getDicName());
                    break;
                }
            }
        }
        if(nationalityDataList != null){
            for(int i = 0; i < nationalityDataList.size(); i++){
                Implementation temp = nationalityDataList.get(i);
                if(temp.getDicItemCode().equals(mItem.getNationalityCode())){
                    mNationalityContent.setText(temp.getDicName());
                    break;
                }
            }
        }
        if(hairColorDataList != null){
            for(int i = 0; i < hairColorDataList.size(); i++){
                Implementation temp = hairColorDataList.get(i);
                if(temp.getDicItemCode().equals(mItem.getHairColorCode())){
                    mHairColorContent.setText(temp.getDicName());
                    break;
                }
            }
        }
        if(eyeColorDataList != null){
            for(int i = 0; i < eyeColorDataList.size(); i++){
                Implementation temp = eyeColorDataList.get(i);
                if(temp.getDicItemCode().equals(mItem.getEyeColorCode())){
                    mEyeColorContent.setText(temp.getDicName());
                    break;
                }
            }
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_companion_requirement);
        assignViews();
    }

    public void backClick(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    protected void init() {
    }

    private void getAll(){
        getValue("occupation", false);
        getValue("occupation", false);
        getValue("nationality", false);
        getValue("hairColor", false);
    }

    @Override
    protected void setListeners() {
        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(labelMap.size() == 0){
                    showToast(R.string.error_new_task_language);
                    return;
                }
                String languages = "";
                for (Map.Entry<String, String> entry : labelMap.entrySet()) {
                    languages += entry.getValue()+",";
                }
                mItem.setLanguageCode(languages.substring(0, languages.length() - 1));
                Intent intent = new Intent();
                intent.putExtra("item", mItem);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        mTitleRightTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("item", mItem);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mOccupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(occupationDataList == null)
                    getValue("occupation", true);
                else
                    show("occupation", occupationDataList);
            }
        });
        mNationality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelectAdd = false;
                if(nationalityDataList == null)
                    getValue("nationality", true);
                else
                    show("nationality", nationalityDataList);
            }
        });
        mHairColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hairColorDataList == null)
                    getValue("hairColor", true);
                else
                    show("hairColor", hairColorDataList);
            }
        });
        mEyeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eyeColorDataList == null)
                    getValue("eyeColor", true);
                else
                    show("eyeColor", eyeColorDataList);
            }
        });
        mLanguageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelectAdd = true;
                if(nationalityDataList == null)
                    getValue("nationality", true);
                else
                    show("nationality", nationalityDataList);
            }
        });
    }

    @Override
    protected void widgetClick(View v) {

    }

    private void getValue(final String dicCode, final boolean flag) {
        //occupation.职业;hairColor.头发;eyeColor.眼睛;nationality.国家/国籍:city.城市
        JSONObject json = new JSONObject();
        json.put("dicCode", dicCode);
        HttpUtils.sendGetSign("common/dictionary/getDic", json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String str) {
                List<Implementation> dataList = JSONArray.parseArray(str, Implementation.class);
                if(dicCode.equals("occupation")){
                    occupationDataList = dataList;
                } else if(dicCode.equals("nationality")){
                    nationalityDataList = dataList;
                } else if(dicCode.equals("hairColor")){
                    hairColorDataList = dataList;
                } else if(dicCode.equals("eyeColor")){
                    eyeColorDataList = dataList;
                }
                if(flag)
                    show(dicCode, dataList);
            }

            @Override
            public void onFailure(Exception e, String msg) {

            }
        });
    }

    private void show(String dicCode, List<Implementation> dataList){
        SelectImplementationPopup mPopup = new SelectImplementationPopup(dicCode, temp, dataList, mContext);
        if(dicCode.equals("occupation")){
            mPopup.show(mOccupation);
        } else if(dicCode.equals("nationality")){
            if(isSelectAdd){
                mPopup.show(mLanguageAdd);
            }else{
                mPopup.show(mNationality);
            }
        } else if(dicCode.equals("hairColor")){
            mPopup.show(mHairColor);
        } else if(dicCode.equals("eyeColor")){
            mPopup.show(mEyeColor);
        }
    }

    private SelectImplementationPopup.OnSelectImplListener temp = new SelectImplementationPopup.OnSelectImplListener() {
        @Override
        public void selected(String dicCode, Implementation item) {
            if(dicCode.equals("occupation")){
                mOccupationContent.setText(item.getDicName());
                mItem.setOccupationCode(item.getDicItemCode());
            } else if(dicCode.equals("nationality")){
                if(isSelectAdd){
                    addLabel(item.getDicName(),item.getDicItemCode());
                }else{
                    mNationalityContent.setText(item.getDicName());
                    mItem.setNationalityCode(item.getDicItemCode());
                }
            } else if(dicCode.equals("hairColor")){
                mHairColorContent.setText(item.getDicName());
                mItem.setHairColorCode(item.getDicItemCode());
            } else if(dicCode.equals("eyeColor")){
                mEyeColorContent.setText(item.getDicName());
                mItem.setEyeColorCode(item.getDicItemCode());
            }
        }
    };

    private void assignViews() {
        mTitleMainRl = (RelativeLayout) findViewById(R.id.title_main_rl);
        mTitleLeftTxt = (ImageView) findViewById(R.id.title_left_txt);
        mTitleRightTxt = (TextView) findViewById(R.id.title_right_txt);
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
        mOk = (TextView) findViewById(R.id.ok);
        mSexSelect = (RadioGroup) findViewById(R.id.sex_select);
        mCrTitleSex1 = (RadioButton) findViewById(R.id.cr_title_sex1);
        mCrTitleSex2 = (RadioButton) findViewById(R.id.cr_title_sex2);
        mCrTitleSex3 = (RadioButton) findViewById(R.id.cr_title_sex3);
        mAgeMin = (TextView) findViewById(R.id.age_min);
        mAgeMax = (TextView) findViewById(R.id.age_max);
        mAge = (RangeSeekBar) findViewById(R.id.age);
        mHeightMin = (TextView) findViewById(R.id.height_min);
        mHeightMax = (TextView) findViewById(R.id.height_max);
        mHeight = (RangeSeekBar) findViewById(R.id.height);
        mWeightMin = (TextView) findViewById(R.id.weight_min);
        mWeightMax = (TextView) findViewById(R.id.weight_max);
        mWeight = (RangeSeekBar) findViewById(R.id.weight);
        mLanguageAdd = (ImageView) findViewById(R.id.language_add);
        mOccupation = (RelativeLayout) findViewById(occupation);
        mOccupationContent = (TextView) findViewById(R.id.occupation_content);
        mNationality = (RelativeLayout) findViewById(R.id.nationality);
        mNationalityContent = (TextView) findViewById(R.id.nationality_content);
        mHairColor = (RelativeLayout) findViewById(R.id.hair_color);
        mHairColorContent = (TextView) findViewById(R.id.hair_color_content);
        mEyeColor = (RelativeLayout) findViewById(R.id.eye_color);
        mEyeColorContent = (TextView) findViewById(R.id.eye_color_content);
        mAuthenticationSelect = (RadioGroup) findViewById(R.id.authentication_select);
        mAuthentication1 = (RadioButton) findViewById(R.id.authentication1);
        mAuthentication12 = (RadioButton) findViewById(R.id.authentication12);
        mFlowLayout = (FlowLayout) findViewById(R.id.flowLayout);
    }

}
