package com.android.xknowledge.framework.mvx.mvc;

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
import com.android.xknowledge.framework.mvx.mvc.model.Board;
import com.android.xknowledge.framework.mvx.mvc.model.Player;

/**
 * MVC架构演进：抽离了Model（不包含View的数据操作），Activity+Fragment+View为Controller；
 * 参考：享学2《MVX架构演进》
 */
public class MVCActivity extends TitleActivity {
    private static String TAG = MVCActivity.class.getName();

    private Board model;

    private ViewGroup buttonGrid;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvx);

        winnerPlayerLabel = findViewById(R.id.mvx_textview_winnerplaylabel);
        winnerPlayerViewGroup = findViewById(R.id.mvx_linearlayout_winerplayergroup);
        buttonGrid = findViewById(R.id.mvx_gridlayout_buttongrid);

        model = new Board();
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
                model.restart();
                resetView();
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

        Player playerThatMoved = model.mark(row, col);

        if (playerThatMoved != null) {
            button.setText(playerThatMoved.toString());
            if (model.getWinner() != null) {
                winnerPlayerLabel.setText(playerThatMoved.toString());
                winnerPlayerViewGroup.setVisibility(View.VISIBLE);
            }
        }

    }

    public void resetView() {
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");

        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }
}
