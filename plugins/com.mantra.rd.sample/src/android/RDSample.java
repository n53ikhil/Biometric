package com.mantra.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class RDSample extends CordovaPlugin {

	private CallbackContext callbackContext;

	public RDSample() {

	}

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);

	}

	@Override
	public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;

		if (action.equals("finger_deviceInfo")) {
			try {
				Intent intent = new Intent();
				intent.setAction("in.gov.uidai.rdservice.fp.INFO");
				cordova.startActivityForResult(this, intent, 1);
			} catch (Exception e) {
				Log.e("Error", e.toString());
				onFailedRes(e.toString());
			}
			return true;
		} else if (action.equals("finger_capture")) {
			try {
				String pidOption = "<PidOptions ver=\"1.0\">"
						+ "<Opts env=\"S\" fCount=\"1\" fType=\"0\" format=\"0\" iCount=\"0\" iType=\"0\" pCount=\"0\" pType=\"0\" pidVer=\"2.0\" posh=\"UNKNOWN\" timeout=\"10000\"/>"
						+ "</PidOptions>";
				Intent intent2 = new Intent();
				intent2.setAction("in.gov.uidai.rdservice.fp.CAPTURE");
				intent2.putExtra("PID_OPTIONS", pidOption);
				cordova.startActivityForResult(this, intent2, 2);
			} catch (Exception e) {
				Log.e("Error", e.toString());
				onFailedRes(e.toString());
			}
			return true;
		} else if (action.equals("iris_deviceInfo")) {
			try {
				Intent intent = new Intent();
				intent.setAction("in.gov.uidai.rdservice.iris.INFO");
				cordova.startActivityForResult(this, intent, 1);
			} catch (Exception e) {
				Log.e("Error", e.toString());
				onFailedRes(e.toString());
			}
			return true;
		} else if (action.equals("iris_capture")) {
			try {
				String pidOption = "<PidOptions ver=\"1.0\">"
						+ "<Opts env=\"S\" fCount=\"0\" fType=\"0\" format=\"0\" iCount=\"1\" iType=\"0\" pCount=\"0\" pType=\"0\" pidVer=\"2.0\" posh=\"UNKNOWN\" timeout=\"10000\"/>"
						+ "</PidOptions>";
				Intent intent2 = new Intent();
				intent2.setAction("in.gov.uidai.rdservice.iris.CAPTURE");
				intent2.putExtra("PID_OPTIONS", pidOption);
				cordova.startActivityForResult(this, intent2, 2);
			} catch (Exception e) {
				Log.e("Error", e.toString());
				onFailedRes(e.toString());
			}
			return true;
		}

		return false;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case 1:
			if (resultCode == Activity.RESULT_OK) {
				try {
					if (data != null) {
						String result = data.getStringExtra("DEVICE_INFO");
						String rdService = data.getStringExtra("RD_SERVICE_INFO");
						JSONObject object = new JSONObject();
						object.put("rd_service_info", rdService);
						object.put("device_info", result);
						onSuccessRes(object);
					}
				} catch (Exception e) {
					Log.e("Error", "Error", e);
					onFailedRes(e.toString());
				}
			}
			return;
		case 2:
			if (resultCode == Activity.RESULT_OK) {
				try {
					JSONObject object = new JSONObject();
					if (data != null) {
						String result = data.getStringExtra("PID_DATA");
						object.put("pid_data", result);
					}
					onSuccessRes(object);
				} catch (Exception e) {
					Log.e("Error", "Error", e);
					onFailedRes(e.toString());
				}
			}
			return;
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	private void onSuccessRes(JSONObject response) {
		if (callbackContext != null) {
			// tolog(response.toString());
			PluginResult result = new PluginResult(PluginResult.Status.OK, response);
			result.setKeepCallback(true);
			callbackContext.sendPluginResult(result);
		}
	}

	private void onFailedRes(String error) {
		if (callbackContext != null) {
			PluginResult result = new PluginResult(PluginResult.Status.ERROR, error);
			result.setKeepCallback(true);
			callbackContext.sendPluginResult(result);
		}
	}

	public void tolog(String toLog) {
		Context context = cordova.getActivity();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, toLog, duration);
		toast.show();
	}
}
