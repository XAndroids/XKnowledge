package com.android.xknowledge.framework.mvx.mvvm.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.databinding.DataBindingUtil;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;
import com.android.xknowledge.databinding.ActivityMvvmBinding;
import com.android.xknowledge.framework.mvx.mvvm.viewmodel.MVVMViewModel;

/**
 * MVVM演进
 * 参考：享学2《MVx演进》
 */
public class MVVMActivity extends TitleActivity {

    MVVMViewModel viewModel = new MVVMViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        binding.setViewModel(viewModel);
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
                viewModel.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
