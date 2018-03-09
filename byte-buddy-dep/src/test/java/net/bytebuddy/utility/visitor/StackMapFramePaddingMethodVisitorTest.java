package net.bytebuddy.utility.visitor;

import net.bytebuddy.test.utility.MockitoRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.mockito.Mockito.inOrder;

public class StackMapFramePaddingMethodVisitorTest {

    @Rule
    public TestRule mockitoRule = new MockitoRule(this);

    @Mock
    private MethodVisitor delegate;

    @Test
    public void testSubsequentFramesNoPadding() {
        MethodVisitor methodVisitor = new StackMapFramePaddingMethodVisitor(Opcodes.ASM6, delegate);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        InOrder inOrder = inOrder(delegate);
        inOrder.verify(delegate).visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        inOrder.verify(delegate).visitInsn(Opcodes.NOP);
        inOrder.verify(delegate).visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testSubsequentFramesPadding() {
        MethodVisitor methodVisitor = new StackMapFramePaddingMethodVisitor(Opcodes.ASM6, delegate);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        InOrder inOrder = inOrder(delegate);
        inOrder.verify(delegate).visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        inOrder.verify(delegate).visitInsn(Opcodes.ICONST_0);
        inOrder.verify(delegate).visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        inOrder.verifyNoMoreInteractions();
    }
}
