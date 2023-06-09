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

package com.lsn.lib.ui.widget.dialog.strategy;

import android.content.DialogInterface;
import androidx.annotation.NonNull;

/**
 * 输入内容回调
 *
 * @author xuexiang
 * @since 2018/11/16 上午12:15
 */
public interface InputCallback {

    void onInput(@NonNull DialogInterface dialog, CharSequence input);
}
