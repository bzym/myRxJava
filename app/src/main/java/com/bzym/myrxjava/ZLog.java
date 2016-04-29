package com.bzym.myrxjava;

import android.app.Activity;
import android.util.Log;

/**
 * log 类
 *
 */
public class ZLog {

	/**
	 * 为false是所有log不输出
	 */
	public static boolean isLogShow = true;

	/**
	 * 属于显示哪个等级的log
	 */
	private static int showWhoLervel = 1;
	//
	//	//	/**
	//	//	 * 示例
	//	//	 */
	//		private static final String TAG = "";
	//		private static final int LOGLEVEL = 1;

	private String TAG = "ZLog";
	private int LOGLEVEL = 1;

	public ZLog(Activity activity, int level) {
		TAG = activity.getLocalClassName();
		LOGLEVEL = level;
	}

	public ZLog(String tag, int level) {
		TAG = tag;
		LOGLEVEL = level;
	}
	public ZLog(String tag) {
		TAG = tag;
		LOGLEVEL = showWhoLervel;
	}

	/**
	 * 普通的log显示 log.i
	 * 
	 * @param string
	 *            显示的内容
	 * @param TAG
	 *            标签
	 * @param LOGLEVEL
	 *            log的等级
	 */
	public static void Zlogi(String string, String TAG, int LOGLEVEL) {
		if (isLogShow) {
			if (showWhoLervel == LOGLEVEL) {
				Log.i(TAG, string);
			}
		}
	}

	/**
	 * log.i
	 * @param string
	 */
	public void zz(String string) {
		if (isLogShow) {
			if (showWhoLervel == LOGLEVEL) {
				Log.i(TAG, string);
			}
		}
	}
	/**
	 * log.d
	 * @param string
	 */
	public void zzd(String string) {
		if (isLogShow) {
			if (showWhoLervel == LOGLEVEL) {
				Log.d(TAG, string);
			}
		}
	}
	/**
	 * log.e
	 * @param string
	 */
	public void zze(String string) {
		if (isLogShow) {
			if (showWhoLervel == LOGLEVEL) {
				Log.e(TAG, string);
			}
		}
	}
}
