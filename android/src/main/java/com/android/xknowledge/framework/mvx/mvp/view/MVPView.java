package com.android.xknowledge.framework.mvx.mvp.view;

/**
 * MVP View接口
 */
public interface MVPView {
    void showWinner(String winningPlayerDisplayLabel);

    void clearWinnerDisplay();

    void clearButtons();

    void setButtonText(int row, int col, String text);
}
