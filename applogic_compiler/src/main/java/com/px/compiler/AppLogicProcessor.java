package com.px.compiler;

import com.google.auto.service.AutoService;
import com.px.annotation.AppLogic;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.px.annotation.AppLogic"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class AppLogicProcessor extends AbstractProcessor {

    private Filer filer;
    private ArrayList<AppLogicInfo> mData = new ArrayList<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        collectInfo(roundEnvironment);
        writeToFile();
        return true;
    }

    //收集所有被注解的类的信息
    private void collectInfo(RoundEnvironment roundEnvironment){
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(AppLogic.class);
        for(Element element : elements){
            AppLogicInfo appLogicInfo = new AppLogicInfo();
            boolean delay = element.getAnnotation(AppLogic.class).delay();
            int priority = element.getAnnotation(AppLogic.class).priority();

            TypeElement typeElement = (TypeElement)element;
            String classFullName = typeElement.getQualifiedName().toString();
            appLogicInfo.setClassName(classFullName);
            appLogicInfo.setDelay(delay);
            appLogicInfo.setPriority(priority);
            mData.add(appLogicInfo);
        }
    }

    //将被注解的类的信息，写到一个类""Test$$AppLogic.java""中
    public void writeToFile() {
        try {
            String packageFullName = "com.px.applogicdem";

            ClassName ArrayList = ClassName.get("java.util","ArrayList");

            ClassName appLogicInfo = ClassName.get("com.px.api","AppLogicInfo");
            TypeName arrayListType = ParameterizedTypeName.get(ArrayList, appLogicInfo);
            FieldSpec arrayList = FieldSpec.builder(arrayListType,"testArrayList")
                    .addModifiers(Modifier.PUBLIC)
                    .initializer("new $T()",arrayListType)
                    .build();

            MethodSpec.Builder methodSpec2 = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC);
           for(int i= 0;i<mData.size();i++){
               String temp = "temp"+i;
                methodSpec2.addStatement("$T "+temp+" = new $T()",appLogicInfo ,appLogicInfo)
                        .addStatement(temp+".setClassName($S)",mData.get(i).getClassName())
                        .addStatement(temp+".setDelay("+mData.get(i).isDelay()+")")
                        .addStatement(temp+".setPriority("+mData.get(i).getPriority()+")")
                        .addStatement("testArrayList.add("+temp+")");
            }
            // 构建Class
            TypeSpec typeSpec = TypeSpec.classBuilder("Test" + "$$AppLogic")
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(methodSpec2.build())
                    .addField(arrayList)
                    .build();
            JavaFile javaFile = JavaFile.builder(packageFullName, typeSpec)
                    .build();
            // 生成class文件
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
