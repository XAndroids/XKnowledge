package com.android.xknowledge.framework.mvx.mvp.presenter;

import com.android.xknowledge.framework.mvx.mvp.model.Board;
import com.android.xknowledge.framework.mvx.mvp.model.Player;
import com.android.xknowledge.framework.mvx.mvp.view.MVPView;

/**
 * MVP Presenter：包含Model和View接口实现，负责View和Model的通信
 */
public class MVPPresenter {
    private MVPView view;
    private Board model;

    public MVPPresenter(MVPView view) {
        this.view = view;
        this.model = new Board();
    }

    public void onButtonSelected(int row, int col) {
        //同样，调用model更新数据，view更新ui
        Player playerThatMoved = model.mark(row, col);

        if (playerThatMoved != null) {
            view.setButtonText(row, col, playerThatMoved.toString());

            if (model.getWinner() != null) {
                view.showWinner(playerThatMoved.toString());
            }
        }
    }

    public void onResetSelected() {
        //包含view和model成员，实现它们之间的通信
        //调用model更新数据，view更新UI
        model.restart();
        view.clearWinnerDisplay();
        view.clearButtons();
    }
}
