package cordova.plugins;

import android.util.Log;

import com.open.downloader.utils.StorageUtils;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 磁盘操作
 */
public class Disk extends CordovaPlugin {

    public static final String ACTIONS_GETDISKINFO = "getDiskinfo";

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException{
        return actionsRoute(action,args,callbackContext);
    }

    /**
     * actions 路由
     * @param action
     * @param args
     * @param callbackContext
     * @return
     * @throws JSONException
     */
    private boolean actionsRoute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException{
        if (action.equals(ACTIONS_GETDISKINFO)) {
        	JSONObject options = args.optJSONObject(0);
            getDiskinfo(options,args,callbackContext);
            return true;
        }
        return false;
    }

    /**
    * 获取磁盘
    * @param options  设置
    * @param args
    * @param callbackContext
    */
    private void getDiskinfo(JSONObject options,JSONArray args,CallbackContext callbackContext) throws JSONException {
        Log.i("Disk", "获取磁盘...");
        Log.i("Disk", "options:"+options);
        long availableStorage = StorageUtils.getAvailableStorage();
        long totalStorage = StorageUtils.getTotalStorage();
        PluginResult progressResult = new PluginResult(PluginResult.Status.OK, new JSONObject(
                "{info:{freeDisk:"+availableStorage+",totalDisk:"+totalStorage+"} }"));
        //progressResult.setKeepCallback(true);
        callbackContext.sendPluginResult(progressResult);
    }

}