package CSCI5308.GroupFormationTool.Course.Service;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Multipart;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;

public class CsvMockFile {
	public static MultipartFile getFile() {
		String data = "bannerId,firstName,lastName,email,contactnumber\n";
        data += "B00854462,arjun,kh,arjunstar14@gmail.com,9123456701\n";
        data += "B00854463,arjun1,kh,arjunstar14@gmail.com,9123456701\n";
        data += "B00854464,arjun2,kh,arjunstar14@gmail.com,9123456701\n";
        String finalData = data;
        MultipartFile multipartFile = new MultipartFile() {

            @Override
            public String getName() {
                return "sample";
            }

            @Override
            public String getOriginalFilename() {
                return "sample";
            }

            @Override
            public String getContentType() {
                return MediaType.TEXT_PLAIN_VALUE;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return finalData.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return finalData.getBytes();
            }

            @Override
            public InputStream getInputStream() throws IOException {
                InputStream inputStream = new ByteArrayInputStream(finalData.getBytes());
                return inputStream;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                // not reqd
            }

	
        };
        return multipartFile;
	}

}
