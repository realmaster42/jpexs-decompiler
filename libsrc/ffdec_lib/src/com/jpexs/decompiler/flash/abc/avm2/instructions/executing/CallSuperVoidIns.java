/*
 *  Copyright (C) 2010-2014 JPEXS, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. */
package com.jpexs.decompiler.flash.abc.avm2.instructions.executing;

import com.jpexs.decompiler.flash.abc.ABC;
import com.jpexs.decompiler.flash.abc.avm2.AVM2Code;
import com.jpexs.decompiler.flash.abc.avm2.AVM2ConstantPool;
import com.jpexs.decompiler.flash.abc.avm2.LocalDataArea;
import com.jpexs.decompiler.flash.abc.avm2.instructions.AVM2Instruction;
import com.jpexs.decompiler.flash.abc.avm2.instructions.InstructionDefinition;
import com.jpexs.decompiler.flash.abc.avm2.model.CallSuperAVM2Item;
import com.jpexs.decompiler.flash.abc.avm2.model.FullMultinameAVM2Item;
import com.jpexs.decompiler.flash.abc.types.MethodBody;
import com.jpexs.decompiler.flash.abc.types.MethodInfo;
import com.jpexs.decompiler.graph.GraphTargetItem;
import com.jpexs.decompiler.graph.ScopeStack;
import com.jpexs.decompiler.graph.TranslateStack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CallSuperVoidIns extends InstructionDefinition {

    public CallSuperVoidIns() {
        super(0x4e, "callsupervoid", new int[]{AVM2Code.DAT_MULTINAME_INDEX, AVM2Code.DAT_ARG_COUNT});
    }

    @Override
    public void execute(LocalDataArea lda, AVM2ConstantPool constants, List<Object> arguments) {
        /*int multinameIndex = (int) ((Long) arguments.get(0)).longValue();
         int argCount = (int) ((Long) arguments.get(1)).longValue();
         List<Object> passArguments = new ArrayList<Object>();
         for (int i = argCount - 1; i >= 0; i--) {
         passArguments.set(i, lda.operandStack.pop());
         }
         //if multiname[multinameIndex] is runtime
         //pop(name) pop(ns)
         Object receiver = lda.operandStack.pop();*/
        throw new RuntimeException("Call to unknown super method");
        //do not push anything
    }

    @Override
    public void translate(boolean isStatic, int scriptIndex, int classIndex, HashMap<Integer, GraphTargetItem> localRegs, TranslateStack stack, ScopeStack scopeStack, AVM2ConstantPool constants, AVM2Instruction ins, List<MethodInfo> method_info, List<GraphTargetItem> output, MethodBody body, ABC abc, HashMap<Integer, String> localRegNames, List<String> fullyQualifiedNames, String path, HashMap<Integer, Integer> localRegsAssignmentIps, int ip, HashMap<Integer, List<Integer>> refs, AVM2Code code) {
        int multinameIndex = ins.operands[0];
        int argCount = ins.operands[1];
        List<GraphTargetItem> args = new ArrayList<>();
        for (int a = 0; a < argCount; a++) {
            args.add(0, stack.pop());
        }
        FullMultinameAVM2Item multiname = resolveMultiname(stack, constants, multinameIndex, ins);
        GraphTargetItem receiver = stack.pop();

        output.add(new CallSuperAVM2Item(ins, true, receiver, multiname, args));

    }

    @Override
    public int getStackDelta(AVM2Instruction ins, ABC abc) {
        int ret = -ins.operands[1] - 1;
        int multinameIndex = ins.operands[0];
        if (abc.constants.getMultiname(multinameIndex).needsName()) {
            ret--;
        }
        if (abc.constants.getMultiname(multinameIndex).needsNs()) {
            ret--;
        }
        return ret;
    }
}
