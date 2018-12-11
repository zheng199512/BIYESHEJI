package cn.e3mall.fast;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

public class FastDfsTest {
	@Test
	public void testUpload() throws Exception{
		ClientGlobal.init("e:/BiyeSheji/e3-manager-web/src/main/resources/conf/client.conf");
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer,storageServer);
		String[] strings=storageClient.upload_file("F:/5a1e7b431d99a.jpg", "jpg", null);
		for(String string:strings){
			System.out.println(string);
		}
	}
	@Test
	public void testFastDfsClient() throws Exception{
		FastDFSClient fastDFSClient = new FastDFSClient("e:/BiyeSheji/e3-manager-web/src/main/resources/conf/client.conf");
		String string=fastDFSClient.uploadFile("F:/59eea8abd23d6.jpg");
		System.out.println(string);
	}
}
