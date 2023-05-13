package com.lsn.comm.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.lsn.comm.core.coil.BlurTransformation
import com.lsn.comm.core.entity.CoilDataEntity


/**
 * @Author : lsn
 * @CreateTime : 2023/5/3 上午 10:39
 * @Description :
 */
object CoilUtil {


    fun load(
        imageView: ImageView?,
        path: Any?,
        build: CoilDataEntity.() -> Unit = {}
    ) {
        if (imageView != null && path != null) {
            imageView.load(path) {
                CoilDataEntity().apply(build).apply {
                    buildParam(isCrossfade, isDefPlaceholder,placeholderRes, isDefError,errorRes)
                }
            }
        }
    }


    /**
     *  加载普通圆角
     */
    fun loadRounded(
        imageView: ImageView?, path: Any?,
        build: CoilDataEntity.() -> Unit = {}
    ) {
        if (imageView != null && path != null) {
            imageView.load(path) {
                CoilDataEntity().apply(build).apply {
                    buildParam(isCrossfade, isDefPlaceholder,placeholderRes, isDefError,errorRes)
                    transformations(RoundedCornersTransformation(radius))
                    scale(Scale.FILL)
                }
            }
        }
    }

    /**
     *  加载四个圆角
     */
    fun loadRoundedDetail(
        imageView: ImageView?, path: Any?,
        build: CoilDataEntity.() -> Unit = {}
    ) {
        if (imageView != null && path != null) {
            imageView.load(path) {
                CoilDataEntity().apply(build).apply {
                    buildParam(isCrossfade, isDefPlaceholder,placeholderRes, isDefError,errorRes)
                    RoundedCornersTransformation(
                        topLeft = topStart,
                        topRight = topEnd,
                        bottomLeft = bottomStart,
                        bottomRight = bottomEnd
                    )
                    scale(Scale.FILL)
                }
            }
        }
    }


    /**
     *  高斯模糊  数值越大，程度越高
     */
    fun loadBlur(
        imageView: ImageView?, path: Any?,
        build: CoilDataEntity.() -> Unit = {}
    ) {
        if (imageView != null && path != null) {
            imageView.load(path) {

                /*       var pictureGrid:RenderEffect?= null
       RenderEffect.createBlurEffect(radius,radius,sampling,)
       if (radius > 0) {
           val blur = RenderEffect.createBlurEffect(
               radius, radius, Shader.TileMode.MIRROR)
           var imageView : ImageView? = null
           imageView?.setRenderEffect()
           output.setRenderEffect(blur)
       } else {
           pictureGrid.setRenderEffect(null)
       }*/

                CoilDataEntity().apply(build).apply {
                    buildParam(isCrossfade, isDefPlaceholder,placeholderRes, isDefError,errorRes)
                    transformations(BlurTransformation(imageView.context, blurRadius, blurSampling))
                    scale(Scale.FILL)
                }


            }
        }
    }

    /**
     *  圆形
     */
    fun loadCircle(
        imageView: ImageView?, path: Any?,
        build: CoilDataEntity.() -> Unit = {}
    ) {
        if (imageView != null && path != null) {
            imageView.load(path) {
                CoilDataEntity().apply(build).apply {
                    buildParam(isCrossfade, isDefPlaceholder,placeholderRes, isDefError,errorRes)
                    transformations(CircleCropTransformation())
                    scale(Scale.FILL)
                }
            }
        }
    }


    /**
     * 获取网络图片的 bitmap
     */
    suspend fun Context.getImageBitmapByUrl(url: String): Bitmap? {
        val request = ImageRequest.Builder(this)
            .data(url)
            .allowHardware(false)
            .build()
        val result = (imageLoader.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }


    private fun ImageRequest.Builder.buildParam(
        isCrossfade: Boolean,
        isDefPlaceholder: Boolean,
        placeholderRes: Int,
        isDefError: Boolean,
        errorRes:Int,
    ) {
        if (isCrossfade) {
            crossfade(500)
        }

        if (placeholderRes != com.lsn.lib.base.R.mipmap.ic_launcher){
            placeholder(placeholderRes)
        }else{
            if (isDefPlaceholder){
                placeholder(com.lsn.lib.base.R.mipmap.ic_launcher)
            }
        }

        if (errorRes != com.lsn.lib.base.R.mipmap.ic_launcher){
            error(errorRes)
        }else{
            if (isDefError){
                error(com.lsn.lib.base.R.mipmap.ic_launcher)
            }
        }
    }

}