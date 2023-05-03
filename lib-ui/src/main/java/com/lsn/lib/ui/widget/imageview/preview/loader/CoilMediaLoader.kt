package com.lsn.lib.ui.widget.imageview.preview.loader

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.fragment.app.Fragment
import coil.Coil
import coil.ImageLoader
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.lsn.lib.ui.R
import com.lsn.lib.ui.widget.imageview.preview.ui.BasePhotoFragment.listener


/**
 * @Author : lsn
 * @CreateTime : 2023/5/3 上午 10:21
 * @Description :
 */
class CoilMediaLoader : IMediaLoader {

    /**
     * 加载图片
     *
     * @param context
     * @param path         图片你的路径
     * @param imageView
     * @param simpleTarget 图片加载状态回调
     */
    override fun displayImage(
        context: Fragment,
        path: String,
        imageView: ImageView?,
        simpleTarget: ISimpleTarget
    ) {

        imageView?.also {
            imageView.load(it) {
                listener(
                    onSuccess = { imageRequest, ruccessResult ->
                        simpleTarget.onResourceReady()
                    },
                    onError = { imageRequest, errorResult ->
                        simpleTarget.onLoadFailed(null)
                    }
                )
            }
        }
    }

    /**
     * 加载gif 图
     *
     * @param context
     * @param path         图片你的路径
     * @param imageView
     * @param simpleTarget 图片加载状态回调
     */
    override fun displayGifImage(
        context: Fragment,
        path: String,
        imageView: ImageView?,
        simpleTarget: ISimpleTarget
    ) {

        imageView?.also {

            imageView.load(it) {


            /*listener(
                    onSuccess = { imageRequest, ruccessResult ->
                        simpleTarget.onResourceReady()
                    },
                    onError = { imageRequest, errorResult ->
                        simpleTarget.onLoadFailed(null)
                    }
                )*/

            }
        }

        /*Glide.with(context)
            .asGif()
            .apply(mRequestOptions)
            .load(path)
            .listener(object : RequestListener<GifDrawable?>() {
                fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    simpleTarget.onLoadFailed(null)
                    return false
                }

                fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    simpleTarget.onResourceReady()
                    return false
                }
            })
            .into(imageView)*/
    }

    /**
     * 停止
     *
     * @param context 容器
     */
    override fun onStop(context: Fragment) {
//        Glide.with(context).onStop()
    }

    /**
     * 停止
     *
     * @param context 容器
     */
    override fun clearMemory(context: Context) {
//        Glide.get(context).clearMemory()
    }


    /**
     * @return 获取glide请求参数
     */
//    fun getRequestOptions(): RequestOptions? {
//        return RequestOptions()
//            .placeholder(R.drawable.xui_ic_default_img)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//    }
}