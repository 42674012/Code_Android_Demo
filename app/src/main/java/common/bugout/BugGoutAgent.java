/**
 * Copyright (c) 2016年 贵州多彩宝互联网服务有限公司. All rights reserved.
 */
package common.bugout;

import android.content.Context;

import com.testin.agent.Bugout;
import com.testin.agent.BugoutConfig;

/**
 *
 * Author: MJiao 
 * Time: 16/9/8  16:45 
 */
public class BugGoutAgent {


    public static void init(Context context, boolean debugModel){

        String userinfo = "";
        BugoutConfig config = new BugoutConfig.Builder(context)
                    .withAppKey("125590a4c8db2a2b1d13f125c4412b73")     // 您的应用的项目 Key,如果已经在 Manifest 中配置则此处可略
                    .withAppChannel("dcb")     // 发布应用的渠道,如果已经在 Manifest 中配置则此处可略
                    .withUserInfo(userinfo)    // 用户信息-崩溃分析根据用户记录崩溃信息
                    .withDebugModel(debugModel)    // 输出更多SDK的debug信息
                    .withErrorActivity(true)    // 发生崩溃时采集Activity信息
                    .withCollectNDKCrash(true) //  收集NDK崩溃信息
                    .withOpenCrash(true)    // 收集崩溃信息开关
                    .withOpenEx(true)     // 是否收集异常信息
                    .withReportOnlyWifi(true)    // 仅在 WiFi 下上报崩溃信息
                    .withReportOnBack(true)    // 当APP在后台运行时,是否采集信息
//                    .withQAMaster(false)    // 是否收集摇一摇反馈
//                    .withCloseOption(true)   // 是否在摇一摇菜单展示‘关闭摇一摇选项’
                    .withLogCat(true)  // 是否系统操作信息
                    .build();
            Bugout.init(config);
    }
}
