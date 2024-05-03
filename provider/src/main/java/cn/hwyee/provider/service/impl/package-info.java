/**
 * 包注释
 * @author hui
 * @version 1.0
 * @date 2023/5/5
 * @since JDK 1.8
 */
@NonNullApi
@NonNullFields
package cn.hwyee.provider.service.impl;

import org.springframework.lang.NonNullApi;
import org.springframework.lang.NonNullFields;

/**
 * 包类:
 * 一般使用缺省的权限修饰符，只有本包的类才可以获取，使用。
 * @author hui
 * @version 1.0
 * @return
 * @date 2024/4/29 22:29
 */
class PackageArgs{
    private PackageArgs(){}
    public static final String PACKAGE_NAME = "service实现包";
}