package manfred.exercises.jvm.memory;

/**
 * 用于 JOL 内存布局分析的简单数据类。
 *
 * 包含 int 和 float 两个私有字段，作为 JavaAgentDemo 和 ObjectLayout 演示中
 * ClassLayout.parseInstance 的分析对象，用于观察字段的内存偏移量和对齐填充规则。
 */
class SampleDataObject {

    private int x = 2;

    private float y = 3.0F;

}
