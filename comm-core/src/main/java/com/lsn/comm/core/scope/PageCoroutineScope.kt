package com.lsn.comm.core.scope


/**
 * @Author : lsn
 * @CreateTime : 2023/3/29 上午 11:48
 * @Description :
 */
//class PageCoroutineScope(
//    val page: PageRefreshLayout,
//    dispatcher: CoroutineDispatcher = Dispatchers.Main
//) : NetCoroutineScope(dispatcher = dispatcher) {
//
//    val index get() = page.index
//
//    init {
//        ViewTreeLifecycleOwner.get(page)?.lifecycle?.addObserver(object : LifecycleEventObserver {
//            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
//                if (event == Lifecycle.Event.ON_DESTROY) cancel()
//            }
//        })
//    }
//
//    override fun start() {
//        previewEnabled = !page.loaded
//        page.trigger()
//    }
//
//    override fun previewFinish(succeed: Boolean) {
//        super.previewFinish(succeed)
//        if (succeed && previewBreakLoading) {
//            page.showContent()
//        }
//        page.loaded = succeed
//    }
//
//    override fun catch(e: Throwable) {
//        super.catch(e)
//        page.showError(e)
//    }
//
//    override fun finally(e: Throwable?) {
//        super.finally(e)
//        if (e == null || e is CancellationException) {
//            page.showContent()
//        }
//        page.trigger()
//    }
//
//    override fun handleError(e: Throwable) {
//        if (page.loaded || !page.stateEnabled) {
//            NetConfig.errorHandler.onError(e)
//        } else {
//            NetConfig.errorHandler.onStateError(e, page)
//        }
//    }
//
//}