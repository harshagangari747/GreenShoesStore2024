package com.store.greenShoes.service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.*;

public class AwsS3Service {
	private static S3Client s3ClientObj;
	private final Region s3Region = Region.US_EAST_1;
	private final String s3BucketName = "web-shoes-images-bucket";
	private final String accessKey = "AKIAQ3EGQB3KCKD67FDB", secretKey = "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";
	private final AwsCredentials webShoeCredentials;
	private static AwsS3Service awsServiceObj;

	private AwsS3Service() {
		webShoeCredentials = AwsBasicCredentials.create(accessKey, secretKey);
		if (s3ClientObj == null) {
			s3ClientObj = S3Client.builder().region(s3Region)
					.credentialsProvider(StaticCredentialsProvider.create(webShoeCredentials)).build();
		}
	}

	public static AwsS3Service GetAwsServiceObj() {
		if (awsServiceObj == null) {
			awsServiceObj = new AwsS3Service();
			return awsServiceObj;
		}
		return awsServiceObj;
	}

	public void CreateAndValidateS3Bucket() {
		try {
			HeadBucketRequest bucketRequestWait = HeadBucketRequest.builder().bucket(s3BucketName).build();
			s3ClientObj.headBucket(bucketRequestWait);
			System.out.println(s3BucketName + " is already created");
		} catch (S3Exception e) {
			System.out.println(s3BucketName + " doesn't exit. Creating one!");
			CreateBucketRequest bucketRequest = CreateBucketRequest.builder().bucket(s3BucketName).build();
			s3ClientObj.createBucket(bucketRequest);
			System.out.println(s3BucketName + " is created!");
		}
	}

	public String UploadImages(MultipartFile f) throws Exception {
		//File newFile = new File(".\\TempImages\\" + f.getOriginalFilename());
		String fileName = f.getOriginalFilename();
		ByteArrayInputStream fileByteArrayInputStream;
		String url = "";
		try {
//			FileOutputStream fos = new FileOutputStream(newFile);
//			fos.write(f.getBytes());
//			fos.close();

			fileByteArrayInputStream = new ByteArrayInputStream(f.getBytes());

			PutObjectRequest request = PutObjectRequest.builder().bucket(s3BucketName).key(fileName)
					.acl(ObjectCannedACL.PUBLIC_READ).build();
			s3ClientObj.putObject(request, RequestBody.fromInputStream(fileByteArrayInputStream, f.getSize()));
			GetUrlRequest urlRequest = GetUrlRequest.builder().bucket(s3BucketName).key(fileName).build();
			url = s3ClientObj.utilities().getUrl(urlRequest).toExternalForm();
			return url;
		} catch (IOException e) {
			throw new Exception();
		}
	}

}