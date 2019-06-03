package cn.gog.conmentmanage.api;

import java.util.List;


import cn.gog.conmentmanage.model.ArticleDetail;
import cn.gog.conmentmanage.model.ArticleMsg;
import cn.gog.conmentmanage.model.ArticlePage;
import cn.gog.conmentmanage.model.ContactPage;
import cn.gog.conmentmanage.model.DutyDetailModel;
import cn.gog.conmentmanage.model.DutyPage;
import cn.gog.conmentmanage.model.MsgEntity;
import cn.gog.conmentmanage.model.OrgEntity;
import cn.gog.conmentmanage.model.SearchEntity;
import cn.gog.conmentmanage.model.SystemInfoPage;
import cn.gog.conmentmanage.model.TaskPage;
import cn.gog.conmentmanage.model.TaskTypeEntity;
import cn.gog.conmentmanage.model.UserInfo;
import common.vo.ResultResponse;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/8
 * desc:新闻模块接口
 */
public interface ApiService {


    String login = "appm/user/login";
    String message_newest = "appm/message/newest";
    String task_page = "appm/task/page";
    String task_detail_byid = "appm/task/findByID";
    String user_page = "appm/user/page";
    String Article_page = "appm/RecommendedArticle/page";
    String Article_findByID = "appm/RecommendedArticle/findByID";
    String Article_deleteByID = "appm/RecommendedArticle/deleteByID";
    String Article_add = "appm/RecommendedArticle/add";
    String taskType_list = "appm/taskType/list";
    String childrenOrg = "appm/org/childrenOrg";
    String task_add = "appm/task/add";
    String task_update = "appm/task/update";
    String updateAndPublish = "appm/task/updateAndPublish";
    String addAndPublish = "appm/task/addAndPublish";
    String task_cancel = "appm/task/cancel";
    String task_finish = "appm/task/finish";
    String task_publish = "appm/task/publish";
    String group_findByOrgid = "appm/group/findByOrgid";
    String assignToGroup = "appm/task/assignToGroup";
    String batchAgree = "appm/RecommendedArticle/batchAgree";
    String batchDisagree = "appm/RecommendedArticle/batchDisagree";
    String pageArticleMsg = "appm/message/pageArticleMsg";
    String pageTaskMsg = "appm/message/pageTaskMsg";
    String pageSystemMsg = "appm/message/pageSystemMsg";
    String updatePassword = "appm/user/updatePassword";
    String search = "appm/message/search";
    String assignToOrg = "appm/task/assignToOrg";
    String message_delete = "appm/message/delete";
    String UPLOAD =  "appm/user/uploadHead";
    String MESSAGEJPUSH =  "appm/messageJpush/save";

    /**
     * 登录
     * @param body
     * @return
     */
    @POST(login)
    Observable<ResultResponse<String>> login(@Body RequestBody body);

    /**
     *获取最新消息
     *
     * @return
     */
    @POST(message_newest)
    Observable<ResultResponse<String>> messageNewest();


    /**
     * 获取任务列表列表
     * @return
     */
    @POST(task_page)
    Observable<ResultResponse<String>> taskPage(@Body RequestBody body);

    /**
     * 获取任务列表列表
     * @return
     */
    @POST(task_detail_byid)
    Observable<ResultResponse<String>> taskById(@Body RequestBody body);


    /**
     * 获取通讯录列表
     * @return
     */
    @POST(user_page)
    Observable<ResultResponse<String>> contactList(@Body RequestBody body);


    /**
     * 获取文章列表
     * @return
     */
    @POST(Article_page)
    Observable<ResultResponse<String>> articlePage(@Body RequestBody body);

    /**
     * 获取文章详情
     * @return
     */
    @POST(Article_findByID)
    Observable<ResultResponse<String>> articleFindByID(@Body RequestBody body);
    /**
     * 删除文章
     * @return
     */
    @POST(Article_deleteByID)
    Observable<ResultResponse<String>> deleteByID(@Body RequestBody body);
    /**
     * 新增文章
     * @return
     */
    @POST(Article_add)
    Observable<ResultResponse<String>> addArticle(@Body RequestBody body);

    /**
     * 新增文章
     * @return
     */
    @POST(taskType_list)
    Observable<ResultResponse<String>> getTaskType();
    /**
     * 新增文章
     * @return
     */
    @POST(childrenOrg)
    Observable<ResultResponse<String>> childrenOrg( );

    /**
     * 新增任务
     * @return
     */
    @POST(task_add)
    Observable<ResultResponse<String>> taskAdd(@Body RequestBody body);

      /**
     * 修好任务
     * @return
     */
    @POST(task_update)
    Observable<ResultResponse<String>> task_update(@Body RequestBody body);
    /**
     * 修好任务并发布
     * @return
     */
    @POST(updateAndPublish)
    Observable<ResultResponse<String>> task_updateAndPublish(@Body RequestBody body);

    /**
     * 新增任务
     * @return
     */
    @POST(addAndPublish)
    Observable<ResultResponse<String>> addAndPublish(@Body RequestBody body);

    /**
     * 任务取消
     * @return
     */
    @POST(task_cancel)
    Observable<ResultResponse<String>> taskCancel(@Body RequestBody body);
    /**
     * 完结
     * @return
     */
    @POST(task_finish)
    Observable<ResultResponse<String>> taskFinish(@Body RequestBody body);
    /**
     * publish
     * @return
     */
    @POST(task_publish)
    Observable<ResultResponse<String>> taskPublish(@Body RequestBody body);

    /**
     * 网评员分组列表
     * @return
     */
    @POST(group_findByOrgid)
    Observable<ResultResponse<String>> groupFindByOrgid();

    /**
     * 网评员分组列表
     * @return
     */
    @POST(assignToGroup)
    Observable<ResultResponse<String>>  assignToGroup(@Body RequestBody body);

    /**
     * 批量采纳
     * @return
     */
    @POST(batchAgree)
    Observable<ResultResponse<String>> batchAgree(@Body RequestBody body);
    /**
     * 批量不采纳
     * @return
     */
    @POST(batchDisagree)
    Observable<ResultResponse<String>> batchDisagree(@Body RequestBody body);

    /**
     * 文章消息通知
     * @return
     */
    @POST(pageArticleMsg)
    Observable<ResultResponse<String>> pageArticleMsg(@Body RequestBody body);
    /**
     * 任务消息通知
     * @return
     */
    @POST(pageTaskMsg)
    Observable<ResultResponse<String>> pageTaskMsg(@Body RequestBody body);
    /**
     * 任务消息通知
     * @return
     */
    @POST(pageSystemMsg)
    Observable<ResultResponse<String>> pageSystemMsg(@Body RequestBody body);

    /**
     * 任务消息通知
     * @return
     */
    @POST(updatePassword)
    Observable<ResultResponse<String>> updatePassword(@Body RequestBody body);

    /**
     * 搜索
     * @return
     */
    @POST(search)
    Observable<ResultResponse<String>> searhByKey(@Body RequestBody body);

    /**
     * 任务派发
     * @return
     */
    @POST(assignToOrg)
    Observable<ResultResponse<String>> assignToOrg(@Body RequestBody body);

    /**
     * 删除消息
     * @return
     */
    @POST(message_delete)
    Observable<ResultResponse<String>> deletaMsgs(@Body RequestBody body);

    /**
     * 用户修改
     *
     * @param body
     * @return
     */
    @POST(UPLOAD)
    Observable<ResultResponse<String>> uploadImg(@Body RequestBody body);
    /**
     * 用户修改
     *
     * @param body
     * @return
     */
    @POST(MESSAGEJPUSH)
    Observable<ResultResponse<String>> MESSAGEJPUSH(@Body RequestBody body);
}
