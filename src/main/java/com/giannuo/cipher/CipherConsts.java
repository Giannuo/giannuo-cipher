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

/**
 * 密码器常量
 *
 * @since 1.0
 */
public interface CipherConsts {
    /**
     * 算法名：AES
     *
     * @since 1.0
     */
    String ALGORITHM_AES = "AES";

    /**
     * 算法默认向量长度：AES
     *
     * @since 1.0
     */
    int ALGORITHM_AES_DEF_IV_BYTE_SIZE = 16;

    /**
     * AES默认加密算法实例名，指明了算法/工作模式/填充模式
     * <ul>
     *     <li>加密算法：AES</li>
     *     <li>工作模式：CBC
     *     <br>
     *     密码块链接 Cipher Block Chaining，CBC，在 CBC模式中，文本块是
     *     连续加密的，在加密当前明文块之前，用前一次块加密的结果修改当前明文块。这个过程改进了加密的
     *     一些特征（例如，相同的明文块不会产生相同的密文块），但是由于其加密过程是连续的，CBC 方式
     *     不支持加密的并行化。CBC方式使用一个称作初始化向量（Initialization Vector,IV）的附加
     *     文本来开始链接过程。IV 用于修改被加密的第一个明文块。
     *     </br>
     *     <table>
     *         <tr>
     *             <td>模式</td>
     *             <td>名称</td>
     *             <td>描述</td>
     *             <td>典型应用</td>
     *         </tr>
     *         <tr>
     *             <td>电子密码本(ECB)</td>
     *             <td>Electronic CodeBook</td>
     *             <td>用相同的密钥分别对明文分组独立加密</td>
     *             <td>单个数据的安全传输(例如一个加密密钥)</td>
     *         </tr>
     *         <tr>
     *             <td>密码分组链接(CBC)</td>
     *             <td>Cipher Block Chaining</td>
     *             <td>加密算法的输入是上一个密文组合下一个明文组的异或</td>
     *             <td>面向分组的通用传输或者认证</td>
     *         </tr>
     *         <tr>
     *             <td>密文反馈(CFB)</td>
     *             <td>Cipher FeedBack</td>
     *             <td>一次处理s位，上一块密文作为加密算法的输入，产生的伪随机数输出与明文异或作为下一单元的密文</td>
     *             <td>面向分组的通用传输或者认证</td>
     *         </tr>
     *         <tr>
     *             <td>输出反馈(OFB)</td>
     *             <td>Output FeedBack</td>
     *             <td>与CFB类似，只是加密算法的输入是上一次加密的输出，并且使用整个分组</td>
     *             <td>噪声信道上的数据流的传输(如卫星通信)</td>
     *         </tr>
     *         <tr>
     *             <td>计数器(CTR)</td>
     *             <td>Counter</td>
     *             <td>每个明文分组都与一个经过加密的计数器相异或。对每个后续分组计数器递增</td>
     *             <td>面向分组的通用传输或者用于高速需求</td>
     *         </tr>
     *     </table>
     *     </li>
     *     <li>填充方式：PKCS5Padding
     *     <br>
     *     Padding指的是：块加密算法要求原文数据长度为固定块大小的整数倍，如果原文数据长度大于固定块大小，则需要在固定块填充数据直到整个块的数据是完整的。
     *     </br>
     *     </li>
     * </ul>
     * 参考：https://www.cnblogs.com/throwable/p/9480540.html
     *
     * @since 1.0
     */
    String ALGORITHM_AES_SPECIFIC_ALGORITHM_NAME = "AES/CBC/PKCS5Padding";

    /**
     * 算法名：DES
     *
     * @since 1.0
     */
    String ALGORITHM_DES = "DES";

    /**
     * 算法默认向量长度：DES
     *
     * @since 1.0
     */
    int ALGORITHM_DES_DEF_IV_BYTE_SIZE = 8;

    /**
     * DES默认加密算法实例名，指明了算法/工作模式/填充模式
     * <ul>
     *     <li>加密算法：DES</li>
     *     <li>工作模式：CBC
     *     <br>
     *     密码块链接 Cipher Block Chaining，CBC，在 CBC模式中，文本块是
     *     连续加密的，在加密当前明文块之前，用前一次块加密的结果修改当前明文块。这个过程改进了加密的
     *     一些特征（例如，相同的明文块不会产生相同的密文块），但是由于其加密过程是连续的，CBC 方式
     *     不支持加密的并行化。CBC方式使用一个称作初始化向量（Initialization Vector,IV）的附加
     *     文本来开始链接过程。IV 用于修改被加密的第一个明文块。
     *     </br>
     *     <table>
     *         <tr>
     *             <td>模式</td>
     *             <td>名称</td>
     *             <td>描述</td>
     *             <td>典型应用</td>
     *         </tr>
     *         <tr>
     *             <td>电子密码本(ECB)</td>
     *             <td>Electronic CodeBook</td>
     *             <td>用相同的密钥分别对明文分组独立加密</td>
     *             <td>单个数据的安全传输(例如一个加密密钥)</td>
     *         </tr>
     *         <tr>
     *             <td>密码分组链接(CBC)</td>
     *             <td>Cipher Block Chaining</td>
     *             <td>加密算法的输入是上一个密文组合下一个明文组的异或</td>
     *             <td>面向分组的通用传输或者认证</td>
     *         </tr>
     *         <tr>
     *             <td>密文反馈(CFB)</td>
     *             <td>Cipher FeedBack</td>
     *             <td>一次处理s位，上一块密文作为加密算法的输入，产生的伪随机数输出与明文异或作为下一单元的密文</td>
     *             <td>面向分组的通用传输或者认证</td>
     *         </tr>
     *         <tr>
     *             <td>输出反馈(OFB)</td>
     *             <td>Output FeedBack</td>
     *             <td>与CFB类似，只是加密算法的输入是上一次加密的输出，并且使用整个分组</td>
     *             <td>噪声信道上的数据流的传输(如卫星通信)</td>
     *         </tr>
     *         <tr>
     *             <td>计数器(CTR)</td>
     *             <td>Counter</td>
     *             <td>每个明文分组都与一个经过加密的计数器相异或。对每个后续分组计数器递增</td>
     *             <td>面向分组的通用传输或者用于高速需求</td>
     *         </tr>
     *     </table>
     *     </li>
     *     <li>填充方式：PKCS5Padding
     *     <br>
     *     Padding指的是：块加密算法要求原文数据长度为固定块大小的整数倍，如果原文数据长度大于固定块大小，则需要在固定块填充数据直到整个块的数据是完整的。
     *     </br>
     *     </li>
     * </ul>
     *
     * @since 1.0
     */
    String ALGORITHM_DES_SPECIFIC_ALGORITHM_NAME = "DES/CBC/PKCS5Padding";

    /**
     * 算法名：3DES/DESede
     *
     * @since 1.0
     */
    String ALGORITHM_DESede = "DESede";

    /**
     * 算法默认向量长度：3DES/DESede
     *
     * @since 1.0
     */
    int ALGORITHM_DESede_DEF_IV_BYTE_SIZE = 8;

    /**
     * 3DES/DESede默认加密算法实例名，指明了算法/工作模式/填充模式
     * <ul>
     *     <li>加密算法：3DES/DESede</li>
     *     <li>工作模式：CBC
     *     <br>
     *     密码块链接 Cipher Block Chaining，CBC，在 CBC模式中，文本块是
     *     连续加密的，在加密当前明文块之前，用前一次块加密的结果修改当前明文块。这个过程改进了加密的
     *     一些特征（例如，相同的明文块不会产生相同的密文块），但是由于其加密过程是连续的，CBC 方式
     *     不支持加密的并行化。CBC方式使用一个称作初始化向量（Initialization Vector,IV）的附加
     *     文本来开始链接过程。IV 用于修改被加密的第一个明文块。
     *     </br>
     *     <table>
     *         <tr>
     *             <td>模式</td>
     *             <td>名称</td>
     *             <td>描述</td>
     *             <td>典型应用</td>
     *         </tr>
     *         <tr>
     *             <td>电子密码本(ECB)</td>
     *             <td>Electronic CodeBook</td>
     *             <td>用相同的密钥分别对明文分组独立加密</td>
     *             <td>单个数据的安全传输(例如一个加密密钥)</td>
     *         </tr>
     *         <tr>
     *             <td>密码分组链接(CBC)</td>
     *             <td>Cipher Block Chaining</td>
     *             <td>加密算法的输入是上一个密文组合下一个明文组的异或</td>
     *             <td>面向分组的通用传输或者认证</td>
     *         </tr>
     *         <tr>
     *             <td>密文反馈(CFB)</td>
     *             <td>Cipher FeedBack</td>
     *             <td>一次处理s位，上一块密文作为加密算法的输入，产生的伪随机数输出与明文异或作为下一单元的密文</td>
     *             <td>面向分组的通用传输或者认证</td>
     *         </tr>
     *         <tr>
     *             <td>输出反馈(OFB)</td>
     *             <td>Output FeedBack</td>
     *             <td>与CFB类似，只是加密算法的输入是上一次加密的输出，并且使用整个分组</td>
     *             <td>噪声信道上的数据流的传输(如卫星通信)</td>
     *         </tr>
     *         <tr>
     *             <td>计数器(CTR)</td>
     *             <td>Counter</td>
     *             <td>每个明文分组都与一个经过加密的计数器相异或。对每个后续分组计数器递增</td>
     *             <td>面向分组的通用传输或者用于高速需求</td>
     *         </tr>
     *     </table>
     *     </li>
     *     <li>填充方式：PKCS5Padding
     *     <br>
     *     Padding指的是：块加密算法要求原文数据长度为固定块大小的整数倍，如果原文数据长度大于固定块大小，则需要在固定块填充数据直到整个块的数据是完整的。
     *     </br>
     *     </li>
     * </ul>
     *
     * @since 1.0
     */
    String ALGORITHM_DESede_SPECIFIC_ALGORITHM_NAME = "DESede/CBC/PKCS5Padding";

    /**
     * 算法名：RSA
     *
     * @since 1.0
     */
    String ALGORITHM_RSA = "RSA";


    /**
     * RSA签名算法实例名
     * <ul>
     *     <li>MD2withRSA</li>
     *     <li>MD5andSHA1withRSA</li>
     *     <li>MD5withRSA</li>
     *     <li>NONEwithRSA</li>
     *     <li>SHA1withRSA</li>
     *     <li>SHA224withRSA</li>
     *     <li>SHA256withRSA</li>
     *     <li>SHA384withRSA</li>
     *     <li>SHA512withRSA</li>
     * </ul>
     *
     * @since 1.0
     */
    String ALGORITHM_RSA_SIGNATURE_ALGORITHM_NAME = "SHA512withRSA";

}
