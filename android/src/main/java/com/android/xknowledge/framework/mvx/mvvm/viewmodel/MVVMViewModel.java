package com.android.xknowledge.framework.mvx.mvvm.viewmodel;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;

import com.android.xknowledge.framework.mvx.mvvm.model.Board;
import com.android.xknowledge.framework.mvx.mvvm.model.Player;

/**
 * VM：使用可观察数据类型（和LiveData和ViewModel没关系），cell数据更新和winner数据更新，UI就更新
 */
public class MVVMViewModel {

    private Board model;

    //FIXME 为何两边保留cell？
    public final ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public final ObservableField<String> winner = new ObservableField<>();

    public MVVMViewModel() {
        model = new Board();
    }

    public void onResetSelected() {
        model.restart();
        winner.set(null);
        cells.clear();
    }

    public void onClickedCellAt(int row, int col) {
        Player playerThatMoved = model.mark(row, col);
        if (playerThatMoved != null) {
            cells.put("" + row + col, playerThatMoved == null ? null : playerThatMoved.toString());
            winner.set(model.getWinner() == null ? null : model.getWinner().toString());
        }
    }
}
