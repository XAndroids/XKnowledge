package com.android.xknowledge.jetpack

import com.android.xknowledge.ListActivity
import com.android.xknowledge.jetpack.databinding.DataBindingActivity
import com.android.xknowledge.jetpack.lifecycler.LifeCyclerActivity
import com.android.xknowledge.jetpack.livedata.LiveDataActivity
import com.android.xknowledge.jetpack.navigation.NavigationActivity
import com.android.xknowledge.jetpack.paging.PagingActivity
import com.android.xknowledge.jetpack.viewmodel.ViewModelActivity

class JetpackActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Paging", "Paging库相关", PagingActivity::class.java),
            ListItem("Navigation", "Navigation库相关", NavigationActivity::class.java),
            ListItem("LifeCycle", "LifeCycle库相关", LifeCyclerActivity::class.java),
            ListItem("ViewModel", "ViewModel库相关", ViewModelActivity::class.java),
            ListItem("LiveData", "LiveData库相关", LiveDataActivity::class.java),
            ListItem("DataBinding", "DataBinding库相关", DataBindingActivity::class.java)
        )
    }
}
