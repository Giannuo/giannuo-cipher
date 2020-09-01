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

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * （RSA）加密器
 *
 * @since 1.0
 */
public final class RSAEncrypt extends CipherEncrypt implements ICommonBlockHandle {
    /**
     * 秘钥规则
     *
     * @since 1.0
     */
    private final PublicKey publicKey;


    /**
     * @return Base64加密公钥
     * @since 1.0
     */
    public String getBase64Key() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    /**
     * (RSA)加密器构造
     *
     * @param base64PublicKey Base64公钥
     * @param charsetName     String  指定字符集名称
     * @since 1.0
     */
    public RSAEncrypt(@NotNull String base64PublicKey, @NotNull String charsetName) {
        this(base64PublicKey, Charset.forName(charsetName));
    }

    /**
     * (RSA)加密器构造
     *
     * @param base64PublicKey Base64公钥
     * @param charset         Charset 指定字符集
     * @since 1.0
     */
    public RSAEncrypt(@NotNull String base64PublicKey, @NotNull Charset charset) {
        super(CipherConsts.ALGORITHM_RSA, charset, CipherConsts.ALGORITHM_RSA);
        try {
            // X.509 是密码学里公钥证书的格式标准
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey));
            // RSAKeyFactory
            KeyFactory keyFactory = KeyFactory.getInstance(CipherConsts.ALGORITHM_RSA);
            // RSA publicKey
            this.publicKey = keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e, "加密公钥（" + base64PublicKey + "）初始化失败");
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
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher;
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @return 加密结果
     * @since 1.0
     */
    @Override
    public byte[] encrypt(byte[] data) {
        cipherInit();
        return rsaBlockCodec(cipher, Cipher.ENCRYPT_MODE, data, ((RSAPublicKey) publicKey).getModulus().bitLength());
    }
}
