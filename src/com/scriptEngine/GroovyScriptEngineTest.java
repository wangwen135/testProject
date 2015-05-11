package com.scriptEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class GroovyScriptEngineTest {

	public static void main(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();

		// List<ScriptEngineFactory> list = manager.getEngineFactories();
		// for (ScriptEngineFactory sef : list) {
		// System.out.println(sef.getEngineName());
		// System.out.println(sef.getEngineVersion());
		// System.out.println(sef.getLanguageName());
		// System.out.println(sef.getLanguageVersion());
		// }

		ScriptEngine engine = manager.getEngineByName("Groovy");

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("key1", 1);
			map.put("key2", 2);
			map.put("key3", 3);

			Map<String, Object> context = new HashMap<String, Object>();
			
			context.put("last", "this is last value!");
			
			map.put("GLOBAL_CONTEXT", context);

			System.out.println("context = " + context);

			//Bindings bind = engine.getBindings(ScriptContext.GLOBAL_SCOPE);
			//System.out.println(bind);
			
			SimpleBindings sbind = new SimpleBindings();
			sbind.putAll(map);

			engine.put("abc", map);
			Object o = engine.eval("GLOBAL_CONTEXT.put(\"aaa\", \"测试1全局1变量\");"
					+"println(GLOBAL_CONTEXT.get('last'));"
					+ "if(key1==1){return key1+'OK'}else{return 'NO'}", sbind);
			System.out.println("输出结果是：" + o);

			System.out.println("context = " + context);

		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
