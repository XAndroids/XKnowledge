package com.android.xknowledge.sdk.ui.custom.viewgroup;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.android.xknowledge.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局
 * 参考：享学2《什么是自定义VIew，什么是高级UI》
 */
public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";

    //每个item横向间距
    private final int mHorizontalSpacing = dp2px(16);
    //每个item横向间距
    private final int mVerticalSpacing = dp2px(8);

    // 记录所有的行，一行一行的存储，用于layout
    private final List<List<View>> allLines = new ArrayList<>();
    // 记录每一行的行高，用于layout
    List<Integer> lineHeights = new ArrayList<>();

    public FlowLayout(Context context) {
        super(context);
    }

    //反射
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //主题style
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //Andorid自定义属性，在Android X还是支持android:xxx写法
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        String mText = ta.getString(R.styleable.MyTextView_android_text);
    }

    //四个参数 自定义属性
    private void clearMeasureParams() {
        allLines.clear();
        lineHeights.clear();
    }

    //度量：递归度量子View，然后根据子View度量尺寸，计算自己的尺寸
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //内存 抖动，onMeasure可能多次调用，具体由父ViewGroup决定，如FrameLayout中就多次调用孩子View的measur
        //e()
        clearMeasureParams();

        //父ViewGroup的padding
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        //记录这行已经使用了多宽的size
        int lineWidthUsed = 0;
        // 一行的行高
        int lineHeight = 0;
        //保存一行中的所有的view
        List<View> lineViews = new ArrayList<>();

        // measure过程中，子View要求的父ViewGroup的宽
        // measure过程中，子View要求的父ViewGroup的高
        int parentNeededWidth = 0;
        int parentNeededHeight = 0;

        //ViewGroup解析的父亲给我的宽度
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);

        //循环度量所有孩子，然后根据孩子的度量尺寸，计算自己的尺寸
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams childLP = childView.getLayoutParams();

            //如果孩子可见才进行度量
            if (childView.getVisibility() != View.GONE) {
                //将孩子layoutParams转变成为父ViewGroup对子View的度量要求measureSpec：
                int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft
                        + paddingRight, childLP.width);
                int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop
                        + paddingBottom, childLP.height);
                //根据父ViewGroup的宽高度量要求MeasureSpec，进行孩子度量
                childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

                //孩子度量完成之后，获取子view的度量宽高，然后根据子View的宽高计算父ViewGroup的宽高
                int childMesauredWidth = childView.getMeasuredWidth();
                int childMeasuredHeight = childView.getMeasuredHeight();

                //如果需要换行
                if (childMesauredWidth + lineWidthUsed + mHorizontalSpacing > selfWidth) {
                    //一旦换行，我们就可以判断当前行需要的宽和高了，所以此时要记录下来
                    allLines.add(lineViews);
                    lineHeights.add(lineHeight);

                    //换行后，重新计算父ViewGroup需要的宽和高
                    parentNeededHeight = parentNeededHeight + lineHeight + mVerticalSpacing;
                    parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing);

                    //清空当前行的View、使用的宽度和高度
                    lineViews = new ArrayList<>();
                    lineWidthUsed = 0;
                    lineHeight = 0;
                }

                //如果不需要换行
                //view是分行layout的，所以要记录每一行有哪些view，这样可以方便layout布局
                lineViews.add(childView);
                //每行都会有自己的宽和高
                lineWidthUsed = lineWidthUsed + childMesauredWidth + mHorizontalSpacing;
                lineHeight = Math.max(lineHeight, childMeasuredHeight);

                //处理最后一行数据，还达不到换行条件，增加改行的Views和高度，计算父ViewGroup需要的宽高
                if (i == childCount - 1) {
                    allLines.add(lineViews);
                    lineHeights.add(lineHeight);
                    parentNeededHeight = parentNeededHeight + lineHeight + mVerticalSpacing;
                    parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing);
                }
            }
        }

        //子View度量完毕后，再度量自己
        //根据子View的度量结果，来重新度量自己ViewGroup
        // 作为一个ViewGroup，它自己也是一个View,它的大小也需要根据它的父亲给它提供的宽高来度量
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // ViewGroup解析的父亲给我的高度
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);
        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeededWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ? selfHeight : parentNeededHeight;

        //保存父ViewGruop的度量结果
        setMeasuredDimension(realWidth, realHeight);
    }

    //布局：根据View度量的尺寸，依次计算View在页面中的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //视图布局左上的起始位置
        int curL = getPaddingLeft();
        int curT = getPaddingTop();

        //流式布局的行数，进行一行一行的布局
        int lineCount = allLines.size();
        for (int i = 0; i < lineCount; i++) {
            //获取该行的所有视图和该行的高度
            List<View> lineViews = allLines.get(i);
            int lineHeight = lineHeights.get(i);

            //一个一个将该行的视图进行布局
            for (int j = 0; j < lineViews.size(); j++) {
                //计算View的左上右下的坐标
                View view = lineViews.get(j);
                int left = curL;
                int top = curT;
                int right = left + view.getMeasuredWidth();
                int bottom = top + view.getMeasuredHeight();

                //设置View所在具体位置
                view.layout(left, top, right, bottom);

                //更新左边的起始位置
                curL = right + mHorizontalSpacing;
            }

            //更新上边的起始位置
            curT = curT + lineHeight + mVerticalSpacing;
            //重置左边的其实位置
            curL = getPaddingLeft();
        }

    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

}
