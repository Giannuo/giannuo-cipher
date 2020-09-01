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
import java.util.Base64;

/**
 * 解密器（抽象类）
 *
 * @since 1.0
 */
public abstract class CipherDecrypt extends CipherCrypt {

    /**
     * 默认解密器（抽象类）构造
     *
     * @param algorithm             算法名称
     * @param charset               字符集
     * @param specificAlgorithmName 明确的算法名称
     * @since 1.0
     */
    protected CipherDecrypt(@NotNull String algorithm, @NotNull Charset charset, @NotNull String specificAlgorithmName) {
        super(algorithm, charset, specificAlgorithmName);
    }

    /**
     * 解密（字符串）数据
     *
     * @param base64Data 待解密数据
     * @return 加密结果数据
     * @since 1.0
     */
    @NotNull
    public String decryptBase64(@NotNull String base64Data) {
        byte[] data = Base64.getDecoder().decode(base64Data);
        byte[] result = decrypt(data);
        return new String(result, charset);
    }

    /**
     * 解密数据文件
     *
     * @param sourceFilePath 待解密数据文件路径
     * @param destFilePath   解密结果数据文件路径
     * @return 加密结果文件
     * @since 1.0
     */
    public File decryptFile(@NotNull String sourceFilePath, @NotNull String destFilePath) {
        return super.cryptFile(sourceFilePath, destFilePath);
    }

    /**
     * 解密（字节数组）数据
     *
     * @param data 待解密（字节数组）数据
     * @return 加密结果（字节数组）数据
     * @since 1.0
     */
    @NotNull
    public byte[] decrypt(@NotNull byte[] data) {
        try {
            cipherInit();
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

}
