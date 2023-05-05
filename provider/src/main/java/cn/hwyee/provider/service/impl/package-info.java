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

class PackageArgs{
    private PackageArgs(){}
    public static final String PACKAGE_NAME = "service实现包";
}