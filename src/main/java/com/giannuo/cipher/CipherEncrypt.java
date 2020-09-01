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

import java.io.File;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 加密器（抽象类）
 *
 * @since 1.0
 */
public abstract class CipherEncrypt extends CipherCrypt {
    /**
     * @return Base64秘钥
     * @since 1.0
     */
    abstract String getBase64Key();

    /**
     * 默认加密器（抽象类）构造
     *
     * @param algorithm             算法名称
     * @param charset               字符集
     * @param specificAlgorithmName 明确的算法名称
     * @since 1.0
     */
    protected CipherEncrypt(@NotNull String algorithm, Charset charset, @NotNull String specificAlgorithmName) {
        super(algorithm, charset, specificAlgorithmName);
    }

    /**
     * 生成随机字节数组，可用于随机向量
     *
     * @param size 字节数组长度
     * @return 随机字节数组
     * @since 1.0
     */
    @NotNull
    protected byte[] randomByteArray(int size) {
        byte[] bytes = new byte[size];
        new SecureRandom().nextBytes(bytes);
        return bytes;
    }

    /**
     * 加密（字符串）数据
     *
     * @param data 待加密数据
     * @return 加密结果Base64数据
     * @since 1.0
     */
    @NotNull
    public String encryptBase64(@NotNull String data) {
        byte[] decodedSecretData = data.getBytes(charset);
        byte[] result = encrypt(decodedSecretData);
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * 加密数据文件
     *
     * @param sourceFilePath 待加密数据文件路径
     * @param destFilePath   加密结果数据文件路径
     * @return 加密结果文件
     * @since 1.0
     */
    public File encryptFile(@NotNull String sourceFilePath, @NotNull String destFilePath) {
        return super.cryptFile(sourceFilePath, destFilePath);
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @return 加密结果
     * @since 1.0
     */
    @NotNull
    public byte[] encrypt(@NotNull byte[] data) {
        try {
            cipherInit();
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }
}
