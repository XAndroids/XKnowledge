package com.android.xknowledge.framework.mvx.mvp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;
import com.android.xknowledge.framework.mvx.mvp.presenter.MVPPresenter;

/**
 * MVP模式实践：实现MVP View接口
 * 参考：享学2《MVx模式演进》
 */
public class MVPActivity extends TitleActivity implements MVPView {
    private static String TAG = MVPActivity.class.getName();

    private ViewGroup buttonGrid;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;

    MVPPresenter presenter = new MVPPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvx);
        winnerPlayerLabel = findViewById(R.id.mvx_textview_winnerplaylabel);
        winnerPlayerViewGroup = findViewById(R.id.mvx_linearlayout_winerplayergroup);
        buttonGrid = findViewById(R.id.mvx_gridlayout_buttongrid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mvx_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mvx_action_reset:
                //除了View操作后，逻辑调用presenter进行处理
                presenter.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onCellClicked(View v) {
        Button button = (Button) v;
        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0, 1));
        int col = Integer.valueOf(tag.substring(1, 2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");

        //除了View操作后，逻辑调用presenter进行处理
        presenter.onButtonSelected(row, col);

    }

    @Override
    public void setButtonText(int row, int col, String text) {
        Button btn = buttonGrid.findViewWithTag("" + row + col);
        if (btn != null) {
            btn.setText(text);
        }
    }

    @Override
    public void clearButtons() {
        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }

    @Override
    public void showWinner(String winningPlayerDisplayLabel) {
        winnerPlayerLabel.setText(winningPlayerDisplayLabel);
        winnerPlayerViewGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void clearWinnerDisplay() {
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");
    }
}
