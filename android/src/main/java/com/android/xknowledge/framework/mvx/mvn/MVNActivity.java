package com.android.xknowledge.framework.mvx.mvn;

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

import static com.android.xknowledge.framework.mvx.mvn.MVNActivity.Player.O;
import static com.android.xknowledge.framework.mvx.mvn.MVNActivity.Player.X;

/**
 * MVN模式，一个Activity包含所有实现
 * 参考：享学2《MVx模式演进》
 */
public class MVNActivity extends TitleActivity {
    private static String TAG = MVNActivity.class.getName();

    //玩家
    public enum Player {X, O}

    //旗子格子
    public class Cell {

        private Player value;

        public Player getValue() {
            return value;
        }

        public void setValue(Player value) {
            this.value = value;
        }
    }

    //游戏状态
    private enum GameState {IN_PROGRESS, FINISHED}

    //旗子格子集合
    private Cell[][] cells = new Cell[3][3];

    //赢家
    private Player winner;
    //游戏当前状态
    private GameState state;
    //当前下棋玩家
    private Player currentTurn;

    //视图
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

        restart();
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
                restart();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 开始一个新游戏，清楚计分板和状态
     */
    public void restart() {
        clearCells();

        winner = null;
        currentTurn = X;
        state = GameState.IN_PROGRESS;

        //重置视图
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");

        //重置旗子格子显示
        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }

    //清空和初始化所有旗子格子
    private void clearCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    /**
     * 棋子格子点击操作
     *
     * @param v
     */
    public void onCellClicked(View v) {
        //获取点击的格子的行和列
        Button button = (Button) v;
        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0, 1));
        int col = Integer.valueOf(tag.substring(1, 2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");


        Player playerThatMoved = mark(row, col);

        if (playerThatMoved != null) {
            button.setText(playerThatMoved.toString());
            if (getWinner() != null) {
                winnerPlayerLabel.setText(playerThatMoved.toString());
                winnerPlayerViewGroup.setVisibility(View.VISIBLE);
            }
        }

    }

    /**
     * 标记当前的选手选择了哪行哪列
     * 如果不是在没有选中的9个格子里面点击将视作无效；
     * 另外，如果游戏已经结束，本次标记忽略
     *
     * @param row 0..2
     * @param col 0..2
     * @return 返回当前选手，如果点击无效发挥为null
     */
    public Player mark(int row, int col) {
        Player playerThatMoved = null;
        if (isValid(row, col)) {
            cells[row][col].setValue(currentTurn);
            playerThatMoved = currentTurn;

            if (isWinningMoveByPlayer(currentTurn, row, col)) {
                state = GameState.FINISHED;
                winner = currentTurn;
            } else {
                // 切换到另外一起棋手，继续
                flipCurrentTurn();
            }
        }

        return playerThatMoved;
    }

    private boolean isValid(int row, int col) {
        if (state == GameState.FINISHED) {
            return false;
        } else if (isCellValueAlreadySet(row, col)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isCellValueAlreadySet(int row, int col) {
        return cells[row][col].getValue() != null;
    }

    /**
     * 如果当前行、当前列、或者两天对角线为同一位棋手下的棋子返回为真
     */
    private boolean isWinningMoveByPlayer(Player player, int currentRow, int currentCol) {
        return (cells[currentRow][0].getValue() == player         // 3-行
                && cells[currentRow][1].getValue() == player
                && cells[currentRow][2].getValue() == player
                || cells[0][currentCol].getValue() == player      // 3-列
                && cells[1][currentCol].getValue() == player
                && cells[2][currentCol].getValue() == player
                || currentRow == currentCol            // 3-对角线
                && cells[0][0].getValue() == player
                && cells[1][1].getValue() == player
                && cells[2][2].getValue() == player
                || currentRow + currentCol == 2    // 3-反对角线
                && cells[0][2].getValue() == player
                && cells[1][1].getValue() == player
                && cells[2][0].getValue() == player);
    }

    private void flipCurrentTurn() {
        currentTurn = currentTurn == X ? O : X;
    }

    public Player getWinner() {
        return winner;
    }
}
