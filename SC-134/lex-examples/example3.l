%{
    #include <stdio.h>
%}

%%
sum {
    printf("IDENTIFIER ");
}
\= {
    printf("ASSIGMENT OPERATOR ");
}
\+ {
    printf("ADITION OPERATOR ");
}
[0-9]+ {
    printf("%s NUMBER ", yytext);
}
; {
    printf("END OF STATEMENT ");
}
\n {
    printf("\n");
}
[ \t]+ /* ignore whitespace */;
%%