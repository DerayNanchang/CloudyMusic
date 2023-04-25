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
                const val ENTRANCE_AUTH = "$ENTRANCE/auth"
                const val ENTRANCE_WELCOME = "$ENTRANCE/welcome"
            }
        }


        interface MUSIC {
            companion object {
                private const val MUSIC = "/music"
                const val MUSIC_INDEX = "$MUSIC/index"
            }
        }

        interface VIDEO {
            companion object {
                private const val VIDEO = "/video"
                const val VIDEO_INDEX = "$VIDEO/index"
            }
        }


        interface GRAPHIC {
            companion object {
                private const val GRAPHIC = "/graphic"
                const val VIDEO_INDEX = "$GRAPHIC/index"
            }
        }

        interface SETTINGS {
            companion object {
                private const val SETTINGS = "/settings"
                const val VIDEO_INDEX = "$SETTINGS/index"
            }
        }
    }

    interface EVENT {

        companion object {

            const val KB_EQ_ROOM_NEW_DATA = "KB_EQ_ROOM_NEW_DATA"

        }
    }

}