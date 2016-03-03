package com.drogsin.rsspotal.application;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import java.io.File;

/**
 * Created by jjm on 2015-12-08.
 */
public class RSSPortalApplication extends Application{
    public static final String RSS_URL_PRIMARY = "http://media.daum.net/syndication/today_sisa.rss";
    public static final String RSS_URL_ENTERTAIN = "http://media.daum.net/syndication/today_entertain.rss";
    public static final String RSS_URL_SPORTS = "http://media.daum.net/syndication/today_sports.rss";
    public static final String RSS_URL_POLITICS = "http://media.daum.net/syndication/politics.rss";
    public static final String RSS_URL_SOCIETY = "http://media.daum.net/syndication/society.rss";
    public static final String RSS_URL_ECONOMIC = "http://media.daum.net/syndication/economic.rss";
    public static final String RSS_URL_FOREIGN = "http://media.daum.net/syndication/foreign.rss";
    public static final String RSS_URL_CULTURE= "http://media.daum.net/syndication/culture.rss";
    public static final String RSS_URL_DIGITAL= "http://media.daum.net/syndication/digital.rss";

    public File cashDir;
    private DisplayImageOptions options = null;
    @Override
    public void onCreate() {
        super.onCreate();
        this.initImageLoader(this);
    }

    /**
     * Copyright 2011-2015 Sergey Tarasevich

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
     */
    private void initImageLoader(Context context){
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
//			.writeDebugLogs() // Remove for release app
                .build();

        ImageLoader.getInstance().init(config);
    }

    public DisplayImageOptions getDisplayImageOptions() {
        if(this.options == null)
        {
            this.options = new DisplayImageOptions.Builder()
//			.showImageOnLoading(R.drawable.ic_drawer)
//			.showImageForEmptyUri(R.drawable.no_img)
//                    .showImageOnFail()
                    .resetViewBeforeLoading(false)
//			.delayBeforeLoading(1000)
//			.preProcessor(...)
//			.postProcessor(...)
//			.extraForDownloader(...)
//			.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
//			.bitmapConfig(Bitmap.Config.ARGB_8888)
//			.decodingOptions(...)
//			.displayer(new SimpleBitmapDisplayer())
//			.handler(new Handler())
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
//			.considerExifParams(true)
                    .build();
        }

        return this.options;
    }

    public void removeFromCache(String imageUri) {
        DiskCacheUtils.removeFromCache(imageUri, ImageLoader.getInstance().getDiskCache());

        MemoryCacheUtils.removeFromCache(imageUri, ImageLoader.getInstance().getMemoryCache());
    }
}
