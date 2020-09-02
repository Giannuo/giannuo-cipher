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
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * （DESede）解密器
 *
 * @since 1.0
 */
public final class DESedeDecrypt extends CipherDecrypt {
    /**
     * 秘钥规则
     *
     * @since 1.0
     */
    private final SecretKeySpec secretKeySpec;
    /**
     * 向量参数规则
     *
     * @since 1.0
     */
    private final IvParameterSpec ivParameterSpec;


    /**
     * (DESede)解密器构造
     *
     * @param base64Key   Base64秘钥
     * @param charsetName String  指定字符集名称
     * @since 1.0
     */
    public DESedeDecrypt(@NotNull String base64Key, @NotNull String base64Iv, @NotNull String charsetName) {
        this(base64Key, base64Iv, Charset.forName(charsetName));
    }

    /**
     * (DESede)解密器构造
     *
     * @param base64Key Base64秘钥
     * @param charset   Charset 指定字符集
     * @since 1.0
     */
    public DESedeDecrypt(@NotNull String base64Key, @NotNull String base64Iv, @NotNull Charset charset) {
        super(CipherConsts.ALGORITHM_DESede, charset, CipherConsts.ALGORITHM_DESede_SPECIFIC_ALGORITHM_NAME);
        this.secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(base64Key), CipherConsts.ALGORITHM_DESede);
        this.ivParameterSpec = new IvParameterSpec(Base64.getDecoder().decode(base64Iv));
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
            cipher.init(Cipher.DECRYPT_MODE, this.secretKeySpec, this.ivParameterSpec);
            return cipher;
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

}
