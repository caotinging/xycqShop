package caotinging.utils;

import java.io.IOException;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * This TypeAdapter unproxies Hibernate proxied objects, and serializes them
 * through the registered (or default) TypeAdapter of the base class.
 * 
 * HibernateProxy异常处理 在使用Hibernate时，那么很可能遇到这样的错误：
 * java.lang.UnsupportedOperationException: Attempted to serialize
 * java.lang.Class: org.hibernate.proxy.HibernateProxy. Forgot to register a
 * type adapter?
 * 因为gson在转换时是使用的反射机制，当获取的实体对象还在hibernate代理的时候，例如刚通过Id获取到，这时候获取到的便是代理对象HibernateProxy。
 * 这和直接调用实体对象的get方法不同，获取对象的属性就不能起作用。
 * 解决的方法便是将代理对象实例化，见下面的代码
 * 
 * 使用的时候将该TypeAdapter的Factory注册到GsonBuilder,参考代码代码：
 * 
 * Gson gson = new GsonBuilder().setExcludeStrategy(new TargetStrategy(Student.class);)
 * .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY) .create();
 * gson.toJson(teacher);
 * 
 */
public class HibernateProxyTypeAdapter extends TypeAdapter<HibernateProxy> {

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            return (HibernateProxy.class.isAssignableFrom(type.getRawType()) ? (TypeAdapter<T>) new HibernateProxyTypeAdapter(gson) : null);
        }
    };
    private final Gson context;

    private HibernateProxyTypeAdapter(Gson context) {
        this.context = context;
    }

    @Override
    public HibernateProxy read(JsonReader in) throws IOException {
        throw new UnsupportedOperationException("Not supported");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void write(JsonWriter out, HibernateProxy value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        // Retrieve the original (not proxy) class
        Class<?> baseType = Hibernate.getClass(value);
        // Get the TypeAdapter of the original class, to delegate the serialization
        TypeAdapter delegate = context.getAdapter(TypeToken.get(baseType));
        // Get a filled instance of the original class
        Object unproxiedValue = ((HibernateProxy) value).getHibernateLazyInitializer()
                .getImplementation();
        // Serialize the value
        delegate.write(out, unproxiedValue);
    }
}
