package com.android.xknowledge.component.activity.distribute

object ModuleFactory {

    fun newModuleInstance(name: String?): AbsModule? {
        if (name == null || name == "") {
            return null
        }

        try {
            val moduleClzz = Class.forName(name)
            return moduleClzz.newInstance() as AbsModule
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
