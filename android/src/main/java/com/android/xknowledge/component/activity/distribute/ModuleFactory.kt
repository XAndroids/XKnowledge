package com.android.xknowledge.component.activity.distribute

/**
 * 模块工厂，负责反射创建模块实例
 */
object ModuleFactory {

    fun newModuleInstance(name: String?): ModuleInterface? {
        if (name == null || name == "") {
            return null
        }

        try {
            val moduleClzz = Class.forName(name)
            return moduleClzz.newInstance() as ModuleInterface
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        return null
    }
}
