package com.android.xknowledge.framework.mvx.mvc.model;

import static com.android.xknowledge.framework.mvx.mvc.model.Player.O;
import static com.android.xknowledge.framework.mvx.mvc.model.Player.X;

public class Board {

    private Cell[][] cells = new Cell[3][3];

    private Player winner;
    private GameState state;
    private Player currentTurn;

    public Board() {
        restart();
    }

    /**
     * 开始一个新游戏，清楚计分板和状态
     */
    public void restart() {
        clearCells();
        winner = null;
        currentTurn = X;
        state = GameState.IN_PROGRESS;
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

    public Player getWinner() {
        return winner;
    }

    private void clearCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
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
     * @param player
     * @param currentRow
     * @param currentCol
     * @return 如果当前行、当前列、或者两天对角线为同一位棋手下的棋子返回为真
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

}
