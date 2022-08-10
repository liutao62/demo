package com.liutao62.design_pattern.creation;

import java.util.Collection;
import java.util.Collections;

/**
 * @see java.util.Enumeration 适配器类 搭配
 * @see Collections#enumeration(Collection) 使用
 * <p>
 * 适配器模式是一种事后的补救策略。适配器提供跟原始类不同的接口。
 * 将不兼容的接口转换为可兼容的接口，让原本由于接口不兼容
 * 而不能一起工作的类可以一起工作。适配器模式有两种实现方式:类适配器和对象适配器。
 * 其中，类适配器使用继承关系来实现，对象适配器使用组合关系来实现。
 * <p>
 * 使用场景：
 * 封装有缺陷的接口设计
 * 统一多个类的接口设计
 * 替换依赖的外部系统
 * 兼容老版本接口
 * 适配不同格式的数据
 */
public class Adapter {
}
