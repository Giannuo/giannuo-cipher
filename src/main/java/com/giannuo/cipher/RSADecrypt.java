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
import org.jetbrains.annotations.NotNull;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * （AES）解密器
 *
 * @since 1.0
 */
public final class RSADecrypt extends CipherDecrypt implements ICommonBlockHandle {
    /**
     * 秘钥规则
     *
     * @since 1.0
     */
    private final PrivateKey privateKey;


    /**
     * (AES)加密器构造
     *
     * @param base64PublicKey Base64公钥
     * @param charsetName     String  指定字符集名称
     * @since 1.0
     */
    public RSADecrypt(@NotNull String base64PublicKey, @NotNull String charsetName) {
        this(base64PublicKey, Charset.forName(charsetName));
    }

    /**
     * (AES)加密器构造
     *
     * @param base64PrivateKey Base64公钥
     * @param charset          Charset 指定字符集
     * @since 1.0
     */
    public RSADecrypt(@NotNull String base64PrivateKey, @NotNull Charset charset) {
        super(CipherConsts.ALGORITHM_RSA, charset, CipherConsts.ALGORITHM_RSA);
        try {
            // PKCS#8
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey));
            // RSAKeyFactory
            KeyFactory keyFactory = KeyFactory.getInstance(CipherConsts.ALGORITHM_RSA);
            // RSA privateKey
            this.privateKey = keyFactory.generatePrivate(privateKeySpec);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e, "解密私钥（" + base64PrivateKey + "）初始化失败");
        }
    }

    /**
     * 密码器初始化（初始化为解密器）
     *
     * @return cipher 密码器
     * @since 1.0
     */
    @Override
    protected Cipher cipherInit() {
        try {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher;
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

    /**
     * 解密（字节数组）数据
     *
     * @param data 待解密（字节数组）数据
     * @return 加密结果（字节数组）数据
     * @since 1.0
     */
    @NotNull
    @Override
    public byte[] decrypt(@NotNull byte[] data) {
        cipherInit();
        return rsaBlockCodec(cipher, Cipher.DECRYPT_MODE, data, ((RSAPrivateKey) privateKey).getModulus().bitLength());
    }
}
