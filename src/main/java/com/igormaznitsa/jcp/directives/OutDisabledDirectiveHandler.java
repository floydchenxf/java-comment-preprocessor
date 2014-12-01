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

import com.igormaznitsa.jcp.containers.PreprocessingFlag;
import com.igormaznitsa.jcp.context.PreprocessorContext;

/**
 * The class implements the //#- directive handler
 *
 * @author Igor Maznitsa (igor.maznitsa@igormaznitsa.com)
 */
public class OutDisabledDirectiveHandler extends AbstractDirectiveHandler {

  @Override
  public String getName() {
    return "-";
  }

  @Override
  public String getReference() {
    return "turn off preprocess result save into result file";
  }

  @Override
  public DirectiveArgumentType getArgumentType() {
    return DirectiveArgumentType.NONE;
  }

  @Override
  public AfterDirectiveProcessingBehaviour execute(final String string, final PreprocessorContext context) {
    context.getPreprocessingState().getPreprocessingFlags().add(PreprocessingFlag.TEXT_OUTPUT_DISABLED);
    return AfterDirectiveProcessingBehaviour.PROCESSED;
  }

}
