package com.bknife.mybatis.auto;

import java.lang.instrument.Instrumentation;

import com.bknife.mybatis.auto.annotations.Table;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.matcher.ElementMatchers;

public class JavaagentEntry {
    public static void premain(String agentArgs, Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.isAnnotatedWith(Table.class))
                .transform(new TableTransform())
                .installOn(inst);
    }
}
