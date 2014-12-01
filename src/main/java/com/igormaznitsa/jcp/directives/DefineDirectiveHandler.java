/* 
 * Copyright 2014 Igor Maznitsa (http://www.igormaznitsa.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.igormaznitsa.jcp.directives;

import com.igormaznitsa.jcp.context.PreprocessorContext;
import com.igormaznitsa.jcp.expression.ExpressionItem;
import com.igormaznitsa.jcp.expression.ExpressionItemType;
import com.igormaznitsa.jcp.expression.ExpressionParser;
import com.igormaznitsa.jcp.expression.ExpressionTree;
import com.igormaznitsa.jcp.expression.ExpressionTreeElement;
import com.igormaznitsa.jcp.expression.Value;
import com.igormaznitsa.jcp.expression.Variable;
import java.io.IOException;

/**
 * The class implements the //#define directive handler
 *
 * @author Igor Maznitsa (igor.maznitsa@igormaznitsa.com)
 */
public class DefineDirectiveHandler extends AbstractDirectiveHandler {

  @Override
  public String getName() {
    return "define";
  }

  @Override
  public DirectiveArgumentType getArgumentType() {
    return DirectiveArgumentType.VARNAME;
  }

  @Override
  public String getReference() {
    return "define a global variable as TRUE";
  }

  @Override
  public AfterDirectiveProcessingBehaviour execute(final String string, final PreprocessorContext context) {
    String name = null;

    try {
      final ExpressionTree tree = ExpressionParser.getInstance().parse(string, context);
      if (tree.isEmpty()) {
        final String text = "There is not any variable";
        throw new IllegalArgumentException(text,context.makeException(text, null));
      }

      final ExpressionTreeElement root = tree.getRoot();
      final ExpressionItem item = root.getItem();
      if (item.getExpressionItemType() != ExpressionItemType.VARIABLE) {
        final String text = "You must use a variable as the argument";
        throw new IllegalArgumentException(text, context.makeException(text, null));
      }

      name = ((Variable) item).getName();
    }
    catch (IOException ex) {
      final String text = "Can't parse the variable name [" + string + ']';
      throw new IllegalArgumentException(text, context.makeException(text, ex));
    }

    if (context.findVariableForName(name) != null) {
      context.logWarning("Variable \'" + name + "\' was already defined");
    }

    context.setGlobalVariable(name, Value.BOOLEAN_TRUE);
    return AfterDirectiveProcessingBehaviour.PROCESSED;
  }
}
