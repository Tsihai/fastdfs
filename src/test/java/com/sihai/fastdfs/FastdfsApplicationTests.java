package com.sihai.fastdfs;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;

@SpringBootTest
class FastdfsApplicationTests {

    @Test
    void contextLoads() throws MyException, IOException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);
        NameValuePair pairs[] = null;
        String fileId = client1.upload_file1("C:\\Users\\13169\\Desktop\\xsz.jpg", "jpg", pairs);
        System.out.println(fileId);

    }



    @Test
    void testDownload() {
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            byte[] bytes = client.download_file1("group1/M00/00/00/fN2Ns2MmtaeAIXruAA2k9xFzVBg280.jpg");
            FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\13169\\Desktop\\2.png"));
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取令牌
     */
    @Test
    public void getToken() throws Exception {
        // 时间戳
        int ts = (int) Instant.now().getEpochSecond();
        String token = ProtoCommon.getToken("M00/00/00/fN2Ns2Mnt9GANIiIAArP_zvf8BQ273.jpg", ts, "FastDFS0821");
        StringBuilder sb = new StringBuilder();
        sb.append("http://124.221.141.179/")
                .append("group1/M00/00/00/fN2Ns2Mnt9GANIiIAArP_zvf8BQ273.jpg")
            .append("?token=")
            .append(token)
            .append("&ts=")
            .append(ts);
        System.out.println(sb.toString());
    }
}
