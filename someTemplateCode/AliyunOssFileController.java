package com.kdyun.mall.api;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.kdyun.common.core.controller.BaseController;
import com.kdyun.common.core.domain.AjaxResult;
import com.kdyun.common.utils.uuid.IdUtils;
import com.kdyun.mall.api.web.context.LoginContext;
import com.kdyun.mall.domain.dto.AliyunFileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 *  阿里云oss  文件上传下载
 * @author  wjc
 */
@RestController
public class AliyunOssFileController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(AliyunOssFileController.class);

    @Value("${aliyun.endpoint}")
    private String endpoint ;

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId ;

    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret ;

    @Value("${aliyun.bucketName}")
    private String bucketName ;


    /**
     * 以流的形式上传
     * @param aliyunFileDto 文件名称,文件自身
     * @return AjaxResult
     */
    @ResponseBody
    @RequestMapping("/common/uploadFile")
    public AjaxResult uploadFile(@Validated AliyunFileDto aliyunFileDto)  {
        LoginContext.getLoginUser();
        log.info("阿里云文件上传入参："+aliyunFileDto);
        Map<Object, Object> data = new HashMap<>();

        String fileName=aliyunFileDto.getFiles().getOriginalFilename();
        String objectName="files/"+ IdUtils.randomUUID().replace("-","").concat(fileName);
        String url;
        try {
            url = upload(aliyunFileDto.getFiles().getBytes(), objectName);
        } catch (IOException e) {
            e.printStackTrace();
            return error(AjaxResult.Type.WARN,"上传的文件异常！请重新上传！");
        }

        data.put("url",url);
        data.put("objectName",objectName);
        log.info("oss 文件上传成功！");
        return success("文件上传成功！").put("data",data);
    }

    public String upload(byte[] bytes,String objectName){
        // 转成文件
        if(bytes.length ==0){
            /*文件长度为零*/
            return "";
        }
        String url="https:".concat(bucketName).concat(".").
                concat(endpoint).concat("/").concat(objectName);

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        // 填写Byte数组。
        byte[] content = bytes;
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));

        // 关闭OSSClient。
        ossClient.shutdown();
        return url;
    }

    /**
     * aliyun 文件下载
     * @param objectName 文件桶位路径+文件名、文件完整路径名
     * @param token token
     * @return AjaxResult
     */
    @ResponseBody
    @RequestMapping("/common/downloadFile")
    public  void downLoanddFile(HttpServletResponse response, @RequestParam("objectName") @NotNull(message = "文件路径不可为空") String objectName,
                                      @RequestParam("token") @NotNull(message = "文件零不可为空") String token){
        log.info("阿里云文件下载入参："+objectName);
//        Map<Object, Object> data = new HashMap<>();

        downLoadFromAliyun(response,objectName);

//        data.put("success",true);
//        return  success("文件下载成功！").put("data",data);
    }

    public  void downLoadFromAliyun(HttpServletResponse response,String objectName ) {

        // 填写Bucket名称。
        // 填写Object的完整路径。Object完整路径中不能包含Bucket名称。
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);

        // 读取文件内容。
//        BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));

//        String randName= UUID.randomUUID().toString().replace("-","").concat(objectType);
        String downName=objectName.substring(objectName.indexOf("/")+1);
        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
//        reader.close();

        // 关闭OSSClient。
        download(response,ossObject,downName,ossClient);
        /*ossClient.shutdown();*/

//        downloadFile(null,"files/8e8a4789ebf34232a9483b73e68576e5t0h.jpg");
    }


    public void download(HttpServletResponse response, OSSObject ossObject, String filename, OSS ossClient){
        try {

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(ossObject.getObjectContent());
//            byte[] read = read(fis);
            byte[] read = input2byte(fis);

            fis.close();
            // 清空response
            response.reset();

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
//            response.setContentType(ossObject.getObjectMetadata().getContentType());
            String fileName = filename.substring(32);
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));

            OutputStream ouputStream = response.getOutputStream();
            ouputStream.write(read);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            logger.error("文件下载出现异常", e);
        }finally {
            if(null != ossClient){
                ossClient.shutdown();
            }
        }
    }


    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        swapStream.flush();
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }


}
