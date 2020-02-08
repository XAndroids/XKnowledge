package com.android.xknowledge.jetpack.paging.utils

import android.content.Context
import com.android.xknowledge.jetpack.paging.api.RetrofitApi
import com.android.xknowledge.jetpack.paging.repository.DbNet.DBNetRepository
import com.android.xknowledge.jetpack.paging.repository.Repository
import com.android.xknowledge.jetpack.paging.repository.inDb.DbRepository
import com.android.xknowledge.jetpack.paging.repository.inNet.byitem.NetItemRepository
import com.android.xknowledge.jetpack.paging.repository.inNet.bypage.NetPageRepository
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * 数据仓库Locator接口
 */
interface RepositoryLocator {

    fun getRepository(repositoryType: Repository.Type): Repository

    companion object {
        //线程安全单例
        private val LOCK = Any()
        private var instance: RepositoryLocator? = null

        fun instance(context: Context): RepositoryLocator {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = DefaultRepositoryLocator()
                }
                return instance!!
            }
        }
    }

    /**
     * 获取网络线程池
     */
    fun getNetworkExecutor(): Executor

    /**
     * 获取Retrofit Api
     */
    fun getRetrofitApi(): RetrofitApi
}

/**
 * 默认的仓库Locator实现
 */
class DefaultRepositoryLocator : RepositoryLocator {
    //网络线程池
    private val NETWORK_IO = Executors.newFixedThreadPool(5)
    //by lazy：值只有在首次访问的时候计算
    //参考：委托属性，https://www.kotlincn.net/docs/reference/delegated-properties.html
    private val mRetrofitApi by lazy {
        RetrofitApi.create()
    }

    override fun getRepository(repositoryType: Repository.Type): Repository {
        return when (repositoryType) {
            Repository.Type.DB_NET -> DBNetRepository()
            Repository.Type.NET_BYITEM -> NetItemRepository(
                mRetrofitApi = getRetrofitApi(),
                mNetExecutor = getNetworkExecutor()
            )
            Repository.Type.NET_BYPAGE -> NetPageRepository()
            Repository.Type.DB -> DbRepository()
        }
    }

    override fun getNetworkExecutor(): Executor = NETWORK_IO

    override fun getRetrofitApi(): RetrofitApi = mRetrofitApi
}