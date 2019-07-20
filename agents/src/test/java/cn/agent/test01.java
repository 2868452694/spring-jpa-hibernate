package cn.agent;

import org.springframework.data.domain.Page;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public
class test01 {
    public static Object f(Object o, int index, int sum, List<Class> classes) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(index==sum){
            return null;
        }
        if(index==2){
            for(Class c:classes){

                if(c.getTypeName().equals( o.getClass().getTypeName() )){
                    return null;
                }else if(c.getTypeName().equals( classes.get( classes.size()-1 ) )){
                    classes.add( o.getClass() );
                }
            }
        }

        //获取所有字段
        Class clazz=o.getClass();
        Field[] fs=clazz.getDeclaredFields();
        //遍历字段
        for(Field f:fs){
            //剔除字段: 非pojo包下的类型 和 非set类型
            String pageName=Class.forName( f.getType().getName() ).getPackage().getName();
            String na=f.getType().getName();
            if(pageName.equals( "cn.agent.pojo" )|| na.equals( "java.util.Set" )){
                String fieldName=f.getName().substring( 0,1 ).toUpperCase()+f.getName().substring( 1 );
                Object result=clazz.getMethod("get"+fieldName).invoke(o);

                //判断是否满足层数需求//满足则将这些字段设置为null
                //不满足
                //字段为集合时遍历此集合 将集合中的对象传入此方法
                if(f.getType().getName().equals( "java.util.Set" )){
                    Set set=new HashSet(  );
                    Set ss= (Set) result;
                    for(Object o1:ss){
                        Object o2=f( result,index+1,sum,classes);
                        if(o2!=null)
                            set.add(o2 );
                    }
                    Method setMethod=clazz.getMethod( "set"+fieldName,Class.forName( f.getType().getName() ));
                    setMethod.invoke( o, set);
                }
                //字段不为集合时将字段传入此方法
                if(Class.forName( f.getType().getName() ).getPackage().getName().equals( "cn.agent.pojo" )){
                    Method setMethod=clazz.getMethod( "set"+fieldName,Class.forName( f.getType().getName() ));
                    setMethod.invoke( o, f( result,index+1,sum,classes));
                }


            }

        }
        return o;
    }
    /**
     * @param o     要处理的对象
     * @param sum   忽略第几层的
     * @param index 当前是第几层
     * @return 处理过后的对象
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     */
    public static
    Object filter(Object o, int sum, int index) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        //反射
        //获取所有的属性
        Class clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Object result = o;
        for (Field field : fields) {
            //首字母大写
            String fieldName = field.getName().substring( 0, 1 ).toUpperCase() + field.getName().substring( 1 );
            //获取get和set方法
            result = clazz.getMethod( "get" + fieldName ).invoke( o );

            Class typeClass = Class.forName( field.getType().getName() );

            //当这个属性不是list set page @entity 时跳过本次循环
            /*if(result instanceof Set){
                for(Object o1:(Set)result){
                    if(typeClass.getAnnotation( Entity.class ) == null){
                        continue;
                    }
                }
            }else*/
            if (typeClass.getAnnotation( Entity.class ) == null) {
                continue;
            }
            if (!(result instanceof Set)) {
                Method setMethod = clazz.getMethod( "set" + fieldName, Class.forName( field.getType().getName() ) );
                setMethod.invoke( o, filter( result, sum, index + 1 ) );
            }else{
                Set set=(Set)result;
                Set<Object> oset=new HashSet<Object>(  );
                for(Object o1:set){
                    oset.add(  filter( o1, sum, index + 1 ));
                }

                Method setMethod = clazz.getMethod( "set" + fieldName, Class.forName( field.getType().getName() ) );
                setMethod.invoke( o,oset );
            }


            //是
            //判断当前层数
            if (sum == index) {
                return null;
            } else {
                return result;
            }
        }

        return result;
    }

}
