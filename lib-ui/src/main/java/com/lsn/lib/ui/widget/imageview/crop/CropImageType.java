/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.lsn.lib.ui.widget.imageview.crop;


/**
 * 图片裁剪类型
 *
 * @author xuexiang
 * @since 2019-10-15 11:32
 */
public class CropImageType {
    /**
     * 图片剪切网格显示关闭
     */
    public static final int CROPIMAGE_GRID_OFF = 0;

    /**
     * 图片剪切触摸时网格显示
     */
    public static final int CROPIMAGE_GRID_ON_TOUCH = 1;

    /**
     * 图片剪切网格显示开启
     */
    public static final int CROPIMAGE_GRID_ON = 2;


    /**
     * 旋转角度
     */
    public static int ROTATE_NINETY_DEGREES = 90;

    /**
     * 翻转
     */

    public enum REVERSE_TYPE {
        UP_DOWN, LEFT_RIGHT
    }


}
