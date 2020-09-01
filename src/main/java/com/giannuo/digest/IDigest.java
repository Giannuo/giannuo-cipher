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
package com.giannuo.digest;

import com.giannuo.util.Assert;
import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 摘要计算接口
 *
 * @see sun.security.provider.MD2
 * @see sun.security.provider.MD4
 * @see sun.security.provider.MD5
 * @see sun.security.provider.SHA
 * @see java.security.MessageDigest
 * @see java.security.MessageDigestSpi
 * @since 1.0
 */
public interface IDigest {
    /**
     * @return 摘要计算算法
     * @since 1.0
     */
    String getAlgorithm();

    /**
     * 计算文件摘要
     *
     * @param file 文件对象
     * @return 文件摘要
     * @since 1.0
     */
    @NotNull
    default String fileDigest(@NotNull File file) {
        if (!file.isFile()) {
            throw Assert.toRuntimeException("非文件不可计算摘要");
        }
        FileInputStream in = null;
        byte[] buffer = new byte[1024];
        int len;
        try {
            MessageDigest digest = MessageDigest.getInstance(getAlgorithm());
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e, "[" + getAlgorithm() + "]计算文件摘要出错");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    /**
     * 计算文件摘要
     *
     * @param filePath 文件路径
     * @return 文件摘要
     * @since 1.0
     */
    @NotNull
    default String fileDigest(@NotNull String filePath) {
        return fileDigest(new File(filePath));
    }


    /**
     * 相同文件比较
     *
     * @param base   （比较）基准文件
     * @param target （比较）目标文件
     * @return 比较结果
     * @since 1.0
     */
    default boolean fileEquals(@NotNull File base, @NotNull File target) {
        return base.length() == target.length() && fileDigest(base).equals(fileDigest(target));
    }

    /**
     * 相同文件比较
     *
     * @param baseFilePath   （比较）基准文件路径
     * @param targetFilePath （比较）目标文件路径
     * @return 比较结果
     * @since 1.0
     */
    default boolean fileEquals(@NotNull String baseFilePath, @NotNull String targetFilePath) {
        return fileEquals(new File(baseFilePath), new File(targetFilePath));
    }
}
