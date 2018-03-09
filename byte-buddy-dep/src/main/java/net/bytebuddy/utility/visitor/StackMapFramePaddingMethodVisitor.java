package net.bytebuddy.utility.visitor;

import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * A method visitor that pads two subsequent stack map frames with a no-op instruction.
 */
public class StackMapFramePaddingMethodVisitor extends MethodVisitor {

    /**
     * {@code true} if the last visitation was for a stack map frame.
     */
    private boolean frameLast;

    /**
     * Creates a method visitor that pads two subsequent stack map frames.
     *
     * @param api           The API version.
     * @param methodVisitor The underlying method visitor.
     */
    public StackMapFramePaddingMethodVisitor(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    @Override
    public void visitFrame(int type, int localVariableLength, Object[] localVariable, int stackSize, Object[] stack) {
        if (frameLast) {
            super.visitInsn(Opcodes.NOP);
        }
        super.visitFrame(type, localVariableLength, localVariable, stackSize, stack);
        frameLast = true;
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
        frameLast = false;
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        super.visitIntInsn(opcode, operand);
        frameLast = false;
    }

    @Override
    public void visitVarInsn(int opcode, int variable) {
        super.visitVarInsn(opcode, variable);
        frameLast = false;
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        super.visitTypeInsn(opcode, type);
        frameLast = false;
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        super.visitFieldInsn(opcode, owner, name, descriptor);
        frameLast = false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor) {
        super.visitMethodInsn(opcode, owner, name, descriptor);
        frameLast = false;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String internalName, String descriptor, boolean isInterface) {
        super.visitMethodInsn(opcode, owner, internalName, descriptor, isInterface);
        frameLast = false;
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle handle, Object... argument) {
        super.visitInvokeDynamicInsn(name, descriptor, handle, argument);
        frameLast = false;
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        super.visitJumpInsn(opcode, label);
        frameLast = false;
    }

    @Override
    public void visitLdcInsn(Object constant) {
        super.visitLdcInsn(constant);
        frameLast = false;
    }

    @Override
    public void visitIincInsn(int variable, int increment) {
        super.visitIincInsn(variable, increment);
        frameLast = false;
    }

    @Override
    public void visitTableSwitchInsn(int minimum, int maximum, Label defaultTarget, Label... label) {
        super.visitTableSwitchInsn(minimum, maximum, defaultTarget, label);
        frameLast = false;
    }

    @Override
    public void visitLookupSwitchInsn(Label defaultTarget, int[] keys, Label[] label) {
        super.visitLookupSwitchInsn(defaultTarget, keys, label);
        frameLast = false;
    }

    @Override
    public void visitMultiANewArrayInsn(String descriptor, int dimension) {
        super.visitMultiANewArrayInsn(descriptor, dimension);
        frameLast = false;
    }
}
