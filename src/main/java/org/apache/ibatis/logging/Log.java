/**
 *    Copyright 2009-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.logging;

/**
 * mybatis封装的日志记录器
 *
 * @author Clinton Begin
 */
public interface Log {

    /**
     * 开启debug了吗
     *
     * @return
     */
    boolean isDebugEnabled();

    /**
     * 开启trace了吗
     *
     * @return
     */
    boolean isTraceEnabled();

    /**
     * 错误日志
     *
     * @param s
     * @param e
     */
    void error(String s, Throwable e);

    /**
     * 错误日志
     *
     * @param s
     */
    void error(String s);

    /**
     * debug日志
     *
     * @param s
     */
    void debug(String s);

    /**
     * 跟踪日志
     *
     * @param s
     */
    void trace(String s);

    /**
     * 警告日志
     *
     * @param s
     */
    void warn(String s);
}
