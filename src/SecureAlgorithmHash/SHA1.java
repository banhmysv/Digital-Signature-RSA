/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecureAlgorithmHash;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author banhm
 */
public class SHA1 {

    private static int BUFFER_SIZE = 2048*2048;
    

    public BigInteger md(String file) throws FileNotFoundException, NoSuchAlgorithmException, IOException {

        BufferedInputStream file2 = new BufferedInputStream(new FileInputStream(file));
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        DigestInputStream dis = new DigestInputStream(file2, md); // tạo một message digest từ dữ liệu mà được đọc từ 1stream 
        int i;
        byte[] buffer = new byte[BUFFER_SIZE];
        do {
            i = dis.read(buffer, 0, BUFFER_SIZE);

        } while (i == BUFFER_SIZE);
        md = dis.getMessageDigest();
        dis.close();
        return new BigInteger(md.digest());

    }
   
}
