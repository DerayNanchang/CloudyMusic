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

package com.lsn.lib.ui.widget.imageview.nine;

import android.widget.ImageView;

import java.util.List;

/**
 * 九宫图长按
 *
 * @author xuexiang
 * @since 2018/12/9 下午10:38
 */
public interface ItemImageLongClickListener<T> {
    /**
     * 长按图片
     *
     * @param imageView
     * @param index     索引
     * @param list      图片列表
     * @return 是否消费了事件
     */
    boolean onItemImageLongClick(ImageView imageView, int index, List<T> list);
}
