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

import com.igormaznitsa.jcp.context.PreprocessingState;
import com.igormaznitsa.jcp.context.PreprocessorContext;

/**
 * The class implements the //#postfix[+|-] directive handler
 *
 * @author Igor Maznitsa (igor.maznitsa@igormaznitsa.com)
 */
public class PostfixDirectiveHandler extends AbstractDirectiveHandler {

  @Override
  public String getName() {
    return "postfix";
  }

  @Override
  public String getReference() {
    return "turn on(+) or turn off(-) writing into result file postfix";
  }

  @Override
  public DirectiveArgumentType getArgumentType() {
    return DirectiveArgumentType.ONOFF;
  }

  @Override
  public AfterDirectiveProcessingBehaviour execute(final String string, final PreprocessorContext context) {
    final PreprocessingState state = context.getPreprocessingState();
    if (!string.isEmpty()) {
      switch (string.charAt(0)) {
        case '+': {
          state.setPrinter(PreprocessingState.PrinterType.POSTFIX);
        }
        break;
        case '-': {
          state.setPrinter(PreprocessingState.PrinterType.NORMAL);
        }
        break;
        default:{
          throw context.makeException("Unsupported ending ["+string+']', null);
        }
      }
      return AfterDirectiveProcessingBehaviour.PROCESSED;
    }
    throw context.makeException(getFullName() + " needs ending [+|-]", null);
  }
}
