/**
 * Copyright 2009-2017 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ibatis.reflection;

import org.apache.ibatis.lang.UsesJava8;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * 可以获函数的方法的名字
 *
 * @author tianfeng
 */
@UsesJava8
public class ParamNameUtil {

    public static List<String> getParamNames(Method method) {
        return getParameterNames(method);
    }

    public static List<String> getParamNames(Constructor<?> constructor) {
        return getParameterNames(constructor);
    }

    private static List<String> getParameterNames(Executable executable) {
        final List<String> names = new ArrayList<>();
        final Parameter[] params = executable.getParameters();
        for (Parameter param : params) {
            names.add(param.getName());
        }
        return names;
    }

    private ParamNameUtil() {
        super();
    }

    public static void main(String[] args) throws NoSuchMethodException {

        final Class[] paramClasses = {int.class, String.class};
        final Constructor<User> constructor = User.class.getConstructor(paramClasses);
        List<String> paramNames = getParameterNames(constructor);
        int index = 0;
        for (String name : paramNames) {
            index++;
            System.err.println("index " + index + " name: " + name);
        }
    }

    public static class User {

        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}
