package myapplication66.yanglh6.example.com.textactivity.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;

public class Util {
	public static void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout,
											 float displayWidthPercentage) {
		if (drawerLayout == null)
			return;
		try {
			Field leftDraggerField = drawerLayout.getClass().getDeclaredField(
					"mLeftDragger");
			leftDraggerField.setAccessible(true);
			ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField
					.get(drawerLayout);
			Field edgeSizeField = leftDragger.getClass().getDeclaredField(
					"mEdgeSize");
			edgeSizeField.setAccessible(true);
			int edgeSize = edgeSizeField.getInt(leftDragger);
			DisplayMetrics dm = new DisplayMetrics();
			activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
			edgeSizeField.setInt(leftDragger, Math.max(edgeSize,
					(int) (dm.widthPixels * displayWidthPercentage)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] bmpToByteArray(final Bitmap bmp,
										final boolean needRecycle) {
		int i;
		int j;
		if (bmp.getHeight() > bmp.getWidth()) {
			i = bmp.getWidth();
			j = bmp.getWidth();
		} else {
			i = bmp.getHeight();
			j = bmp.getHeight();
		}

		Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
		Canvas localCanvas = new Canvas(localBitmap);

		while (true) {
			localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0, i,
					j), null);
			if (needRecycle)
				bmp.recycle();
			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
			localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
					localByteArrayOutputStream);
			localBitmap.recycle();
			byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
			try {
				localByteArrayOutputStream.close();
				return arrayOfByte;
			} catch (Exception e) {
				// F.out(e);
			}
			i = bmp.getHeight();
			j = bmp.getHeight();
		}
	}

	/**
	 * 程序是否在前台运行
	 *
	 * @return
	 */
	public static boolean isAppOnForeground(Context context) {
		// Returns a list of application processes that are running on the
		// device
		ActivityManager activityManager = (ActivityManager)context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
		String packageName = context.getApplicationContext().getPackageName();

		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;
		for (RunningAppProcessInfo appProcess : appProcesses) {
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}
		return false;
	}



	public   static  String   replaceAll(int i, String idCard){
		if (!TextUtils.isEmpty(idCard)) {

			if (i==0) {
				String sb = idCard.substring(5, 10);
				year = idCard.substring(0, 4);
				mont = idCard.substring(5, 7);
				return sb.replaceAll("-", "/");
			}else {
				String sb = idCard.substring(8 , 10);
				return  sb;
			}
		}else {
			return "";
		}
	}


	private static  String year;
	public static  String  getYear(){

		return  year;
	}
	private static  String mont;
	public static  String  getMont(){

		return  mont;
	}


	public static int   junZhi(List<Integer>lists){
		int a =0;
		for (int i = 0; i < lists.size(); i++) {
			a+=lists.get(i);
		}
		return a/lists.size();
	}


	public static  int    Max (List<Integer>lists){
		int min,max;

		for ( int  maxI =0 ;maxI<lists.size();maxI++) {

			min = max = lists.get(maxI);
			for (maxI = 0; maxI < lists.size(); maxI++) {
//	                System.out.print(A[maxI] + " ");
				if ( lists.get(maxI) > max)   // 判断最大值
					max = lists.get(maxI);
				if ( lists.get(maxI) < min)   // 判断最小值
					min = lists.get(maxI);
			}

			return   max;
		}
		return  0;
	}

	public static  int    Min (List<Integer>lists){



		int min,max;

		for ( int  maxI =0 ;maxI<lists.size();maxI++) {

			min = max = lists.get(maxI);
			for (maxI = 0; maxI < lists.size(); maxI++) {
//	                System.out.print(A[maxI] + " ");
				if ( lists.get(maxI) > max)   // 判断最大值
					max = lists.get(maxI);
				if ( lists.get(maxI) < min)   // 判断最小值
					min = lists.get(maxI);
			}

			return   min;
		}
		return  0;
	}




}
