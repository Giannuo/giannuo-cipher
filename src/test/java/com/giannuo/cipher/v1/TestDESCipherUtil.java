/*
 * Copyright 2020 姚嘉乐（Giannuo）
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.giannuo.cipher.v1;

import com.giannuo.cipher.DESCipherUtil;
import com.giannuo.cipher.DESDecrypt;
import com.giannuo.cipher.DESEncrypt;
import com.giannuo.digest.MD5Util;
import com.giannuo.digest.SHAUtil;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@FixMethodOrder(MethodSorters.JVM)
public class TestDESCipherUtil {
    DESCipherUtil util = DESCipherUtil.SINGLETON;

    @Test
    public void testGenerateBase64KeyNoArgs() {
        System.out.println(">>>>>>>>>>>>>>>>\ntest for generateBase64Key()");
        String key = util.generateBase64Key();
        System.out.println("  key : " + key);
        System.out.println("<<<<<<<<<<<<<<<<\n");
    }

    @Test
    public void testGenerateBase64KeyWithKeySize() {
        int keySize = 56;

        System.out.println(">>>>>>>>>>>>>>>>\ntest for generateBase64Key(int keySize)");
        System.out.println("keySize : " + keySize);
        String key = util.generateBase64Key(keySize);
        System.out.println("=> key : " + key);
        System.out.println("<<<<<<<<<<<<<<<<\n");
    }

    @Test
    public void testGenerateBase64KeyWithSeed() {

        String seed = "【`你好` Giannuo】";
        Charset charset = StandardCharsets.UTF_8;
        String seed2 = "【`我很好` Dear】";
        Charset charset2 = Charset.forName("GBK");

        System.out.println(">>>>>>>>>>>>>>>>\ntest for generateBase64Key(String seed,Charset charset)");
        System.out.println("  seed : " + seed);
        System.out.println("  charset : " + charset);
        String key_1_1 = util.generateBase64Key(seed, charset);
        System.out.println("  key_1_1 : " + key_1_1);
        System.out.println("<<<<<<<<<<<<<<<<\n");


        System.out.println(">>>>>>>>>>>>>>>>\ntest for generateBase64Key(String seed,Charset charset)");
        System.out.println("  seed : " + seed);
        System.out.println("  charset : " + charset);
        String key_1_2 = util.generateBase64Key(seed, charset);
        System.out.println("  key_1_2 : " + key_1_2);
        System.out.println("<<<<<<<<<<<<<<<<\n");


        System.out.println(">>>>>>>>>>>>>>>>\ntest for generateBase64Key(String seed,Charset charset)");
        System.out.println("  seed2 : " + seed2 + ", diffrent from " + seed);
        System.out.println("  charset : " + charset);
        String key_2_1 = util.generateBase64Key(seed2, charset);
        System.out.println("  key_2_1 : " + key_2_1);
        System.out.println("<<<<<<<<<<<<<<<<\n");

        System.out.println(">>>>>>>>>>>>>>>>\ntest for generateBase64Key(String seed,Charset charset)");
        System.out.println("  seed : " + seed);
        System.out.println("  charset2 : " + charset2 + ", diffrent from " + charset);
        String key_2_2 = util.generateBase64Key(seed, charset2);
        System.out.println("  key_2_2 : " + key_2_2);
        System.out.println("<<<<<<<<<<<<<<<<\n");


    }


    @Test
    public void testDESCrypt() {
        System.out.println(">>>>>>>>>>>>>>>>\ntest for encrypt(String data,CipherEncrypt cipherEncrypt)/decrypt(String data,CipherDecrypt cipherDecrypt,Charset charset)");
        String data = "aaaaaaaaaaaaaaaaaaaa我是一段密文，我需要被加密";
        String charsetName = StandardCharsets.UTF_8.displayName();
        System.out.println("Encrypt original data：" + data);
        String key = util.generateBase64Key(56);

        DESEncrypt cipherEncrypt = new DESEncrypt(key, charsetName);
        System.out.println("Encrypt Base64Key ： " + cipherEncrypt.getBase64Key());
        System.out.println("Encrypt Base64Iv  ： " + cipherEncrypt.getBase64Iv());
        String secretData = util.encrypt(data, cipherEncrypt);
        System.out.println("Encrypt result secret data：" + secretData);


        System.out.println("Decrypt original secret data：" + secretData);
        String unsecretData = util.decrypt(secretData, () -> new DESDecrypt(cipherEncrypt.getBase64Key(), cipherEncrypt.getBase64Iv(), charsetName));
        System.out.println("Decrypt unsecretData data：" + unsecretData);

        Assert.assertEquals("期望的解密数据与原数据不同", data, unsecretData);

        System.out.println("<<<<<<<<<<<<<<<<\n");

    }

    @SuppressWarnings("ALL")
    @Test
    public void testDESCryptFile() {
        System.out.println(">>>>>>>>>>>>>>>>\ntest for encryptFile(String sourceFilePath,String destFilePath,CipherEncrypt cipherEncrypt)/decryptFile(String sourceFilePath,String destFilePath,CipherDecrypt cipherDecrypt,Charset charset)");
        final String baseResourcePath = getClass().getResource("/").getPath();
        System.out.println("baseResourcePath ： " + baseResourcePath);

        final String sourceFilePath$todoEncrypt = baseResourcePath + "original.txt";
        final String destFilePath$doneEncrypt = baseResourcePath + "encrypted.txt";

        String charsetName = StandardCharsets.UTF_8.displayName();
        System.out.println("Encrypt original source file path：" + sourceFilePath$todoEncrypt);
        System.out.println("Encrypt original dest file path：" + destFilePath$doneEncrypt);
        String key = util.generateBase64Key(56);

        DESEncrypt cipherEncrypt = new DESEncrypt(key, charsetName);
        System.out.println("Encrypt Base64Key ： " + cipherEncrypt.getBase64Key());
        System.out.println("Encrypt Base64Iv  ： " + cipherEncrypt.getBase64Iv());
        File encryptedFile = util.encryptFile(sourceFilePath$todoEncrypt, destFilePath$doneEncrypt, cipherEncrypt);

        final String sourceFilePath$todoDecrypt = destFilePath$doneEncrypt;
        final String destFilePath$doneDecrypt = baseResourcePath + "decrypted.txt";

        System.out.println("Decrypt original secret source file path：" + sourceFilePath$todoDecrypt);
        System.out.println("Decrypt original dest file path：" + destFilePath$doneDecrypt);
        File decryptedFile = util.decryptFile(sourceFilePath$todoDecrypt, destFilePath$doneDecrypt, () -> new DESDecrypt(cipherEncrypt.getBase64Key(), cipherEncrypt.getBase64Iv(), charsetName));

        String md5_expected = MD5Util.SINGLETON.fileDigest(sourceFilePath$todoEncrypt);
        String md5_actual = MD5Util.SINGLETON.fileDigest(destFilePath$doneDecrypt);
        System.out.println("原始文件MD5摘要 ： " + md5_expected);
        System.out.println("解密文件MD5摘要 ： " + md5_actual);
        System.out.println("文件比较 ： " + MD5Util.SINGLETON.fileEquals(sourceFilePath$todoEncrypt, destFilePath$doneDecrypt));

        Assert.assertEquals("期望的原始文件MD5摘要与解密后的MD5摘要不同", md5_expected, md5_actual);

        String sha1_expected = SHAUtil.SINGLETON.fileDigest(sourceFilePath$todoEncrypt);
        String sha1_actual = SHAUtil.SINGLETON.fileDigest(destFilePath$doneDecrypt);
        System.out.println("原始文件SHA-1摘要 ： " + sha1_expected);
        System.out.println("解密文件SHA-1摘要 ： " + sha1_actual);
        System.out.println("文件比较 ： " + SHAUtil.SINGLETON.fileEquals(sourceFilePath$todoEncrypt, destFilePath$doneDecrypt));

        Assert.assertEquals("期望的原始文件SHA-1摘要与解密后的SHA-1摘要不同", sha1_expected, sha1_actual);


        System.out.println("<<<<<<<<<<<<<<<<\n");

    }

}
