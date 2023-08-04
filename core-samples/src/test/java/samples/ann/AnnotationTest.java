package samples.ann;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

public class AnnotationTest {
    @Test
    public void test() {
        //for (Annotation annotation : AnnService.class.getAnnotations()) {
        //    System.out.println(annotation);
        //}

        for (Annotation declaredAnnotation : AnnService.class.getDeclaredAnnotations()) {
            System.out.println(declaredAnnotation);
        }
    }
}
