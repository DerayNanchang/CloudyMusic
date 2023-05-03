package com.lsn.comm.core.utils

import android.widget.ImageView
import coil.load
import coil.request.ImageRequest
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
                    buildParam(isCrossfade, isPlaceholder, isError)
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
                    buildParam(isCrossfade, isPlaceholder, isError)
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
                    buildParam(isCrossfade, isPlaceholder, isError)
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
                    buildParam(isCrossfade, isPlaceholder, isError)
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
                    buildParam(isCrossfade, isPlaceholder, isError)
                    transformations(CircleCropTransformation())
                    scale(Scale.FILL)
                }
            }
        }
    }


    private fun ImageRequest.Builder.buildParam(
        isCrossfade: Boolean,
        isPlaceholder: Boolean,
        isError: Boolean
    ) {
        if (isCrossfade) {
            crossfade(500)
        }
        if (isPlaceholder) {
            placeholder(com.lsn.lib.base.R.mipmap.ic_launcher)
        }

        if (isError) {
            error(com.lsn.lib.base.R.mipmap.ic_launcher)
        }
    }

}