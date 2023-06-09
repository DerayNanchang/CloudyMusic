/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.lsn.lib.ui.widget.progress.materialprogressbar;

/**
 * A {@code Drawable} that has an intrinsic padding.
 */
public interface IntrinsicPaddingDrawable {

    /**
     * Get whether this drawable is using an intrinsic padding. The default is {@code true}.
     *
     * @return Whether this drawable is using an intrinsic padding.
     */
    boolean getUseIntrinsicPadding();

    /**
     * Set whether this drawable should use an intrinsic padding. The default is {@code true}.
     *
     * @param useIntrinsicPadding Whether this drawable should use its intrinsic padding.
     */
    void setUseIntrinsicPadding(boolean useIntrinsicPadding);
}
