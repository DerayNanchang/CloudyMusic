/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lsn.lib.ui.widget.imageview.photoview.gestures;

/**
 * 手势监听器
 *
 * @author xuexiang
 * @since 2018/12/5 上午10:51
 */
public interface OnGestureListener {

    /**
     * 拖拽
     * @param dx
     * @param dy
     */
    void onDrag(float dx, float dy);

    /**
     * 移动
     * @param startX
     * @param startY
     * @param velocityX
     * @param velocityY
     */
    void onFling(float startX, float startY, float velocityX,
                 float velocityY);

    /**
     * 缩放
     * @param scaleFactor
     * @param focusX
     * @param focusY
     */
    void onScale(float scaleFactor, float focusX, float focusY);

}