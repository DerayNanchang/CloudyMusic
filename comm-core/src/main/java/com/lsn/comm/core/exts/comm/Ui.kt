/*
 * Copyright 2016 JetBrains s.r.o.
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
 */

@file:Suppress("unused", "NOTHING_TO_INLINE")
package com.lsn.comm.core.exts.comm

import android.view.View

@DslMarker
@Target(AnnotationTarget.TYPE)
annotation class AnkoViewDslMarker

/**
 * Apply [f] to this [View] and to all of its children recursively.
 * 
 * @return the receiver.
 */
inline fun <T : View> T.applyRecursively(noinline f: (View) -> Unit): T {
    AnkoInternals.applyRecursively(this, f)
    return this
}