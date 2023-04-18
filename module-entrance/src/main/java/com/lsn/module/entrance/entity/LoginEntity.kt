package com.pmisy.roomkb.entity.login

/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 04:55
 * @Description : 用户登录关联entity
 */
/**
 *  提交登录数据
 */
data class LoginRequEntity(
    var password: String = "",
    var languageNo: String = "",
    var loginType: String = "",
    var userNo: String = "",
    var manufaturer: String = "",
    var clientNo: String = "",
    var modelNumber: String = "",
    var systemVersion: String = "",
    var resolution: String = "",
    var deviceId: String = "",
)

/**
 *  返回登录数据
 */
data class LoginRespEntity(
    var moduleList: List<ModuleListEntity>? = null,
    var systemConf: SystemConfEntity? = null,
    var userInf: UserInfEntity? = null,
    var token: String = "",
)

data class UserInfEntity(
    var address: String = "",
    val backgroundIm: String = "",
    val birthDay: String = "",
    val blockFlg: Int = 0,
    val building: String = "",
    val changePw: String = "",
    val city: String = "",
    val cityNo: String = "",
    val clientNo: String = "",
    val comLang: String = "",
    val comLangNo: String = "",
    val costCenter: String = "",
    val country: String = "",
    val countryNo: String = "",
    val createBy: String = "",
    val createDate: String = "",
    val customerRowStatus: String = "",
    val dateFmt: String = "",
    val decimalFmt: String = "",
    val department: String = "",
    val departmentNo: String = "",
    val faxNo: String = "",
    val fontFamily: String = "",
    val headPortrait: String = "",
    val huanXinPassword: String = "",
    val huanXinUserName: String = "",
    val invalidTms: Int = 0,
    val languageNo: String = "",
    val lastLogin: String = "",
    val location: String = "",
    val logMaxNum: Int = 0,
    val loginLang: String = "",
    val md5Code: String = "",
    val mobilePhone: String = "",
    var password: String = "",
    val phoneNo: String = "",
    val position: String = "",
    val positionNo: String = "",
    val postcode: String = "",
    val Ph: String = "",
    val region: String = "",
    val regionNo: String = "",
    val room: String = "",
    val statusFlag: String = "",
    val street: String = "",
    val systemNo: String = "",
    val tzone: String = "",
    val userGroup: String = "",
    val userName: String = "",
    val userNo: String = "",
    val userType: String = "",
    val validFr: String = "",
    val validTo: String = "",
    val vendorRowStatus: String = "",
    val workPh: String? = "",
)


data class SystemConfEntity(

    var pubDateFormat: String = "",
    var pubFileServer: String = "",
    var pubDateTimeFormat: String = "",
    var pubTimeFormat: String? = "",
)


data class ModuleListEntity(
    var activationLogo: String = "",
    var changeBy: String = "",
    var changeDate: String = "",
    var clientNo: String = "",
    var createBy: String = "",
    var createDate: String = "",
    var description: String = "",
    var moduleLogo: String = "",
    var moduleNo: String = "",
    var platformFlag: Int = 0
)