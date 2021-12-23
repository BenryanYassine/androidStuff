package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {
    private SimpleExoPlayer player = null;
    private TextureView mTextureView;
    //ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextureView = (TextureView) findViewById(R.id.textures_view);
         //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

    }

    private void initializePlayer(){
        DefaultTrackSelector trackSelector = new DefaultTrackSelector((Context)this);
        trackSelector.setParameters(trackSelector.buildUponParameters().setMaxVideoSizeSd());
        player = new SimpleExoPlayer.Builder(this).setTrackSelector(trackSelector).build();
        // Use the explicit MIME type to build an HLS media item.
        MediaItem mediaItem = new MediaItem.Builder()
                .setUri("https://manifest.googlevideo.com/api/manifest/hls_playlist/expire/1640296106/ei/SprEYebRD8_m1gbArpXICw/ip/80.215.224.154/id/dp8PhLsUcFE.5/itag/301/source/yt_live_broadcast/requiressl/yes/ratebypass/yes/live/1/sgoap/gir%3Dyes%3Bitag%3D140/sgovp/gir%3Dyes%3Bitag%3D299/hls_chunk_host/rr1---sn-cv0tb0xn-jqbe7.googlevideo.com/playlist_duration/30/manifest_duration/30/vprv/1/playlist_type/DVR/initcwndbps/8770/mh/3k/mm/44/mn/sn-cv0tb0xn-jqbe7/ms/lva/mv/m/mvi/1/pl/21/dover/11/keepalive/yes/fexp/24001373,24007246/mt/1640274279/sparams/expire,ei,ip,id,itag,source,requiressl,ratebypass,live,sgoap,sgovp,playlist_duration,manifest_duration,vprv,playlist_type/sig/AOq0QJ8wRAIgRVZaL5n-8jCN1XZ-muZCKuUd46eti-rI2eOZ9o57lzUCIE6PZONtZ7ifCYsDhCcKmclu_DjH2BY9-KsJ1VG000zb/lsparams/hls_chunk_host,initcwndbps,mh,mm,mn,ms,mv,mvi,pl/lsig/AG3C_xAwRQIgB5NXaQjXjuHDhreBr9roeIYPgGJtdafevj2KFJPfVNkCIQChwoFJLKrEi9rRPQAsCnboYYwX3MebFZL9nMuX_Bc4cA%3D%3D/playlist/index.m3u8")
                .setMimeType(MimeTypes.APPLICATION_M3U8)
                .build();
         player.setVideoTextureView(mTextureView);
        player.setMediaItem(mediaItem);
        player.setPlayWhenReady(true);
        player.seekTo(0,0L);
        player.prepare();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer() ;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer();
        }
    }
    @SuppressLint("InlinedApi")
    private void hideSystemUi() {

        mTextureView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer() ;
        }
    }
    private void releasePlayer(){

    }
}