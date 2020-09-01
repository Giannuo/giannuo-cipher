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
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.function.Supplier;

/**
 * 非对称加密工具类接口
 *
 * @since 1.0
 */
public interface IAsymmetricCipherUtil {
    /**
     * @return 算法 用于 秘钥生成 | 签名算法
     * @since 1.0
     */
    String getAlgorithm();
    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

    /**
     * 生成秘钥对接口，算法由实现类提供
     *
     * @return Base64秘钥对
     * @since 1.0
     */
    default Base64KeyPair generateBase64Key() {
        try {
            // KeyGenerator : 秘钥生成器
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(getAlgorithm());

            // 跳过初始化

            // 生成一个密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return Base64KeyPair.of(keyPair);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

    /**
     * 生成秘钥对接口，算法由实现类提供
     *
     * @param keySize 秘钥（的比特位）长度
     * @return Base64秘钥对
     * @since 1.0
     */
    @NotNull
    default Base64KeyPair generateBase64Key(@NotNull int keySize) {
        try {
            // KeyGenerator : 秘钥生成器
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(getAlgorithm());

            // 初始化
            keyPairGen.initialize(keySize);

            // 生成一个密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return Base64KeyPair.of(keyPair);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

    /**
     * 生成秘钥对接口，算法由实现类提供
     *
     * @param keySize 秘钥（的比特位）长度
     * @param seed    （字节数组）种子
     * @return Base64秘钥对
     * @since 1.0
     */
    @NotNull
    default Base64KeyPair generateBase64Key(@NotNull int keySize, @NotNull byte[] seed) {
        try {
            // KeyGenerator : 秘钥生成器
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(getAlgorithm());

            // 初始化
            SecureRandom secureRandom = new SecureRandom(seed);
            keyPairGen.initialize(keySize, secureRandom);

            // 生成一个密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return Base64KeyPair.of(keyPair);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

    /**
     * 生成秘钥对接口，算法由实现类提供
     *
     * @param keySize 秘钥（的比特位）长度
     * @param seed    （字符串）种子
     * @param charset 字符集，你可以使用 {@code Charset.defaultCharset()}
     * @return Base64秘钥对
     * @since 1.0
     */
    default Base64KeyPair generateBase64Key(@NotNull int keySize, @NotNull String seed, @NotNull Charset charset) {
        return generateBase64Key(keySize, seed.getBytes(charset));
    }

    /**
     * 生成秘钥对接口，算法由实现类提供
     *
     * @param param 算法参数
     * @param <T>   AlgorithmParameterSpec 算法参数规范接口
     * @return Base64秘钥对
     * @since 1.0
     */
    @NotNull
    default <T extends AlgorithmParameterSpec> Base64KeyPair generateBase64Key(@NotNull T param) {
        try {
            // KeyGenerator : 秘钥生成器
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(getAlgorithm());

            // 初始化
            keyPairGen.initialize(param);

            // 生成一个密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return Base64KeyPair.of(keyPair);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

    /**
     * 生成秘钥对接口，算法由实现类提供
     *
     * @param param 算法参数
     * @param seed  （字节数组）种子
     * @param <T>   AlgorithmParameterSpec 算法参数规范接口
     * @return Base64秘钥对
     * @since 1.0
     */
    @NotNull
    default <T extends AlgorithmParameterSpec> Base64KeyPair generateBase64Key(@NotNull T param, @NotNull byte[] seed) {
        try {
            // KeyGenerator : 秘钥生成器
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(getAlgorithm());

            // 初始化
            keyPairGen.initialize(param, new SecureRandom(seed));

            // 生成一个密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return Base64KeyPair.of(keyPair);
        } catch (Exception e) {
            throw Assert.toRuntimeException(e);
        }
    }

    /**
     * @param param   算法参数
     * @param seed    （字节串）种子
     * @param charset 字符集，你可以使用 {@code Charset.defaultCharset()}
     * @param <T>     AlgorithmParameterSpec 算法参数规范接口
     * @return Base64秘钥对
     * @since 1.0
     */
    @NotNull
    default <T extends AlgorithmParameterSpec> Base64KeyPair generateBase64Key(@NotNull T param, @NotNull String seed, @NotNull Charset charset) {
        return generateBase64Key(param, seed.getBytes(charset));
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


    /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */

}
