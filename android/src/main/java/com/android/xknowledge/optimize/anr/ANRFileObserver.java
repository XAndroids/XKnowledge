package com.android.xknowledge.optimize.anr;

import android.os.FileObserver;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 监控文件目录发生变更，用于监控ANR traces.txt文件生成目录/data/anr的目录变化，从而监控ANR
 * FIXME 为什么无法监听文件，成员变量、权限、等问题已排查，还是不行？？！
 * 参考：享学2《性能优化-ANR优化》
 */
public class ANRFileObserver extends FileObserver {

    public ANRFileObserver(String path) {
        super(path);
    }

    public ANRFileObserver(String path, int mask) {
        super(path, mask);
    }

    @Override
    public void onEvent(int event, @Nullable String path) {
        switch (event) {
            case FileObserver.ACCESS://文件被访问
                Log.i("ANR", "ACCESS: " + path);
                break;
            case FileObserver.ATTRIB://文件属性被修改，如 chmod、chown、touch 等
                Log.i("ANR", "ATTRIB: " + path);
                break;
            case FileObserver.CLOSE_NOWRITE://不可写文件被 close
                Log.i("ANR", "CLOSE_NOWRITE: " + path);
                break;
            case FileObserver.CLOSE_WRITE://可写文件被 close
                Log.i("ANR", "CLOSE_WRITE: " + path);
                break;
            case FileObserver.CREATE://创建新文件
                Log.i("ANR", "CREATE: " + path);
                break;
            case FileObserver.DELETE:// 文件被删除，如 rm
                Log.i("ANR", "DELETE: " + path);
                break;
            case FileObserver.DELETE_SELF:// 自删除，即一个可执行文件在执行时删除自己
                Log.i("ANR", "DELETE_SELF: " + path);
                break;
            case FileObserver.MODIFY://文件被修改
                Log.i("ANR", "MODIFY: " + path);
                break;
            case FileObserver.MOVE_SELF://自移动，即一个可执行文件在执行时移动自己
                Log.i("ANR", "MOVE_SELF: " + path);
                break;
            case FileObserver.MOVED_FROM://文件被移走，如 mv
                Log.i("ANR", "MOVED_FROM: " + path);
                break;
            case FileObserver.MOVED_TO://文件被移来，如 mv、cp
                Log.i("ANR", "MOVED_TO: " + path);
                break;
            case FileObserver.OPEN://文件被 open
                Log.i("ANR", "OPEN: " + path);
                break;
            default:
                //CLOSE ： 文件被关闭，等同于(IN_CLOSE_WRITE | IN_CLOSE_NOWRITE)
                //ALL_EVENTS ： 包括上面的所有事件
                Log.i("ANR", "DEFAULT(" + event + "): " + path);
                break;
        }
    }
}
