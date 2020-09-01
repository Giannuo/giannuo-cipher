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
import com.giannuo.cipher.RSASignUtil;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TestRSASignUtil {
    RSACipherUtil cipherUtil = RSACipherUtil.SINGLETON;
    RSASignUtil signUtil = RSASignUtil.SINGLETON;

    @Test
    public void test1_Cipher() {
        String data = "我是一只小小小小鸟，想要飞呀飞，却飞也飞不高~我寻寻觅觅，寻寻觅觅，一个温暖的怀抱……";
        System.out.println("data : " + data);

        Base64KeyPair keyPair = cipherUtil.generateBase64Key();
        String base64PublicKey = keyPair.getBase64PublicKey();
        String base64PrivateKey = keyPair.getBase64PrivateKey();
        System.out.println("base64 public key : " + base64PublicKey);
        System.out.println("base64 private key : " + base64PrivateKey);
        Charset charset = StandardCharsets.UTF_8;

        System.out.println("sign start");
        String sign = signUtil.sign(base64PrivateKey, data, charset);
        System.out.println("sign - sign data : " + sign);
        System.out.println("sign end");

        System.out.println("verify start");
        boolean verify = signUtil.verify(base64PublicKey, data, sign, charset);
        System.out.println("verify - result : " + verify);
        System.out.println("verify end");

        Assert.assertTrue("验签失败", verify);

    }

}
