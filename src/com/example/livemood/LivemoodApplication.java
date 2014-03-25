package com.example.livemood;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class LivemoodApplication extends Application {

	@Override
    public void onCreate() {
        super.onCreate();
        
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
	        .cacheInMemory(false)
	        .cacheOnDisc(false)
	        .build();
        
        // Create global configuration and initialize ImageLoader with this configuration
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        	.defaultDisplayImageOptions(defaultOptions)
            .build();
        ImageLoader.getInstance().init(config);
    }

}
