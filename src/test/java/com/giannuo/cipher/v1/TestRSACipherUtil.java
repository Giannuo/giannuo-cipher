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

import com.giannuo.cipher.Base64KeyPair;
import com.giannuo.cipher.RSACipherUtil;
import com.giannuo.cipher.RSADecrypt;
import com.giannuo.cipher.RSAEncrypt;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.spec.RSAKeyGenParameterSpec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRSACipherUtil {
    RSACipherUtil util = RSACipherUtil.SINGLETON;

    @Before
    public void before() {
        System.out.println(">>>>>>>>");
    }

    @After
    public void after() {
        System.out.println("<<<<<<<<");
    }

    @Test
    public void test1_generateBase64Key() {
        Base64KeyPair keyPair = util.generateBase64Key();
        System.out.println("base64 public key : " + keyPair.getBase64PublicKey());
        System.out.println("base64 private key : " + keyPair.getBase64PrivateKey());
    }

    @Test
    public void test2_generateBase64KeyWithKeySize() {
        Base64KeyPair keyPair = util.generateBase64Key(512);
        System.out.println("base64 public key : " + keyPair.getBase64PublicKey());
        System.out.println("base64 private key : " + keyPair.getBase64PrivateKey());
    }

    @Test
    public void test3_generateBase64KeyWithKeySize() {
        Base64KeyPair keyPair = util.generateBase64Key(1024);
        System.out.println("base64 public key : " + keyPair.getBase64PublicKey());
        System.out.println("base64 private key : " + keyPair.getBase64PrivateKey());
    }

    @Test
    public void test4_generateBase64KeyWithWrongKeySize() {
        //1.如果抛出的异常与设定的异常相同，则这一步的断言成功并返回一个异常的顶级父类
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            Base64KeyPair keyPair = util.generateBase64Key(511);
            System.out.println(keyPair);
        });
        //2.接下来便可以对返回的异常进行一些其他的测试
        //比如对异常的信息进行断言测试等。。
        assertEquals("java.security.InvalidParameterException: RSA keys must be at least 512 bits long", exception.getMessage());
        System.out.println("OK");
    }

    @Test
    public void test5_generateBase64KeyWithKeySizeAndSeed() {
        String seed = "Hello,I'm Giannuo!";
        Charset charset = StandardCharsets.UTF_8;
        Base64KeyPair keyPair = util.generateBase64Key(512, seed, charset);
        System.out.println("base64 public key : " + keyPair.getBase64PublicKey());
        System.out.println("base64 private key : " + keyPair.getBase64PrivateKey());
    }


    @Test
    public void test6_generateBase64KeyWithKeySizeAndParm() {
        String seed = "Hello,I'm Giannuo!";
        Charset charset = StandardCharsets.UTF_8;
        RSAKeyGenParameterSpec param = new RSAKeyGenParameterSpec(512, RSAKeyGenParameterSpec.F4);
        Base64KeyPair keyPair = util.generateBase64Key(param, seed, charset);
        System.out.println("base64 public key : " + keyPair.getBase64PublicKey());
        System.out.println("base64 private key : " + keyPair.getBase64PrivateKey());
    }

    @Test
    public void test7_generateBase64KeyWithParm() {
        RSAKeyGenParameterSpec param = new RSAKeyGenParameterSpec(512, RSAKeyGenParameterSpec.F0);
        Base64KeyPair keyPair = util.generateBase64Key(param);
        System.out.println("base64 public key : " + keyPair.getBase64PublicKey());
        System.out.println("base64 private key : " + keyPair.getBase64PrivateKey());
    }

    @Test
    public void test8_Cipher() {
        String data = "我是一只小小小小鸟，想要飞呀飞，却飞也飞不高~我寻寻觅觅，寻寻觅觅，一个温暖的怀抱……";
        System.out.println("data : " + data);
        Base64KeyPair keyPair = util.generateBase64Key();
        String base64PublicKey = keyPair.getBase64PublicKey();
        String base64PrivateKey = keyPair.getBase64PrivateKey();
        System.out.println("base64 public key : " + base64PublicKey);
        System.out.println("base64 private key : " + base64PrivateKey);
        Charset charset = StandardCharsets.UTF_8;
        String charsetName = charset.displayName();

        System.out.println("cipher Encrypt start");
        String secretData = util.encrypt(data, new RSAEncrypt(base64PublicKey, charsetName));
        System.out.println("cipher Encrypt - secret data : " + secretData);
        System.out.println("cipher Encrypt end");

        System.out.println("cipher Decrypt start");
        String unsecretData = util.decrypt(secretData, () -> new RSADecrypt(base64PrivateKey, charsetName));
        System.out.println("cipher Decrypt - unsecret data : " + unsecretData);
        System.out.println("cipher Decrypt end");

        Assert.assertEquals("解密数据与原数据不一致", data, unsecretData);

    }
}
