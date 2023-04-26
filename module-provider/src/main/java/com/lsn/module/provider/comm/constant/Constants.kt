package com.pmisy.roomkb


/**
 * @Author : lsn
 * @CreateTime : 2023/4/6 上午 11:33
 * @Description :
 */
interface Constants {

    interface Entrance {

        companion object {

        }

    }

    interface Music {

        companion object {

        }

    }


    interface Video {

        companion object {

        }

    }


    interface Graphic {

        companion object {

        }

    }


    interface Settings {

        companion object {

        }

    }


    interface RouterPath {

        interface MAIN {

            companion object {
                private const val MAIN = "/main"
                const val INDEX = "$MAIN/index"
                const val PROVIDE = "$MAIN/provider"
            }
        }

        interface ENTRANCE {

            companion object {
                private const val ENTRANCE = "/entrance"
                const val AUTH = "$ENTRANCE/auth"
                const val WELCOME = "$ENTRANCE/welcome"
            }
        }


        interface MUSIC {
            companion object {
                private const val MUSIC = "/music"
                const val INDEX = "$MUSIC/index"
                const val PROVIDE = "$MUSIC/provider"
            }
        }

        interface VIDEO {
            companion object {
                private const val VIDEO = "/video"
                const val INDEX = "$VIDEO/index"
                const val PROVIDE = "$VIDEO/provider"
            }
        }


        interface GRAPHIC {
            companion object {
                private const val GRAPHIC = "/graphic"
                const val INDEX = "$GRAPHIC/index"
                const val PROVIDE = "$GRAPHIC/provider"
            }
        }

        interface MINE {
            companion object {
                private const val MINE = "/settings"
                const val INDEX = "$MINE/index"
                const val PROVIDE = "$MINE/provider"
            }
        }
    }

    interface EVENT {

        companion object {

            const val KB_EQ_ROOM_NEW_DATA = "KB_EQ_ROOM_NEW_DATA"

        }
    }

}