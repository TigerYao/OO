package com.taiwan.oomatcher.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseApplication;
import com.taiwan.oomatcher.widget.SelectableRoundedImageView;

import java.util.List;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/28 16:08
 */

public class DialogUtils{
    // 多选对话框
    public final static int SELECT_DIALOG = 1;
    // 单选对话框
    public final static int RADIO_DIALOG = 2;

    private static Dialog mLoadingDialog = null;


    /**
     * 显示加载对话框
     */
    public static void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new Dialog(BaseApplication.getInstance(), R.style.dialog);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.setContentView(R.layout.dialog_loading);
        }
        mLoadingDialog.show();
    }
    public static android.app.Dialog showListDialog(Context context, String title, String[] items, final DialogItemClickListener dialogClickListener) {
        final android.app.Dialog dialog = new android.app.Dialog(context, R.style.dialog_popup_list);
        dialog.setCancelable(true);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_pop, null);
        dialog.setContentView(view);
        if (Tools.isEmpty(title)) {
            ((TextView) view.findViewById(R.id.title)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.tv_line)).setVisibility(View.GONE);
        } else {
            ((TextView) view.findViewById(R.id.title)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.tv_line)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.title)).setText(title);
        }
        //根据items动态创建
        LinearLayout parent = (LinearLayout) view.findViewById(R.id.dialogLayout);
        parent.removeAllViews();
        int length = items.length;
        for (int i = 0; i < items.length; i++) {
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(-1, -2);
            params1.rightMargin = 1;
            final TextView tv = new TextView(context);
            tv.setLayoutParams(params1);
            tv.setTextSize(18);
            tv.setText(items[i]);
            tv.setTextColor(context.getResources().getColor(R.color.text_blue));
            int pad = context.getResources().getDimensionPixelSize(R.dimen.dp_10);
            tv.setPadding(pad, pad, pad, pad);
            tv.setSingleLine(true);
            tv.setGravity(Gravity.CENTER);
            if (Tools.isEmpty(title)) {
                if (length == 1) {
                    tv.setBackgroundResource(R.drawable.menudialog_single_selector);
                } else {
                    if (i == 0) {
                        tv.setBackgroundResource(R.drawable.menudialog_top_selector);
                    } else if (i != length - 1) {
                        tv.setBackgroundResource(R.drawable.menudialog_center_selector);
                    } else {
                        tv.setBackgroundResource(R.drawable.menudialog_bottom_selector);
                    }
                }
            } else {
                if (i != length - 1) {
                    tv.setBackgroundResource(R.drawable.menudialog_center_selector);
                } else {
                    tv.setBackgroundResource(R.drawable.menudialog_bottom_selector);
                }
            }

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    dialog.dismiss();
                    dialogClickListener.confirm(tv.getText().toString());
                }
            });
            parent.addView(tv);
            if (i != length - 1) {
                TextView divider = new TextView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, (int) 1);
                divider.setLayoutParams(params);
                divider.setBackgroundResource(android.R.color.transparent);
                parent.addView(divider);
            }
        }
        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        lp.width = ScreenUtils.getScreenWidth(context);
        mWindow.setGravity(Gravity.BOTTOM);
        //添加动画
        mWindow.setWindowAnimations(R.style.dialog_popup_anim);
        mWindow.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return dialog;
    }

    public static android.app.Dialog showVipDialog(Context context, final DialogItemClickListener dialogClickListener) {
        final android.app.Dialog dialog = new android.app.Dialog(context, R.style.dialog_popup_list);
        dialog.setCancelable(true);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_vip, null);
        dialog.setContentView(view);
        final RelativeLayout mTypeRBtn1 = (RelativeLayout) view.findViewById(R.id.type_rBtn1);
        final RelativeLayout mTypeRBtn2 = (RelativeLayout) view.findViewById(R.id.type_rBtn2);
        final RelativeLayout mTypeRBtn3 = (RelativeLayout) view.findViewById(R.id.type_rBtn3);
        final RelativeLayout mTypeRBtn4 = (RelativeLayout) view.findViewById(R.id.type_rBtn4);
        TextView mNoTxt = (TextView) view.findViewById(R.id.no_txt);
        TextView mYesTxt = (TextView) view.findViewById(R.id.yes_txt);
        mTypeRBtn1.setSelected(true);
        mTypeRBtn2.setSelected(false);
        mTypeRBtn3.setSelected(false);
        mTypeRBtn4.setSelected(false);
        mTypeRBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypeRBtn1.setSelected(true);
                mTypeRBtn2.setSelected(false);
                mTypeRBtn3.setSelected(false);
                mTypeRBtn4.setSelected(false);
            }
        });
        mTypeRBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypeRBtn1.setSelected(false);
                mTypeRBtn2.setSelected(true);
                mTypeRBtn3.setSelected(false);
                mTypeRBtn4.setSelected(false);
            }
        });
        mTypeRBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypeRBtn1.setSelected(false);
                mTypeRBtn2.setSelected(false);
                mTypeRBtn3.setSelected(true);
                mTypeRBtn4.setSelected(false);
            }
        });
        mTypeRBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypeRBtn1.setSelected(false);
                mTypeRBtn2.setSelected(false);
                mTypeRBtn3.setSelected(false);
                mTypeRBtn4.setSelected(true);
            }
        });
        view.findViewById(R.id.no_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.yes_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                dialogClickListener.confirm("");
            }
        });
        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        lp.width = ScreenUtils.getScreenWidth(context);
        mWindow.setGravity(Gravity.BOTTOM);
        //添加动画
        mWindow.setWindowAnimations(R.style.dialog_popup_anim);
        mWindow.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return dialog;
    }

    /**
     * 关闭加载对话框
     */
    public static void closeLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.cancel();
        }
    }

    public static android.app.Dialog showRadioDialog(Context context, String content, final DialogClickListener dialogClickListener) {
        return ShowDialog(context, RADIO_DIALOG, null, content, null, null, null, dialogClickListener);
    }

    public static android.app.Dialog showRadioDialog(Context context, String content, String radioStr, final DialogClickListener dialogClickListener) {
        return ShowDialog(context, RADIO_DIALOG, null, content, null, null, radioStr, dialogClickListener);
    }

    /**
     * 弹出网页dialog
     *
     * @param context
     * @param title
     * @param url
     */
    public static void showWebViewDialog(Context context, String title, String url) {
//        View view = LayoutInflater.from(context).inflate(R.layout.activity_preview_rule, null);
//        WebView webView = (WebView) view.findViewById(R.id.preview_webview);
//        WebSettings mWebSettings = webView.getSettings();
//        mWebSettings.setJavaScriptEnabled(true); // 允许执行js
//        webView.loadUrl(url); // 加载赞助规则
//        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(title).setView(view)
//                .setCancelable(false).setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//        builder.create().show(); // 开始展示赞助规则
    }




    /**
     * 创建对话框
     *
     * @param context
     * @param title               标题，为NULL则不显示
     * @param content             对话框内容
     * @param leftBtnStr          对话框按钮左边文本内容
     * @param rightBtnStr         对话框按钮右边文本内容
     * @param dialogClickListener 返回监听
     * @return
     */
    public static android.app.Dialog showDialog(Context context, String title, String content, String leftBtnStr, String rightBtnStr, final DialogClickListener dialogClickListener) {
        return ShowDialog(context, SELECT_DIALOG, title, content, leftBtnStr, rightBtnStr, null, dialogClickListener);
    }

    /**
     * 创建对话框
     *
     * @param context
     * @param content             对话框内容
     * @param dialogClickListener 返回监听
     * @return
     */
    public static android.app.Dialog showBoxDialog(Context context, String content, final DialogClickListener dialogClickListener) {
        return ShowDialog(context, SELECT_DIALOG, null, content, null, null, null, dialogClickListener);
    }

    /**
     * 创建对话框
     *
     * @param context
     * @param contentResId        对话框内容
     * @param dialogClickListener 返回监听
     * @return
     */
    public static android.app.Dialog showBoxDialog(Context context, int contentResId, int leftStrResId, int rightStrResId, final DialogClickListener dialogClickListener) {
        return ShowDialog(context, SELECT_DIALOG, "", context.getString(contentResId), context.getString(leftStrResId), context.getString(rightStrResId), null, dialogClickListener);
    }

    /**
     * 创建对话框
     *
     * @param context
     * @param titleResId          标题，为NULL则不显示
     * @param contentResId        对话框内容
     * @param dialogClickListener 返回监听
     * @return
     */
    public static android.app.Dialog showBoxDialog(Context context, int titleResId, int contentResId, int leftStrResId, int rightStrResId, final DialogClickListener dialogClickListener) {
        return ShowDialog(context, SELECT_DIALOG, context.getString(titleResId), context.getString(contentResId), context.getString(leftStrResId), context.getString(rightStrResId), null, dialogClickListener);
    }

    /**
     * 创建对话框
     *
     * @param context
     * @param dialogClickListener 返回监听
     * @return
     */
    public static android.app.Dialog showBoxDialog(Context context, String contentStr, int leftStrResId, int rightStrResId, final DialogClickListener dialogClickListener) {
        return ShowDialog(context, SELECT_DIALOG, "", contentStr, context.getString(leftStrResId), context.getString(rightStrResId), null, dialogClickListener);
    }

    /**
     * 创建对话框
     *
     * @param context
     * @param title               标题，为NULL则不显示
     * @param content             对话框内容
     * @param dialogClickListener 返回监听
     * @return
     */
    public static android.app.Dialog showBoxDialog(Context context, String title, String content, final DialogClickListener dialogClickListener) {
        return ShowDialog(context, SELECT_DIALOG, title, content, null, null, null, dialogClickListener);
    }

    /**
     * 创建对话框
     *
     * @param context
     * @param titleResId          标题，为NULL则不显示
     * @param contentResId        对话框内容
     * @param dialogClickListener 返回监听
     * @return
     */
    public static android.app.Dialog showBoxDialog(Context context, int titleResId, int contentResId, final DialogClickListener dialogClickListener) {
        return ShowDialog(context, SELECT_DIALOG, context.getString(titleResId), context.getString(contentResId), null, null, null, dialogClickListener);
    }

    public static Dialog showUpdateDialog(Context context, String version, String size, String content, final DialogClickListener dialogClickListener){
        Animation startAnimation = AnimationUtils.loadAnimation(context, R.anim.modal_in);
        final Animation endAnimation = AnimationUtils.loadAnimation(context, R.anim.modal_out);
        final android.app.Dialog dialog = new android.app.Dialog(context, R.style.dialog_popup_list);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_update, null);
        dialog.setContentView(view);

        ((TextView) view.findViewById(R.id.version_txt)).setText(context.getString(R.string.update_version, version));
        ((TextView) view.findViewById(R.id.size_txt)).setText(context.getString(R.string.update_size, size));
        ((TextView) view.findViewById(R.id.content_txt)).setText(content);

        view.findViewById(R.id.update_btn_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                view.startAnimation(endAnimation);
                if (dialogClickListener != null) {
                    dialogClickListener.rightClick();
                }
            }
        });

        view.findViewById(R.id.close_imgView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                view.startAnimation(endAnimation);
                if (dialogClickListener != null) {
                    dialogClickListener.leftClick();
                }
            }
        });

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    view.startAnimation(endAnimation);
                    return true;
                }
                return false;
            }
        });
        endAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dialog.dismiss();
            }
        });
        view.startAnimation(startAnimation);
        Window mWindow = dialog.getWindow();
        dialog.show();

        return dialog;
    }

    /**
     * 从底部弹出选择item
     *
     * @param context
     * @param title
     * @param items
     * @param dialogClickListener
     * @return
     */


    /**
     * 单选、多选对话框
     *
     * @param context
     * @param dialogType
     * @param title
     * @param toasts
     * @param leftBtnStr
     * @param rightBtnStr
     * @param radioStr            单选按钮文本
     * @param dialogClickListener
     * @return
     */
    private static android.app.Dialog ShowDialog(Context context, int dialogType, String title, String toasts, String leftBtnStr, String rightBtnStr, String radioStr, final DialogClickListener dialogClickListener) {
        Animation startAnimation = AnimationUtils.loadAnimation(context, R.anim.modal_in);
        final Animation endAnimation = AnimationUtils.loadAnimation(context, R.anim.modal_out);
        final android.app.Dialog dialog = new android.app.Dialog(context, R.style.dialog_popup_list);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_box, null);
        dialog.setContentView(view);
        if (Tools.isEmpty(title)) {
            view.findViewById(R.id.point).setVisibility(View.GONE);
        } else {
            ((TextView) view.findViewById(R.id.point)).setText(title);
        }
        if (dialogType == RADIO_DIALOG) {
            view.findViewById(R.id.cancel).setVisibility(View.GONE);
            view.findViewById(R.id.ok).setVisibility(View.GONE);
            view.findViewById(R.id.divider).setVisibility(View.GONE);
            view.findViewById(R.id.text_radio).setVisibility(View.VISIBLE);
            if (!Tools.isEmpty(radioStr)) {
                ((TextView) view.findViewById(R.id.text_radio)).setText(radioStr);
            }
        } else {
            if (!Tools.isEmpty(leftBtnStr)) {
                ((TextView) view.findViewById(R.id.cancel)).setText(leftBtnStr);
            }
            if (!Tools.isEmpty(rightBtnStr)) {
                ((TextView) view.findViewById(R.id.ok)).setText(rightBtnStr);
            }
        }
        ((TextView) view.findViewById(R.id.toast)).setText(toasts);
        view.findViewById(R.id.text_radio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                view.startAnimation(endAnimation);
                if (dialogClickListener != null) {
                    dialogClickListener.rightClick();
                }
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                view.startAnimation(endAnimation);
                if (dialogClickListener != null) {
                    dialogClickListener.leftClick();
                }
            }
        });
        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                view.startAnimation(endAnimation);
                if (dialogClickListener != null) {
                    dialogClickListener.rightClick();
                }
            }
        });
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    view.startAnimation(endAnimation);
                    return true;
                }
                return false;
            }
        });
        endAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dialog.dismiss();
            }
        });
        view.startAnimation(startAnimation);
        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {//横屏
            lp.width = ScreenUtils.getScreenHeight(context) / 10 * 8;
        } else {
            lp.width = ScreenUtils.getScreenWidth(context) / 10 * 8;
        }
        mWindow.setAttributes(lp);
        dialog.show();

        return dialog;
    }


    public interface DialogItemClickListener {
        void confirm(String result);
    }

    public interface DialogLiveUserClickListener {
        void report();
        void addAttention(String phone);
        void calcelAttention(String phone, String nickname);
        void addSubscription(String sharePhone, String shareType);
        void cancelSubscription(String sharePhone, String shareType);
        void ta(String phone, String nickname);
        void chat(String chatId, String nickname);
        void userHomepage(String phone);
        void shareHomepage(String sharePhone, String shareType);
    }

    public interface DialogClickListener {
        void leftClick();
        void rightClick();
    }

    public interface DialogVerificationClickListener {
        void cancelClick();
        void confirmClick(String str);
    }









   }
