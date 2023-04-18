package com.lsn.comm.core.annotation

import java.lang.annotation.RetentionPolicy

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@DslMarker
@Retention(AnnotationRetention.BINARY)
internal annotation class BindingPropertyDelegate

