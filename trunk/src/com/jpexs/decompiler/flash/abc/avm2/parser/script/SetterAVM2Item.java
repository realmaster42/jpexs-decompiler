/*
 * Copyright (C) 2014 petrik
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpexs.decompiler.flash.abc.avm2.parser.script;

import com.jpexs.decompiler.graph.GraphTargetItem;
import java.util.List;

/**
 *
 * @author petrik
 */
public class SetterAVM2Item extends MethodAVM2Item{

    public SetterAVM2Item(int namespaceKind, String methodName, List<String> paramNames, List<GraphTargetItem> body, List<VariableAVM2Item> subvariables) {
        super(namespaceKind, methodName, paramNames, body, subvariables);
    }
    
}