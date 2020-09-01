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
package com.giannuo.cipher;

import com.giannuo.util.Assert;
import com.sun.istack.internal.NotNull;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA签名工具类
 *
 * @since 1.0
 */
public enum RSASignUtil {
    // （枚举模式）单例
    SINGLETON;

    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
    // 签名

    /**
     * 签名
     *
     * @param base64PrivateSignKey 签名私钥
     * @param data                 待签名数据
     * @param charset              字符集
     * @return 签名结果
     * @since 1.0
     */
    public String sign(@NotNull String base64PrivateSignKey, @NotNull String data, @NotNull Charset charset) {
        byte[] result = sign(base64PrivateSignKey, data.getBytes(charset));
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * 签名
     *
     * @param base64PrivateSignKey 签名私钥
     * @param data                 待签名数据
     * @return 签名结果
     * @since 1.0
     */
    public byte[] sign(String base64PrivateSignKey, byte[] data) {
        // 签名器
        final Signature signature;
        try {
            signature = Signature.getInstance(CipherConsts.ALGORITHM_RSA_SIGNATURE_ALGORITHM_NAME);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e, "(" + CipherConsts.ALGORITHM_RSA_SIGNATURE_ALGORITHM_NAME + ")签名算法实例获取失败");
        }

        // 签名私钥
        final PrivateKey privateSignKey;
        try {
            // PKCS#8
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateSignKey));
            // RSAKeyFactory
            KeyFactory keyFactory = KeyFactory.getInstance(CipherConsts.ALGORITHM_RSA);
            // RSA privateKey
            privateSignKey = keyFactory.generatePrivate(privateKeySpec);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e, "(" + CipherConsts.ALGORITHM_RSA + ")签名私钥（" + base64PrivateSignKey + "）初始化失败");
        }

        // 签名
        try {
            signature.initSign(privateSignKey);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            throw Assert.toRuntimeException(e, "(" + CipherConsts.ALGORITHM_RSA_SIGNATURE_ALGORITHM_NAME + ")签名失败");
        }
    }
    /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
    // 验签

    /**
     * 验签
     *
     * @param base64PublicVerifyKey 验签公钥
     * @param data                  待签名数据
     * @param charset               字符集
     * @return 签名结果
     * @since 1.0
     */
    public boolean verify(@NotNull String base64PublicVerifyKey, @NotNull String data, @NotNull String sign, @NotNull Charset charset) {
        return verify(base64PublicVerifyKey, data.getBytes(charset), Base64.getDecoder().decode(sign));
    }

    /**
     * 签名
     *
     * @param base64PublicVerifyKey 验签公钥
     * @param data                  待签名数据
     * @return 签名结果
     * @since 1.0
     */
    public boolean verify(String base64PublicVerifyKey, byte[] data, byte[] sign) {
        // 签名器
        final Signature signature;
        try {
            signature = Signature.getInstance(CipherConsts.ALGORITHM_RSA_SIGNATURE_ALGORITHM_NAME);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e, "(" + CipherConsts.ALGORITHM_RSA_SIGNATURE_ALGORITHM_NAME + ")签名算法实例获取失败");
        }

        // 签名私钥
        final PublicKey publicVerifyKey;
        try {
            // X.509 是密码学里公钥证书的格式标准
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicVerifyKey));
            // RSAKeyFactory
            KeyFactory keyFactory = KeyFactory.getInstance(CipherConsts.ALGORITHM_RSA);
            // RSA publicKey
            publicVerifyKey = keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e, "(" + CipherConsts.ALGORITHM_RSA + ")验签公钥（" + base64PublicVerifyKey + "）初始化失败");
        }

        // 签名
        try {
            signature.initVerify(publicVerifyKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e, "(" + CipherConsts.ALGORITHM_RSA_SIGNATURE_ALGORITHM_NAME + ")验签失败");
        }
    }
    /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */


}
