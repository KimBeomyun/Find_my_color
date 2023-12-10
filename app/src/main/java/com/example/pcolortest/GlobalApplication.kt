package com.example.pcolortest

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "843146d232af3549d0f8ebf7f286d50e")
    }
}
