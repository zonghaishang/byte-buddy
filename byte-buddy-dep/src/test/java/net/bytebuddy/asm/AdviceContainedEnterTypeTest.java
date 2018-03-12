package net.bytebuddy.asm;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class AdviceContainedEnterTypeTest {

    private static final String FOO = "foo", ENTER = "enter";

    private final Class<?> advice;

    public AdviceContainedEnterTypeTest(Class<?> advice) {
        this.advice = advice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {ObsoleteBooleanValue.class},
                {ObsoleteByteValue.class},
                {ObsoleteShortValue.class},
                {ObsoleteCharacterValue.class},
                {ObsoleteIntegerValue.class},
                {ObsoleteLongValue.class},
                {ObsoleteFloatValue.class},
                {ObsoleteDoubleValue.class},
                {ObsoleteReferenceValue.class},
                {ObsoleteBooleanValueSuppression.class},
                {ObsoleteByteValueSuppression.class},
                {ObsoleteShortValueSuppression.class},
                {ObsoleteCharacterValueSuppression.class},
                {ObsoleteIntegerValueSuppression.class},
                {ObsoleteLongValueSuppression.class},
                {ObsoleteFloatValueSuppression.class},
                {ObsoleteDoubleValueSuppression.class},
                {ObsoleteReferenceValueSuppression.class}
        });
    }

    @Test
    public void testObsoleteReturnValue() throws Exception {
        Class<?> type = new ByteBuddy()
                .redefine(Sample.class)
                .visit(Advice.to(advice).on(named(FOO)))
                .make()
                .load(ClassLoadingStrategy.BOOTSTRAP_LOADER, ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        assertThat(type.getDeclaredMethod(FOO).invoke(type.getDeclaredConstructor().newInstance()), is((Object) FOO));
        assertThat(type.getDeclaredField(ENTER).get(null), is((Object) 1));
    }

    public static class Sample {

        public static int enter;

        public String foo() {
            return FOO;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteBooleanValue {

        @Advice.OnMethodEnter
        private static boolean enter() {
            Sample.enter++;
            return false;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteByteValue {

        @Advice.OnMethodEnter
        private static byte enter() {
            Sample.enter++;
            return 0;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteShortValue {

        @Advice.OnMethodEnter
        private static short enter() {
            Sample.enter++;
            return 1;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteCharacterValue {

        @Advice.OnMethodEnter
        private static char enter() {
            Sample.enter++;
            return 1;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteIntegerValue {

        @Advice.OnMethodEnter
        private static int enter() {
            Sample.enter++;
            return 1;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteLongValue {

        @Advice.OnMethodEnter
        private static long enter() {
            Sample.enter++;
            return 1L;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteFloatValue {

        @Advice.OnMethodEnter
        private static float enter() {
            Sample.enter++;
            return 1f;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteDoubleValue {

        @Advice.OnMethodEnter
        private static double enter() {
            Sample.enter++;
            return 1;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteReferenceValue {

        @Advice.OnMethodEnter
        private static Object enter() {
            Sample.enter++;
            return null;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteBooleanValueSuppression {

        @Advice.OnMethodEnter(suppress = Throwable.class)
        private static boolean enter() {
            Sample.enter++;
            return false;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteByteValueSuppression {

        @Advice.OnMethodEnter(suppress = Throwable.class)
        private static byte enter() {
            Sample.enter++;
            return 0;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteShortValueSuppression {

        @Advice.OnMethodEnter(suppress = Throwable.class)
        private static short enter() {
            Sample.enter++;
            return 1;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteCharacterValueSuppression {

        @Advice.OnMethodEnter(suppress = Throwable.class)
        private static char enter() {
            Sample.enter++;
            return 1;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteIntegerValueSuppression {

        @Advice.OnMethodEnter(suppress = Throwable.class)
        private static int enter() {
            Sample.enter++;
            return 1;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteLongValueSuppression {

        @Advice.OnMethodEnter(suppress = Throwable.class)
        private static long enter() {
            Sample.enter++;
            return 1L;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteFloatValueSuppression {

        @Advice.OnMethodEnter(suppress = Throwable.class)
        private static float enter() {
            Sample.enter++;
            return 1f;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteDoubleValueSuppression {

        @Advice.OnMethodEnter(suppress = Throwable.class)
        private static double enter() {
            Sample.enter++;
            return 1;
        }
    }

    @SuppressWarnings("unused")
    public static class ObsoleteReferenceValueSuppression {

        @Advice.OnMethodEnter(suppress = Throwable.class)
        private static Object enter() {
            Sample.enter++;
            return null;
        }
    }
}
