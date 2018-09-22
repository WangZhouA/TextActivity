package myapplication66.yanglh6.example.com.textactivity.utils;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import myapplication66.yanglh6.example.com.textactivity.entity.MusicBean;

/**
 * 音频文件工具类
 */

public class MediaUtil {


    //根据contengprivate获取本地音频文件
    public static List<MusicBean> getMusicListFromSD(Context context) {
        ContentResolver contentResolver = context.getContentResolver();

        if (contentResolver == null) {
            Log.d("---->","无权限？？？");
            return null;
        }




        Cursor mCursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,   //路径
                new String[]{  //写入我想要获得的信息（列）,填null是获取所有
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.ALBUM_ID,
                        MediaStore.Audio.Media.DATA

                }, null, null, null);

        List<MusicBean> musicList = new ArrayList<>();
        //查出来的是否是空
        if (mCursor.moveToFirst()) {
            do {
                //这里参数要和上面的对应
                String title = mCursor.getString(0);
                String album = mCursor.getString(1);
                String displayName = mCursor.getString(2);
                String artist = mCursor.getString(3);
                long duration = mCursor.getLong(4);
                long size = mCursor.getLong(5);
                int album_id = mCursor.getInt(6);
                String url = mCursor.getString(7);


                //时间是0说明播放不了，下一个
                if (duration <= 0) {
                    Log.d("---->","音频文件损坏");
                    continue;
                }
                MusicBean musicBeen = new MusicBean();
                musicBeen.setType(MusicBean.TYPE_SD);
                musicBeen.setTitle(title);
                musicBeen.setAlbum(album);
                musicBeen.setDisplayName(displayName);
                musicBeen.setArtist(artist);
                musicBeen.setDuration(duration);
                musicBeen.setSize(size);
                musicBeen.setAlbum_id(album_id);
                musicBeen.setUrl(url);
                musicList.add(musicBeen);
            } while (mCursor.moveToNext());
        }
        mCursor.close();

        for (int i = 0; i < musicList.size(); i++) {
            Log.d("---->",musicList.get(i).getDisplayName());
        }

        return musicList;

    }


    public static List<MusicBean> getAllFromAssets(Context context) {
        String[] images = null;
        AssetManager assets = context.getAssets();

        List<MusicBean> musicBeen = new ArrayList<>();
        try {
            //获取/assets/目录下所有文件
            images = assets.list("");
            for (int i = 0; i < images.length; i++) {
                if (images[i].endsWith(".mp3")) {
                    musicBeen.add(getMp3info(assets, images[i]));
                }
            }

            //打印
            for (int i = 0; i < musicBeen.size(); i++) {
                Log.d("---->",musicBeen.get(i).getTitle());
            }
            return musicBeen;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    //获取assets内Mp3文件信息
    static MusicBean getMp3info(AssetManager assets, String path) throws IOException {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        AssetFileDescriptor afd = assets.openFd(path);
        mmr.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());

        String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
//        String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR);
        String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION); // 播放时长单位为毫秒

        MusicBean musicBeen = new MusicBean();
        musicBeen.setTitle(title);
        musicBeen.setDisplayName(title);
        //内置音乐，自定义
        musicBeen.setType(MusicBean.TYPE_INSTER);
        //专辑
        if (TextUtils.isEmpty(album)) {
            album = "未知";
        }
        musicBeen.setAlbum(album);
        //作者
        if (TextUtils.isEmpty(artist)) {
            artist = "未知";
        }
        musicBeen.setArtist(artist);
        //时长
        musicBeen.setDuration(Long.parseLong(duration));
        //路径
        musicBeen.setUrl(path);
        return musicBeen;
    }

//    private void findLocalAudio(Intent data) {
//        Uri uri = data.getData();
//        String filePath = GetPathFromUri4kitkat.getPath(RecordingActivity.this, uri);
//        MediaScannerConnection.scanFile(RecordingActivity.this, new String[]{filePath}, null, new ScanCompletedListener());
//    }
//
//    private void chooseLocalAudio(String choosePath) {
//        ContentResolver contentResolver = this.getContentResolver();
//        AttachBean attachBean = new AttachBean();
//        if (contentResolver != null) {
//            Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    do {
//                        String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
//                        String localAudioPath = new File(filePath).toString();
//                        if (localAudioPath.equals(choosePath)) {
//
//                            String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
//                            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
//                            long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
//                            long dateAdded = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DATE_ADDED)) * 1000;
//                            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
//
//                            if (duration <= 0) {
//                                Toast.makeText(this, "音频损坏", Toast.LENGTH_SHORT).show();
//                            }
//                            attachBean.setAttachType(AttachColumns.Type.AUDIO);
//                            attachBean.setAddTime(dateAdded);
//                            attachBean.setCreateTime(String.valueOf(dateAdded));
//                            attachBean.setCloud(false);
//                            attachBean.setFileSize(String.valueOf(size));
//                            attachBean.setFilePath(localAudioPath);
//                            attachBean.setLocalDisplayName(Uri.decode(displayName));
//                            attachBean.setTotalTime(String.valueOf(duration));
//                            attachBean.setTitle(title);
//                            OfflineCreateActivity.startOfflineCreateActivity(RecordingActivity.this, attachBean);
//                        }
//                    } while (cursor.moveToNext());
//                }
//                cursor.close();
//            }
//        }
//    }
//
//
//    private class ScanCompletedListener implements MediaScannerConnection.OnScanCompletedListener {
//        @Override
//        public void onScanCompleted(String path, Uri uri) {
//            if (path != null) {
//                switch (path.substring(path.lastIndexOf("."))) {
//                    case ".mp3":
//                    case ".ogg":
//                    case ".wav":
//                        chooseLocalAudio(path);
//                        break;
//                    default:
//                        ToastUtils.show(RecordingActivity.this, "音频格式暂时只支持主流的 mp3 ogg wav");
//                        break;
//                }
//            }
//        }
//    }





    /**
     *  读写权限  自己可以添加需要判断的权限
     */
    public static String[]permissionsREAD={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };





}
