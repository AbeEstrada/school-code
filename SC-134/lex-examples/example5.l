%{
    #include <stdio.h>
%}
%%

[ \t]+ /* ignore whitespace */;
is|am|are {
    printf("%s: is a verb\n", yytext);
}
[a-zA-Z]+ {
    printf("%s: is not a verb\n", yytext);
}
.|\n {
    ECHO; /* normal default anyway */
}
%%

main() {
    yylex();
}