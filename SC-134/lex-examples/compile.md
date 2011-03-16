Examples from http://ds9a.nl/lex-yacc/cvs/lex-yacc-howto.html
- - -
lex example1.l

cc lex.yy.c -o example1 -ll
- - -
lex example4.l

yacc -d example4.y

cc lex.yy.c y.tab.c -o example4