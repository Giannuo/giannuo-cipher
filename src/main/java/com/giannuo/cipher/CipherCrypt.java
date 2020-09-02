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
import javax.crypto.CipherInputStream;
import java.io.*;
import java.nio.charset.Charset;

/**
 * 密码器（抽象类）
 *
 * @since 1.0
 */
abstract class CipherCrypt {
    /**
     * 算法名称
     *
     * @since 1.0
     */
    protected final String algorithm;
    /**
     * 字符集
     *
     * @since 1.0
     */
    protected final Charset charset;
    /**
     * JCE密码器实例
     *
     * @since 1.0
     */
    protected final Cipher cipher;


    /**
     * 默认`密码器（抽象类）`构造
     *
     * @param algorithm             算法名称
     * @param charset               字符集
     * @param specificAlgorithmName 明确的算法名称
     * @since 1.0
     */
    protected CipherCrypt(@NotNull String algorithm, @NotNull Charset charset, @NotNull String specificAlgorithmName) {
        this.algorithm = algorithm;
        this.charset = charset;
        try {
            this.cipher = Cipher.getInstance(specificAlgorithmName);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

    /**
     * 密码器初始化
     *
     * @return cipher 密码器
     * @since 1.0
     */
    protected abstract Cipher cipherInit();

    /**
     * 根据cipherInit初始化的模式（加密/解密）处理数据文件
     *
     * @param sourceFilePath 待处理（加密/解密）数据文件路径
     * @param destFilePath   处理（加密/解密）结果数据文件路径
     * @return 处理（加密/解密）结果文件
     * @since 1.0
     */
    public File cryptFile(@NotNull String sourceFilePath, @NotNull String destFilePath) {
        File sourceFile = new File(sourceFilePath);
        File destFile = new File(destFilePath);
        Assert.state(sourceFile.exists(), "源文件[" + sourceFilePath + "]不存在");
        Assert.state(!sourceFile.isDirectory(), "源文件[" + sourceFilePath + "]类型错误，不可以是目录");
        Assert.state(sourceFile.isFile(), "源文件[" + sourceFilePath + "]类型错误，非文件");

        final InputStream in;
        OutputStream out = null;
        CipherInputStream cin = null;
        try {
            // 密码器初始化
            cipherInit();
            in = new FileInputStream(sourceFile);
            out = new FileOutputStream(destFile);
            cin = new CipherInputStream(in, cipher);
            byte[] buffer = new byte[512];
            int i;
            while ((i = cin.read(buffer)) != -1) {
                out.write(buffer, 0, i);
            }

            return destFile;
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (cin != null) {
                    cin.close();//closed `in` and `cin`
                }
            } catch (Exception ignored) {
            }
        }
    }
}
