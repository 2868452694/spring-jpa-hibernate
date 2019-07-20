package cn.agent;

import cn.agent.pojo.Keyword;
import cn.agent.pojo.Role;
import cn.agent.pojo.Users;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test {

    public static void main(String[] as){
        //组合对象
        Users user=new Users();
        user.setRole( new Role() );
        try {
            f(user,1,3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println();

    }

    /**
     *
     * @param o 实体对象 @Eneity
     * @param index 当前的层数
     * @param sum 目标层数
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object f(Object o,int index,int sum) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(index==sum){
            return null;
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
                        Object o2=f( result,index+1,sum);
                        if(o2!=null)
                            set.add(o2 );
                    }
                    Method setMethod=clazz.getMethod( "set"+fieldName,Class.forName( f.getType().getName() ));
                    setMethod.invoke( o, set);

                }
                //字段不为集合时将字段传入此方法
                if(Class.forName( f.getType().getName() ).getPackage().getName().equals( "cn.agent.pojo" )){
                    Method setMethod=clazz.getMethod( "set"+fieldName,Class.forName( f.getType().getName() ));
                    setMethod.invoke( o, f( result,index+1,sum));
                }
            }

        }
        return o;
    }













}
