package com.dm.material.dashboard.candybar.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.dm.material.dashboard.candybar.R;
import com.dm.material.dashboard.candybar.helpers.FileHelper;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.io.File;

/*
 * CandyBar - Material Dashboard
 *
 * Copyright (c) 2014-2016 Dani Mahardhika
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class ImageConfig {

    public static ImageLoaderConfiguration getImageLoaderConfiguration(@NonNull Context context) {
        return new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPoolSize(4)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .imageDownloader(new ImageDownloader(context))
                .diskCache(new UnlimitedDiskCache(new File(
                        context.getCacheDir().toString() + "/" + FileHelper.UIL_CACHE_DIR)))
                .diskCacheSize(256 * FileHelper.MB)
                .memoryCacheSize(6 * FileHelper.MB)
                .build();
    }

    public static DisplayImageOptions getDefaultImageOptions(boolean cacheOnDisk) {
        DisplayImageOptions.Builder options = new DisplayImageOptions.Builder();
        options.delayBeforeLoading(10)
                .resetViewBeforeLoading(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(700))
                .cacheOnDisk(cacheOnDisk)
                .cacheInMemory(false);
        return options.build();
    }

    public static DisplayImageOptions getWallpaperOptions() {
        DisplayImageOptions.Builder options = new DisplayImageOptions.Builder();
        options.delayBeforeLoading(10)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .cacheOnDisk(true)
                .cacheInMemory(false);
        return options.build();
    }

    public static DisplayImageOptions.Builder getRawDefaultImageOptions() {
        DisplayImageOptions.Builder options = new DisplayImageOptions.Builder();
        options.delayBeforeLoading(10)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY);
        return options;
    }

    public static DisplayImageOptions.Builder getRawImageOptions() {
        DisplayImageOptions.Builder options = new DisplayImageOptions.Builder();
        options.delayBeforeLoading(10)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .imageScaleType(ImageScaleType.EXACTLY);
        return options;
    }

    public static ImageSize getThumbnailSize(@NonNull Context context) {
        int quality = context.getResources().getInteger(R.integer.wallpaper_grid_preview_quality);
        if (quality <= 0) quality = 1;
        return new ImageSize((50 * quality), (50 * quality));
    }
}

