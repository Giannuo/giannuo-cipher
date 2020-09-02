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

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.function.Supplier;

/**
 * 对称加密工具类接口
 *
 * @since 1.0
 */
public interface ISymmetricCipherUtil {
    /**
     * @return KeyGenerator秘钥生成的实际算法
     * @since 1.0
     */
    String getKeyGeneratorAlgorithm();
    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

    /**
     * 生成秘钥接口，算法由实现类提供
     *
     * @return Base64秘钥
     * @since 1.0
     */
    default String generateBase64Key() {
        try {
            // KeyGenerator : 秘钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance(getKeyGeneratorAlgorithm());
            // keyGenerator.init(keySize);
            SecretKey sk = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

    /**
     * 生成秘钥接口，算法由实现类提供
     *
     * @param keySize 秘钥（的比特位）长度
     * @return Base64秘钥
     * @since 1.0
     */
    @NotNull
    default String generateBase64Key(int keySize) {
        try {
            // KeyGenerator : 秘钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance(getKeyGeneratorAlgorithm());
            keyGenerator.init(keySize);
            SecretKey sk = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

    /**
     * 生成秘钥接口，算法由实现类提供
     *
     * @param seed （字节数组）种子
     * @return Base64秘钥
     * @since 1.0
     */
    @NotNull
    default String generateBase64Key(@NotNull byte[] seed) {
        try {
            // KeyGenerator : 秘钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance(getKeyGeneratorAlgorithm());
            keyGenerator.init(new SecureRandom(seed));
            SecretKey sk = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

    /**
     * 生成秘钥接口，算法由实现类提供
     *
     * @param seed    （字符串）种子
     * @param charset 字符集
     * @return Base64秘钥
     * @since 1.0
     */
    default String generateBase64Key(@NotNull String seed, @NotNull Charset charset) {
        return generateBase64Key(seed.getBytes(charset));
    }

    /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

    /**
     * 加密数据
     *
     * @param data    待加密（字符串）数据
     * @param encrypt 加密器
     * @return 加密结果Base64数据
     * @since 1.0
     */
    @NotNull
    default String encrypt(@NotNull String data, @NotNull CipherEncrypt encrypt) {
        return encrypt.encryptBase64(data);
    }


    /**
     * 加密数据文件
     *
     * @param sourceFilePath 待加密数据文件路径
     * @param destFilePath   加密结果数据文件路径
     * @param encrypt        加密器
     * @return 加密结果文件
     * @see ISymmetricCipherUtil#encryptFile(String, String, CipherEncrypt)
     * @since 1.0
     */
    default File encryptFile(@NotNull String sourceFilePath, @NotNull String destFilePath, @NotNull CipherEncrypt encrypt) {
        return encrypt.encryptFile(sourceFilePath, destFilePath);
    }

    /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

    /**
     * 解密数据
     *
     * @param data            待解密（Base64字符串）数据
     * @param decryptSupplier 解密器Suppiler
     * @return 解密结果数据
     * @since 1.0
     */
    @NotNull
    default String decrypt(@NotNull String data, @NotNull Supplier<CipherDecrypt> decryptSupplier) {
        return decrypt(data, decryptSupplier.get());
    }

    /**
     * 加密数据
     *
     * @param data    待解密（Base64字符串）数据
     * @param decrypt 解密器
     * @return 解密结果数据
     * @since 1.0
     */
    @NotNull
    default String decrypt(@NotNull String data, @NotNull CipherDecrypt decrypt) {
        return decrypt.decryptBase64(data);
    }

    /**
     * 解密数据文件
     *
     * @param sourceFilePath  待解密数据文件路径
     * @param destFilePath    解密结果数据文件路径
     * @param decryptSupplier 解密器Suppiler
     * @return 解密结果数据
     * @since 1.0
     */
    @NotNull
    default File decryptFile(@NotNull String sourceFilePath, @NotNull String destFilePath, @NotNull Supplier<CipherDecrypt> decryptSupplier) {
        return decryptFile(sourceFilePath, destFilePath, decryptSupplier.get());
    }

    /**
     * 解密数据文件
     *
     * @param sourceFilePath 待解密数据文件路径
     * @param destFilePath   解密结果数据文件路径
     * @param decrypt        解密器
     * @return 解密结果文件
     * @since 1.0
     */
    default File decryptFile(@NotNull String sourceFilePath, @NotNull String destFilePath, @NotNull CipherDecrypt decrypt) {
        return decrypt.decryptFile(sourceFilePath, destFilePath);
    }

    /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
}
