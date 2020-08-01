package com.android.xknowledge.annotation.compiler;

import com.android.xknowledge.annotation.BindPath;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class AnnotaionCompiler extends AbstractProcessor {
    Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //获取到当前模块用用到了BindPath的节点
        //TypeElement 类节点
        //ExecutableElement 方法节点
        //VariableElement 变量节点
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(BindPath.class);
        Map<String, String> map = new HashMap<String, String>();
        for (Element element : elementsAnnotatedWith) {
            TypeElement typeElement = (TypeElement) element;
            BindPath annotation = typeElement.getAnnotation(BindPath.class);
            String key = annotation.value();
            Name activityName = typeElement.getQualifiedName();
            map.put(key, activityName + ".class");
        }

        //写文件
        if (map.size() > 0) {
            Writer writer = null;
            String activityName = "ActivityUtil" + System.currentTimeMillis();
            try {
                JavaFileObject sourFile = filer.createSourceFile("com.android.xknowledge." + activityName);
                writer = sourFile.openWriter();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("package com.android.xknowledge;\n");
                stringBuffer.append("import com.android.xknowledge.router.ARouter;\n");
                stringBuffer.append("import com.android.xknowledge.router.IRouter;\n");
                stringBuffer.append("public class " + activityName + " implements IRouter {\n" +
                        "@Override\n" +
                        "public void putActivity() {\n");
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String className = map.get(key);
                    stringBuffer.append("ARouter.getInstance().addActivity(\"" + key + "\"," + className + ");\n");
                }
                stringBuffer.append("\n}\n}");
                writer.write(stringBuffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
