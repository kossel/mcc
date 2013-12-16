package com.kossel.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.InvalidPropertyException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Yichao
 * Date: 12/9/13
 * Time: 11:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyBeanUtils {

    public static void copyBeanPropertiesIgnoreNull(final Object beanA, final Object beanB){

        final BeanWrapper wrapperA = new BeanWrapperImpl(beanA);
        final BeanWrapper wrapperB = new BeanWrapperImpl(beanB);

        try{
            for(final PropertyDescriptor descriptor : wrapperB.getPropertyDescriptors()){

                final String propertyName = descriptor.getName();
                if(wrapperA.getPropertyValue(propertyName) != null && propertyName.compareToIgnoreCase("class")!=0){
                    wrapperB.setPropertyValue(propertyName,
                            wrapperA.getPropertyValue(propertyName));
                }
            }
        } catch(final /* unchecked */ InvalidPropertyException e){
            throw new IllegalArgumentException("Incompatible types: " + beanA
                    + ", " + beanB, e);
        }
    }

    public static void copyBeanPropertiesIgnoreNull2(final Object beanA, final Object beanB){
        try{
            @SuppressWarnings("unchecked") // this should be safe
            final Map<String, Object> beanAProps = PropertyUtils.describe(beanA);
            @SuppressWarnings("unchecked") // this should be safe
            final Map<String, Object> beanBProps = PropertyUtils.describe(beanB);

            if(!beanAProps.keySet().containsAll(beanBProps.keySet())){
                throw new IllegalArgumentException("Incompatible types: "
                        + beanA + ", " + beanB);
            }
            for(final Map.Entry<String, Object> entryA : beanAProps.entrySet()){
                if(beanAProps.get(entryA.getKey()) != null){
                    PropertyUtils.setMappedProperty(beanB, entryA.getKey(), entryA.getValue());
                }
            }
        } catch(final IllegalAccessException e){
            throw new IllegalStateException(e);
        } catch(final InvocationTargetException e){
            throw new IllegalStateException(e);
        } catch(final NoSuchMethodException e){
            throw new IllegalStateException(e);
        }
    }

}
