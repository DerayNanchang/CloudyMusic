package com.lsn.lib.ui.widget.imageview.strategy.impl

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import coil.Coil
import coil.load
import coil.util.CoilUtils
import com.lsn.lib.ui.widget.imageview.preview.loader.GlideMediaLoader.getRequestOptions
import com.lsn.lib.ui.widget.imageview.strategy.*


/**
 * @Author : lsn
 * @CreateTime : 2023/5/3 上午 09:45
 * @Description :
 */
class CoilImageLoadStrategy : IImageLoadStrategy {

    override fun loadImage(imageView: ImageView, path: Any?) {
        path?.let {
            imageView.load(it)
        }
    }

    /**
     * 加载图片【最常用】
     *
     * @param imageView 图片控件
     * @param path      图片资源的索引
     * @param listener  加载监听
     */
    override fun loadImage(imageView: ImageView, path: Any?, listener: ILoadListener) {

        path?.let {
            imageView.load(it) {
                listener(
                    onError = { request, throwable ->
                        listener.onLoadFailed(throwable.throwable)
                    },
                    onSuccess = { request, metadata ->
                        listener.onLoadSuccess()
                    }
                )
            }
        }
    }

    override fun loadGifImage(imageView: ImageView, path: Any?) {

        path?.let {
            imageView.load(path)
        }
    }

    /**
     * 加载图片【最常用】
     *
     * @param imageView 图片控件
     * @param path      图片资源的索引
     * @param listener  加载监听
     */
    override fun loadGifImage(imageView: ImageView, path: Any?, listener: ILoadListener) {
        loadImage(imageView, path, listener)
    }

    override fun loadImage(imageView: ImageView, path: Any?, strategy: DiskCacheStrategyEnum?) {
        loadImage(imageView, path, LoadOption.of(strategy))
    }

    /**
     * 加载图片
     *
     * @param imageView 图片控件
     * @param path      图片资源的索引
     * @param strategy  磁盘缓存策略
     * @param listener  加载监听
     */
    override fun loadImage(
        imageView: ImageView,
        path: Any?,
        strategy: DiskCacheStrategyEnum?,
        listener: ILoadListener?
    ) {
        loadImage(imageView, path, LoadOption.of(strategy), listener)
    }

    override fun loadGifImage(imageView: ImageView, path: Any?, strategy: DiskCacheStrategyEnum?) {
        loadGifImage(imageView, path, LoadOption.of(strategy))
    }

    /**
     * 加载Gif图片
     *
     * @param imageView 图片控件
     * @param path      图片资源的索引
     * @param strategy  磁盘缓存策略
     * @param listener  加载监听
     */
    override fun loadGifImage(
        imageView: ImageView,
        path: Any?,
        strategy: DiskCacheStrategyEnum?,
        listener: ILoadListener?
    ) {
        loadGifImage(imageView, path, LoadOption.of(strategy), listener)
    }

    override fun loadImage(
        imageView: ImageView,
        path: Any?,
        placeholder: Drawable?,
        strategy: DiskCacheStrategyEnum?
    ) {
        loadImage(imageView, path, LoadOption.of(placeholder).setCacheStrategy(strategy))
    }

    /**
     * 加载图片
     *
     * @param imageView   图片控件
     * @param path        图片资源的索引
     * @param placeholder 占位图片
     * @param strategy    磁盘缓存策略
     * @param listener    加载监听
     */
    override fun loadImage(
        imageView: ImageView,
        path: Any?,
        placeholder: Drawable?,
        strategy: DiskCacheStrategyEnum?,
        listener: ILoadListener?
    ) {
        loadImage(imageView, path, LoadOption.of(placeholder).setCacheStrategy(strategy), listener)
    }

    override fun loadGifImage(
        imageView: ImageView,
        path: Any?,
        placeholder: Drawable?,
        strategy: DiskCacheStrategyEnum?
    ) {
        loadGifImage(imageView, path, LoadOption.of(placeholder).setCacheStrategy(strategy))
    }

    /**
     * 加载Gif图片
     *
     * @param imageView   图片控件
     * @param path        图片资源的索引
     * @param placeholder 占位图片
     * @param strategy    磁盘缓存策略
     * @param listener    加载监听
     */
    override fun loadGifImage(
        imageView: ImageView,
        path: Any?,
        placeholder: Drawable?,
        strategy: DiskCacheStrategyEnum?,
        listener: ILoadListener?
    ) {
        loadGifImage(
            imageView,
            path,
            LoadOption.of(placeholder).setCacheStrategy(strategy),
            listener
        )
    }

    /**
     * 加载图片
     *
     * @param imageView  图片控件
     * @param path       图片资源的索引
     * @param loadOption 加载选项
     */
    override fun loadImage(imageView: ImageView, path: Any?, loadOption: LoadOption) {
        loadImage(imageView, path, loadOption, null)
    }

    /**
     * 加载Gif图片
     *
     * @param imageView  图片控件
     * @param path       图片资源的索引
     * @param loadOption 加载选项
     */
    override fun loadGifImage(imageView: ImageView, path: Any?, loadOption: LoadOption) {
        loadGifImage(imageView, path, loadOption, null)
    }

    /**
     * 加载图片
     *
     * @param imageView  图片控件
     * @param path       图片资源的索引
     * @param loadOption 加载选项
     */
    @SuppressLint("CheckResult")
    override fun loadImage(
        imageView: ImageView,
        path: Any?,
        loadOption: LoadOption,
        listener: ILoadListener?
    ) {

//        imageView.load(path){
//            this.
//        }
//
//
//        imageView.load
//        val builder: RequestBuilder<Drawable> = Glide.with(imageView.context)
//            .load(path)
//            .apply(getRequestOptions(loadOption))
//        if (listener != null) {
//            builder.listener(object : RequestListener<Drawable?>() {
//                fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable?>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    listener.onLoadFailed(e)
//                    return false
//                }
//
//                fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: Target<Drawable?>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    listener.onLoadSuccess()
//                    return false
//                }
//            })
//        }
//        builder.into(imageView)
    }

    /**
     * 加载Gif图片
     *
     * @param imageView  图片控件
     * @param path       图片资源的索引
     * @param loadOption 加载选项
     */
    @SuppressLint("CheckResult")
    override fun loadGifImage(
        imageView: ImageView,
        path: Any?,
        loadOption: LoadOption,
        listener: ILoadListener?
    ) {
/*        val builder: RequestBuilder<GifDrawable> = Glide.with(imageView.context)
            .asGif()
            .load(path)
            .apply(getRequestOptions(loadOption))
        if (listener != null) {
            builder.listener(object : RequestListener<GifDrawable?>() {
                fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    listener.onLoadFailed(e)
                    return false
                }

                fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    listener.onLoadSuccess()
                    return false
                }
            })
        }
        builder.into(imageView)*/
    }

    /**
     * loadOption转化为RequestOptions
     *
     * @param loadOption
     * @return
     */
//    @SuppressLint("CheckResult")
//    private fun getRequestOptions(loadOption: LoadOption): RequestOptions? {
//        val options = RequestOptions()
//        if (loadOption.hasSize()) {
//            options.override(loadOption.getWidth(), loadOption.getHeight())
//        }
//        if (loadOption.placeholder != null) {
//            options.placeholder(loadOption.placeholder)
//        }
//        if (loadOption.error != null) {
//            options.error(loadOption.error)
//        }
//        if (loadOption.cacheStrategy != null) {
//            options.diskCacheStrategy(toGlideStrategy(loadOption.cacheStrategy))
//        }
//        when (loadOption.align) {
//            AlignEnum.CENTER_CROP -> options.centerCrop()
//            AlignEnum.CIRCLE_CROP -> options.circleCrop()
//            AlignEnum.CENTER_INSIDE -> options.centerInside()
//            AlignEnum.FIT_CENTER -> options.fitCenter()
//            else -> {}
//        }
//        options.timeout(loadOption.timeoutMs)
//        return options
//    }

    override fun clearCache(context: Context?) {
        /*Glide.get(context).clearMemory()
        Glide.get(context).clearDiskCache()*/
    }

    override fun clearMemoryCache(context: Context?) {
//        Glide.get(context).clearMemory()
    }

    override fun clearDiskCache(context: Context?) {
//        Glide.get(context).clearDiskCache()
    }

    /**
     * 策略切换
     *
     * @param strategyEnum
     * @return
     */
/*    private fun toGlideStrategy(strategyEnum: DiskCacheStrategyEnum): DiskCacheStrategy? {
        return when (strategyEnum) {
            DiskCacheStrategyEnum.ALL -> DiskCacheStrategy.ALL
            DiskCacheStrategyEnum.NONE -> DiskCacheStrategy.NONE
            DiskCacheStrategyEnum.DATA -> DiskCacheStrategy.DATA
            DiskCacheStrategyEnum.RESOURCE -> DiskCacheStrategy.RESOURCE
            DiskCacheStrategyEnum.AUTOMATIC -> DiskCacheStrategy.AUTOMATIC
            else -> DiskCacheStrategy.AUTOMATIC
        }
    }*/
}