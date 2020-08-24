//package com.justnow.androidsummarize.aop.apt;
//
//import com.google.auto.service.AutoService;
//
//@AutoService(Processor.class)
//public class ButterKnifeProcessor extends AbstractProcessor {
//    /**
//     * 元素相关的工具类
//     */
//    private Element elementUtils;
//    /**
//     * 文件相关的工具类
//     */
//    private Filer filer;
//    /**
//     * 日志相关的工具类
//     */
//    private Messager messager;
//    /**
//     * 类型相关工具类
//     */
//    private Types typeUtils;
//
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        return Collections.singleton(BindView.class.getCanonicalName());
//    }
//
//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.RELEASE_7;
//    }
//
//    @Override
//    public synchronized void init(ProcessingEnvironment processingEnvironment) {
//        super.init(processingEnvironment);
//        elementUtils = processingEnv.getElementUtils();
//        filer = processingEnv.getFiler();
//        messager = processingEnv.getMessager();
//        typeUtils = processingEnv.getTypeUtils();
//    }
//
//    @Override
//    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        return false;
//    }
//
//}
