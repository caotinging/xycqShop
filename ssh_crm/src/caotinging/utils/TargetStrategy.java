package caotinging.utils;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * 这里使用google的Gson包做JSON转换，因为较早的1.4版本的FieldAttributes类中没有getDeclaringClass()这个方法，
 * 这个方法是获取field所属的类，在我的排除策略中会用到。
 * 
 * 排除策略 最简单的gson转换可以是这样的，但却没有多少实际的作用。切面日志时，一个实体和其他实体存在关联，
 * 这时候就需要通过自定义排除策略决定如何转换关联对象，否则可能出现“爆炸式”的json字符串。 Gson gson = new Gson(); int[]
 * ints = {1, 2, 3, 4, 5}; String[] strings = {"abc", "def", "ghi"};
 * 
 * // Serialization gson.toJson(ints); ==> prints [1,2,3,4,5]
 * gson.toJson(strings); ==> prints ["abc", "def", "ghi"]
 * 
 * 下面是我一个排除策略的类，能基本满足需求，从内网搬过来的，未测试 使用的时候是这样的 复制代码
 * 
 * TargetStrategy ts = new TargetStrategy(Student.class);
 * //这里表示仅转换Student中的id和name属性 ts.setFields(new String[] {"id", "name"});
 * ts.setReverse(true);
 * 
 * Gson gson = new GsonBuilder().setExcludeStrategy(ts).create();
 * gson.toJson(teacher);
 * 
 * @author caoting
 *
 */

public class TargetStrategy implements ExclusionStrategy {
    private static Logger log = LoggerFactory.getLogger(TargetStrategy.class);
    private Class<?> target;
    private String[] fields;
    private Class<?>[] clazz;
    private boolean reverse;

    public TargetStrategy(Class<?> target) {
        super();
        this.target = target;
    }

    @Override
    public boolean shouldSkipClass(Class<?> class1) {
        return false;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes fieldattributes) {
        Class<?> owner = fieldattributes.getDeclaringClass();
        Class<?> c = fieldattributes.getDeclaredClass();
        String f = fieldattributes.getName();
        boolean isSkip = false;
        
        if (owner == target) {
            if (ArrayUtils.contains(fields, f)) {
                log.debug("fitler field:{} for class:{}", f, owner);
                isSkip = true;
            }
            if (ArrayUtils.contains(clazz, c)) {
                log.debug("fitler class:{} for class:{}", c, owner);
                isSkip = true;
            }
            if (reverse) {
                isSkip = !isSkip;
            }
        }

        return isSkip;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public void setClazz(Class<?>[] clazz) {
        this.clazz = clazz;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }
}