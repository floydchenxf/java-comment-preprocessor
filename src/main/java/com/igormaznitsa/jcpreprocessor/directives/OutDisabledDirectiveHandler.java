package com.igormaznitsa.jcpreprocessor.directives;

public class OutDisabledDirectiveHandler  extends DirectiveHandler {

    @Override
    public String getName() {
        return "-";
    }

    @Override
    public boolean hasSpaceOrEndAfter() {
        return true;
    }
    
}
